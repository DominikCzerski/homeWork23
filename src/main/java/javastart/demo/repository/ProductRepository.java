package javastart.demo.repository;

import javastart.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Cup", 12.12, "Home"));
        products.add(new Product("Table", 122, "Home"));
        products.add(new Product("TV", 12456, "Home"));
        products.add(new Product("Apple", 14, "Groceries"));
        products.add(new Product("Strawberry", 10, "Groceries"));
        products.add(new Product("Sausages", 25, "Groceries"));
        products.add(new Product("Le baquette", 25, "Others"));
    }

    public List<Product> listAll() {
        return new ArrayList<>(products);
    }

    public double sumPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public List<Product> getCategory(String category) {
        return products.stream()
                .filter(product -> category.equals(product.getCategory()))
                .collect(Collectors.toList());
    }

    public void saveProduct(Product product) {
        products.add(product);
    }

}
