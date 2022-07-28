package edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.domain.Bill;
import edu.domain.ListResult;
import edu.domain.ModelFile;
import edu.domain.Report;
import edu.dto.ModelFileDto;
import edu.dto.ReportDto;
import edu.mapper.ModelFileMapper;
import edu.mapper.ReportMapper;
import edu.service.ModelFileService;
import edu.support.base.service.BaseServiceImpl;
import edu.support.mybatis.MybatisWrapper;
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

    @Autowired
    private ReportMapper reportMapper;

    @Value("${fast.upload.path}")
    protected String uploadPath;


    public String upload(MultipartFile file, String username) {
        String path = "/upload/" + username + "/";
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

    public List<ListResult> list(QueryWrapper<ModelFile> wrapper) {
        List<ModelFileDto> files = modelFileMapper.selectDto(wrapper);
        return files.stream().map(file -> {
            ListResult tmp = new ListResult();
            tmp.setUsername(file.getUsername());
            String[] parts = file.getPath().split("/");
            String filename = parts[parts.length - 1];
            tmp.setFilename(filename);

            QueryWrapper<Report> hasResultWrapper = new MybatisWrapper<Report>()
                    .like("m.username", file.getUsername())
                    .like("m.filename", filename);
            ReportDto report = reportMapper.selectDisplayDto(hasResultWrapper);
            tmp.setHasResult(report == null ? 0 : 1);

            return tmp;
        }).collect(Collectors.toList());
    }

}
