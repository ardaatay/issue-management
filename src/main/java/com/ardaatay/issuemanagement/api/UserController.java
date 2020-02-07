package com.ardaatay.issuemanagement.api;

import com.ardaatay.issuemanagement.dto.UserDto;
import com.ardaatay.issuemanagement.service.impl.UserServiceImpl;
import com.ardaatay.issuemanagement.util.ApiPaths;
import com.ardaatay.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(produces = ApiPaths.UserCtrl.CTRL)
@Slf4j
@CrossOrigin
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = UserDto.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable) {
        log.info("UserController -> getAllByPagination");
        TPage<UserDto> data = userService.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get By Pagination Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("ProjectController -> getAll");
        List<UserDto> data = userService.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id") Long id) {
        log.info("ProjectController -> GetById");
        log.debug("ProjectController -> GetById->PARAM:" + id);
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = UserDto.class)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update Operation", response = UserDto.class)
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
