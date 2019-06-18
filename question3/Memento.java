package question3;

import question1.*;
import java.util.*;

public class Memento {
     // Note : Un usage du patron Memento, 
     //        d’un premier visiteur pour la sauvegarde et 
     //        d’un second pour la restitution du composite, 
     //        représentent une solution possible. 
     
     private Cotisant c;
     
     public Memento(Cotisant c) {
       this.c=c;
     }

     public void setState(Cotisant c) {
       if(c instanceof Contributeur) ((Contributeur)c).setState(this.c);
       if(c instanceof GroupeDeContributeurs) ((GroupeDeContributeurs)c).setState(this.c);
     }
    
    }