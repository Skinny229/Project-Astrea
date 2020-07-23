const envVariables = require('../env-variables');
const isDev = require('electron-is-dev');


const {apiIdentifier, auth0Domain, clientId, backendURL, prodBackendURL} = envVariables;

var setGameID = "";

var echoSesionId ="";

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

function setEchoSessionId(sessionId){
  echoSesionId = sessionId;
}

function getEchoSessionId(sessionId){
  return sessionId;
}

module.exports = {
 getBackendURL,
 setCurrentLobbyFocus,
 setEchoSessionId,
 getEchoSessionId
};
