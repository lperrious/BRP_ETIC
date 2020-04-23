/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Projet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class ProjetDao {
    
    public void Creer(Projet projet){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(projet);
    }
    
    public Projet ChercherParId(Long idProjet) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Projet.class, idProjet); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Projet> ListerProjets() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Projet> query = em.createQuery("SELECT p FROM Projet p", Projet.class);
        return query.getResultList();
    }
    
    public void Update(Projet projet) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(projet);
    }
    
    public void Remove(Projet projet) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(projet);
    }
}
