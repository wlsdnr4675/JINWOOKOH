package shop.jinwookoh.api.resume.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResumeFileDto {

    private Long resumeFileId;
    private String uuid;
    private String fname;
    private Boolean repImg;
}
