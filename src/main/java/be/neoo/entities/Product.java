package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries(value = {
        @NamedQuery(name = "product.findAll", query = "select p from Product p ")
})

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;

    @Column(name = "unit_cost_price", nullable = false)
    private long unitCostPrice;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "minimum_quantity")
    private int minimumQuantity;

    @Column(name = "quantity_box", nullable = false)
    private int quantityBox;

    @Column(name = "quantity_package", nullable = false)
    private int quantityPackage;

    @Column(name = "code", nullable = false, length = 60)
    private String code;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id", nullable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "id", nullable = false, updatable = false)
    private Brand brand;

//    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<WareHouseProduct> wareHouseProducts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public long getUnitCostPrice() {
        return unitCostPrice;
    }

    public void setUnitCostPrice(long unitCostPrice) {
        this.unitCostPrice = unitCostPrice;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public int getQuantityBox() {
        return quantityBox;
    }

    public void setQuantityBox(int quantityBox) {
        this.quantityBox = quantityBox;
    }

    public int getQuantityPackage() {
        return quantityPackage;
    }

    public void setQuantityPackage(int quantityPackage) {
        this.quantityPackage = quantityPackage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public List<WareHouseProduct> getWareHouseProducts() {
//        return wareHouseProducts;
//    }
//
//    public void setWareHouseProducts(List<WareHouseProduct> wareHouseProducts) {
//        this.wareHouseProducts = wareHouseProducts;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return id == product.id && totalQuantity == product.totalQuantity && unitCostPrice == product.unitCostPrice
                && minimumQuantity == product.minimumQuantity && quantityBox == product.quantityBox
                && quantityPackage == product.quantityPackage && Objects.equals(name, product.name)
                && Objects.equals(active, product.active) && Objects.equals(code, product.code)
                && Objects.equals(category, product.category) && Objects.equals(brand,
                product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalQuantity, unitCostPrice, active, minimumQuantity, quantityBox,
                quantityPackage,
                code, category, brand);
    }
}
