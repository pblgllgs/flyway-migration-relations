package com.pblgllgs.flyway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "identifier_unique_constraint",
                        columnNames = "identifier"),
                @UniqueConstraint(
                        name = "email_unique_constraint",
                        columnNames = "email")
        })
public class Employee {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_employee_sequence_generator"
    )
    @SequenceGenerator(
            name = "id_employee_sequence_generator",
            sequenceName = "id_employee_sequence_generator",
            allocationSize = 1
    )
    private Integer id;
    @Column(name = "identifier", nullable = false)
    private String identifier;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    /*
    unidirectional one to one
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {
                    CascadeType.REMOVE,
                    CascadeType.PERSIST
            },
            targetEntity = Address.class
    )
    @JoinColumn(
            name = "address_id",
            foreignKey =
            @ForeignKey(
                    name = "address_id_foreign_key"
            )
    )
    private Address address;

    /*
    bidirectional many to one
     */
    @ManyToOne(
            targetEntity = Department.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "department_id",
            foreignKey =
            @ForeignKey(
                    name = "department_id_foreign_key"
            )
    )
    private Department department;

    /*
    bidirectional many to many
     */
    @ManyToMany
    @JoinTable(
            name = "employee_mission",
            joinColumns = @JoinColumn(
                    name = "employee_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "employee_id_foreign_key"
                    ),
                    table = "employees"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "mission_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                            name = "mission_id_foreign_key"
                    ),
                    table = "missions"
            )
    )
    private List<Mission> missions;
}
