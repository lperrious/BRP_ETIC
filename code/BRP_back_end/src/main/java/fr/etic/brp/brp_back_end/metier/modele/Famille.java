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
public class Famille implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFamille;
    private String intituleFamille;
    
    public Famille(){ 
    }

    public Famille(String intituleFamille) {
        this.intituleFamille = intituleFamille;
    }

    public Long getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Long idFamille) {
        this.idFamille = idFamille;
    }

    public String getIntituleFamille() {
        return intituleFamille;
    }

    public void setIntituleFamille(String intituleFamille) {
        this.intituleFamille = intituleFamille;
    }
}
