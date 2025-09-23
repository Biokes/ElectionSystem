# Election System

A robust and secure electronic voting system built with Spring Boot that enables the management of elections, voters, candidates, and the voting process. The system is designed to handle various types of elections including presidential, gubernatorial, and senatorial elections with comprehensive security measures and user management.

## System Overview

The Election System provides a complete solution for conducting electronic elections with the following core components:

### Entity Management
- **Voters**: Complete voter registration system with email verification
- **Candidates**: Candidate registration with document verification (affidavit upload)
- **Elections**: Flexible election creation and management system
- **Votes**: Secure vote recording and tracking

### Election Types
Supports various election offices including:
- Presidential Elections
- Gubernatorial Elections
- Senatorial Elections
- Local Government Elections
- Other Custom Elections

## Features

- **User Management**
  - Voter registration with email verification
  - Admin user management with role-based permissions
  - Candidate registration with document verification
  - Contact information and address management
  - Date of birth verification for voter eligibility
  - Registration status tracking (PENDING, VERIFIED, SUSPENDED)

- **Election Management**
  - Create and schedule elections with flexible time windows
  - Define election parameters including office type
  - Track election status (ACTIVE, COMPLETED, CANCELLED, SUSPENDED)
  - Multiple candidate registration per election
  - Election rescheduling capabilities
  - Real-time election monitoring

- **Voting Process**
  - Secure JWT-based voter authentication
  - Real-time vote casting with instant confirmation
  - Prevention of double voting through vote tracking
  - Vote verification and audit system
  - Support for multiple simultaneous elections
  - Vote counting and result tabulation

- **Security Features**
  - JWT-based authentication
  - Role-based access control
  - Email validation
  - Secure password handling

## Technology Stack

- **Backend Framework**: Spring Boot 3.3.2
- **Java Version**: Java 17
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT
- **File Storage**: Cloudinary
- **Build Tool**: Maven
- **Documentation**: POSTMAN

## Key Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- PostgreSQL Driver
- Lombok
- ModelMapper
- Cloudinary
- Java JWT (Auth0)
- Jackson Datatype JSR310
- Jakarta Validation API

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/election/electionsystem/
│   │       ├── annotation/         # Custom annotations
│   │       ├── configuration/      # Application configs
│   │       ├── controllers/        # REST endpoints
│   │       ├── data/              # Domain models & enums
│   │       ├── dtos/              # Data transfer objects
│   │       ├── exceptions/         # Custom exceptions
│   │       ├── handlers/          # Exception handlers
│   │       ├── repo/              # Data repositories
│   │       ├── security/          # Security configurations
│   │       ├── services/          # Business logic
│   │       └── utils/             # Utility classes
│   └── resources/
│       └── application.properties  # Application configuration
└── test/
    └── java/                      # Test cases
```

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6 or later
- PostgreSQL database

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Biokes/ElectionSystem.git
   ```

2. Navigate to the project directory:
   ```bash
   cd ElectionSystem
   ```

3. Configure the database connection in `src/main/resources/application.properties`

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Testing

The project includes comprehensive test coverage for controllers and services. To run the tests:

```bash
mvn test
```

## Security

The application implements comprehensive security measures to ensure the integrity of the election process:

### Authentication & Authorization
- JWT-based authentication for secure API access
- Role-based access control (Admin, Voter, Candidate)
- Password encryption using secure hashing algorithms
- Session management and token expiration

### Data Protection
- Input validation using Jakarta Validation
- Custom email validation annotations
- Date validation for election scheduling
- Secure file upload handling with Cloudinary
- PostgreSQL database with proper indexing and constraints

### Election Integrity
- Prevention of double voting through database constraints
- Voter registration status verification
- Document verification for candidate registration
- Audit trails for important actions
- Real-time monitoring of election activities

### API Security
- CORS configuration
- CSRF protection
- Rate limiting
- Request validation
- Secure error handling and logging

## API Documentation

### Core Endpoints

#### Voter Management
- `POST /api/v1/voters/register` - Register a new voter
- `POST /api/v1/voters/login` - Voter login
- `GET /api/v1/voters/profile` - Get voter profile
- `PUT /api/v1/voters/update` - Update voter information

#### Election Management
- `POST /api/v1/elections/create` - Create a new election
- `PUT /api/v1/elections/{id}/reschedule` - Reschedule an election
- `GET /api/v1/elections/active` - Get active elections
- `POST /api/v1/elections/{id}/vote` - Cast a vote

#### Candidate Management
- `POST /api/v1/candidates/register` - Register as a candidate
- `GET /api/v1/candidates/{election_id}` - Get candidates for an election
- `POST /api/v1/candidates/upload-document` - Upload candidate documents

#### Admin Operations
- `POST /api/v1/admin/register` - Register admin user
- `PUT /api/v1/admin/suspend-voter` - Suspend voter registration
- `GET /api/v1/admin/election-stats` - Get election statistics

### Data Models

#### Voter
```java
- id (Long)
- firstname (String)
- lastname (String)
- email (String, unique)
- password (String)
- dateOfBirth (LocalDate)
- contactInformation (ContactInformation)
- registrationStatus (RegisterationStatus)
```

#### Election
```java
- id (Long)
- description (String)
- status (Status)
- startDate (LocalDateTime)
- endDate (LocalDateTime)
- candidates (List<Candidate>)
```

#### Candidate
```java
- id (Long)
- voter (Voter)
- election (Election)
- documentUrl (String)
- office (Office)
- votes (Set<Vote>)
```

## Support

For technical support or bug reports:
1. Open an issue in the GitHub repository
2. Include detailed description of the problem
3. Provide steps to reproduce the issue
4. Include relevant error messages or logs


For general inquiries, you can [Click here ](https://x.com/blockchainrafik) to reach out to me

Drop a mail [Here](mailto:blockchainrafik@gmail.com)

