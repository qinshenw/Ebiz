package edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import edu.dto.ScoreDto;
import edu.support.base.BasePage;
import edu.support.base.service.BaseServiceImpl;
import edu.mapper.ScoreMapper;
import edu.domain.Score;
import edu.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Score)表服务实现类
 *
 * @author 执笔
 * @date 2021-05-07 23:48:11
 */
@Service
public class ScoreServiceImpl extends BaseServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageInfo<ScoreDto> pageDto(BasePage rowPage, QueryWrapper<Score> wrapper) {
        startPage(rowPage);
        return new PageInfo<>(scoreMapper.selectDto(wrapper));
    }
}
