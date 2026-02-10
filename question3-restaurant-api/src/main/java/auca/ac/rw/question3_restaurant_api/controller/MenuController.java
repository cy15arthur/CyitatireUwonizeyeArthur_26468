package auca.ac.rw.question3_restaurant_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.question3_restaurant_api.model.MenuItem;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
     List<MenuItem> menuItems = new ArrayList<>();

    public MenuController() {
        menuItems.add(new MenuItem(1L, "Spring Rolls", "Vegetable crispy rolls", 5.0, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Grilled Salmon", "Salmon with lemon butter", 22.0, "Main Course", true));
        menuItems.add(new MenuItem(3L, "Chocolate Cake", "Dark chocolate fudge", 8.0, "Dessert", true));
        menuItems.add(new MenuItem(4L, "Caesar Salad", "Classic romaine with croutons", 12.0, "Appetizer", true));
        menuItems.add(new MenuItem(5L, "Beef Steak", "Tenderloin with mash", 25.0, "Main Course", false));
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    @GetMapping("/{itemId}")
    public MenuItem getMenuItemById(@PathVariable Long itemId) {
        for (MenuItem item : menuItems) {
            if (item.getItemId().equals(itemId)) return item;
        }
        return null;
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) results.add(item);
        }
        return results;
    }

    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem newItem) {
        menuItems.add(newItem);
        return newItem;
    }

    @PutMapping("/{itemId}")
    public MenuItem updateMenuItem(@PathVariable Long itemId, @RequestBody MenuItem updatedItem) {
        for (MenuItem item : menuItems) {
            if (item.getItemId().equals(itemId)) {
                item.setItemName(updatedItem.getItemName());
                item.setDescription(updatedItem.getDescription());
                item.setPrice(updatedItem.getPrice());
                item.setCategory(updatedItem.getCategory());
                item.setIsAvailable(updatedItem.getIsAvailable());
                return item;
            }
        }
        return null;
    }

    @DeleteMapping("/{itemId}")
    public String deleteMenuItem(@PathVariable Long itemId) {
        MenuItem toRemove = null;
        for (MenuItem item : menuItems) {
            if (item.getItemId().equals(itemId)) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            menuItems.remove(toRemove);
            return "Item deleted successfully";
        }
        return "Item not found";
    }
}