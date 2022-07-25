package edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.support.base.service.BaseService;

import java.util.List;

public interface ReportService extends BaseService<Report> {

    Report display(QueryWrapper<Report> wrapper);

    ModelFile export(QueryWrapper<ModelFile> wrapper);
}
