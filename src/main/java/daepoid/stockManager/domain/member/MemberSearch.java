package daepoid.stockManager.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearch {

    private String name;

    private GradeType gradeType;

    private MemberStatus memberStatus;
}
