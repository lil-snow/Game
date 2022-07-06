package org.lilsnow.game.utils;

public class ArrayUtils {
	
	public static boolean contains_only(Object[] arr, Object obj) {
		for (Object o : arr) {
			if (o != obj)
				return false;
		}
		return true;
	}
	
	public static boolean contains_only(boolean[] arr, boolean obj) {
		for (boolean o : arr) {
			if (o != obj)
				return false;
		}
		return true;
	}
	
}
