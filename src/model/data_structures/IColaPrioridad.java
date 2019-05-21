package model.data_structures;


public interface IColaPrioridad<T> extends Iterable<T>
{

	public int darNumElementos();

	public void agregar(T elemento);

	public T delMax();

	public T max(); 

	public boolean esVacia();

}

