Mat4Identity = [[1, 0, 0, 0], [0, 1, 0, 0], [0, 0, 1, 0], [0, 0, 0, 1]];
Mat3Identity = [[1, 0, 0], [0, 1, 0], [0, 0, 1]];

function copyMat4(mat){
	var copyArray = [];
	for(i = 0; i < 4; i++){
		copyArray[i] = mat[i].slice();
	}return copyArray;
}

function Mat4(){
	this.matrix = copyMat4(Mat4Identity);

	this.set = function(mat){
		this.matrix = mat;
	}
	
	this.getSingleArrayMatrix = function(){
		m = [];
		for(i = 0; i < 4; i++){
			for(j = 0; j < 4; j++){
				m.push(this.matrix[i][j]);
			}
		}return m;
	}

	this.print = function(){
		value = "<br>";
		for(i = 0; i < 4; i++){
			value += this.matrix[i] + "<br>";
		}document.getElementById("matrix").innerHTML = value;
	}

	this.multiply = function(matrix){
		var tmpMat = copyMat4(this.matrix);
		for (i = 0; i < 4; i++){
			for (j = 0; j < 4; j++){ 
				sum = 0; 
				for (k = 0; k < 4; k++){
					sum += tmpMat[i][k] * matrix[k][j];
				}this.matrix[i][j] = sum;
			}
		} 	
	}
}