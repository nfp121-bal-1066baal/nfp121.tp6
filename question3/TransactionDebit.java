package question3;
 
import question1.*;

public class TransactionDebit extends AbstractTransaction{
  private Gardien gardien;
  
  public TransactionDebit(Cotisant cotisant){
        super(cotisant);
        this.gardien = new Gardien();
    }
    
  public void beginTransaction(){
      Cotisant c=null;
      try{
          if(cotisant instanceof Contributeur) c= (Contributeur)((Contributeur)cotisant).clone();
          if(cotisant instanceof GroupeDeContributeurs) c= (GroupeDeContributeurs)((GroupeDeContributeurs)cotisant).clone();
      }
      catch(CloneNotSupportedException e){
          e.printStackTrace();
      }
     gardien.setMemento(new Memento(c));
  }
  
  public void endTransaction(){
  }
  
  public  void rollbackTransaction(){
    gardien.getMemento().setState(cotisant);
    
  }
}