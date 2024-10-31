package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import member.bean.MemberDTO;

@Mapper
public interface MemberDAO {

    public List<MemberDTO> onLogin(Map<String, String> user);

}
