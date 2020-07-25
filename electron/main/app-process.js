const {BrowserWindow} = require('electron');
const authService = require('../services/auth-service');
const envVariables = require('../env-variables');
const astreaService = require('../services/astrea-service');
const isDev = require('electron-is-dev');
const request = require('request');
const util = require('util');

const {apiIdentifier, auth0Domain, clientId, backendURL} = envVariables;

var mainWindow;

function createAppWindow() {


  console.log('trying to run on login');

  let win = new BrowserWindow({
    width: 1300,
    height: 700,
    webPreferences: {
      nodeIntegration: true
    },
  });

  //if(!isDev)
   // win.removeMenu();

  mainWindow = win;
  win.loadFile('./renderers/home/home.html');
  win.on('closed', () => {
    win = null;
    mainWindow = null;
  });
}




function goToLobby(){
  mainWindow.loadFile('./renderers/lfg/lobby.html');
}

module.exports ={ createAppWindow,goToLobby};