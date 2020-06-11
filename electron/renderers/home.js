const {remote} = require('electron');
const axios = require('axios');
const authService = remote.require('./services/auth-service');
const authProcess = remote.require('./main/auth-process');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {
 
});

document.getElementById('exit').onclick = async () => {
  remote.getCurrentWindow().close();
}