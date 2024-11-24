package model.data_structures;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ColaEncadenada<T extends Comparable <T>> extends ListaEncadenada<T>
{
	public void enqueue(T element)
	{
		try {
			this.addLastCola(element);
		} catch (NullException e) {
		
			Logger logger = Logger.getLogger(getClass().getName());
    		logger.log(Level.SEVERE, "NullPointerException occurred: " + e.getMessage(), e);

		}
	}
	
	public T dequeue()
	{
		T retorno=null;
		try 
		{
			retorno= this.deleteElement(1);
		} catch (PosException | VacioException e) {
			
			Logger logger = Logger.getLogger(getClass().getName());
    		logger.log(Level.SEVERE, "NullPointerException occurred: " + e.getMessage(), e);

		}
		
		return retorno;
	}
	
	public T peek()
	{
		T retorno=null;
		try {
			retorno = this.getElement(1);
		} catch (PosException | VacioException e) {
			Logger logger = Logger.getLogger(getClass().getName());
    		logger.log(Level.SEVERE, "NullPointerException occurred: " + e.getMessage(), e);
		}
		
		return retorno;
	}
}
