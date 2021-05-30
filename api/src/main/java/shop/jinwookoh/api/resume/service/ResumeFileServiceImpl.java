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
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
import shop.jinwookoh.api.resume.repository.ResumeFileRepository;
import shop.jinwookoh.api.resume.repository.ResumeRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResumeFileServiceImpl implements ResumeFileService {
    @Value("${shop.jinwookoh.upload.path}")
    private String uploadPath;
    private final ResumeFileRepository repo;

    @Override
    public List<ResumeFileDto> uploadFile(List<MultipartFile> uploadFiles) {

        List<ResumeFileDto> resultDtoList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String ofname = uploadFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            StringBuilder sb = new StringBuilder();
            sb.append(uploadPath).append(File.separator).append(uuid).append("_").append(ofname);
            String saveName = sb.toString();
            log.info("file upload name : " + saveName);
            Path savePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + ofname;
                Thumbnails.of(new File(saveName)).size(100, 100).outputFormat("JPEG").toFile(thumbnailSaveName);
                Thumbnails.of(new File(saveName)).scale(1)
                        .watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File(uploadPath + "watermark.jpg")), 0.5f)
                        .toFile(new File(uploadPath + File.separator + "w_" + uuid + "_" + ofname));
                ResumeFileDto resumeFileDto = ResumeFileDto.builder().uuid(uuid).fname(ofname).build();
                resultDtoList.add(resumeFileDto);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultDtoList;
    }

    @Override
    public void removeFiles(Long resumeId) {
        List<ResumeFile> fileList = repo.getAllForRemove(resumeId);
        for (ResumeFile fileLists : fileList) {
            File f = new File(uploadPath + fileLists.getUuid() + "_" + fileLists.getFname());
            File sf = new File(uploadPath + "s_" + fileLists.getUuid() + "_" + fileLists.getFname());
            File wf = new File(uploadPath + "w_" + fileLists.getUuid() + "_" + fileLists.getFname());
            System.out.println("deleteFile: " + f.exists());
            if (f.exists()) {
                f.delete();
                sf.delete();
                wf.delete();
            }
        }
    }

}
