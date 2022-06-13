package daepoid.stockManager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordService {

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
}
