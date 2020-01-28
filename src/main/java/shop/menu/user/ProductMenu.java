package shop.menu.user;

import shop.entity.Product;
import shop.menu.Menu;
import shop.service.ProductService;
import shop.service.impl.ProductServiceImpl;

import java.util.*;

public class ProductMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = new ArrayList<>();
    private ProductService productService = new ProductServiceImpl();
    //privet OrderService orderService = new OrderService();

    @Override
    public void addOptions() {
        options.add("1. Show products");
        options.add("2. Search for specific product");
        options.add("3. Add specific product to order");
        options.add("4. Order checkout");
        options.add("0. Go back");
    }

    @Override
    public void show() {
        addOptions();
        showOptions(options);

        try {
            while (true) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        for (Product product : productService.getAllProducts()) {
                            System.out.println(product);
                        }
                        if (productService.getAllProducts().isEmpty()) {
                            System.out.println("Products list is empty");
                        }
                        break;
                    case 2:
                        try {
                            System.out.println(productService.getProductByName(scanner.nextLine()));
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        //orderService.addSpecificProduct(productId);
                        break;
                    case 4:
                        //orderService.checkout(orderId);
                        break;
                    case 0:
                        close();
                        break;
                    default:
                        showOptions(options);
                        break;
                }
            }
        } catch (InputMismatchException i) {
            System.out.println("Please choose the number from the menu");
            new ProductMenu().show();
        }
    }

    @Override
    public void close() {
        new UserMenu().show();
    }
}