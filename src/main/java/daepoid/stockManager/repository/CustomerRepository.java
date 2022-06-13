package daepoid.stockManager.repository;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.domain.search.CustomerSearch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    //==생성 로직==//
    Long save(Customer user);

    //==조회 로직==//
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    List<Customer> findAll(int maxResult);
    List<Customer> findAll(int firstResult, int maxResult);
    Optional<Customer> findByLoginId(String loginId);
    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByTableNumber(String tableNumber);
    List<Customer> findCustomerByCustomerSearch(CustomerSearch customerSearch);

    //==수정 로직==//
    void changePassword(Long userId, String password);
    void changeUserName(Long userId, String userName);
    void changeGradeType(Long userId, GradeType gradeType);
    void changeTableNumber(Long customerId, String tableNumber);
    void changeExpirationTime(Long customerId, LocalDateTime expirationTime);

    //==삭제 로직==//
    void remove(Long userId);
}
