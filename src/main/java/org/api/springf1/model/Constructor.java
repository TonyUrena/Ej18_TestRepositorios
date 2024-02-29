package org.api.springf1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "constructors")
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constructorid")
    private Long id;

    @Column(name = "constructorref", unique = true, nullable = false)
    private String ref;

    @Column(nullable = false)
    private String name;

    private String nationality;

    private String url;

    @OneToMany(mappedBy = "constructor")
    private List<Driver> drivers;

}
