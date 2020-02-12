package com.ardaatay.issuemanagement.service;

import com.ardaatay.issuemanagement.dto.IssueHistoryDto;
import com.ardaatay.issuemanagement.entity.Issue;
import com.ardaatay.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IssueHistoryService {

    IssueHistoryDto save(IssueHistoryDto issueHistory);

    IssueHistoryDto getById(Long id);

    List<IssueHistoryDto> getByIssueId(Long id);

    TPage<IssueHistoryDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueHistoryDto issueHistory);

    void addHistory(Long id, Issue issue);
}
