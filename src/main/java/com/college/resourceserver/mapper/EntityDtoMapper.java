package com.college.resourceserver.mapper;

import com.college.resourceserver.dto.*;
import com.college.resourceserver.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EntityDtoMapper {

    // ==================== USER MAPPINGS ====================

    public UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone_number(user.getPhone_number());
        response.setDate_of_birth(user.getDate_of_birth());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        
        if (user.getRoles() != null) {
            response.setRoles(user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet()));
        }
        
        return response;
    }

    public List<UserResponse> toUserResponseList(List<User> users) {
        return users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    public User toUserEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone_number(request.getPhone_number());
        user.setDate_of_birth(request.getDate_of_birth());
        
        return user;
    }

    public void updateUserEntity(UserUpdateRequest request, User user) {
        if (request == null || user == null) {
            return;
        }
        
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }
        if (request.getPhone_number() != null) {
            user.setPhone_number(request.getPhone_number());
        }
        if (request.getDate_of_birth() != null) {
            user.setDate_of_birth(request.getDate_of_birth());
        }
    }

    // ==================== ROLE MAPPINGS ====================

    public RoleResponse toRoleResponse(Role role) {
        if (role == null) {
            return null;
        }
        
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        response.setDescription(role.getDescription());
        response.setLabel(role.getLabel());
        
        if (role.getMenus() != null) {
            response.setMenus(role.getMenus().stream()
                    .map(this::toMenuResponse)
                    .collect(Collectors.toList()));
        }
        
        return response;
    }

    public List<RoleResponse> toRoleResponseList(List<Role> roles) {
        return roles.stream()
                .map(this::toRoleResponse)
                .collect(Collectors.toList());
    }

    public Role toRoleEntity(RoleCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setLabel(request.getLabel());
        
        return role;
    }

    // ==================== STUDENT MAPPINGS ====================

    public StudentResponse toStudentResponse(Student student) {
        if (student == null) {
            return null;
        }
        
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setUser(toUserResponse(student.getUser()));
        response.setRollNumber(student.getRollNumber());
        response.setYear(student.getYear());
        response.setAttendancePercentage(student.getAttendancePercentage());
        response.setCgpa(student.getCgpa());
        
        if (student.getDepartment() != null) {
            response.setDepartment(student.getDepartment().getName());
        }
        
        return response;
    }

    public List<StudentResponse> toStudentResponseList(List<Student> students) {
        return students.stream()
                .map(this::toStudentResponse)
                .collect(Collectors.toList());
    }

    // ==================== FACULTY MAPPINGS ====================

    public FacultyResponse toFacultyResponse(Faculty faculty) {
        if (faculty == null) {
            return null;
        }
        
        FacultyResponse response = new FacultyResponse();
        response.setId(faculty.getId());
        response.setUser(toUserResponse(faculty.getUser()));
        response.setDesignation(faculty.getDesignation());
        
        if (faculty.getDepartment() != null) {
            response.setDepartment(faculty.getDepartment().getName());
        }
        
        return response;
    }

    public List<FacultyResponse> toFacultyResponseList(List<Faculty> faculties) {
        return faculties.stream()
                .map(this::toFacultyResponse)
                .collect(Collectors.toList());
    }

    // ==================== DEPARTMENT MAPPINGS ====================

    public DepartmentResponse toDepartmentResponse(Department department) {
        if (department == null) {
            return null;
        }
        
        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        
        if (department.getHod() != null) {
            response.setHod(department.getHod().getUser().getName());
            response.setHodId(department.getHod().getId());
        }
        
        return response;
    }

    public List<DepartmentResponse> toDepartmentResponseList(List<Department> departments) {
        return departments.stream()
                .map(this::toDepartmentResponse)
                .collect(Collectors.toList());
    }

    // ==================== COURSE MAPPINGS ====================

    public CourseResponse toCourseResponse(Course course) {
        if (course == null) {
            return null;
        }
        
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setCode(course.getCode());
        
        if (course.getDepartment() != null) {
            response.setDepartment(course.getDepartment().getName());
            response.setDepartmentId(course.getDepartment().getId());
        }
        
        if (course.getFaculty() != null) {
            response.setFaculty(course.getFaculty().getUser().getName());
            response.setFacultyId(course.getFaculty().getId());
        }
        
        return response;
    }

    public List<CourseResponse> toCourseResponseList(List<Course> courses) {
        return courses.stream()
                .map(this::toCourseResponse)
                .collect(Collectors.toList());
    }

    // ==================== EXAM MAPPINGS ====================

    public ExamResponse toExamResponse(Exam exam) {
        if (exam == null) {
            return null;
        }
        
        ExamResponse response = new ExamResponse();
        response.setId(exam.getId());
        response.setName(exam.getName());
        response.setExamDate(exam.getExamDate());
        
        if (exam.getCourse() != null) {
            response.setCourse(exam.getCourse().getTitle());
            response.setCourseId(exam.getCourse().getId());
        }
        
        return response;
    }

    public List<ExamResponse> toExamResponseList(List<Exam> exams) {
        return exams.stream()
                .map(this::toExamResponse)
                .collect(Collectors.toList());
    }

    // ==================== ATTENDANCE MAPPINGS ====================

    public AttendanceResponse toAttendanceResponse(Attendance attendance) {
        if (attendance == null) {
            return null;
        }
        
        AttendanceResponse response = new AttendanceResponse();
        response.setId(attendance.getId());
        response.setDate(attendance.getDate());
        response.setStatus(attendance.getStatus() != null ? attendance.getStatus().name() : null);
        
        if (attendance.getStudent() != null) {
            response.setStudent(attendance.getStudent().getRollNumber());
            response.setStudentId(attendance.getStudent().getId());
        }
        
        if (attendance.getCourse() != null) {
            response.setCourse(attendance.getCourse().getCode());
            response.setCourseId(attendance.getCourse().getId());
        }
        
        return response;
    }

    public List<AttendanceResponse> toAttendanceResponseList(List<Attendance> attendances) {
        return attendances.stream()
                .map(this::toAttendanceResponse)
                .collect(Collectors.toList());
    }

    // ==================== RESULT MAPPINGS ====================

    public ResultResponse toResultResponse(Result result) {
        if (result == null) {
            return null;
        }
        
        ResultResponse response = new ResultResponse();
        response.setId(result.getId());
        response.setMarks(result.getMarks());
        response.setGrade(result.getGrade());
        
        if (result.getStudent() != null) {
            response.setStudent(result.getStudent().getRollNumber());
            response.setStudentId(result.getStudent().getId());
        }
        
        if (result.getCourse() != null) {
            response.setCourse(result.getCourse().getCode());
            response.setCourseId(result.getCourse().getId());
        }
        
        return response;
    }

    public List<ResultResponse> toResultResponseList(List<Result> results) {
        return results.stream()
                .map(this::toResultResponse)
                .collect(Collectors.toList());
    }

    // ==================== ANNOUNCEMENT MAPPINGS ====================

    public AnnouncementResponse toAnnouncementResponse(Announcement announcement) {
        if (announcement == null) {
            return null;
        }
        
        AnnouncementResponse response = new AnnouncementResponse();
        response.setId(announcement.getId());
        response.setTitle(announcement.getTitle());
        response.setMessage(announcement.getMessage());
        response.setCreatedByRole(announcement.getCreatedByRole() != null ? announcement.getCreatedByRole().getName() : null);
        response.setCreatedAt(announcement.getCreatedAt());
        
        return response;
    }

    public List<AnnouncementResponse> toAnnouncementResponseList(List<Announcement> announcements) {
        return announcements.stream()
                .map(this::toAnnouncementResponse)
                .collect(Collectors.toList());
    }

    public Announcement toAnnouncementEntity(AnnouncementCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        Announcement announcement = new Announcement();
        announcement.setTitle(request.getTitle());
        announcement.setMessage(request.getMessage());
        
        return announcement;
    }

    // ==================== COMPLAINT MAPPINGS ====================

    public ComplaintResponse toComplaintResponse(Complaint complaint) {
        if (complaint == null) {
            return null;
        }
        
        ComplaintResponse response = new ComplaintResponse();
        response.setId(complaint.getId());
        response.setMessage(complaint.getMessage());
        response.setStatus(complaint.getStatus() != null ? complaint.getStatus().name() : null);
        response.setCreatedAt(complaint.getCreatedAt());
        
        if (complaint.getStudent() != null) {
            response.setStudent(complaint.getStudent().getRollNumber());
            response.setStudentId(complaint.getStudent().getId());
        }
        
        return response;
    }

    public List<ComplaintResponse> toComplaintResponseList(List<Complaint> complaints) {
        return complaints.stream()
                .map(this::toComplaintResponse)
                .collect(Collectors.toList());
    }

    public Complaint toComplaintEntity(ComplaintCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        Complaint complaint = new Complaint();
        complaint.setMessage(request.getMessage());
        complaint.setStatus(request.getStatus() != null ? Complaint.Status.valueOf(request.getStatus()) : Complaint.Status.OPEN);
        
        return complaint;
    }

    // ==================== EVENT MAPPINGS ====================

    public EventResponse toEventResponse(Event event) {
        if (event == null) {
            return null;
        }
        
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setName(event.getName());
        response.setDescription(event.getDescription());
        response.setStatus(event.getStatus() != null ? event.getStatus().name() : null);
        response.setCreatedAt(event.getCreatedAt());
        
        if (event.getCreatedByUser() != null) {
            response.setCreatedByUser(event.getCreatedByUser().getName());
            response.setCreatedByUserId(event.getCreatedByUser().getId());
        }
        
        return response;
    }

    public List<EventResponse> toEventResponseList(List<Event> events) {
        return events.stream()
                .map(this::toEventResponse)
                .collect(Collectors.toList());
    }

    public Event toEventEntity(EventCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setStatus(request.getStatus() != null ? Event.Status.valueOf(request.getStatus()) : Event.Status.PENDING);
        
        return event;
    }

    // ==================== MENU MAPPINGS ====================

    public MenuResponse toMenuResponse(Menu menu) {
        if (menu == null) {
            return null;
        }
        
        MenuResponse response = new MenuResponse();
        response.setId(menu.getId());
        response.setName(menu.getName());
        response.setPath(menu.getPath());
        response.setIcon(menu.getIcon());
        
        if (menu.getRole() != null) {
            response.setRole(menu.getRole().getName());
        }
        
        if (menu.getParent() != null) {
            response.setParentId(menu.getParent().getId());
        }
        
        if (menu.getChildren() != null) {
            response.setChildren(menu.getChildren().stream()
                    .map(this::toMenuResponse)
                    .collect(Collectors.toList()));
        }
        
        return response;
    }

    public List<MenuResponse> toMenuResponseList(List<Menu> menus) {
        return menus.stream()
                .map(this::toMenuResponse)
                .collect(Collectors.toList());
    }
}
