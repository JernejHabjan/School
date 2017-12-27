
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
  // tukaj v faces 3 cifre predstavljajo en trikotnik
  // TODO: narediti array teh trikotnikov in podati, ce si znotraj 
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

// TODO: pravilno izrisi kovance

// Handle loaded Coins
//
function handleLoadedCoins(coinData) {
  // Pass the normals into WebGL
  coinVertexNormalBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, coinVertexNormalBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(coinData.normals), gl.STATIC_DRAW);
  coinVertexNormalBuffer.itemSize = 3;
  coinVertexNormalBuffer.numItems = coinData.normals.length / 3;
  // console.log(coinData.faces);

  // Pass the texture coordinates into WebGL
  coinVertexTextureCoordBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, coinVertexTextureCoordBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(coinData.uvs), gl.STATIC_DRAW);
  coinVertexTextureCoordBuffer.itemSize = 2;
  coinVertexTextureCoordBuffer.numItems = coinData.uvs.length / 2;

  // Pass the vertex positions into WebGL
  coinVertexPositionBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, coinVertexPositionBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(coinData.vertices), gl.STATIC_DRAW);
  coinVertexPositionBuffer.itemSize = 3;
  coinVertexPositionBuffer.numItems = coinData.vertices.length / 3;

  // Pass the indices into WebGL
  // tukaj v faces 3 cifre predstavljajo en trikotnik
  // TODO: narediti array teh trikotnikov in podati, ce si znotraj 
  coinVertexIndexBuffer = gl.createBuffer();
  gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, coinVertexIndexBuffer);
  gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(coinData.faces), gl.STATIC_DRAW);
  coinVertexIndexBuffer.itemSize = 1;
  coinVertexIndexBuffer.numItems = coinData.faces.length;

  // document.getElementById("loadingtext").textContent = "";
}

// TODO: vec coinov daj v mapo, potem pa da jih poberas!!!

function loadCoins() {
  var request = new XMLHttpRequest();
  request.open("GET", "./assets/probaTRI.json");
  request.onreadystatechange = function () {
    if (request.readyState == 4) {
      handleLoadedCoins(JSON.parse(request.responseText));
    }
  }
  request.send();
}



// funkcija za dobivanje koordinat tal

function getPositions(floorData) {
  // console.log(indides);
  var postitions = floorData.vertices;
  
  trianglePositions = [];
  var curr = [];
  var j = 0;

  for (var k = 0; k < floorData.faces.length; k++) {
    indides.push(floorData.faces[k]);
  }

  // dobimo pozicije trikotnikov

  for (var i = 0; i < postitions.length; i++) {
    if (j == 0 || j == 1 || j == 2) {
      curr.push(postitions[i]);
      j += 1;
      if (j == 3) {
        trianglePositions.push(curr);
        curr = [];
        j = 0;
      }
    }
  }

  // console.log(trianglePositions);

}

// detekcija kolizij

function checkCollision(xPosition, zPosition) {
  var x1;
  var z1;
  var x2;
  var z2;
  var x3;
  var z3;
  // console.log("inddies: ", indides);
  // console.log(trianglePositions);
  for (var i = 0; i < indides.length; i += 3) {
    // console.log(indides.length);
    var oglisce1 = indides[i];
    var oglisce2 = indides[i + 1];
    var oglisce3 = indides[i + 2];

    x1 = trianglePositions[oglisce1][0];
    z1 = trianglePositions[oglisce1][2];

    x2 = trianglePositions[oglisce2][0];
    z2 = trianglePositions[oglisce2][2];

    x3 = trianglePositions[oglisce3][0];
    z3 = trianglePositions[oglisce3][2];
    
    var p1 = surfaceTriangle(x1, z1, x2, z2, x3, z3);
    
    var smallP1 = surfaceTriangle(xPosition, zPosition, x2, z2, x3, z3);

    var smallP2 = surfaceTriangle(x1, z1, xPosition, zPosition, x3, z3); 

    var smallP3 = surfaceTriangle(x1, z1, x2, z2, xPosition, zPosition);

    // izracunas ploscine manjsih trikotnikov in pogledas, ce je vecja od glavnega trikotnika
    // ce je vecja, pomenio, da si izven tal -> v zidu

    var sumSmall = smallP1 + smallP2 + smallP3;

    // console.log("sumSmall: ", sumSmall);
    // console.log("P1: ", p1);

    // ni collisina
    if (Math.abs(sumSmall - p1) < 0.000001) {
      return true;
    }
  }

  // ce ne, je collision
  return false;
}


function loadFloor() {
  var request = new XMLHttpRequest();
  request.open("GET", "./assets/tla2.json");
  request.onreadystatechange = function () {
    if (request.readyState == 4) {
      getPositions(JSON.parse(request.responseText));
    }
  }
  request.send();
}

