Ecom Bookstore Story

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  A scenario to test all the categories returned
Given order service is running
When the service api <providedUrl> is invoked for order service with entity <entity>
Then check the statusCode <statusCode> match response <response>
Examples:
| providedUrl                                           |  entity|statusCode | response |
|https://localhost:8040/orderProcessService/SignUp		|{"first_name":"varun","last_name":"hm","email":"email1","billing_info":"1991 st laurent blvd","shipping_info":"1991 st alurent","password":"varun","phone":"9008228318"}]}|    200     |
|https://localhost:8040/orderProcessService/CheckAccount |{"username":"varunhm.293@gmail.com","password":"varun"}]} | 200 |
|https://localhost:8040/orderProcessService/createOrder  |{"accountId":"0789c217-4d13-47a4-9ec6-7707618496bc","shipping_info":" 119 lower street","total_price":100,"status":"PROCESSED","bookIds":["1118008189"]}]} | 200 | 
|https://localhost:8040/orderProcessService/confirmOrder |{"purchase_id":"c12a0b09-e3b1-4782-95e3-8d9d6c8e17a4","status":"ORDERED"}]}| 200|
