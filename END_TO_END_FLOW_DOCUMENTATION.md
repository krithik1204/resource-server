# College Resource Server - End-to-End Flow Documentation

## Overview

This document describes the complete end-to-end flow implemented in the College Resource Server system with the following key actors:
- **Principal**: Creates and manages exams
- **Admin**: Manages users, departments, courses, students, and results
- **Teacher/Faculty**: Marks attendance for students
- **Student**: Views their profile, attendance, and exam results

## Architecture & Entities

### Key Entities

1. **User** - Base user entity with authentication details
   - Relationships: Many-to-Many with Role
   - Has: Student or Faculty profile

2. **Role** - User roles with permissions
   - Types: PRINCIPAL, ADMIN, TEACHER, STUDENT

3. **Student** - Student profile
   - Relationships: One-to-One with User, Many-to-One with Department
   - Attributes: Roll Number, Year, CGPA, Attendance %

4. **Faculty** - Faculty/Teacher profile
   - Relationships: One-to-One with User, Many-to-One with Department
   - Attributes: Designation

5. **Department** - Academic department
   - Relationships: One-to-Many with Students and Faculty

6. **Course** - Course offered by department
   - Relationships: Many-to-One with Department

7. **Exam** - Exam for a course
   - Relationships: Many-to-One with Course
   - Attributes: Duration (minutes), Total Marks

8. **Attendance** - Student attendance record
   - Relationships: Many-to-One with Student and Course
   - Attributes: Date, Status (PRESENT/ABSENT)

9. **Result** - Student exam result
   - Relationships: Many-to-One with Student and Course
   - Attributes: Marks, Grade

## Security & Authorization

### Role-Based Access Control (RBAC)

```
GET /api/** - All authenticated users can READ
POST /api/departments - ADMIN only
PUT /api/departments/** - ADMIN only
DELETE /api/departments/** - ADMIN only

POST /api/courses - ADMIN only
PUT /api/courses/** - ADMIN only
DELETE /api/courses/** - ADMIN only

POST /api/exams - PRINCIPAL only
PUT /api/exams/** - PRINCIPAL only
DELETE /api/exams/** - PRINCIPAL only

POST /api/attendance - TEACHER/FACULTY only
PUT /api/attendance/** - TEACHER/FACULTY only

POST /api/results - ADMIN only
PUT /api/results/** - ADMIN only
DELETE /api/results/** - ADMIN only

POST /api/students - ADMIN only
PUT /api/students/** - ADMIN only
DELETE /api/students/** - ADMIN only

POST /api/events - ADMIN, TEACHER, PRINCIPAL
PUT /api/events/** - ADMIN, PRINCIPAL
DELETE /api/events/** - ADMIN, PRINCIPAL

POST /api/complaints - STUDENT only
PUT /api/complaints/** - STUDENT, ADMIN, PRINCIPAL
DELETE /api/complaints/** - ADMIN, PRINCIPAL

POST /api/announcements - ADMIN, PRINCIPAL
PUT /api/announcements/** - ADMIN, PRINCIPAL
DELETE /api/announcements/** - ADMIN, PRINCIPAL

GET /api/students/{id}/attendance - All authenticated users
GET /api/students/{id}/results - All authenticated users
GET /api/announcements - All authenticated users
GET /api/events - All authenticated users
GET /api/complaints - All authenticated users
```

## End-to-End Flow Steps

### Phase 1: System Setup (Admin)

#### 1.1 Create Users
- Create Admin User
- Create Principal User
- Create Teacher User(s)
- Create Student User(s)

```bash
POST /api/users
Content-Type: application/json

{
  "name": "User Name",
  "email": "user@college.edu",
  "password": "password",
  "phone_number": "1234567890",
  "date_of_birth": "1990-01-15"
}
```

#### 1.2 Assign Roles to Users
- Assign ADMIN role to admin user
- Assign PRINCIPAL role to principal user
- Assign TEACHER role to teacher user
- Assign STUDENT role to student users

