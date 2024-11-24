package model.data_structures;

public class PilaEncadenada<T extends Comparable <T>> extends ListaEncadenada<T> 
{
	public void push(T element)
	{
		try {
			this.addLastCola(element);
		} catch (NullException e) {
			System.err.println("Se produjo un error de tipo NullException: " + e.getMessage());
        	e.printStackTrace();
    	
    		throw new RuntimeException("Error crítico: operación no puede continuar debido a un valor nulo", e);

		}
	}
	
	public T pop()
	{
		try 
		{
			return this.removeLastPila();
		} catch (VacioException e) {
			System.err.println("Error: Operación no válida, la estructura está vacía. Detalles: " + e.getMessage());
      		e.printStackTrace();
			return null;
		}
		
	}
	
	public T top()
	{

		return this.lastElement();

	}
}
