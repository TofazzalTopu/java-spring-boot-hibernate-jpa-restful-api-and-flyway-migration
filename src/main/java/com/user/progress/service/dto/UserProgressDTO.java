package com.user.progress.service.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserProgressDTO {

    int level;
    Float score;

}
