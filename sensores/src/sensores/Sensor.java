package sensores;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sensor {

	private static List<Integer> valores = new ArrayList();
	private static final Integer constanteS = 5;
	private static final Integer constanteM = 5;
	private static Logger logger = Logger.getLogger(Sensor.class.getName()); 
	
	public static void main(String[] args) throws IOException {

		
		
		BufferedReader reader =	new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Seleccione 1 para carga random , 2 para carga manual ");
		String name = reader.readLine();
		if(name.equals("1")){
			valores.add(getRandom(10));
			valores.add(getRandom(10));
			valores.add(getRandom(10));
			valores.add(getRandom(10));
				
		}else if (name.equals("2")) {
			System.out.println("seleccione un numero y presione enter "); 
			valores.add( Integer.parseInt( reader.readLine() ));
			System.out.println("seleccione otro numero y presione enter ");
			valores.add( Integer.parseInt( reader.readLine() ));
			System.out.println("seleccione otro numero y presione enter ");
			valores.add( Integer.parseInt( reader.readLine() ));
			System.out.println("seleccione otro numero y presione enter ");
			valores.add( Integer.parseInt( reader.readLine() ));
			
		}
		
		
		correrSensor(valores);
	    
	    
	}

	
	public static void correrSensor(List <Integer> valores) {
		
	    ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
	    executor.scheduleWithFixedDelay(calculate(valores), 0, 60, TimeUnit.SECONDS);
	    executor.scheduleWithFixedDelay(calculate(valores), 0, 60, TimeUnit.SECONDS);
	}
	
	private static Runnable calculate(List <Integer> valores) {
		return new Runnable() {
			public void run() {
				
				Double promedio = promedio(valores);
				logger.log(Level.INFO,"Promedio : " + promedio);
				
				Integer maxValue = maximo(valores);
	        	System.out.println("Maximo" + maxValue);
	        	logger.log(Level.INFO,"Maximo : " + maxValue);
	        	
	        	Integer minValue = minimo(valores);
	        	System.out.println("Minimo" + minValue);
	        	logger.log(Level.INFO,"Minimo : " + minValue);
	        	
	        	if(maxValue - minValue  > constanteS) {
	        		logger.log(Level.SEVERE, "la constate S es mayor que la diferencia entre maximo y minimo");
	        	}
	        	if(promedio  > constanteM) {
	        		logger.log(Level.SEVERE,"la constate M es mayor que el promedio");
	        	}
			}
		};
	}
	
	
	public static Integer minimo (List <Integer> valores) {
		return valores.stream().min(Comparator.naturalOrder()).get();
	}
	
	public static Integer maximo (List <Integer> valores) {
		return  valores.stream().max(Comparator.naturalOrder()).get();
	}

	public static Double promedio (List <Integer> valores) {
		return  valores.stream().mapToInt(i -> i ).average().orElse(0.0);
	}
	
	public static int getRandom(int max){
		int i =(int) (Math.random()*max);
		logger.log(Level.INFO,"numero random " + i);
		return i;
	}


}
