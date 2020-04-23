package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.CorpsEtat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class CorpsEtatDao {
    
    public void Creer(CorpsEtat corpsEtat){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(corpsEtat);
    }
    
    public CorpsEtat ChercherParId(Long idCorpsEtat) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(CorpsEtat.class, idCorpsEtat); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<CorpsEtat> ListerCorpsEtat() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<CorpsEtat> query = em.createQuery("SELECT c FROM CorpsEtat c", CorpsEtat.class);
        return query.getResultList();
    }
    
    public void Update(CorpsEtat corpsEtat) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(corpsEtat);
    }
    
    public void Remove(CorpsEtat corpsEtat) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(corpsEtat);
    }
}
