package yetanotherx.peachy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UsefulMethods {
    
    public static ArrayList<String> arrayUniqueMerge(ArrayList<String> ... lists) {
        
        ArrayList<String> outList = new ArrayList<String>();
        for( ArrayList<String> list : lists ) {
            outList.addAll(list);
        }
        
        return new ArrayList<String>(new HashSet<String>(outList));
        
    }
    
    public static HashMap<String, String> arrayUniqueMerge(HashMap<String, String> ... lists) {
        
        HashMap<String, String> outList = new HashMap<String, String>();
        for( HashMap<String, String> list : lists ) {
            outList.putAll(list);
        }
        
        return outList;
        
    }
}
