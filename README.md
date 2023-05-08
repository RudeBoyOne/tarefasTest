# Tarefas teste

 > API RESTful para gerenciamento de tarefas
-----
## Como testar a aplicação:

> A aplicação está publicada, então os testes via Sawgger e Postman irão consumir a API do deploy

1. Documentação com Swagger UI
   - [SWAGGER UI](http://tarefas.ddns.net:8080/docs/swagger-ui){:target="_blank"}

2. Postman acessando as URI's dos recursos
   - no postman importar json com as requests [arquivo](./Tarefas%20teste.postman_collection.json);

3. Localmente
   - git clone https://github.com/RudeBoyOne/tarefasTest.git
   - cd tarefas
   - modificar url, usuário e senha de conexão do banco de dados no arquivo [applications.properties](./src/main/resources/application.properties)
   - ./mvnw clean install
   - ./mvnw spring-boot:run
   - aplicação estará disponível na porta 8080

----

## Recursos disponíveis e suas respectivas URI's:

BaseURL: 
```
http://tarefas.ddns.net:8080
```

### Gerenciamento de usuários

Quem tem acesso?
* Usuários do tipo "ADMIN"


Recurso               | URI                    |  MÉTODO
----------------------|------------------------|------
Criar usuário         | /usuarios              |  POST
Atualizar usuário     | /usuarios/{idUsuario}  |  PUT
Listar todos usuários | /usuarios              |  GET
Listar um usuário     | /usuarios/{idUsuario}  |  GET
Excluir um usuário    | /usuarios/{idUsuario}  |  DELETE 


### Gerenciamento de tarefas

Quem tem acesso?
* Usuários do tipo "ADMIN" e "USER"

Recurso              | URI                          |  MÉTODO
---------------------|------------------------------|------
Criar tarefa         | /tarefas                     |  POST
Atualizar tarefa     | /tarefas/{idTarefa}          |  PUT
Concluir uma tarefa  | /tarefas/{idTarefa}/concluir |  PUT
Listar todas tarefas | /tarefas                     |  GET
Buscar uma tarefa    | /tarefas/{idTarefa}          |  GET
Excluir uma tarefa   | /tarefas/{idTarefa}          |  DELETE

----

Desenvolvido por [Lucas Ferreira Nogueira](https://github.com/RudeBoyOne), desenvolvedor backend Java.