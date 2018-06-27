function Tile(x, y, width, height, depth){
	this.x = x; //levo kot
	this.y = y; //levo kot
	this.width = width;
	this.height = height;
	this.depth = depth; //recursionDepth

	this.circleArray = new Array(); //childrenCircles

	this.childrenT = new Array(); //childrenTiles
}