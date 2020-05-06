package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *
 * @author quentinmarc
 */
@Entity
public class CorpsEtat implements Serializable {
    
    @Id
    private String idCorpsEtat;
    private String intituleCorpsEtat;
    
    @OneToMany
    private List<Categorie> listCategorie;
    
    public CorpsEtat(){  
    }

    public CorpsEtat(String idCorpsEtat, String intituleCorpsEtat) {
        this.idCorpsEtat = idCorpsEtat;
        this.intituleCorpsEtat = intituleCorpsEtat;
        this.listCategorie = null;
    }
    
    public String getIdCorpsEtat() {
        return idCorpsEtat;
    }

    public void setIdCorpsEtat(String idCorpsEtat) {
        this.idCorpsEtat = idCorpsEtat;
    }

    public String getIntituleCorpsEtat() {
        return intituleCorpsEtat;
    }

    public void setIntituleCorpsEtat(String intituleCorpsEtat) {
        this.intituleCorpsEtat = intituleCorpsEtat;
    }

    public List<Categorie> getListCategorie() {
        return listCategorie;
    }

    public void setListCategorie(List<Categorie> listCategorie) {
        this.listCategorie = listCategorie;
    }

    @Override
    public String toString() {
        return "CorpsEtat{" + "idCorpsEtat=" + idCorpsEtat + ", intituleCorpsEtat=" + intituleCorpsEtat + ", listCategorie=" + listCategorie + '}';
    }  
}
