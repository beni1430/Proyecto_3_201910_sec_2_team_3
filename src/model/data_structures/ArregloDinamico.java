package model.data_structures;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico  < T extends Comparable<T>> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T []) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [] copia = elementos;
			elementos = (T [])new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	public int darTamano() {
		// TODO implementar
		return tamanoAct;
	}

	public T darElemento(int i) {
		// TODO implementar
		return elementos[i];
	}

	public T buscar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		for(int i = 0; i < elementos.length; i++)
		{
			if(elementos[i].compareTo(dato)==0)
			{
				return elementos[i];
			}
		}
		return null;
	}

	public T eliminar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T eliminado = null;
		if(buscar(dato) != null)
		{
			int indice = 0; 
			for(int i = 0; i < elementos.length; i++)
			{
				if(elementos[i].compareTo(dato)==0)
				{
					eliminado = elementos[i];
					elementos[i]= null;
					tamanoAct--;
					indice = i; 
					System.out.println("Se ha eliminado el String");

				}
			}
			//Cambia el orden para que el null quede de ultimo;
			for(int i = indice, j = indice + 1; j < elementos.length; i++, j++)
			{
				elementos[i] = elementos[j];
			}
			elementos[elementos.length-1] = null;
			return eliminado;
		}
		else
		{
			System.out.println("El String a eliminar no existe");
			return null;
		}
	}

	public IteratorLista<T> darIterator()
	{
		return (IteratorLista<T>) Arrays.asList(elementos).iterator();
	}
}
