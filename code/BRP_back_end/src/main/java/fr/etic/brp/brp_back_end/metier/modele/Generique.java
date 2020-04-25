package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author quentinmarc
 */
@Entity
public class Generique extends Descriptif implements Serializable {
    
    public Generique(){
    }
    
    public Generique(String idDescriptif, String nomDescriptif, String description, String courteDescription){
        super(idDescriptif, nomDescriptif, description, courteDescription);
    }

    @Override
    public String toString() {
        return "Generique{" + '}';
    }

}
