package com.example.AppPfe.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole name;
    public Role(ERole name) {
        this.name = name;
    }

}
