Feature: ItemsProject
  @Regression
  Scenario: As a user I would like to add an item in a project
    Given I have access to https://todo.ly/

    #TEST: Create Item
    When I send POST request 'api/items.json' with json
    """
    {
      "Content":"New item",
      "ProjectId": "DEFAULT_PROJECT_ID_USER"
    }
    """
    Then I expect the response code 200
    And I expect the response body is equal
    """
    {
       "Id": "EXCLUDE",
       "Content": "New item",
       "ItemType": "1",
       "Checked": false,
       "ProjectId": "DEFAULT_PROJECT_ID_USER",
       "ParentId": "null",
       "Path": "",
       "Collapsed": false,
       "DateString": "EXCLUDE",
       "DateStringPriority": "0",
       "DueDate": "",
       "Recurrence":null,
       "ItemOrder": "EXCLUDE",
       "Priority": "4",
       "LastSyncedDateTime":"EXCLUDE",
       "Children": [],
       "DueDateTime":null,
       "CreatedDate":"EXCLUDE",
       "LastCheckedDate":null,
       "LastUpdatedDate":"EXCLUDE",
       "Deleted":false,
       "Notes":"",
       "InHistory":false,
       "SyncClientCreationId":null,
       "DueTimeSpecified":true,
       "OwnerId":"ID_USER"
    }
    """
    And I get the property value 'Id' and save on ID_ITEM
    And I get the property value 'Content' and save on ITEM_NAME

    #TEST: Update Item
    When I send PUT request 'api/items/ID_ITEM.json' with json
    """
    {
       "Checked": true
    }
    """
    Then I expect the response code 200
    And I expect the response body is equal
    """
    {
       "Id": "ID_ITEM",
       "Content": "New item",
       "ItemType": "1",
       "Checked": true,
       "ProjectId": "DEFAULT_PROJECT_ID_USER",
       "ParentId": "null",
       "Path": "",
       "Collapsed": false,
       "DateString": "EXCLUDE",
       "DateStringPriority": "0",
       "DueDate": "",
       "Recurrence":null,
       "ItemOrder": "EXCLUDE",
       "Priority": "4",
       "LastSyncedDateTime":"EXCLUDE",
       "Children": [],
       "DueDateTime":null,
       "CreatedDate":"EXCLUDE",
       "LastCheckedDate":"EXCLUDE",
       "LastUpdatedDate":"EXCLUDE",
       "Deleted":false,
       "Notes":"",
       "InHistory":true,
       "SyncClientCreationId":null,
       "DueTimeSpecified":true,
       "OwnerId":"ID_USER"
    }
    """

    #TEST: GET Item by ID
    When I send GET request 'api/items/ID_ITEM.json' with json
    """
    """
    Then I expect the response code 200
    And I expect the response body is equal
    """
    {
       "Id": "ID_ITEM",
       "Content": "New item",
       "ItemType": "1",
       "Checked": true,
       "ProjectId": "DEFAULT_PROJECT_ID_USER",
       "ParentId": "null",
       "Path": "",
       "Collapsed": false,
       "DateString": "EXCLUDE",
       "DateStringPriority": "0",
       "DueDate": "",
       "Recurrence":null,
       "ItemOrder": "EXCLUDE",
       "Priority": "4",
       "LastSyncedDateTime":"EXCLUDE",
       "Children": [],
       "DueDateTime":null,
       "CreatedDate":"EXCLUDE",
       "LastCheckedDate":"EXCLUDE",
       "LastUpdatedDate":"EXCLUDE",
       "Deleted":false,
       "Notes":"",
       "InHistory":true,
       "SyncClientCreationId":null,
       "DueTimeSpecified":true,
       "OwnerId":"ID_USER"
    }
    """

    #TEST: DELETE Item by ID
    When I send POST request 'api/items.json' with json
    """
    {
      "Content":"New item to delete",
      "ProjectId": "DEFAULT_PROJECT_ID_USER"
    }
    """
    Then I expect the response code 200
    And I get the property value 'Id' and save on ID_ITEM2
    When I send PUT request 'api/items/ID_ITEM2.json' with json
    """
    {
       "Checked": true
    }
    """
    Then I expect the response code 200
    When I send DELETE request 'api/items/ID_ITEM2.json' with json
    """
    """
    Then I expect the response code 200