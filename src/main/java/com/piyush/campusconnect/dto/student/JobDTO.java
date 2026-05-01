package com.piyush.campusconnect.dto.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobDTO {
    private Long id;
    private String title;
    private String company;
    private String description;
    private LocalDateTime deadline;
    private String type;
    private String location;
}
