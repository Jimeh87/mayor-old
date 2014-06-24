package objects;

import specification.property.PropertySpecification;



public class Cursor{
	
	//Fields
	private CursorType cursorType;
	private Integer xLocation;
	private Integer yLocation;
	private PropertySpecification defaultSpecification;
	
	//Construct
	public Cursor(CursorType cursorType) {
		this.cursorType = cursorType;
	}
	
	//Methods
	public CursorType getCursorType() {
		return cursorType;
	}
	public void setCursorType(CursorType cursorType) {	
		this.cursorType = cursorType;
	}
	public Integer getXLocation(){
		return xLocation;
	}
	public void setXLocation(Integer xLocation){
		this.xLocation = xLocation;
	}
	public Integer getYLocation(){
		return yLocation;
	}
	public void setYLocation(Integer yLocation){
		this.yLocation = yLocation;
	}

	public void setDefaultSpecification(PropertySpecification defaultSpecification) {
		this.defaultSpecification = defaultSpecification;
	}
	
	public PropertySpecification getDefaultSpecification() {
		return defaultSpecification;
	}


	
}
