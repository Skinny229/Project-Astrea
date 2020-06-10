const {remote} = require('electron');
const axios = require('axios');
const authService = remote.require('./services/auth-service');
const authProcess = remote.require('./main/auth-process');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {
  console.log('the fuck');
  const profile = authService.getProfile();
  console.log(profile);
  document.getElementById('picture').src = profile.picture;
  document.getElementById('name').innerText = profile.name;
  document.getElementById('success').innerText = 'You successfully used OpenID Connect and OAuth 2.0 to authenticate.';
});

document.getElementById('logout').onclick = async () => {
  //await authProcess.createLogoutWindow();
  axios.get('localhost:3000/api/private', {
    headers: {
      'Authorization': `Bearer ${authService.getAccessToken()}`,
    },
  }).then((response) => {
    const messageJumbotron = document.getElementById('message');
    messageJumbotron.innerText = response.data;
    messageJumbotron.style.display = 'block';
  }).catch((error) => {
    if (error) throw new Error(error);
  });
 // remote.getCurrentWindow().close();
};
