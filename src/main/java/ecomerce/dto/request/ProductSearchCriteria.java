package ecomerce.dto.request;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductSearchCriteria {
    private String keyword;

    // ĐỔI TỪ INTEGER SANG LIST<INTEGER> VÀ ĐỔI TÊN THÀNH SỐ NHIỀU
    private List<Integer> categoryIds;
    private List<Integer> brandIds;
    private List<Integer> seriesIds;
    private List<Integer> characterIds;


    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private Integer SoldQuantity;
    private Boolean isPreorder;
    private Boolean inStock;
    private String sort; // Mình đổi sortBy thành sort để khớp với Pageable của Spring
}
