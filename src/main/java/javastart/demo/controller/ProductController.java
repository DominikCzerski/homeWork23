package javastart.demo.controller;

import javastart.demo.model.Product;
import javastart.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public String getAll() {
        List<Product> productsList = productRepository.listAll();
        return productsList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br/>")) + "<br/> " + "Products summed price is = " + productRepository.sumPrice(productsList);

    }

    @GetMapping(value = "/products", params = "category")
    @ResponseBody
    public String getGroceries(@RequestParam String category) {
        List<Product> productsList = productRepository.getCategory(category);
        return productsList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br/>")) + "<br/> " + "Products summed price is = " + productRepository.sumPrice(productsList);
    }

    @GetMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam double price,
                      @RequestParam String category) {
        Product product = new Product(name, price, category);
        productRepository.saveProduct(product);
        return "redirect:/success.html";
    }

    @GetMapping("/add-product-form")
    public String addProductForm() {
        return "redirect:/addProductForm.html";
    }


}
