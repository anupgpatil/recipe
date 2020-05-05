package anup.springlearn.recipe.controllers;

import anup.springlearn.recipe.model.Category;
import anup.springlearn.recipe.model.UnitOfMeasure;
import anup.springlearn.recipe.repositories.CategoryRepository;
import anup.springlearn.recipe.repositories.UnitOfMesaurementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMesaurementRepository unitOfMesaurementRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMesaurementRepository unitOfMesaurementRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMesaurementRepository = unitOfMesaurementRepository;
    }

    @RequestMapping({"/","","/index"})
    public String getIndexPage(){
        Optional<Category> category =  categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> uom = unitOfMesaurementRepository.findByDescription("TeaSpoon");

        System.out.println(category.get().getId());
        System.out.println(uom.get().getId());
        return "index";
    }

}
