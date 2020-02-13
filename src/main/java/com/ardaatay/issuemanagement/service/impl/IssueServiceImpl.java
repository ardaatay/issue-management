package com.ardaatay.issuemanagement.service.impl;

import com.ardaatay.issuemanagement.dto.*;
import com.ardaatay.issuemanagement.entity.Issue;
import com.ardaatay.issuemanagement.entity.Project;
import com.ardaatay.issuemanagement.entity.User;
import com.ardaatay.issuemanagement.repository.IssueRepository;
import com.ardaatay.issuemanagement.service.IssueHistoryService;
import com.ardaatay.issuemanagement.service.IssueService;
import com.ardaatay.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final IssueHistoryService issueHistoryService;
    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, IssueHistoryService issueHistoryService, ProjectServiceImpl projectService, UserServiceImpl userService) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.issueHistoryService = issueHistoryService;
        this.projectService = projectService;
        this.userService = userService;
    }


    @Override
    public IssueDto save(IssueDto issueDto) {
        Issue issueDb = modelMapper.map(issueDto, Issue.class);

        if (issueDto.getDate() == null) {
            issueDb.setDate(new Date());
        }

        ProjectDto projectDto = projectService.getById(issueDto.getProjectId());
        Project project = modelMapper.map(projectDto, Project.class);
        issueDb.setProject(project);

        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb, IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issueDb = issueRepository.getOne(id);
        return modelMapper.map(issueDb, IssueDto.class);
    }

    @Override
    public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);
        return detailDto;
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findByOrderByIdDesc(pageable);
        TPage<IssueDto> page = new TPage<>();
        IssueDto[] dtos = modelMapper.map(data.getContent(), IssueDto[].class);
        page.setStat(data, Arrays.asList(dtos));
        return page;
    }

    @Override
    public Boolean delete(IssueDto issueDto) {
        issueRepository.delete(modelMapper.map(issueDto, Issue.class));
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteById(Long id) {
        issueRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public IssueDetailDto update(Long id, IssueUpdateDto issueUpdateDto) {
        Issue issueDb = issueRepository.getOne(id);

        issueHistoryService.addHistory(id, issueDb);

        if (issueDb.getId() == null) {
            throw new IllegalArgumentException("Issue Does Not Exist");
        }

        issueDb.setDescription(issueUpdateDto.getDescription());
        issueDb.setDetails(issueUpdateDto.getDetails());
        issueDb.setDate(issueUpdateDto.getDate());
        issueDb.setIssueStatus(issueUpdateDto.getIssueStatus());

        ProjectDto projectDto = projectService.getById(issueUpdateDto.getProject_id());
        Project project = modelMapper.map(projectDto, Project.class);
        issueDb.setProject(project);

        UserDto userDto = userService.getById(issueUpdateDto.getAssignee_id());
        User user = modelMapper.map(userDto, User.class);
        issueDb.setAssignee(user);

        return getByIdWithDetails(issueDb.getId());
    }
}
