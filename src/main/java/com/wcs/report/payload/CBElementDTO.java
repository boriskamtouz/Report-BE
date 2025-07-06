package com.wcs.report.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CBElementDTO {
    private Long id;
    private String code;
    private String name;
}
