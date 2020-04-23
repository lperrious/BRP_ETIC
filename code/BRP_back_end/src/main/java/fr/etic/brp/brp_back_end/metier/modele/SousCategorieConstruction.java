
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
public class SousCategorieConstruction implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idSousCategorieConstruction;
    private String intituleSousCategorieConstruction;

    public SousCategorieConstruction() {
    }

    public SousCategorieConstruction(String intituleSousCategorieConstruction) {
        this.intituleSousCategorieConstruction = intituleSousCategorieConstruction;
    }

    public Long getIdSousCategorieConstruction() {
        return idSousCategorieConstruction;
    }

    public void setIdSousCategorieConstruction(Long idSousCategorieConstruction) {
        this.idSousCategorieConstruction = idSousCategorieConstruction;
    }

    public String getIntituleSousCategorieConstruction() {
        return intituleSousCategorieConstruction;
    }

    public void setIntituleSousCategorieConstruction(String intituleSousCategorieConstruction) {
        this.intituleSousCategorieConstruction = intituleSousCategorieConstruction;
    }
}