Ecom Bookstore Story

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  A scenario to test all the categories returned
Given product service is running
When the service api <providedUrl> is invoked
Then check the statusCode <statusCode> and the response <response>
Examples:
| providedUrl                                           | statusCode |response|
|https://localhost:8060/productCatalog/allCategories     |  200       |{"categories":["Computers","Entertainment","History","Biography and Memoir"]}|
|https://localhost:8060/productCatalog/product/0132350882| 200        |{"books":[{"bookid":"0132350882","title":"CLEAN CODE: A HANDBOOK OF AGILE SOFTWARE CRAFTSMANSHIP","price":1599,"author":"Robert C. Martin","category":"Computers"}]}|				 
|https://localhost:8060/productCatalog/category/Computers| 200        |{"books":[{"bookid":"0132350882","title":"CLEAN CODE: A HANDBOOK OF AGILE SOFTWARE CRAFTSMANSHIP","price":1599,"author":"Robert C. Martin","category":"Computers"},{"bookid":"1118008189","title":"HTML AND CSS: DESIGN AND BUILD WEBSITES","price":1599,"author":"Jon Duckett","category":"Computers"}]}|				 


