function Circle(x,y){
	this.x = x;
	this.y = y;
	this.d = getRandom(10,20);
	this.dx = getSpeed();
	this.dy = getSpeed();
	this.c = "#0000ff";

	function getRandom(min, max) {
	    return Math.random() * (max - min) + min;
	}

	function getSpeed(){
		var rand= getRandom(1,3);
		return (Math.random() >= 0.5)? rand: -rand;
	}


}