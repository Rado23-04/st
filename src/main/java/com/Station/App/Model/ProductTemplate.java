package com.Station.App.Model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductTemplate {
    private int id;
    private String name;
    private double quantite;
    private double prix;
}
