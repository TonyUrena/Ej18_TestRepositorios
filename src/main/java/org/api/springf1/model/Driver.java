package org.api.springf1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driverid")
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String forename;

    @Column(nullable = false)
    private String surname;

    private LocalDate dob;

    private String nationality;

    private String url;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "constructorid")
    private Constructor constructor;
}
