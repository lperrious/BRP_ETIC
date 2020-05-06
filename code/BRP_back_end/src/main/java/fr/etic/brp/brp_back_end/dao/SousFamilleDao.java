package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class SousFamilleDao {
    
    public void Creer(SousFamille sousFamille){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(sousFamille);
    }
    
    public SousFamille ChercherParId(String idSousFamille) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(SousFamille.class, idSousFamille); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<SousFamille> ListerSousFamilles() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<SousFamille> query = em.createQuery("SELECT s FROM SousFamille s", SousFamille.class);
        return query.getResultList();
    }
    
    public void Update(SousFamille sousFamille) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(sousFamille);
    }
    
    public void Remove(SousFamille sousFamille) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(sousFamille);
    }
}
