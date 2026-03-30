package tacos.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tacos.taco.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import tacos.data.IngredientRepository;
import tacos.data.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tacos.taco.Ingredient;
import tacos.taco.Ingredient.Type;

@Configuration
public class DataConfig {
    
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo){
        return args->{
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }
     
    /*creating two custom users to log in to the application */
    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
    //     List<UserDetails> userList=new ArrayList<>();
    //     userList.add(new User("karti", passwordEncoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    //     userList.add(new User("kirti", passwordEncoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("USER_ROLE"))));
    //     return new InMemoryUserDetailsManager(userList);
    // }

    
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username -> {
            User user=userRepo.findByUsername(username);
            if(user != null) return user;
            throw new UsernameNotFoundException("User '"+username+"' not found");
        };
    }
}
