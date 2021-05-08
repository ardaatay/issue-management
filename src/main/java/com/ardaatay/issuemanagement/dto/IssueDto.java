package com.ardaatay.issuemanagement.dto;

import com.ardaatay.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Issue Data Transfer Object")
public class IssueDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true, value = "Description")
    private String description;

    @ApiModelProperty(value = "Details")
    private String details;

    @ApiModelProperty(value = "Date")
    private Date date;

    @ApiModelProperty(value = "ID")
    private IssueStatus issueStatus;

    @ApiModelProperty(value = "Assignee")
    private UserDto assignee;

    @ApiModelProperty(value = "AssigneeId")
    private Long assigneeId;

    @ApiModelProperty(value = "Project")
    private ProjectDto project;

    @NotNull
    @ApiModelProperty(required = true, value = "ProjectId")
    private Long projectId;
}
