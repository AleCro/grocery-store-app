package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart{
    private final List<ItemLine> lines=new ArrayList<>();

    public void addLine(ItemLine line){
        if(line==null){
            throw new IllegalArgumentException("Line is required");
        }
        lines.add(line);

    }

    public List<ItemLine>getLines(){
        return Collections.unmodifiableList(lines);
    }

    public double getSubtotal(){
        double sum=0.0;

        for (ItemLine line:lines){
            sum+=line.getLineTotal();

        }
        return sum;
    }


    public double getTax(double taxRate){
        if(taxRate<0)throw new IllegalArgumentException("Tax rate cannot be negative");
        return getSubtotal()*taxRate;
    }

    public double getTotal(double taxRate){
        return getSubtotal()+getTax(taxRate);
    }
}