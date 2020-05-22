package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Descriptif;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class DescriptifDao {
    
    public void Creer(Descriptif descriptif){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(descriptif);
    }
    
    public Descriptif ChercherParId(String idDescriptif) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Descriptif.class, idDescriptif); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Descriptif> ListerDescriptifs() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Descriptif> query = em.createQuery("SELECT d FROM Descriptif d", Descriptif.class);
        return query.getResultList();
    }
    
    public List<Descriptif> compterDescriptifsSuppr(String idDescriptif) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Descriptif> query = em.createQuery("SELECT d FROM Descriptif d WHERE d.idDescriptif LIKE '"+idDescriptif+"%'", Descriptif.class);
        return query.getResultList();
    }
    
    public void Update(Descriptif descriptif) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(descriptif);
    }
    
    public void Remove(Descriptif descriptif) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(descriptif);
    }
}
