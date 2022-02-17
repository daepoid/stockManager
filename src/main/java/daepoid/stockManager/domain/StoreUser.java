package daepoid.stockManager.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class StoreUser {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    // jpa - GeneratedValue 이면 사용할 필요없음
    public void changeId(Long id) {
        this.id = id;
    }

    public void changeLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeUserName(String userName) {
        this.userName = userName;
    }
}
