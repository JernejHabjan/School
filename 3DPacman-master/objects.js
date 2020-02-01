
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

// coin class

function Coin(startCoordinatesX, startCoordinatesZ) {
  this.startCoordX = startCoordinatesX;
  this.startCoordZ = startCoordinatesZ; 
  this.angle = 0;
  this.rotationSpeed = 0.5;
}


Coin.prototype.draw = function() {
  mvPushMatrix();

  // Move to the star's position
  // mat4.rotate(mvMatrix, degToRad(this.angle), [0.0, 0.0, 1.0]);
  mat4.translate(mvMatrix, [this.startCoordX, 0.0, this.startCoordZ]);

  // // Rotate back so that the star is facing the viewer
  mat4.rotate(mvMatrix, degToRad(-this.angle), [0.0, 1.0, 0.0]);
  // mat4.rotate(mvMatrix, degToRad(-tilt), [1.0, 0.0, 0.0]);

  // mat4.translate(mvMatrix, [this.startCoordX, 0.0, this.startCoordZ]);

  // All coins spin around the Y axis at the same rate
  mat4.rotate(mvMatrix, degToRad(spin), [0.0, -1.0, 0.0]);

  drawCoin();

  mvPopMatrix();
}

// vrtenje kovancev
Coin.prototype.animate = function (elapsedTime) {
  this.angle += this.rotationSpeed * effectiveFPMS * elapsedTime;
}

// nafilamo array za vse coine
function initCoins() {
  var arr1 = [];
  var arr2 = [];

  // pozicije kovancev
  arr1.push([0, 0]);
  arr1.push([0, 4]);
  arr1.push([0, 12]);
  arr1.push([-2, 8]);
  arr1.push([-2, 16]);
  arr1.push([-6, 2]);
  arr1.push([-6, 8]);
  arr1.push([-6, 12]);
  arr1.push([-10, 2]);
  arr1.push([-10, 8]);
  arr1.push([-10, 12]);
  arr1.push([-10, 16]);
  arr1.push([-12, 5]);
  arr1.push([-16, 2]);
  arr1.push([-16, 4]);
  arr1.push([-16, 8]);
  arr1.push([-16, 12]);
  arr1.push([-16, 16]);

  
  

  for (var i in arr1) {
    var c = arr1[i];
    var x = Number(c[0]);
    var z = Number(c[1]);

    // console.log("x: ", x);
    // console.log("z: ", z);

    if (x != 0) {
      arr2.push([-x, z]);
      if (z != 0) {
        arr2.push([-x, -z]);
      }
    }
  
    arr2.push([x, z]);
    if (z != 0) {
      arr2.push([x, -z]);  
    }
  

  }
  // console.log(arr1);
  // console.log(arr2);

  for (var i in arr2) {
    var c = arr2[i];
    coins.push(new Coin(c[0], c[1]));
  }
  console.log("all coins: ", coins.length);

  // console.log(coins.length);
}



function loadCoins() {
  var request = new XMLHttpRequest();
  request.open("GET", "./assets/coin43.json");
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
// gres cez vse trikotnike, preveris ploscine treh trikotnikov znotraj glavnega

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