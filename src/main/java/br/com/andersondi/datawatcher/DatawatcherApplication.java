package br.com.andersondi.datawatcher;

import br.com.andersondi.datawatcher.Service.DirectoryMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class DatawatcherApplication {

    public static void main( String[] args ) {
        SpringApplication.run( DatawatcherApplication.class, args );
    }

    @EventListener( ApplicationReadyEvent.class )
    public void monitor() throws IOException {
        DirectoryMonitor directoryMonitor = new DirectoryMonitor();
        directoryMonitor.processEvents();
    }
}

