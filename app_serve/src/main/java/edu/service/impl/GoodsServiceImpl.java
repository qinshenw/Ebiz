package edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.GoodsDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseServiceImpl;
import edu.mapper.GoodsMapper;
import edu.domain.Goods;
import edu.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Goods)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-08 17:53:45
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo<GoodsDto> pageDto(BasePage rowPage, QueryWrapper<Goods> wrapper) {
        startPage(rowPage);
        return new PageInfo<>(goodsMapper.selectDto(wrapper));
    }
}
