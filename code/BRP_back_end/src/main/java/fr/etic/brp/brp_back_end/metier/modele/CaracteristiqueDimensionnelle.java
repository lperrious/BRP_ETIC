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
public class CaracteristiqueDimensionnelle implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaracteristiqueDimensionnelle;
    private String codeCaractDim;
    private Double valeur;

    public CaracteristiqueDimensionnelle() {
    }

    public CaracteristiqueDimensionnelle(String codeCaractDim, Double valeur) {
        this.codeCaractDim = codeCaractDim;
        this.valeur = valeur;
    }

    public Long getIdCaracteristiqueDimensionnelle() {
        return idCaracteristiqueDimensionnelle;
    }

    public String getCodeCaractDim() {
        return codeCaractDim;
    }

    public void setCodeCaractDim(String codeCaractDim) {
        this.codeCaractDim = codeCaractDim;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "CaracteristiqueDimensionnelle{" + "idCaracteristiqueDimensionnelle=" + idCaracteristiqueDimensionnelle + ", codeCaractDim=" + codeCaractDim + ", valeur=" + valeur + '}';
    }
}
