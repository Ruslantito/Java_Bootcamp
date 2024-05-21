package edu.school21.models;

public class Product {
    private Long id;
    private String name;
    private Double price;

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long GetId() {
        return id;
    }
    public String GetName() {
        return name;
    }
    public Double GetPrice() {
        return price;
    }

    public void SetId(Long id) {
        this.id = id;
    }
    public void SetName(String name) {
        this.name = name;
    }
    public void SetPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.price == null) ? (other.price != null) : !this.price.equals(other.price)) {
            return false;
        }
        return true;
    } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Long.valueOf(this.id).intValue();
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.price != null ? this.price.hashCode() : 0);

        return hash;
    }
    
    @Override
    public String toString() {
        return "Product : {\nid=" + this.id + ",\nname=" + this.name + ",\nprice=" + this.price + "\n}";
    }

}
