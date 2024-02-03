# OpenDocAdminPlatform

## Description
OpenDocAdminPlatform is a comprehensive document management and user administration system built with **Spring Boot**. It integrates advanced functionalities like **JWT-based** user authentication, custom validation using the Validation framework, and data persistence with **MyBatis and MySQL**. The platform features user management with capabilities such as login logic, avatar updates with **AWS S3 cloud storage**, password renewal, and user information retrieval optimized with **ThreadLocal**. Additionally, it offers document categorization and management with CRUD operations, **conditional pagination** for document lists, and deployment support on **AWS Services** for scalable cloud hosting.

## Features
- **User Authentication:** Secure login mechanism using JWT.
- **User Management:** Update avatars file to AWS S3, change passwords, fetch and update user details using ThreadLocal for efficiency.
- **Document Categorization:** Facilitates easy addition, deletion, modification, and retrieval of document categories.
- **Document Management:** Manage documents with features for list pagination based on conditions, and comprehensive CRUD operations.
- **Deployment:** Ready for deployment on AWS, ensuring scalability and reliability.

## Installation

### Prerequisites
- Java JDK 11 or newer
- Maven 3.6+
- MySQL 5.7+ or any compatible database
- Git

### Setup
1. **Clone the repository:**
```bash
git clone https://github.com/mylittleZ/OpenDocAdminPlatform.git
cd OpenDocAdminPlatform
```

2. **Configure application properties:**
   - Navigate to `src/main/resources/application.properties`.
   - Update the database URL, username, and password as per your MySQL setup.
   - Configure any other necessary properties such as server port, JWT secret, AWS credentials.

### Build
- **With Maven:**
```bash
mvn clean install
```

## Running the Application

1. **Run the Spring Boot application:**
- **With Maven:**
```bash
mvn spring-boot:run
```

2. **Access the application:**
   - Open a web browser and navigate to `http://localhost:8080` (or whatever port you configured).


## Usage

To test the OpenDocAdminPlatform's functionalities with **Postman**, follow these steps:

1. **User Authentication:**
   -  Send a `POST` request to `/user/register` with the username and password in the body to create an account.
   -  Send a `POST` request to `/user/login` with the register username and password in the body to receive a JWT token.

2. **User Management:**
   - Retrieve user information with a `GET` request to `/user/userInfo`.
   - Update user details using a `PUT` request to `/user/update`.
   - Change the user avatar by sending a `PATCH` request to `/user/updateAvatar` with the `avatarUrl` query parameter.
   - Modify the user's password with a `PATCH` request to `/user/updatePwd`.
   - Ensure to include the JWT token in the request header for authentication across all user management operations.

3. **Document Category Management:**
   - Get a new document category by sending a `GET` request to `/category`.
   - Create a new document category by sending a `POST` request to `/category`.
   - Update an existing category with a `PUT` request to `/category`.
   - Retrieve the details of a specific category by issuing a `GET` request to `/category/detail` with the appropriate ID, such as `?id=2`.
   - Delete a category by making a `DELETE` request to `/category` with the corresponding ID parameter, for example, `?id=5`.

4. **Document Management:**
   - Publish a new document by making a `POST` request to `/article`.
   - Update an existing document's details with a `PUT` request to `/article`.
   - Retrieve detailed information of a specific document using a `GET` request to `/article/detail` by appending the document ID, for example, `?id=4`.
   - Delete a document by issuing a `DELETE` request to `/article` and including the document ID as a parameter, like `?id=4`.
   - For listing documents with pagination, send a `GET` request to `/article` with `pageNum` and `pageSize` parameters, such as `?pageNum=1&pageSize=5`.


Ensure that each request has the correct headers and body format as required by the API endpoints. The JWT token must be included in the header for all authenticated requests.


---

