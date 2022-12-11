package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByPersonId(int id);

    @Modifying
    @Query(value = "delete from product_cart where product_id=?1 and person_id=?2", nativeQuery = true)
    void deleteCartById(int id, int person_id);

}
