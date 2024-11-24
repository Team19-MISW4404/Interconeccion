package model.data_structures;

public class MinPQIndexada<K extends Comparable<K>, I extends Comparable<I>, V extends Comparable<V>> extends MinPQ<K, V>

{
    public MinPQIndexada(int inicial) {
		super(inicial);
	}

	public void insert(K key, I indexedKey, V value) {
        try {
			arbol.insertElement(new NodoIndexedMinPQ<>(key, indexedKey, value), arbol.size() +1);
            
		} catch (PosException | NullException e) {
			System.err.println("Se produjo un error: " + e.getMessage());
            throw new RuntimeException("Error manejado en el catch", e);
		}
        tamano += 1;
        swim(arbol, tamano);
    }
    
    public void changePriority(I indexedKey, K newKey, V value) 
    {
    	try
    	{
    		int posicionBuscado = -1;
            for(int i = 1; i <= arbol.size() && posicionBuscado == -1; i++) {
                NodoIndexedMinPQ<K, I, V> actual = (NodoIndexedMinPQ<K, I, V>) arbol.getElement(i);
                if(actual.getIndexedKey().compareTo(indexedKey) == 0) {
                    posicionBuscado = i;
                }
            }
            
            
            if(newKey.compareTo(arbol.getElement(posicionBuscado).getKey()) > 0) {
                arbol.getElement(posicionBuscado).setKey(newKey);
                arbol.getElement(posicionBuscado).setValue(value);
                sink(arbol, posicionBuscado);
            }else if(newKey.compareTo(arbol.getElement(posicionBuscado).getKey()) < 0) {
                arbol.getElement(posicionBuscado).setKey(newKey);
                arbol.getElement(posicionBuscado).setValue(value);
                swim(arbol, posicionBuscado);
            }
    	}
    	catch (PosException | VacioException e) 
		{
			e.printStackTrace();
		}
        
    }
}
