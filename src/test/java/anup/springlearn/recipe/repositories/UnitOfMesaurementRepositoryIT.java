package anup.springlearn.recipe.repositories;

import anup.springlearn.recipe.model.UnitOfMeasure;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMesaurementRepositoryIT {

    @Autowired
    UnitOfMesaurementRepository unitOfMesaurementRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMesaurementRepository.findByDescription("TeaSpoon");

        Assert.assertEquals("TeaSpoon",unitOfMeasureOptional.get().getDescription());

    }
}
