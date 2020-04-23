package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCorpsEtat;
    private String intituleCorpsEtat;
    
    @OneToMany
    private Categorie categorie;
    
    public CorpsEtat(){  
    }

    public CorpsEtat(String intituleCorpsEtat) {
        this.intituleCorpsEtat = intituleCorpsEtat;
        this.categorie = null;
    }

    public Long getIdCorpsEtat() {
        return idCorpsEtat;
    }

    public void setIdCorpsEtat(Long idCorpsEtat) {
        this.idCorpsEtat = idCorpsEtat;
    }

    public String getIntituleCorpsEtat() {
        return intituleCorpsEtat;
    }

    public void setIntituleCorpsEtat(String intituleCorpsEtat) {
        this.intituleCorpsEtat = intituleCorpsEtat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "CorpsEtat{" + "idCorpsEtat=" + idCorpsEtat + ", intituleCorpsEtat=" + intituleCorpsEtat + ", categorie=" + categorie + '}';
    }
    
    
}
