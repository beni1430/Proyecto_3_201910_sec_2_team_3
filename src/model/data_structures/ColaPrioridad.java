package model.data_structures;



import java.util.Comparator;
import java.util.Iterator;


public class ColaPrioridad<T> implements IColaPrioridad<T>
{

	private Nodo<T> primerNodo;
	private Nodo<T> ultimoNodo;
	private int cantidadElementos;
	private Comparator<T> comp;
	
	public ColaPrioridad(Comparator<T> c) 
	{
		primerNodo = null;
		cantidadElementos = 0;
		ultimoNodo = null;
		comp = c;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new IteratorMinColaP<T>(primerNodo);
	}

	public Nodo<T> darPrimero()
	{
		return primerNodo;
	}
	
	@Override
	public boolean esVacia() {
		
		return cantidadElementos==0;
	}

	@Override
	public int darNumElementos() {
		return cantidadElementos;
	}

	@Override
	public void agregar(T t) {
		Nodo<T> nuevo = new Nodo<T>(t);
		if(primerNodo == null)
		{
			primerNodo = nuevo;
			
		}
		else if(comp.compare(primerNodo.darElemento(),t)<0)
		{
			nuevo.cambiarSiguiente(primerNodo);
			primerNodo.cambiarAnterior(nuevo);
			primerNodo = nuevo;
		}
		else
		{
			Nodo<T> actual = primerNodo;
			boolean agregado = false;
			while(actual!=null && !agregado)
			{
				if(comp.compare(actual.darElemento(),t)<0)
				{
					nuevo.cambiarSiguiente(actual);
					nuevo.cambiarAnterior(actual.darAnterior());
					actual.darAnterior().cambiarSiguiente(nuevo);
					actual.cambiarAnterior(nuevo);
					agregado = true;
				}
				if(actual.darSiguiente()==null && !agregado )
				{
					nuevo.cambiarAnterior(actual);
					actual.cambiarSiguiente(nuevo);
					agregado = true;
				}
				actual = actual.darSiguiente();
			}
		}
		if(nuevo.darSiguiente()==null)
		{
			ultimoNodo = nuevo;
		}
		cantidadElementos++;
	}

	@Override
	public T delMax() {
		Nodo<T> max = null;
		if(primerNodo!=null)
		{
			max = primerNodo;
			primerNodo = primerNodo.darSiguiente();
			if(primerNodo!=null)
			{
				primerNodo.cambiarAnterior(null);
			}
			else
			{
				ultimoNodo =null;
			}
			cantidadElementos--;
			return max.darElemento();
		}
		return null;
	}

	@Override
	public T max() {
		if(ultimoNodo!=null && primerNodo!= null)
		{
			return primerNodo.darElemento();
		}
		return null;
	}
	
	public T darElem(int k)
	{
		Nodo<T> elem = primerNodo;
		if (k==1) 
		{
			return elem.darElemento();
		}
		else if(k>1 && cantidadElementos>=k)
		{
			int i = 2;
			while(k>i)
			{
				elem = elem.darSiguiente();
				i++;
			}
			return elem.darElemento();
		}
		else
			return null;
	}

	public boolean contains(T t)
	{
		Iterator iter = iterator();
		while(iter.hasNext())
		{
			if(iter.next().equals(t))
				return true;
		}
		return false;
	}
	

	

}
