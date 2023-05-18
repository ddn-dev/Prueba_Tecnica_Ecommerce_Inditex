package com.ddn.inditest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SIZE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size implements Serializable {

    @Id
    private Integer id;
    @ManyToOne
    @JsonIgnore
    private Product product;
    private Boolean backSoon;
    private Boolean special;
}
