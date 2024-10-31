package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import board.bean.BoardDTO;

@Mapper
public interface BoardDAO {

    public boolean onWrtie(Map<String, String> letter);

    public List<BoardDTO> getList();

    public BoardDTO getView(int seq);

    public boolean onDelete(int seq);

    public boolean onUpdate(Map<String, Object> map);

}
