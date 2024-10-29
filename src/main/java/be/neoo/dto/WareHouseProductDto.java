package be.neoo.dto;

import be.neoo.entities.Product;
import be.neoo.entities.Warehouse;

import java.util.Date;

public class WareHouseProductDto {

    private Warehouse warehouse;

    private Product product;

    private Date date;

    private Boolean deliver;

    private int quantity;

}
