package edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.domain.Score;
import edu.dto.ScoreDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Score)表数据库访问层
 *
 * @author 执笔
 * @date 2021-05-07 23:48:11
 */
public interface ScoreMapper extends BaseMapper<Score> {

    List<ScoreDto> selectDto(@Param("ew") QueryWrapper<Score> wrapper);
}
