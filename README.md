# Customer Management System

This project is a simple web application for managing customer information. It allows users to view a list of customers, add new customers, edit existing customer details, delete customers, and search for customers based on various criteria.

## Features

- **Customer List**: View a list of all customers with their details such as first name, last name, address, email, and phone number.
- **Add Customer**: Add a new customer to the system by providing their details.
- **Edit Customer**: Modify existing customer information.
- **Delete Customer**: Remove a customer from the system.
- **Search**: Search for customers by first name, city, email, or phone number.
- **Pagination**: Navigate through the customer list using pagination controls.

## Technologies Used

- **Java**: Backend logic is implemented using Java.
- **JSP (JavaServer Pages)**: Frontend views are developed using JSP for dynamic content generation.
- **Servlets**: Handle HTTP requests and responses.
- **HTML/CSS**: For styling and structuring the web pages.
- **MySQL Database**: Store customer information.
- **Tomcat Server**: Used as the servlet container to deploy the application.

## Getting Started

1. **Create Local Database**:
    ```sql
    CREATE DATABASE sunbase;
    ```

2. **Create Customer Table**:
    ```sql
    CREATE TABLE customer (
        cid VARCHAR(45) PRIMARY KEY,
        fname VARCHAR(45),
        lname VARCHAR(45),
        street VARCHAR(45),
        address VARCHAR(45),
        city VARCHAR(20),
        state VARCHAR(20),
        email VARCHAR(45),
        phone VARCHAR(20)
    );
    ```

**To run this project locally, follow these steps**:
   
1. Download the project and import it into Eclipse IDE.
   
2. Run the application by navigating to the index.jsp file and selecting "Run on Server".
   
3. Access the application through [http://localhost:8080/SunbaseProject/index.jsp](http://localhost:8080/SunbaseProject/index.jsp)
   
4. Log in using your credentials on the prompted login page.
   
  ![page1](https://github.com/guddukrp/SunbaseProject/assets/91307403/ff43efdf-a537-4884-b89f-b2472b8f6524)
5. View the customer list upon successful login.
   
  ![page2](https://github.com/guddukrp/SunbaseProject/assets/91307403/72d3c0ac-7568-41a9-bbf2-57b15d292e36)
6. Click the "Sync" button to fetch the latest customer data from an API and store it locally.
    
  ![page3](https://github.com/guddukrp/SunbaseProject/assets/91307403/2c53af06-f020-46e1-98a7-f35e28c8dc51)
7. Add a new customer by clicking the "Add Customer" button and filling out the provided form.
   
  ![page4](https://github.com/guddukrp/SunbaseProject/assets/91307403/af2c0b68-417f-4ac6-a053-3b2650825c31)
8. Edit the details of an existing customer by clicking the "Edit" link next to the customer's information.
    
  ![page5](https://github.com/guddukrp/SunbaseProject/assets/91307403/3c6d7ac9-6b36-4340-8a0d-4b2e0176585d)
9. Delete a customer by clicking the "Delete" link .
    
  ![page2](https://github.com/guddukrp/SunbaseProject/assets/91307403/72d3c0ac-7568-41a9-bbf2-57b15d292e36)
10. Logout from Application by clicking logout..


## Demo Video


[https://github.com/guddukrp/Sunbase/assets/91307403/ec2d5386-f4e7-4080-9ed5-d8337faa7227
](https://github.com/guddukrp/SunbaseProject/assets/91307403/c6a1d670-3444-4598-bc81-0a0535f303e1
)


