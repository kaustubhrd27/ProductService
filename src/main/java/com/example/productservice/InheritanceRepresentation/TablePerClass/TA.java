package com.example.productservice.InheritanceRepresentation.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_ta")
public class TA extends User {
    private int numOfSessions;
    private double avgRating;
}
