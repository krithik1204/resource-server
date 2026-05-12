# Quick Start Guide - College Resource Server End-to-End Flow

## 🚀 Quick Setup (5 Minutes)

### 1. Database Setup
```sql
-- Ensure roles exist
INSERT INTO roles (name, label, description) VALUES
  (1, 'STUDENT', 'Student', 'Student role'),
  (2, 'ADMIN', 'Admin', 'Administrator role'),
  (3, 'FACULTY', 'Faculty', 'Faculty role'),
  (4, 'TEACHER', 'Teacher', 'Teacher role'),
  (5, 'PRINCIPAL', 'Principal', 'Principal role');
```

### 2. Start Server
```bash
cd resource-server
mvn spring-boot:run
# Server runs on http://localhost:8080
```

### 3. Import Postman Collection
- File → Import
- Select `Postman_Collection_End_To_End_Flow.json`
- Set environment variables for tokens

## 📋 Complete Flow in 10 Steps

### Step 1: Create Admin User
```bash
POST http://localhost:8080/api/users
{
  "name": "Admin User",
  "email": "admin@college.edu",
  "password": "admin@123",
  "phone_number": "9876543210",
  "date_of_birth": "1985-05-15"
}
Response: User 1 created
```

### Step 2: Create Other Users
Create Principal, Teacher, and 2 Students similarly
- Principal User → User 2
- Teacher User → User 3
- Student 1 → User 4
- Student 2 → User 5

### Step 3: Assign Roles (as Admin)
```bash
POST http://localhost:8080/api/users/{userId}/role
Authorization: Bearer {adminToken}

# User 1 → ADMIN (roleId: 2)
# User 2 → PRINCIPAL (roleId: 5)
# User 3 → TEACHER (roleId: 4)
# User 4 → STUDENT (roleId: 1)
# User 5 → STUDENT (roleId: 1)
```

### Step 4: Create Department (as Admin)
```bash
POST http://localhost:8080/api/departments
Authorization: Bearer {adminToken}

{
  "name": "Computer Science",
  "code": "CS",
  "hod_name": "Dr. Jane Smith"
}
Response: Department 1 created
```

### Step 5: Create Courses (as Admin)
```bash
POST http://localhost:8080/api/courses
Authorization: Bearer {adminToken}

{
  "name": "Data Structures and Algorithms",
  "code": "CS201",
  "credits": 4,
  "semester": 2
}
Response: Course 1 created

{
  "name": "Database Management Systems",
  "code": "CS301",
  "credits": 4,
  "semester": 3
}
Response: Course 2 created
```

### Step 6: Create Faculty (as Admin)
```bash
POST http://localhost:8080/api/faculties
Authorization: Bearer {adminToken}

{
  "userId": 3,
  "departmentId": 1,
  "designation": "Assistant Professor"
}
Response: Faculty 1 created
```

### Step 7: Create Students (as Admin)
```bash
POST http://localhost:8080/api/students
Authorization: Bearer {adminToken}

# Student 1
{
  "userId": 4,
  "rollNumber": "CS2021001",
  "departmentId": 1,
  "year": 2,
  "attendancePercentage": 85.0,
  "cgpa": 3.5
}
Response: Student 1 created

# Student 2
{
  "userId": 5,
  "rollNumber": "CS2021002",
  "departmentId": 1,
  "year": 2,
  "attendancePercentage": 90.0,
  "cgpa": 3.8
}
Response: Student 2 created
```

### Step 8: Create Exams (as Principal)
```bash
POST http://localhost:8080/api/exams
Authorization: Bearer {principalToken}

{
  "name": "Data Structures Mid Term",
  "examDate": "2024-06-15T10:00:00",
  "courseId": 1,
  "duration": 120,
  "totalMarks": 100
}
Response: Exam 1 created
```

### Step 9: Mark Attendance (as Teacher)
```bash
POST http://localhost:8080/api/attendance
Authorization: Bearer {teacherToken}

{
  "studentId": 1,
  "courseId": 1,
  "date": "2024-06-10",
  "status": "PRESENT"
}
Response: Attendance 1 created
```

### Step 10: Create Results (as Admin)
```bash
POST http://localhost:8080/api/results
Authorization: Bearer {adminToken}

{
  "studentId": 1,
  "courseId": 1,
  "marks": 85.5,
  "grade": "A"
}
Response: Result 1 created
```

### Step 11: Student Creates Complaint (as Student)
```bash
POST http://localhost:8080/api/complaints
Authorization: Bearer {studentToken}

{
  "studentId": 1,
  "message": "Need assistance with course registration.",
  "status": "OPEN"
}
Response: Complaint 1 created
```

### Step 12: Teacher Creates Event (as Teacher)
```bash
POST http://localhost:8080/api/events
Authorization: Bearer {teacherToken}

{
  "name": "Guest Lecture on AI",
  "description": "A lecture for all CS students.",
  "status": "PENDING",
  "createdByUserId": 3
}
Response: Event 1 created
```

