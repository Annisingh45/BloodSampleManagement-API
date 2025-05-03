package com.ketan.bsm.response;

import com.ketan.bsm.enums.BloodGroup;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DonationRequestResponse {
    private int requestId;
    private LocalDate date;
    private LocalTime time;
    private List<String> cites;
    private List<BloodGroup> bloodGroups;
}
