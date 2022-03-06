package com.user.progress.controller;


import com.user.progress.constant.AppConstants;
import com.user.progress.exceptions.AlreadyExistException;
import com.user.progress.model.User;
import com.user.progress.service.UserService;
import com.user.progress.service.dto.responses.Response;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/" + AppConstants.API_VERSION + "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiOperation(value = "User information.")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Save user")
    @PostMapping
    public ResponseEntity<Response<User>> save(@Valid @RequestBody User user) throws URISyntaxException {
        if(userService.findByName(user.getName()).isPresent())
            throw new AlreadyExistException(AppConstants.USER_NAME_ALREADY_EXIST);

        return ResponseEntity.created(new URI("/save")).body(new Response<>(HttpStatus.CREATED.value(),
                AppConstants.USER_SAVED_SUCCESS, userService.save(user)));
    }

    @ApiOperation(value = "Update user by id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<User>> update(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseEntity.accepted().body(new Response<>(HttpStatus.ACCEPTED.value(),
                AppConstants.USER_UPDATED_SUCCESS, userService.update(id, user)));
    }

    @ApiOperation(value = "Update users country by id")
    @PatchMapping(value = "/{id}/{country}")
    public ResponseEntity<Response<User>> updatePartial(@PathVariable Long id, @PathVariable String country) {
        return ResponseEntity.accepted().body(new Response<>(HttpStatus.ACCEPTED.value(),
                AppConstants.USER_UPDATED_SUCCESS, userService.updatePartial(id, country)));
    }


    @ApiOperation(value = "Fetch user by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<User>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new Response<>(HttpStatus.OK.value(),
                AppConstants.USER_FETCH_SUCCESS, userService.findById(id)));
    }

    @ApiOperation(value = "Fetch all users")
    @GetMapping
    public ResponseEntity<Response<List<User>>> findAll() {
        return ResponseEntity.ok().body(new Response<>(HttpStatus.OK.value(),
                AppConstants.USER_FETCH_SUCCESS, userService.findAll()));
    }

}
