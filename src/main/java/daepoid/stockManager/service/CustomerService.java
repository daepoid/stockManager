package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer findByTableNumber(int tableNumber) {
        return customerRepository.findByTableNumber(tableNumber);
    }

    //==수정 로직==//
    @Transactional
    public void changeName(Long customerId, String name) {
        customerRepository.changeName(customerId, name);
    }

    @Transactional
    public void changeTableNumber(Long customerId, int tableNumber) {
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

    //==삭제 로직==//
    @Transactional
    public void removeCustomer(Customer customer) {
        customerRepository.removeCustomer(customer);
    }

}
