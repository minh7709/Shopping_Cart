package ecomerce.dto.response;

import ecomerce.entity.Series;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeriesResponse {
    private Integer id;
    private String name;
    public static SeriesResponse toDTO(Series series) {
        return SeriesResponse.builder()
                .id(series.getId())
                .name(series.getName())
                .build();
    }
    public static ecomerce.entity.Series toEntity(SeriesResponse seriesResponse) {
        ecomerce.entity.Series series = new ecomerce.entity.Series();
        series.setId(seriesResponse.getId());
        series.setName(seriesResponse.getName());
        return series;
    }
}

