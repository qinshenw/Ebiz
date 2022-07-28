package edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.service.ReportService;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@Slf4j
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;

    @Value("${fast.upload.path}")
    protected String uploadPath;

    @PostMapping("/display")
    public JsonResponse display(String username, String filename) {
        QueryWrapper<Report> wrapper = new MybatisWrapper<Report>()
                .like("m.username", username)
                .like("m.filename", filename);

        Report files = reportService.display(wrapper);

        return JsonResponse.success(files);
    }

    @GetMapping("/export")
    public void export(String username, String filename, HttpServletResponse response) {

        String path = "/report/" + username + "/";
        String filePath = uploadPath + path + filename + ".txt";

        QueryWrapper<Report> wrapper = new MybatisWrapper<Report>()
                .like("m.username", username)
                .like("m.filename", filename);

        boolean updateReport = reportService.export(wrapper, filePath);
        if (!updateReport) log.error("Download Failure");
        else {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
            try {
                IOUtils.copy(new FileInputStream(filePath), response.getOutputStream());
            } catch (IOException e) {
                log.error("Download Failure", e);
            }
        }

    }
}
