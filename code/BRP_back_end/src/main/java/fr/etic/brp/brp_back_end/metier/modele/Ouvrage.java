package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author quentinmarc
 */
@Entity
public class Ouvrage extends Descriptif implements Serializable {
    
    private String unite;
    
    public Ouvrage(){ 
    }
    
    public Ouvrage(String idDescriptif, String nomDescriptif, String description, String courteDescription, double prix, String unite){
        super(idDescriptif, nomDescriptif, description, courteDescription, prix);
        this.unite = unite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}