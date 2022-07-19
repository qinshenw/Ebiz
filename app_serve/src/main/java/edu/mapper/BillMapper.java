package edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.domain.Bill;
import edu.dto.BillDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Bill)表数据库访问层
 *
 * @author 执笔
 * @date 2021-05-08 17:53:44
 */
public interface BillMapper extends BaseMapper<Bill> {

    List<BillDto> selectDto(@Param("ew") QueryWrapper<Bill> wrapper);
}
