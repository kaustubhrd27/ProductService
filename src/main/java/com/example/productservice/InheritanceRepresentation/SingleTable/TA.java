package com.example.productservice.InheritanceRepresentation.SingleTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TA extends User {
    private int numOfSessions;
    private double avgRating;
}
