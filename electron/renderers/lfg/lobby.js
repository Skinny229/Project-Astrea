const {remote} = require('electron');
require('jquery');
require('bootstrap');
const axios = require('axios');
const astreaService = remote.require('./services/astrea-service');
const authService = remote.require('./services/auth-service');
const { promisify } = require('util');

var backendAddress = astreaService.getBackendURL();
const webContents = remote.getCurrentWebContents();

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
  

webContents.on('dom-ready', () => {
    console.log('got here');
    mainUpdateLoop();
  
  })
  


  document.getElementById('leavelobby').onclick = async () => {
    var leaveLobbyAddress = backendAddress + 'api/lfg/leaveLobby';

    axios.get(leaveLobbyAddress, {
      headers: {
        'Authorization': `Bearer ${authService.getAccessToken()}`,
      },
    }).then((response) => {
      window.location.href = "lfg.html";

    }).catch((error) => {
      window.location.href = "lfg.html";

      if (error) throw new Error(error);
    });

    

  };
  


async function mainUpdateLoop(){
   
    while(true){
    await sleep(750);
    var lobbyDataEndPoint = astreaService.getBackendURL() + 'api/lfg/lobbystatus';
    axios.get(lobbyDataEndPoint, {
        
        headers: {
          'Authorization': `Bearer ${authService.getAccessToken()}`,
        }
      }).then((response) => {
         updatePlayers(response.data.lobby.players);
      }).catch((error) => {
        document.getElementById('plyColumn').innerHTML = '<div class="alert alert-danger alert-dismissible fade hide show" role="alert"> <strong>ERROR!</strong> It seems something is wrong with the backend server. Please contact Skinny if the issue persists<button type="button" class="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> </div> ';
        if (error) throw new Error(error);
      });
    }
}



async function updatePlayers(playersArr){
    var playerColumn = document.getElementById('plyColumn');
    playerColumn.innerHTML = " ";

    for(var i = 0; i < playersArr.length; i++){
        var playerNameContainer = document.createElement('div');
        playerNameContainer.setAttribute('class', 'row card align-self-start');
        playerNameContainer.innerHTML = '<p class="align-self-center">'+playersArr[i].discordName+'</p>';
        playerColumn.append(playerNameContainer);
    }

}