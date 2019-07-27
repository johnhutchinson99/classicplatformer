
public class enemyType1 extends Enemy{  //enemy type1:move around in an area, go back when touch Obstructs

	boolean canLeft = true;		
	boolean canRight = true;
	boolean onLand = false;
	boolean isLeft = true;
	Thread t = new Thread();
	
	@Override
	public void move() {
		while (true) {
			//javafx shape
			//move speed
			for (int i = 0; i < getAllObstruction().size(); i++) {
				Obstrution ob = getAllObstruction().get(i);
				// cannot move left
				if (ob.getX() == ob size < this.y && ob size > this.y)) {
					canRight = false;
				}
				// cannot move right
				if (ob.getX() ==    < this.y &&   > this.y)) {
					canLeft = false;
				}
				
				//turn back if touch Obstruct
				if (this.isLeft && !canLeft || this.x == 0) { 
					this.isLeft = false;
				} else if (this.isLeft && !canRight || this.x == 840) {
					this.isLeft = true;
				}
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void run() {
		move();
	}
	
	public enemyType1(int x,int y) {
		//x y
		//size

		t.start();
		t.suspend();
	}

}
