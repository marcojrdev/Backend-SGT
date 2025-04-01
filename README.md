# Backend - Sistema de Gerenciamento de Tarefas

Este é o backend de um sistema de gerenciamento de tarefas, desenvolvido com Spring Boot. Ele fornece APIs REST para gerenciar tarefas, autenticação de usuários e controle de acesso. O projeto utiliza PostgreSQL como banco de dados e JWT para autenticação.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Security**
- **PostgreSQL**
- **JWT (JSON Web Token)**
- **Lombok**
- **Hibernate/JPA**

## Funcionalidades

- Gerenciamento de tarefas (CRUD)
- Autenticação e registro de usuários
- Controle de acesso baseado em roles
- Filtros de segurança com JWT
- Configuração de CORS para integração com frontend

## Configuração do Ambiente

1. **Pré-requisitos**:

   - Java 21 instalado
   - PostgreSQL configurado e rodando
   - Maven instalado (ou use o wrapper incluído no projeto)

2. **Configuração do Banco de Dados**:

   - Crie um banco de dados chamado `sgt-db` no PostgreSQL.
   - Atualize as credenciais do banco no arquivo `src/main/resources/application-dev.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/sgt-db
     spring.datasource.username=postgres
     spring.datasource.password=postgres
     ```

3. **Configuração do JWT**:
   - O segredo do JWT está configurado no arquivo `application-dev.properties`:
     ```properties
     jwt.secret=12345
     ```

## Como Rodar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd Backend
   ```

````
2. **Compile o projeto:**
```./mvnw clean install
Ou usando o Maven Wrapper
./mvnw clean install
````

3. **Execute o projeto:**

```
Usando Maven:
mvn spring-boot:run
Ou usando o Maven Wrapper:
./mvnw spring-boot:run
```
