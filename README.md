# Webhook App

This repo is for simple chat application and developed by SpringBoot.

Before start please check first application.properties file located under `/main/resource/` and you can edit database configuration.

Please do not forget to change for initial DDL data insert `spring.datasource.initialization-mode=always` to `never` after first run.

````
# Database Properties
spring.datasource.url=jdbc:postgresql://localhost:9191/postgres?currentSchema=schema
spring.datasource.username=username
spring.datasource.password=password
````


## There are three main API groups
After run application open `localhost:8080/swagger-ui/` on the browser to try APIs

All API request object have own javax.validation annotations and its description for validation. ``@Email(message = "Email is not valid")``

### webhook-api
| **Name** | **Address**|**Description**|
|----------|------------|---------------|
|webhookMessageProcessor|/chat/webhook/api/{secretKey}|Send message to client that has secretKey|
|genereteKey|/chat/webhook/generatekey|Generate new secret key for webhook api|

##### Webhook Sample JSON
```
{
  "email": "string",
  "ipAddress": "string",
  "name": "string",
  "phone": "string",
  "text": "string"
}
```
```
text, email and name are required body parameters

secretKey is required path variable
```
### message-api
| **Name** | **Address**|**Description**|
|----------|------------|---------------|
|listAllConversation|/chat/message/listallconversation/{user}|List all coversation of user by id|
|listSingleConversation|/chat/message/listsingleconversation/{user}/{contact}|list single coversation of user with contact by ids|
|sendMessage|/chat/message/send|Send message to specific contact|


When User send a message to contact can use predefined placeholders. In this project 5 different placeholders defined. 

**Placeholders:** ``#TODAY``, ``#TOMORROW``,`` #YESTERDAY``, ``#BITCOINPRICE``, ``#CONTACT``

One of them is #BitcoinPrice placeholder and this placeholder use an API provided by `coindesk`
```
https://api.coindesk.com/v1/bpi/currentprice.json
```

### contact-api
| **Name** | **Address**|**Description**|
|----------|------------|---------------|
|listContact|/chat/contact/list/{userId}|List all contact by userId|
|createContact|/chat/contact/create|Create new contact for specified user|
