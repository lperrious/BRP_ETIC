package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.BasePrixRef;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class BasePrixRefDao {
    
    public void Creer(BasePrixRef basePrixRef){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(basePrixRef);
    }
    
    public BasePrixRef ChercherParId(Long idBasePrixRef) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(BasePrixRef.class, idBasePrixRef); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<BasePrixRef> ListerBasePrixRefs() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<BasePrixRef> query = em.createQuery("SELECT b FROM BasePrixRef b", BasePrixRef.class);
        return query.getResultList();
    }
    
    public void Update(BasePrixRef basePrixRef) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(basePrixRef);
    }
    
    public void Remove(BasePrixRef basePrixRef) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(basePrixRef);
    }
}
