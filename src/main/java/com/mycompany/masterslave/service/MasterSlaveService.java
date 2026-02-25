package com.mycompany.masterslave.service;

import com.mycompany.masterslave.dto.MasterSlaveRequest;
import com.mycompany.masterslave.dto.MasterSlaveResponse;

import java.util.List;

public interface MasterSlaveService {

    MasterSlaveResponse create(MasterSlaveRequest request);

    List<MasterSlaveResponse> getAll();

    MasterSlaveResponse update(Long id, MasterSlaveRequest request);

    void delete(Long id);
}