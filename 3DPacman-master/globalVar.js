// Global variable definitionvar canvas;
var canvas;
var gl;
var shaderProgram;

// Buffers
var teapotVertexPositionBuffer;
var teapotVertexNormalBuffer;
var teapotVertexTextureCoordBuffer;
var teapotVertexIndexBuffer;

// coin
var coinVertexPositionBuffer;
var coinVertexNormalBuffer;
var coinVertexTextureCoordBuffer;
var coinVertexIndexBuffer;

// Model-view and projection matrix and model-view matrix stack
var mvMatrixStack = [];
var mvMatrix = mat4.create();
var pMatrix = mat4.create();

// Variable for storing textures
var earthTexture;
var metalTexture;
var goldTexture;

// Variable that stores  loading state of textures.
var numberOfTextures = 2;
var texturesLoaded = 0;

// Helper variables for rotation
// var teapotAngle = 180;

// Helper variable for animation
var lastTime = 0;

// pozicije tock trikotnikov

var trianglePositions = [];

// vsi trikotniki
var indides = [];

var triangles = [];

// coins

var coins = [];
var coinCounter = 0;
var rotationCoin = 0;

// endGame

var end = false;


// Variables for storing current rotation of cube
var tilt = 90;
var spin = 1.5;

// helper variables for animation

var effectiveFPMS = 60 / 1000;

// time

var counterNode;
var timeNode;
var then = 0;

// Keyboard handling helper variable for reading the status of keys
var currentlyPressedKeys = {};

// Variables for storing current position and speed
var pitch = 0;
var pitchRate = 0;
var yaw = 0;
var yawRate = 0;
var xPosition = 0;
var yPosition = -0.3;
var zPosition = 0;
var speed = 0;

// Used to make us "jog" up and down as we move forward.
var joggingAngle = 0;






