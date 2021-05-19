package shop.jinwookoh.api.resume.service;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
import shop.jinwookoh.api.resume.repository.ResumeFileRepository;

@Log4j2
@Service
@RequiredArgsConstructor

public class ResumeFileServiceImpl implements ResumeFileService {
    private final ResumeFileRepository repo;

    @Value("${shop.jinwookoh.upload.path}")
    private String uploadPath;

    @Override
    public String registerFile(MultipartFile file) {

        List<ResumeFileDto> resultDtoList = new ArrayList<>();
        String ofname = file.getOriginalFilename();
        int idx = ofname.lastIndexOf(".");
        String ofheader = ofname.substring(0, idx);
        String ext = ofname.substring(idx);
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(ofheader).append("_").append(uuid).append(ext);
        String fname = sb.toString();
        log.info("file upload name : " + fname);
        Path savePath = Paths.get(fname);

        try {
            file.transferTo(savePath);
            String thumbnailSaveName = uploadPath + "s_" + uuid + fname;
            Thumbnails.of(new File(uploadPath + fname)).size(100, 100).outputFormat("jpg").toFile(thumbnailSaveName);
            Thumbnails.of(new File(uploadPath + fname))
                    .watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File(uploadPath + "watermark.png")), 0.5f)
                    .toFile(new File(uploadPath + "w_" + fname));
            ResumeFileDto resumeFileDto = ResumeFileDto.builder().uuid(uuid).fname(fname).build();
            resultDtoList.add(resumeFileDto);

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return uploadPath + fname;
    }

    @Override
    public Boolean getfile(String fileName) {
        // TODO Auto-generated method stub
        byte[] result = null;
        try {
            String strFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName", fileName);
            File file = new File(uploadPath + File.separator + strFileName);
            log.info("file: ", file);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", Files.probeContentType(file.toPath()));
            result = FileCopyUtils.copyToByteArray(file);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
