package fr.etic.brp.brp_back_end.metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-23T10:43:44")
@StaticMetamodel(Operateur.class)
public class Operateur_ { 

    public static volatile SingularAttribute<Operateur, Integer> salt;
    public static volatile SingularAttribute<Operateur, String> mail;
    public static volatile SingularAttribute<Operateur, String> mdp;
    public static volatile SingularAttribute<Operateur, Boolean> admin;
    public static volatile SingularAttribute<Operateur, Long> idOperateur;
    public static volatile SingularAttribute<Operateur, String> nom;
    public static volatile SingularAttribute<Operateur, String> token;

}