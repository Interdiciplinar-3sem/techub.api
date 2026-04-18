package com.techub.api.dto;

import lombok.Data;

@Data
public class SummaryRequest {
    private String Title;
    private String Content;
    private Long UserId;

}
