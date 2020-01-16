package com.ardaatay.issuemanagment.repository;

import com.ardaatay.issuemanagment.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
