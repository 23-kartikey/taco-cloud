package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.taco.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{

}
