package fr.etic.brp.brp_back_end.metier.modele;

import fr.etic.brp.brp_back_end.metier.modele.CaractDim;
import fr.etic.brp.brp_back_end.metier.modele.SousCategorieConstruction;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-22T22:30:54")
@StaticMetamodel(CategorieConstruction.class)
public class CategorieConstruction_ { 

    public static volatile ListAttribute<CategorieConstruction, SousCategorieConstruction> listeSousCategorieConstruction;
    public static volatile ListAttribute<CategorieConstruction, CaractDim> listeCaractDim;
    public static volatile SingularAttribute<CategorieConstruction, String> intituleCategorieConstruction;
    public static volatile SingularAttribute<CategorieConstruction, String> codeCategorieConstruction;

}