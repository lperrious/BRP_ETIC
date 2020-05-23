package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *
 * @author quentinmarc
 */
@Entity
public class Chapitre implements Serializable {
    
    @Id
    private String idChapitre;
    private String intituleChapitre;
    
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<Categorie> listCategorie;
    
    public Chapitre(){  
    }

    public Chapitre(String idChapitre, String intituleChapitre) {
        this.idChapitre = idChapitre;
        this.intituleChapitre = intituleChapitre;
        this.listCategorie = null;
    }
    
    public String getIdChapitre() {
        return idChapitre;
    }

    public void setIdChapitre(String idChapitre) {
        this.idChapitre = idChapitre;
    }

    public String getIntituleChapitre() {
        return intituleChapitre;
    }

    public void setIntituleChapitre(String intituleChapitre) {
        this.intituleChapitre = intituleChapitre;
    }

    public List<Categorie> getListCategorie() {
        return listCategorie;
    }

    public void setListCategorie(List<Categorie> listCategorie) {
        this.listCategorie = listCategorie;
    }

    @Override
    public String toString() {
        return "Chapitre{" + "idChapitre=" + idChapitre + ", intituleChapitre=" + intituleChapitre + ", listCategorie=" + listCategorie + '}';
    }  
}
