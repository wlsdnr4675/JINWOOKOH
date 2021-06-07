package shop.philoarte.api.artist.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class PageRequestFileDto {
    private int page;
    private int size;
    private String type;
    private String keyword;

    private List<ArtistFileDto> pageFileDto;

    public PageRequestFileDto() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {

        return PageRequest.of(page - 1, size, sort);
    }
}
