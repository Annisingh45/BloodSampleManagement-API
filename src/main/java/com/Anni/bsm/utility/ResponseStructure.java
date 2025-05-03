package com.ketan.bsm.utility;

import com.ketan.bsm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure <T>{
    private int status;
    private String message;
    private T data;


}
