/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.Operateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class OperateurDao {
    
    public void Creer(Operateur operateur){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(operateur);
    }
    
    public Operateur ChercherParId(Long idOperateur) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Operateur.class, idOperateur); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<Operateur> ListerOperateurs() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Operateur> query = em.createQuery("SELECT o FROM Operateur o", Operateur.class);
        return query.getResultList();
    }
    
    public Operateur ChercherParMail(String mailOperateur)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Operateur> query = em.createQuery("SELECT o FROM Operateur o WHERE o.mail = :mail", Operateur.class);
        query.setParameter("mail", mailOperateur); //Correspond au param ":mail" dans la requête
        List<Operateur> operateurs = query.getResultList();
        Operateur result = null;
        if(!operateurs.isEmpty())
        {
            result = operateurs.get(0); //premier de la liste
        }
        return result;
    }
    
    public Integer RecupererSalt(String mailOperateur)
    {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Query query = em.createQuery("SELECT o.salt FROM Operateur o WHERE o.mail = :mail");
        query.setParameter("mail", mailOperateur); //Correspond au param ":mail" dans la requête
        Integer result = (Integer)query.getSingleResult();
        return result;
    }
     
    public void Update(Operateur operateur) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(operateur);
    }
    
    public void Remove(Operateur operateur) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(operateur);
    }
}
