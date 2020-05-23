package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author quentinmarc
 */
@Entity
public class Ouvrage extends Descriptif implements Serializable {
    
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<Prestation> listePrestation;
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<BasePrixRef> listeBasePrixRefOuvrage;
    
    public Ouvrage(){ 
    }
    
    public Ouvrage(String idDescriptif, String nomDescriptif, String description, String courteDescription){
        super(idDescriptif, nomDescriptif, description, courteDescription);
        this.listeBasePrixRefOuvrage = null;
        this.listePrestation = null;
    }

    public List<Prestation> getListePrestation() {
        return listePrestation;
    }

    public void setListePrestation(List<Prestation> listePrestation) {
        this.listePrestation = listePrestation;
    }

    public List<BasePrixRef> getListeBasePrixRefOuvrage() {
        return listeBasePrixRefOuvrage;
    }

    public void setListeBasePrixRefOuvrage(List<BasePrixRef> listeBasePrixRefOuvrage) {
        this.listeBasePrixRefOuvrage = listeBasePrixRefOuvrage;
    }

    @Override
    public String toString() {
        return "Ouvrage{" + "listePrestation=" + listePrestation + ", listeBasePrixRefOuvrage=" + listeBasePrixRefOuvrage + '}';
    }    
}