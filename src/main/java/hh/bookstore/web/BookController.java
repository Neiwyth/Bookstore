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

    @GetMapping("/add")
    public String newBook(Model model) {

        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/save")
    public String saveNewBook(Book book) {

        bookRepository.save(book);
        return "redirect:booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {

        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

}
