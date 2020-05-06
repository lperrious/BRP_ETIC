package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class CategorieDao {
    
    public void Creer(Categorie categorie){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(categorie);
    }
    
    public Categorie ChercherParId(String idCategorie) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Categorie.class, idCategorie); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Categorie> ListerCategories() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c", Categorie.class);
        return query.getResultList();
    }
    
    public void Update(Categorie categorie) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(categorie);
    }
    
    public void Remove(Categorie categorie) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(categorie);
    }
}
