package model.data_structures;

public class ArregloDinamico<T extends Comparable<T>> implements ILista<T> {
	public static final String POSICION_NO_VALIDA = "La posición no es válida";
	public static final String LISTA_VACIA = "La lista está vacía";
	public static final String ELEMENTO_NO_VALIDO = "No es válido el elemento ingresado";
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la
	 * posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T[] elementos;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * 
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico(int max) {
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void addLast(T dato) {
		if (tamanoAct == tamanoMax) { // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for (int i = 0; i < tamanoAct; i++) {
				System.arraycopy(copia, i, elementos, i, copia.length);
			}
		}
		elementos[tamanoAct] = dato;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int size() {
		return tamanoAct;
	}

	public T getElement(int pos) throws PosException, VacioException {
		if (pos < 1) {
			throw new PosException(POSICION_NO_VALIDA);
		}
		if (pos > tamanoMax) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		} else {
			if (elementos[pos - 1] == null) {
				return null;
			} else {
				return elementos[pos - 1];
			}
		}
	}

	public T buscar(T dato) {
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo())
		// definido en Strings.
		T elemento = null;
		boolean ya = false;
		for (int i = 0; i < tamanoAct && !ya; i++) {

			if (elementos[i].compareTo(dato) == 0) {

				elemento = elementos[i];
				ya = true;
			}

		}

		return elemento;

	}

	public T deleteElement(T dato) throws VacioException, NullException {

		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo())
		// definido en Strings.
		T elemento = null;

		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		} else if (dato == null) {
			throw new NullException(ELEMENTO_NO_VALIDO);
		} else {
			boolean ya = false;

			T[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];

			for (int i = 0; i < copia.length && !ya; i++) {

				if (copia[i].compareTo(dato) == 0) {
					for (int j = i; j < copia.length - 1; j++) {
						elementos[j] = copia[j + 1];
					}

					elemento = copia[i];
					ya = true;
					tamanoAct = tamanoAct - 1;
				} else {
					System.arraycopy(copia, i, elementos, i, copia.length);
				}
			}
		}

		return elemento;

	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	@Override
	public void addFirst(T element) {
		if (tamanoAct > 0) {
			if (tamanoAct == tamanoMax) {
				tamanoMax = 2 * tamanoMax;
			}
			T[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];

			elementos[0] = element;
			for (int i = 0; i < tamanoAct; i++) {
				elementos[i + 1] = copia[i];
			}
		} else {
			elementos[0] = element;
		}

	}

	@Override
	public void insertElement(T elemento, int pos) throws PosException, NullException {
		if (pos - 1 > tamanoMax || pos < 1) {
			throw new PosException(POSICION_NO_VALIDA);
		} else {
			if (pos == 1) {
				addFirst(elemento);
			} else if (tamanoAct + 1 == pos) {
				addLast(elemento);
			} else {
				addElement(elemento, pos);
			}

			tamanoAct++;
		}

	}

	public void addElement(T elemento, int pos) {

		if (tamanoAct == tamanoMax) {
			tamanoMax = 2 * tamanoMax;
		}
		T[] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];

		for (int i = 0; i < pos - 1; i++) {
			System.arraycopy(copia, i, elementos, i, copia.length);
		}

		elementos[pos - 1] = elemento;

		for (int i = pos; i < tamanoAct; i++) {
			elementos[i] = copia[i - 1];
		}
	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	@Override
	public T removeFirst() {
		T elemento = elementos[0];

		T[] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];

		for (int i = 0; i < tamanoAct - 1; i++) {
			elementos[i] = copia[i + 1];
		}

		tamanoAct--;
		return elemento;

	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	@Override
	public T removeLast() {
		T elemento = elementos[tamanoAct - 1];
		elementos[tamanoAct - 1] = null;
		tamanoAct--;
		return elemento;
	}

	@Override
	public T deleteElement(int pos) throws PosException, VacioException {
		T elemento = null;

		if (pos > tamanoMax) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (pos < 1) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		} else {
			elemento = elementos[pos];
			if (pos == 1) {
				removeFirst();
			} else if (pos == tamanoAct) {
				removeLast();
			} else {
				T[] copia = elementos;
				elementos = (T[]) new Comparable[tamanoMax];

				for (int i = 0; i < pos - 1; i++) {
					elementos[i] = copia[i];
				}

				for (int i = pos - 1; i < tamanoAct; i++) {
					elementos[i] = copia[i + 1];
				}
				tamanoAct--;
			}
		}

		return elemento;
	}

	@Override
	public T firstElement() throws VacioException {
		T retorno = null;
		if (tamanoAct == 0) {
			throw new VacioException(LISTA_VACIA);
		} else {
			retorno = elementos[0];
		}

		return retorno;
	}

	@Override
	public T lastElement() throws VacioException {
		if (tamanoAct == 0) {
			throw new VacioException(LISTA_VACIA);
		} else {
			return elementos[tamanoAct - 1];
		}

	}

	@Override
	public boolean isEmpty() {
		return tamanoAct < 0;
	}

	@Override
	public int isPresent(T element) throws NullException, VacioException {
		int pos = -1;
		if (element == null) {
			throw new NullException(ELEMENTO_NO_VALIDO);
		} else if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		} else {
			boolean ya = false;
			for (int i = 0; i < tamanoAct && !ya; i++) {
				if (elementos[i].compareTo(element) == 0) {
					pos = i;
					ya = true;
				}

			}
		}

		return pos + 1;
	}

	@Override
	public void exchange(int pos1, int pos2) throws PosException, VacioException {
		if (pos1 > tamanoMax) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (pos2 > tamanoMax) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (pos1 < 1) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (pos2 < 1) {
			throw new PosException(POSICION_NO_VALIDA);
		} else if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		} else if (pos1 != pos2 && tamanoAct > 1) {
			T elemento1 = elementos[pos1 - 1];
			T elemento2 = elementos[pos2 - 1];

			elementos[pos1 - 1] = elemento2;
			elementos[pos2 - 1] = elemento1;
		}

	}

	@Override
	public void changeInfo(int pos, T element) throws PosException, VacioException, NullException {
		if (pos < 1 || pos > tamanoMax) {
			throw new PosException(POSICION_NO_VALIDA);
		}

		else if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		}

		else if (element == null) {
			throw new NullException(ELEMENTO_NO_VALIDO);
		} else {
			elementos[pos - 1] = element;
		}
	}

	public ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException {
		if (isEmpty()) {
			throw new VacioException(LISTA_VACIA);
		} else if (numElementos < 0) {
			throw new PosException("La cantidad de elementos no es válida");
		} else if (numElementos >= size()) {
			return this;
		} else {
			ILista<T> copia = new ArregloDinamico<>(numElementos);

			for (int i = 0; i < numElementos; i++) {
				T elemento = this.getElement(pos + i);
				copia.addLast(elemento); // Agregar elemento a la sublista
			}

			return copia;
		}
	}

	@Override
	public int compareTo(ILista o) {
		return 0;
	}

}
