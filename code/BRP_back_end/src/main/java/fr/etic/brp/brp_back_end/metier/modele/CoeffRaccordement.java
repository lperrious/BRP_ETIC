
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
public class CoeffRaccordement implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idCoeffRaccordement;
    private String localisation;
    private Double valeur;

    public CoeffRaccordement() {
    }

    public CoeffRaccordement(String localisation, Double valeur) {
        this.localisation = localisation;
        this.valeur = valeur;
    }

    public Long getIdCoeffRaccordement() {
        return idCoeffRaccordement;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    } 

    @Override
    public String toString() {
        return "CoeffRaccordement{" + "idCoeffRaccordement=" + idCoeffRaccordement + ", localisation=" + localisation + ", valeur=" + valeur + '}';
    }
}