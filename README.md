🔐 SecureText: End-to-End Encryption Tool
SecureText is a full-stack web application designed to protect sensitive information through robust encryption and decryption. Whether it's a simple text message, a Gmail body, or a local file, this tool ensures that data remains unreadable to unauthorized parties.

🚀 Features
Text Encryption: Convert plain text into secure cipher text using industry-standard algorithms.

Gmail Integration: Specifically formatted encryption for email content to maintain privacy during transmission.

File Security: Upload and process files to encrypt their contents before storage or sharing.

Secure Storage: All metadata and encrypted logs are managed via a relational database for tracking and retrieval.

Responsive UI: A clean, modern interface built for seamless user experience across devices.

🛠️ Tech Stack
Frontend
HTML5 & CSS3: Structured layouts with custom styling and responsive design.

JavaScript (ES6+): Handles asynchronous API calls and DOM manipulation.

Backend
Java & Spring Boot: Powers the RESTful API and handles the core encryption logic.

Spring Security: (Optional/Recommended) Ensures the safety of the API endpoints.

Database
MySQL: Stores user records, activity logs, and encrypted data blobs.

🏗️ Architecture
The application follows a classic Client-Server architecture:

Client: The user interacts with the HTML/CSS/JS frontend.

API Layer: Spring Boot Controllers receive requests and pass data to Service layers.

Logic Layer: Advanced Java algorithms perform the mathematical transformations for encryption.

Data Layer: MySQL persists the necessary information securely.

📋 Prerequisites
Before running this project, ensure you have the following installed:

JDK 17 or higher

Maven 3.6+

MySQL Server 8.0+

A modern web browser (Chrome, Firefox, Edge)

🔧 Installation & Setup
Clone the Repository

Bash
git clone https://github.com/your-username/secure-text-tool.git
cd secure-text-tool
Database Configuration

Create a schema in MySQL: CREATE DATABASE encryption_db;

Update src/main/resources/application.properties:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/encryption_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Run the Backend

Bash
mvn spring-boot:run
Launch the Frontend

Open index.html in your preferred browser.
