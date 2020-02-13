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

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true, value = "ID")
    private String description;

    @ApiModelProperty(value = "ID")
    private String details;

    @ApiModelProperty(value = "ID")
    private Date date;

    @ApiModelProperty(value = "ID")
    private IssueStatus issueStatus;

    @ApiModelProperty(value = "ID")
    private UserDto assignee;

    @ApiModelProperty(value = "ID")
    private Long assigneeId;

    @ApiModelProperty(value = "ID")
    private ProjectDto project;

    @NotNull
    @ApiModelProperty(required = true, value = "ID")
    private Long projectId;
}
