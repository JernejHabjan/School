
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

  // var specularHighlights = document.getElementById("specular").checked;
  gl.uniform1i(shaderProgram.showSpecularHighlightsUniform, true);

  // Ligthing
  // var lighting = document.getElementById("lighting").checked;



  // set uniform to the value of the checkbox.
  gl.uniform1i(shaderProgram.useLightingUniform, true);

  // set uniforms for lights as defined in the document
  // if (lighting) {
    gl.uniform3f(
      shaderProgram.ambientColorUniform,
      parseFloat(document.getElementById("ambientR").value),
      parseFloat(document.getElementById("ambientG").value),
      parseFloat(document.getElementById("ambientB").value)
    );

    gl.uniform3f(
      shaderProgram.pointLightingLocationUniform,
      -0.3,
      4.0,
      -20.0
      // parseFloat(document.getElementById("lightPositionX").value),
      // parseFloat(document.getElementById("lightPositionY").value),
      // parseFloat(document.getElementById("lightPositionZ").value)
    );

    gl.uniform3f(
      shaderProgram.pointLightingSpecularColorUniform,
      0.8,
      0.8,
      0.8
      // parseFloat(document.getElementById("specularR").value),
      // parseFloat(document.getElementById("specularG").value),
      // parseFloat(document.getElementById("specularB").value)
    );

    gl.uniform3f(
      shaderProgram.pointLightingDiffuseColorUniform,
      0.67,
      0.43,
      0.66
      // parseFloat(document.getElementById("diffuseR").value),
      // parseFloat(document.getElementById("diffuseG").value),
      // parseFloat(document.getElementById("diffuseB").value)
    );
  // }

  // Textures
  // var texture = document.getElementById("texture").value;

  // set uniform to the value of the checkbox.
  gl.uniform1i(shaderProgram.useTexturesUniform, true);

  
  
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
  gl.bindTexture(gl.TEXTURE_2D, metalTexture);
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

  // rotacija kovancev
  // mvPushMatrix();
  // mat4.rotate(mvMatrix, degToRad(rotationCoin), [0, 0, 1]);

  for (var i in coins) {
    coins[i].draw();
  }


  // ce je zmankalo casa ali pobrali vse kovance, konec igre
  if (end) {
    document.getElementById("loadingtext").textContent = "Game Over, you have collected"+ " " + coinCounter + " " + "coins. Congratulations.";
  }


}

function drawCoin() {
  // mvPushMatrix();
  // mat4.rotate(mvMatrix, degToRad(rotationCoin), [0, 1, 0]);

  gl.bindTexture(gl.TEXTURE_2D, goldTexture);
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

  // mvPopMatrix();
}





//
// animate
//
// Called every time before redeawing the screen.
//
function animate() {
  var timeNow = new Date().getTime();
  var audio = new Audio('./assets/coinSound.mp3');
  if (lastTime != 0) {
    var elapsed = timeNow - lastTime; //calculate delta time

    // console.log("coinCounter: ", coinCounter);

    // rotationCoin += (75 * elapsed) / 1000.0;

     // ce so pobrani vsi kovanci, ali pa je zmankalo casa, je konec
     if (end) {
      timeNode = 0;
      return;
    }


    for (var i in coins) {
      // rotate the cube for a small amount
      coins[i].animate(elapsed);
    }

	// update walk animation
	if (speed != 0) {
      
    var xPositionTemp = xPosition - Math.sin(degToRad(yaw)) * speed * elapsed * 5;
    var zPositionTemp = zPosition - Math.cos(degToRad(yaw)) * speed * elapsed * 5;


    // pogleda, ce bi sel iz tal -> v zid
    if (checkCollision(xPositionTemp, zPositionTemp)) {
      xPosition -= Math.sin(degToRad(yaw)) * speed * elapsed;
      zPosition -= Math.cos(degToRad(yaw)) * speed * elapsed;
      
      // tu gledamo, ce smo pobrali kovanec
      for (var i = 0; i < coins.length; i++) {
        var l = Math.sqrt(Math.pow((xPositionTemp - coins[i].startCoordX), 2) + Math.pow((zPositionTemp - coins[i].startCoordZ), 2));
        if (l < 0.4) {
          // console.log("stevilo kovancev: ", coinCounter);
          coins.splice(i, 1);
          // kovanec je pobran, na novo zrisemo seznam kovancev, z enim manj
          coinCounter += 1;
          counterNode.nodeValue = coinCounter;
          // coinSound play

          // ce so vsi kovanci pobrani, se igra konca
          
          if (coinCounter == coins.length) {
            end = true;
          }

          audio.play();
          for (var i in coins) {
            coins[i].draw();
          }
        }
      }

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



