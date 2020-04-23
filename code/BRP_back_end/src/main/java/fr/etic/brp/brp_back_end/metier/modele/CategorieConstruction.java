
package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author louisrob
 */
@Entity
public class CategorieConstruction implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idCategorieConstruction;
    private String intituleCategorieConstruction;

    public CategorieConstruction() {
    }

    public CategorieConstruction(String intituleCategorieConstruction) {
        this.intituleCategorieConstruction = intituleCategorieConstruction;
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
}