package com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport;

import com.huellitassolidarias.huellitassolidarias_backend.entity.AnimalReport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnimalReportResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime reportDate;
    private String image;
    private String state;
    private String username;

    public AnimalReportResponse(AnimalReport report) {
        this.id = report.getId();
        this.name = report.getName();
        this.description = report.getDescription();
        this.location = report.getLocation();
        this.reportDate = report.getReportDate();
        this.image = report.getImageUrl();
        this.state = report.getState().toString();
        this.username = report.getUser().getUsername();
    }
}
