# Gestão de Pedidos - API
API responsável pela gestão de pedidos. Teste técnico 27487. 

## Tecnologias Utilizadas
O projeto foi implementado utilizando as seguintes tecnologias:

- **Java 21**  
- **Maven** (Apache Maven 3.9.11)  
- **Spring Framework**:
  - Spring Boot  
  - Spring Web  
  - Spring Data JPA  
- **JasperReports** para geração de relatórios  
- **Git** para versionamento de código  
- **Swagger** para documentação das APIs  

---

## Como Rodar o Projeto
O projeto, por ser Spring Boot, é inicializado pela bean `@SpringBootApplication` (**GestaoPedidosApplication**).  

Foi utilizado o Maven como gerenciador de dependências e criado scripts para facilitar a inicialização do projeto:

- **Windows**:
  - `start-dev.bat`
  - `start-prod.bat`
- **Linux**:
  - `start-linux-dev.sh`
  - `start-linux-prod.sh`

Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/index.html#/

##  Organização do Projeto
O projeto foi estruturado seguindo o padrão **Package by Feature**, facilitando a manutenção e evolução.  
Os domínios principais são:

- **Pedido**
- **Produto**
- **Relatório**

Dentro de cada domínio, o código está dividido em camadas como:
- `controllers`
- `dtos`
- `models`
- `mappers`
- `repositories`
- entre outros

As camadas de **configuração do projeto** estão concentradas em `core/config`.

---

## Conversão de Entidades e DTOs
- Utilização do **MapStruct** para conversão de entidades ↔ DTOs.

---

## Banco de Dados e Versionamento
- No profile de dev está configurado o H2.
- No profile de prod está configrado o Postgres.
- **Flyway** utilizado para versionamento de banco de dados.  
- Configuração no arquivo `application.properties`.  
- Scripts localizados em `src/main/resources/db`.

---

##  Relatórios
Os relatórios foram desenvolvidos utilizando **JasperReports** e estão localizados em:

### Relatórios disponíveis:
- **Visão de Pedidos**
  - `gestao_pedidos_relatorio.jrxml`
  - `subreport_produtos.jrxml`

- **Visão de Produtos**
  - `gestao_pedidos_grupo_produto_relatorio.jrxml`
  - `subreport_pedidos.jrxml`

---

## Como rodar testes
- Os testes não foram implementados , porém utilizaria o JUNIT com banco em memória H2 ou o Mockito. 
- Os testes seguiriam a mesma estrutura realizada de **Package by Feature** realizada no src/main/java .
