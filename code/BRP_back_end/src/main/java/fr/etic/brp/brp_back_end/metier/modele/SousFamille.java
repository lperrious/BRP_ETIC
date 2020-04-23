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
public class SousFamille implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSousFamille;
    private String intituleSousFamille;
    
    @OneToMany
    private Descriptif descriptif;
    
    public SousFamille(){ 
    }

    public SousFamille(String intituleSousFamille) {
        this.intituleSousFamille = intituleSousFamille;
        this.descriptif = null;
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

    public Descriptif getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(Descriptif descriptif) {
        this.descriptif = descriptif;
    }

    @Override
    public String toString() {
        return "SousFamille{" + "idSousFamille=" + idSousFamille + ", intituleSousFamille=" + intituleSousFamille + ", descriptif=" + descriptif + '}';
    }
}
