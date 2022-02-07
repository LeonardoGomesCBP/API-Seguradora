Para executar o projeto via WAR basta entrar no diretório com o CMD em modo administrador e executar: "java -jar apiseguradora.war".

Necessário JAVA 10 e MYSQL(O banco será criado automaticamente pelo Hibernate/JPA)

Datas de vigência devem ser escritas em "dd-MM-yyyy" e o que definirá se o vencimento já aconteceu ou está proximo, será usado a data em tempo real.

O Swagger está configurado, basta executar o war e acessar http://localhost:8080/swagger-ui.html#/.

Na hora da requisição post, o numero da apolice pode ser deixado em branco já que será criado automaticamente.
