package tacos.converter;

import java.lang.Override;
import org.springframework.stereotype.Component;

import tacos.taco.Ingredient;

import org.springframework.core.convert.converter.Converter;


import tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{
    
    private final IngredientRepository ingredientRepo;

    public IngredientByIdConverter(IngredientRepository ingredientRepo){
        this.ingredientRepo=ingredientRepo;
    }

    @Override
    public Ingredient convert(String id){
        return ingredientRepo.findById(id).orElse(null);
    }

}
