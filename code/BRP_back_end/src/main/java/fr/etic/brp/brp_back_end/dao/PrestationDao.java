package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class PrestationDao {
    
    public void Creer(Prestation prestation){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(prestation);
    }
    
    public Prestation ChercherParId(String idPrestation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Prestation.class, idPrestation); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Prestation> ListerPrestations() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Prestation> query = em.createQuery("SELECT p FROM Prestation p", Prestation.class);
        return query.getResultList();
    }
    
    public void Update(Prestation prestation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(prestation);
    }
    
    public void Remove(Prestation prestation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(prestation);
    }
}
