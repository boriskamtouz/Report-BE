package com.wcs.report.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CBReportUpdateDTO {
    private Long cbElementId;
    private String val;
}
