package com.cpan228.distributioncentre.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpan228.distributioncentre.model.DistributionCentre;
import com.cpan228.distributioncentre.model.Item;
import com.cpan228.distributioncentre.repository.DistributionCentreRepository;
import com.cpan228.distributioncentre.repository.ItemRepository;

@RestController
@RequestMapping("/api/distribution")
public class DistributionCentreController {

    private final DistributionCentreRepository dcRepository;
    private final ItemRepository itemRepository;

    public DistributionCentreController(DistributionCentreRepository dcRepository, ItemRepository itemRepository) {
        this.dcRepository = dcRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<DistributionCentre> getAllCentres() {

        return (List<DistributionCentre>) dcRepository.findAll();
    }

    @GetMapping("/{id}/items")
    public List<Item> getItemsForCentre(@PathVariable int id) {

        var currentDistributionCentre = dcRepository.findById(id);
        var items = currentDistributionCentre.get().getItems();
        return items;
    }

    @PostMapping("/{id}/items")
    public Item addItemToCentre(@PathVariable int id, @RequestBody Item item) {

        var currentDistributionCentre = dcRepository.findById(id);
        item.setDistributionCentre(currentDistributionCentre.get());
        var savedItem = itemRepository.save(item);
        return savedItem;
    }
    
    @GetMapping("/items")
    public Iterable<Item> getAllItems() {
      return itemRepository.findAll();
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
      return itemRepository.save(item);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long itemId) {
      Item item = itemRepository.findById(itemId)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + itemId));
      itemRepository.delete(item);
      return ResponseEntity.ok().build();
    }

    @GetMapping("/items/{brand}/{name}")
    public List<Item> getItemsByBrandAndName(@PathVariable("brand") String brand, @PathVariable("name") String name) {
      return itemRepository.findByBrandAndName(brand, name);
    }

    @PostMapping("/admin/distribution-centre/request-item")
    public String requestItem(@ModelAttribute("requestForm") RequestEntity form, Model model) {

      return "success";
    }

}
