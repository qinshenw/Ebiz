package edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.dto.ModelFileDto;
import edu.dto.ReportDto;
import edu.mapper.ReportMapper;
import edu.service.ReportService;
import edu.support.base.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends BaseServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Report display(QueryWrapper<Report> wrapper) {

        ReportDto report = reportMapper.selectDisplayDto(wrapper);
        return (Report) report;
    }

    @Override
    public ModelFile export(QueryWrapper<ModelFile> wrapper) {
        ModelFileDto report = reportMapper.selectExportDto(wrapper);
        return (ModelFile) report;
    }
}
