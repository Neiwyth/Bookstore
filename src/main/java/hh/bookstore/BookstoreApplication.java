package hh.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner Demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {

			log.info("Save some categories");
			categoryRepository.save(new Category("Comedy"));
			categoryRepository.save(new Category("Non-Fiction"));
			categoryRepository.save(new Category("Sci-fi"));

			log.info("Save few books");
			bookRepository.save(new Book("Puffer Fish as Pets", "Elliot Lang", 2013, "978-1909151284", 18.99));
			bookRepository.save(new Book("365 Days of Dad Jokes", "Jim Chumley", 2023, "978-1800076938", 7.38));
			bookRepository.save(new Book("The Commuter Pig Keeper", "Michaela Giles", 2016, "978-1910455531", 28.78));
			bookRepository.save(new Book("How to Read Book and Why", "Harold Bloom", 2001, "978-1841150390", 10.00));

			log.info("Fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			log.info("Fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}