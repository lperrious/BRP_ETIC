package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
    private String idSousFamille;
    private String intituleSousFamille;
    
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<Descriptif> listDescriptif;
    
    public SousFamille(){ 
    }

    public SousFamille(String idSousFamille, String intituleSousFamille) {
        this.idSousFamille = idSousFamille;
        this.intituleSousFamille = intituleSousFamille;
        this.listDescriptif = null;
    }

    public String getIdSousFamille() {
        return idSousFamille;
    }

    public void setIdSousFamille(String idSousFamille) {
        this.idSousFamille = idSousFamille;
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
