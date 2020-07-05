const {remote} = require('electron');
const axios = require('axios');
const authService = remote.require('./services/auth-service');
const authProcess = remote.require('./main/auth-process');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {
 
});

document.getElementById('exit').onclick = async () => {
  axios.get('http://localhost:3000/api/lfg/lobbies', {
    headers: {
      'Authorization': `Bearer ${authService.getAccessToken()}`,
    },
  }).then((response) => {
    const messageJumbotron = document.getElementById('message');
    messageJumbotron.innerText = response.data;
    messageJumbotron.style.display = 'block';
    console.log(response.data);
  }).catch((error) => {
    if (error) throw new Error(error);
  });
  remote.getCurrentWindow().close();
}