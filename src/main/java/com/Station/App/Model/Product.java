package com.Station.App.Model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private int idProductTemplate;
    private int idStation;
    private double evaporation; //10/jour
}
