package be.neoo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderProductDto {
    @JsonProperty("_id")
    private int id;
    @JsonProperty("_qte")
    private int qte;
    @JsonProperty("_unitePrice")
    private int unitePrice;
    @JsonProperty("_deliver")
    private Boolean deliver;
    @JsonProperty("_discount")
    private long discount;
    //    @JsonProperty("_order")
    private OrderDto orderDto;
    @JsonProperty("_product")
    private ProductDto productDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(int unitePrice) {
        this.unitePrice = unitePrice;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver(Boolean deliver) {
        this.deliver = deliver;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
