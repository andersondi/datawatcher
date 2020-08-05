
# Índice

- [Sobre](#sobre)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Resultado](#resultado)
- [Como Usar](#como-usar)


## Sobre

Este projeto tem como objetivo simular um microserviço que monitora a inserção de arquivos em uma pasta.

A pasta de entrada de arquivos fica em **HOMEPATH/data/in** e os arquivos de relatório ficam em **HOMEPATH/data/out**. Estes caso estes diretórios não existam na máquina, serão criados pelo serviço.

A pasta monitorada considera apenas aquivos de texto no formato **plain text** e com extensão **.dat** para sua análise.

Há três tipos de entradas de dados lidos possíveis.

Uma linha pode conter um vendedor no formato:

```sh
  001çCPFçNameçSalary
```

Uma linha pode conter um cliente no formato:

```sh
  002çCNPJçNameçBusiness Area
```

Uma linha pode conter uma venda no formato:

```sh
  003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
```

Os dados de entrada são disposto da seguinte forma.
```sh
  001ç1234567891234çPedroç50000
  001ç3245678865434çPauloç40000.99
  002ç2345675434544345çJose da SilvaçRural
  002ç2345675433444345çEduardo PereiraçRural
  003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
  003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```
O serviço identifica cada tipo de dado e gera um arquivo de relatório com o nome no formato **(nome do arquivo processado) + .done.dat**.

O relatório resume as seguintes informações:
- Quantidade de clientes no arquivo de entrada;
- Quantidade de vendedor no arquivo de entrada;
- ID da venda mais cara;
- O pior vendedor.

O relatório também discrimina tentativas de sobrepor registros. 

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

- [Java](https://www.java.com/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [JUnit5](https://junit.org/junit5/)
- [Docker](https://www.docker.com/)
- [Gradle](https://gradle.org/)

## Resultados

O arquivo de relatório gerado tem o seguinte formato:
```sh
  Dados referentes ao arquivo testFileWithAll.dat
  04 Aug 2020 23:48:29
  ==============================
  Numero de clientes: 22
  Numero de vendedores: 25
  ID da transacao de maior valor: 12
  Vendedor com pior desempenho: Matheus
  
  
  Desconsideradas 1 entradas de clientes repetidos
  Desconsideradas 0 entradas de vendedores repetidos
  Encontradas 5 entradas invalidas
  ==============================
```
Como pode ser visto abaixo, o sistema desconsidera arquivos com nomes incorretos ou extensões incorretas.

![Peek 2020-07-31 02-29](https://user-images.githubusercontent.com/46526999/89006502-4b3b3c00-d2dd-11ea-9104-6718a42234ad.gif)

Abaixo é mostrado o processamento de um arquivo e a geração de um relátório. Quebras de linha são tratadas como entradas inválidas.

![Peek 2020-08-05 01-14](https://user-images.githubusercontent.com/46526999/89371310-5d402480-d6b9-11ea-9b29-6ecb60b51d35.gif)



## Como usar

  É **necessário** possuir o **[JDK11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)** instalado na máquina.

Faça o clone do projeto:
```sh
  $ git clone https://github.com/andersondi/datawatcher.git
```
Para fazer o _build_ da aplicação é necessário o **Gradle**. Para isso faça:
```sh
  $ cd /home/datawatcher/
  $ ./gradlew build -x test && java -jar build/libs/datawatcher-0.0.1.jar
```
Para rodar a aplicação, faça:
```sh
  $ cd /home/datawatcher/build/libs
  $ java -jar datawatcher-0.0.1-SNAPSHOT.jar
```

O serviço estará monitorando a pasta recém criada no diretório HOMEPATH/data/in.

---
Feito por [Anderson Dias]("https://www.linkedin.com/in/aodias/")