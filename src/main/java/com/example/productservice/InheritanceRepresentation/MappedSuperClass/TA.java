package com.example.productservice.InheritanceRepresentation.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_ta")
public class TA extends User{
    private int numOfSessions;
    private double avgRating;
}
