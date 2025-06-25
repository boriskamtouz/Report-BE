package com.wcs.report.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<CBElementDTO> cbElements = new ArrayList<>();
}
