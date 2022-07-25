package edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.Bill;
import edu.domain.ModelFile;
import edu.service.ModelFileService;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import edu.support.resp.UploadResp;
import edu.support.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/model")
@Slf4j
public class ModelFileController {

    @Autowired
    private ModelFileService modelFileService;

    @PostMapping("/upload")
    public JsonResponse<UploadResp> upload(MultipartFile file, String username) {
        if (file == null || file.isEmpty()) {
            return JsonResponse.fail("请选择要上传的文件");
        }
        String path = modelFileService.upload(file, username);
        return JsonResponse.success(new UploadResp(username, path, null));
    }

    @PostMapping("/list")
    public JsonResponse list(String username) {

        QueryWrapper<ModelFile> wrapper = new MybatisWrapper<ModelFile>()
                .like("m.username", username);

        List<ModelFile> files = modelFileService.list(wrapper);

        return JsonResponse.success(files);
    }
}
