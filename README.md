Sistema Bancário - API RESTful
Uma API de sistema bancário focada em segurança, arquitetura modular e transações. O projeto simula o gerenciamento de usuários e contas, com um fluxo de autenticação seguro usando JWT (JSON Web Token).

O objetivo é demonstrar a criação de uma API back-end robusta e completa, com camadas bem definidas e foco nas melhores práticas do mercado.

Tecnologias Utilizadas
Categoria	Tecnologia	Finalidade
Linguagem	Java	A base de toda a aplicação.
Framework	Spring Boot	Facilita a criação de aplicações Spring independentes e prontas para produção.
Autenticação	Spring Security	Framework de segurança de alto nível para proteger a API.
Tokens	JWT (JSON Web Token)	Padrão de autenticação sem estado (stateless) para APIs REST.
Persistência	Spring Data JPA e Hibernate	Gerencia a comunicação com o banco de dados de forma simples e eficiente.
Banco de Dados	MySQL	Banco de dados relacional para persistir os dados da aplicação.
Infraestrutura	Docker Compose	Gerencia o container do banco de dados, garantindo um ambiente de desenvolvimento consistente.
Validação	Jakarta Validation	Para garantir a integridade dos dados de entrada.
Produtividade	Lombok	Reduz o código repetitivo (getters, setters, construtores).
