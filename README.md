This is a Demo API created to handle Match object and some of its associated basic functionalities.

![image](https://user-images.githubusercontent.com/115825104/196260152-31007322-2edb-4db8-a9ef-4fdb39665104.png)

1. The code is developed in spring boot as a REST API which is exposing its API's at "http://localhost:8080/api".
2. Swagger API helps to display the API and quickly test them in an ongoing basis. The swagger UI can be accessed at "http://localhost:8080/swagger-ui/".

![image](https://user-images.githubusercontent.com/115825104/196259651-bffcea97-acdd-46dc-b6fa-122bd1d70977.png)

3. This code is using H2 in memory database for quick integration with logger functionalities.

![image](https://user-images.githubusercontent.com/115825104/196259909-8541b70c-6e5b-4710-9baf-9ab04da0ce3b.png)

4. Individual Controller, Entity, Services have been created to maintain separation of durties and to keep the exposed code in a separate class.
5. Proper placeholders are in place for providing documentation of each method and exposed entities. Due to time limit could not put comments on each and every method but having those provides clear idea to the reader about what the function does. Though for this code, the method names and class names are self explaining.
6. Unit tests have been written using Junit for each of the method exposed in the service.

This code does not cover following things, which should have been in any code.
1. The authentication and authorization part as its a basic code.
2. Data validations, null pointer validations, security aspects

This code assumes that the api caller is a trusted inside entity and would have allready passed the authentication and is authorized to use this api.
