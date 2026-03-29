package tacos.controller;

import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import java.util.stream.StreamSupport;


import tacos.taco.Taco;
import tacos.taco.TacoOrder;
import tacos.taco.Ingredient;
import tacos.taco.Ingredient.Type;
import tacos.data.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private static final Logger log=LoggerFactory.getLogger(DesignTacoController.class);
    private final IngredientRepository ingredientRepo;

    public DesignTacoController(IngredientRepository ingredientRepo){
        this.ingredientRepo=ingredientRepo;
    }

    @ModelAttribute
    public void addIngredienttoModel(Model model){
        List<Ingredient> ingredients=StreamSupport.stream(ingredientRepo.findAll().spliterator(), false).collect(Collectors.toList());
        Type[] types=Ingredient.Type.values();
        for(Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    public Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(a->a.getType().equals(type)).collect(Collectors.toList());
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute(name="tacoOrder")
    public TacoOrder tacoOrder(){
        return new TacoOrder();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if(errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing Taco: {}", taco);

        return "redirect:/orders/current";
    }
    
}
