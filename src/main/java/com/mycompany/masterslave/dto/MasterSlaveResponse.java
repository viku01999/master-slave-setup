package com.mycompany.masterslave.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MasterSlaveResponse {

    private Long id;
    private String name;

}
