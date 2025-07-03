package com.wcs.report.payload;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedCBElementDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CBElementDTO> cbElementDTOS;
}
