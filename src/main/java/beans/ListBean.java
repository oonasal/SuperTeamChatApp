package beans;

import java.util.ArrayList;
import java.util.List;

public class ListBean {
    
    public List<String> getItems() {
        List<String> list = new ArrayList<>();
        
        list.add("Liyue");
        list.add("Milda");
        list.add("Leena");
        list.add("Maria");
        list.add("Oona");
        
        return list;
    }
}
