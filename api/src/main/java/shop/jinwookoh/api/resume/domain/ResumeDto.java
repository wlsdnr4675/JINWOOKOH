package shop.jinwookoh.api.resume.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.common.util.ModelMapperUtils;

@Component
@Data
@NoArgsConstructor
public class ResumeDto {

    private long resumeId;
    private String title;
    private String detail;
    private String coworker;
    private String mainPic;
    private String mainPicTitle;
    private String mainPicDate;

    public static ResumeDto of(Resume resume) {
        ResumeDto resumeDto = ModelMapperUtils.getModelMapper().map(resume, ResumeDto.class);
        return resumeDto;
    }

}
