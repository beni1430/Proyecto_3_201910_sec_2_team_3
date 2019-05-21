package model.data_structures;


import java.util.Iterator;

public class Queue<T> implements IQueue<T>
{
	private Nodo<T> primero;
	private int tamano; 

	public Queue ()
	{
		primero=null;
		tamano=0;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new IteratorLista<T>(primero);
	}

	@Override
	public boolean isEmpty() 
	{
		return primero==null;
	}

	public T darElem(int i)
	{
		Nodo<T> actual=primero;
		int x=1;
		while (x!=i)
		{
			actual=actual.darSiguiente();
			x++;
		}
		return actual.darElemento();
	}

	@Override
	public int size() 
	{
		return tamano;
	}

	@Override
	public void enqueue(T t) 
	{
		if(primero==null)
			primero=new Nodo<T>(t);

		else 
		{
			Nodo actual=primero;
			while(actual.darSiguiente()!=null)
			{
				actual=actual.darSiguiente();
			}
			actual.cambiarSiguiente(new Nodo<T>(t));
		}

		tamano++;
	}

	@Override
	public T dequeue() 
	{
		Nodo<T> eliminado=primero;
		primero=primero.darSiguiente();
		tamano--;
		return eliminado.darElemento();
	}

}
