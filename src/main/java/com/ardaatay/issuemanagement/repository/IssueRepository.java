package com.ardaatay.issuemanagement.repository;

import com.ardaatay.issuemanagement.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
