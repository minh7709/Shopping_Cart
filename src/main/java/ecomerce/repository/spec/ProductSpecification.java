package ecomerce.repository.spec;

import ecomerce.dto.request.ProductSearchCriteria;
import ecomerce.entity.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getProducts(ProductSearchCriteria searchCriteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. PERFORMANCE: Fetch Join de tranh loi N + 1 thi khi kieu tra ve khong phai la Long
            if(Long.class != query.getResultType()){
                root.fetch("category", JoinType.LEFT);
                root.fetch("brand", JoinType.LEFT);
                root.fetch("series", JoinType.LEFT);
                root.fetch("character", JoinType.LEFT);
            }

            predicates.add(cb.equal(root.get("isDeleted"), false));
            if(searchCriteria.getKeyword() != null && !searchCriteria.getKeyword().isEmpty()){
                String pattern = "%" + searchCriteria.getKeyword().toLowerCase() + "%";
                predicates.add(cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("sku")), pattern)
                ));
            }
            if(searchCriteria.getCategoryIds() != null && !searchCriteria.getCategoryIds().isEmpty()){
                predicates.add(root.get("category").get("id").in(searchCriteria.getCategoryIds()));
            }
            if(searchCriteria.getBrandIds() != null){
                predicates.add(root.get("brand").get("id").in(searchCriteria.getBrandIds()));
            }
            if(searchCriteria.getSeriesIds() != null){
                predicates.add(root.get("series").get("id").in(searchCriteria.getSeriesIds()));
            }
            if(searchCriteria.getCharacterIds() != null){
                predicates.add(root.get("character").get("id").in(searchCriteria.getCharacterIds()));
            }
            if(searchCriteria.getMinPrice() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), searchCriteria.getMinPrice()));
            }
            if(searchCriteria.getMaxPrice() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), searchCriteria.getMaxPrice()));
            }

            if(searchCriteria.getIsPreorder() != null){
                predicates.add(cb.equal(root.get("isPreorder"), searchCriteria.getIsPreorder()));
            }
            if(searchCriteria.getInStock() != null && searchCriteria.getInStock()){
                predicates.add(cb.greaterThan(root.get("stockQuantity"), 0));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}