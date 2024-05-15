 # LITERALURA

<p align="center">
  <img src="https://img.shields.io/static/v1?label=SPRING&message=framework&color=048C25&style=for-the-badge&logo=SPRING"/>
  <img src="https://img.shields.io/static/v1?label=LICENSE&message=MIT&color=8A2BE2&style=for-the-badge"/>
  <img src="https://img.shields.io/static/v1?label=STATUS&message=completed&color=green&style=for-the-badge"/>
  <img src="https://img.shields.io/static/v1?label=POSTGRES&message=database&color=blue&style=for-the-badge&logo=POSTGRESQL"/>
</p>

## Descrição do Projeto

O Literalura foi desenvolvido como parte do desafio da formação Java Back-End ONE, uma colaboração entre a Alura e a Oracle. Este desafio consiste em consumir uma API de livros gratuitos (estamos utilizando a GUTENDEX API), utilizando Java Spring e persistindo os dados em um banco de dados PostgreSQL. Tudo isso é feito via linha de comando, implementando a interface CommandLineRunner.

## Funcionalidades

- ✔️ Buscar dados na API e salvar no banco de dados
- ✔️ Listar livros registrados no banco de dados
- ✔️ Listar autores registrados no banco de dados
- ✔️ Listar autores vivos em determinado ano
- ✔️ Listar livros em determinado idioma
- ✔️ Listar os top 10 livros mais baixados
- ✔️ Buscar autor pelo nome
- ✔️ Buscar estatísticas dos livros
- ✔️ Listar autores nascidos em determinado ano

## Layout da Aplicação

![Layout da Aplicação](https://i.imgur.com/65SyabT.png)

## Linguagens, Dependências e Bibliotecas Utilizadas 📚

- [PostgreSQL 16](https://www.postgresql.org/about/news/postgresql-16-released-2715/)
- [IntelliJ IDEA Community](https://www.jetbrains.com/pt-br/idea/download/?section=windows)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql)
- [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

## Como Rodar a Aplicação ▶️

```bash
git clone https://github.com/kddez/one-challenge-literalura.git

mvn clean install
```
## Iniciando/Configurando o Banco de Dados e Conectando ao Projeto

Para iniciar e configurar o banco de dados e conectá-lo ao projeto, siga estas etapas:

1. **Instalação e Configuração do PostgreSQL**:
   - Certifique-se de ter o PostgreSQL instalado em seu sistema. Você pode baixá-lo em [PostgreSQL Downloads](https://www.postgresql.org/download/).
   - Após a instalação, abra o pgAdmin4.

2. **Criação do Banco de Dados**:
   - No pgAdmin, conecte-se ao seu servidor PostgreSQL.
   - Crie um novo banco de dados para o projeto.

3. **Configuração do Projeto**:
   - Com o projeto aberto em sua IDE, navegue até `src > main > resources > application.properties`.
   - Dentro do arquivo `application.properties`, defina as seguintes variáveis de ambiente de acordo com as especificações do seu banco de dados:
     ```
     DB_HOST   >>>> ex.: localhost
     DB_NAME   >>>> ex.: db_literalura
     DB_USER   >>>> ex.: user_postgres
     DB_PASSWORD >>>> ex.: 12345
     ```

4. **Pronto para Rodar a Aplicação**:
   - Após configurar corretamente as variáveis de ambiente, você está pronto para executar a aplicação.


## Licença 

The [MIT License](LICENSE) (MIT)


