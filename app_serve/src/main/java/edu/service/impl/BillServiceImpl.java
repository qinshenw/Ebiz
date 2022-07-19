package edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.BillDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseServiceImpl;
import edu.mapper.BillMapper;
import edu.domain.Bill;
import edu.service.BillService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Bill)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-08 17:53:44
 */
@Service
public class BillServiceImpl extends BaseServiceImpl<BillMapper, Bill> implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public PageInfo<BillDto> pageDto(BasePage rowPage, QueryWrapper<Bill> wrapper) {
        startPage(rowPage);
        return new PageInfo<BillDto>(billMapper.selectDto(wrapper));
    }
}
