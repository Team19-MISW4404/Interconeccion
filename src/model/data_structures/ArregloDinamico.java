package model.data_structures;

public class ArregloDinamico<T extends Comparable<T>> implements ILista<T> {
	public static final String POSICION_NO_VALIDA = "La posición no es válida";
	public static final String LISTA_VACIA = "La lista está vacía";
	public static final String ELEMENTO_NO_VALIDO = "No es válido el elemento ingresado";

	private int tamanoMax;
	private int tamanoAct;
	private T[] elementos;

	public ArregloDinamico(int max) {
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void addLast(T dato) {
		if (tamanoAct == tamanoMax) { // caso de arreglo lleno (aumentar tamaño)
			tamanoMax = 2 * tamanoMax;
			T[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			System.arraycopy(copia, 0, elementos, 0, tamanoAct); // Efficient array copy
		}
		elementos[tamanoAct++] = dato; // Add element at the end and increase tamanoAct
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int size() {
		return tamanoAct;
	}

	public T getElement(int pos) throws PosException, VacioException {
		if (pos < 1 || pos > tamanoAct) {
			throw new PosException(POSICION_NO_VALIDA);
		}
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		return elementos[pos - 1];
	}

	public T buscar(T dato) {
		for (int i = 0; i < tamanoAct; i++) {
			if (elementos[i].compareTo(dato) == 0) {
				return elementos[i];
			}
		}
		return null;
	}

	public T deleteElement(T dato) throws VacioException, NullException {
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		if (dato == null) {
			throw new NullException(ELEMENTO_NO_VALIDO);
		}
		T elemento = null;
		for (int i = 0; i < tamanoAct; i++) {
			if (elementos[i].compareTo(dato) == 0) {
				elemento = elementos[i];
				System.arraycopy(elementos, i + 1, elementos, i, tamanoAct - i - 1); // Efficient removal
				tamanoAct--;
				break;
			}
		}
		return elemento;
	}

	@Override
	public void addFirst(T element) {
		if (tamanoAct == tamanoMax) {
			tamanoMax = 2 * tamanoMax;
		}
		T[] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];
		System.arraycopy(copia, 0, elementos, 1, tamanoAct); // Shift elements one position to the right
		elementos[0] = element;
		tamanoAct++;
	}

	@Override
	public void insertElement(T elemento, int pos) throws PosException, NullException {
		if (pos < 1 || pos > tamanoAct + 1) {
			throw new PosException(POSICION_NO_VALIDA);
		}
		if (pos == 1) {
			addFirst(elemento);
		} else if (pos == tamanoAct + 1) {
			addLast(elemento);
		} else {
			addElement(elemento, pos);
		}
	}

	public void addElement(T elemento, int pos) {
		if (tamanoAct == tamanoMax) {
			tamanoMax = 2 * tamanoMax;
		}
		T[] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];
		System.arraycopy(copia, 0, elementos, 0, pos - 1);
		elementos[pos - 1] = elemento;
		System.arraycopy(copia, pos - 1, elementos, pos, tamanoAct - pos + 1);
		tamanoAct++;
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			return null; // Return null if empty
		}
		T elemento = elementos[0];
		System.arraycopy(elementos, 1, elementos, 0, tamanoAct - 1); // Efficient shift
		tamanoAct--;
		return elemento;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			return null; // Return null if empty
		}
		T elemento = elementos[--tamanoAct]; // Decrease tamanoAct before accessing the element
		elementos[tamanoAct] = null; // Clear the last element reference
		return elemento;
	}

	@Override
	public T deleteElement(int pos) throws PosException, VacioException {
		if (pos < 1 || pos > tamanoAct) {
			throw new PosException(POSICION_NO_VALIDA);
		}
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		T elemento = elementos[pos - 1];
		if (pos == 1) {
			return removeFirst();
		} else if (pos == tamanoAct) {
			return removeLast();
		} else {
			System.arraycopy(elementos, pos, elementos, pos - 1, tamanoAct - pos); // Efficient removal
			tamanoAct--;
			return elemento;
		}
	}

	@Override
	public T firstElement() throws VacioException {
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		return elementos[0];
	}

	@Override
	public T lastElement() throws VacioException {
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		return elementos[tamanoAct - 1];
	}

	@Override
	public boolean isEmpty() {
		return tamanoAct == 0;
	}

	@Override
	public int isPresent(T element) throws NullException, VacioException {
		if (element == null) {
			throw new NullException(ELEMENTO_NO_VALIDO);
		}
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		for (int i = 0; i < tamanoAct; i++) {
			if (elementos[i].compareTo(element) == 0) {
				return i + 1;
			}
		}
		return -1;
	}

	@Override
	public void exchange(int pos1, int pos2) throws PosException, VacioException {
		if (pos1 < 1 || pos2 < 1 || pos1 > tamanoAct || pos2 > tamanoAct) {
			throw new PosException(POSICION_NO_VALIDA);
		}
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		if (pos1 != pos2) {
			T temp = elementos[pos1 - 1];
			elementos[pos1 - 1] = elementos[pos2 - 1];
			elementos[pos2 - 1] = temp;
		}
	}

	@Override
	public void changeInfo(int pos, T element) throws PosException, VacioException, NullException {
		if (pos < 1 || pos > tamanoAct) {
			throw new PosException(POSICION_NO_VALIDA);
		}
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		if (element == null) {
			throw new NullException(ELEMENTO_NO_VALIDO);
		}
		elementos[pos - 1] = element;
	}

	public ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException {
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}
		if (numElementos < 0 || pos < 1 || pos + numElementos - 1 > tamanoAct) {
			throw new PosException("La cantidad de elementos no es válida");
		}
		ILista<T> sublista = new ArregloDinamico<>(numElementos);
		for (int i = 0; i < numElementos; i++) {
			sublista.addLast(this.getElement(pos + i));
		}
		return sublista;
	}

	@Override
	public int compareTo(ILista o) {
		return 0; // To be implemented based on the actual requirements.
	}
}
