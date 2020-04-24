
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
 * @author louisrob
 */
@Entity
public class CategorieConstruction implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idCategorieConstruction;
    private String intituleCategorieConstruction;
    
    @OneToMany
    private List<SousCategorieConstruction> listSousCategorieConstruction;
    
    

    public CategorieConstruction() {
    }

    public CategorieConstruction(String intituleCategorieConstruction) {
        this.intituleCategorieConstruction = intituleCategorieConstruction;
        this.listSousCategorieConstruction = null;
    }

    public List<SousCategorieConstruction> getListSousCategorieConstruction() {
        return listSousCategorieConstruction;
    }

    public void setListSousCategorieConstruction(List<SousCategorieConstruction> listSousCategorieConstruction) {
        this.listSousCategorieConstruction = listSousCategorieConstruction;
    }

    public Long getIdCategorieConstruction() {
        return idCategorieConstruction;
    }

    public void setIdCategorieConstruction(Long idCategorieConstruction) {
        this.idCategorieConstruction = idCategorieConstruction;
    }

    public String getIntituleCategorieConstruction() {
        return intituleCategorieConstruction;
    }

    public void setIntituleCategorieConstruction(String intituleCategorieConstruction) {
        this.intituleCategorieConstruction = intituleCategorieConstruction;
    }

    @Override
    public String toString() {
        return "CategorieConstruction{" + "idCategorieConstruction=" + idCategorieConstruction + ", intituleCategorieConstruction=" + intituleCategorieConstruction + ", listSousCategorieConstruction=" + listSousCategorieConstruction + '}';
    }
}