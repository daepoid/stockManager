package daepoid.stockManager.inteceptor;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class GradeCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 = {}", requestURI);

        HttpSession session = request.getSession(false);
        // session check 를 이미 수행함, 굳이 추가로 할 필요가 있을까?
        // 만약 찰나의 순간에 session 정보가 사라진다면 차라리 튕겨내는 것이 좋을지도?

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            response.sendRedirect("/");
            return false;
        }

        LoginMemberDTO loginMemberDTO = (LoginMemberDTO) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Member member = memberService.findMemberByLoginId(loginMemberDTO.getLoginId());
        return member.getGradeType().equals(GradeType.MANAGER) || member.getGradeType().equals(GradeType.CEO);
    }
}
