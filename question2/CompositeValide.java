package question2;

import question1.*;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class CompositeValide implements Visiteur<Boolean>{
  // Le solde de chaque contributeur doit être supérieur ou égal à 0 
  // et il n’existe pas de groupe n’ayant pas de contributeurs.
  
  public Boolean visite(Contributeur c){
      if(c.solde()>=0)
        return true;
      return false;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = false;
    if(g.getChildren().size()==0)
        return res;
    else{
        for(Cotisant c:g.getChildren()){
            if(c instanceof  Contributeur)
                res=visite((Contributeur)c);
            else
                res=visite((GroupeDeContributeurs)c);
        }
    }
    return res ;
  }
}