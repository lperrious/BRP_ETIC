package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Famille;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class FamilleDao {
    
    public void Creer(Famille famille){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(famille);
    }
    
    public Famille ChercherParId(String idFamille) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Famille.class, idFamille); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Famille> ListerFamilles() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Famille> query = em.createQuery("SELECT f FROM Famille f", Famille.class);
        return query.getResultList();
    }
    
    public void Update(Famille famille) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(famille);
    }
    
    public void Remove(Famille famille) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(famille);
    }
}
