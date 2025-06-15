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
    private String imageUrl;
    private String state;
    private String username;
    private String contactName;
    private String contactPhone;

    public AnimalReportResponse(AnimalReport report) {
        this.id = report.getId();
        this.name = report.getName();
        this.description = report.getDescription();
        this.location = report.getLocation();
        this.reportDate = report.getReportDate();
        this.imageUrl = report.getImageUrl();
        this.state = report.getState().toString();
        this.username = report.getUser().getUsername();
        this.contactName = report.getContactName();
        this.contactPhone = report.getContactPhone();
    }
}
