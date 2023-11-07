package com.pblgllgs.flyway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_department_sequence_generator"
    )
    @SequenceGenerator(
            name = "id_department_sequence_generator",
            sequenceName = "id_department_sequence_generator",
            allocationSize = 1
    )
    private Integer id;
    private String name;
    @OneToMany(
            mappedBy = "department",
            targetEntity = Employee.class,
            fetch = FetchType.EAGER
    )
    private List<Employee> employees;
}
