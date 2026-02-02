package ecomerce.repository;

import org.springframework.stereotype.Repository;
import ecomerce.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
