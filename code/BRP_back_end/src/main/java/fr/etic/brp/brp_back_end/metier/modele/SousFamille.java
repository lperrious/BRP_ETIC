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
public class SousFamille implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousFamille;
    private String intituleSousFamille;
    
    @OneToMany
    private List<Descriptif> listDescriptif;
    
    public SousFamille(){ 
    }

    public SousFamille(String intituleSousFamille) {
        this.intituleSousFamille = intituleSousFamille;
        this.listDescriptif = null;
    }

    public Long getIdSousFamille() {
        return idSousFamille;
    }

    public String getIntituleSousFamille() {
        return intituleSousFamille;
    }

    public void setIntituleSousFamille(String intituleSousFamille) {
        this.intituleSousFamille = intituleSousFamille;
    }

    public List<Descriptif> getListDescriptif() {
        return listDescriptif;
    }

    public void setListDescriptif(List<Descriptif> listDescriptif) {
        this.listDescriptif = listDescriptif;
    }

    @Override
    public String toString() {
        return "SousFamille{" + "idSousFamille=" + idSousFamille + ", intituleSousFamille=" + intituleSousFamille + ", listDescriptif=" + listDescriptif + '}';
    }
}
