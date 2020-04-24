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
 * @author quentinmarc
 */
@Entity
public class Famille implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFamille;
    private String intituleFamille;
    
    @OneToMany
    private List<SousFamille> listSousFamille;
    
    public Famille(){ 
    }

    public Famille(String intituleFamille) {
        this.intituleFamille = intituleFamille;
        this.listSousFamille = null;
    }

    public Long getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Long idFamille) {
        this.idFamille = idFamille;
    }

    public String getIntituleFamille() {
        return intituleFamille;
    }

    public void setIntituleFamille(String intituleFamille) {
        this.intituleFamille = intituleFamille;
    }

    public List<SousFamille> getListSousFamille() {
        return listSousFamille;
    }

    public void setListSousFamille(List<SousFamille> listSousFamille) {
        this.listSousFamille = listSousFamille;
    }

    @Override
    public String toString() {
        return "Famille{" + "idFamille=" + idFamille + ", intituleFamille=" + intituleFamille + ", listSousFamille=" + listSousFamille + '}';
    }
}
