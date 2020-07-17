package fr.etic.brp.brp_back_end.metier.modele;

import fr.etic.brp.brp_back_end.metier.modele.BasePrixRef;
import fr.etic.brp.brp_back_end.metier.modele.Prestation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-12T11:50:17")
@StaticMetamodel(Ouvrage.class)
public class Ouvrage_ extends Descriptif_ {

    public static volatile SingularAttribute<Ouvrage, String> unite;
    public static volatile ListAttribute<Ouvrage, Prestation> listePrestation;
    public static volatile ListAttribute<Ouvrage, BasePrixRef> listeBasePrixRefOuvrage;

}