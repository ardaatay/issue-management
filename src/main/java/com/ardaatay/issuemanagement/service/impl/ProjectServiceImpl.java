package com.ardaatay.issuemanagement.service.impl;

import com.ardaatay.issuemanagement.dto.ProjectDto;
import com.ardaatay.issuemanagement.dto.UserDto;
import com.ardaatay.issuemanagement.entity.Project;
import com.ardaatay.issuemanagement.entity.User;
import com.ardaatay.issuemanagement.repository.ProjectRepository;
import com.ardaatay.issuemanagement.service.ProjectService;
import com.ardaatay.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserServiceImpl userServiceImpl;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, UserServiceImpl userServiceImpl) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        if (projectDto.getId() != null) {
            Project p = projectRepository.getOne(projectDto.getId());
            if (p.getId() != 0) {
                throw new IllegalArgumentException("Project had been created");
            }
            throw new IllegalArgumentException("Project id sets automatically");
        }
        Project projectCheck = projectRepository.getByProjectCode(projectDto.getProjectCode());
        if (projectCheck != null) {
            throw new IllegalArgumentException("Project Code Already Exist");
        }

        Project project = modelMapper.map(projectDto, Project.class);
        UserDto userDto = userServiceImpl.getById(projectDto.getManagerId());
        if (userDto != null) {
            User user = modelMapper.map(userDto, User.class);
            project.setManager(user);
        }
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
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
        return response;
    }

    @Override
    public List<ProjectDto> getAll() {
        return Arrays.asList(modelMapper.map(projectRepository.findAll(), ProjectDto[].class));
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
        if (projectDb.getId() == 0) {
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
