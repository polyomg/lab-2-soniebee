package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import poly.edu.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    // Danh sách sản phẩm chung (p3 – có sẵn A, B)
    private List<Product> items = new ArrayList<>(Arrays.asList(
            new Product("A", 1.0),
            new Product("B", 12.0)
    ));

    // ?1 → sản phẩm mặc định
    @ModelAttribute("p1")
    public Product defaultProduct() {
        return new Product("iPhone 30", 5000.0);
    }

    // ?3 → danh sách sản phẩm
    @ModelAttribute("items")
    public List<Product> getItems() {
        return items;
    }

    @GetMapping("/product/form")
    public String form(Model model) {
        // p2 để bind với form
        model.addAttribute("p2", new Product());
        return "product/form"; // nằm ở templates/product/form.html
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("p2") Product p, Model model) {
        // thêm sản phẩm vào danh sách chung (p3)
        items.add(p);

        // hiển thị lại sản phẩm vừa nhập (p2)
        model.addAttribute("p2", p);

        return "product/form";
    }
}
