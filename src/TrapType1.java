
public class TrapType1 extends Enemy {  //This trap is move up and move down(underground)
	
	private boolean isUp = true;
	private int upMax = 0;
	private int downMax = 0;
	
	//Thread t = new Thread();

	@Override
	public void move() {
		if (this.isUp) {
			this.y -= height;
		} else {
			this.y += height;
		}
		
		if (this.isUp && this.y == this.upMax) {
			this.isUp = false;
		}
		if (!this.isUp && this.y == this.downMax) {
			this.isUp = true;
		}

	}
	
	/*
	@Override
	public void run() {
		move();
	}
	*/
	
	public TrapType1(int x, int y, boolean isUp, int upMax, int downMax) {
		super(x,y);
		this.isUp = isUp;
		this.upMax = upMax;
		this.downMax = downMax;	
	}
	
	public TrapType1(TrapType1 toCopy){
		super(toCopy.getX(),toCopy.getY());
		this.isUp = toCopy.isUp;
		this.upMax = toCopy.upMax;
		this.downMax = toCopy.downMax;	
	}
}
