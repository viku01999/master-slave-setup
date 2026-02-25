package com.mycompany.masterslave.repository;

import com.mycompany.masterslave.entity.MasterSlave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterSlaveRepository extends JpaRepository<MasterSlave, Long> { }
