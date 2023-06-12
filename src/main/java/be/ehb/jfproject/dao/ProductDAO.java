package be.ehb.jfproject.dao;

import be.ehb.jfproject.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository <Product, Integer> {
    //es integer porque el id es integer.
}
