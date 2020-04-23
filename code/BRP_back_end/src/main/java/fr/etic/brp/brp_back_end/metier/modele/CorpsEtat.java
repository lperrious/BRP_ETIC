package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    public CorpsEtat(){  
    }

    public CorpsEtat(String intituleCorpsEtat) {
        this.intituleCorpsEtat = intituleCorpsEtat;
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
}
