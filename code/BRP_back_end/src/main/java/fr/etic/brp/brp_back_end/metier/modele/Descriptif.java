package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author quentinmarc
 */
@Entity
public abstract class Descriptif implements Serializable {
    
    @Id
    protected String idDescriptif;
    protected String nomDescriptif;
    protected String description;
    protected String courteDescription;
    protected double prix;
    
    public Descriptif(){ 
    }

    public Descriptif(String idDescriptif, String nomDescriptif, String description, String courteDescription, double prix) {
        this.idDescriptif = idDescriptif;
        this.nomDescriptif = nomDescriptif;
        this.description = description;
        this.courteDescription = courteDescription;
        this.prix = prix;
    }

    public String getIdDescriptif() {
        return idDescriptif;
    }

    public void setIdDescriptif(String idDescriptif) {
        this.idDescriptif = idDescriptif;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
