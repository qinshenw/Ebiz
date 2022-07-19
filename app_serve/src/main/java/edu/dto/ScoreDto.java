package edu.dto;

import edu.domain.Score;
import lombok.Data;

/**
 * @author 执笔
 * @date 2021/5/8  14:25
 */
@Data
public class ScoreDto extends Score {

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String nickname;

    /**
     *
     */
    private String goodsNo;
    private String goodsRoom;
}
