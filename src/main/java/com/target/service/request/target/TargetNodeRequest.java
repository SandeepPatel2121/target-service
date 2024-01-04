package com.target.service.request.target;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetNodeRequest {

    private String node;
    private String description;
    private String target;
    private String recommendation;
    private String alternative;
    private String component;

}
