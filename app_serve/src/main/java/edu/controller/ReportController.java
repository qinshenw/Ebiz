package edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.service.ReportService;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@Slf4j
public class ReportController {

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
        QueryWrapper<ModelFile> wrapper = new MybatisWrapper<ModelFile>()
                .like("m.username", username)
                .likeRight("m.path", "/" + filename);
        String path = reportService.export(wrapper).getPath();
        int idx = path.lastIndexOf("/");
        String filePath = path.substring(0, idx);
        //待下载文件名
        String fileName = path.substring(idx + 1);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try {
            IOUtils.copy(new FileInputStream(filePath), response.getOutputStream());
        } catch (IOException e) {
            log.error("下载文件失败", e);
        }
    }
}
