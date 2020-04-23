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
public class Categorie implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategorie;
    private String intituleCategorie;
    
    @OneToMany
    private Famille famille;
    
    public Categorie(){ 
    }

    public Categorie(String intituleCategorie) {
        this.intituleCategorie = intituleCategorie;
        this.famille = null;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getIntituleCategorie() {
        return intituleCategorie;
    }

    public void setIntituleCategorie(String intituleCategorie) {
        this.intituleCategorie = intituleCategorie;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCategorie=" + idCategorie + ", intituleCategorie=" + intituleCategorie + ", famille=" + famille + '}';
    }
}