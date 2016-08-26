package datastructure;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class HashMapCustom<K, V> extends HashMap<K, V> {

	public V getOrDefault( Object key, V defaultValue ) {
		
		Iterator<Entry<K,V>> i = entrySet().iterator();
		
        if (!( key == null ) ) {           
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
        return defaultValue;
		
	}

}
