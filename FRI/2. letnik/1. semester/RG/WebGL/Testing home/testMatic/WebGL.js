
var gl = null;
var screenWidth, screenHeight;
var matrix = new Matrix();
var shader = new Shader();
var cube = new Cube(0.5);
var oldTime = new Date().getTime() / 500.0;
var texture = -1;
var mipmaps = false;

function handleTextureLoaded(image, tex) {
		gl.bindTexture(gl.TEXTURE_2D, tex);
		gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, image);
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);

		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, mipmaps ? 
			gl.LINEAR_MIPMAP_NEAREST : gl.LINEAR);
		if(mipmaps)gl.generateMipmap(gl.TEXTURE_2D);

		gl.texParameteri(gl.TEXTURE_2D, gl.WRAP_T, gl.CLAMP_TO_EDGE);
		gl.texParameteri(gl.TEXTURE_2D, gl.WRAP_S, gl.CLAMP_TO_EDGE);
		gl.bindTexture(gl.TEXTURE_2D, null);
	}

function initTextures(){
		texture = gl.createTexture();
		img = new Image();
		img.crossOrigin = "";
		img.onload = function() { handleTextureLoaded(img, texture); }
		img.src = "http://i.imgur.com/iUCeYF8.png";
	}


function initGL(canvas) {
  try {
    gl = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
  }catch(e) {}

  if (!gl) {
    alert("WebGL unsupported al neki");
    gl = null;
  }
  initTextures();
}

function initWebGL(){
	//window.location.reload(true);
	var context = document.getElementById("context");
	initGL(context);
	screenWidth = context.width;
	screenHeight = context.height;

	if (gl) {
		shader.initShaders("shader-vert", "shader-frag");
		cube.initBuffers(gl);
	    gl.enable(gl.DEPTH_TEST);
	    gl.enable(gl.BLEND);
	    gl.blendFunc(gl.SRC_ALPHA, gl.ONE_MINUS_SRC_ALPHA);

		gl.clearColor(0.0, 1.0, 1.0, 1.0);
		matrix.setMatrixMode(matrix.PROJECTION_MATRIX);
		matrix.loadIdentity();
	    matrix.perspective(60.0, screenWidth / screenHeight, 0.1, 10000.0);
	    matrix.setMatrixMode(matrix.MODEL_MATRIX);
	    matrix.loadIdentity();

	    draw();
  }
}

function draw(){
	gl.clearColor(1.0, 1.0, 1.0, 1.0);
	gl.viewport(0, 0, screenWidth, screenHeight);
	gl.clear(gl.GL_COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    gl.useProgram(shader.program);
	matrix.loadIdentity();

    var time = new Date().getTime() / 500.0 - oldTime; 
    time = (time % 25.0).toFixed(3);
    document.getElementById("time").innerHTML = "Time: " + time + " (Resets at 25.0s)";

    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture);
    gl.uniform1i(gl.getUniformLocation(shader.program, "texture"), 0);

    for(a = -2; a <= 2; a++)
    	for(b = -2; b <= 2; b++){
    		//matrix.rotate(time * (360/25.0), Math.random(), 1.0, 1.0);
    		matrix.rotate(time , Math.random(), Math.random(), Math.random() * 1.5);
    		matrix.translate(a * 0.8, b * 0.6, -2.0);
		    matrix.loadUniforms(shader.program);
		    cube.draw(gl, shader.program);
		    matrix.translate(-a * 0.8, -b * 0.6, 2.0);
    	}
    

	setTimeout(function(){requestAnimationFrame(draw);}, 1000 / 30 ); 
}
