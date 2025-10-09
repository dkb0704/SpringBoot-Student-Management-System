package com.controller;

import com.dao.CourseMapper;
import com.dao.EnrollmentMapper;
import com.entity.Course;
import com.entity.Result;
import com.entity.User;
import com.service.CourseService;
import com.service.EnrollmentService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private EnrollmentService enrollmentService;

    //    学生查看可选课程列表：GET /api/courses/available
    @GetMapping("/api/courses/available")
    @ResponseBody
    public Result listElectiveCourse() {
        List<Course> courses = courseService.listElectiveCourse();
        if (courses == null) {
            return Result.error("404", "获取可选课程表失败");
        } else {
            return Result.success("获取可选课表成功", courses);
        }
    }

    //    教师查看所有课程：GET /api/courses
    @GetMapping("/api/courses")
    @ResponseBody
    public Result listCourse() {
        List<Course> courses = courseService.listCourse();
        if (courses == null) {
            return Result.error("404", "获取全部课程表失败");
        } else {
            return Result.success("获取全部课表成功", courses);
        }
    }

    //    教师创建课程：POST /api/courses
    @PostMapping("/api/courses")
    @ResponseBody
    public Result insertCourse(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("max_students") Integer maxStudents,
                               HttpSession session) {
        Course course = new Course();
        course.setMax_students(maxStudents);
        course.setName(name);
        course.setDescription(description);
        User currentUser = (User) session.getAttribute("currentUser");
        course.setTeacher_id(currentUser.getId());
        int infected = courseService.insertCourse(course);
        if (infected < 1) {
            return Result.error("404", "创建课程失败");
        } else {
            return Result.success("新建课程成功", course);
        }
    }

    //    教师修改课程信息：PUT /api/courses/{id}
    @PutMapping("/api/courses/{id}")
    @ResponseBody
    public Result updateCourse(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam(value = "max_students", required = false) Integer max_students,
                               @RequestParam(value = "status", required = false) Integer status,
                               @PathVariable("id") Long id) {
        Course course = new Course();
        course.setMax_students(max_students);
        course.setName(name);
        course.setDescription(description);
        course.setId(id);
        course.setStatus(status);
        int infected = courseService.updateCourse(course);
        Course update_course = courseService.searchCourseById(id);
        if (infected < 1) {
            return Result.error("404", "修改课程失败");
        } else {
            return Result.success("修改课程成功", update_course);
        }
    }

    //    教师删除课程：DELETE /api/courses/{id}
    @DeleteMapping("/api/courses/{id}")
    @ResponseBody
    public Result deleteCourseById(@PathVariable("id") Long id) {
        int infected = courseService.deleteCourseById(id);
        if (infected < 1) {
            return Result.error("404", "删除课程失败");
        } else {
            return Result.success("删除课程成功");
        }
    }

}