```bash
POST /api/users/{userId}/role
Content-Type: application/json
Authorization: Bearer {adminToken}

{
  "roleId": 2
}
```

#### 1.3 Create Academic Structure
- Create Departments
- Create Courses
- Assign Faculty to Departments
- Assign Students to Departments

```bash
# Create Department
POST /api/departments
Authorization: Bearer {adminToken}

{
  "name": "Computer Science",
  "code": "CS",
  "hod_name": "Dr. Jane Smith"
}

# Create Course
POST /api/courses
Authorization: Bearer {adminToken}

{
  "name": "Data Structures",
  "code": "CS201",
  "credits": 4,
  "semester": 2
}

# Create Faculty
POST /api/faculties
Authorization: Bearer {adminToken}

{
  "userId": 3,
  "departmentId": 1,
  "designation": "Assistant Professor"
}

# Create Student
POST /api/students
Authorization: Bearer {adminToken}

{
  "userId": 4,
  "rollNumber": "CS2021001",
  "departmentId": 1,
  "year": 2,
  "attendancePercentage": 85.0,
  "cgpa": 3.5
}
```

### Phase 2: Academic Operations

#### 2.1 Principal Creates Exams
The Principal has exclusive authority to create exams.

```bash
POST /api/exams
Authorization: Bearer {principalToken}
Content-Type: application/json

{
  "name": "Data Structures Mid Term Exam",
  "examDate": "2024-06-15T10:00:00",
  "courseId": 1,
  "duration": 120,
  "totalMarks": 100
}
```

#### 2.2 Teacher Marks Attendance
Teachers can mark attendance for students in their courses.

```bash
POST /api/attendance
Authorization: Bearer {teacherToken}
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "date": "2024-06-10",
  "status": "PRESENT"
}
```

Update attendance if needed:
```bash
PUT /api/attendance/{attendanceId}
Authorization: Bearer {teacherToken}
Content-Type: application/json

{
  "status": "ABSENT"
}
```

#### 2.3 Admin Records Exam Results
Admin records results after exam completion.

```bash
POST /api/results
Authorization: Bearer {adminToken}
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "marks": 85.5,
  "grade": "A"
}
```

#### 2.4 Events, Complaints, and Announcements
This flow adds student complaints, campus events, and announcements.

- Students can raise complaints for issues or support requests.
- Teachers can create event proposals.
- Principals can approve or reject events.
- Admins and principals can publish announcements.
- Students can read announcements after they are published.

##### 2.4.1 Student Creates Complaint
```bash
POST /api/complaints
Authorization: Bearer {studentToken}
Content-Type: application/json

{
  "studentId": 1,
  "message": "Need assistance with course registration.",
  "status": "OPEN"
}
```

##### 2.4.2 Teacher Creates Event
```bash
POST /api/events
Authorization: Bearer {teacherToken}
Content-Type: application/json

{
  "name": "Guest Lecture on AI",
  "description": "A lecture for all CS students.",
  "status": "PENDING",
  "createdByUserId": 3
}
```

##### 2.4.3 Principal Approves Event
```bash
PUT /api/events/{eventId}
Authorization: Bearer {principalToken}
Content-Type: application/json

{
  "status": "APPROVED"
}
```

##### 2.4.4 Admin Creates Announcement
```bash
POST /api/announcements
Authorization: Bearer {adminToken}
Content-Type: application/json

{
  "title": "Campus Holiday",
  "message": "College will be closed on Friday due to maintenance.",
  "createdByRole": "ADMIN"
}
```

##### 2.4.5 Student Views Announcements
```bash
GET /api/announcements
Authorization: Bearer {studentToken}
```

### Phase 3: Student View Records

#### 3.1 Student Views Profile
```bash
GET /api/students/{studentId}
Authorization: Bearer {studentToken}
```

#### 3.2 Student Views Attendance
```bash
GET /api/students/{studentId}/attendance
Authorization: Bearer {studentToken}
```

