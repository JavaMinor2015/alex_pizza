package pizza.repository.abs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/9/15.
 */
@Getter
@Setter
public abstract class Repository<T> {

    @PersistenceContext(unitName = "PizzaPersist")
    private EntityManager em;

    @Getter(AccessLevel.PROTECTED)
    private List<T> itemList = new ArrayList<>();

    /**
     * Retrieve all items.
     */
    public abstract List<T> getAll();

    /**
     * Persist all items in this repository.
     */
    public void save() {
        itemList.forEach(getEm()::persist);
        getEm().flush();
    }

    /**
     * Add an item to this repository.
     * <p>
     * Note: call {@link #save()} to persist added items.
     *
     * @param item the item to add.
     */
    public void add(final T item) {
        itemList.add(item);
    }

    /**
     * Retrieve all items from the persistence unit.
     *
     * @param clazz the class type of item
     * @return a list of items, empty if none found
     */
    protected List<T> getAll(final Class<T> clazz) {
        List<T> response;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> t = cq.from(clazz);
        cq.select(t);
        TypedQuery<T> q = em.createQuery(cq);
        response = q.getResultList();
        return response;
    }

    protected T findById(final Class<T> clazz, final Long idToFind) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        cb.equal(root.get("id"), idToFind);
        cq.select(root);

        TypedQuery<T> q = em.createQuery(cq);
        q.setMaxResults(1); // TODO look into why more than one result will be returned otherwise, ID is unique
        return q.getSingleResult();
    }
}
