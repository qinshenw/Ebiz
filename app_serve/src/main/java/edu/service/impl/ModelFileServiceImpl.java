package edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.Bill;
import edu.domain.ModelFile;
import edu.dto.ModelFileDto;
import edu.mapper.ModelFileMapper;
import edu.service.ModelFileService;
import edu.support.base.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ModelFileServiceImpl extends BaseServiceImpl<ModelFileMapper, ModelFile> implements ModelFileService {
    @Autowired
    private ModelFileMapper modelFileMapper;

    @Value("${fast.upload.path}")
    protected String uploadPath;


    public String upload(MultipartFile file, String username) {
        String path = "/upload/" + username + "/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/";
        String fileName = file.getOriginalFilename();
        File newFile = new File(uploadPath + path + fileName);
        newFile.getParentFile().mkdirs();
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
            ModelFile modelFile = new ModelFile();
            modelFile.setUsername(username);
            modelFile.setPath(uploadPath + path + fileName);
            save(modelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("◎Upload File◎  " + newFile.getAbsolutePath());
        return path + fileName;
    }

    public List<ModelFile> list(QueryWrapper<ModelFile> wrapper) {
        List<ModelFileDto> files = modelFileMapper.selectDto(wrapper);
        return files.stream().map(file -> {
            ModelFile tmp = new ModelFile();
            tmp.setUsername(file.getUsername());
            String[] parts = file.getPath().split("/");
            tmp.setPath(parts[parts.length - 1]);
            return tmp;
        }).collect(Collectors.toList());
    }

}
