package shop.jinwookoh.api.resume.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResumeDto {

    private long resumeId;
    private String title;
    private String detail;
    private String coworker;
    private String mainPic;
    private String mainPicTitle;
    private String mainPicDate;

}
