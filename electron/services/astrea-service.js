const envVariables = require('../env-variables');
const isDev = require('electron-is-dev');


const {apiIdentifier, auth0Domain, clientId, backendURL, prodBackendURL} = envVariables;



function getBackendURL(){
    if(isDev)
      return backendURL
    else
      return prodBackendURL
}


module.exports = {
 getBackendURL
};
