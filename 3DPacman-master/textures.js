

//
// initTextures
//
// Initialize the textures we'll be using, then initiate a load of
// the texture images. The handleTextureLoaded() callback will finish
// the job; it gets called each time a texture finishes loading.
//
function initTextures() {
  earthTexture = gl.createTexture();
  earthTexture.image = new Image();
  earthTexture.image.onload = function () {
    handleTextureLoaded(earthTexture)
  }
  earthTexture.image.src = "./assets/earth.jpg";

  metalTexture = gl.createTexture();
  metalTexture.image = new Image();
  metalTexture.image.onload = function () {
    handleTextureLoaded(metalTexture)
  }
  metalTexture.image.src = "./assets/metal.jpg";

  goldTexture = gl.createTexture();
  goldTexture.image = new Image();
  goldTexture.image.onload = function () {
    handleTextureLoaded(goldTexture)
  }
  goldTexture.image.src = "./assets/gold.jpg";
}

function handleTextureLoaded(texture) {
  gl.pixelStorei(gl.UNPACK_FLIP_Y_WEBGL, true);

  // Third texture usus Linear interpolation approximation with nearest Mipmap selection
  gl.bindTexture(gl.TEXTURE_2D, texture);
  gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, texture.image);
  gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
  gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
  gl.generateMipmap(gl.TEXTURE_2D);

  gl.bindTexture(gl.TEXTURE_2D, null);

  // when texture loading is finished we can draw scene.
  texturesLoaded += 1;
}