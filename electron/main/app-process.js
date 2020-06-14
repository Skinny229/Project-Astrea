const {BrowserWindow} = require('electron');
const authService = require('../services/auth-service');
const envVariables = require('../env-variables');
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
    width: 1000,
    height: 600,
    webPreferences: {
      nodeIntegration: true
    },
  });

  win.loadFile('./renderers/home.html');

  win.on('closed', () => {
    win = null;
  });
}

module.exports = createAppWindow;