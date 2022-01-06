package daepoid.stockManager.dto;

import daepoid.stockManager.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMyInfoDTO {

    private Long id;

    // 로그인 아이디
    private String loginId;

    // 이름
    private String name;

    // 전화번호 '01012341234' 형태로 저장됨
    private String phoneNumber;

    // 비민번호, 개인정보 수정 시 확인용
    private String password;

    public EditMyInfoDTO(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
    }
}
