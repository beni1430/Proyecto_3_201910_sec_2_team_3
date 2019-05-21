package model.data_structures;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorMinColaP<T> implements Iterator<T>
{
	private Nodo<T> proximo;

	public IteratorMinColaP( Nodo<T> primero )
	{
		proximo = primero;
	}

	public boolean hasNext( )
	{
		return proximo != null;
	}

	public T next( )
	{
		if( proximo == null )
		{ 
			throw new NoSuchElementException("No hay proximo"); 
		}
		T elemento = proximo.darElemento(); 
		proximo = proximo.darSiguiente(); 
		return elemento;
	}
}
