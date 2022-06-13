package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.domain.search.CustomerSearch;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final JpaCustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordService passwordService;

    //==생성 로직==//
    @Transactional
    public Long saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //==조회 로직==//
    public Optional<Customer> findCustomer(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> findCustomers(int maxResult) {
        return customerRepository.findAll(maxResult);
    }

    public List<Customer> findCustomers(int firstResult, int maxResult) {
        return customerRepository.findAll(firstResult, maxResult);
    }

    public Optional<Customer> findCustomerByLoginId(String loginId) {
        return customerRepository.findByLoginId(loginId);
    }

    public Optional<Customer> findCustomerByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }

    public Optional<Customer> findCustomerByTableNumber(String tableNumber) {
        return customerRepository.findByTableNumber(tableNumber);
    }

    public List<Customer> findCustomersByCustomerSearch(CustomerSearch customerSearch) {
        return customerRepository.findCustomerByCustomerSearch(customerSearch);
    }

    //==수정 로직==//
    @Transactional
    public boolean changePassword(Long customerId, String password) {
        if(passwordService.createPasswordValid(password)) {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if(customer.isPresent()) {
                customer.get().changePassword(passwordEncoder.encode(password));
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean changeUserName(Long customerId, String userName) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            customer.get().changeUserName(userName);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeGradeType(Long customerId, GradeType gradeType) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            customer.get().changeGradeType(gradeType);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeTableNumber(Long customerId, String tableNumber) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            customer.get().changeTableNumber(tableNumber);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeExpirationTime(Long customerId, LocalDateTime expirationTime) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            customer.get().changeExpirationTime(expirationTime);
            return true;
        }
        return false;
    }

    //==삭제 로직==//
    @Transactional
    public boolean removeCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            customerRepository.remove(customerId);
            return true;
        }
        return false;
    }
}
