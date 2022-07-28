package edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.dto.ModelFileDto;
import edu.dto.ReportDto;
import edu.mapper.ReportMapper;
import edu.service.ReportService;
import edu.support.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
    public boolean export(QueryWrapper<Report> wrapper, String filepath) {
        File file = new File(filepath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ReportDto report = reportMapper.selectDisplayDto(wrapper);
        String content = String.format("\t\tIoU\tArea\tMaxDetections\tValue\n" +
                "Average Precision\t0.50:0.95\tall\t100\t\t%s\n" +
                "\t\t0.5\tall\t100\t\t%s\n" +
                "\t\t0.75\tall\t100\t\t%s\n" +
                "\t\t0.50:0.95\tsmall\t100\t\t%s\n" +
                "\t\t0.50:0.95\tmedium\t100\t\t%s\n" +
                "\t\t0.50:0.95\tlarge\t100\t\t%s\n" +
                "Average Recall\t0.50:0.95\tall\t1\t\t%s\n" +
                "\t\t0.50:0.95\tall\t10\t\t%s\n" +
                "\t\t0.50:0.95\tall\t100\t\t%s\n" +
                "\t\t0.50:0.95\tsmall\t100\t\t%s\n" +
                "\t\t0.50:0.95\tmedium\t100\t\t%s\n" +
                "\t\t0.50:0.95\tlarge\t100\t\t%s", report.getValue1(), report.getValue2(), report.getValue3(), report.getValue4(),
                report.getValue5(), report.getValue6(), report.getValue7(), report.getValue8(), report.getValue9(), report.getValue10(),
                report.getValue11(), report.getValue12());
        return writeFile(content, filepath);
    }

    private boolean writeFile(String content,String filepath) {
        boolean flag=false;
        try {
            PrintWriter pw=new PrintWriter(filepath);
            pw.write(content);
            pw.flush();
            pw.close();
            flag=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
