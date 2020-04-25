
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
    private String codeCategorieConstruction;
    
    @OneToMany
    private List<SousCategorieConstruction> listeSousCategorieConstruction;
    @OneToMany
    private List<CaracteristiqueDimensionnelle> listeCaracteristiqueDimensionnelle;
       

    public CategorieConstruction() {
    }

    public CategorieConstruction(String intituleCategorieConstruction, String codeCategorieConstruction) {
        this.intituleCategorieConstruction = intituleCategorieConstruction;
        this.codeCategorieConstruction = codeCategorieConstruction;
    }

    public Long getIdCategorieConstruction() {
        return idCategorieConstruction;
    }

    public String getIntituleCategorieConstruction() {
        return intituleCategorieConstruction;
    }

    public void setIntituleCategorieConstruction(String intituleCategorieConstruction) {
        this.intituleCategorieConstruction = intituleCategorieConstruction;
    }

    public String getCodeCategorieConstruction() {
        return codeCategorieConstruction;
    }

    public void setCodeCategorieConstruction(String codeCategorieConstruction) {
        this.codeCategorieConstruction = codeCategorieConstruction;
    }

    public List<SousCategorieConstruction> getListeSousCategorieConstruction() {
        return listeSousCategorieConstruction;
    }

    public void setListeSousCategorieConstruction(List<SousCategorieConstruction> listeSousCategorieConstruction) {
        this.listeSousCategorieConstruction = listeSousCategorieConstruction;
    }

    public List<CaracteristiqueDimensionnelle> getListeCaracteristiqueDimensionelle() {
        return listeCaracteristiqueDimensionnelle;
    }

    public void setListeCaracteristiqueDimensionelle(List<CaracteristiqueDimensionnelle> listeCaracteristiqueDimensionelle) {
        this.listeCaracteristiqueDimensionnelle = listeCaracteristiqueDimensionelle;
    }

    @Override
    public String toString() {
        return "CategorieConstruction{" + "idCategorieConstruction=" + idCategorieConstruction + ", intituleCategorieConstruction=" + intituleCategorieConstruction + ", codeCategorieConstruction=" + codeCategorieConstruction + ", listeSousCategorieConstruction=" + listeSousCategorieConstruction + ", listeCaracteristiqueDimensionelle=" + listeCaracteristiqueDimensionnelle + '}';
    }
}