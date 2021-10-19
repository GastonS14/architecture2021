## FIRST OF ALL
    1- Run docker-compose
    2- Run the main class to insert data. 

## How can I run the Dockerfile ?
You must be above TP3 directory and run the following command: docker-compose up -d 


## API
#### MAIN PATHS : 
    . http://localhost:8080/university/api/students
    . http://localhost:8080/university/api/careers

#### ALL DATE FORMATS : 
    .   "DATE":"yyyy-mm-dd"
    .   Example: 
            {
               "idCarrera":11,
               "fIngreso":"2020-07-17",
               "fEgreso":"2025-12-01"
           }

#### FOR WORK IN POSTMAN 
    - There is a JSON file in src/resources/data called UniversityAPI, you must import that file into a collection in postman.
