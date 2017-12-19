
//
// drawScene
//
// Draw the scene.
//
function drawScene() {
  // set the rendering environment to full canvas size
  gl.viewport(0, 0, gl.viewportWidth, gl.viewportHeight);
  // Clear the canvas before we start drawing on it.
  gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

  if (teapotVertexPositionBuffer == null || teapotVertexNormalBuffer == null || teapotVertexTextureCoordBuffer == null || teapotVertexIndexBuffer == null) {
    return;
  }
  
  // Establish the perspective with which we want to view the
  // scene. Our field of view is 45 degrees, with a width/height
  // ratio of 640:480, and we only want to see objects between 0.1 units
  // and 100 units away from the camera.
  mat4.perspective(45, gl.viewportWidth / gl.viewportHeight, 0.1, 100.0, pMatrix);

  var specularHighlights = document.getElementById("specular").checked;
  gl.uniform1i(shaderProgram.showSpecularHighlightsUniform, specularHighlights);

  // Ligthing
  var lighting = document.getElementById("lighting").checked;

  // set uniform to the value of the checkbox.
  gl.uniform1i(shaderProgram.useLightingUniform, lighting);

  // set uniforms for lights as defined in the document
  if (lighting) {
    gl.uniform3f(
      shaderProgram.ambientColorUniform,
      parseFloat(document.getElementById("ambientR").value),
      parseFloat(document.getElementById("ambientG").value),
      parseFloat(document.getElementById("ambientB").value)
    );

    gl.uniform3f(
      shaderProgram.pointLightingLocationUniform,
      parseFloat(document.getElementById("lightPositionX").value),
      parseFloat(document.getElementById("lightPositionY").value),
      parseFloat(document.getElementById("lightPositionZ").value)
    );

    gl.uniform3f(
      shaderProgram.pointLightingSpecularColorUniform,
      parseFloat(document.getElementById("specularR").value),
      parseFloat(document.getElementById("specularG").value),
      parseFloat(document.getElementById("specularB").value)
    );

    gl.uniform3f(
      shaderProgram.pointLightingDiffuseColorUniform,
      parseFloat(document.getElementById("diffuseR").value),
      parseFloat(document.getElementById("diffuseG").value),
      parseFloat(document.getElementById("diffuseB").value)
    );
  }

  // Textures
  var texture = document.getElementById("texture").value;

  // set uniform to the value of the checkbox.
  gl.uniform1i(shaderProgram.useTexturesUniform, texture != "none");

  
  // Set the drawing position to the "identity" point, which is
  // the center of the scene.
  mat4.identity(mvMatrix);

  // Now move the drawing position a bit to where we want to start
  // drawing the world.
  // mat4.translate(mvMatrix, [0, 0, -60]);
  //mat4.rotate(mvMatrix, degToRad(23.4), [1, 0, -1]);
  //mat4.rotate(mvMatrix, degToRad(teapotAngle), [0, 1, 0]);
  mat4.rotate(mvMatrix, degToRad(-pitch), [1, 0, 0]);
  mat4.rotate(mvMatrix, degToRad(-yaw), [0, 1, 0]);
  mat4.translate(mvMatrix, [-xPosition, -yPosition, -zPosition]);

  
  
  
  // Activate textures
  gl.activeTexture(gl.TEXTURE0);
  if (texture == "earth") {
    gl.bindTexture(gl.TEXTURE_2D, earthTexture);
  } else if (texture == "galvanized") {
    gl.bindTexture(gl.TEXTURE_2D, metalTexture);
  }
  gl.uniform1i(shaderProgram.samplerUniform, 0);

  // Activate shininess
  gl.uniform1f(shaderProgram.materialShininessUniform, parseFloat(document.getElementById("shininess").value));

  // Set the vertex positions attribute for the teapot vertices.
  gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexPositionBuffer);
  gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, teapotVertexPositionBuffer.itemSize, gl.FLOAT, false, 0, 0);

  // Set the texture coordinates attribute for the vertices.
  gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexTextureCoordBuffer);
  gl.vertexAttribPointer(shaderProgram.textureCoordAttribute, teapotVertexTextureCoordBuffer.itemSize, gl.FLOAT, false, 0, 0);

  // Set the normals attribute for the vertices.
  gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexNormalBuffer);
  gl.vertexAttribPointer(shaderProgram.vertexNormalAttribute, teapotVertexNormalBuffer.itemSize, gl.FLOAT, false, 0, 0);

  // Set the index for the vertices.
  gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, teapotVertexIndexBuffer);
  setMatrixUniforms();

  // Draw the teapot
  gl.drawElements(gl.TRIANGLES, teapotVertexIndexBuffer.numItems, gl.UNSIGNED_SHORT, 0);
}







//
// animate
//
// Called every time before redeawing the screen.
//
function animate() {
  var timeNow = new Date().getTime();
  if (lastTime != 0) {
    var elapsed = timeNow - lastTime; //calculate delta time

    // rotate the teapot for a small amount
    // teapotAngle += 0.05 * elapsed;
	
	// update walk animation
	if (speed != 0) {
      xPosition -= Math.sin(degToRad(yaw)) * speed * elapsed;
      zPosition -= Math.cos(degToRad(yaw)) * speed * elapsed;

      joggingAngle += elapsed * 0.6; // 0.6 "fiddle factor" - makes it feel more realistic :-)
      yPosition = Math.sin(degToRad(joggingAngle)) / 20 - 0.3
    }
	//update rotation
    yaw += yawRate * elapsed;
    pitch += pitchRate * elapsed;
	
	
  }
  lastTime = timeNow;
}

