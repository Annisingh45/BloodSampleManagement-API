package com.ketan.bsm.response;

import com.ketan.bsm.enums.AdminType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private int adminId;
    private AdminType adminType;
    private UserResponse userResponse;

}
