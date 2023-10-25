package hh.bookstore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = categoryRepository.findByName("Comedy");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Comedy");
    }

    @Test
    public void createNewCategory() {
        Category category = new Category("Fantasy");
        categoryRepository.save(category);
        assertThat(category.getCategoryId()).isNotNull();
    }

    @Test
    public void deleteCategory() {
        List<Category> categories = categoryRepository.findByName("Non-Fiction");
        categoryRepository.delete(categories.get(0));

        List<Category> updatedCategories = categoryRepository.findByName("Non-Fiction");
        assertThat(updatedCategories).hasSize(0);
    }
}
