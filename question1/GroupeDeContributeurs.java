package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import question2.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>, Cloneable{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    liste = new ArrayList<Cotisant>();
  }
  
  public void ajouter(Cotisant cotisant){
    cotisant.setParent(this);
      liste.add(cotisant);
  }
  
  
  public int nombreDeCotisants(){
    int nombre = 0;
    for(Cotisant c:liste){
        if(c instanceof Contributeur){
            nombre++;
        }else{
            nombre+=((GroupeDeContributeurs)c).nombreDeCotisants();
        }
    }
    return nombre;
  }
  
  public String toString(){
    String str = new String();
    for(Cotisant c:liste){
        if(c instanceof Contributeur){
            str+=c.toString();
        }else{
            str+=((GroupeDeContributeurs)c).toString();
        }
    }
    return str;
  }
  
  public List<Cotisant> getChildren(){
    return liste;
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
      if(somme < 0||solde()<somme){throw new RuntimeException("Erreur en somme");}
      if(new DebitMaximal().visite(this)<somme)
        throw new SoldeDebiteurException("Erreur en somme");
    for(Cotisant c:liste){
        if(c instanceof Contributeur){
            c.debit(somme);
        }else{
            ((GroupeDeContributeurs)c).debit(somme);
        }
    }
  }
  
  public void credit(int somme){
      if(somme < 0){throw new RuntimeException("Erreur en somme");}
    for(Cotisant c:liste){
        if(c instanceof Contributeur){
            c.credit(somme);
        }else{
            ((GroupeDeContributeurs)c).credit(somme);
        }
    }
  }
  
  public int solde(){
    int solde = 0;
    for(Cotisant c:liste){
        if(c instanceof Contributeur){
            solde+=c.solde();
        }else{
            solde+=((GroupeDeContributeurs)c).solde();
        }
    }
    return solde;
  }
  
  // méthodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  
  public void setState(Cotisant c){
    this.liste = ((GroupeDeContributeurs) c).getChildren();
  }
  
  public Object clone()throws CloneNotSupportedException{
      GroupeDeContributeurs gg = new GroupeDeContributeurs(this.nom);
      for(Cotisant c: this.getChildren()){
          if(c instanceof Contributeur) c= (Contributeur)((Contributeur)c).clone();
          if(c instanceof GroupeDeContributeurs) c= (GroupeDeContributeurs)((GroupeDeContributeurs)c).clone();
          gg.ajouter(c);
      }
      return gg;
  }

}
