package com.example.AppPfe.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table
public class suivi_doc_1ereAge  extends  suivi_document{
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="libelleDirection",referencedColumnName = "libelleDirection")
    private Direction_Regionale libelleDirection;
   //  @JoinColumn(name = "libelleDirection",referencedColumnName = " libelleDirection")}

}

