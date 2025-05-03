package com.ketan.bsm.request;

import com.ketan.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequestRequest {

    private LocalDate date;
    private LocalTime time;
    private List<String> cites;
    private List<BloodGroup> bloodGroups;
}
