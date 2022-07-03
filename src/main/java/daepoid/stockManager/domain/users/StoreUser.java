package daepoid.stockManager.domain.users;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@MappedSuperclass
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private GradeType gradeType = GradeType.UNDEFINED;

    public void changeLoginId(String loginId) {
        this.loginId = loginId;
    }
    public void changePassword(String password) {
        this.password = password;
    }
    public void changeUserName(String userName) {
        this.userName = userName;
    }
    public void changeGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }
}
