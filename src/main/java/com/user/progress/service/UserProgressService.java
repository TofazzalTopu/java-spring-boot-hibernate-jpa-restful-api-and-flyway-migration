package com.user.progress.service;

import com.user.progress.model.UserProgress;
import com.user.progress.service.dto.UserProgressDTO;
import com.user.progress.service.dto.responses.UserRank;

import java.util.List;
import java.util.Optional;

public interface UserProgressService {

    UserProgress save(Long userId, UserProgressDTO userProgressDTO);
    UserProgress update(Long userId, UserProgressDTO userProgressDTO);
    UserProgress findById(Long userProgressId);
    UserProgress findUserProgressByUserId(Long userId);
    Optional<UserProgress> findByUserId(Long userId);
    UserRank findRankByUserId(Long userId);
    List<UserProgress> findAll();
    List<UserRank> findTopTenUsersRankBasedOnScore();
    List<UserProgress> findAllByUserId(Long userId);

}
