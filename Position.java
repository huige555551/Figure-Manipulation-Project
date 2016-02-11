
public class Position {
	protected int x_pos; 
	protected int y_pos;
	
	public Position (int x, int y){
		this.x_pos=x; 
		this.y_pos=y;
	}
	public int getX(){ 
		return x_pos; 
	}
	public int getY(){ 
		return y_pos; 
	}
	/*public int compareTo(Position p){
		if (this.y_pos==p.getY()){
			if (this.x_pos==p.getX()){
				return 0;
			}
			else if (this.x_pos>p.getX()){
				return 1;
			}
			else{
				return -1;
			}
		}
		else if (this.y_pos>p.getY()){
			return 1; 
		}
		else{
			return -1;
		}
	}*/
	public int compareTo(Position p){
		if((this.getY() < p.getY()) || (this.getY() == p.getY() && this.getX() < p.getX())){
			return -1;
		} else if(this.getY() == p.getY() && this.getX() == p.getX()){
			return 0;
		} else{
			return 1;
		}
	}
	
}

