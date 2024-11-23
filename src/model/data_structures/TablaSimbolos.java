package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos<K, V> {

	private ILista<NodoTS<K, V>> listaNodos;

	public TablaSimbolos() {
		listaNodos = new ArregloDinamico<NodoTS<K, V>>(1);
	}

	public void put(K key, V value) {
		NodoTS<K, V> agregar = new NodoTS<K, V>(key, value);
		try {
			listaNodos.insertElement(agregar, size() + 1);
		} catch (PosException | NullException e) {
			e.printStackTrace();
		}
	}

	@Override
	public V get(K key) {

		int i = 1;
		int f = keySet().size();
		while (i <= f) {
			int m = (i + f) / 2;

			try {
				if (keySet().getElement(m).compareTo(key) == 0) {
					return listaNodos.getElement(m).getValue();
				} else if (keySet().getElement(m).compareTo(key) > 0) {
					f = m - 1;
				} else {
					i = m + 1;
				}
			} catch (PosException | VacioException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public V remove(K key) {
		V eliminado1 = null;

		try {
			Object obj = get(key);

			if (obj instanceof NodoTS<?, ?>) {
				@SuppressWarnings("unchecked")
				NodoTS<K, V> nodo = (NodoTS<K, V>) obj;

				int pos = listaNodos.isPresent(nodo);

				eliminado1 = listaNodos.getElement(pos).getValue();
				listaNodos.deleteElement(pos);
			} else {
				throw new IllegalArgumentException("El objeto recuperado no es de tipo NodoTS<K, V>");
			}
		} catch (VacioException | NullException | PosException e) {
			e.printStackTrace();
		}

		return eliminado1;
	}

	@Override
	public boolean contains(K key) {
		boolean respuesta = false;

		try {
			Object obj = get(key);

			if (obj instanceof NodoTS<?, ?>) {
				@SuppressWarnings("unchecked")
				NodoTS<K, V> nodo = (NodoTS<K, V>) obj;

				int pos = listaNodos.isPresent(nodo);

				if (pos >= 0) {
					respuesta = true;
				}
			} else {
				throw new IllegalArgumentException("El objeto recuperado no es de tipo NodoTS<K, V>");
			}
		} catch (VacioException | NullException | PosException e) {
			e.printStackTrace();
		}

		return respuesta;
	}

	@Override
	public boolean isEmpty() {
		return listaNodos.isEmpty();
	}

	@Override
	public int size() {
		return listaNodos.size();
	}

	@Override
	public ILista<K> keySet() {
		ILista<K> lista = new ArregloDinamico<K>(1);
		for (int i = 1; i <= size(); i++) {
			try {
				lista.insertElement(listaNodos.getElement(i).getKey(), lista.size() + 1);
			} catch (PosException | NullException | VacioException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ILista<V> valueSet() {
		ILista<V> lista = new ArregloDinamico<V>(1);
		for (int i = 1; i <= size(); i++) {
			try {
				lista.insertElement(listaNodos.getElement(i).getValue(), lista.size() + 1);
			} catch (PosException | VacioException | NullException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	public String toString() {
		return "Cantidad de duplas: " + size();
	}

	public ILista<NodoTS<K, V>> darListNodos() {
		return listaNodos;
	}

	@Override
	public ILista<NodoTS<K, V>> darListaNodos() {
		return null;
	}

	@Override
	public int hash(K key) {
		return 0;
	}
}
