package question2;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class DebitMaximal implements Visiteur<Integer>{

  public Integer visite(Contributeur c){
    
    return c.solde();
  }
  
  public Integer visite(GroupeDeContributeurs g){
    int res = 0;
    for(int i=0;i<g.getChildren().size();i++){
        if(i==0){
            if(g.getChildren().get(0) instanceof Contributeur){

                res=g.getChildren().get(0).solde();
            }
            else{
                visite((GroupeDeContributeurs)g.getChildren().get(0));
            }
        }
        if(g.getChildren().get(i) instanceof Contributeur){
            if(res>g.getChildren().get(i).solde())
                res=g.getChildren().get(i).solde();
        }
        else{
            visite((GroupeDeContributeurs)g.getChildren().get(i));
        }
    
    }
    return res ;
  }
}