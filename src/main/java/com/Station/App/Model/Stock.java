package com.Station.App.Model;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock {
    private int id;
    private Timestamp date;
    private String name;
    private double quantite;
    private int idProductTemplate;
}
