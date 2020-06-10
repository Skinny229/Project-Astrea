const {remote} = require('electron');
const axios = require('axios');
const authService = remote.require('./services/auth-service');
const authProcess = remote.require('./main/auth-process');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {
  const profile = authService.getProfile();
  document.getElementById('picture').src = profile.picture;
  document.getElementById('name').innerHTML = ('Welcome, '+ profile.name);
});

document.getElementById('exit').onclick = async () => {
 remote.getCurrentWindow().close();
};


document.getElementById('Logout').onclick = async () => {
  await authProcess.createLogoutWindow();
 
  remote.getCurrentWindow().close();
};