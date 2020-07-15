const {remote} = require('electron');
require('jquery');
require('bootstrap');
const axios = require('axios');
const astreaService = remote.require('./services/astrea-service');
const authService = remote.require('./services/auth-service');
const { promisify } = require('util');


const webContents = remote.getCurrentWebContents();

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
  

webContents.on('dom-ready', () => {
    console.log('got here');
    mainUpdateLoop();
  
  })
  



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