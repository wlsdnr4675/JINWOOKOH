package shop.jinwookoh.api.resume.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.common.util.ModelMapperUtils;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDto {

    private Long resumeId;
    private String title;
    private String selfIntroduce;
    private String detail;
    private ArrayList<ResumeFileDto> resumeFiles;

    public static ResumeDto of(Resume resume) {
        ResumeDto resumeDto = ModelMapperUtils.getModelMapper().map(resume, ResumeDto.class);
        return resumeDto;
    }
}
