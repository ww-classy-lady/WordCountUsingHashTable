//hash table implementation
public class hash{
  private int size; // table size
  private int numItems;
  private Entry[] h;
  private double loadFactor; //numitems/table size
  public hash()
  {
    size = 3; //initially size 3 (odd number size and will guarantee for the first two entries the load factor is less than 1) This size will change (roughly 2*size +1) with rehashing method (in Main java)
    h= new Entry[size]; //new string array
    numItems = 0;
  }
  public hash(int tableSize){
//the load factor for separate chaining must be <1 (num items/table size)
   size = tableSize;
   h= new Entry[size];
   numItems = 0;
  }
  public int getSize(){ //size of the hashtable is the size of the array. Within each array slot is a linked list of entries (if multiple entries have the same hashcode/index)
    return h.length;
  }
  public int getnumItems(){ //search through the array of linked entries to count items
    Entry cur;
    int num=0;
    for(int s=0;s<h.length; s++)
    {
      cur = h[s];
      while(cur!=null)
      {
        num++;
        cur=cur.getNext();
      }
    }
    numItems=num;
    return numItems;
  }
  public void setNum(int number){ //set the number of items to a specified value
    numItems = number;
  }
  public void setArray(Entry[] hey) //set array to parameter entry array
  {
    h = hey;
  }
  public Entry[] getArray(){ //return the array of entries
    return h;
  }
  public double getLF(){ //return the load factor in double 
    loadFactor = (numItems/(double)this.getSize());
    return loadFactor;
  }
  
}