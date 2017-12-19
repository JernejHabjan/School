
//
// handleLoadedTeapot
//
// Handle loaded teapot
//
function handleLoadedTeapot(teapotData) {
  // Pass the normals into WebGL
  teapotVertexNormalBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexNormalBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(teapotData.normals), gl.STATIC_DRAW);
  teapotVertexNormalBuffer.itemSize = 3;
  teapotVertexNormalBuffer.numItems = teapotData.normals.length / 3;

  // Pass the texture coordinates into WebGL
  teapotVertexTextureCoordBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexTextureCoordBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(teapotData.uvs), gl.STATIC_DRAW);
  teapotVertexTextureCoordBuffer.itemSize = 2;
  teapotVertexTextureCoordBuffer.numItems = teapotData.uvs.length / 2;

  // Pass the vertex positions into WebGL
  teapotVertexPositionBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexPositionBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(teapotData.vertices), gl.STATIC_DRAW);
  teapotVertexPositionBuffer.itemSize = 3;
  teapotVertexPositionBuffer.numItems = teapotData.vertices.length / 3;

  // Pass the indices into WebGL
  teapotVertexIndexBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, teapotVertexIndexBuffer);
  gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(teapotData.faces), gl.STATIC_DRAW);
  teapotVertexIndexBuffer.itemSize = 1;
  teapotVertexIndexBuffer.numItems = teapotData.faces.length;

  document.getElementById("loadingtext").textContent = "";
}

//
// loadTeapot
//
// Load teapot
//
function loadTeapot() {
  var request = new XMLHttpRequest();
  request.open("GET", "./assets/map.json");
  request.onreadystatechange = function () {
    if (request.readyState == 4) {
      handleLoadedTeapot(JSON.parse(request.responseText));
    }
  }
  request.send();
}
