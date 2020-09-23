package fr.etic.brp.brp_back_end.metier.modele;

import fr.etic.brp.brp_back_end.metier.modele.Categorie;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-21T21:54:50")
@StaticMetamodel(Chapitre.class)
public class Chapitre_ { 

    public static volatile SingularAttribute<Chapitre, String> intituleChapitre;
    public static volatile ListAttribute<Chapitre, Categorie> listCategorie;
    public static volatile SingularAttribute<Chapitre, String> idChapitre;

}