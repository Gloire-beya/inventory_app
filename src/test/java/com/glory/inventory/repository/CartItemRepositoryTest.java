package com.glory.inventory.repository;

import com.glory.inventory.entity.CartItem;
import com.glory.inventory.entity.Product;
import com.glory.inventory.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Commit
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addItemFromDatabase() {
        Product product = entityManager.find(Product.class, 7);
        User user = entityManager.find(User.class, 1);

        if (product != null && user != null) {
            CartItem item = new CartItem(4, product, user);
            cartItemRepository.save(item);
        } else {
            log.info("Item could not be saved");
            log.info("User = " + user + " Product = " + product);
        }
    }

    @Test
    public void listAllItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        cartItems.stream()
                .forEachOrdered(cartItem -> log.info("Item "
                        + cartItem.getId() + ": "
                        + cartItem.getQuantity() + " "
                        + cartItem.getProduct().getName() + " "
                        + cartItem.getUser().getEmail()));

    }

    @Test
    public void updatingItem(){
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(1);
        if (cartItemOptional.isPresent()){
            cartItemOptional.get().setQuantity(5);
            cartItemRepository.save(cartItemOptional.get());
        }
    }

    @Test
    public void deleteItem(){
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(2);
        if (cartItemOptional.isPresent())cartItemRepository.deleteById(2);
    }

}