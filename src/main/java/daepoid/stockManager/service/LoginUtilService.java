package daepoid.stockManager.service;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.users.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUtilService {

    private MemberService memberService;
    private CustomerService customerService;

    public boolean createPasswordValid(String password) {
        boolean digit = false, lower = false, upper = false, special = false;
        for(int i = 0; i < password.length(); i++) {
            if(!digit && Character.isDigit(password.charAt(i))) {
                digit = true;
            } else if (!lower && Character.isLowerCase(password.charAt(i))) {
                lower = true;
            } else if (!upper && Character.isUpperCase(password.charAt(i))) {
                upper = true;
            } else if (!special && !Character.isAlphabetic(password.charAt(i))) {
                special = true;
            }
        }
        return digit && lower && upper && special;
    }

    public boolean customerLoginCheck (HttpServletRequest request, Long customerId) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Optional<Customer> customer = customerService.findCustomerByLoginId(loginId);
        return customer.isPresent() && !customer.get().getId().equals(customerId);
    }
}
