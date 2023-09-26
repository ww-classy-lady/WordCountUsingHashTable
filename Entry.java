//aka nodes with values (implement similar to a linked list node class)
public class Entry{
  private String value;
  private int frequency; 
  private Entry next;
  private String code; // hashcode
  public Entry(String info){
    value = info; //value is the key
    frequency = 1;  //exist as a word
    next = null; //initially dont point to another node/entry so next is null
  }
  public String getKey(){ //get the value of the string(word) from object entry
    return value;
  }
  public int getFreq (){ //get the frequency of the word from object entry
    return frequency;
  }
  public Entry getNext(){ //get the next node
    return next;
  }
  public void setNext(Entry n){ //set the next node to Entry n
    next = n;
  }
  public void setCode (String hashCode) //hahaha not using this
  {
    code = hashCode;
  }
  public void setFreq(int newFreq){ //incrememnt by 1
    frequency = newFreq;
  }
  
}