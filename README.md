# FileManagement
File Upload, List, Delete and Edit operations REST API project.

Project Techonologies:
- Java Springboot with Java 11 
- PostgreSQL 14 
- Angular 15 
- Springboot version: 2.7.9 
- API test tool: Postman 

# API Architecture
<img src="https://user-images.githubusercontent.com/32410197/222538170-b4843326-8a29-4ceb-874c-a7158de3b968.png" alt="alt text" width="330" height="600">

# Postgresql Database

Before run the API, Postgre database need to be created. 
pgAdmin 4 tool has been used for the database confirmation. 
Database name is 'files'.

A Database can be created with this way:

![image](https://user-images.githubusercontent.com/32410197/222535005-d8bfeeac-7c80-429b-b8cf-4cac432d08c7.png)

API Database View:
![image](https://user-images.githubusercontent.com/32410197/222539383-2f364253-26af-4289-876d-c628c21d5b20.png)

# API

File Management REST API Swagger document: https://app.swaggerhub.com/apis-docs/BORHAN96/file.management/1.0.0-oas3

![image](https://user-images.githubusercontent.com/32410197/222532263-0a1f1ff6-99fc-4d35-854c-d589e18818fc.png)

# Authentication

This API uses token-based authentication. To authenticate and get a token, send a POST request to /login. The response will contain a token to use for subsequent requests.

POST - http://localhost:8082/login -> This request must be requested with username and password body to generate a jwt token for the authentication header. 
API didn't has any user db credential. There is a defult username and password for the getting token:
<img src="https://user-images.githubusercontent.com/32410197/222534099-1477a81e-8769-47fc-bc61-6efba31626d8.png" alt="alt text" width="700" height="340">

# API Endpoints

Before the send request, We need to confirm the our token. It should be like this:
![image](https://user-images.githubusercontent.com/32410197/222536241-118e1845-7a43-4c83-9f9d-cc37d698c893.png)

POST - http://localhost:8082/files/upload -> Upload a file. 
File types must be one of 'png, jpeg, jpg, docx, pdf, xlsx' them. File size must be shorter than 5MB.
![image](https://user-images.githubusercontent.com/32410197/222536820-727f2b55-ee72-44a7-83a1-23186e96d44f.png)

GET - http://localhost:8082/files -> List the all of files with attributes.
![image](https://user-images.githubusercontent.com/32410197/222537326-ffd64508-68c8-43f8-9f9c-b979b98eb17a.png)

GET - http://localhost:8082/files/{id} -> Get a file with all attributes by file id.
![image](https://user-images.githubusercontent.com/32410197/222537631-99a8d4c9-0125-43de-a23c-bc44925d78db.png)

GET - http://localhost:8082/files/{id}/content -> Get a only file content as a byte array by file id 
![image](https://user-images.githubusercontent.com/32410197/222537989-97825109-8f80-4d95-91c3-c2f36c609428.png)

DELETE - http://localhost:8082/files/{id} -> Delete a file by file id.
![image](https://user-images.githubusercontent.com/32410197/222538446-9a5be08d-d31f-4c21-990e-bb248e3514c6.png)

PUT - http://localhost:8082/files/{id} -> Replace a current file with a new file by file id:
![image](https://user-images.githubusercontent.com/32410197/222538947-9f021fdc-4b0c-492a-bbe2-295dc07c64bc.png)
![image](https://user-images.githubusercontent.com/32410197/222539147-0be71c7d-80da-4ebe-bf70-4ee817460cc0.png)

