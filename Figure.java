//Author: Neeraja Murali Dharan 
//Date Last Modified: November 20th 2015
//ClassName: Figure.java
//Description: Defines variable values of a figure and checks if a move is valid 

public class Figure implements FigureADT{
	protected int id; //identifier of figure 
	protected int width; //width of enclosing rectangle
	protected int height; //height of enclosing rectangle
	protected int type;//fixed figure, moved by user, moved by computer or target figure 
	protected Position pos;//offset of figure 
	protected BinarySearchTree bst;//binary search tree 
	
	//constructor for the class figure returns a Figure object 
	public Figure(int id, int width, int height, int type, Position pos){
		this.id=id; 
		this.width=width; 
		this.height=height; 
		this.type=type; 
		this.pos=pos;
		this.bst=new BinarySearchTree();//empty binary search tree created 
	}
	//returns a BinarySearchTree object 
	public BinarySearchTree getBST(){
		return this.bst;
	}
	
	//sets type of figure ot specified value 
	public void setType(int type){
		this.type=type;
	}
	//returns width of enclosing rectangle of this figure 
	public int getWidth(){
		return this.width;
	}
	//returns height of enclosing rectangle of this figure 
	public int getHeight(){
		return this.height;
	}
	//returns type of this figure 
	public int getType(){
		return this.type;
	}
	//returns offset of this figure 
	public int getId(){
		return this.id; 
	}
	//returns id of this figure 
	public Position getOffset(){
		return this.pos; 
	}
	//changes offset of this figure to a specified value 
	public void setOffset(Position value){
		this.pos=value;
	}
	
	/*this method chreates a DictEnry to represent the given pixel and 
	 * insert it into the binary tree associated with this figure. 
	 * Throws an exception if an error occurs when inserting 
	 */
	public void addPixel(int x, int y, int color)throws BSTException{
		Position position=new Position(x,y);
		DictEntry entry=new DictEntry(position,color);
		try{
			bst.insert(entry);
		}
		catch(BSTException e){
			System.out.println("Error:insertion could not be performed");
		}
	}
	
	/*this method returns true if this figure intersects the figure 
	 * specified in the parameter, and false otherwise 
	 */
	public boolean intersects(Figure fig){
		//checks if the enclosing rectangles intersect 
		Position upperLeftComparison=fig.getOffset(); 
		Position lowerRightComparison=new Position(upperLeftComparison.getX(),fig.getHeight()+upperLeftComparison.getY());
		Position upperLeft=fig.getOffset(); 
		Position lowerRight=new Position(upperLeft.getX(), this.getHeight()+upperLeft.getY());
		
		if (upperLeftComparison.getY()>upperLeft.getY()&&lowerRightComparison.getY()<lowerRight.getY()){
			return false;
		}
		
		//checks if any pixel in this figure overlaps the with the figure sent in as a parameter  
		else{
			
			DictEntry entry=bst.smallest();
			DictEntry next=entry; 
			Position pixelValue;
			int pixely,pixelx;
			
			while (bst.successor(next.getPosition())!=null){
				pixelx=next.getPosition().getX()+this.getOffset().getX();
				pixely=next.getPosition().getY()+this.getOffset().getY();
				pixelValue=new Position(pixelx-fig.getOffset().getX(), pixely-fig.getOffset().getY());
				if (fig.getBST().find(pixelValue)!=null){
					return true; 
				}
				next=bst.successor(next.getPosition());
			}
			
		}
		return false;
	}

}
		
	

