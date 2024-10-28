package be.neoo.dto;

import be.neoo.entities.WareHouseProduct;

import java.util.List;

public class ProductDto {

    private int id;
    private String name;
    private int totalQuantity;
    private long unitCostPrice;
    private Boolean active;
    private int minimumQuantity;
    private int quantityBox;
    private int quantityPackage;
    private String code;
    private CategoryDto categoryDto;
    private BrandDto brandDto;
//    private List<WareHouseProduct> wareHouseProducts;

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

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public BrandDto getBrandDto() {
        return brandDto;
    }

    public void setBrandDto(BrandDto brandDto) {
        this.brandDto = brandDto;
    }

//    public List<WareHouseProduct> getWareHouseProducts() {
//        return wareHouseProducts;
//    }
//
//    public void setWareHouseProducts(List<WareHouseProduct> wareHouseProducts) {
//        this.wareHouseProducts = wareHouseProducts;
//    }
}
