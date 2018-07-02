'use strict';

/**
 * Based on Louis Hoebregts's "Quantum"
 *  + https://tympanus.net/Development/DecorativeBackgrounds/index3.html
 *  + https://github.com/Mamboleoo/DecorativeBackgrounds/
 */

/**
 * Setup Canvas
 */
var canvas = document.querySelector('#scene');
var width = canvas.offsetWidth;
var height = canvas.offsetHeight;

/**
 * Create the WebGL Renderer
 */
var renderer = new THREE.WebGLRenderer({
  canvas: canvas,
  antialias: true
});
renderer.setPixelRatio(window.devicePixelRatio > 1 ? 2 : 1);
renderer.setSize(width, height);
renderer.setClearColor('#CA1D31', 1);

/**
 * Scene
 */
var scene = new THREE.Scene();

var camera = new THREE.PerspectiveCamera(100, width / height, 0.1, 10000);
camera.position.set(0, 0, 600);

/**
 * Lights
 */
var domeLight = new THREE.HemisphereLight('#fff', '#C61522', 1);
scene.add(domeLight);

var light = new THREE.DirectionalLight('#CA1D31', 0.5);
light.position.set(200, 300, 400);
scene.add(light);

var light2 = light.clone();
light2.position.set(-200, 300, 400);
scene.add(light2);

var light3 = new THREE.DirectionalLight('#DBD8D1', 1);
light.position.set(0, 0, -600);
scene.add(light3);

/**
 * Geometry + Material = Shape
 */
var geometry = new THREE.IcosahedronGeometry(220, 4);
geometry.vertices.forEach(function (vertex) {
  vertex._o = vertex.clone();
});

var material = new THREE.MeshPhongMaterial({
  color: '#FED530',
  emissive: '#CA1D31',
  flatShading: true,
  emissiveIntensity: 0.1,
  shininess: 0
});

var shape = new THREE.Mesh(geometry, material);
scene.add(shape);

/**
 * Post-Processing
 */
var composer = new THREE.EffectComposer(renderer, new THREE.WebGLRenderTarget(width, height));

// The usual renderer(scene, camera) stuff.
// This will be the base layer
var renderPass = new THREE.RenderPass(scene, camera);
composer.addPass(renderPass);
// 猬囷笍 add some grain and scan lines
var filmPass = new THREE.FilmPass(0.1, 0.2, 25, false);
composer.addPass(filmPass);
// 猬囷笍 add a vignette
var vignettePass = new THREE.ShaderPass(THREE.VignetteShader);
composer.addPass(vignettePass);
// 猬囷笍 add a bit of glow
var bloomPass = new THREE.UnrealBloomPass(new THREE.Vector2(width, height), 1.5, 0.4, 0.85);
// This is the one we are actually rendering to screen!
// Each pass receives the previous pass as a texture.
bloomPass.renderToScreen = true;
composer.addPass(bloomPass);

/**
 * Animate Blob
 */
var displacementRatio = function displacementRatio(vector, time, scale) {
  var perlin = noise.simplex3(vector.x * 0.006 + time * 0.0002, vector.y * 0.006 + time * 0.0003, vector.z * 0.006);

  return perlin * 0.4 * (scale + 0.1) + 0.8;
};

function updateVertices(time) {
  geometry.vertices.forEach(function (vertex) {
    vertex.copy(vertex._o);
    var ratio = displacementRatio(vertex, time, 0.6);
    vertex.multiplyScalar(ratio);
  });
  geometry.verticesNeedUpdate = true;
}

/**
 * Render Loop
 */
function render(a) {
  requestAnimationFrame(render);
  updateVertices(a);
  var delta = 0.01;
  composer.render(delta);
}

requestAnimationFrame(render);

/**
 * Handle Window Resize
 */
function onResize() {
  canvas.style.width = '';
  canvas.style.height = '';
  width = canvas.offsetWidth;
  height = canvas.offsetHeight;
  camera.aspect = width / height;
  camera.updateProjectionMatrix();
  renderer.setSize(width, height);
  composer.setSize(width, height);
}

var resizeTimer = undefined;
window.addEventListener('resize', function () {
  resizeTimer = clearTimeout(resizeTimer);
  resizeTimer = setTimeout(onResize, 200);
});