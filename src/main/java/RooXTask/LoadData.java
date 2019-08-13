package RooXTask;

import RooXTask.model.Customer;
import RooXTask.model.PartnerMapping;
import RooXTask.repo.CustomerRepository;
import RooXTask.repo.PartnerMappingRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoadData implements ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final PartnerMappingRepository partnerMappingRepository;

    public LoadData(CustomerRepository customerRepository, PartnerMappingRepository partnerMappingRepository) {
        this.customerRepository = customerRepository;
        this.partnerMappingRepository = partnerMappingRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Customer ivan = new Customer(UUID.fromString("6d5e503e-bd0c-11e9-9cb5-2a2ae2dbcce4"));
        ivan.setFullName("Ivan Petrov");
        ivan.setBalance(200);
        ivan.setActive(true);
        ivan.setUsername("ivan");
        ivan.setPassword("uwZTQJ4a");
        customerRepository.save(ivan);

        Customer mariya = new Customer(UUID.fromString("2fa8b210-bd0d-11e9-9cb5-2a2ae2dbcce4"));
        mariya.setFullName("Mariya Petrova");
        mariya.setBalance(300);
        mariya.setActive(true);
        mariya.setUsername("mariya");
        mariya.setPassword("GzxbZjkN");
        customerRepository.save(mariya);

        Customer dima = new Customer(UUID.fromString("51d53d36-bd0d-11e9-9cb5-2a2ae2dbcce4"));
        dima.setFullName("Programmer Dima");
        dima.setBalance(500);
        dima.setActive(true);
        dima.setUsername("dima");
        dima.setPassword("6yz4zAt");
        customerRepository.save(dima);

        PartnerMapping ivanMapping1 = new PartnerMapping(UUID.fromString("29841228-bd10-11e9-9cb5-2a2ae2dbcce4"));
        ivanMapping1.setPartnerId("35");
        ivanMapping1.setFullName("Ivan on Facebook");
        ivanMapping1.setAccountId("10");
        ivanMapping1.setCustomer(ivan);
        ivanMapping1.setAvatar("data:image/png;base64,data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAQ5JREFUeNpi/P//PwMlgImBQjDwBrCgCxj1XGfg4OZmYGNnj2FgZCxg+P9/wq+fP5f8+PqV4VyJJnEuAAZsDFBTQZS7mDGIBvGJ9gJI8c9v3wri/OWMX/xgYIj2kzMG8XEZgmHAz+/fbb9/+cIwcdbps4+/MzBMmX36LIgPEicqDP7/+5f+++dPht+/fp55+JWB4dvnTwysbOwmrOzsxAXi148fGUA2gsDrn0ADPn0GsoD4zjYgbYo1wFAw2FRxLQbuyCVndA7+/w+iQXxsakGYBZuz/ry8pvH/8YVbN/q+Mfx/e+vW35fXjIDC14D4B7paRvS8wMjICKJEgJgN2aEgHwHV/iFowNDLCwABBgC9qJ54WqC2JwAAAABJRU5ErkJggg==".getBytes());
        partnerMappingRepository.saveAndFlush(ivanMapping1);

        PartnerMapping ivanMapping2 = new PartnerMapping(UUID.fromString("c9c8eaa4-bd12-11e9-9cb5-2a2ae2dbcce4"));
        ivanMapping2.setPartnerId("33");
        ivanMapping2.setFullName("Ivan on Twitter");
        ivanMapping2.setAccountId("11");
        ivanMapping2.setCustomer(ivan);
        partnerMappingRepository.saveAndFlush(ivanMapping2);

        PartnerMapping mariyaMapping1 = new PartnerMapping();
        mariyaMapping1.setPartnerId("33");
        mariyaMapping1.setFullName("Mariya on Twitter");
        mariyaMapping1.setAccountId("12");
        mariyaMapping1.setCustomer(mariya);
        partnerMappingRepository.saveAndFlush(mariyaMapping1);
    }
}