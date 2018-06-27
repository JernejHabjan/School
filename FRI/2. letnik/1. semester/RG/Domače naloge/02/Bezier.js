function Circle(x, y, type){
	this.type = type;
	this.x = x;
	this.y = y;
	this.d = 10;
	this.c = "#ff0000";
	this.boundingBox = false;
	this.joined = false;
}

function Quad(x, y, type){ //x,y sta zgoraj levo
	this.type = type;
	this.x = x;
	this.y = y;
	this.width = 20;
	this.height = this.width;
	this.c = "#0000ff";
	this.boundingBox = false;
	this.boundingBoxRectangle = false;
}

	
function Canvas(){
	var t = this;

	this.canvas;
	this.canvasE;
	this.width;
	this.height;

	this.drag = false;
    this.vertexToMove; //za premikat vertexe

    this.curveColor = "#006400";

    this.natancnostCurve = 20;

    this.boundingBoxArray = new Array(); //shranjeni vertexi z bounding boxi
    this.vertexArray = new Array(); //shranjene vse točke
    var vertexArray = this.vertexArray; // da ne pišem povsod this.vertexArray

    this.init = function() {
		this.canvasE = document.getElementById('can');
	    this.canvas= this.canvasE.getContext('2d');
	    this.width = this.canvasE.width;
	    this.height = this.canvasE.height;

	    this.canvasE.addEventListener('mousedown', this.mouseClicked, false);
    	this.canvasE.addEventListener('mouseup', this.mouseUp, false);
    	this.canvasE.addEventListener('mousemove', this.mouseMove, false);
    	this.canvasE.addEventListener ("mouseout", this.mouseUp, false);

	    setInterval(function(){t.loop();}, 15);

    }
    this.changeNatancnost = function(){
		var content = document.getElementById("myTextarea").value;
		content = parseInt(content);
		if(Number.isInteger(content)){
			this.natancnostCurve = content;
		}
    }

    this.smernikoeficient = function(e1, v, e2){ //blue 1, red 2, blue 3
    	try {
		    
			var k1 = -((v.y - e1.y)/ (v.x - e1.x));
			var k2 = -((e2.y - v.y)/ (e2.x - v.x));

			var e2xx = v.x+(v.x-e1.x);
			if(e1.x>=v.x){
				//k1 = -k1;
				e2xx = v.x-(e1.x-v.x);
			}
			

			var e2yy= -k1*(e2xx-v.x)+v.y;

			e2.y = e2yy;
			e2.x = e2xx;
		}catch(err) {
			
		}

	}

    this.loop = function() {
	    this.clear();

	    this.resetBoundingBox();//preverjanje boundingBoxa

	    this.draw();
	

		this.smernikoeficientUpdate();


		
	}

	this.smernikoeficientUpdate = function(){
		
		var rectIndex;
		for (var i = 0; i < vertexArray.length; i++) {
			if(vertexArray[i].boundingBoxRectangle) rectIndex=i;
		}

		for (var i = 0; i < vertexArray.length; i++) {
			var vertex = vertexArray[i];
			if(vertex.joined === true){ //ko ga najde
				if(i%4===1){
					if(rectIndex%4 ===2){
						this.smernikoeficient(vertexArray[i+5], vertex, vertexArray[i+2]);
					}else{
						this.smernikoeficient(vertexArray[i+2], vertex, vertexArray[i+5]);
					}

				}
				if(i===0){
					if(rectIndex%4 ===2){
						this.smernikoeficient(vertexArray[2],vertex, vertexArray[vertexArray.length-1]);
					}else{
						this.smernikoeficient(vertexArray[vertexArray.length-1],vertex, vertexArray[2]);
					}
					
				}

				
			}
		}
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

				this.canvas.fillText(i+1,vertex.x - 5,vertex.y - 5);

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

		    	this.canvas.fillText(i+1,vertex.x -vertex.d - 5,vertex.y -vertex.d - 5);
    		}
			
    	}

    	//POVEZAVA OGLIŠČ
    	this.connectVertices()

    }


    this.connectVertices = function(){
    	var veckratnik = vertexArray.length % 4;
    		
    	if(vertexArray.length > 1){
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
				    this.bezierCurve(this.natancnostCurve);
				}
    		}
	    	//3 VERTEXI
	    	var iteration3 = 0;
			for (var i = 0; i < vertexArray.length; i += 4) {

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
		    	}iteration3 += 1;
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

				for (t1 = 0.0; t1 <= 1.0001; t1 += 1.0/segments){

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
			    			
			    			if (this.containsObject(vertex, this.boundingBoxArray)){
			    				vertex.boundingBox = false;
			    			}else{
			    				for (var i = 0; i < vertexArray.length; i++) {
			    					vertexArray[i].boundingBoxRectangle=false;
			    				}
			    				vertex.boundingBoxRectangle = true;
			    			}
				        	
				        	this.drag = true;
				        	this.vertexToMove = vertex;
				        	return -1;
				        }
			    	}else{
			    		if (Math.abs(vertex.x - object.x) < vertex.width && Math.abs(vertex.y - object.y) < vertex.width) { 
				        	if (this.containsObject(vertex, this.boundingBoxArray)){
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
				    		if (this.containsObject(vertex, this.boundingBoxArray)){
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
				        	if (this.containsObject(vertex, this.boundingBoxArray)){
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
		this.canvas.strokeStyle = "#000000";
    	this.canvas.setLineDash([]);
	    this.canvas.fillStyle="#ffffff";
	    this.canvas.fillRect(0, 0, this.width, this.height);
	    this.canvas.fillStyle="#888888";
	    this.canvas.strokeRect(0, 0, this.width, this.height);

	}

	this.drawBoundingBox = function(x, y, d){
		this.canvas.beginPath();
		this.canvas.rect(x, y, d, d);
		this.canvas.stroke();
		this.canvas.closePath();

	}

	this.resetBoundingBox = function(){

		var oldBoundingBox = this.boundingBoxArray.slice();

		this.boundingBoxArray = []; // spraznemo array
    	for (var i = 0; i < vertexArray.length; i++) { //vsi k majo bound jih damo v array
    		if(vertexArray[i].boundingBox === true){
    			this.boundingBoxArray.push(vertexArray[i]);
    		}
		}

		if(this.boundingBoxArray.length > 2){ 
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


	this.checkIfSameCurve = function(ver1, ver2){
    	var index1;
		var index2;
		for (var i = 0; i < vertexArray.length; i++) {
			if(vertexArray[i]===this.boundingBoxArray[0]){
				index1=i;
			}
			if(vertexArray[i]===this.boundingBoxArray[1]){
				index2=i;
			}
		}

		return (index1+1)===index2?true:false;

    }

    this.joinCurves = function(){
    	if(this.boundingBoxArray.length === 2){
    		if(this.boundingBoxArray[0].type === "circle" && this.boundingBoxArray[1].type === "circle" ){
    			

    			if(this.checkIfSameCurve(this.boundingBoxArray[0], this.boundingBoxArray[1])===false){


		    		var middleX = (this.boundingBoxArray[0].x + this.boundingBoxArray[1].x) /2;
		    		var middleY = (this.boundingBoxArray[0].y + this.boundingBoxArray[1].y) /2;

		    		var newCircle = new Circle(middleX, middleY, "circle");
		    		newCircle.joined = true;
		    		newCircle.boundingBox = true;
		    		var newCircleReferenca = newCircle;

			   		for (var i = 0; i < vertexArray.length; i++) {//VN VRŽEŠ OBA VERTEXA
			   			if((this.boundingBoxArray[0] === vertexArray[i])){
			   				//tastarga na i mestu vn vržeš in prekriješ s tanovmu
			   				vertexArray[i] = newCircle; //DODAŠ NOV VERTEX
			   			}
			   			if((this.boundingBoxArray[1] === vertexArray[i])){
			   				vertexArray[i] = newCircleReferenca;
			   			}
			   		}

		   		}
	   		}		
    	}
    }
    
}