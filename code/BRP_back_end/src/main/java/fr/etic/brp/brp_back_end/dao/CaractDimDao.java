package fr.etic.brp.brp_back_end.dao;

import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author louisrob
 */
public class CaractDimDao {
    
    public void Creer(CaractDim caractDim){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(caractDim);
    }
    
    public CaractDim ChercherParId(Long idCaractDim) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(CaractDim.class, idCaractDim); // renvoie null si l'identifiant n'existe pas
    }
    
    public List<CaractDim> ListerCaractDims() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<CaractDim> query = em.createQuery("SELECT c FROM CaractDim c", CaractDim.class);
        return query.getResultList();
    }
    
    public void Update(CaractDim caractDim) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(caractDim);
    }
    
    public void Remove(CaractDim caractDim) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.remove(caractDim);
    }
}
