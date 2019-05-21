package model.data_structures;



public class Nodo<T> 
{

	private T elemento;

	private Nodo<T> siguiente;
	private Nodo<T> anterior;

	public Nodo(T dato)
	{
		elemento = dato;
		siguiente = null;
		anterior=null;
	}

	public void cambiarAnterior(Nodo<T> dato)
	{
		anterior = dato;
	}

	public Nodo<T> darAnterior()
	{
		return anterior;
	}

	public void cambiarSiguiente(Nodo<T> dato)
	{
		siguiente = dato;
	}

	public Nodo<T> darSiguiente()
	{
		return siguiente;
	}

	public T darElemento()
	{
		return elemento;
	}
}
