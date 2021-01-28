package ru.grbi3yh.customers.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grbi3yh.customers.entity.Address;
import ru.grbi3yh.customers.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.isNull;

@Repository
@Transactional
public class AddressRepository {

    private final Class<Address> entityClass = Address.class;

    @PersistenceContext
    protected EntityManager em;

    public Address save(Address entity) {
        if (isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    protected boolean isNew(Address entity) {
        return isNull(entity.getId());
    }

    public void remove(long id) {
        Address entity = em.find(entityClass, id);
        if (isNull(entity)) throw new IllegalArgumentException("Entity is not found");
        em.remove(entity);
    }
}
