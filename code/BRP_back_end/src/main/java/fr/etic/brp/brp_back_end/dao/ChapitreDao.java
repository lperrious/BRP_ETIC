package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Chapitre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class ChapitreDao {
    
    public void Creer(Chapitre chapitre){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(chapitre);
    }
    
    public Chapitre ChercherParId(String idChapitre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Chapitre.class, idChapitre); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Chapitre> ListerChapitre() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Chapitre> query = em.createQuery("SELECT c FROM Chapitre c", Chapitre.class);
        return query.getResultList();
    }
    
    public void Update(Chapitre chapitre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(chapitre);
    }
    
    public void Remove(Chapitre chapitre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(chapitre);
    }
}
