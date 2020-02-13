package com.ardaatay.issuemanagement.service;

import com.ardaatay.issuemanagement.dto.IssueDetailDto;
import com.ardaatay.issuemanagement.dto.IssueDto;
import com.ardaatay.issuemanagement.dto.IssueUpdateDto;
import com.ardaatay.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;


public interface IssueService {

    IssueDto save(IssueDto issueDto);

    IssueDto getById(Long id);

    IssueDetailDto getByIdWithDetails(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueDto issueDto);

    Boolean deleteById(Long id);

    IssueDetailDto update(Long id, IssueUpdateDto issueUpdateDto);
}
