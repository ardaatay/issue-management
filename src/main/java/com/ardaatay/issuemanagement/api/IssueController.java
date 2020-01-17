package com.ardaatay.issuemanagement.api;

import com.ardaatay.issuemanagement.dto.IssueDto;
import com.ardaatay.issuemanagement.service.impl.IssueServiceImpl;
import com.ardaatay.issuemanagement.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
public class IssueController {

    private final IssueServiceImpl issueService;

    public IssueController(IssueServiceImpl issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueDto> getById(@PathVariable(value = "id") Long id) {
        IssueDto issueDto = issueService.getById(id);
        return ResponseEntity.ok(issueDto);
    }

    @PostMapping
    public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto issueDto) {
        return ResponseEntity.ok(issueService.save(issueDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<IssueDto> updateProject(@PathVariable(value = "id") Long id, @Valid @RequestBody IssueDto issueDto) {
        return ResponseEntity.ok(issueService.update(id, issueDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(issueService.deleteById(id));
    }
}
