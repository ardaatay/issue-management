package com.ardaatay.issuemanagment.dto;

import com.ardaatay.issuemanagment.entity.IssueStatus;
import lombok.Data;

import java.util.Date;

@Data
public class IssueDto {
    private Long id;
    private String description;
    private String details;
    private Date date;
    private IssueStatus issueStatus;
    private UserDto assignee;
    private ProjectDto project;
}
