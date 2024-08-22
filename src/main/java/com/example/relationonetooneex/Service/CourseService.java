package com.example.relationonetooneex.Service;

import com.example.relationonetooneex.API.APIException;
import com.example.relationonetooneex.Model.Course;
import com.example.relationonetooneex.Model.Teacher;
import com.example.relationonetooneex.Repository.CourseRepository;
import com.example.relationonetooneex.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course, Integer id) {
       Course c = courseRepository.findCourseById(id);
        if (c == null) {
            throw new APIException("Teacher not found");
        }
        course.setName(course.getName());
        courseRepository.save(course);
    }

    public void deleteCourse(Integer id){
        Course course = courseRepository.findCourseById(id);
        if (course == null) {
            throw new APIException("Course not found");
        }
        courseRepository.delete(course);
        }


    public void assignTeacherToCourses(Integer course_id, Integer teacher_id){
        Course c = courseRepository.findCourseById(course_id);
        Teacher t = teacherRepository.findTeacherById(teacher_id);
        if (c == null || t == null) {
            throw new APIException("Course not found");
        }
        c.setTeacher(t);
        courseRepository.save(c);
    }

    public String findTeacherByCourseId(Integer id) {
        Course c = courseRepository.findCourseById(id);
        if (c == null || c.getTeacher() == null) {
            throw new APIException("Course not found");
        }
        return c.getTeacher().getName();

    }

    }

