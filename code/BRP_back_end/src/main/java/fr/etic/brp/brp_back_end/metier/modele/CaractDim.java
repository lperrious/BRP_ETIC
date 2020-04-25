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
public class CaractDim implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaractDim;
    private String codeCaractDim;
    private Double valeur;

    public CaractDim() {
    }

    public CaractDim(String codeCaractDim, Double valeur) {
        this.codeCaractDim = codeCaractDim;
        this.valeur = valeur;
    }

    public Long getIdCaracteristiqueDimensionnelle() {
        return idCaractDim;
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
        return "CaracteristiqueDimensionnelle{" + "idCaracteristiqueDimensionnelle=" + idCaractDim + ", codeCaractDim=" + codeCaractDim + ", valeur=" + valeur + '}';
    }
}
