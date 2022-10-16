This is a Demo API created to handle Match object and some of its associated basic functionalities.

1. The code is developer in spring boot as a REST API which is exposing its API's at "http://localhost:8080/api".
2. Swagger API helps to display the API and quickly test them in an ongoing basis. The swagger UI can be accessed at "http://localhost:8080/swagger-ui/".
3. This code is using H2 in memory database for quick integration with logger functionalities.
4. Individual Controller, Entity, Services have been created to maintain separation of durties and to keep the exposed code in a separate class.
5. Proper placeholders are in place for providing documentation of each method and exposed entities. Due to time limit could not put comments on each and every method but having those provides clear idea to the reader about what the function does. Though for this code, the method names and class names are self explaining.
6. Unit tests have been written using Junit for each of the method exposed in the service.

This code does not cover following things, which should have been in any code.
1. The authentication and authorization part as its a basic code.
2. Data validations, null pointer validations, security aspects

This code assumes that the api caller is a trusted inside entity and would have allready passed the authentication and is authorized to use this api.
