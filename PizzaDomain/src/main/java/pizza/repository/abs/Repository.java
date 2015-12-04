package pizza.repository.abs;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pizza.rules.Globals;

/**
 * Created by alex on 11/9/15.
 * <p>
 * An abstract repository which manages PersistentEntity's.
 *
 * @param <T> the generic type.
 */
@Getter
@Setter
public abstract class Repository<T> {

    private static final Logger LOGGER = LogManager.getLogger(Repository.class.getName());

    @PersistenceContext(unitName = Globals.PERSISTENCE_UNIT)
    private EntityManager em;

    /**
     * Retrieve all items.
     */
    public abstract List<T> getAll();

    /**
     * Add an item to the repository and persist.
     *
     * @param item the item to save
     */
    @Transactional
    public void save(final T item) {
        em.persist(item);
        em.flush();
    }

    /**
     * Update an item in the repository.
     *
     * @param item the item to update.
     */
    @Transactional
    public void update(final T item) {
        em.merge(item);
    }

    /**
     * Retrieve all items from the persistence unit.
     *
     * @param clazz the class type of item
     * @return a list of items, empty if none found
     */
    protected List<T> getAll(final Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> t = cq.from(clazz);
        cq.select(t);
        TypedQuery<T> q = em.createQuery(cq);
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            LOGGER.warn(e);
            return new ArrayList<>();
        }
    }

    public abstract T findById(final Long idToFind);

    /**
     * Find an item by its id attribute.
     *
     * @param clazz    the item's class.
     * @param idToFind the id to find
     * @return the item, or null if not found
     */
    protected T findById(final Class<T> clazz, final Long idToFind) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        cq.where(
                cb.equal(root.get(Globals.PERSISTENCE_ID_IDENTIFIER), idToFind)
        );
        TypedQuery<T> q = em.createQuery(cq);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn(e);
            return null;
        }
    }
}

