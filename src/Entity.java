

public class Entity {
public int x;
public int y;



public Entity(int startX,int startY) {
	x = startX;
	y = startY;
}



public void setX(int newX) {
	x = newX;
}
public void setY(int newY) {
	y = newY;
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}


public String toString() {
	return (x+","+y);
}


}
