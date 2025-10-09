package com.controller;

import com.dao.CourseMapper;
import com.entity.Course;
import com.entity.Enrollment;
import com.entity.Result;
import com.entity.User;
import com.service.CourseService;
import com.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class EnrollmentController {
    @Resource
    private EnrollmentService enrollmentService;
    @Resource
    private CourseService courseService;

    //    教师查看课程学生：GET /api/courses/{courseId}/students
    @GetMapping("/api/courses/{courseId}/students")
    @ResponseBody
    public Result getStudentInfoByCourseId(@PathVariable("courseId") Long id) {
        List<User> users = enrollmentService.searchStudentByCourseId(id);
        if (users == null) {
            return Result.error("404", "查询学生信息失败，请联系管理员");
        } else {
            return Result.success("查询成功", users);
        }
    }

    //    学生选课：POST /api/enrollments
    @PostMapping("/api/enrollments")
    @ResponseBody
    public Result insertEnrollment(@RequestParam("course_id") Long course_id,
                                   HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        //超出最大人数时 无法选择课程
        Course course = courseService.searchCourseById(course_id);
        if (course.getMax_students().equals(course.getCurrent_students())) {
            return Result.error("404", "该课程选择人数已满 请选择其他课程");
        }

        //查询该用户是否已经选择过该课程
        List<User> users = enrollmentService.searchStudentByCourseId(course_id);
        for (User user : users) {
            if (Objects.equals(user.getId(), currentUser.getId())) {
                return Result.error("404", "请勿重复选择该课程");
            }
        }


        Enrollment enrollment = new Enrollment();
        enrollment.setCourse_id(course_id);
        enrollment.setStudent_id(currentUser.getId());
        int infect = enrollmentService.insertEnrollment(enrollment);
        if (infect < 1) {
            return Result.error("404", "选课失败，请联系管理员");
        } else {
            return Result.success("选课成功");
        }
    }

    //    学生退课：DELETE /api/enrollments/{id}
    @DeleteMapping("/api/enrollments/{id}")
    @ResponseBody
    public Result deleteEnrollment(@PathVariable("id") Long course_id,
                                   HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse_id(course_id);
        enrollment.setStudent_id(currentUser.getId());
        int infect = enrollmentService.deleteEnrollment(enrollment);
        if (infect < 1) {
            return Result.error("404", "退课失败，请联系管理员");
        } else {
            return Result.success("退课成功");
        }
    }

    //    学生查看已选课程列表：GET /api/enrollments/student
    @GetMapping("/api/enrollments/student")
    @ResponseBody
    public Result searchCourseByStudentId(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        List<Course> courses = enrollmentService.searchCourseByStudentId(currentUser.getId());
        if (courses == null) {
            return Result.error("404", "查看课程失败，请联系管理员");
        } else {
            return Result.success("查看课程成功", courses);
        }
    }

}
