package daepoid.stockManager.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityLoginMemberDTO {

    private String loginId;

    private String username;
}
