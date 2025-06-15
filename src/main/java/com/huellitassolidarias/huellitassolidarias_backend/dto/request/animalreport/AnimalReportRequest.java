package com.huellitassolidarias.huellitassolidarias_backend.dto.request.animalreport;

import com.huellitassolidarias.huellitassolidarias_backend.enums.State;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AnimalReportRequest {
    private String name;
    private String description;
    private String location;
    private MultipartFile image;
    private State state;
    private String contactName;
    private String contactPhone;
}
