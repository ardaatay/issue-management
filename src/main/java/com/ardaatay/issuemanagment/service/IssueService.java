package com.ardaatay.issuemanagment.service;

import com.ardaatay.issuemanagment.dto.IssueDto;
import com.ardaatay.issuemanagment.util.TPage;
import org.springframework.data.domain.Pageable;


public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueDto issue);
}
