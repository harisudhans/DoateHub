package com.example.DonateHub.entity;

import jakarta.persistence.*;

@Entity
public class DonatedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String itemCondition;
    private Integer quantity;
    private boolean distributed = false;

    @ManyToOne
    private Drive drive;

    @ManyToOne
    private Donor donor;

    @ManyToOne
    private Recipient recipient;

    public DonatedItem() {
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isDistributed() {
        return distributed;
    }

    public Drive getDrive() {
        return drive;
    }

    public Donor getDonor() {
        return donor;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}