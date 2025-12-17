package com.inventory;
import java.util.*;


public class Main {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
ProductDAO dao = new ProductDAO();


while (true) {
System.out.println("1.Add Product\n2.View Products\n3.Delete Product\n4.Exit");
int ch = sc.nextInt();


switch (ch) {
case 1:
System.out.print("Name: ");
String name = sc.next();
System.out.print("Quantity: ");
int qty = sc.nextInt();
System.out.print("Price: ");
double price = sc.nextDouble();
dao.addProduct(new Product(name, qty, price));
break;
case 2:
dao.viewProducts();
break;
case 3:
System.out.print("Enter ID: ");
int id = sc.nextInt();
dao.deleteProduct(id);
break;
case 4:
System.exit(0);
}
}
}
}
