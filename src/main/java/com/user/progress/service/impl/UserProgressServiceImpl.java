package com.user.progress.service.impl;

import com.user.progress.constant.AppConstants;
import com.user.progress.exceptions.AlreadyExistException;
import com.user.progress.exceptions.NotFoundException;
import com.user.progress.model.UserProgress;
import com.user.progress.repository.UserProgressRepository;
import com.user.progress.service.UserProgressService;
import com.user.progress.service.UserService;
import com.user.progress.service.dto.UserProgressDTO;
import com.user.progress.service.dto.responses.UserRank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserProgressServiceImpl implements UserProgressService {

    private final UserProgressRepository userProgressRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public UserProgress save(Long userId, UserProgressDTO userProgressDTO) {
        log.info("\nSaving user progress {}", userProgressDTO);
        Optional<UserProgress> optionalUserProgress = findByUserId(userId);
        if (optionalUserProgress.isPresent()) {
            throw new AlreadyExistException(AppConstants.USER_PROGRESS_ALREADY_EXIST_WITH_THIS_USER);
        }
        UserProgress userProgress = modelMapper.map(userProgressDTO, UserProgress.class);
        userProgress.setUser(userService.findById(userId));
        return userProgressRepository.save(userProgress);
    }

    @Override
    public UserProgress update(Long userId, UserProgressDTO userProgressDTO) {
        log.info("\nUpdating user progress by user id {}", userId);
        Optional<UserProgress> optionalUserProgress = findByUserId(userId);
        if (!optionalUserProgress.isPresent())
            throw new AlreadyExistException(AppConstants.USER_PROGRESS_NOT_FOUND);

        UserProgress userProgress = buildUserProgress(userId, optionalUserProgress.get().getId(), userProgressDTO);
        return userProgressRepository.save(userProgress);
    }

    @Override
    public UserProgress findById(Long userProgressId) {
        return userProgressRepository.findById(userProgressId).orElseThrow(() -> new NotFoundException(AppConstants.USER_PROGRESS_NOT_FOUND));
    }

    @Override
    public UserProgress findUserProgressByUserId(Long userId) {
        Optional<UserProgress> optionalUserProgress = userProgressRepository.findByUserId(userId);
        if (optionalUserProgress.isPresent()) {
            return optionalUserProgress.get();
        }
        return null;
    }

    @Override
    public Optional<UserProgress> findByUserId(Long userId) {
        return userProgressRepository.findByUserId(userId);
    }

    @Override
    public UserRank findRankByUserId(Long userId) {
        return getUserRank(userId, findAll());
    }

    @Override
    public List<UserRank> findTopTenUsersRankBasedOnScore() {
        log.info("\nFetching top 10 users progress based on score.");
        List<UserProgress> userProgressList = userProgressRepository.findFirst10ByOrderByScoreDesc();
        return getUserRankList(userProgressList);
    }

    @Override
    public List<UserProgress> findAll() {
        return userProgressRepository.findAll(Sort.by("score").descending());
    }

    @Override
    public List<UserProgress> findAllByUserId(Long userId) {
        log.info("\nFetching users progress by user id.");
        return userProgressRepository.findAllByUserId(userId);
    }

    private UserProgress buildUserProgress(Long userId, Long userProgressId, UserProgressDTO userProgressDTO) {
        UserProgress userProgress = modelMapper.map(userProgressDTO, UserProgress.class);
        userProgress.setId(userProgressId);
        userProgress.setUser(userService.findById(userId));
        return userProgress;
    }

    private UserRank getUserRank(Long userId, List<UserProgress> userProgressList) {
        UserRank userRank = new UserRank();
        for (int i = 0; i < userProgressList.size(); i++) {
            UserProgress userProgress = userProgressList.get(i);
            if (userProgress.getUser().getId().equals(userId)) {
                userRank = modelMapper.map(userProgress, UserRank.class);
                userRank.setRank(i + 1);
                userRank.setName(userProgress.getUser().getName());
                break;
            }
        }
        log.info("\n{}", userRank);
        return userRank;
    }

    private List<UserRank> getUserRankList(List<UserProgress> userProgressList) {
        List<UserRank> userRankList = new ArrayList<>();
        for (int i = 0; i < userProgressList.size(); i++) {
            UserRank userRank = new UserRank();
            UserProgress userProgress = userProgressList.get(i);
            userRank = modelMapper.map(userProgress, UserRank.class);
            userRank.setRank(i + 1);
            userRank.setName(userProgress.getUser().getName());
            userRankList.add(userRank);
        }
        log.info("\nTotal UserRanks {}", userRankList.size());
        return userRankList;
    }

}
