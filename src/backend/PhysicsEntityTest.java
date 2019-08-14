package backend;

//Unit test for Physics
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PhysicsEntityTest {

	// Physics Entity is abstract, so a child needs to be made to test functionality
	private class PhysicsEntityChild extends PhysicsEntity {
		// Initial test values
		public PhysicsEntityChild(int xCoord, int yCoord, int width, int height, World world) {
			super(xCoord, yCoord, width, height, world);
		}

		public PhysicsEntityChild(PhysicsEntity p) {
			super(p);
		}

	}

	// Custom assert method to allow for better error messages
	// Factors in tiny difference in doubles
	private static void assertEquals(String name, double actual, double expected) {

		double shouldBeZero = (expected - actual);

		if (shouldBeZero < 0) {
			shouldBeZero *= -1;
		}

		assertTrue("Incorrect " + name + ". Expected Value: " + expected + ". Actual Value: " + actual,
				0.01 > shouldBeZero);

	}

	/**
	 * Tests the constructor with all (valid) parameters and checks they are entered
	 */
	@Test
	public void fullConstructorTest() {

		int xCoord = 10;
		int yCoord = 6;
		int width = 0;
		int height = 14;
		World world = new World();

		PhysicsEntity test = new PhysicsEntityChild(xCoord, yCoord, width, height, world);

		// Due to no X acceleration, X values should not change at all in runtime

		assertEquals("X Position", test.getxCoord(), xCoord);
		assertEquals("Y Position", test.getyCoord(), yCoord);

		assertEquals("X Velocity", test.getxVelocity(), 0); // Initial velocity should be 0
		assertEquals("Y Velocity", test.getyVelocity(), 0);

		assertEquals("Height", test.getHeight(), height);
		assertEquals("Width", test.getWidth(), width);

		assertEquals("X Acceleration", test.getXAcceleration(), 0);
		assertEquals("Y Acceleration", test.getYAcceleration(), PhysicsEntity.GRAVITY);

		assertTrue("World is null", test.getWorld() != null);

	}

	/**
	 * Tests the constructor with all parameters and checks they are entered 
	 * Note:
	 * 	xCoords including negative are always "valid"
	 *  yCoords including negative are always "valid"
	 *  (including outside application width/height, 
	 *  i.e. characters can exist off screen/"off world")
	 *  Width and height should be 0 at minimum however
	 */
	@Test
	public void fullConstructorTestInvalid() {

		int xCoord = 20;
		int yCoord = -50;
		int width = -100;
		int height = -100;
		World world = new World();

		PhysicsEntity test = new PhysicsEntityChild(xCoord, yCoord, width, height, world);

		// Due to no X acceleration, X values should not change at all in runtime

		assertEquals("X Position", test.getxCoord(), xCoord);
		assertEquals("Y Position", test.getyCoord(), yCoord);

		assertEquals("X Velocity", test.getxVelocity(), 0); // Initial velocity should be 0
		assertEquals("Y Velocity", test.getyVelocity(), 0);

		assertEquals("Height", test.getHeight(), 0);
		assertEquals("Width", test.getWidth(), 0);

		assertEquals("X Acceleration", test.getXAcceleration(), 0);
		assertEquals("Y Acceleration", test.getYAcceleration(), PhysicsEntity.GRAVITY);

		assertTrue("World is null", test.getWorld() != null);

	}

	/**
	 * Testing the copier constructor with valid input
	 */
	@Test
	public void copyConstructor() {

		int xCoord = 3;
		int yCoord = 1;
		int width = 7;
		int height = 0;
		World world = new World();

		PhysicsEntity test = new PhysicsEntityChild(new PhysicsEntityChild(xCoord, yCoord, width, height, world));

		assertEquals("X Position", test.getxCoord(), xCoord);
		assertEquals("Y Position", test.getyCoord(), yCoord);

		assertEquals("X Velocity", test.getxVelocity(), 0); // Initial velocity should be 0
		assertEquals("Y Velocity", test.getyVelocity(), 0);

		assertEquals("Height", test.getHeight(), height);
		assertEquals("Width", test.getWidth(), width);

		assertEquals("X Acceleration", test.getXAcceleration(), 0);
		assertEquals("Y Acceleration", test.getYAcceleration(), PhysicsEntity.GRAVITY);

		assertTrue("World is null", test.getWorld() != null);

	}

	/**
	 * Testing the copier constructor with invalid inputs (outside range).
	 * 
	 * Note:
	 * 	xCoords including negative are always "valid" 
	 *  yCoords including negative are always "valid"
	 *  (including outside application width/height, 
	 *  i.e. characters can exist off screen/"off world")
	 *  Width and height should be 0 at minimum however
	 */
	@Test
	public void copyConstructorInvalid() {

		int xCoord = 0; // xCoords including negative are always "valid"
		int yCoord = 0; // yCoords including negative are always "valid"
		int width = -1; // Should be 0 at minimum
		int height = -5; // Should be 0 at minimum
		World world = new World();

		PhysicsEntity test = new PhysicsEntityChild(new PhysicsEntityChild(xCoord, yCoord, width, height, world));

		assertEquals("X Position", test.getxCoord(), xCoord);
		assertEquals("Y Position", test.getyCoord(), yCoord);

		assertEquals("X Velocity", test.getxVelocity(), 0);
		assertEquals("Y Velocity", test.getyVelocity(), 0);

		assertEquals("Height", test.getHeight(), 0);
		assertEquals("Width", test.getWidth(), 0);

		assertEquals("X Acceleration", test.getXAcceleration(), 0);
		assertEquals("Y Acceleration", test.getYAcceleration(), PhysicsEntity.GRAVITY);

		assertTrue("World is null", test.getWorld() != null);

	}

	/**
	 * Testing the set Y Velocity method with a negative input
	 */
	@Test
	public void setYVelocityTestNegative() {
		PhysicsEntity test = new PhysicsEntityChild(3, 1, 4, 6, new World());

		test.setyVelocity(-25);

		assertEquals("Y Velocity", test.getyVelocity(), -25);
	}
	
	/**
	 * Testing the set Y Velocity method with positive input
	 */
	@Test
	public void setYVelocityTestPositive() {
		PhysicsEntity test = new PhysicsEntityChild(3, 1, 4, 6, new World());

		test.setyVelocity(25);

		assertEquals("Y Velocity", test.getyVelocity(), 25);
	}
	
	/**
	 * Testing the set Y Velocity method with positive input
	 */
	@Test
	public void setYVelocityTestZero() {
		PhysicsEntity test = new PhysicsEntityChild(3, 1, 4, 6, new World());

		test.setyVelocity(0);

		assertEquals("Y Velocity", test.getyVelocity(), 0);
	}


	/**
	 * Testing the set X Velocity method with negative input
	 */
	@Test
	public void setXVelocityTestNegative() {
		PhysicsEntity test = new PhysicsEntityChild(0, 0, 0, 0, new World());

		test.setxVelocity(-25);

		assertEquals("X Velocity", test.getxVelocity(), -25);
	}
	
	/**
	 * Testing the set X Velocity method with positive input
	 */
	@Test
	public void setXVelocityTestPositive() {
		PhysicsEntity test = new PhysicsEntityChild(0, 0, 0, 0, new World());

		test.setxVelocity(25);

		assertEquals("X Velocity", test.getxVelocity(), 25);
	}
	
	/**
	 * Testing the set X Velocity method with zero
	 */
	@Test
	public void setXVelocityTestZero() {
		PhysicsEntity test = new PhysicsEntityChild(0, 0, 0, 0, new World());

		test.setxVelocity(0);

		assertEquals("X Velocity", test.getxVelocity(), 0);
	}

	/**
	 * Testing the set Y Acceleration method with positive input
	 */
	@Test
	public void setYAccelerationTestPositive() {
		PhysicsEntity test = new PhysicsEntityChild(1, 1, 1, 1, new World());

		test.setYAcceleration(4);

		assertEquals("Y Acceleration", test.getYAcceleration(), 4);
	}
	
	/**
	 * Testing the set Y Acceleration method with negative input
	 */
	@Test
	public void setYAccelerationTestNegative() {
		PhysicsEntity test = new PhysicsEntityChild(1, 1, 1, 1, new World());

		test.setYAcceleration(-4);

		assertEquals("Y Acceleration", test.getYAcceleration(), -4);
	}
	
	/**
	 * Testing the set Y Acceleration method with zero
	 */
	@Test
	public void setYAccelerationTestZero() {
		PhysicsEntity test = new PhysicsEntityChild(1, 1, 1, 1, new World());

		test.setYAcceleration(0);

		assertEquals("Y Acceleration", test.getYAcceleration(), 0);
	}


	/**
	 * Testing the set X Acceleration method with negative input
	 */
	@Test
	public void setXAccelerationTestNegative() {
		PhysicsEntity test = new PhysicsEntityChild(0, 0, 0, 0, new World());

		test.setXAcceleration(-25);

		assertEquals("X Acceleration", test.getXAcceleration(), -25);
	}
	
	/**
	 * Testing the set X Acceleration method with positive input
	 */
	@Test
	public void setXAccelerationTestPositive() {
		PhysicsEntity test = new PhysicsEntityChild(0, 0, 0, 0, new World());

		test.setXAcceleration(25);

		assertEquals("X Acceleration", test.getXAcceleration(), 25);
	}
	
	/**
	 * Testing the set X Acceleration method with zero
	 */
	@Test
	public void setXAccelerationTestZero() {
		PhysicsEntity test = new PhysicsEntityChild(0, 0, 0, 0, new World());

		test.setXAcceleration(0);

		assertEquals("X Acceleration", test.getXAcceleration(), 0);
	}

	/**
	 * Values after 0 seconds of acceleration in X and Y
	 * 
	 */
	@Test
	public void zeroSecondAcceleration() {
		PhysicsEntity test = new PhysicsEntityChild(7, 17, 0, 0, new World());
		test.setxVelocity(11);
		test.setyVelocity(-4);
		test.setXAcceleration(15);
		test.setYAcceleration(9);

		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.update();
		// x and y coord should not change
		// x and y velocity should be set and equal to param given
		assertEquals("X Position", test.getxCoord(), 7);
		assertEquals("X Velocity", test.getxVelocity(), 11);

		assertEquals("Y Position", test.getyCoord(), 17);
		assertEquals("Y Velocity", test.getyVelocity(), -4);

	}


	/**
	 * Values after 1 second of acceleration in X and Y
	 * 
	 */
	@Test
	public void oneSecondAcceleration() {
		PhysicsEntity test = new PhysicsEntityChild(7, 17, 0, 0, new World());
		test.setxVelocity(11);
		test.setyVelocity(-4);
		test.setXAcceleration(15);
		test.setYAcceleration(9);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.update();
		assertEquals("X Position", test.getxCoord(), 26); // Calculated manually
		assertEquals("X Velocity", test.getxVelocity(), 26);

		assertEquals("Y Position", test.getyCoord(), 18);
		assertEquals("Y Velocity", test.getyVelocity(), 5);

	}

	/**
	 * Values after 1/2 second of acceleration in X and Y
	 */
	@Test
	public void HalfSecondAcceleration() {
		PhysicsEntity test = new PhysicsEntityChild(1, 2, 5, 78, new World());
		test.setxVelocity(10);
		test.setyVelocity(7);
		test.setXAcceleration(-4);
		test.setYAcceleration(10);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.update();
		assertEquals("X Position", test.getxCoord(), 6); // Calculated manually
		assertEquals("X Velocity", test.getxVelocity(), 8);

		assertEquals("Y Position", test.getyCoord(), 7);
		assertEquals("Y Velocity", test.getyVelocity(), 12);

	}

}
