package com.ardaatay.issuemanagment.repository;

import com.ardaatay.issuemanagment.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {
}
