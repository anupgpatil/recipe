package anup.springlearn.recipe.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


class CategoryTest {

    Category  category;

    @BeforeEach
    private void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long idVal = 3L;

        category.setId(4L);
        Assertions.assertEquals(category.getId(),idVal);
        Assert.isTrue(category.getId().equals(idVal),"Id is Matching");
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}
