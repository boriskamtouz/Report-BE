package com.wcs.report.payload;

import com.wcs.report.entities.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CBElementDTO> cbElementDTOS;

    public ReportDTO toDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setCreatedAt(report.getCreatedAt());
        dto.setUpdatedAt(report.getUpdatedAt());

        if (report.getCbElements() != null) {
            List<CBElementDTO> cbElementDTOS = report.getCbElements()
                    .stream()
                    .map(cb -> {
                        CBElementDTO d = new CBElementDTO();
                        d.setName(cb.getName());
                        d.setVal(cb.getVal());
                        return d;
                    })
                    .toList();
            dto.setCbElementDTOS(cbElementDTOS);
        }

        return dto;
    }

}
