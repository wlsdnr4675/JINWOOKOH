package shop.jinwookoh.api.resume.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeFileDto {

    private String uuid;
    private String fname;
    private Boolean repImg;

    private String fileTitle;
    private String fileDetail;
    private String fileWorkedDate;

}
