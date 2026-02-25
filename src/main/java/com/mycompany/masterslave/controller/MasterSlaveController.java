package com.mycompany.masterslave.controller;

import com.mycompany.masterslave.dto.MasterSlaveRequest;
import com.mycompany.masterslave.dto.MasterSlaveResponse;
import com.mycompany.masterslave.service.MasterSlaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/master-slave")
@RequiredArgsConstructor
public class MasterSlaveController {

    private final MasterSlaveService masterSlaveService;

    @PostMapping
    public MasterSlaveResponse create(@RequestBody MasterSlaveRequest request){
        return masterSlaveService.create(request);
    }

    @GetMapping("/")
    public List<MasterSlaveResponse> getAll(){
        return masterSlaveService.getAll();
    }

    @PutMapping("/{id}")
    public MasterSlaveResponse update(@RequestBody MasterSlaveRequest request, @PathVariable Long id){
        return masterSlaveService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        masterSlaveService.delete(id);
    }
}