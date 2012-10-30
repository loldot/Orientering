package no.orientering.utils;

public class ByteUtil {
	public static String BytesToHexString(byte[] bytes){
		String hex = "";
		
		for(byte x : bytes){
			hex += String.format("%02x", x);
		}
		
		return hex;
	}
}
