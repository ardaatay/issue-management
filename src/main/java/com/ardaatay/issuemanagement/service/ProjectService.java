package com.ardaatay.issuemanagement.service;

import com.ardaatay.issuemanagement.dto.ProjectDto;
import com.ardaatay.issuemanagement.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProjectService {

    ProjectDto save(ProjectDto project);

    ProjectDto getById(Long id);

    List<ProjectDto> getByProjectCode(String projectCode);

    List<Project> getByProjectCodeContains(String projectCode);

    Page<Project> getAllPageable(Pageable pageable);

    Boolean delete(Project project);

    Boolean deleteById(Long id);

    ProjectDto update(Long id, ProjectDto projectDto);
}
