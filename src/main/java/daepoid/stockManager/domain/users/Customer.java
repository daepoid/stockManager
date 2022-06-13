package daepoid.stockManager.domain.users;

import daepoid.stockManager.domain.member.GradeType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Customer extends StoreUser {

    @NotNull
    @Column(unique=true)
    private String tableNumber;

    private LocalDateTime expirationTime;

    @Builder
    public Customer(String loginId, String password, String userName, GradeType gradeType,
                    String tableNumber, LocalDateTime expirationTime) {
        this.changeLoginId(loginId);
        this.changePassword(password);
        this.changeUserName(userName);
        this.changeGradeType(gradeType);

        this.tableNumber = tableNumber;
        this.expirationTime = expirationTime;
    }

    public void changeTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void changeExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
