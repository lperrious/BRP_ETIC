package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class CategorieConstructionDao {
    
    public void Creer(CategorieConstruction categorieConstruction){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(categorieConstruction);
    }
    
    public CategorieConstruction ChercherParId(String idCategorieConstruction) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(CategorieConstruction.class, idCategorieConstruction); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<CategorieConstruction> ListerCategorieConstructions() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<CategorieConstruction> query = em.createQuery("SELECT c FROM CategorieConstruction c", CategorieConstruction.class);
        return query.getResultList();
    }
    
    public void Update(CategorieConstruction categorieConstruction) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(categorieConstruction);
    }
    
    public void Remove(CategorieConstruction categorieConstruction) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(categorieConstruction);
    }
}
