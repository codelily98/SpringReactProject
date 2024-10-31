package board.bean;

import lombok.Data;

@Data
public class BoardDTO {
    private int seq;
    private String title;
    private String content;
    
//    작성일 형식 지정 방법
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
//    private Date logtime;
}
