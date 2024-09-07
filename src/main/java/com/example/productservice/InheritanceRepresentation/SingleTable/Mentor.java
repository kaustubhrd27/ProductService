package com.example.productservice.InheritanceRepresentation.SingleTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mentor extends User {
    private double avgRating;

}
