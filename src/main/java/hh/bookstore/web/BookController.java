package hh.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/booklist")
    public String showAllBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {

        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/savebook")
    public String saveBook(Book book) {

        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {

        bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @GetMapping("/editbook/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {

        model.addAttribute("book", bookRepository.findById(bookId));
        return "editbook";
    }
}
