package br.com.andersondi.datawatcher.Service;

import br.com.andersondi.datawatcher.GetPropertyValues;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.lang.System.getProperty;
import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryMonitor {
    GetPropertyValues properties = new GetPropertyValues();
    private final String HOMEPATH = getProperty( "user.home" );
    private Path dir = Paths.get( HOMEPATH + properties.getPropValues( "inputPath" ) );
    private final WatchService watcher;
    DataReader dataReader = new DataReader();

    public DirectoryMonitor() throws IOException {
        this.generateMinimalDirectoriesStructure();
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dir.register( watcher, ENTRY_CREATE, ENTRY_MODIFY );
        dataReader.processFiles(dir);
    }

    private void generateMinimalDirectoriesStructure() throws IOException {
        new File( HOMEPATH + properties.getPropValues( "inputPath" ) ).mkdirs();
        new File( HOMEPATH + properties.getPropValues( "outputPath" ) ).mkdirs();
    }

    public void processEvents() {
        for ( ; ; ) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch ( InterruptedException x ) {
                return;
            }

            for ( WatchEvent< ? > event : key.pollEvents() ) {
                WatchEvent.Kind kind = event.kind();

                if ( kind == OVERFLOW ) {
                    continue;
                }

                WatchEvent< Path > ev = ( WatchEvent< Path > ) event;
                Path filename = ev.context();
                dir.resolve( filename );
                dataReader.processFiles(dir);
            }

            boolean valid = key.reset();
            if ( !valid ) {
                break;
            }
        }
    }
}