
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjet;
    private String nomProjet;
    private TypeMarche typeMarche;
    private TypeConstruction typeConstruction;
    private TypeLot typeLot;
    private Site site;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePrixRef;
    private Float coeffAdapt;
    
    @ManyToOne
    private CoeffRaccordement coeffRaccordement; 
    @ManyToOne
    private CategorieConstruction categorieConstruction;
    

    public Projet() {
    }

    public Projet(String nomProjet) {
        this.nomProjet = nomProjet;
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

    @Override
    public String toString() {
        return "Projet{" + "idProjet=" + idProjet + ", nomProjet=" + nomProjet + ", typeMarche=" + typeMarche + ", typeConstruction=" + typeConstruction + ", typeLot=" + typeLot + ", site=" + site + ", datePrixRef=" + datePrixRef + ", coeffAdapt=" + coeffAdapt + ", coeffRaccordement=" + coeffRaccordement + ", categorieConstruction=" + categorieConstruction + '}';
    } 
}