function Canvas(){
	var t = this;

	this.canvas;
	this.width;
	this.height;

	this.circleContainer = new Array(); //TUKEJ SO SHRANJENI VSI CIRCLI ZA REDRAW
		//ko se cleara cev quad tree da dodeliš nodom spet circle

	this.MAX_RECURSION_DEPTH = 4;
	this.MAX_CIRCLES_PER_NODE = 5;

	this.init = function() {
	    this.canvasE = document.getElementById('canvas');
	    this.canvas= this.canvasE.getContext('2d');
	    this.width = this.canvasE.width;
	    this.height = this.canvasE.height;

	    this.canvasE.addEventListener("mousedown", this.addCircle, false);

	    this.tile = new Tile(0,0,this.width,this.height, 0);
	    setInterval(function(){t.loop();}, 50);

	}

	this.addCircle = function(e) {
		t.circleContainer.push(new Circle(e.clientX, e.clientY));
	}


	this.drawLines = function(tile){ //rekurzivno gre skoz child node od tila in riše črte
		var x = tile.x;
		var y = tile.y;
		var height = tile.height;
		var width = tile.width;
		var depth = tile.depth;

		if (tile.circleArray.length > this.MAX_CIRCLES_PER_NODE){ 	//draw lines for tile
			this.canvas.beginPath();
			//vertikalna črta
	    	this.canvas.moveTo(x + width/2, y);
			this.canvas.lineTo(x + width/2, y + height);
			this.canvas.stroke();
			//horizontalna črta
			this.canvas.moveTo(x, y+ height/2);
			this.canvas.lineTo(x + width, y+ height/2);
	    	this.canvas.stroke();

		    this.canvas.closePath();
		}


		if (tile.circleArray.length > this.MAX_CIRCLES_PER_NODE){ //omeji min 5 circlu pol razdeli 
			if (tile.depth <this.MAX_RECURSION_DEPTH){//omeji rekurzijo
				if (tile.childrenT.length === 0){ //appends kids to tile
			    	console.log("added new tiles");
					tile.childrenT[0] = new Tile(x, y, width/2, height/2, depth+1);//upper left
					tile.childrenT[1] = new Tile(x+ width/2, y, width/2, height/2, depth+1);//upper right
					tile.childrenT[2] = new Tile(x, y+ height/2, width/2, height/2, depth+1);//lower left
					tile.childrenT[3] = new Tile(x+ width/2, y+ height/2, width/2, height/2, depth+1);//lower right
					
					//premik krogcev v kide
					var tempCircleArray = tile.circleArray;
					for (var i = 0; i < tempCircleArray.length; i++) { //skoz vse krogce
						
						var circle = tempCircleArray[i];
						for (var j = 0; j < tile.childrenT.length; j++) {//skoz vse njegove kide
							var child = tile.childrenT[j];

							if ( (child.x < (circle.x + circle.d))  && ((circle.x + circle.d < child.x + child.width)) && (child.y < (circle.y + circle.d)) && ((circle.y + circle.d) < child.y + child.height) ){
								console.log("circle moved to child x:" + child.x + ", y:"+child.y);
								tile.childrenT[j].circleArray.push(circle);
								//tile.circleArray.splice(i, 1);  //NE BRIŠEM JIH IZ PARENTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

							}

						}
						
					}
					

			    }

			}
			
		}

		for (var i = 0; i < tile.childrenT.length; i++) {
			this.drawLines(tile.childrenT[i]); //še za vse kide nardi line
		}

	}

	//max recursion depth= 4;
	this.loop = function() {

		console.log(this.mousedownID);

	    this.clear();

	    this.tile.circleArray = this.circleContainer; 

	    this.drawLines(this.tile);  

	    this.recursiveDraw(this.tile); //loopa skoz vse node in izrisuje njihove krogce

	    this.testRecursiveCollision(this.tile); 
	}






	this.reDraw = function(tile){


		for (var i = 0; i < tile.circleArray.length; i++) {
	    	this.canvas.beginPath();

	    	var obj = tile.circleArray[i];
	    	var x = obj.x;
	    	var y = obj.y;
	    	var d = obj.d;
	    	var dx = obj.dx;
	    	var dy = obj.dy;
	    	var c = obj.c;

	    	this.canvas.arc(x,y,d,0,Math.PI*2,true);
	    	this.canvas.fillStyle=c;
	    	this.canvas.fill();
		    this.canvas.closePath();
		    		

		    // move circle on new location
		    if( x< (d - dx) + 1 || x > this.width - (d + dx) - 1){
		    	dx = -dx;
		    	tile.circleArray[i].dx= dx; //assign new speed
		    } 
		    if( y< (d - dx) + 1 || y > this.height - (d + dy) - 1){
		    	dy = -dy;
		    	tile.circleArray[i].dy = dy; //assign new speed
		    }
		    tile.circleArray[i].x += dx;
		    tile.circleArray[i].y += dy;    
	    }
	}

	this.recursiveDraw = function(tile){
		this.reDraw(tile); //redraw circles in this grid
		

		for (var i = 0; i < tile.childrenT.length; i++) {
			this.recursiveDraw(tile.childrenT[i]); //and circles in lower nodes
		}
		return;
	}

	this.testRecursiveCollision = function(tile){ //da testa collision samo v temu tilu
		if (tile.circleArray.length === 0 || tile.circleArray.length ===1){
			return;
		}else{
			this.testCollision(tile); ///test collison za ta tile
		}

		for (var i = 0; i < tile.childrenT.length; i++) {
			this.testRecursiveCollision(tile.childrenT[i]); //and circles in lower nodes
		}
		return;
	}

	this.testCollision = function(tile){ // ne dela good
		for (var i = 0; i < tile.circleArray.length; i++) {
			var obj1= tile.circleArray[i];
			var x1 = obj1.x;
	    	var y1 = obj1.y;
	    	var d1 = obj1.d;

			var collide = false;
			for (var j = 0; j < tile.circleArray.length; j++) {
		    	var obj2 = tile.circleArray[j];
		    	var x2 = obj2.x;
		    	var y2 = obj2.y;
		    	var d2 = obj2.d;

		    	if (obj1 != obj2){  //da ne preverja istga krogca
		    		var distance = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));

			    	if (distance <= d1+d2){
			    		obj1.c = "#ff0000";
			    		obj2.c = "#ff0000";
			    		collide = true;
			    	}
		    	}
			}if(collide === false){
				obj1.c = "#0000ff";
			}
		}
	}



	




	this.clear = function() {
	    this.canvas.fillStyle="#ffffff";
	    this.canvas.fillRect(0, 0, this.width, this.height);
	    this.canvas.fillStyle="#888888";
	    this.canvas.strokeRect(0, 0, this.width, this.height);

	    //clear quadtree:
	    this.tile.childrenT = []; //sprazniš parent array
	    this.tile.circleArray = []; //in circle not v njemu
	}

}
