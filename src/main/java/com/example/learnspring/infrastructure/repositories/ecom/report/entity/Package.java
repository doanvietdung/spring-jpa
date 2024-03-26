package com.example.learnspring.infrastructure.repositories.ecom.report.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "packages")
public class Package {
    @Id
    private String id;
    @Basic(optional = false)
    @Column(name = "order")
    private Long order;
    @Basic(optional = false)
    @Column(name = "alias")
    private String alias;
    @Column(name = "cur_station_id")
    private Integer curStationId;
    @Column(name = "package_status_id")
    private Integer packageStatusId;
    @Column(name = "cur_bag_order")
    private Integer curBagOrder;

}
