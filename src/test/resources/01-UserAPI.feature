Feature: Users
  @Regression
  Scenario: As a client I would like to create a todo.ly account
    Given I have access to https://todo.ly/

    #TEST Create user
    When I send POST request 'api/user.json' with json
    """
    {
      "Email": "user11223333@gmail.com",
      "FullName": "Pepe Blow",
      "Password": "user11223333@gmail.com"
    }
    """
    Then I expect the response code 200
    And I expect the response body is equal
    """
    {
        "Id": "EXCLUDE",
        "Email": "user11223333@gmail.com",
        "Password": null,
        "FullName": "Pepe Blow",
        "TimeZone": 0,
        "IsProUser": false,
        "DefaultProjectId": "EXCLUDE",
        "AddItemMoreExpanded": false,
        "EditDueDateMoreExpanded": false,
        "ListSortType": 0,
        "FirstDayOfWeek": 0,
        "NewTaskDueDate": -1,
        "TimeZoneId": "EXCLUDE"
    }
    """
    And I get the property value 'Id' and save on ID_USER
    And I get the property value 'Email' and save on EMAIL_USER
    And I get the property value 'DefaultProjectId' and save on DEFAULT_PROJECT_ID_USER

    #TEST Update user
    When I send PUT request 'api/user/ID_USER.json' with json
    """
    {
     "FullName": "Pepe Blow (Updated)"
    }
    """
    Then I expect the response code 200
    And I expect the response body is equal
    """
    {
      "Id": "EXCLUDE",
      "Email": "user11223333@gmail.com",
      "Password": null,
      "FullName": "Pepe Blow (Updated)",
      "TimeZone": 0,
      "IsProUser": false,
      "DefaultProjectId": "EXCLUDE",
      "AddItemMoreExpanded": false,
      "EditDueDateMoreExpanded": false,
      "ListSortType": 0,
      "FirstDayOfWeek": 0,
      "NewTaskDueDate": -1,
      "TimeZoneId": "EXCLUDE"
    }
   """