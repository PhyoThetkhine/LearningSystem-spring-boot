package com.system.Learning_system_springboot_angular.model.dto;

import com.system.Learning_system_springboot_angular.model.entity.Submission;

public class SubmissionFileDTO {
    private Integer id;
    private String fileUrl;
    private Submission submission;
    private Integer submissionID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Integer submissionID) {
        this.submissionID = submissionID;
    }
}
