package com.mycompany.masterslave.service.impl;


import com.mycompany.masterslave.dto.MasterSlaveRequest;
import com.mycompany.masterslave.dto.MasterSlaveResponse;
import com.mycompany.masterslave.entity.MasterSlave;
import com.mycompany.masterslave.repository.MasterSlaveRepository;
import com.mycompany.masterslave.service.MasterSlaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
public class MasterSlaveServiceImpl implements MasterSlaveService {

    private final MasterSlaveRepository repository;

    /*
     * Client calls POST API
     * Controller calls this method
     *
     * @Transactional (default readOnly = false)
     * AOP detects write operation
     * → sets MASTER
     */
    @Transactional
    public MasterSlaveResponse create(MasterSlaveRequest request) {

        MasterSlave entity = MasterSlave.builder()
                .name(request.getName())
                .build();

        return mapToResponse(repository.save(entity));
    }

    /*
     * Client calls GET API
     * @Transactional(readOnly = true)
     * AOP detects readOnly = true
     * → sets SLAVE
     */
    @Override
    @Transactional(readOnly = true)
    public List<MasterSlaveResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public MasterSlaveResponse update(Long id, MasterSlaveRequest request) {

        MasterSlave entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        entity.setName(request.getName());

        return mapToResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private MasterSlaveResponse mapToResponse(MasterSlave entity) {
        return MasterSlaveResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}


//
//@Service
//@RequiredArgsConstructor
//public class MasterSlaveServiceImpl implements MasterSlaveService {
//
//    private final MasterSlaveRepository repository;
//
//
//    public MasterSlaveResponse create(MasterSlaveRequest request) {
//        MasterSlave masterSlave = MasterSlave.builder()
//                .name(request.getName())
//                .build();
//
//        MasterSlave saved = repository.save(masterSlave);
//
//        return mapToResponse(saved);
//    }
//
//    @Override
//    public List<MasterSlaveResponse> getAll() {
//        return repository.findAll()
//                .stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public MasterSlaveResponse update(Long id, MasterSlaveRequest request) {
//        MasterSlave masterSlave = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Record not found"));
//
//        masterSlave.setName(request.getName());
//
//        MasterSlave updated = repository.save(masterSlave);
//
//        return mapToResponse(updated);
//    }
//
//    @Override
//    public void delete(Long id) {
//        repository.deleteById(id);
//    }
//
//    private MasterSlaveResponse mapToResponse(MasterSlave masterSlave) {
//        return MasterSlaveResponse.builder()
//                .id(masterSlave.getId())
//                .name(masterSlave.getName())
//                .build();
//    }
//}
