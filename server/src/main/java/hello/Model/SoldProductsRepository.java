package hello.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SoldProductsRepository extends CrudRepository<SoldProducts, Long> {
    //@Query("SELECT NEW hello.Model.CountSoldProduct(sum(S.count), S.id, S.price, S.seller, S.product) FROM SoldProducts S INNER JOIN S.seller G where G.shop.name=:Name group by S.product")
    //Iterable<CountSoldProduct> findSoldProducts(@Param("Name") String Name);

    @Query("SELECT S FROM SoldProducts S INNER JOIN S.seller G where G.shop.name=:Name")
    Iterable<SoldProducts> findByName(@Param("Name") String Name);
}

