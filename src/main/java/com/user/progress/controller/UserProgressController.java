package com.user.progress.controller;

import com.user.progress.constant.AppConstants;
import com.user.progress.exceptions.AlreadyExistException;
import com.user.progress.model.UserProgress;
import com.user.progress.service.UserProgressService;
import com.user.progress.service.dto.UserProgressDTO;
import com.user.progress.service.dto.responses.Response;
import com.user.progress.service.dto.responses.UserRank;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/" + AppConstants.API_VERSION + "/progress", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiOperation(value = "User progress information.")
public class UserProgressController {

    private final UserProgressService userProgressService;

    @ApiOperation(value = "Save user progress by user id")
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Response<UserProgress>> save(@PathVariable Long userId, @RequestBody UserProgressDTO userProgressDTO) throws URISyntaxException {
        if(userProgressService.findByUserId(userId).isPresent())
            throw new AlreadyExistException(AppConstants.USER_PROGRESS_ALREADY_EXIST_WITH_THIS_USER);

        return ResponseEntity.created(new URI("/save")).body(new Response<>(HttpStatus.CREATED.value(),
                AppConstants.USER_PROGRESS_SAVED_SUCCESS, userProgressService.save(userId, userProgressDTO)));
    }

    @ApiOperation(value = "Update user progress by user id")
    @PutMapping(value = "/{userId}")
    public ResponseEntity<Response<UserProgress>> update(@PathVariable Long userId, @RequestBody UserProgressDTO user) {
        return ResponseEntity.accepted().body(new Response<>(HttpStatus.ACCEPTED.value(),
                AppConstants.USER_PROGRESS_UPDATED_SUCCESS, userProgressService.update(userId, user)));
    }

    @ApiOperation(value = "Fetch user progress by user id")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<Response<UserProgress>> findUserProgressByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(new Response<>(HttpStatus.OK.value(),
                AppConstants.USER_PROGRESS_FETCH_SUCCESS, userProgressService.findUserProgressByUserId(userId)));
    }

    @ApiOperation(value = "Fetch user score by user id")
    @GetMapping(value = "/{userId}/score")
    public ResponseEntity<Response<UserRank>> findUserRankByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(new Response<>(HttpStatus.OK.value(),
                AppConstants.USER_PROGRESS_FETCH_SUCCESS, userProgressService.findRankByUserId(userId)));
    }

    @ApiOperation(value = "Fetch top 10 users progress based on score")
    @GetMapping
    public ResponseEntity<Response<List<UserRank>>> findTopTenUsersRankBasedOnScore() {
        return ResponseEntity.ok().body(new Response<>(HttpStatus.OK.value(),
                AppConstants.USER_PROGRESS_FETCH_SUCCESS, userProgressService.findTopTenUsersRankBasedOnScore()));
    }

}
