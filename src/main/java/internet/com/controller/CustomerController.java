package internet.com.controller;

import internet.com.dto.customer_dto.CustomerDTO;
import internet.com.entity.customer.Customer;
import internet.com.entity.user.AppUser;
import internet.com.services.customer.ICustomerService;
import internet.com.services.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Created by: HaoNH
     * Date Created: 09/06/2022
     * method save customer
     *
     * @param customerDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Created by: DuyNT
     * Date Created: 10/08/2022
     * load customer info from database by id parameter
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id){
        Optional<Customer> customer = this.customerService.findCustomerById(id);
        if(!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    /**
     * Created by: CuongTM
     * Date Created: 11/08/2022
     * @param id
     * @param customerDTO
     * @param bindingResult
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody @Valid CustomerDTO
            customerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customerEdit = customerService.findCustomerById(id).get();
        if (customerEdit.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerEdit.getUser().setPassword(customerDTO.getPassword());
        customerEdit = modelMapper.map(customerDTO, Customer.class);
        AppUser appUser = new AppUser();
        appUser.setUsername(customerDTO.getUserName().getUsername());
        appUser.setPassword(customerDTO.getPassword());
        customerEdit.setCommune(customerDTO.getCommune());
        iUserService.updateUser(appUser);
        customerService.update(customerEdit);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}