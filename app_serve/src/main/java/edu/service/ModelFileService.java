package edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.Bill;
import edu.domain.Goods;
import edu.domain.ModelFile;
import edu.support.base.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ModelFileService extends BaseService<ModelFile> {

    String upload(MultipartFile file, String username);

    List<ModelFile> list(QueryWrapper<ModelFile> wrapper);
}
