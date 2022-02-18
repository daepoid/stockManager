package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.CustomerSearch;
import daepoid.stockManager.domain.order.ManagerOrderSearch;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final JpaCustomerRepository customerRepository;

    //==생성 로직==//
    @Transactional
    public Long saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //==조회 로직==//
    public Customer findCustomer(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByLoginId(String loginId) {
        return customerRepository.findByLoginId(loginId);
    }

    public Customer findCustomerByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }

    public Customer findCustomerByTableNumber(String tableNumber) {
        return customerRepository.findByTableNumber(tableNumber);
    }

    public List<Customer> findCustomersByCustomerSearch(CustomerSearch customerSearch) {
        return customerRepository.findCustomerByCustomerSearch(customerSearch);
    }

    //==수정 로직==//
    @Transactional
    public void changeUserName(Long customerId, String userName) {
        customerRepository.changeUserName(customerId, userName);
    }

    @Transactional
    public void changeTableNumber(Long customerId, String tableNumber) {
        customerRepository.changeTableNumber(customerId, tableNumber);
    }

    @Transactional
    public void changeOrders(Long customerId, List<Order> orders) {
        customerRepository.changeOrders(customerId, orders);
    }

    @Transactional
    public void addOrder(Long customerId, Order order) {
        customerRepository.addOrder(customerId, order);
    }

    @Transactional
    public void changeCart(Long customerId, Map<Long, Integer> numberOfMenus) {
        customerRepository.changeCart(customerId, numberOfMenus);
    }

    @Transactional
    public void addCart(Long customerId, Long menuId, int count) {
        customerRepository.addCart(customerId, menuId, count);
    }

    //==삭제 로직==//
    @Transactional
    public void removeCustomer(Long customerId) {
        customerRepository.removeCustomer(customerId);
    }

}
