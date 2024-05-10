package com.Station.App.Service;

import com.Station.App.DAO.ProductTemplateDAO;
import com.Station.App.DAO.StockDAO;
import com.Station.App.Model.ProductTemplate;
import com.Station.App.Model.Stock;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;

@Service
public class Approvisoinnement {
    public String DoApprovisoinnement (Stock stock) throws SQLException{
        ProductTemplateDAO productTemplateDAO = new ProductTemplateDAO();
        StockDAO stockDAO = new StockDAO();

        ProductTemplate productTemplate = productTemplateDAO.selectById(stock.getIdProductTemplate());
        String essence = "essence";
        String gazoil = "gazoil";
        String petrole = "petrole";
      if(productTemplate.getName().equals(essence)){
          double value = stock.getQuantite();
          productTemplate.setQuantite(productTemplate.getQuantite()+value);
          productTemplateDAO.save(productTemplate);
          stockDAO.save(stock);
          return "essence";
      } else if (productTemplate.getName().equals(gazoil)) {
          productTemplate.setQuantite(productTemplate.getQuantite()+stock.getQuantite());
          productTemplateDAO.save(productTemplate);
          stockDAO.save(stock);
          return "gazoil";

      } else if (productTemplate.getName().equals(petrole)) {
          productTemplate.setQuantite(productTemplate.getQuantite()+stock.getQuantite());
          productTemplateDAO.save(productTemplate);
          stockDAO.save(stock);
          return "petrole";
      }else {
          return "erreur";
      }
    }

    public static void main(String[] args) throws SQLException {
        Timestamp timestamp = Timestamp.valueOf("2024-12-12 00:00:00");
        Stock stock = new Stock(2,timestamp,"gazoil",5,2);
        Approvisoinnement approvisoinnement = new Approvisoinnement();
        System.out.println(approvisoinnement.DoApprovisoinnement(stock));
    }
}