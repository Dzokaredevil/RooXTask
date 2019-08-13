package RooXTask.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

import RooXTask.model.Customer;

/**
 * Этот репозиторий предоставляет методы REST только read для Customers.
 */
@RepositoryRestResource
public interface CustomerRepository extends Repository<Customer, UUID> {

    /**
     * Получить клиента по id. Для не ADMIN пользователей получают только, если он обладает правами.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or #uuid == principal")
    Customer findOne(@P("uuid") UUID uuid);

    /**
     * Получить список клиентов. Для не ADMIN пользователей получают только, если он обладает правами.
     */
    @Query("select c from Customer as c where 1 = ?#{hasRole('ROLE_ADMIN') ? 1 : 0} or c.id = ?#{principal}")
    Page<Customer> findAll(Pageable pageable);

    @RestResource(exported = false)
    <S extends Customer> S save(S entity);

}