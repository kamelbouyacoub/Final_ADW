package listes;

import java.util.List;



public class Listes <Type> {
		
	public static<Type> int size(List<Type> list) {
		return  list.size();
	}

	public static<Type> boolean isEmpty(List<Type> list) {
		return  list.isEmpty();
	}

	public static<Type> boolean add(List<Type> list, Type e) {
		return list.add(e);
	}

	public static<Type> boolean remove( List<Type> list, Object o) {
		return list.remove(o);
	}

	public static<Type> Object get(List<Type> list, int index) {
		return  list.get(index);
	}
	
	public static<Type> boolean contains(List<Type> list, Object o) {
		return list.contains(o);
	}

}
