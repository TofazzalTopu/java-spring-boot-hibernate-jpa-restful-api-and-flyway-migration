package com.user.progress.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_progress")
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "level can not be null")
    @Column(nullable = false)
    int level;

    @NotNull(message = "score can not be null")
    @Column(nullable = false)
    float score;

    @NotNull(message = "user can not be null")
    @OneToOne(optional = false)
    User user;

}
