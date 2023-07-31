package com.example.idvlabsTask.products.entity;

import com.example.idvlabsTask.common.entity.BaseEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@NoArgsConstructor
@Setter
public class Product extends BaseEntity {

    private String productType;
    private Integer availableSlots;
    private String productName;

    public Product(String roomNumber, String productType, Integer availableSlots) {
        this.availableSlots = availableSlots;
        this.productName = roomNumber;
        this.productType = productType;
    }

    public Product(Integer availableSlots, String roomNumber) {
        this.availableSlots = availableSlots;
        this.productName = roomNumber;
    }

    public void update(Product updatedMenu) {
        this.availableSlots = updatedMenu.availableSlots;
        this.productName = updatedMenu.productName;
    }

}
