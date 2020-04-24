package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class CoeffRaccordementDao {
    public void Creer(CoeffRaccordement coeffRaccordement){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(coeffRaccordement);
    }
    
    public CoeffRaccordement ChercherParId(Long idCoeffRaccordement) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(CoeffRaccordement.class, idCoeffRaccordement); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<CoeffRaccordement> ListerCoeffRaccordements() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<CoeffRaccordement> query = em.createQuery("SELECT c FROM CoeffRaccordement c", CoeffRaccordement.class);
        return query.getResultList();
    }
    
    public void Update(CoeffRaccordement coeffRaccordement) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(coeffRaccordement);
    }
    
    public void Remove(CoeffRaccordement coeffRaccordement) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(coeffRaccordement);
    }
}
