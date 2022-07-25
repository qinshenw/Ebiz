package edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.domain.ModelFile;
import edu.dto.ModelFileDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelFileMapper extends BaseMapper<ModelFile> {

    List<ModelFileDto> selectDto(@Param("ew") QueryWrapper<ModelFile> wrapper);
}
