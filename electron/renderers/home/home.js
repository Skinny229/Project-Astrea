const {remote} = require('electron');
const axios = require('axios');
const authService = remote.require('./services/auth-service');
const astreaService = remote.require('./services/astrea-service');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {
  var onLoginEndPoint = astreaService.getBackendURL() + 'api/misc/onlogin';
  var profile = authService.getProfile();
  axios.get(onLoginEndPoint, {
    headers: {
      'Authorization': `Bearer ${authService.getAccessToken()}`,
    },
    params:{'name': profile.name,
  'picture': profile.picture}
  }).then((response) => {
  }).catch((error) => {
    if (error) throw new Error(error);
  });
 
});

document.getElementById('exit').onclick = async () => {
  remote.getCurrentWindow().close();
}