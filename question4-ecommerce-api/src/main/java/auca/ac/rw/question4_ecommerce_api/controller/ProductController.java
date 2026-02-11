package auca.ac.rw.question4_ecommerce_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.question4_ecommerce_api.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  List <Product> products = new ArrayList<>();
  
  public ProductController() {
    products.add(new Product(1L, "Laptop", "HP Elitebook touch screen laptop", 999.99, "Electronics", 10, "HP"));
    products.add(new Product(2L, "Smartphone", "The new iphone model", 899.99, "Electronics", 20, "Apple"));
    products.add(new Product(3L, "Headphones", "Noise-cancelling headphones", 199.99, "Audio", 15, "Sony"));
    products.add(new Product(4L, "Smartwatch", "Fitness tracking smartwatch", 149.99, "Wearables", 25, "Apple"));
    products.add(new Product(5L, "Camera", "DSLR camera with 24MP sensor", 499.99, "Photography", 8, "Canon"));
    products.add(new Product(6L, "Gaming Console", "Next-gen gaming console(playstation 5)", 699.99, "Gaming", 12, "Sony"));
    products.add(new Product(7L, "Tablet", "10-inch tablet with high-resolution display", 299.00, "Electronics", 18, "Samsung"));
    products.add(new Product(8L, "Bluetooth Speaker", "Portable Bluetooth speaker with deep bass", 89.00, "Audio", 30, "JBL"));
    products.add(new Product(9L, "External Hard Drive", "2TB external hard drive for data storage", 129.49, "Storage", 20, "Western Digital"));
    products.add(new Product(10L, "Phone Charger", "Fast wireless phone charger with LED indicator", 12.00, "Accessories", 50, "Samsung"));
  }   

  @GetMapping
public List<Product> getAllProducts(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer limit) {

          if (page == null || limit == null) {
        return products;
    }
   int start = (page - 1) * limit;
        int end = Math.min(start + limit, products.size());
        
        List<Product> paginatedList = new ArrayList<>();
        if (start < products.size()) {
            for (int i = start; i < end; i++) {
                paginatedList.add(products.get(i));
    }
  }
    return paginatedList;
}

@GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getId().equals(productId)) return p;
        }
        return null;
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) results.add(p);
        }
        return results;
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand().equalsIgnoreCase(brand)) results.add(p);
        }
        return results;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        List<Product> results = new ArrayList<>();
        String lowKey = keyword.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(lowKey) || p.getDescription().toLowerCase().contains(lowKey)) {
                results.add(p);
            }
        }
        return results;
    }

    @GetMapping("/price-range")
    public List<Product> getByPrice(@RequestParam Double min, @RequestParam Double max) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) results.add(p);
        }
        return results;
    }

    @GetMapping("/in-stock")
    public List<Product> getInStock() {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getStockQuantity() > 0) results.add(p);
        }
        return results;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        products.add(p);
        return p;
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product info) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                p.setName(info.getName());
                p.setDescription(info.getDescription());
                p.setPrice(info.getPrice());
                p.setCategory(info.getCategory());
                p.setStockQuantity(info.getStockQuantity());
                p.setBrand(info.getBrand());
                return p;
            }
        }
        return null;
    }

    @PatchMapping("/{productId}/stock")
    public Product updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                p.setStockQuantity(quantity);
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                products.remove(p);
                return "Deleted";
            }
        }
        return "Not Found";
    }
}

