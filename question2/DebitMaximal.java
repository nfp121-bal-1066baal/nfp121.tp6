package question2;
import java.util.*;
import question1.*;

public class DebitMaximal implements Visiteur<Integer>{

  public Integer visite(Contributeur c){
    
    return c.solde();
  }
  
  public Integer visite(GroupeDeContributeurs g){
    int res = 0;
    List<Cotisant> l = g.getChildren();
    for(int i=0;i<l.size();i++){
        if(i==0){
            Cotisant c = l.get(0);
            if(c instanceof Contributeur){

                res=c.solde();
            }
            else{
                visite((GroupeDeContributeurs)c);
            }
            continue;
        }
        if(l.get(i) instanceof Contributeur){
            Cotisant c= l.get(i);
            if(res>c.solde())
                res=c.solde();
        }
        else{
            visite((GroupeDeContributeurs)l.get(i));
        }
    
    }
    return res ;
  }
}