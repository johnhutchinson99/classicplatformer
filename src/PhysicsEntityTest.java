//Unit test for Physics
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class PhysicsEntityTest {

	//Physics Entity is abstract, so a child needs to be made to test functionality
	public class PhysicsEntityChild extends PhysicsEntity {
		//Initial test values
		public PhysicsEntityChild(int xCoord, int yCoord, int width, int height, World world) {
			super(xCoord, yCoord, width, height, world);
		}

		public PhysicsEntityChild(PhysicsEntity p) {
			super(p);
		}

	}
	
	
	
	
	//Custom  assert method to allow for better error messages
	//Factors in tiny difference in doubles
	public static void assertEquals(String name,double actual, double expected) {
		
		double shouldBeZero = (expected-actual);
		
		if(shouldBeZero<0) {
			shouldBeZero*=-1;
		}
		
		assertTrue("Incorrect "+name+". Expected Value: "+expected+". Actual Value: "+actual,0.01>shouldBeZero);
		
		
	}
	


	
	
	
	

	/**
	 * Tests the constructor with all parameters
	 */
	@Test
	public void fullConstructorTest() {
		
		int xCoord = 10;
		int yCoord = 6;
		int width = 0;
		int height = 14;
		World world = new World();
		
		
		
		
		PhysicsEntity test = new PhysicsEntityChild(xCoord, yCoord, width, height, world);
		
		//Due to no X acceleration, X values should not change at all in runtime
		
		assertEquals("X Position",test.getxCoord(),xCoord);
		assertEquals("Y Position",test.getyCoord(),yCoord);
		 
		assertEquals("X Velocity",test.getxVelocity(),0); //Initial velocity should be 0
		assertEquals("Y Velocity",test.getyVelocity(),0);
		
		assertEquals("Height",test.getHeight(),height);
		assertEquals("Width",test.getWidth(),width);
		
		assertEquals("X Acceleration",test.getXAcceleration(),0);
		assertEquals("Y Acceleration",test.getYAcceleration(),5);
		
		assertTrue("World is null",test.getWorld()!=null);
		
		
		
		
		
		
		
	}
	
	
	@Test
	public void copyConstructor() {
		
		int xCoord = 3;
		int yCoord = 1;
		int width = 7;
		int height = 0;
		World world = new World();
		
		
		
		PhysicsEntity test = new PhysicsEntityChild(new PhysicsEntityChild(xCoord, yCoord, width, height, world));

		assertEquals("X Position",test.getxCoord(),xCoord);
		assertEquals("Y Position",test.getyCoord(),yCoord);
		 
		assertEquals("X Velocity",test.getxVelocity(),0); //Initial velocity should be 0
		assertEquals("Y Velocity",test.getyVelocity(),0);
		
		assertEquals("Height",test.getHeight(),height);
		assertEquals("Width",test.getWidth(),width);
		
		assertEquals("X Acceleration",test.getXAcceleration(),0);
		assertEquals("Y Acceleration",test.getYAcceleration(),5);
		
		assertTrue("World is null",test.getWorld()!=null);
		
		
	}
	
	
	@Test
	public void jumpTest() {
		PhysicsEntity test = new PhysicsEntityChild(3,1,4,6,new World());
		
		
		test.jump();
		
		assertEquals("Y Velocity",test.getyVelocity(),-25);
	}
	
	
	
	
	
	
}


