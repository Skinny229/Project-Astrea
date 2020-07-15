const {remote} = require('electron');
const astreaService = remote.require('./services/astrea-service');

var loadLobbyID = astreaService.getCurrentLobbyID();

function loadLobby(id){
    console.log("Loading " + id);
}



