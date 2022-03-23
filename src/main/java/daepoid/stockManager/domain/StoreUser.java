package daepoid.stockManager.domain;

import daepoid.stockManager.domain.member.GradeType;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    // 유형
    @NotNull
    @Enumerated(EnumType.STRING)
    private GradeType gradeType = GradeType.UNDEFINED;

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

    // 유형 변경
    public void changeGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

}
