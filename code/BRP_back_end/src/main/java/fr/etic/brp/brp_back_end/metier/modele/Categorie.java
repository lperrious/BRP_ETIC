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
public class Categorie implements Serializable {
    
    @Id
    private String idCategorie;
    private String intituleCategorie;
    
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<Famille> listeFamille;
    
    public Categorie(){ 
    }

    public Categorie(String idCategorie, String intituleCategorie) {
        this.idCategorie = idCategorie;
        this.intituleCategorie = intituleCategorie;
        this.listeFamille = null;
    }
    
    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getIntituleCategorie() {
        return intituleCategorie;
    }

    public void setIntituleCategorie(String intituleCategorie) {
        this.intituleCategorie = intituleCategorie;
    }

    public List<Famille> getListeFamille() {
        return listeFamille;
    }

    public void setListeFamille(List<Famille> listeFamille) {
        this.listeFamille = listeFamille;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCategorie=" + idCategorie + ", intituleCategorie=" + intituleCategorie + ", listeFamille=" + listeFamille + '}';
    }
}