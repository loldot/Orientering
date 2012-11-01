package no.orientering.utils;

public class NetHelp {

	public static boolean isNullOrEmpty(String string){
		return ( isNull(string) || string.trim().length() == 0);
	}
	private static boolean isNull(String str) {
		return str == null ? true : false;
	}
	
}
