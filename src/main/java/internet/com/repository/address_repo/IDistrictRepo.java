package internet.com.repository.address_repo;

import internet.com.entity.customer.District;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDistrictRepo extends CrudRepository<District, Integer> {
    @Query(value="select id, district_name, province_id from district where province_id = :provinceId", nativeQuery = true)
    List<District> findAllDistrict(@Param("provinceId") Integer provinceId);
}
