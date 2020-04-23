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
public class Famille implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFamille;
    private String intituleFamille;
    
    @OneToMany
    private SousFamille sousFamille;
    
    public Famille(){ 
    }

    public Famille(String intituleFamille) {
        this.intituleFamille = intituleFamille;
        this.sousFamille = null;
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

    public SousFamille getSousFamille() {
        return sousFamille;
    }

    public void setSousFamille(SousFamille sousFamille) {
        this.sousFamille = sousFamille;
    }

    @Override
    public String toString() {
        return "Famille{" + "idFamille=" + idFamille + ", intituleFamille=" + intituleFamille + ", sousFamille=" + sousFamille + '}';
    }
}
