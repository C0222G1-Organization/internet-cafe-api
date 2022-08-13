package internet.com.services.customer;

import internet.com.dto.customer_dto.ICustomerDTO;
import internet.com.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService {

    /**
     * Created by: DuyNT
     * Date Created: 10/08/2022
     * @param id
     */
    Optional<Customer> findCustomerById(Integer id);

    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     */
    Page<ICustomerDTO> searchCustomerByProvince(String province,String name,String activeStatus,String starDay,String endDay,Pageable pageable);
    Page<ICustomerDTO> searchCustomerByDistrict(String district,String name,String activeStatus,String starDay,String endDay,Pageable pageable);
    Page<ICustomerDTO> searchCustomerByCommune( String commune,String name,String activeStatus,String starDay,String endDay,Pageable pageable);


}
