
package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author louisrob
 */
@Entity
public class CategorieConstruction implements Serializable {
    
    @Id
    private String codeCategorieConstruction;
    private String intituleCategorieConstruction;
    
    @OneToMany
    private List<SousCategorieConstruction> listeSousCategorieConstruction;
    @OneToMany
    private List<CaractDim> listeCaractDim;
       

    public CategorieConstruction() {
    }

    public CategorieConstruction(String intituleCategorieConstruction, String codeCategorieConstruction) {
        this.intituleCategorieConstruction = intituleCategorieConstruction;
        this.codeCategorieConstruction = codeCategorieConstruction;
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

    public List<CaractDim> getListeCaractDim() {
        return listeCaractDim;
    }

    public void setListeCaractDim(List<CaractDim> listeCaractDim) {
        this.listeCaractDim = listeCaractDim;
    }

    @Override
    public String toString() {
        return "CategorieConstruction{" + "intituleCategorieConstruction=" + intituleCategorieConstruction + ", codeCategorieConstruction=" + codeCategorieConstruction + ", listeSousCategorieConstruction=" + listeSousCategorieConstruction + ", listeCaractDim=" + listeCaractDim + '}';
    }
}