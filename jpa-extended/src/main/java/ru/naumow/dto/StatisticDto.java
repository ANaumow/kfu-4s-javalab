package ru.naumow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticDto {

    private Long userId;
    private int imageCount;
    private int musicCount;
    private int videoCount;

}
