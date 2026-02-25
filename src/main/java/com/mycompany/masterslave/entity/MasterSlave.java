package com.mycompany.masterslave.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "master_slave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterSlave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