Response:
```json
[
  {
    "id": 1,
    "student": "Alice Johnson",
    "studentId": 1,
    "course": "Data Structures",
    "courseId": 1,
    "date": "2024-06-10",
    "status": "PRESENT"
  }
]
```

#### 3.3 Student Views Results/Transcript
```bash
GET /api/students/{studentId}/results
Authorization: Bearer {studentToken}
```

Response:
```json
[
  {
    "id": 1,
    "student": "Alice Johnson",
    "studentId": 1,
    "course": "Data Structures",
    "courseId": 1,
    "marks": 85.5,
    "grade": "A"
  }
]
```

## Database Schema Updates

### New Exam Fields
The Exam entity has been enhanced with:
- `duration` (Integer) - Duration of exam in minutes
- `totalMarks` (Double) - Total marks for the exam

### Enhanced Repositories
- **AttendanceRepository**: Added `findByStudentId(Long studentId)` query method
- **ResultRepository**: Added `findByStudentId(Long studentId)` query method

### Service Methods
- **AttendanceService**: Added `getAttendanceByStudentId(Long studentId)`
- **ResultService**: Added `getResultsByStudentId(Long studentId)`

## API Endpoints Summary

### Users Management
- `POST /api/users` - Create new user
- `GET /api/users` - Get users without roles
- `GET /api/users/all` - Get all users
- `GET /api/users/{id}` - Get specific user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user
- `POST /api/users/{id}/role` - Assign role to user

### Students
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student profile
- `POST /api/students` - Create student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student
- `GET /api/students/{id}/attendance` - Get student's attendance
- `GET /api/students/{id}/results` - Get student's results

### Faculty
- `GET /api/faculties` - Get all faculty
- `GET /api/faculties/{id}` - Get faculty profile
- `POST /api/faculties` - Create faculty
- `PUT /api/faculties/{id}` - Update faculty
- `DELETE /api/faculties/{id}` - Delete faculty

### Departments
- `GET /api/departments` - Get all departments
- `GET /api/departments/{id}` - Get department details
- `POST /api/departments` - Create department
- `PUT /api/departments/{id}` - Update department
- `DELETE /api/departments/{id}` - Delete department

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course details
- `POST /api/courses` - Create course
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course

### Exams
- `GET /api/exams` - Get all exams
- `GET /api/exams/{id}` - Get exam details
- `POST /api/exams` - Create exam (PRINCIPAL only)
- `PUT /api/exams/{id}` - Update exam (PRINCIPAL only)
- `DELETE /api/exams/{id}` - Delete exam (PRINCIPAL only)

### Attendance
- `GET /api/attendance` - Get all attendance records
- `GET /api/attendance/{id}` - Get specific attendance record
- `POST /api/attendance` - Mark attendance (TEACHER only)
- `PUT /api/attendance/{id}` - Update attendance (TEACHER only)
- `DELETE /api/attendance/{id}` - Delete attendance record

### Results
- `GET /api/results` - Get all results
- `GET /api/results/{id}` - Get specific result
- `POST /api/results` - Create result (ADMIN only)
- `PUT /api/results/{id}` - Update result (ADMIN only)
- `DELETE /api/results/{id}` - Delete result

## Postman Collection Usage

### Setup Instructions

1. **Import the Collection**
   - Open Postman
   - Click "Import" → "Upload Files"
   - Select `Postman_Collection_End_To_End_Flow.json`

2. **Configure Environment Variables**
   - Create variables in Postman for:
     - `adminToken` - JWT token for admin user
     - `principalToken` - JWT token for principal user
     - `teacherToken` - JWT token for teacher user
     - `studentToken` - JWT token for student user

3. **Execute the Flow**
   - Follow the numbered folders in sequence
   - Each folder contains related requests
   - Update IDs as needed based on your created entities

### Collection Structure

