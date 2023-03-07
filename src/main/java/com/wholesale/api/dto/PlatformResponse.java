package com.wholesale.api.dto;

import com.wholesale.exception.PlatformError;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class PlatformResponse extends RepresentationModel<PlatformResponse> {

    private boolean success;
    private Object data;
    private PlatformError error;
}
