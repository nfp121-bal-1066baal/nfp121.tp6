package question2;

import question1.*;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class CompositeValide implements Visiteur<Boolean>{
  // Le solde de chaque contributeur doit �tre sup�rieur ou �gal � 0 
  // et il n�existe pas de groupe n�ayant pas de contributeurs.
  
  public Boolean visite(Contributeur c){
      if(c.solde()>=0)
        return true;
      return false;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = true;
    if(g.getChildren().size()==0)
        res=false;
    else{
        for(Cotisant c:g.getChildren()){
            if(c instanceof  Contributeur){
                
                res=visite((Contributeur)c);
                if(!res)
                    break;
            }
            else{
                
                res=visite((GroupeDeContributeurs)c);
                if(!res)
                    break;
            }
        }
    }
    return res ;
  }
}