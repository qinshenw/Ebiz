package edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.GoodsDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseService;
import edu.domain.Goods;
import org.springframework.stereotype.Service;


/**
 * (Goods)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-08 17:53:45
 */
public interface GoodsService extends BaseService<Goods> {

    PageInfo<GoodsDto> pageDto(BasePage rowPage, QueryWrapper<Goods> wrapper);
}
