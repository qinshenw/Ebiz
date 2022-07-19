package edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.ScoreDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseService;
import edu.domain.Score;
import org.springframework.stereotype.Service;


/**
 * (Score)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-07 23:48:12
 */
public interface ScoreService extends BaseService<Score> {

    PageInfo<ScoreDto> pageDto(BasePage rowPage, QueryWrapper<Score> wrapper);
}
