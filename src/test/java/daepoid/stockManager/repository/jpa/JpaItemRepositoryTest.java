package daepoid.stockManager.repository.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaItemRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaItemRepository itemRepository;

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findByItemType() {
    }

    @Test
    void findByPackageCount() {
    }

    @Test
    void findByQuantity() {
    }

    @Test
    void findByItemSearch() {
    }

    @Test
    void changeName() {
    }

    @Test
    void changeItemType() {
    }

    @Test
    void changePrice() {
    }

    @Test
    void changePackageCount() {
    }

    @Test
    void changeQuantity() {
    }

    @Test
    void changeUnitType() {
    }

    @Test
    void removeByItem() {
    }

    @Test
    void removeById() {
    }
}