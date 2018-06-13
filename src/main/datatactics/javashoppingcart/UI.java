package datatactics.javashoppingcart;

import java.util.List;
import java.util.Scanner;

public class UI {

    // Checkout checkout = new Checkout();
    Checkout checkout;
    private String ch = "";

    public UI (List pricingRules) {
        checkout = new Checkout(pricingRules);
        menu();
    }

    public void startScreen () {
        System.out.println("1. Display Store Products");
        System.out.println("2. Display Checkout");
        System.out.println("0. Exit");
    }

    public void showProductsMenu() {
        System.out.println("1. Add to Checkout");
        System.out.println("2. Remove From Checkout");
        System.out.println("0. Exit");
    }

    public void menu () {

        Integer chInt = 0;
        do {
            startScreen();
            getUserInput();

            try {
                chInt = Integer.parseInt(ch);
            }
            catch (Exception e) {

            }

            switch (chInt) {
                case 1:
                    System.out.println("Choose Add or Remove (type 1 or 2 return)\nThen type a product sku");
                    showProductsMenu();
                    showStoreProducts();
                    getUserInput();
                    innerChoice1();
                    break;
                case 2:
                    showCheckout();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (chInt != 0);
    }

    private void innerChoice1() {

        Integer chInt = 1;

        try {
            chInt = Integer.parseInt(ch);
            switch (chInt) {
                case 1:
                    addProductToCheckout(null);
                    showCheckout();
                    break;
                case 2:
                    removeProductFromCheckout();
                    break;
                default:

                    break;
            }
        }
        catch (Exception e) {
            addProductToCheckout(ch); // maybe the user typed a sku - assume she wants to add to checkout and try it
            showCheckout();
       }
    }

    private String getUserInput() throws NumberFormatException {
        Scanner in = new Scanner (System.in);
        ch = in.nextLine(); // Integer.parseInt(in.nextLine());
        return ch;
    }

    private void showStoreProducts() {
        List<Product> products = new Products().getProducts();
        for (Product prod: products) {
            System.out.println(
                prod.getSku() + " - " +
                    prod.getName() + " " +
                    prod.getPrice() + " " +
                    prod.getStock()
            );
        }
    }

    private void addProductToCheckout(String sku) {
        if (sku == null) {
            sku = getUserInput();
        }
        CheckoutItem checkoutItem = checkout.addProductToCheckoutBySku(sku, -1, true); // java doesn't have optional arguments so -1 = use default price
        if (checkoutItem == null) {
            System.out.println("Product not found");
        }
    }

    private void showCheckout() {
        System.out.println("Checkout");
        checkout.printCheckoutItems();
    }

    private void removeProductFromCheckout() {
        String sku = getUserInput();
        checkout.removeCheckoutItemBySku(sku);
    }
}
