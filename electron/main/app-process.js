const {BrowserWindow} = require('electron');
const authService = require('../services/auth-service');
const envVariables = require('../env-variables');
const isDev = require('electron-is-dev');
const request = require('request');

const {apiIdentifier, auth0Domain, clientId, backendURL} = envVariables;


function createAppWindow() {


  const onAuthLogin = {
    method: 'GET',
    url: `http://${backendURL}/api/misc/onlogin`,
    headers: {'Authorization': 'Bearer ' + authService.getAccessToken()},
  };
  request(onAuthLogin, async function (error, response, body) {
    if (error || body.error) {
      console.log("uh oh");
      //await logout();
      //return reject(error || body.error);
    }


  });

  let win = new BrowserWindow({
    width: 1300,
    height: 700,
    webPreferences: {
      nodeIntegration: true
    },
  });


  if(!isDev)
    win.removeMenu();

  win.loadFile('./renderers/home/home.html');
  win.on('closed', () => {
    win = null;
  });
}

module.exports = createAppWindow;