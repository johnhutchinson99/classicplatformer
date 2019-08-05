import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Platform extends Entity{


	private Point2D topLeft;
	
	private Point2D bottomRight;
	
	
	/**
	 * 
	 * @param topLeftX X coordinate of top left corner of platform
	 * @param topLeftY Y coordinate of top left corner of platform
	 * @param width how far the platform extends in the X direction
	 * @param height how far the platform extends in the Y direction
	 */
	public Platform(int topLeftX,int topLeftY,int width,int height) {
		
	
		super(width,height);
		setPhysics(new Physics(topLeftX,topLeftY));
		topLeft = new Point2D(topLeftX,topLeftY);
		bottomRight = new Point2D(topLeftX+width,topLeftY+height);
		
		
	}
	
	
	
	
}
