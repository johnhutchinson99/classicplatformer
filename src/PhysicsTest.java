import static org.junit.Assert.*;
import org.junit.Test;
public class PhysicsTest {

	
	
	/**
	 * TEST TAKES TIME
	 * SOME CODE REQUIRES REAL WORLD TIME  TO RUN
	 */
	
	
	
	/**
	 * Tests the constructor for correct value initialization
	 */
	@Test
	public void constructorTest()
	{
		Physics tester = new Physics(4,5,7,8);
		
		assertEquals("X Coordinate in constructor",4,tester.getXPosition());
		assertEquals("Y Coordinate in constructor",5,tester.getYPosition());
		assertEquals("Maximum X coordinate in constructor",7,tester.getMaxXPosition());
		assertEquals("Maximum Y coordinate in constructor",8,tester.getMaxYPosition());
	}
	
	
	
	/**
	 * Checks if the Physics class knows if the object is traveling left
	 */
	@Test
	public void goingLeftTest() {
		Physics tester = new Physics(1,3,4,5);
		
		tester.setXVelocity(-3);
		
		assertTrue(tester.isGoingLeft());
		
	}
	/**
	 * Checks if the Physics class knows if the object is traveling right
	 */
	@Test
	public void goingRightTest() {
		Physics tester = new Physics(1,3,4,5);
		
		tester.setXVelocity(3);
		
		assertTrue(tester.isGoingRight());
		
	}
	/**
	 * Checks if the Physics class knows if the object is traveling up
	 */
	@Test
	public void goingUpTest() {
		Physics tester = new Physics(1,3,4,5);
		
		tester.setYVelocity(-3);
		
		assertTrue(tester.isGoingUp());
		
	}
	/**
	 * Checks if the Physics class knows if the object is traveling down
	 */
	@Test
	public void goingDownTest() {
		Physics tester = new Physics(1,3,4,5);
		
		tester.setYVelocity(3);
		
		assertTrue(tester.isGoingDown());
		
	}
	/**
	 * Checks if the Physics class can completely stop itself
	 */
	@Test 
	public void fullStopTest() {
		Physics tester = new Physics(1,4,6,8);
		
		tester.setXVelocity(100);
		tester.setYVelocity(100);
		tester.setYAcceleration(100);
		tester.setXAcceleration(100);

		tester.fullStop();
		assertTrue(tester.getXVelocity()==0);
		assertTrue(tester.getYVelocity()==0);
		assertTrue(tester.getXAcceleration()==0);
		assertTrue(tester.getYAcceleration()==0);
		
		
		
		
	}
	
	/**
	 * Tests calculations of the position based on combined velocity and acceleration
	 */
	@Test
	public void calculateNewXPosition() {
		Physics tester = new Physics(2,4,1000,1000);
		
		tester.setXAcceleration(3);
		tester.setXVelocity(2);
		
		
		
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Gives a time for calculations

		assertEquals("Calculated X from velocity and acceleration after 1.5 seconds",8,tester.getXPosition());
		
		
		
		
	}
	/**
	 * Tests calculations of the position based on combined velocity and acceleration
	 */
	@Test
	public void calculateNewYPosition() {
		Physics tester = new Physics(5,3,1000,1000);
		
		tester.setYAcceleration(1);
		tester.setYVelocity(5);
		
		
		
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Gives a time for calculations

		assertEquals("Calculated Y from velocity and acceleration after 2 seconds",12,tester.getYPosition());
		
		
		
		
	}
	
	
	
}
