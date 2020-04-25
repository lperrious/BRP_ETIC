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
public class BasePrixRef implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBasePrixRef;
    private String annee;
    private Double BT;
    private Double qteInf;
    private Double qteSup;
    private String unite;
    private Double prixUnitaire;

    public BasePrixRef() {
    }

    public BasePrixRef(String annee, Double BT, Double qteInf, Double qteSup, String unite, Double prixUnitaire) {
        this.annee = annee;
        this.BT = BT;
        this.qteInf = qteInf;
        this.qteSup = qteSup;
        this.unite = unite;
        this.prixUnitaire = prixUnitaire;
    }

    public Long getIdBasePrixRef() {
        return idBasePrixRef;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Double getBT() {
        return BT;
    }

    public void setBT(Double BT) {
        this.BT = BT;
    }

    public Double getQteInf() {
        return qteInf;
    }

    public void setQteInf(Double qteInf) {
        this.qteInf = qteInf;
    }

    public Double getQteSup() {
        return qteSup;
    }

    public void setQteSup(Double qteSup) {
        this.qteSup = qteSup;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "BasePrixRef{" + "idBasePrixRef=" + idBasePrixRef + ", annee=" + annee + ", BT=" + BT + ", qteInf=" + qteInf + ", qteSup=" + qteSup + ", unite=" + unite + ", prixUnitaire=" + prixUnitaire + '}';
    }  
}
