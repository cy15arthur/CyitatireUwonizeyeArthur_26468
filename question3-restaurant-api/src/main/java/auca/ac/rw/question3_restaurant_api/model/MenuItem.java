package auca.ac.rw.question3_restaurant_api.model;

public class MenuItem {
    private Long itemId;
    private String itemName;
    private String description;
    private Double price;
    private String category;
    private Boolean isAvailable;

    public MenuItem() {
    }

    public MenuItem(Long itemId, String itemName, String description, Double price, String category, Boolean isAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public Long getItemId() {
      return itemId;
    }

    public void setItemId(Long itemId) {
      this.itemId = itemId;
    }

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
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

    public Boolean getIsAvailable() {
      return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
      this.isAvailable = isAvailable;
    }
    
}
