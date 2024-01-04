package com.target.service.model.filters;
/*
 * @author Sandeep Patel
 * @Since 21-11-2022
 * @description
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterResponse {

    private Integer page;
    private Integer limit;
    private Integer total;
    private Object data;

}
