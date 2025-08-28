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

Foi utilizado o Maven como gerenciador de dependências e criados scripts para facilitar a inicialização do projeto:

- **Windows**:
  - `start-dev.bat`
  - `start-prod.bat`
- **Linux**:
  - `start-linux-dev.sh`
  - `start-linux-prod.sh`

Projeto estático do 

Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/index.html#/

## Como rodar testes

