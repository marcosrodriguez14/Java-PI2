package ejemplos;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Ejemplo3 {
	/*
	PI2 - Ejemplo 3

	Analizar los tiempos de ejecución de las búsquedas lineal y binaria, 
	usando en los casos que correspondan listas ordenadas o desordenadas.
	*/

	public static <E> Integer busquedaLineal(List<E> ls, E elem) {
		Integer n = ls.size();
		Integer i = 0;
		Boolean b = false;
		while (i < ls.size() && !b) {
			if (ls.get(i).equals(elem)) {
				b = true;
			} else {
				i++;
			}
		}
		return b?i:-1;
	}
	
	public static <E> Integer busquedaLinealR(List<E> ls, E elem) {
		return busquedaLinealR(0,ls,elem);
	}
	
	private static <E> Integer busquedaLinealR(Integer i, List<E> ls, E elem) {
	 	Integer b = -1;
	 	Integer n = ls.size();
		if(n-i> 0){
			if (ls.get(i).equals(elem)) b = i;
	    		else b = busquedaLinealR(i+1,ls,elem);
		}
		return b;
	}

	public static <E> Integer busquedaLinealFun(List<E> ls, E elem) {
		return IntStream.range(0, ls.size())
				.map(i-> elem.equals(ls.get(i))?i:-1)
				.filter(i-> i>-1)
				.findFirst().orElse(-1);
	}
	
	

	public static <E> Integer busquedaLinealOrdI(List<E> ls, E elem) {
		return busquedaLinealOrdI(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaLinealOrdI(List<E> ls, E elem, Comparator<?> c) {
		Integer r = -1;
		Integer i = 0;
		Boolean fin = false;
		Iterator<E> iterator = ls.iterator();
		while (iterator.hasNext() && !fin) {
			E e = iterator.next();
	      	if (((Comparator<? super E>) c).compare(elem, e) == 0) {
	      		r = i;
	      		fin = true;
	      	} else if(((Comparator<? super E>) c).compare(elem, e) < 0) {
	      		r = -1;
	      		fin = true;
	      	}
	   		else i++;
		}
		return r;
	}

	
	
	public static <E> Integer busquedaLinealOrdR(List<E> ls, E elem) {
		return busquedaLinealOrdR(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaLinealOrdR(List<E> ls, E elem, Comparator<?> c) {
		return busquedaLinealOrdR(0,ls.iterator(),elem,(Comparator<? super E>) c);
	}
	
	private static <E> Integer busquedaLinealOrdR(Integer i, Iterator<E> iterator, E elem, Comparator<? super E> c) {
		Integer r = -1;
		if(iterator.hasNext()){
			E e = iterator.next();
	      	if (c.compare(elem, e) == 0) r = i;
			else if(c.compare(elem, e) < 0) r = -1;
	   		else r = busquedaLinealOrdR(i+1,iterator,elem,c);
		}
		return r;
	}

	
	public static <E> Integer busquedaBinaria(List<E> ls, E elem) {
		return busquedaBinaria(ls, elem, Comparator.naturalOrder());
	}
	
	private static <E> Integer busquedaBinaria(List<E> ls, E elem, Comparator<?> comparator) {
		Integer i = 0;
		Integer j = ls.size();
		Integer k = (i+j)/2;
		Integer r = -1;
		while (j-i> 0 && r==-1) {
			if (((Comparator<? super E>)comparator).compare(elem, ls.get(k)) == 0) r = k;
			else if (((Comparator<? super E>)comparator).compare(elem, ls.get(k)) < 0) {
				j = k;
				k = (i+j)/2;
			} else {
				i = k+1;
				k = (i+j)/2;
			}
		}
		return r;
	}
	
	public static <E> Integer busquedaBinariaR(List<E> ls, E elem) {
		return busquedaBinariaR(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaBinariaR(List<E> ls, E elem, Comparator<?> c) {
		Integer n = ls.size();
		return busquedaBinariaR(0,n,ls,elem,(Comparator<? super E>) c);
	}
	
	private static <E> Integer busquedaBinariaR(Integer i, Integer j, List<E> ls, E elem, Comparator<? super E> c) {
	 	Integer r = -1;
		if(j-i> 0){
			Integer k = (j+i)/2;
			if (c.compare(elem, ls.get(k)) == 0) r = k;
			else if (c.compare(elem, ls.get(k)) < 0) 
				r = busquedaBinariaR(i,k,ls,elem,c);
			else r = busquedaBinariaR(k+1,j,ls,elem,c); 
		}
		return r;
	}
	


	
}
