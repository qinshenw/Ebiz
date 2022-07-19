package edu.dto;

import edu.domain.Goods;
import lombok.Data;

/**
 * @author 执笔
 * @date 2021/5/8 23:37
 */
@Data
public class GoodsDto extends Goods {


    /**
     *
     */
    private String username;

    /**
     *
     */
    private String nickname;
}
