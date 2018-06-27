function Matrix4f(matrixArray){
	this.matrixArray=matrixArray;

	this.negateM4f = function(m){
		var newArray = new Array();
		for (coord in m.matrixArray){
			newArray.push(-m.matrixArray[coord]);
		}
		return new Matrix4f(newArray);
	}

	this.addM4f = function(m1, m2){
		var newArray = new Array();
		for (coord in m1.matrixArray){
			newArray.push(m1.matrixArray[coord] + m2.matrixArray[coord]);
		}
		return new Matrix4f(newArray);
	}

	this.transposeM4f = function(m){
		//ustvarim prazno matrico
		var mArray=[0,0,0,0,
					0,0,0,0,
					0,0,0,0,
					0,0,0,0];
		var matrix = new Matrix4f(mArray);
		for (var i = 0; i < m.matrixArray.length; i++) {
			var row = parseInt(i / 4);
			var column = i % 4;
			matrix.matrixArray[row * 4 + column] = m.matrixArray[column * 4 + row];
		}
		return matrix;
	}

	this.multiplyScalarM4f = function(scalar, m){
		var newArray = new Array();
		for (coord in m.matrixArray){
			newArray.push(scalar * m.matrixArray[coord]);
		}
		return new Matrix4f(newArray);
	}

	this.multiplyM4f = function(m1, m2){
		var mArray=[0,0,0,0,
					0,0,0,0,
					0,0,0,0,
					0,0,0,0];
		var matrix = new Matrix4f(mArray);

		for (var i = 0; i < m1.matrixArray.length; i++){
			var row = parseInt(i / 4);
			var column = i % 4;
			var tempSum=0;
			for (var j = 0; j < 4; j++){
				tempSum += m1.matrixArray[row*4+j]  * this.transposeM4f(m2).matrixArray[column*4+j];
			}
			matrix.matrixArray[i] = tempSum;
		}
		return matrix;
	}
}

