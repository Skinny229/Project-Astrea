const {remote} = require('electron');
require('jquery');
require('bootstrap');
const axios = require('axios');
const astreaService = remote.require('./services/astrea-service');
const authService = remote.require('./services/auth-service');
const echoProtocolService = remote.require('./services/echo-protocol-service');
const { promisify } = require('util');

var backendAddress = astreaService.getBackendURL();
const webContents = remote.getCurrentWebContents();

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
  

webContents.on('dom-ready', () => {
    console.log('got here');
    astreaService.setEchoSessionId("");
    mainUpdateLoop();
  
  })
  


  document.getElementById('leavelobby').onclick = async () => {
    var leaveLobbyAddress = backendAddress + 'api/lfg/leaveLobby';

    axios.get(leaveLobbyAddress, {
      headers: {
        'Authorization': `Bearer ${authService.getAccessToken()}`,
      }
    }).then((response) => {
      window.location.href = "lfg.html";

    }).catch((error) => {
      window.location.href = "lfg.html";

      if (error) throw new Error(error);
    });
  };

  document.getElementById('linkEchoPrivateGame').onclick = async () => {
    var echoVr = 'http://127.0.0.1:6721/session';

    axios.get(echoVr, {
      headers: {
        'Authorization': `Bearer ${authService.getAccessToken()}`,
      }
    }).then((response) => {
       sendToBackend(response.data.sessionid);
    
    }).catch((error) => {
      if (error) throw new Error(error);
    });

  };


async function sendToBackend(sessionid){

  var linklobbyaddress = backendAddress + 'api/lfg/linkEchoSession?sessionid='+sessionid;


  axios.get(linklobbyaddress, {
    headers: {
      'Authorization': `Bearer ${authService.getAccessToken()}`,
    },
  }).then((response) => {
   

  }).catch((error) => {

    if (error) throw new Error(error);
  });
}


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
         if(astreaService.getEchoSessionId() == "" && response.data.lobby.echoSessionId != null){
            astreaService.setEchoSessionId(response.data.lobby.echoSessionId);
            displayJoinButton();
            
         }
      }).catch((error) => {
        document.getElementById('plyColumn').innerHTML = '<div class="alert alert-danger alert-dismissible fade hide show" role="alert"> <strong>ERROR!</strong> It seems something is wrong with the backend server. Please contact Skinny if the issue persists<button type="button" class="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> </div> ';
        if (error) throw new Error(error);
      });
    }
}


async function displayJoinButton()
{
    console.log("got here2");
    var nolink = document.getElementById('noEchoLink');
    nolink.parentNode.removeChild(nolink);

    var joinLobbyButton = document.createElement('div');
    joinLobbyButton.setAttribute('class', 'btn btn-success');
    joinLobbyButton.setAttribute('type', 'button');
    joinLobbyButton.setAttribute('id', 'joinEchoLobby');
    joinLobbyButton.innerHTML='Join Echo Lobby<div class="spinner-border spinner-border-sm text-dark" role="status"> <span class="sr-only">Loading...</span></div>';
    document.getElementById('actionButtons').append(joinLobbyButton);
    
  document.getElementById('joinEchoLobby').onclick = async () => {
    echoProtocolService.launchAsPlayer(astreaService.getEchoSessionId());
  };

}

async function updatePlayers(playersArr){
    var playerColumn = document.getElementById('plyColumn');
    playerColumn.innerHTML = " ";

    for(var i = 0; i < playersArr.length; i++){
        var playerNameContainer = document.createElement('div');
        playerNameContainer.setAttribute('class', 'row card col-12');
        var playerPfpDiv = document.createElement('img');
        playerPfpDiv.setAttribute('class', 'profile-crop profile-pic');
        playerPfpDiv.setAttribute('src', playersArr[i].discordProfilePic);
        playerNameContainer.append(playerPfpDiv);
        var playerNameDiv = document.createElement('p');
        playerNameDiv.setAttribute('class', 'align-self-center')
        playerNameDiv.innerHTML = playersArr[i].discordName;
        playerNameContainer.append(playerNameDiv);
        playerColumn.append(playerNameContainer);
    }

}