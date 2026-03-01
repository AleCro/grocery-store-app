package com.example;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class App{
    private static final double TAX_RATE=0.06;
    public static void main(String[]args){

        System.out.println("Welcome to our store!");

        Map<String,Double>storeItems=buildStoreItems();

        Cart cart=new Cart();

        printMenu(storeItems);
        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.print("\nPlease enter the Item Name you want to purchase (To Checkout please type: 'checkout'): ");
            String itemInput=scanner.nextLine().trim();

            if(itemInput.equalsIgnoreCase("checkout")){
                break;
            }
            String normalized=normalize(itemInput);

            if(!storeItems.containsKey(normalized)){
                System.out.println("Sorry, we don't have that item. Please type an item exactly as shown in the menu.");
                continue;
            }

            int qty=readPositiveInt(scanner, "Please enter the amount (quantity): ");
            double price=storeItems.get(normalized);

            cart.addLine(new ItemLine(normalized,qty,price));
            System.out.println("Added to cart: "+qty+" x "+normalized+" @ $" + money(price));
        }

        printReceipt(cart, TAX_RATE);
        scanner.close();
    }

    private static Map<String,Double> buildStoreItems(){
        Map<String,Double> items=new LinkedHashMap<>();

        items.put("Milk", 3.49);
        items.put("Eggs", 2.99);
        items.put("Bread", 2.49);
        items.put("Rice", 4.99);
        items.put("Chicken", 7.99);
        items.put("Ground Beef", 6.99);
        items.put("Apples", 1.29);
        items.put("Bananas", 0.79);
        items.put("Cheese", 4.59);
        items.put("Yogurt", 1.19);
        items.put("Cereal", 4.49);
        items.put("Pasta", 1.89);
        items.put("Tomatoes", 1.59);
        items.put("Onions", 1.09);
        items.put("Potatoes", 3.99);
        items.put("Orange Juice", 3.99);

        return items;
    }

    private static void printMenu(Map<String,Double>storeItems){
        System.out.println("\n----- STORE MENU -----");

        for (Map.Entry<String,Double>entry:storeItems.entrySet()){
            System.out.println(entry.getKey()+" - $"+money(entry.getValue()));

        }
        System.out.println("----------------------");
        System.out.println("Tip: Type the item name exactly as shown (example: Milk).");
    }

    private static int readPositiveInt(Scanner scanner, String prompt){
        while(true){
            System.out.print(prompt);

            String input=scanner.nextLine().trim();

            try{
                int value=Integer.parseInt(input);
                if(value<=0){
                    System.out.println("Please enter a number greater than 0.");
                    continue;
                }
                return value;

            }catch(NumberFormatException e){
                System.out.println("Invalid number. Try again.");

            }
        }
    }

    private static void printReceipt(Cart cart, double taxRate){

        System.out.println("\n=========== RECEIPT ===========");

        if(cart.getLines().isEmpty()){

            System.out.println("Your cart is empty.");
            System.out.println("===============================");

            return;
        }

        for(ItemLine line:cart.getLines()){
            System.out.println(line.getQuantity()+" x "+line.getItemName()
                    +" @ $"+money(line.getUnitPrice())
                    +" = $"+money(line.getLineTotal()));
        }

        double subtotal=cart.getSubtotal();
        double tax=cart.getTax(taxRate);
        double total=cart.getTotal(taxRate);

        System.out.println("-------------------------------");
        System.out.println("Subtotal: $"+money(subtotal));
        System.out.println("Tax ("+(int)(taxRate*100)+"%): $"+money(tax));
        System.out.println("Total:    $"+money(total));
        System.out.println("===============================");
        System.out.println("Thank you for shopping!");
    }

    private static String normalize(String s){

        // Standardize user input to match our menu keys.
        // Example: "milk" -> "Milk"
        s=s.trim().toLowerCase(Locale.ROOT);
        if (s.isEmpty())return s;

        String[] parts=s.split("\\s+");

        StringBuilder out=new StringBuilder();

        for(int i=0;i<parts.length;i++){

            String p=parts[i];

            if(p.isEmpty())continue;
            out.append(Character.toUpperCase(p.charAt(0)));

            if(p.length()>1)out.append(p.substring(1));

            if(i<parts.length-1) out.append(" ");
        }

        return out.toString();
    }

    private static String money(double value){
        return String.format(Locale.US,"%.2f",value);
    }
}