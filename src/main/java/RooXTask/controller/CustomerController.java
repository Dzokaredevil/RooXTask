package RooXTask.controller;

import RooXTask.model.Customer;
import RooXTask.repo.CustomerRepository;
import RooXTask.security.JwtAuthToken;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@RepositoryRestController
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Shortcut findOne для текущего зарегистрированного пользователя.
     */
    @RequestMapping(value = "/customers/@me", method = RequestMethod.GET)
    public ResponseEntity<Resource<?>> getMe(Principal principal,
                                             PersistentEntityResourceAssembler assembler) {
        Customer customer = customerRepository.findOne(((JwtAuthToken)principal).getId());
        PersistentEntityResource resource = assembler.toFullResource(customer);
        return new ResponseEntity<>(resource, HttpStatus.OK);

    }

}