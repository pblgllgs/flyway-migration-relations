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
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_mission_sequence_generator"
    )
    @SequenceGenerator(
            name = "id_mission_sequence_generator",
            sequenceName = "id_mission_sequence_generator",
            allocationSize = 1
    )
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "duration", nullable = false)
    private int duration;

    @ManyToMany(
            mappedBy = "missions",
            fetch = FetchType.EAGER
    )
    private List<Employee> employees;
}
