package internet.com.repository.customer_repo;

import internet.com.dto.customer_dto.ICustomerDTO;
import internet.com.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface ICustomerRepository extends CrudRepository<Customer, Integer> {

    /**
     * Created by: DuyNT
     * Date Created: 10/08/2022
     *
     * @param customerId
     * @return
     */
    @Query(value = "SELECT id, customer_name, date_of_birth,phone_number, email, active_status, delete_status, " +
            "remaining_time, user_name, address_id FROM customer WHERE id =:customerId", nativeQuery = true)
    Optional<Customer> findByIdCustomer(@Param("customerId") Integer customerId);

    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     *
     */

    @Query(value = "select dt.id as idDistric,p.id as idProvince, c.id as id,user_name as userName,phone_number as phoneNumber, customer_name as name," +
            "date_of_birth as dateOfBirth, email as email,active_status as activeStatus, remaining_time as remainingTime," +
            "cm.commune_name as nameCommune ,cm.district_id as idDistrict ,dt.district_name as nameDistrict," +
            "p.province_name as nameProvince,delete_status as deleteStatus  from customer c " +
            "join commune cm on c.address_id = cm.id join district dt on dt.id = cm.district_id " +
            "join province p on p.id = dt.province_id where delete_status=0 and p.province_name like :province " +
            "and customer_name like :name and active_status like :activeStatus and (date_of_birth between :starDate and :endDate)"
            , nativeQuery = true, countQuery = "select dt.id as idDistric,p.id as idProvince,  c.id as id,user_name as userName,phone_number as phoneNumber, customer_name as name," +
            "date_of_birth as dateOfBirth, email as email,active_status as activeStatus, remaining_time as remainingTime," +
            "cm.commune_name as nameCommune ,cm.district_id as idDistrict ,dt.district_name as nameDistrict," +
            "p.province_name as nameProvince,delete_status as deleteStatus  from customer c " +
            "join commune cm on c.address_id = cm.id " +
            "join district dt on dt.id = cm.district_id " +
            "join province p on p.id = dt.province_id " +
            "where delete_status=0 " +
            "and p.province_name like :province " +
            "and customer_name like :name " +
            "and active_status like :activeStatus and (date_of_birth between :starDate and :endDate)")
    Page<ICustomerDTO> searchCustomerByProvince(@Param("province") String province,
                                                @Param("name") String name,
                                                @Param("activeStatus") String activeStatus,
                                                @Param("starDate") String starDate,
                                                @Param("endDate") String endDate,
                                                Pageable pageable);

    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     */
    @Query(value = "select  c.id as id, dt.id as idDistric,p.id as idProvince, " +
            "user_name as userName, " +
            "c.phone_number as phoneNumber, " +
            "c.customer_name as name, " +
            "date_of_birth as dateOfBirth, " +
            "email as email, " +
            "active_status as activeStatus, " +
            "remaining_time as remainingTime, " +
            "cm.commune_name as nameCommune, " +
            "dt.district_name as nameDistrict, " +
            "p.province_name as nameProvince, " +
            "delete_status as deleteStatus  from customer c " +
            "join commune cm on c.address_id = cm.id " +
            "join district dt on dt.id = cm.district_id " +
            "join province p on p.id = dt.province_id " +
            "where delete_status=0 and dt.district_name like :district " +
            "and customer_name like :name and active_status like :activeStatus and (date_of_birth between :starDate and :endDate)"
            , nativeQuery = true, countQuery = "select  c.id as id,dt.id as idDistric,p.id as idProvince,user_name as userName,phone_number as phoneNumber, customer_name as name," +
            "date_of_birth as dateOfBirth, email as email,active_status as activeStatus, remaining_time as remainingTime," +
            "cm.commune_name as nameCommune ,dt.district_name as nameDistrict," +
            "p.province_name as nameProvince,delete_status as deleteStatus  from customer c " +
            "join commune cm on c.address_id = cm.id join district dt on dt.id = cm.district_id " +
            "join province p on p.id = dt.province_id where delete_status=0 and dt.district_name like :district " +
            "and customer_name like :name and active_status like :activeStatus and (date_of_birth between :starDate and :endDate)")
    Page<ICustomerDTO> searchCustomerByDistrict(@Param("district") String commune,
                                                @Param("name") String name,
                                                @Param("activeStatus") String statusActive,
                                                @Param("starDate") String starDate,
                                                @Param("endDate") String endDate,
                                                Pageable pageable);

    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     */
    @Query(value = "select dt.id as idDistric,p.id as idProvince,  c.id as id,user_name as userName,c.phone_number as phoneNumber, c.customer_name as name," +
            "date_of_birth as dateOfBirth, email as email,active_status as activeStatus, remaining_time as remainingTime," +
            "cm.commune_name as nameCommune ,cm.district_id as idDistrict ,dt.district_name as nameDistrict," +
            "p.province_name as nameProvince,delete_status as deleteStatus  from customer c " +
            "join commune cm on c.address_id = cm.id join district dt on dt.id = cm.district_id " +
            "join province p on p.id = dt.province_id where delete_status=0 and cm.commune_name like :commune " +
            "and customer_name like :name and active_status like :activeStatus and (date_of_birth between :starDate and :endDate) "
            , nativeQuery = true, countQuery = "select  c.id as id,user_name as userName,phone_number as phoneNumber, customer_name as name," +
            "date_of_birth as dateOfBirth, email as email,active_status as activeStatus, remaining_time as remainingTime," +
            "cm.commune_name as nameCommune ,cm.district_id as idDistrict ,dt.district_name as nameDistrict," +
            "p.province_name as nameProvince,delete_status as deleteStatus  from customer c " +
            "join commune cm on c.address_id = cm.id join district dt on dt.id = cm.district_id " +
            "join province p on p.id = dt.province_id where delete_status=0 and cm.commune_name like :commune and (date_of_birth between :starDate and :endDate) " +
            "and customer_name like :name and active_status like :activeStatus")
    Page<ICustomerDTO> searchCustomerByCommune(@Param("commune") String commune,
                                               @Param("name") String name,
                                               @Param("activeStatus") String statusActive,
                                               @Param("starDate") String starDate,
                                               @Param("endDate") String endDate,
                                               Pageable pageable);
    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     */
    @Modifying
    @Query(value="update customer set delete_status = 1 where id = :id ",nativeQuery = true)
    void deleteCustomerById(@Param("id") Integer id);

    /**
     * Created by:HaoNH
     * Date Created:09/06/2022
     * method save
     * customer
     *
     * @param name
     * @param dateOfBirth
     * @param email
     * @param phone
     * @param userName
     * @param address
     */

    @Modifying
    @Query(value = "INSERT INTO customer(customer_name, date_of_birth, email, phone_number, remaining_time, user_name, address_id) VALUE " +
            "( :name, :dateOfBirth, :email, :phone, 0, :userName, :address)", nativeQuery = true)
    void saveCustomer(@Param("name") String name,
                      @Param("dateOfBirth") String dateOfBirth,
                      @Param("email") String email,
                      @Param("phone") String phone,
                      @Param("userName") String userName,
                      @Param("address") Integer address);

    /**
     * Create by HaoNH
     * Date create: 11/09/2022
     * method check email is exits
     *
     * @param email
     * @return
     */
    @Query(value = "SELECT email FROM customer where email = :email", nativeQuery = true)
    String existsEmail(@Param("email") String email);

    /**
     * Create by HaoNH
     * Date create: 11/09/2022
     * method check phone is exits
     *
     * @param phone
     * @return
     */
    @Query(value = "SELECT phone_number FROM customer where phone_number = :phone", nativeQuery = true)
    String existsPhone(@Param("phone") String phone);

    /**
     * Create by CuongTM
     * Date create: 11/08/2022
     * method update customer
     *
     * @param name
     * @param dateOfBirth
     * @param email
     * @param phoneNumber
     * @param activeStatus
     * @param communeId
     * @param customerId
     */
    @Modifying
    @Query(value = " UPDATE customer SET customer_name=:name, date_of_birth=:dateOfBirth,phone_number=:phoneNumber, " +
            "email=:email, " +
            " active_status=:activeStatus, address_id=:communeId WHERE id=:customerId", nativeQuery = true)
    void update(@Param("name") String name, @Param("dateOfBirth") String dateOfBirth,
                @Param("email") String email,
                @Param("phoneNumber") String phoneNumber,
                @Param("activeStatus") Integer activeStatus,
                @Param("communeId") Integer communeId,
                @Param("customerId") Integer customerId);
}

