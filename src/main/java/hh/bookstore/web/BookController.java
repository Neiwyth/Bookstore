package hh.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // RESTful service to get all books
    @GetMapping("/books")
    public @ResponseBody List<Book> booklistRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // RESTful service to get a book by ID
    @GetMapping("books/{bookId}")
    public @ResponseBody Optional<Book> findBookById(@PathVariable("bookId") Long bookId) {
        return bookRepository.findById(bookId);
    }

    // list all books
    @GetMapping("/booklist")
    public String showAllBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    // add new book
    @GetMapping("/addbook")
    public String addBook(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    // save new book or book edits
    @PostMapping("/savebook")
    public String saveBook(Book book) {

        bookRepository.save(book);
        return "redirect:/booklist";
    }

    // delete book
    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {

        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    // edit book
    @GetMapping("/editbook/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {

        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }
}
