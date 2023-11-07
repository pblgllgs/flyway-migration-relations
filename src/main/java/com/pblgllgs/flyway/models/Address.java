package com.pblgllgs.flyway.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_address_sequence_generator"
    )
    @SequenceGenerator(
            name = "id_address_sequence_generator",
            sequenceName = "id_address_sequence_generator",
            allocationSize = 1
    )
    private Integer id;
    @Column(name = "street_name", nullable = false)
    private String streetName;
    @Column(name = "house_number", nullable = false)
    private String houseNumber;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
}
