package Entities;

import Entities.Labpartidas;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-15T05:10:16")
@StaticMetamodel(Labusuarios.class)
public class Labusuarios_ { 

    public static volatile SingularAttribute<Labusuarios, String> pass;
    public static volatile CollectionAttribute<Labusuarios, Labpartidas> labpartidasCollection;
    public static volatile SingularAttribute<Labusuarios, String> usuario;
    public static volatile SingularAttribute<Labusuarios, Integer> userid;
    public static volatile SingularAttribute<Labusuarios, String> email;

}