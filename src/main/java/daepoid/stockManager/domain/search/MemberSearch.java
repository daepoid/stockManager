package daepoid.stockManager.domain.search;

import daepoid.stockManager.domain.users.GradeType;
import daepoid.stockManager.domain.users.MemberStatus;

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
