package datatactics.javashoppingcart;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> products = new ArrayList<Product>();

    public Products () {
        this.initStoreItems();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void initStoreItems() {
        String [] productSkus = {
                "ipd",
                "mbp",
                "atv",
                "vga"
        };
        String [] productNames = {
                "Super iPad",
                "MacBook Pro",
                "Apple TV",
                "VGA adapter"
        };
        Double [] productPrice = {
                549.99d,
                1399.99d,
                109.50d,
                30.00d
        };
        Integer [] stock = {
                1000,
                1000,
                1000,
                1000
        };

        for (int i=0; i < productNames.length; i++) {
            this.products.add(new Product(productSkus[i], productNames[i], productPrice[i], stock[i]));
        }
    }
}
