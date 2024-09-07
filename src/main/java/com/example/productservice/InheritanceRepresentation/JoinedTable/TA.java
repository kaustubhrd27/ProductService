package com.example.productservice.InheritanceRepresentation.JoinedTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_ta")
public class TA extends User {
    private int numOfSessions;
    private double avgRating;
}
