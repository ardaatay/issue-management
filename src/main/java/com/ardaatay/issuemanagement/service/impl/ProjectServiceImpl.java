package com.ardaatay.issuemanagement.service.impl;

import com.ardaatay.issuemanagement.dto.ProjectDto;
import com.ardaatay.issuemanagement.entity.Project;
import com.ardaatay.issuemanagement.repository.ProjectRepository;
import com.ardaatay.issuemanagement.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project projectCheck = projectRepository.getByProjectCode(projectDto.getProjectCode());
        if (projectCheck != null) {
            throw new IllegalArgumentException("Project Code Already Exist");
        }

        Project project = modelMapper.map(projectDto, Project.class);
        project = projectRepository.save(project);
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto getById(Long id) {
        Project p = projectRepository.getOne(id);
        return modelMapper.map(p, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<Project> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Project project) {
        projectRepository.delete(project);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteById(Long id) {
        projectRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project projectDb = projectRepository.getOne(id);
        if (projectDb == null) {
            throw new IllegalArgumentException("Project Does Not Exist ID: " + id);
        }

        if (!projectDb.getProjectCode().equals(projectDto.getProjectCode())) {
            Project projectCheck = projectRepository.getByProjectCode(projectDto.getProjectCode());
            if (projectCheck != null) {
                throw new IllegalArgumentException("Project Code Already Exist");
            }
        }

        projectDb.setProjectCode(projectDto.getProjectCode());
        projectDb.setProjectName(projectDto.getProjectName());
        projectRepository.save(projectDb);

        return modelMapper.map(projectDb, ProjectDto.class);
    }

}
