package member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Controller
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberDAO memberDAO;
    
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam ("id") String id, @RequestParam ("pwd") String pwd) { 
        
        Map<String, String> user = new HashedMap<>();
        user.put("id", id);
        user.put("pwd", pwd);
        
        List<MemberDTO> list = new ArrayList<>();
        try {
            list = memberDAO.onLogin(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "서버 오류: ";
        }
        
        if (!list.isEmpty()) {
            session.setAttribute("userId", id);
            return "로그인 성공";
        } else {
            return "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        session.invalidate();
        
        return "로그아웃 성공";
    }
}