1. **1. Setup - Create Users** - Create all user accounts
2. **2. Setup - Assign Roles** - Assign roles to users
3. **3. Setup - Create Departments and Courses** - Create academic structure
4. **4. Setup - Create Faculty and Students** - Assign users to academic roles
5. **5. Principal - Create Exams** - Create exams for courses
6. **6. Teacher - Mark Attendance** - Mark attendance records
7. **7. Admin - Create Results** - Record exam results
8. **8. Student - View Profile and Records** - View student information
9. **9. Admin - View and Manage Data** - Admin dashboard operations
10. **10. Query Specific Student Data** - Get detailed student information

## Example Workflow

### Complete End-to-End Scenario

```
Day 1: System Setup
├── Admin creates 5 users (Admin, Principal, Teacher, 2 Students)
├── Admin assigns roles to all users
├── Admin creates CS Department
├── Admin creates 2 courses (Data Structures, Database Management)
├── Admin creates Faculty record for Teacher
└── Admin creates Student records for both students

Day 2: Exam Setup
├── Principal creates exam "Data Structures Mid Term"
└── Principal creates exam "Database Management Final"

Day 3-4: Classes and Attendance
├── Teacher marks attendance daily
├── Each student has multiple attendance records
└── System calculates attendance percentage

Day 5: Results
├── Admin inputs exam results for all students
├── Results include marks and grades
└── Students can view their complete transcripts

On Demand: Student Views
├── Student logs in
├── Views their profile (CGPA, Attendance %)
├── Views all attendance records by course
└── Views all exam results by course
```

## Error Handling

### Common Errors and Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| 401 Unauthorized | Missing or invalid token | Ensure JWT token is valid and hasn't expired |
| 403 Forbidden | Insufficient permissions | Verify user has required role for the operation |
| 404 Not Found | Invalid ID | Check that the resource ID exists in the database |
| 409 Conflict | Duplicate data | Use unique fields like email, roll number |
| 422 Validation Error | Invalid input | Review required fields and data types |

## Database Initialization

Before using the API, ensure:

1. Database is created and initialized
2. Tables are migrated (run Flyway or Liquibase scripts)
3. Default roles are inserted:
   ```sql
   INSERT INTO roles (name, label, description) VALUES
   ('STUDENT', 'Student', 'Student role'),
   ('ADMIN', 'Admin', 'Administrator role'),
   ('TEACHER', 'Teacher', 'Faculty/Teacher role'),
   ('FACULTY', 'Faculty', 'Faculty role'),
   ('PRINCIPAL', 'Principal', 'Principal role');
   ```

## Testing Checklist

- [ ] Create users with different roles
- [ ] Assign roles to users
- [ ] Create departments and courses
- [ ] Create student and faculty records
- [ ] Principal creates exams
- [ ] Teacher marks attendance
- [ ] Admin records results
- [ ] Student views profile
- [ ] Student views attendance records
- [ ] Student views exam results
- [ ] Verify role-based access control
- [ ] Test error scenarios
- [ ] Verify token expiration handling

## Security Considerations

1. **Authentication**: OAuth2 JWT tokens required for all endpoints except public ones
2. **Authorization**: Role-based access control (RBAC) implemented
3. **CORS**: Configured for local development (ports 5173, 5174)
4. **CSRF**: Disabled for REST API consumption
5. **Encryption**: Passwords should be encrypted before storage
6. **Token Expiry**: Implement token refresh mechanism

## Notes for Developers

- All timestamps use LocalDateTime format
- Dates use LocalDate format (YYYY-MM-DD)
- Status values for Attendance: PRESENT, ABSENT
- Grade calculation can be customized in the Result entity
- Attendance percentage is calculated and stored with Student
- CGPA is stored with Student and can be updated
- All IDs are auto-generated using IDENTITY strategy

## Future Enhancements

- [ ] Implement batch attendance marking
- [ ] Add grade calculation rules
- [ ] Implement performance analytics
- [ ] Add course prerequisites
- [ ] Implement student timetable
- [ ] Add online exam functionality
- [ ] Implement automated report generation
- [ ] Add notification system
- [ ] Implement discussion forums per course
- [ ] Add assignment submission and grading
