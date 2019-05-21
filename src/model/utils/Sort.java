package model.utils;

import java.util.Comparator;
import model.vo.VOMovingViolations;

public class Sort {

	private static Comparator<VOMovingViolations> comparadorActual;
	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 * @param hi 
	 * @param mid 
	 * @param lo 
	 * @param aux 
	 */
	public static void ordenarMergeSort( VOMovingViolations[ ] datos, VOMovingViolations[] aux, int lo, int mid, int hi ) 
	{
		for (int k = lo; k <= hi; k++)
		{	
			aux[k] = datos[k]; 
		}
		int i = lo;
		int j = mid+1;

		for (int k = lo; k <= hi; k++)
		{
			if      (i > mid)            
				datos[k] = aux[j++];
			else if (j > hi)             
				datos[k] = aux[i++];
			else if (less(aux[j], aux[i])) 
				datos[k] = aux[j++];
			else                          
				datos[k] = aux[i++];
		}
	}
	private static void sortM(VOMovingViolations[]datos, VOMovingViolations[] aux, int lo, int hi)
	{
		if (hi<=lo)
			return;
		int mid=lo+(hi-lo)/2;
		sortM(datos,aux,lo,mid);
		sortM(datos,aux,mid+1,hi);
		ordenarMergeSort(datos,aux,lo,mid,hi);
	}

	public static void sortM(VOMovingViolations[]datos, Comparator<VOMovingViolations>entrada)
	{
		comparadorActual = entrada;
		VOMovingViolations[]aux=new VOMovingViolations[datos.length];
		sortM(datos,aux,0,datos.length-1);
	}



	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 *param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean less(VOMovingViolations v, VOMovingViolations w)
	{
		return comparadorActual.compare(v, w)<0;
	}

	public static void invertirMuestra( VOMovingViolations[ ] datos )
	{
		VOMovingViolations[] muestraInvertida = new VOMovingViolations[ datos.length ];
		int j = datos.length-1;
		for ( int i = 0 ; i < datos.length-1 ; i++ )
		{
			muestraInvertida[i] = datos[j];
			j--;	
		}	
		datos = muestraInvertida;
	}


}