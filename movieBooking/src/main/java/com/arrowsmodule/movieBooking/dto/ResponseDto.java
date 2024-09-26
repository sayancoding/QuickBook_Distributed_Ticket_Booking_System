package com.arrowsmodule.movieBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    @JsonUnwrapped
    private MetaResponse metadata;
    private Object data;
}
