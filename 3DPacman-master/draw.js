
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
  
  if (coinVertexPositionBuffer == null || coinVertexNormalBuffer == null || coinVertexTextureCoordBuffer == null || teapotVertexIndexBuffer == null) {
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


  // TODO: izrisi kovance, iz blenderja moras pravilno exportati

  // coini
  gl.bindBuffer(gl.ARRAY_BUFFER, coinVertexPositionBuffer);
  gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, coinVertexPositionBuffer.itemSize, gl.FLOAT, false, 0, 0);

  // Set the texture coordinates attribute for the vertices.
  gl.bindBuffer(gl.ARRAY_BUFFER, coinVertexTextureCoordBuffer);
  gl.vertexAttribPointer(shaderProgram.textureCoordAttribute, coinVertexTextureCoordBuffer.itemSize, gl.FLOAT, false, 0, 0);

  // Set the normals attribute for the vertices.
  gl.bindBuffer(gl.ARRAY_BUFFER, coinVertexNormalBuffer);
  gl.vertexAttribPointer(shaderProgram.vertexNormalAttribute, coinVertexNormalBuffer.itemSize, gl.FLOAT, false, 0, 0);

  // Set the index for the vertices.
  gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, coinVertexIndexBuffer);
  setMatrixUniforms();

  // Draw the coin
  gl.drawElements(gl.TRIANGLES, coinVertexIndexBuffer.numItems, gl.UNSIGNED_SHORT, 0);
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
      
    var xPositionTemp = xPosition - Math.sin(degToRad(yaw)) * speed * elapsed * 5;
    var zPositionTemp = zPosition - Math.cos(degToRad(yaw)) * speed * elapsed * 5;

    // console.log(trianglePositions);

    // pogleda, ce bi sel iz tal -> v zid
    if (checkCollision(xPositionTemp, zPositionTemp)) {
      xPosition -= Math.sin(degToRad(yaw)) * speed * elapsed;
      zPosition -= Math.cos(degToRad(yaw)) * speed * elapsed;

      // joggingAngle += elapsed * 0.6; // 0.6 "fiddle factor" - makes it feel more realistic :-) 
      yPosition = Math.sin(degToRad(joggingAngle)) / 20 - 0.3;
    }
    }
  //update rotation
    // if (xPosition )
    yaw += yawRate * elapsed;
    pitch += pitchRate * elapsed;
	
	
  }
  lastTime = timeNow;
}

// collision detection part
// TODO: gres cez vse trikotnike, preveris ploscine treh trikotnikov znotraj glavnega

// ploscina trikotnika
function surfaceTriangle(x1, z1, x2, z2, x3, z3) {
  var a = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((z3 - z2), 2));
  var b = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((z3 - z1), 2));
  var c = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((z2 - z1), 2));
  // console.log(a, b, c);

  var s = (a + b + c) / 2;

  var surface = Math.sqrt(s * (s - a) * (s - b) * (s - c));

  return surface;
}


// console.log(surfaceTriangle(1, 2, 3, 3, 4, 2));

// TODO: checkCollision ->
// cez vse trikotnike
// vzames notranje tri, izracunas za vsakega ploscino
// sestejes ploscino in primerjas z ploscino glavnega trikotnika
// ce je enaka (ali manjša?), si znotraj trikotnika
// ce je vecja, si zunaj tal -> si v zidu



// function checkCollision(x, y, z) {

// }

