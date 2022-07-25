package edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.dto.ModelFileDto;
import edu.dto.ReportDto;
import org.apache.ibatis.annotations.Param;

public interface ReportMapper extends BaseMapper<Report> {
    ReportDto selectDisplayDto(@Param("ew") QueryWrapper<Report> wrapper);

    ModelFileDto selectExportDto(@Param("ew") QueryWrapper<ModelFile> wrapper);
}
