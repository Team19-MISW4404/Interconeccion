package model.data_structures;

public class NodoIndexedMinPQ<K extends Comparable<K>, I extends Comparable<I>, V extends Comparable <V>> extends NodoTS<K, V>
{
	private I llaveIndexacion;
	
	public NodoIndexedMinPQ(K llave, I llaveIndexacion, V valor )
	{
		super(llave, valor);
		this.llaveIndexacion= llaveIndexacion;
	}
	
	public I getIndexedKey()
	{
		return llaveIndexacion;
	}
}

