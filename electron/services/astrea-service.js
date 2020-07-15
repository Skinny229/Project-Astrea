const envVariables = require('../env-variables');
const isDev = require('electron-is-dev');


const {apiIdentifier, auth0Domain, clientId, backendURL, prodBackendURL} = envVariables;

var setGameID = "";

function getBackendURL(){
    if(isDev)
      return backendURL
    else
      return prodBackendURL
}

function setCurrentLobbyFocus(gameID){
  setGameID = gameID;
}

function getCurrentLobbyFocus(){
  return this.setGameID;
}


module.exports = {
 getBackendURL,
 setCurrentLobbyFocus
};
