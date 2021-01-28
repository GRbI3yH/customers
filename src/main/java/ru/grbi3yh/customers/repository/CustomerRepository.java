package ru.grbi3yh.customers.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grbi3yh.customers.dto.CustomerSearchCriteria;
import ru.grbi3yh.customers.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;

import static java.util.Objects.isNull;

@Repository
@Transactional
public class CustomerRepository {

    private final Class<Customer> entityClass = Customer.class;

    @PersistenceContext
    protected EntityManager em;

    public Customer save(Customer entity) {
        if (isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public Customer getById(long id) {
        return em.find(entityClass, id);
    }

    public void remove(long id) {
        Customer entity = em.find(entityClass, id);
        if (isNull(entity)) throw new IllegalArgumentException("Entity is not found");
        em.remove(entity);
    }

    protected boolean isNew(Customer entity) {
        return isNull(entity.getId());
    }

    public List<Customer> getCustomersBy(CustomerSearchCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root).where(getPredicate(criteria, cb, root));
        return em.createQuery(cq).getResultList();
    }

    private Predicate getPredicate(CustomerSearchCriteria criteria, CriteriaBuilder cb, Root<Customer> root){
        Predicate predicateStart = cb.like(root.get("firstName"), criteria.firstName);
        Predicate predicateEnd = cb.like(root.get("lastName"), criteria.lastName);
        return cb.and(predicateStart, predicateEnd);
    }

    private String partialCoincidence(String str){
        return "%"+str+"%";
    }

}
