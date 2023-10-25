package hh.bookstore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.CategoryRepository;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void findByTitleShouldReturnAuthor() {
        List<Book> books = bookRepository.findByTitle("365 Days of Dad Jokes");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Jim Chumley");
    }

    @Test
    public void createNewBook() {
        Book book = new Book("Test title", "Author 1", 2023, "978-1234546",
                categoryRepository.findByName("Comedy").get(0), 13.37);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByTitle("365 Days of Dad Jokes");
        bookRepository.delete(books.get(0));

        List<Book> updatedBooks = bookRepository.findByTitle("365 Days of Dad Jokes");
        assertThat(updatedBooks).hasSize(0);
    }
}
