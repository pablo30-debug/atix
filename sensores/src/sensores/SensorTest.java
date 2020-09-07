package sensores;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SensorTest {

	@Test
	void testMaximo() {
		
		List<Integer> valores = new ArrayList();
		valores.add(1);
		valores.add(2);
		valores.add(3);
		valores.add(4);
		valores.add(5);
		valores.add(6);
		valores.add(7);
		
		Integer max = Sensor.maximo(valores);
		
		assertEquals(7, max);
		
	}
	
	@Test
	void testMinimo() {
		
		List<Integer> valores = new ArrayList();
		valores.add(1);
		valores.add(2);
		valores.add(3);
		valores.add(4);
		valores.add(5);
		valores.add(6);
		valores.add(7);
		
		Integer max = Sensor.minimo(valores);
		
		assertEquals(1, max);
		
	}

}
