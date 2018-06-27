
function Transformation(){
	var originalMatrix = [1, 0, 0, 0,
						  0, 1, 0, 0,
						  0, 0, 1, 0,
						  0, 0, 0, 1];

	this.mArray = originalMatrix;
	this.m = new Matrix4f(this.mArray); //začetna matrica - skupna


	this.resetTransformationMatrix = function(){
		this.mArray = originalMatrix;
		this.m = new Matrix4f(this.mArray);
	}

	this.degreesToRadians = function(degrees){
		return degrees * (Math.PI / 180);
	}

	this.translate = function(v){
		var translateMatrixArray=[1, 0, 0, v.x,
								  0, 1, 0, v.y,
								  0, 0, 1, v.z,
								  0, 0, 0, 1];
		var translateMatrix = new Matrix4f(translateMatrixArray);
		this.m = globalMatrix.multiplyM4f(translateMatrix, this.m);
	}

	this.scale = function(v){
		var scaleMatrixArray=[v.x, 0, 0, 0,
							  0, v.y, 0, 0,
							  0, 0, v.z, 0,
							  0, 0, 0, 1];
		var scaleMatrix = new Matrix4f(scaleMatrixArray);
		this.m = globalMatrix.multiplyM4f(scaleMatrix, this.m);
	}

	this.rotateX = function(degrees){
		var radians = this.degreesToRadians(degrees);
		var rotateXMatrixArray=[1, 0,                  0,                 0,
								0, Math.cos(radians), -Math.sin(radians), 0,
								0, Math.sin(radians),  Math.cos(radians), 0,
								0, 0,                  0,                 1];
		var rotateXMatrix = new Matrix4f(rotateXMatrixArray);
		this.m = globalMatrix.multiplyM4f(rotateXMatrix, this.m);
	}

	this.rotateY = function(degrees){
		var radians = this.degreesToRadians(degrees);
		var rotateYMatrixArray=[Math.cos(radians),  0, Math.sin(radians), 0,
								0,                  1, 0,                 0,
								-Math.sin(radians), 0, Math.cos(radians), 0,
								0,                  0, 0,                 1];
		var rotateYMatrix = new Matrix4f(rotateYMatrixArray);
		this.m = globalMatrix.multiplyM4f(rotateYMatrix, this.m);
	}

	this.rotateZ = function(degrees){
		var radians = this.degreesToRadians(degrees);
		var rotateZMatrixArray=[Math.cos(radians), -Math.sin(radians), 0, 0,
								Math.sin(radians), Math.cos(radians),  0, 0,
								0,                 0,                  1, 0,
								0,                 0,                  0, 1];
		var rotateZMatrix = new Matrix4f(rotateZMatrixArray);
		this.m = globalMatrix.multiplyM4f(rotateZMatrix, this.m);
	}

	this.transformPoint = function(v){
		var newVector = new Array();
		var mArray = this.m.matrixArray;
		for(var i = 0; i < mArray.length; i+=4){
			newVector.push(globalVector.dotProductV4f(new Vector4f(mArray[i], mArray[i+1], mArray[i+2], mArray[i+3]), v));
		}

		return new Vector4f(newVector[0], newVector[1], newVector[2], newVector[3]);
	}

}

