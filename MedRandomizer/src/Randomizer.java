import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Randomizer {

	 private static final int AELEGIR = 100;

	public static void main(String[] args) {
		 
		 HashMap<String, String> TablaTerm_Sem2Sem=new HashMap<>();
		 
		 HashMap<String, String> TablaTerm_Sem2Term=new HashMap<>();
		 
		 List<String> ListTerms=new LinkedList<String>();
		try {
			String archivo="randomme.txt";
			 String cadena;
		     FileReader f = new FileReader(archivo);
		     BufferedReader b = new BufferedReader(f);
		     String ActSem="";
		     while((cadena = b.readLine())!=null) {
		    	 if (cadena.startsWith("Semantica="))
		    		 ActSem=cadena;
		    	 else
			    	 {
		    		 String ActSemTerm = ActSem+"$"+cadena;
		    		 
		    		 TablaTerm_Sem2Sem.put(ActSemTerm,ActSem);
		    		 TablaTerm_Sem2Term.put(ActSemTerm, cadena);
			    	 ListTerms.add(ActSemTerm);
			    	 System.out.println(cadena);
			    	 }
		     }
		     b.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		Collections.shuffle(ListTerms);
		
		HashMap<String, List<String>> TablaTerm2ListSem=new HashMap<>();
		
		for (int i = 0; i < AELEGIR; i++) {
			String Semterm=ListTerms.get(i);
			String MiSem = TablaTerm_Sem2Sem.get(Semterm);
			List<String> MisTermSelec = TablaTerm2ListSem.get(MiSem);
			if (MisTermSelec==null)
				MisTermSelec=new LinkedList<>();
			MisTermSelec.add(TablaTerm_Sem2Term.get(Semterm));
			TablaTerm2ListSem.put(MiSem, MisTermSelec);
		}
		
		
		try {
			FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("randomized.txt");
	            pw = new PrintWriter(fichero);

	            for (Entry<String, List<String>> string : TablaTerm2ListSem.entrySet()) {
	            	pw.println(string.getKey()+":");
	            	for (String word : string.getValue()) {
	            		pw.println(word.replaceFirst("\\+", " ").replaceFirst("\\+", " "));
					}
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("randomizedB.csv");
	            pw = new PrintWriter(fichero);

	            for (Entry<String, List<String>> string : TablaTerm2ListSem.entrySet()) {
	            	for (String word : string.getValue()) {
	            		pw.println(string.getKey().replace("Semantica=>", "")+";"+word.trim().replaceFirst("\\+", "").replaceFirst("\\+", ""));
					}
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
}
