package shop.jinwookoh.api.resume.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResumeFileServiceImpl implements ResumeFileService {
    @Value("${shop.jinwookoh.upload.path}")
    private String uploadPath;

    @Override
    public List<ResumeFileDto> uploadFile(List<MultipartFile> uploadFiles) {

        List<ResumeFileDto> resultDtoList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String ofname = uploadFile.getOriginalFilename();
            int idx = ofname.lastIndexOf(".");
            String ofheader = ofname.substring(0, idx);
            String ext = ofname.substring(idx);
            String uuid = UUID.randomUUID().toString();
            StringBuilder sb = new StringBuilder();
            sb.append(uploadPath).append(ofheader).append("_").append(uuid).append(ext);
            String saveName = sb.toString();
            log.info("file upload name : " + saveName);
            Path savePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + "s_" + uuid + ofname;
                Thumbnails.of(new File(saveName)).size(100, 100).outputFormat("JPEG").toFile(thumbnailSaveName);
                Thumbnails.of(new File(saveName)).scale(1)
                        .watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File(uploadPath + "watermark.jpg")), 0.5f)
                        .toFile(new File(uploadPath + "w_" + uuid + ofname));
                ResumeFileDto resumeFileDto = ResumeFileDto.builder().uuid(uuid).fname(saveName).build();
                resultDtoList.add(resumeFileDto);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultDtoList;
    }

}
