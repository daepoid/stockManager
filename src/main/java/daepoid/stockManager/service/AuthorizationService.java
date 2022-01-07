package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorizationService {

    private final JpaMemberRepository memberRepository;

    public boolean checkAuthorityMemberByGradeType(Long memberId, GradeType... gradeTypes) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) {
            log.info("wrong memberId = {}", member);
            return false;
        }

        GradeType memberGradeType = member.getGradeType();
        for (GradeType gradeType : gradeTypes) {
            if(memberGradeType.equals(gradeType)) {
                return true;
            }
        }
        return false;
    }
}
