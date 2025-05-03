package com.ketan.bsm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRequest {

    @NotNull(message="username cannot be null")
    @NotBlank(message = "username cannot be blank")
    private String hospitalName;

}
