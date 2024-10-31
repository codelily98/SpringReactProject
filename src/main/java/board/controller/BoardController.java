package board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

//@Controller // .jsp를 사용하기위해서 @Controller를 사용한다.
@RestController // .jsp를 사용하지 못하는 SpringBoot에서 기본으로 사용하는 어노테이션 (@ResponseBody와 같다)
@CrossOrigin(origins = "http://localhost:5173")//, allowCredentials = "true" <= 세션 사용을 가능하게 해줌)
@RequestMapping("board")
public class BoardController {
    @Autowired
    private BoardDAO boardDAO;
    
    @RequestMapping(value = "write", method = RequestMethod.POST)
//    @ResponseBody //모든 결과를 브라우저(Body)로 바로 뿌려줭
    public String write(@RequestParam("title") String title, @RequestParam("content") String content) {
        Map<String, String> letter = new HashedMap<String, String>();
        letter.put("title", title);
        letter.put("content", content);
        
        boolean result = boardDAO.onWrtie(letter);
        
        if(!result) {
            return "글작성 실패";
        } else {
            return "글작성 성공";
        }
    }
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
//    @ResponseBody
    public List<BoardDTO> list() {
        List<BoardDTO> list = new ArrayList<BoardDTO>();
        list = boardDAO.getList();
        
        return list;
    }
    
    @RequestMapping(value = "view", method = RequestMethod.GET)
//    @ResponseBody
    public BoardDTO view(@RequestParam("seq") int seq) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO = boardDAO.getView(seq);
        
        return boardDTO;
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam("seq") int seq) {
        boolean result = boardDAO.onDelete(seq);
        
        if(result) {
            return "글 삭제가 완료되었습니다.";
        } else {
            return "글 삭제 실패";
        }
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam("seq") int seq, @RequestParam("title") String title, @RequestParam("content") String content) {
        Map<String, Object> map = new HashedMap<String, Object>();
        map.put("seq", seq);
        map.put("title", title);
        map.put("content", content);
        
        boolean result = boardDAO.onUpdate(map);
        
        if(result) {
            return "글 수정을 완료했습니다.";
        } else {
            return "글 수정에 실패했습니다.";
        }
    }
}
