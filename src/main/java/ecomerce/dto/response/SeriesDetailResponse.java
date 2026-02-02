package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeriesDetailResponse {
    private Integer id;
    private String name;
    private List<CharacterResponse> characters;
}

