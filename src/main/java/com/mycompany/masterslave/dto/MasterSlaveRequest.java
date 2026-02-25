package com.mycompany.masterslave.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MasterSlaveRequest {
    private  String name;
}
