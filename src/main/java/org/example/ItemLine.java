package com.example;

public class ItemLine{

    private final String itemName;
    private final int quantity;
    private final double unitPrice;

    public ItemLine(String itemName, int quantity, double unitPrice){
        if(itemName==null||itemName.isBlank()){
            throw new IllegalArgumentException("Item name is required");

        }
        if (quantity<=0){
            throw new IllegalArgumentException("Quantity must be at least 1");

        }
        if (unitPrice<0){
            throw new IllegalArgumentException("Price cannot be negative");

        }
        this.itemName=itemName.trim();
        this.quantity=quantity;
        this.unitPrice=unitPrice;
    }

    public String getItemName(){
        return itemName;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getUnitPrice(){
        return unitPrice;
    }

    public double getLineTotal(){
        return unitPrice*quantity;
    }
}