package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest{

    @Test
    void calculatesSubtotalTaxAndTotal(){
        Cart cart=new Cart();

        //2 Milk @3.49 = 6.98
        cart.addLine(new ItemLine("Milk", 2, 3.49));

        //5 Apples @1.29 = 6.45
        cart.addLine(new ItemLine("Apples", 5, 1.29));

        double expectedSubtotal=6.98+6.45;// 13.43
        assertEquals(expectedSubtotal,cart.getSubtotal(), 0.0001);

        double taxRate=0.06;
        double expectedTax=expectedSubtotal*taxRate;// 0.8058
        assertEquals(expectedTax, cart.getTax(taxRate), 0.0001);

        double expectedTotal=expectedSubtotal+expectedTax;// 14.2358
        assertEquals(expectedTotal,cart.getTotal(taxRate), 0.0001);
    }

    @Test
    void rejectsInvalidItemLine(){
        assertThrows(IllegalArgumentException.class,()->new ItemLine("", 1, 1.00));
        assertThrows(IllegalArgumentException.class,()->new ItemLine("Milk", 0, 1.00));
        assertThrows(IllegalArgumentException.class,()->new ItemLine("Milk", 1, -0.01));
    }

    @Test
    void rejectsNegativeTaxRate(){
        Cart cart=new Cart();
        cart.addLine(new ItemLine("Bread", 1, 2.49));
        assertThrows(IllegalArgumentException.class,()-> cart.getTax(-0.01));
        assertThrows(IllegalArgumentException.class,()-> cart.getTotal(-0.01));
    }

    @Test
    void startsEmpty(){
        Cart cart=new Cart();
        assertTrue(cart.getLines().isEmpty());
        assertEquals(0.0, cart.getSubtotal(), 0.0001);
    }
}