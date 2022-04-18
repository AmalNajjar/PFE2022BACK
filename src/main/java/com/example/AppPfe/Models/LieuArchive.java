package com.example.AppPfe.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class LieuArchive implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private int code;
    private String Lieu;



}
