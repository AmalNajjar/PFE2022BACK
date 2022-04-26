package com.example.AppPfe.Models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table
@ToString
public class suivi_doc_3emeAge  {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
