package com.example.learnspring.infrastructure.repositories.new_bag.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bags")
public class Bag {
    @Id
    private String id;
    @Basic(optional = false)
    @Column(name = "order")
    private Long order;
}
