function Matrix(){
	this.PROJECTION_MATRIX = 0;
	this.MODEL_MATRIX = 1;
	this.VIEW_MATRIX = 2;

	this.modelMatrix = new Mat4();
	this.viewMatrix = new Mat4();
	this.projectionMatrix = new Mat4();
	this.matrixMode = this.MODEL_MATRIX;

	this.setMatrixMode = function(mode){
		switch(mode){
			case this.MODEL_MATRIX: case this.VIEW_MATRIX: case this.PROJECTION_MATRIX: 
			this.matrixMode = mode; break;
			default: alert("Wrong matrix mode"); break;
		}
	}

 	this.loadIdentity = function(){
 		switch(this.matrixMode){
 			case this.MODEL_MATRIX: this.modelMatrix.set(copyMat4(Mat4Identity)); break;
 			case this.VIEW_MATRIX: this.viewMatrix.set(copyMat4(Mat4Identity)); break;
 			case this.PROJECTION_MATRIX: this.projectionMatrix.set(copyMat4(Mat4Identity)); break;
 			default: alert("Wrong matrix type ??? wtf"); break;
 		}
 	}

 	this.perspective = function(FOV, aspectRatio, nearPlane, farPlane){
 		var range = Math.tan((FOV / 2.0) * (3.1415 / 180.0)) * nearPlane;	
		var left = -range * aspectRatio;
		var right = range * aspectRatio;
		var bottom = -range;
		var top = range;

		mat = new Mat4();
		mat.matrix[0][0] = (2.0 * nearPlane) / (right - left);
		mat.matrix[1][1] = (2.0 * nearPlane) / (top - bottom);
		mat.matrix[2][2] = - (farPlane + nearPlane) / (farPlane - nearPlane);
		mat.matrix[2][3] = - 1.0;
		mat.matrix[3][2] = - (2.0 * farPlane * nearPlane) / (farPlane - nearPlane);

 		this.projectionMatrix.multiply(mat.matrix);
 	}

 	this.translate = function(x, y, z){
 		translationMatrix = new Mat4();
 		translationMatrix.matrix[3][0] = x;
 		translationMatrix.matrix[3][1] = y;
 		translationMatrix.matrix[3][2] = z;
 		this.matrixMode == this.MODEL_MATRIX ? 
 			this.modelMatrix.multiply(translationMatrix.matrix) : 
 			this.viewMatrix.multiply(translationMatrix.matrix);
 	}

 	this.scale = function(x, y, z){
 		this.modelMatrix.matrix[0][0] *= x;
 		this.modelMatrix.matrix[1][1] *= y;
 		this.modelMatrix.matrix[2][2] *= z;
 	}

 	this.rotate = function(angle, x, y, z){
 		rotationMatrix = new Mat4();
 		v = new Vec3(x, y, z); //rotation axis
 		v.normalize();

 		var c = Math.cos(angle * (3.1415 / 180.0));
		var s = Math.sin(angle * (3.1415 / 180.0));

		rotationMatrix.matrix[0][0] = v.x * v.x * (1.0 - c) + c;
		rotationMatrix.matrix[1][0] = v.x * v.y * (1.0 - c) - v.z * s;
		rotationMatrix.matrix[2][0] = v.x * v.z * (1.0 - c) + v.y * s;
		rotationMatrix.matrix[0][1] = v.y * v.x * (1.0 - c) + v.z * s;
		rotationMatrix.matrix[1][1] = v.y * v.y * (1.0 - c) + c;
		rotationMatrix.matrix[2][1] = v.y * v.z * (1.0 - c) - v.x * s;
		rotationMatrix.matrix[0][2] = v.x * v.z * (1.0 - c) - v.y * s;
		rotationMatrix.matrix[1][2] = v.y * v.z * (1.0 - c) + v.x * s;
		rotationMatrix.matrix[2][2] = v.z * v.z * (1.0 - c) + c;
 		this.modelMatrix.multiply(rotationMatrix.matrix)
 	}

 	this.rotateX = function(angle){
 		rotationMatrix = new Mat4();
 		rotationMatrix.matrix[1][1] = Math.cos(-angle * (3.1415 / 180.0));
 		rotationMatrix.matrix[2][2] = rotationMatrix.matrix[1][1];
 		rotationMatrix.matrix[1][2] = Math.sin(-angle * (3.1415 / 180.0));
 		rotationMatrix.matrix[2][1] = -rotationMatrix.matrix[1][2];
 		this.modelMatrix.multiply(rotationMatrix.matrix);
 	}
 	this.rotateY = function(angle){
 		rotationMatrix = new Mat4();
 		rotationMatrix.matrix[0][0] = Math.cos(angle * (3.1415 / 180.0));
 		rotationMatrix.matrix[2][2] = rotationMatrix.matrix[0][0];
 		rotationMatrix.matrix[0][2] = Math.sin(angle * (3.1415 / 180.0));
 		rotationMatrix.matrix[2][0] = -rotationMatrix.matrix[0][2];
 		this.modelMatrix.multiply(rotationMatrix.matrix);
 	}
 	this.rotateZ = function(angle){
 		rotationMatrix = new Mat4();
 		rotationMatrix.matrix[0][0] = Math.cos(angle * (3.1415 / 180.0));
 		rotationMatrix.matrix[1][1] = rotationMatrix.matrix[0][0];
 		rotationMatrix.matrix[1][0] = Math.sin(angle * (3.1415 / 180.0));
 		rotationMatrix.matrix[0][1] = -rotationMatrix.matrix[1][0];
 		this.modelMatrix.multiply(rotationMatrix.matrix);
 	}

 	this.loadUniforms = function(programId){

 		this.modelMatrix.print();

 		gl.useProgram(programId);
 		gl.uniformMatrix4fv(gl.getUniformLocation(programId, "projectionMatrix"), 
 			false, this.projectionMatrix.getSingleArrayMatrix());
 		gl.uniformMatrix4fv(gl.getUniformLocation(programId, "modelMatrix"), 
 			false, this.modelMatrix.getSingleArrayMatrix());
 		gl.uniformMatrix4fv(gl.getUniformLocation(programId, "viewMatrix"), 
 			false, this.viewMatrix.getSingleArrayMatrix());
 	}
}

