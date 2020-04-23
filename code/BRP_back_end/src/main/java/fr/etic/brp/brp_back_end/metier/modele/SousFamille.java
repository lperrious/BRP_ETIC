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
public class SousFamille implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSousFamille;
    private String intituleSousFamille;
    
    public SousFamille(){ 
    }

    public SousFamille(String intituleSousFamille) {
        this.intituleSousFamille = intituleSousFamille;
    }

    public Long getIdSousFamille() {
        return idSousFamille;
    }

    public void setIdSousFamille(Long idSousFamille) {
        this.idSousFamille = idSousFamille;
    }

    public String getIntituleSousFamille() {
        return intituleSousFamille;
    }

    public void setIntituleSousFamille(String intituleSousFamille) {
        this.intituleSousFamille = intituleSousFamille;
    }
}
