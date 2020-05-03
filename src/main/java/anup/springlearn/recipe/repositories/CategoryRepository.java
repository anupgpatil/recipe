package anup.springlearn.recipe.repositories;

import anup.springlearn.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
