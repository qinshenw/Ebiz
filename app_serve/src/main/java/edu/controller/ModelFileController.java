package edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.ListResult;
import edu.domain.ModelFile;
import edu.service.ModelFileService;
import edu.support.base.controller.BaseController;
import edu.support.mybatis.MybatisWrapper;
import edu.support.resp.JsonResponse;
import edu.support.resp.UploadResp;
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
public class ModelFileController extends BaseController {

    @Autowired
    private ModelFileService modelFileService;

    @PostMapping("/upload")
    public JsonResponse<UploadResp> upload(MultipartFile file, String username) {
        if (file == null || file.isEmpty()) {
            return JsonResponse.fail("Choose a file");
        }
        String path = modelFileService.upload(file, username);
        return JsonResponse.success(new UploadResp(username, path, null));
    }

    @PostMapping("/list")
    public JsonResponse list(String username) {

        QueryWrapper<ModelFile> wrapper = new MybatisWrapper<ModelFile>()
                .like("m.username", username);

        List<ListResult> files = modelFileService.list(wrapper);
        files.sort((o1, o2) -> o2.getHasResult() - o1.getHasResult());

        return JsonResponse.success(files);
    }
}