### Step 13: Create Announcement (as Admin)
```bash
POST http://localhost:8080/api/announcements
Authorization: Bearer {adminToken}

{
  "title": "Campus Holiday",
  "message": "College will be closed on Friday due to maintenance.",
  "createdByRole": "ADMIN"
}
Response: Announcement 1 created
```

## 🔍 View Your Data

### As Student (Student 1)
```bash
# View profile
GET http://localhost:8080/api/students/1
Authorization: Bearer {studentToken}

# View attendance
GET http://localhost:8080/api/students/1/attendance
Authorization: Bearer {studentToken}

# View exam results
GET http://localhost:8080/api/students/1/results
Authorization: Bearer {studentToken}
```

### As Admin
```bash
# View all students
GET http://localhost:8080/api/students
Authorization: Bearer {adminToken}

# View all faculty
GET http://localhost:8080/api/faculties
Authorization: Bearer {adminToken}

# View all exams
GET http://localhost:8080/api/exams
Authorization: Bearer {adminToken}

# View all attendance
GET http://localhost:8080/api/attendance
Authorization: Bearer {adminToken}

# View all results
GET http://localhost:8080/api/results
Authorization: Bearer {adminToken}
```

## 🔐 Role-Based Access Control

| Role | Can Do | Cannot Do |
|------|--------|-----------|
| PRINCIPAL | Create exams | Manage users, departments, courses |
| ADMIN | Manage users, departments, courses, results, students | Create exams, mark attendance |
| TEACHER | Mark attendance, view students | Manage users, create exams, create results |
| STUDENT | View own profile, attendance, results | Any management operations |

## 📝 Key Endpoints Reference

| Operation | Method | Endpoint | Role |
|-----------|--------|----------|------|
| Create User | POST | /api/users | Public |
| Assign Role | POST | /api/users/{id}/role | ADMIN |
| Create Department | POST | /api/departments | ADMIN |
| Create Course | POST | /api/courses | ADMIN |
| Create Faculty | POST | /api/faculties | ADMIN |
| Create Student | POST | /api/students | ADMIN |
| **Create Exam** | POST | /api/exams | **PRINCIPAL** |
| **Mark Attendance** | POST | /api/attendance | **TEACHER** |
| Create Result | POST | /api/results | ADMIN |
| View Student Attendance | GET | /api/students/{id}/attendance | All |
| View Student Results | GET | /api/students/{id}/results | All |
| View All Exams | GET | /api/exams | All |

## 🐛 Troubleshooting

### Issue: 401 Unauthorized
**Solution:** 
- Check Authorization header has format: `Bearer {token}`
- Verify token is valid and not expired
- Get new token from authentication server

### Issue: 403 Forbidden
**Solution:**
- Verify user has required role
- Check role assignments in Step 3
- Review endpoint authorization rules

### Issue: 404 Not Found
**Solution:**
- Verify resource ID exists
- Check response from previous request
- Use actual IDs, not placeholders

### Issue: 422 Unprocessable Entity
**Solution:**
- Check all required fields present
- Verify data types (dates as YYYY-MM-DD)
- Review DTO validation rules

## 📊 Sample Data Flow

```
Admin Creates:
  → Users (Admin, Principal, Teacher, Students)
  → Departments & Courses
  → Assigns Faculty to Department
  → Assigns Students to Department

Principal Creates:
  → Exams for Courses
  → Defines exam duration and total marks

Teacher Records:
  → Daily attendance for students
  → Updates if attendance correction needed

Admin Finalizes:
  → Records exam results after evaluation
  → Assigns marks and grades

Student Accesses:
  → Personal profile with CGPA & attendance %
  → All attendance records by course
  → Complete transcript with exam results
```

## 🔄 Common Workflows

### Daily Attendance Workflow
1. Teacher logs in
2. Gets list of students for course
3. Marks attendance for each student
4. Submits attendance records
5. Student can view updated attendance

### Exam Workflow
1. Principal creates exam in system
2. Defines duration and total marks
3. Students take exam
4. Admin enters marks and grades
5. Students view results and grades
6. System calculates GPA

### Semester End Workflow
1. All exams created
2. All attendance recorded
3. All results entered
4. Admin generates transcript
5. Student views final transcript
6. System generates reports

## 💾 Data Persistence

All data is persisted in MySQL database:
- Users and their roles
- Department and course structure
- Faculty and student assignments
- Exams with complete details
- Attendance records with dates
- Results with marks and grades

## 🎯 Success Criteria

✅ System is working if:
- [x] Users can be created with different roles
- [x] Roles can be assigned to users
- [x] Principal can create exams only
- [x] Teacher can mark attendance only
- [x] Admin can create results only
- [x] Student can view their data only
- [x] All endpoints return proper responses
- [x] Role-based access control works

## 📚 Additional Resources

- Full documentation: `END_TO_END_FLOW_DOCUMENTATION.md`
- Implementation details: `IMPLEMENTATION_SUMMARY.md`
- Postman collection: `Postman_Collection_End_To_End_Flow.json`

## ✉️ Need Help?

Refer to:
1. Error messages in API responses
2. Postman collection examples
3. Documentation files
4. Check logs for detailed error stack traces
