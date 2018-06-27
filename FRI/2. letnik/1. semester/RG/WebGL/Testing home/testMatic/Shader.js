function Shader(){
	this.program = null;

	this.initShaders = function(vsID, fsID){
		var vShader = this.loadShader(gl, vsID);
		var fShader = this.loadShader(gl, fsID);
		this.program = gl.createProgram();
		gl.attachShader(this.program, vShader);
		gl.attachShader(this.program, fShader);
		gl.linkProgram(this.program);
		if (!gl.getProgramParameter(this.program, gl.LINK_STATUS)) {
            alert("Error loading shader");
        }
	}

	this.loadShader = function(gl, id){
		var shaderString = document.getElementById(id);
		if(!shaderString) return null;

		var string = "";
		var child = shaderString.firstChild;
		while(child){
			if(child.nodeType == 3){
				string += child.textContent;
			}child = child.nextSibling;
		}

		var sh;
		switch(shaderString.type){
			case "vertex-shader": sh = gl.createShader(gl.VERTEX_SHADER); break;
			case "fragment-shader": sh = gl.createShader(gl.FRAGMENT_SHADER); break;
			default: alert("wrong shader type"); return null;
		}

		gl.shaderSource(sh, string);
		gl.compileShader(sh);
		 if (!gl.getShaderParameter(sh, gl.COMPILE_STATUS)) {
            alert(id + ": " + gl.getShaderInfoLog(sh));
            return null;
        }return sh;
	}
}