package com.innovate.itms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class Test {
	public static void main(String[] args) {
//        String hashAlgorithmName = "MD5";
//        String credentials = "123456";
//        String s = new String("123456");
//        int hashIterations = 1;
//        new String("111");
//        ByteSource credentialsSalt = ByteSource.Util.bytes("dzeb");
//        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
//       // String password=new SimpleHash("MD5",user.getPassword(),user.getAccount(),2).toHex();
//        
//        String password=new SimpleHash("MD5","12345678","zjjj",1024).toHex();
//        
//        byte[]  str = Base64.decode("3AvVhmFLUs0KTA3Kprsdag==");
//        System.out.println(str);
//        System.out.println(credentials == s );
//        System.out.println(credentials.equals(s) );
		
		 String str = "id={id}&name={name}&.temp=idijdu";
		 
		    Pattern p=Pattern.compile("\\{(\\w+)\\}");
		    Matcher m=p.matcher(str);
		    while(m.find()){
		    	String group = m.group(1);
		        System.out.println(group);
		        
		    }
		
    }
}
