
var vertexArray = new Array(); //shranjene vse točke
var boundingBoxArray = new Array(); //shranjeni vertexi z bounding boxi

//TE VAR LAH NARDIM LOCAL -this.!!!!


function Circle(x, y, type){
	this.type = type;
	this.x = x;
	this.y = y;
	this.d = 10;
	this.c = "#ff0000";
	this.boundingBox = false;
}

function Quad(x, y, type){ //x,y sta zgoraj levo
	this.type = type;
	this.x = x;
	this.y = y;
	this.width = 20;
	this.height = this.width;
	this.c = "#0000ff";
	this.boundingBox = false;
}
//pr zlepku isti smerni koeficient

//POGLEJ SI LINEAR INTERPOLATION NA WIKI

	
function Canvas(){
	var t = this;

	this.canvas;
	this.canvasE;
	this.width;
	this.height;

	this.drag = false;
    this.vertexToMove; //za premikat vertexe

    this.curveColor;

    this.init = function() {
		this.canvasE = document.getElementById('can');
	    this.canvas= this.canvasE.getContext('2d');
	    this.width = this.canvasE.width;
	    this.height = this.canvasE.height;


	    this.canvasE.addEventListener('mousedown', this.mouseClicked, false);
    	this.canvasE.addEventListener('mouseup', this.mouseUp, false);
    	this.canvasE.addEventListener('mousemove', this.mouseMove, false);
    	this.canvasE.addEventListener ("mouseout", this.mouseUp, false);

	    //setInterval(function(){t.loop();}, 10); // tok mav zato d dela smooth premikanje
    	setInterval(function(){t.loop();}, 50);

    }

    this.loop = function() {
    	this.canvas.strokeStyle = "#000000";
    	this.canvas.setLineDash([]);
	    this.clear();
	    this.resetBoundingBox();//preverjanje boundingBoxa

	    this.draw();
	}
    

    this.draw = function() {
    	var vertex1 = -1;
    	for (var i = 0; i < vertexArray.length; i++) {
    		this.canvas.setLineDash([]);
    		var vertex = vertexArray[i];

    		if (vertex.type === "quad"){
    			this.canvas.beginPath();
    			var x = vertex.x;
		    	var y = vertex.y;
		    	var d = vertex.d;
		    	var c = vertex.c;
		    	var width = vertex.width;
		    	var height = vertex.height;
		    	var boundingBox = vertex.boundingBox;

    			this.canvas.fillStyle = c;
				this.canvas.fillRect(x, y, width, height);
				this.canvas.stroke();
				this.canvas.closePath();
				if(boundingBox){
					this.drawBoundingBox(vertex.x - 2.5, vertex.y - 2.5, vertex.width + 5);
				}

    		}else{
    			this.canvas.beginPath();
		    	
		    	var x = vertex.x;
		    	var y = vertex.y;
		    	var d = vertex.d;
		    	var c = vertex.c;
		    	var boundingBox = vertex.boundingBox;
		    
		    	this.canvas.arc(x,y,d,0,Math.PI*2,true);
		    	this.canvas.fillStyle = c;
		    	this.canvas.fill();
			    this.canvas.closePath();
		    
			    if(boundingBox){
		    		this.drawBoundingBox(vertex.x - vertex.d , vertex.y - vertex.d, vertex.d * 2);
		    	}
    		}
			
    	}

    	//POVEZAVA OGLIŠČ
    	this.connectLines()

    }


    this.connectLines = function(){
    	var veckratnik = vertexArray.length % 4;
    		
    	if(vertexArray.length > 1){


    		//KRIVULJE
    		for (var i = 0; i < vertexArray.length; i += 4) {
    			
    			if(i+1 < vertexArray.length){

		    		var vertex1 = vertexArray[i];
		    		var vertex2 = vertexArray[i + 1];

		    		//NARIŠEŠ CURVE
		    		this.canvas.setLineDash([]);
		    		// set line color
		   			this.canvas.strokeStyle = "#070707";
		   			this.canvas.lineWidth = 2;
		    		this.drawLine(vertex1, vertex2);



		    	}

	    	}
		    var iteration4 = 0;
	    	//4 VERTEXI
			for (var i = 0; i < vertexArray.length; i += 4) {

				if(i+3 < vertexArray.length){ //če 4 vertexi

					iteration4+=1;
					

    				var vertex1 = vertexArray[i];
    				var vertex2 = vertexArray[i + 1];
    				var vertexQuad1 = vertexArray[i + 2];
    				var vertexQuad2 = vertexArray[i + 3];
    				//NARIŠEŠ CURVE
					this.canvas.lineWidth = 2;
					this.canvas.setLineDash([5, 15]);

				    // set line color
				    this.canvas.strokeStyle = "#000000";
				    this.drawLine(vertex1, vertexQuad1);
				    this.drawLine(vertexQuad1, vertexQuad2);
				    this.drawLine(vertex2, vertexQuad2);


				    //NARDIŠ BEZIER

				    this.bezierCurve(19);
		    		

				}
    		}


	    	//3 VERTEXI
	    	var iteration3 = 0;
			for (var i = 0; i < vertexArray.length; i += 4) {
				

				// vertexarray 11
				// iteration4 2

				if(iteration4 <= iteration3){

					if(i+2 < vertexArray.length){ //če 3 vertexi

	    				var vertex1 = vertexArray[i];
	    				var vertex2 = vertexArray[i + 1];
	    				var vertexQuad = vertexArray[i + 2];

	    				//NARIŠEŠ CURVE
						this.canvas.lineWidth = 2;
						this.canvas.setLineDash([5, 15]);

					    // set line color
					    this.canvas.strokeStyle = "#000000";
					    this.drawLine(vertex1, vertexQuad);
					    this.drawLine(vertex2, vertexQuad);
		    		}
	    		
		    	}

	    		iteration3 += 1;
    		}
    			
    	}
    	
    }

    this.bezierCurve = function(segments){
    	for (var i = 0; i < vertexArray.length; i+=4) {

    		if(i+3 < vertexArray.length){ //če 4 vertexi
				var p0 = vertexArray[i];
				var p1 = vertexArray[i + 1];
				var p2 = vertexArray[i + 2];
				var p3 = vertexArray[i + 3];


	
				var xOld = p0.x;
				var yOld = p0.y;

				this.canvas.setLineDash([]);
				this.canvas.strokeStyle = this.curveColor;
				this.canvas.lineWidth = 5;

				for (t1 = 0.0; t1 <= 1.0; t1 += 1.0/segments){
					console.log(Number(t1) === t1 && t1 % 1 !== 0);

					var x = Math.pow((1 - t1), 3) * p0.x + 3 * Math.pow((1 - t1), 2) * t1 * p2.x + 3 * (1 -t1) * Math.pow(t1, 2) * p3.x + Math.pow(t1, 3) * p1.x;
					var y = Math.pow((1 - t1), 3) * p0.y + 3 * Math.pow((1 - t1), 2) * t1 * p2.y + 3 * (1 -t1) * Math.pow(t1, 2) * p3.y + Math.pow(t1, 3) * p1.y;

			


					this.canvas.beginPath();
					this.canvas.moveTo(xOld, yOld);
					this.canvas.lineTo(x, y);
					this.canvas.stroke();
					this.canvas.closePath();





					yOld = y;
					xOld = x;




				}
			}

		}
    }

    this.drawLine = function(fromVertex, toVertex){
    	this.canvas.beginPath();
		this.canvas.moveTo(fromVertex.x, fromVertex.y);
		this.canvas.lineTo(toVertex.x, toVertex.y);
		this.canvas.stroke();
		this.canvas.closePath();
    }







	this.mouseUp = function() {
	    t.drag = false;
	}

	this.mouseMove = function(e) {
		if(t.drag){
	        var x = e.clientX - t.canvasE.offsetLeft;
	        var y = e.clientY - t.canvasE.offsetTop;

	        t.vertexToMove.x = x;
	        t.vertexToMove.y = y;
	    }
	}

    this.mouseClicked = function(e) {
		var currX = e.clientX - t.canvasE.offsetLeft;
		var currY = e.clientY - t.canvasE.offsetTop;

		if (vertexArray.length % 4 === 2 || vertexArray.length % 4 === 3){ 
			var newQuad = t.checkCollison(new Quad(currX, currY, "quad"));
			if (newQuad != -1){ // če ni prišlo do collisiona
				vertexArray.push(newQuad);
			}
		}else{//KROG

			var newCircle = t.checkCollison(new Circle(currX, currY, "circle"));

			if (newCircle != -1){ // če ni prišlo do collisiona
				vertexArray.push(newCircle);
			}
			
		}

    }

    this.containsObject = function(obj, list) {
	    for (var i = 0; i < list.length; i++) {
	        if (list[i] === obj) {
	            return true;
	        }
	    }

	    return false;
	}

    this.checkCollison = function(object){
    	for (var i = 0; i < vertexArray.length; i++) {
			var vertex = vertexArray[i];

			switch(vertex.type) {
			    case "quad":

			    	if(object.type === "circle"){

			    		if (this.intersects(object, vertex) === true){
			    			
			    			if (this.containsObject(vertex, boundingBoxArray)){
			    				vertex.boundingBox = false;
			    			}else{
			    				vertex.boundingBox = true;
			    			}
				        	
				        	this.drag = true;
				        	this.vertexToMove = vertex;
				        	return -1;
				        }
			    	}else{
			    		if (Math.abs(vertex.x - object.x) < vertex.width && Math.abs(vertex.y - object.y) < vertex.width) { 
				        	if (this.containsObject(vertex, boundingBoxArray)){
			    				vertex.boundingBox = false;
			    			}else{
			    				vertex.boundingBox = true;
			    			}
				        	this.drag = true;
				        	this.vertexToMove = vertex;
				        	return -1;
				        }
			    	}
			        


			        break;
			    case "circle":
			   		if(object.type === "circle"){
				        var distance = Math.sqrt(Math.pow((object.x - vertex.x), 2) + Math.pow((object.y - vertex.y), 2)); 
				    	if (distance <= vertex.d + object.d){
				    		if (this.containsObject(vertex, boundingBoxArray)){
			    				vertex.boundingBox = false;
			    			}else{
			    				vertex.boundingBox = true;
			    			}
				    		this.drag = true;
				    		this.vertexToMove = vertex;
				    		return -1;
				    	}
				    }else{
				    	if (this.intersects(vertex, object) === true){
				        	if (this.containsObject(vertex, boundingBoxArray)){
			    				vertex.boundingBox = false;
			    			}else{
			    				vertex.boundingBox = true;
			    			}
				        	this.drag = true;
				        	this.vertexToMove = vertex;
				        	return -1;
				        }
				    }
				    break; 
			      
			}
		}
		return object;
    }


    this.intersects = function(circle, rect){
	    var circleDistanceX = Math.abs(circle.x - circle.d - rect.x);
	    var circleDistanceY = Math.abs(circle.y - circle.d - rect.y);

	    if (circleDistanceX > (rect.width/2 + circle.d)) { return false; }
	    if (circleDistanceY > (rect.height/2 + circle.d)) { return false; }

	    if (circleDistanceX <= (rect.width/2)) { return true; } 
	    if (circleDistanceY <= (rect.height/2)) { return true; }

	    var cornerDistance_sq = Math.pow((circleDistanceX - rect.width/2),2) +
	                         Math.pow((circleDistanceY - rect.height/2),2);


	    return (cornerDistance_sq <= (Math.pow(circle.d,2)));
	}

    this.clear = function() {

	    this.canvas.fillStyle="#ffffff";
	    this.canvas.fillRect(0, 0, this.width, this.height);
	    this.canvas.fillStyle="#888888";
	    this.canvas.strokeRect(0, 0, this.width, this.height);

	}

	this.drawBoundingBox = function(x, y, d){
		this.canvas.beginPath();
		//this.canvas.setLineDash([5]);

		this.canvas.rect(x, y, d, d);
		this.canvas.stroke();
		this.canvas.closePath();

	}

	this.resetBoundingBox = function(){

		var oldBoundingBox = boundingBoxArray.slice();

		boundingBoxArray = []; // spraznemo array
    	for (var i = 0; i < vertexArray.length; i++) { //vsi k majo bound jih damo v array
    		if(vertexArray[i].boundingBox === true){
    			boundingBoxArray.push(vertexArray[i]);
    		}
		}

		if(boundingBoxArray.length > 2){ 
			oldBoundingBox[0].boundingBox = false;
		}
    }

    this.changeColor = function(){

    	this.curveColor = this.getRandomColor();
    }

    this.getRandomColor = function() {
	    var letters = '0123456789ABCDEF';
	    var color = '#';
	    for (var i = 0; i < 6; i++ ) {
	        color += letters[Math.floor(Math.random() * 16)];
	    }
	    return color;
	}

    this.joinVertexes = function(){

    	
    	if(boundingBoxArray.length === 2){
    		if(boundingBoxArray[0].type === "circle" && boundingBoxArray[1].type === "circle" ){


	    		var middleX = (boundingBoxArray[0].x + boundingBoxArray[1].x) /2;
	    		var middleY = (boundingBoxArray[0].y + boundingBoxArray[1].y) /2;

	    		var newCircle = new Circle(middleX, middleY, "circle");
	    		newCircle.boundingBox = true;
	    		

		   		for (var i = 0; i < vertexArray.length; i++) {//VN VRŽEŠ OBA VERTEXA
		   			if((boundingBoxArray[0] === vertexArray[i])){
		   				//tastarga na i mestu vn vržeš in prekriješ s tanovmu
		   				vertexArray[i] = newCircle; //DODAŠ NOV VERTEX
		   			}
		   			if((boundingBoxArray[1] === vertexArray[i])){
		   				vertexArray.splice(i, 1); //tega vn vržeš
		   			}

		   		}
	   		}
    			
    	}
   		

    }


}