package com.ardaatay.issuemanagement.service.impl;

import com.ardaatay.issuemanagement.dto.IssueDetailDto;
import com.ardaatay.issuemanagement.dto.IssueDto;
import com.ardaatay.issuemanagement.dto.IssueHistoryDto;
import com.ardaatay.issuemanagement.entity.Issue;
import com.ardaatay.issuemanagement.repository.IssueRepository;
import com.ardaatay.issuemanagement.service.IssueHistoryService;
import com.ardaatay.issuemanagement.service.IssueService;
import com.ardaatay.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final IssueHistoryService issueHistoryService;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, IssueHistoryService issueHistoryService) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.issueHistoryService = issueHistoryService;
    }


    @Override
    public IssueDto save(IssueDto issueDto) {
        Issue issueDb = modelMapper.map(issueDto, Issue.class);
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
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage page = new TPage<IssueDto>();
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

    @Override
    public IssueDto update(Long id, IssueDto issueDto) {
        Issue issueDb = issueRepository.getOne(id);
        if (issueDb == null) {
            throw new IllegalArgumentException("Issue Does Not Exist");
        }

        issueDb.setDescription(issueDto.getDescription());
        issueDb.setDetails(issueDto.getDetails());
        issueDb.setDate(issueDto.getDate());
        issueDb.setIssueStatus(issueDto.getIssueStatus());

        return modelMapper.map(issueRepository.save(issueDb), IssueDto.class);
    }
}
