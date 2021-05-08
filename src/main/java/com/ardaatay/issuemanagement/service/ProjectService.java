package com.ardaatay.issuemanagement.service;

import com.ardaatay.issuemanagement.dto.ProjectDto;
import com.ardaatay.issuemanagement.entity.Project;
import com.ardaatay.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

	ProjectDto save(ProjectDto project);

	ProjectDto getById(Long id);

	List<ProjectDto> getByProjectCode(String projectCode);

	List<Project> getByProjectCodeContains(String projectCode);

	TPage<ProjectDto> getAllPageable(Pageable pageable);

	List<ProjectDto> getAll();

	Boolean delete(Project project);

	Boolean deleteById(Long id);

	ProjectDto update(Long id, ProjectDto projectDto);
}
