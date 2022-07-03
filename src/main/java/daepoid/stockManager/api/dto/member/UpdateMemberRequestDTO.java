package daepoid.stockManager.api.dto.member;

import daepoid.stockManager.domain.users.GradeType;
import daepoid.stockManager.domain.users.MemberStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateMemberRequestDTO {

    @NotBlank
    private String password;

    private String userName;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
}
