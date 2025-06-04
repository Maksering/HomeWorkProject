package HwHashMap;

public class HwHashMap<K,V> {
    private MapElement<K,V>[] table;
    private final float loadFactor;
    private int currentSize;

    public HwHashMap(){
        this(16,0.75f);
    }

    public HwHashMap(int sizeOnCreate,float loadFactor){
        this.loadFactor=loadFactor;
        this.table = new MapElement[sizeOnCreate];
    }

    public V get (K key){
        if(key == null){
            return null;
        }

        int keyHash=hash(key);
        int index = keyHash & (table.length-1);

        for(MapElement<K,V> elm=table[index]; elm!=null; elm=elm.nextElement){
            //Key проверяется в начале каждого метода на null. Добавил еще проверку elm.key
            if(elm.key != null && (hash(elm.key) == keyHash && (elm.key==key || key.equals(elm.key)))){
                return elm.value;
            }
        }
        return null;
    }

    public V put(K key, V value){
        if(key==null){
            return null;
        }

        int keyHash=hash(key);
        int index = keyHash & (table.length-1);

        for(MapElement<K,V> elm=table[index]; elm!=null; elm=elm.nextElement){
            if(elm.key != null && (hash(elm.key) == keyHash && (elm.key==key || key.equals(elm.key)))){
                V prevVal=elm.value;
                elm.value= value;
                return prevVal;
            }
        }
        MapElement<K,V> elm=table[index];
        table[index] = new MapElement<>(key,value,elm);
        if(currentSize++>=table.length*loadFactor){
            resize(2*table.length);
        }
        return null;
    }

    public V remove(K key){
        if(key == null){
            return null;
        }

        int keyHash=hash(key);
        int index = keyHash & (table.length-1);

        MapElement<K,V> prevEl=table[index];
        MapElement<K,V> elm=prevEl;

        while (elm!=null){
            MapElement<K,V> nextElm=elm.nextElement;
            if(elm.key != null && (hash(elm.key) == keyHash && (elm.key==key || key.equals(elm.key)))) {
                if(prevEl==elm){
                    table[index]=nextElm;
                }
                else{
                    prevEl.nextElement=nextElm;
                }
                currentSize--;
                return elm.value;
            }
            prevEl=elm;
            elm=nextElm;
        }
        return null;
    }

    private  int hash(Object key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private  void resize(int capacity){
        MapElement<K,V>[] oldTable = table;
        MapElement<K,V>[] newTable = new MapElement[capacity];
        for(int i = 0; i<oldTable.length; i++) {
            MapElement<K, V> elm = oldTable[i];
            while (elm != null) {

                MapElement<K, V> nextEl = elm.nextElement;
                int index = hash(elm.key) & (capacity - 1);
                elm.nextElement = newTable[index];
                newTable[index] = elm;
                elm = nextEl;
            }
        }
        table = newTable;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getMaxSize(){
        return table.length;
    }

    //Node
    private static class MapElement<K,V>{
        K key;
        V value;
        MapElement<K, V> nextElement;
        MapElement(K key, V value, MapElement<K,V> nextElement){
            this.key=key;
            this.value=value;
            this.nextElement=nextElement;
        }
    }
}
