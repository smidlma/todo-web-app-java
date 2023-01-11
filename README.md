
# Categories
#### GET /api/categories - get all categories

#### GET /api/categories/{id} - get category by id

#### POST /api/categories - create new category

```json
{
    "categoryName" : "Home",
    "categoryDescription": "This category is used to identify home tasks"
}
```

#### PUT /api/categories/{id} - update category with given id

```json
{
    "categoryName" : "Homework",
    "categoryDescription": "This category is used to identify homework tasks"
}
```

#### DELETE /api/categories/{id} - delete category with given id

# Tasks

#### GET /api/tasks - get all tasks

#### GET /api/tasks/{id} - get task by id

#### POST /api/tasks - create new task

```json
{
    "taskName" : "Math",
    "taskDescription": "IDK",
    "deadline": "2023-03-12",
    "category" : {
        "categoryId" : 1
    }
}
```

#### PUT /api/tasks/{id} - update task with given id
```json
{
    "taskName" : "Math",
    "taskDescription": "IDK",
    "deadline": "2023-03-12",
    "category" : {
        "categoryId" : 2
    }
}
```

#### DELETE /api/tasks/{id} - delete task with given id




# Assignment
This is a skeleton of Spring Boot application which should be used as a start point to create a working one.
The goal of this task is to create simple web application which allows users to create TODOs via REST API.

Below you may find a proposition of the DB model:

![DB model](DBModel.png)

To complete the exercices please implement all missing classes and functonalites in order to be able to store and retrieve information about tasks and their categories.
Once you are ready, please send it to me (ie link to your git repository) before  our interview.
