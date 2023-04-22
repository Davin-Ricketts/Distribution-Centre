package com.cpan228.distributioncentre.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistributionCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    @OneToMany(mappedBy = "distributionCentre")
    private List<Item> items;

    public Item getItemByBrandAndName(Item.Brand brand, String name) {
      for (Item item : items) {
          if (item.getBrand() == brand && item.getName().equalsIgnoreCase(name)) {
              return item;
          }
      }
      return null;
  }


}