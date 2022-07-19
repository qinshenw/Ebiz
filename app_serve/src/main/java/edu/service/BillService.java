package edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.BillDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseService;
import edu.domain.Bill;
import org.springframework.stereotype.Service;


/**
 * (Bill)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-08 17:53:44
 */
public interface BillService extends BaseService<Bill> {

    PageInfo<BillDto> pageDto(BasePage rowPage, QueryWrapper<Bill> wrapper);
}
