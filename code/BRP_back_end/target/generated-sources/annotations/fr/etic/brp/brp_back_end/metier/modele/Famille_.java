package fr.etic.brp.brp_back_end.metier.modele;

import fr.etic.brp.brp_back_end.metier.modele.SousFamille;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-10T15:15:18")
@StaticMetamodel(Famille.class)
public class Famille_ { 

    public static volatile SingularAttribute<Famille, String> idFamille;
    public static volatile SingularAttribute<Famille, String> intituleFamille;
    public static volatile ListAttribute<Famille, SousFamille> listSousFamille;

}