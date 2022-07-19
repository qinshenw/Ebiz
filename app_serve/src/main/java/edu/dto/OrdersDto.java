package edu.dto;

import edu.domain.Orders;
import lombok.Data;

/**
 * @author 执笔
 * @date 2021/5/8  15:01
 */
@Data
public class OrdersDto extends Orders {



    /**
     *
     */
    private String username;

    /**
     *
     */
    private String nickname;
}
