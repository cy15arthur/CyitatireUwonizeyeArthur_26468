package auca.ac.rw.question4_ecommerce_api.model;

public class Product {
  private Long id;
  private String name;
    private String description;
    private Double price;
    private String category;
    private int stockQuantity;
    private String brand;

    
    public Product(Long id, String name, String description, Double price, String category, int stockQuantity,
        String brand) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.price = price;
      this.category = category;
      this.stockQuantity = stockQuantity;
      this.brand = brand;
    }


    public Product() {
    }


    public Long getId() {
      return id;
    }


    public void setId(Long id) {
      this.id = id;
    }


    public String getName() {
      return name;
    }


    public void setName(String name) {
      this.name = name;
    }


    public String getDescription() {
      return description;
    }


    public void setDescription(String description) {
      this.description = description;
    }


    public Double getPrice() {
      return price;
    }


    public void setPrice(Double price) {
      this.price = price;
    }


    public String getCategory() {
      return category;
    }


    public void setCategory(String category) {
      this.category = category;
    }


    public int getStockQuantity() {
      return stockQuantity;
    }


    public void setStockQuantity(int stockQuantity) {
      this.stockQuantity = stockQuantity;
    }


    public String getBrand() {
      return brand;
    }


    public void setBrand(String brand) {
      this.brand = brand;
    }


}
