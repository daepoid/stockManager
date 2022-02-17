package daepoid.stockManager.api.dto.member;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.MemberStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UpdateMemberRequestDTO {

    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
}
