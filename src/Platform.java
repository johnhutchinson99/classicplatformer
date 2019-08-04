import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Platform{

	private ArrayList<Point2D> platformCoordinates = new ArrayList<Point2D>();
	
	
	/**
	 * 
	 * @param topLeftX X coordinate of top left corner of platform
	 * @param topLeftY Y coordinate of top left corner of platform
	 * @param width how far the platform extends in the X direction
	 * @param height how far the platform extends in the Y direction
	 */
	public Platform(int topLeftX,int topLeftY,int width,int height) {
		
		
		for(int x = topLeftX; x<=topLeftX+width;x++) { //Extends in X direction
			for(int y = topLeftY; y<=topLeftY+height;y++) {
				platformCoordinates.add(new Point2D(x,y));
			}
		}
		
		
		
	}
	
	/**
	 *  
	 * @param checkX X that is to be checked by the program
	 * @param checkY Y that is to be checked by the program
	 * @return Whether or not the inputed coordinates are part of the platform
	 */
	public boolean isPartOfPlatform(int checkX,int checkY) {
		for(Point2D point: platformCoordinates) {
			if(checkX==point.getX()) {
				if(checkY==point.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
}
