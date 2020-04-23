/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class SousCategorieConstructionDao {
    
    public void Creer(SousCategorieConstruction sousCategorieConstruction){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(sousCategorieConstruction);
    }
    
    public SousCategorieConstruction ChercherParId(Long idSousCategorieConstruction) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(SousCategorieConstruction.class, idSousCategorieConstruction); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<SousCategorieConstruction> ListerSousCategorieConstructions() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<SousCategorieConstruction> query = em.createQuery("SELECT s FROM SousCategorieConstruction s", SousCategorieConstruction.class);
        return query.getResultList();
    }
    
    public void Update(SousCategorieConstruction sousCategorieConstruction) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(sousCategorieConstruction);
    }
    
    public void Remove(SousCategorieConstruction sousCategorieConstruction) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(sousCategorieConstruction);
    }
}
