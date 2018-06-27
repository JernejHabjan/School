function PointManager(object, m1, v1){
	//common vertex array
	this.vertexArray = new Array(); 
	this.modifiedArray = new Array();

	this.object = object;


	this.createTransformationMatrix = function(){ //makes transformation matrix
		var area= document.getElementById("transformTextArea").innerHTML.replace( /\n/g, " " ).split(" ");

		for (var i = 0; i < area.length; i++) {
			//console.log(area[i]);
			if(isNaN(area[i])){ //ko je beseda
				switch (area[i]){
					case "translate":
						var x = area[i+1];
						var y = area[i+2];
						var z = area[i+3];
						var w = 0;  //PAZI VEKTOR
						object.translate(new Vector4f(x,y,z,w));
						i+=3; //da skippa 3 indexe

						console.log("translate updated matrix to:\n"+object.m.matrixArray.toString());
						break;
					case "scale":
						var x = area[i+1];
						var y = area[i+2];
						var z = area[i+3];
						object.scale(new Vector4f(x,y,z));
						i+=3; //da skippa 3 indexe

						console.log("scale updated matrix to: \n"+object.m.matrixArray.toString());
						break;

					case "rotateX":
						var degrees = area[i+1];
						object.rotateX(degrees);
						i+=1;

						console.log("rotateX updated matrix to:\n"+object.m.matrixArray.toString());
						break;
					case "rotateY":
						var degrees = area[i+1];
						object.rotateY(degrees);
						i+=1;
						
						console.log("rotateY updated matrix to:\n"+object.m.matrixArray.toString());
						break;
					case "rotateZ":
						var degrees = area[i+1];
						object.rotateZ(degrees);
						i+=1;
						
						console.log("rotateZ updated matrix to:\n"+object.m.matrixArray.toString());
						break;
					default:
						console.log("error reading transformation");
						break;
				} 
			}
		}
	}

	this.transformVertexMatrix = function(){
		for (var i = 0; i < this.vertexArray.length; i++) {
			this.modifiedArray.push(object.transformPoint(this.vertexArray[i]));
			//console.log(object.transformPoint(this.vertexArray[i]));
		}
	}


	this.writeInTextArea = function(){
		var strJoin = "";
		for (var i = 0; i < this.modifiedArray.length; i++) {
			//alert(this.modifiedArray[i].x);
			if (i === this.modifiedArray.length-1){
				strJoin += "v " + this.modifiedArray[i].x + " " + this.modifiedArray[i].y + " " + this.modifiedArray[i].z;
			}else{
				strJoin += "v " + this.modifiedArray[i].x + " " + this.modifiedArray[i].y + " " + this.modifiedArray[i].z + "\n";
			}
			
		}
		document.getElementById("myTextarea").value = strJoin;
	}

	this.readPoints = function(){
		var textAreaContent = document.getElementById("myTextarea").value;

		document.getElementById("myTextarea").value = ""; //clears

		var contentArray = textAreaContent.replace( /\n/g, " " ).split(" ");
		
		for (var i = 0; i <contentArray.length; i+=4) {
			if(contentArray[i] === "v"){
				var x = contentArray[i + 1];
				var y = contentArray[i + 2];
				var z = contentArray[i + 3];

				//izpiše vertexe samo do errorja
				if ((!isNaN(x)) && (!isNaN(y)) && (!isNaN(z))){
					this.vertexArray.push(new Vector4f(x, y, z, 1)); //PREDSTAVITEV TOČKE Z VEKTORJEM???????????
									//TOČKA ma 1, VECTOR 0
					
				}
			}
		}
	}

	this.writeVertex = function(){
		//clear arrays:
		this.modifiedArray = [];
		this.vertexArray = [];

		this.readPoints();
		this.createTransformationMatrix();
		this.transformVertexMatrix();
		this.writeInTextArea();
	}

	

}
