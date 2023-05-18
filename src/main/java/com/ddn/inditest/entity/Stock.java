package com.ddn.inditest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STOCK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

    @Id
    private Integer sizeId;
    private Integer quantity;
}
