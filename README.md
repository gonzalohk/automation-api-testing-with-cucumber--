## Automation API REST Testing for todo.ly app
This is a small example to set up an automation API REST test environment with Java, Gradle, JUnit, and Cucumber.

### Project 
* [http://todo.ly/](http://todo.ly/)

### Tests
##### User
* Create new User
* Get User By Id
##### Project
* Create new Project
* Get Project By Id
* Update Project By Id
* Delete Project By Id
##### Item
* Create new Item
* Get Item By Id
* Update Item By Id
* Delete Item By Id

### Run
```sh
gradle clean cucumber -Psuite=@Regression
```
