package model.data_structures;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaEncadenada<T extends Comparable<T>> implements ILista<T> {
	private static final String INVALID_ELEMENT_MESSAGE = "No es válido el elemento ingresado";
	private static final String INVALID_POCITION_MESSAGE = "La posición no es válida";
	private static final String LIST_VACIA_MESSAGE = "La lista está vacía";

	private Nodo<T> first;

	private int size;

	private Nodo<T> last;

	public ListaEncadenada() {
		first = null;
		last = null;
		size = 0;
	}

	public ListaEncadenada(T element) {
		first = new Nodo<T>(element);
		last = first;
		size = 1;
	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	public void addFirst(T element) {
		Nodo<T> actual = new Nodo<>(element);
		actual.setNext(first);
		first = actual;
	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	public void addLast(T element) {
		Nodo<T> actual = new Nodo<>(element);
		last.setNext(actual);
		last = actual;
		actual.setNext(null);

	}

	public void addLastCola(T element) throws NullException {

		if (element == null) {
			throw new NullException(INVALID_ELEMENT_MESSAGE);
		}

		else {
			if (first == null) {
				Nodo<T> actual = new Nodo<>(element);
				last = actual;
				first = actual;
			} else {
				Nodo<T> actual = new Nodo<>(element);
				last.setNext(actual);
				last = actual;
				actual.setNext(null);
			}
			size++;
		}
	}

	public void insertElement(T elemento, int pos) throws PosException, NullException {
		Nodo<T> nuevo = new Nodo<>(elemento);

		if (pos < 1 || pos - 1 > size) {
			throw new PosException(INVALID_POCITION_MESSAGE);
		} else if (elemento == null) {
			throw new NullException(INVALID_ELEMENT_MESSAGE);
		}

		else {
			if (isEmpty()) {
				first = nuevo;
				last = first;
			} else if (pos == 1) {
				this.addFirst(elemento);
			} else if (pos == size + 1) {
				this.addLast(elemento);
			} else {
				Nodo<T> actual = first;
				for (int i = 0; i < pos - 2; i++) {
					actual = actual.getNext();
				}
				nuevo.setNext(actual.getNext());
				actual.setNext(nuevo);
			}
		}
		size++;
	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	public T removeFirst() throws VacioException {
		T primero = firstElement();
		if (first != null) {
			first = first.getNext();
		}

		return primero;

	}

	// Siempre se llama a insert o a delete primero, esos métodos manejan los casos
	// de que el elemento sea null,
	// isEmpty o que la posición no sea válida
	public T removeLast() {
		Nodo<T> penultimo = first;
		while (penultimo.getNext().getNext() != null) {
			penultimo = penultimo.getNext();
		}
		Nodo<T> ultimo = penultimo.getNext();

		penultimo.disconnectNext(penultimo);
		last = penultimo;

		return ultimo.getInfo();

	}

	public T removeLastPila() throws VacioException {
		Nodo<T> ultimo = null;
		if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);

		} else if (first.getNext() != null) {
			if (first.getNext().getNext() != null) {
				Nodo<T> penultimo = first;

				while (penultimo.getNext().getNext() != null) {
					penultimo = penultimo.getNext();
				}
				ultimo = penultimo.getNext();

				penultimo.disconnectNext(penultimo);
				last = penultimo;

				size--;
			} else {
				Nodo<T> penultimo = first;
				ultimo = penultimo.getNext();
				penultimo.disconnectNext(penultimo);
				last = penultimo;
				size--;

			}

		} else {
			ultimo = first;
			first = null;
		}

		return ultimo.getInfo();

	}

	public T deleteElement(int pos) throws PosException, VacioException {
		// Validar posición
		validatePosition(pos);

		T retorno;

		if (pos == 1) {
			retorno = removeFirst();
		} else if (pos == size) {
			retorno = removeLast();
		} else {
			retorno = removeIntermediateElement(pos);
		}

		size--;

		return retorno;
	}

	private void validatePosition(int pos) throws PosException, VacioException {
		if (pos < 1 || pos > size) {
			throw new PosException(INVALID_POCITION_MESSAGE);
		}
		if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		}
	}

	// Método auxiliar para eliminar un elemento intermedio
	private T removeIntermediateElement(int pos) {

		Nodo<T> actual = first;
		T retorno = null;

		if (actual.getNext() != null) {
			Nodo<T> anterior = null;
			try {
				while (actual.getNext() != null && !actual.getInfo().equals(getElement(pos - 1))) {
					anterior = actual;
					actual = actual.getNext();
				}
			} catch (Exception e) {

				Logger logger = Logger.getLogger(getClass().getName());
				logger.log(Level.SEVERE, "NullPointerException occurred: " + e.getMessage(), e);

			}
			retorno = actual.getInfo();
			if (anterior != null) {
				anterior.disconnectNext(anterior);
			}
		} else {
			retorno = actual.getInfo();

		}
		return retorno;

	}

	public T firstElement() throws VacioException {
		if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		} else {
			return first.getInfo();
		}
	}

	public T lastElement() {
		if (isEmpty()) {
			return null;
		} else {
			return last.getInfo();
		}

	}

	public T getElement(int pos) throws PosException, VacioException {
		if (pos < 1 || pos > size) {
			throw new PosException(INVALID_POCITION_MESSAGE);
		} else if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		} else {
			Nodo<T> actual = first;

			for (int i = 0; i < pos - 1; i++) {
				actual = actual.getNext();
			}
			return actual.getInfo();
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int isPresent(T element) throws VacioException, NullException, PosException {
		int pos = -1;
		if (element == null) {
			throw new NullException(INVALID_ELEMENT_MESSAGE);
		} else if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		} else {
			boolean end = false;
			for (int i = 0; i < size && !end; i++) {
				if (getElement(i).equals(element)) {
					pos = i;
					end = true;
				}
			}
		}

		return pos + 1;
	}

	public void exchange(int pos1, int pos2) throws PosException, VacioException {
		if (pos1 > size || pos2 > size || pos1 < 1 || pos2 < 1) {
			throw new PosException(INVALID_POCITION_MESSAGE);
		} else if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		} else if (pos1 != pos2 && size > 1) {

			Nodo<T> actual1 = first;

			while (actual1.getNext() != null && !actual1.getInfo().equals(getElement(pos1))) {
				actual1 = actual1.getNext();
			}

			Nodo<T> actual2 = first;

			while (actual2.getNext() != null && !actual2.getInfo().equals(getElement(pos2))) {
				actual2 = actual2.getNext();
			}

			Nodo<T> cambiado = actual1;
			actual1.change(actual2.getInfo());
			actual2.change(cambiado.getInfo());

		}
	}

	public void changeInfo(int pos, T element) throws PosException, VacioException, NullException {
		if (pos < 1 || pos > size) {
			throw new PosException(INVALID_POCITION_MESSAGE);
		} else if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		} else if (element == null) {
			throw new NullException(INVALID_ELEMENT_MESSAGE);
		} else {
			Nodo<T> actual = first;
			for (int i = 0; i < pos - 1; i++) {
				actual = actual.getNext();
			}

			actual.change(element);

		}

	}

	public ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException {
		if (isEmpty()) {
			throw new VacioException(LIST_VACIA_MESSAGE);
		} else if (numElementos < 0) {
			throw new PosException("La cantidad de elementos no es válida");
		} else if (numElementos >= size()) {
			return this;
		} else {
			ILista<T> copia = new ListaEncadenada<>();

			int contador = pos;
			for (int i = 0; i < numElementos; i++) {
				copia.insertElement(this.getElement(contador), i + 1);
				contador++;
			}

			return copia;
		}

	}

	@Override
	public int compareTo(ILista o) {
		if (o == null) {
			throw new NullPointerException("La lista proporcionada para comparar es nula.");
		}

		// Comparar con base en el tamaño de la lista
		int thisSize = this.size();
		int otherSize = o.size();

		// Retorna -1 si this es menor, 1 si es mayor, 0 si son iguales
		return Integer.compare(thisSize, otherSize);
	}
}
