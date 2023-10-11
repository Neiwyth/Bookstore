package hh.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // show all categories
    @GetMapping("/categorylist")
    public String categoryList(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    // add new category
    @GetMapping("/addcategory")
    public String addCategory(Model model) {

        model.addAttribute("category", new Category());
        return "addcategory";
    }

    // save new category
    @PostMapping("/savecategory")
    public String saveCategory(Category category) {

        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

    // delete category
    @GetMapping("/deletecategory/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {

        categoryRepository.deleteById(categoryId);
        return "redirect:/categorylist";
    }

}
