package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author louisrob
 */
@Entity
public class Prestation extends Descriptif implements Serializable {
    
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<BasePrixRef> listeBasePrixRefPrestation;

    public Prestation() {
    }

    public Prestation(String idDescriptif, String nomDescriptif, String description, String courteDescription) {
        super(idDescriptif, nomDescriptif, description, courteDescription);
        this.listeBasePrixRefPrestation = null;
    }

    public List<BasePrixRef> getListeBasePrixRefPrestation() {
        return listeBasePrixRefPrestation;
    }

    public void setListeBasePrixRefPrestation(List<BasePrixRef> listeBasePrixRefPrestation) {
        this.listeBasePrixRefPrestation = listeBasePrixRefPrestation;
    }

    @Override
    public String toString() {
        return "Prestation{" + "listeBasePrixRefPrestation=" + listeBasePrixRefPrestation + '}';
    }  
}
