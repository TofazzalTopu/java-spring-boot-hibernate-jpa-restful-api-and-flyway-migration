package com.user.progress.service.dto.responses;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserRank {
    int rank;
    int level;
    String name;
    float score;
}
