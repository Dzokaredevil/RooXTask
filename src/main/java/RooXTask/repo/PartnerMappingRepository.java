package RooXTask.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

import RooXTask.model.PartnerMapping;

@RepositoryRestResource
public interface PartnerMappingRepository extends Repository<PartnerMapping, UUID> {

    /**
     * 92/5000
     * Получить партнерское сопоставление по идентификатору. Для не ADMIN пользователи получают только, если он принадлежит текущему зарегистрированному пользователю.
     */
    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.customer.id == principal")
    PartnerMapping findOne(UUID uuid);

    /**
     * Получить список партнеров. Для не ADMIN пользователи получают только, если он принадлежит текущему зарегистрированному пользователю.
     */
    @Query("select pm from PartnerMapping as pm where 1 = ?#{hasRole('ROLE_ADMIN') ? 1 : 0} or pm.customer.id = ?#{principal}")
    Page<PartnerMapping> findAll(Pageable pageable);

    @PreAuthorize("hasRole('ROLE_ADMIN') or #pm.customer.id == principal")
    PartnerMapping save(@P("pm") PartnerMapping pm);

    PartnerMapping saveAndFlush(PartnerMapping pm);

    void delete(UUID uuid);
}
