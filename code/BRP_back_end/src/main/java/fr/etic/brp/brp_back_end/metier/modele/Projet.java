
package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

enum TypeMarche {
  marchePublic,
  marchePrive;
}

enum TypeConstruction {
  neuf,
  renovation;
}

enum TypeLot {
  lotSepare,
  entrepriseGenerale;
}

enum Site {
  libre,
  occupe;
}

/**
 *
 * @author louisrob
 */
@Entity
public class Projet implements Serializable {
        
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idProjet;
    private String nomProjet;
    private TypeMarche typeMarche;
    private TypeConstruction typeConstruction;
    private TypeLot typeLot;
    private Site site;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePrixRef;
    private Float coeffAdapt;
    private Integer EFF;
    private Integer CA1;
    private Integer CA2;
    private Integer CA3;
    private Integer CA4;
    private Integer CA5;
    private Integer CA6;
    private Integer CA7;
    private Integer CA8;
    private Integer CA9;
    private Integer C81;
    private Integer C82;
    
    @ManyToOne
    private CoeffRaccordement coeffRaccordement; 
    @ManyToOne
    private CategorieConstruction categorieConstruction;
    

    public Projet() {
    }

    public Projet(String nomProjet) {
        this.nomProjet = nomProjet;
        this.CA1 = null;
        this.CA2 = null;
        this.CA3 = null;
        this.CA4 = null;
        this.CA5 = null;
        this.CA6 = null;
        this.CA7 = null;
        this.CA8 = null;
        this.CA9 = null;
        this.C81 = null;
        this.C82 = null;
        this.EFF = null;
        this.categorieConstruction = null;
        this.coeffAdapt = null;
        this.coeffRaccordement = null;
        this.datePrixRef = null;
        this.idProjet = null;
    }

    public CoeffRaccordement getCoeffRaccordement() {
        return coeffRaccordement;
    }

    public void setCoeffRaccordement(CoeffRaccordement coeffRaccordement) {
        this.coeffRaccordement = coeffRaccordement;
    }

    public CategorieConstruction getCategorieConstruction() {
        return categorieConstruction;
    }

    public void setCategorieConstruction(CategorieConstruction categorieConstruction) {
        this.categorieConstruction = categorieConstruction;
    }

    public Long getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Long idProjet) {
        this.idProjet = idProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public TypeMarche getTypeMarche() {
        return typeMarche;
    }

    public void setTypeMarche(TypeMarche typeMarche) {
        this.typeMarche = typeMarche;
    }

    public TypeConstruction getTypeConstruction() {
        return typeConstruction;
    }

    public void setTypeConstruction(TypeConstruction typeConstruction) {
        this.typeConstruction = typeConstruction;
    }

    public TypeLot getTypeLot() {
        return typeLot;
    }

    public void setTypeLot(TypeLot typeLot) {
        this.typeLot = typeLot;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Date getDatePrixRef() {
        return datePrixRef;
    }

    public void setDatePrixRef(Date datePrixRef) {
        this.datePrixRef = datePrixRef;
    }

    public Float getCoeffAdapt() {
        return coeffAdapt;
    }

    public void setCoeffAdapt(Float coeffAdapt) {
        this.coeffAdapt = coeffAdapt;
    }

    public Integer getEFF() {
        return EFF;
    }

    public void setEFF(Integer EFF) {
        this.EFF = EFF;
    }

    public Integer getCA1() {
        return CA1;
    }

    public void setCA1(Integer CA1) {
        this.CA1 = CA1;
    }

    public Integer getCA2() {
        return CA2;
    }

    public void setCA2(Integer CA2) {
        this.CA2 = CA2;
    }

    public Integer getCA3() {
        return CA3;
    }

    public void setCA3(Integer CA3) {
        this.CA3 = CA3;
    }

    public Integer getCA4() {
        return CA4;
    }

    public void setCA4(Integer CA4) {
        this.CA4 = CA4;
    }

    public Integer getCA5() {
        return CA5;
    }

    public void setCA5(Integer CA5) {
        this.CA5 = CA5;
    }

    public Integer getCA6() {
        return CA6;
    }

    public void setCA6(Integer CA6) {
        this.CA6 = CA6;
    }

    public Integer getCA7() {
        return CA7;
    }

    public void setCA7(Integer CA7) {
        this.CA7 = CA7;
    }

    public Integer getCA8() {
        return CA8;
    }

    public void setCA8(Integer CA8) {
        this.CA8 = CA8;
    }

    public Integer getCA9() {
        return CA9;
    }

    public void setCA9(Integer CA9) {
        this.CA9 = CA9;
    }

    public Integer getC81() {
        return C81;
    }

    public void setC81(Integer C81) {
        this.C81 = C81;
    }

    public Integer getC82() {
        return C82;
    }

    public void setC82(Integer C82) {
        this.C82 = C82;
    }

    @Override
    public String toString() {
        return "Projet{" + "idProjet=" + idProjet + ", nomProjet=" + nomProjet + ", typeMarche=" + typeMarche + ", typeConstruction=" + typeConstruction + ", typeLot=" + typeLot + ", site=" + site + ", datePrixRef=" + datePrixRef + ", coeffAdapt=" + coeffAdapt + ", EFF=" + EFF + ", CA1=" + CA1 + ", CA2=" + CA2 + ", CA3=" + CA3 + ", CA4=" + CA4 + ", CA5=" + CA5 + ", CA6=" + CA6 + ", CA7=" + CA7 + ", CA8=" + CA8 + ", CA9=" + CA9 + ", C81=" + C81 + ", C82=" + C82 + ", coeffRaccordement=" + coeffRaccordement + ", categorieConstruction=" + categorieConstruction + '}';
    }
}