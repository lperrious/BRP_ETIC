package fr.etic.brp.brp_back_end.metier.modele;

import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.CategorieConstruction;
import fr.etic.brp.brp_back_end.metier.modele.CoeffRaccordement;
import fr.etic.brp.brp_back_end.metier.modele.Projet.Site;
import fr.etic.brp.brp_back_end.metier.modele.Projet.TypeConstruction;
import fr.etic.brp.brp_back_end.metier.modele.Projet.TypeLot;
import fr.etic.brp.brp_back_end.metier.modele.Projet.TypeMarche;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-23T16:42:52")
@StaticMetamodel(Projet.class)
public class Projet_ { 

    public static volatile SingularAttribute<Projet, Date> datePrixRef;
    public static volatile SingularAttribute<Projet, String> refBRP;
    public static volatile SingularAttribute<Projet, CategorieConstruction> categorieConstruction;
    public static volatile SingularAttribute<Projet, Long> idProjet;
    public static volatile SingularAttribute<Projet, SousCategorieConstruction> sousCategorieConstructionSelection;
    public static volatile SingularAttribute<Projet, TypeConstruction> typeConstruction;
    public static volatile SingularAttribute<Projet, TypeLot> typeLot;
    public static volatile SingularAttribute<Projet, Site> site;
    public static volatile SingularAttribute<Projet, CaractDim> caractDimSelection;
    public static volatile SingularAttribute<Projet, TypeMarche> typeMarche;
    public static volatile SingularAttribute<Projet, String> nomProjet;
    public static volatile SingularAttribute<Projet, CoeffRaccordement> coeffRaccordement;
    public static volatile SingularAttribute<Projet, Float> coeffAdapt;

}