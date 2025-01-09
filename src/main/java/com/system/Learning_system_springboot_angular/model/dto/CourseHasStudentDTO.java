package com.system.Learning_system_springboot_angular.model.dto;

import com.system.Learning_system_springboot_angular.model.entity.Course;
import com.system.Learning_system_springboot_angular.model.entity.User;

import java.util.Date;

public class CourseHasStudentDTO {
    private Integer courseId;
    private Course course;
    private Integer studentId;
    private Integer createAdminId;
    private String createAdminName;
    private User createAdmin;
    private User student;
    private String status;
    private Date createDate;
    private Date updateDate;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getCreateAdminId() {
        return createAdminId;
    }

    public void setCreateAdminId(Integer createAdminId) {
        this.createAdminId = createAdminId;
    }

    public String getCreateAdminName() {
        return createAdminName;
    }

    public void setCreateAdminName(String createAdminName) {
        this.createAdminName = createAdminName;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getCreateAdmin() {
        return createAdmin;
    }

    public void setCreateAdmin(User createAdmin) {
        this.createAdmin = createAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
