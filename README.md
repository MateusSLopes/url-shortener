# Encurtador de URL
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)  
Encurtador de URL feito utilizando Java, Spring Boot, JPA, Docker e MySQL. 

Você manda a URL para ser encurtada e recebe um código responsável por redirecionar a URL original. 

```
  Exemplo:
  url: https:/google.com/
  
  Retorno:
  "asuwiolbvcx"
```

Tecnologias utilizadas:
* Java
* JPA
* Hibernate
* Docker (Para a conteinerização do banco de dados)
* MapStruct
* Jackson
* H2
* MySQL
* Junit
* Mockito

## Instalação

### Pré-requisitos
* Alguma IDE que rode Java.
* Postman/Navegador web
* Docker


### Instalação

1. Clone o repositório

   ```sh
   git clone https://github.com/MateusSLopes/url-shortener
   ```
2. Abra o projeto em sua IDE

3. No terminal, execute seguinte comando
   ```sh
     docker-compose up
   ```
   
4. Após subir o container, execute UrlShortenerApplication.

5. Para usar a API, faça requisições no endereço localhost:8080

Exemplo de JSON para o método POST:
 ```
    {
      "url": "https://google.com/"
    }
 ```
   ```JS
   POST /url - Cria uma url encurtada
   
   GET /find/{shortUri} - Redireciona para o link da página vinculada
   
   DELETE /url/{id} - Exclui a página vinculada ao ID passado
   ```
