package com.user.progress.repository;

import com.user.progress.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    Optional<UserProgress> findByUserId(Long userId);
    List<UserProgress> findAllByUserId(Long userId);
    List<UserProgress> findFirst10ByOrderByScoreDesc();
}
