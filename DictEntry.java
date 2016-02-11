//Author: Neeraja Murali Dharan 
//Last Modified: November 20th, 2015
//FileName: DictEntry.java
//Description: Class represents a data item stored in the binary search tree

//each DictEntry object consists of two parts; Position and int color
public class DictEntry {
	protected Position pos; 
	protected int colour_value;

	//constructor returns a new DictEntry object with specified coordinates and color
	public DictEntry (Position p, int color){
		this.pos=p;
		colour_value=color;
	}
	//returns the Position in the DictEntry 
	public Position getPosition(){
		return pos; 
	}
	//returns the color in the DictEntry 
	public int getColor(){ 
		return colour_value;
	}
}
