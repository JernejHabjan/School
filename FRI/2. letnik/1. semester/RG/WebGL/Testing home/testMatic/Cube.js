
function Cube(s){
	this.NUM_TRIANGLES = 36;
	this.SIZE = s;
	this.VBO = -1;
	this.IND = -1;
	this.CLR = -1;
	this.NOR = -1;
	this.UV = -1;

	this.vertices = [
		//FRONT
		-this.SIZE/2.0, -this.SIZE/2.0, this.SIZE/2.0,    
		-this.SIZE/2.0, this.SIZE/2.0, this.SIZE/2.0,    
	 	this.SIZE/2.0, this.SIZE/2.0, this.SIZE/2.0, 
	 	this.SIZE/2.0, -this.SIZE/2.0, this.SIZE/2.0,

	 	//BACK
	 	-this.SIZE/2.0, -this.SIZE/2.0, -this.SIZE/2.0,    
		-this.SIZE/2.0, this.SIZE/2.0, -this.SIZE/2.0,    
	 	this.SIZE/2.0, this.SIZE/2.0, -this.SIZE/2.0, 
	 	this.SIZE/2.0, -this.SIZE/2.0, -this.SIZE/2.0,

	 	//TOP
	 	-this.SIZE/2.0, this.SIZE/2.0, this.SIZE/2.0,    
		-this.SIZE/2.0, this.SIZE/2.0, -this.SIZE/2.0,    
	 	this.SIZE/2.0, this.SIZE/2.0, -this.SIZE/2.0, 
	 	this.SIZE/2.0, this.SIZE/2.0, this.SIZE/2.0,

	 	//BOTTOM
	 	-this.SIZE/2.0, -this.SIZE/2.0, this.SIZE/2.0,    
		-this.SIZE/2.0, -this.SIZE/2.0, -this.SIZE/2.0,    
	 	this.SIZE/2.0, -this.SIZE/2.0, -this.SIZE/2.0, 
	 	this.SIZE/2.0, -this.SIZE/2.0, this.SIZE/2.0,

	 	//LEFT
	 	-this.SIZE/2.0, this.SIZE/2.0, -this.SIZE/2.0,    
		-this.SIZE/2.0, -this.SIZE/2.0, -this.SIZE/2.0,    
	 	-this.SIZE/2.0, -this.SIZE/2.0, this.SIZE/2.0, 
	 	-this.SIZE/2.0, this.SIZE/2.0, this.SIZE/2.0,

	 	//RIGHT
	 	this.SIZE/2.0, this.SIZE/2.0, -this.SIZE/2.0,    
		this.SIZE/2.0, -this.SIZE/2.0, -this.SIZE/2.0,    
	 	this.SIZE/2.0, -this.SIZE/2.0, this.SIZE/2.0, 
	 	this.SIZE/2.0, this.SIZE/2.0, this.SIZE/2.0
	];
	this.indices = [
		0, 1, 2, 0, 2, 3,
		4, 5, 6, 4, 6, 7,
		8, 9, 10, 8, 10, 11,
		12, 13, 14, 12, 14, 15,
		16, 17, 18, 16, 18, 19,
		20, 21, 22, 20, 22, 23
	];
	this.colors = [
		0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
		0.0, 0.5, 1.0, 0.0, 0.5, 1.0, 0.0, 0.5, 1.0, 0.0, 0.5, 1.0,
		1.0, 0.5, 0.0, 1.0, 0.5, 0.0, 1.0, 0.5, 0.0, 1.0, 0.5, 0.0,
		1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,
		1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0,
		0.5, 0.0, 1.0, 0.5, 0.0, 1.0, 0.5, 0.0, 1.0, 0.5, 0.0, 1.0
	];
	this.normals = [
		0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0,
		0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 
		0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 
		0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 
		-1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, 
		1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0
	];
	this.uvs=[ //size = xy
		0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
		0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
		0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
		0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
		0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
		0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0
	];

	

	this.initBuffers = function(gl){
		this.VBO = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, this.VBO);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(this.vertices), gl.STATIC_DRAW);

		this.NOR = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, this.NOR);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(this.normals), gl.STATIC_DRAW);

		this.UV = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, this.UV);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(this.uvs), gl.STATIC_DRAW);

		this.CLR = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, this.CLR);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(this.colors), gl.STATIC_DRAW);

		this.IND = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, this.IND);
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(this.indices), gl.STATIC_DRAW);

		gl.bindBuffer(gl.ARRAY_BUFFER, null);
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, null);
	}

	this.draw = function(gl, programID){
		var vertex = gl.getAttribLocation(programID, "vertex");
    	var color = gl.getAttribLocation(programID, "color");
    	var normal = gl.getAttribLocation(programID, "normal");
    	var uv = gl.getAttribLocation(programID, "uv");
    	
    	gl.uniform3f(gl.getUniformLocation(programID, "lightPosition"), 0.0, 0.0, 0.0);

		gl.enableVertexAttribArray(vertex);
	    gl.bindBuffer(gl.ARRAY_BUFFER, this.VBO);
	    gl.vertexAttribPointer(vertex, 3, gl.FLOAT, false, 0, 0);

	    gl.enableVertexAttribArray(color);
	    gl.bindBuffer(gl.ARRAY_BUFFER, this.CLR);
	    gl.vertexAttribPointer(color, 3, gl.FLOAT, false, 0, 0);

	    gl.enableVertexAttribArray(normal);
	    gl.bindBuffer(gl.ARRAY_BUFFER, this.NOR);
	    gl.vertexAttribPointer(normal, 3, gl.FLOAT, false, 0, 0);

	    gl.enableVertexAttribArray(uv);
	    gl.bindBuffer(gl.ARRAY_BUFFER, this.UV);
	    gl.vertexAttribPointer(uv, 2, gl.FLOAT, false, 0, 0);

	    gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, this.IND);
	    gl.drawElements(gl.TRIANGLES, this.NUM_TRIANGLES, gl.UNSIGNED_SHORT, 0);
	}
}
