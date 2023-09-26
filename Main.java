//Important Note: for the load factor, it is equal to number of items divided by the size. If an item have frequency of four, it still count as a single item in this file because it is still two items stored in the hash table it's just that the frequency if the item is >1 (or 4 to be specific)
//Wendy Wu
//Purpose: write and use the method wordCount that takes a string as an input and prints all the words from the input and their occurences. This is done with Hash Table with separate chaining that is constructed in the hash.java and Entry.java files. (method not case sensitive)
class Main {
  //hashtable is an array of linked list (made up of entry)
  public void wordCount(String k)
  {
    Entry one; //the pointer to the entry we want to insert
    int sum=0; //just a variable to keep track of the total number of items (not super important)
    String h = k.toLowerCase(); //so that the method is not case sensitive. Uniform lowercase
    String hi[]= h.split("\\P{Alpha}+"); //split the string into a string array with all the words
    hash old = new hash(); //first set to the numitems size, will rehash
    Entry arr[] = old.getArray();
    Entry current = arr[0];
    Entry prev = current;
    String oneVal=""; //starting string?
    int index=0; //starting index 
    //search process
    boolean stop = false;
    for(int i = 0; i< hi.length; i++) //for all the strings in the string array
    {
    //  for(int a = 0; a< (old.getArray()).length; a++) //search through hash table array
   //   {
        arr=old.getArray();
        one = new Entry(hi[i]); //new entry with string value at index i of string array
        oneVal = one.getKey();
        index = Math.abs(oneVal.hashCode())%((old.getSize()));
        if(arr[index]==null) //if spot is clean and open
        {
          arr[index] = one;
          sum++;
          old.setNum(old.getnumItems()+1);
        }
        else
        {
          current = arr[index]; //current entry
          prev = current;
          while((current!=null)&& (stop == false)) //while not at the end and haven't found a value which needs to increment frequency
          {
            if((current.getKey()).equals(oneVal)) //if the strings are the same
            {
              current.setFreq((current.getFreq())+1); //increment frequency by one
             // System.out.println(current.getFreq());
              stop = true; //exist search since finished insert
             
            }
            else
            {
              prev = current;
              current = current.getNext(); //move on 
            }
          }
          if(current == null) //reached the end of list
          {
          prev.setNext(one); //add at the end of the linked list
          sum++;
          old.setNum(old.getnumItems()+1);
          }
        }

   //   }
      stop = false; //so that next word start with stop = false
      old.setArray(arr);  //re point old's array to this modified array arr
      //end of search/insert process
      //time to call expansion and rehash using load factor
      old = rehashing(old); 
      //end of expansion/rehashing 
    }
    //time to print out things
    Entry [] print = old.getArray(); //the main array in the hash table
    Entry it;
    int total =0;
    for(int p = 0; p< print.length; p++)
    {
      it = print[p];
      while(it!=null) //go through each linked list of entries in an array slot
      {
      System.out.println("String " + total + " : " + it.getKey());
      System.out.println("Frequency: "+ (it.getFreq()));
      System.out.println("/////////////line break////////////");
      total++; //items
      it = it.getNext();
      }
    }
    if(h=="")
    {
      System.out.println("String is empty. No words exist in parameter. Try again");
    }
    //just using the print lines to check specific values/methods to make sure it works properly
 /* System.out.println(print.length);
  System.out.println(total);
  System.out.println(old.getnumItems());
  System.out.println(sum);
  System.out.println(old.getLF()); */
  }
  public hash rehashing(hash oldTable){ //helper method
  //if load factor >= 1 then rehash, else keep the same table
    //helper method for rehashing
    Entry[] old = oldTable.getArray();
    Entry now;
    boolean halt = false;
    Entry current = old[0];
    Entry on;
    Entry prev = current;
    String nowKey = "";
   // String[] cur = new String[oldTable.getSize()*2+1];
    boolean found = false;
    int j=0;
    hash newTable = new hash(oldTable.getSize()*2+1); //times two and plus one to make size odd
    Entry[] newOne = newTable.getArray();
   // Entry[] old = oldTable.getArray();
    int dex=0;
    if(oldTable.getLF()<(1.0)&&((oldTable.getnumItems()+1)/(oldTable.getSize()) <1.0))
    {//if the Loadfactor is less than 1 and adding one more item will still be less than 1 then return oldtable. No rehashing/expansion is needed
      return oldTable;
    }
    else //have to expand and rehash
    {
      for(int g= 0; g<oldTable.getSize(); g++) //as long as the new table array is there
      {
        now = old[g];
        if(now!=null)
        {
        nowKey = now.getKey();
        dex = Math.abs((nowKey.hashCode())%(newTable.getSize()));
        if(newOne[dex]==null)
        {
          newOne[dex] = now;
          newTable.setNum(newTable.getnumItems()+1); //increment items in the newTable (new hash table)
        }
        else
        {
          current = newOne[dex]; //pointer to the current entry
          prev = current;
          while((current!=null)&&(found==false))
          {
          if((current.getKey()).equals(nowKey))
          {
            current.setFreq((current.getFreq())+1); //incrememt frequency
            //same number of items its just that the frequency incremented
            found = true;
          }
          else
          {
            prev = current;
            current = current.getNext();
          }
          }
          if(current==null) //reached the end of linkedlist and will add a new items to the tail
          {
            prev.setNext(now);
            newTable.setNum(newTable.getnumItems()+1);
          }
          //reached the end of linkedlist
        }
        }
      }

    return newTable;
    }
  }
  public static void main(String[] args) {
    //testing/calling in the main method
    ///PLEASE TEST THE METHOD HERE
    Main hi = new Main();
   // String p = "dont fail midterm dont please uwu aha dont";
    //String p = "hi there, I hope you are doing well. Thanks friend. I hope you are doing well.";
   // String p = "la la la le la";
   String p ="Csds cSds csDs csdS CSDS csds "; //test example for not case sensitive 
  //String p = "."; // not going to print since the split method won't turn it into anything so i cant do anything about it
  // String p="";
    hi.wordCount(p);
   /* String p1[]=p.split("\\P{Alpha}+");
    for(int h =0; h<p1.length; h++){
      System.out.println(p1[h]);
      System.out.println(Math.abs(p1[h].hashCode())%p1.length);
    }*/


  }
}