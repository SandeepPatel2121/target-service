package com.target.service.model.recommendation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationModel {

    private String property;
    private Object value;
    private String recommendation;

    public RecommendationModel(String property, Object value, String recommendation) {
        this.property = property;
        this.value = value;
        this.recommendation = recommendation;
    }
}
