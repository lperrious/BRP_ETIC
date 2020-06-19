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
    private Long nbPrixRef; //Permet d'identifier les prixRef d'un(e) ouvrage/prestation
    private Integer annee;
    private Double BT;
    private Double qteInf;
    private Double qteSup;
    private Double prixUnitaire;

    public BasePrixRef() {
    }

    public BasePrixRef(Integer annee, Long nbPrixRef, Double BT, Double qteInf, Double qteSup, Double prixUnitaire) {
        this.annee = annee;
        this.nbPrixRef = nbPrixRef;
        this.BT = BT;
        this.qteInf = qteInf;
        this.qteSup = qteSup;
        this.prixUnitaire = prixUnitaire;
    }

    public Long getIdBasePrixRef() {
        return idBasePrixRef;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setNbPrixRef(Long nbPrixRef) {
        this.nbPrixRef = nbPrixRef;
    }

    public Long getNbPrixRef() {
        return nbPrixRef;
    }

    public void setAnnee(Integer annee) {
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

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "BasePrixRef{" + "idBasePrixRef=" + idBasePrixRef + ", nbPrixRef=" + nbPrixRef + ", annee=" + annee + ", BT=" + BT + ", qteInf=" + qteInf + ", qteSup=" + qteSup + ", prixUnitaire=" + prixUnitaire + '}';
    }
}
