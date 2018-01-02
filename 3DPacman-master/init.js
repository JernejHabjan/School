
//
// initGL
//
// Initialize WebGL, returning the GL context or null if
// WebGL isn't available or could not be initialized.
//
function initGL(canvas) {
  var gl = null;
  try {
    // Try to grab the standard context. If it fails, fallback to experimental.
    gl = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
    gl.viewportWidth = canvas.width;
    gl.viewportHeight = canvas.height;
  } catch(e) {}

  // If we don't have a GL context, give up now
  if (!gl) {
    alert("Unable to initialize WebGL. Your browser may not support it.");
  }
  return gl;
}



//
// start
//
// Called when the canvas is created to get the ball rolling.
// Figuratively, that is. There's nothing moving in this demo.
//
function start() {
  canvas = document.getElementById("glcanvas");

  // time and counter

  // look up the elements we want to affect
  var timeElement = document.getElementById("time");
  var counterElement = document.getElementById("counter");


  // Create text nodes to save some time for the browser.
  timeNode = document.createTextNode("");
  counterNode = document.createTextNode("");


  // Add those text nodes where they need to go
  timeElement.appendChild(timeNode);
  counterElement.appendChild(counterNode);
  //


  gl = initGL(canvas);      // Initialize the GL context
  
  // timer
  var time = 60,
      display = timeNode;
      startTimer(time, display);

  if (gl) {
    gl.clearColor(0.0, 0.0, 0.0, 1.0);                      // Set clear color to black, fully opaque
    gl.clearDepth(1.0);                                     // Clear everything
    gl.enable(gl.DEPTH_TEST);                               // Enable depth testing
    gl.depthFunc(gl.LEQUAL);                                // Near things obscure far things

    // Initialize the shaders; this is where all the lighting for the
    // vertices and so forth is established.
    initShaders();
    
    // Next, load and set up the textures we'll be using.
    initTextures();
    loadFloor();
    // nafilamo vse coine
    initCoins();
    loadCoins();
    loadTeapot();
    
    // MUSIC in the background
    // var audio = new Audio('./assets/magnifico.mp3');
    // audio.play();
	
	
	// Bind keyboard handling functions to document handlers
    document.onkeydown = handleKeyDown;
    document.onkeyup = handleKeyUp;
  

    // Set up to draw the scene periodically.
    setInterval(function() {
      if (texturesLoaded) { // only draw scene and animate when textures are loaded.
        requestAnimationFrame(animate);
        handleKeys();
        drawScene();
      }
    }, 15);


  }
}

// timer

function startTimer(duration, display) {
  var timer = duration, minutes, seconds;
  setInterval(function () {
      // minutes = parseInt(timer / 60, 10)
      seconds = parseInt(timer % 60, 10);

      seconds =  seconds;

      if (!end) {
        display.nodeValue = seconds;
      } else {
        display.nodeValue = 0;
      }
      

      // if (timer == 0) {
      //   console.log(timer);
      // }
      if (--timer < 0) {
          // timer = duration;
          end = true;
      }
  }, 1000);
}
