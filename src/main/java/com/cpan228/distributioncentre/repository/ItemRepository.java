package com.cpan228.distributioncentre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cpan228.distributioncentre.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

  Optional<Item> findById(Long itemId);

  List<Item> findByBrandAndName(String brand, String name);

}
