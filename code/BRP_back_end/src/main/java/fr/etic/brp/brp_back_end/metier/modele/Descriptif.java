package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

/**
 *
 * @author quentinmarc
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED) //Définit la stratégie d'héritage dans la BD
public abstract class Descriptif implements Serializable {
    
    @Id
    protected String idDescriptif;
    protected String nomDescriptif;
    @Lob
    @Column
    protected String description;
    protected String courteDescription;
    
    public Descriptif(){ 
    }

    public Descriptif(String idDescriptif, String nomDescriptif, String description, String courteDescription) {
        this.idDescriptif = idDescriptif;
        this.nomDescriptif = nomDescriptif;
        this.description = description;
        this.courteDescription = courteDescription;
    }

    public String getIdDescriptif() {
        return idDescriptif;
    }

    public String getNomDescriptif() {
        return nomDescriptif;
    }

    public void setNomDescriptif(String nomDescriptif) {
        this.nomDescriptif = nomDescriptif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourteDescription() {
        return courteDescription;
    }

    public void setCourteDescription(String courteDescription) {
        this.courteDescription = courteDescription;
    }

    @Override
    public String toString() {
        return "Descriptif{" + "idDescriptif=" + idDescriptif + ", nomDescriptif=" + nomDescriptif + ", description=" + description + ", courteDescription=" + courteDescription + '}';
    }
}
