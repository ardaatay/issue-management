package com.ardaatay.issuemanagement.api;

import com.ardaatay.issuemanagement.dto.IssueDetailDto;
import com.ardaatay.issuemanagement.dto.IssueDto;
import com.ardaatay.issuemanagement.dto.IssueUpdateDto;
import com.ardaatay.issuemanagement.entity.IssueStatus;
import com.ardaatay.issuemanagement.service.impl.IssueServiceImpl;
import com.ardaatay.issuemanagement.util.ApiPaths;
import com.ardaatay.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(produces = ApiPaths.IssueCtrl.CTRL)
@Slf4j
@CrossOrigin
public class IssueController {

    private final IssueServiceImpl issueService;

    public IssueController(IssueServiceImpl issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> getById(@PathVariable(value = "id") Long id) {
        IssueDto issueDto = issueService.getById(id);
        return ResponseEntity.ok(issueDto);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value = "id", required = true) Long id) {
        IssueDetailDto detailDto = issueService.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> createIssue(@Valid @RequestBody IssueDto issueDto) {
        return ResponseEntity.ok(issueService.save(issueDto));
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update Operation", response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> updateIssue(@PathVariable(value = "id") Long id, @Valid @RequestBody IssueUpdateDto issueUpdateDto) {
        return ResponseEntity.ok(issueService.update(id, issueUpdateDto));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(issueService.deleteById(id));
    }

    @GetMapping("/statuses")
    @ApiOperation(value = "Get All Issue Statuses Operation", response = IssueDto.class)
    public ResponseEntity<List<IssueStatus>> getAll() {
        return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get All Issue With Pagination Statuses Operation", response = IssueDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        log.info("IssueController -> getAllByPagination");
        TPage<IssueDto> data = issueService.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
}
