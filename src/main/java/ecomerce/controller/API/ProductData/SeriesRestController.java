package ecomerce.controller.API.ProductData;
import ecomerce.dto.response.SeriesResponse;
import ecomerce.entity.Series;
import ecomerce.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecomerce.dto.response.ApiResponse;
import java.util.List;
@RestController
@RequestMapping("/api/product-data")
@RequiredArgsConstructor

public class SeriesRestController {
    private final SeriesRepository seriesRepository;
    @GetMapping("/series")
    public ApiResponse<List<SeriesResponse>> getAllSeries() {
        List<Series> series = seriesRepository.findAll();
        List<SeriesResponse> seriesResponses = series.stream()
                .map(SeriesResponse::toDTO)
                .toList();
        return ApiResponse.success(seriesResponses);
    }
}
