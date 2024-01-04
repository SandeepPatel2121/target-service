package com.target.service.model.filters;

import lombok.Getter;
import lombok.Setter;

/*
 * @author Sandeep Patel
 * @Since 21-11-2022
 * @description
 */

@Getter
@Setter
public class FilterModel {

    private Integer page;
    private Integer limit;
    private String keyword;

    public FilterModel(Integer page, Integer limit, String keyword) {
        this.page = page;
        this.limit = limit;
        this.keyword = keyword;
    }
}
