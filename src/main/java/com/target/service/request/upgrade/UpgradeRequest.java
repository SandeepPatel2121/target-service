package com.target.service.request.upgrade;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpgradeRequest {

    private Long projectId;

    private MultipartFile frontEnd;

}
