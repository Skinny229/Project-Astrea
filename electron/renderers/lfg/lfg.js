const {remote} = require('electron');
const axios = require('axios');
const astreaService = require('../services/astrea-service.js');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {

    reloadListings();


  })

  document.getElementById('lfgreload').onclick = async () => {
    reloadListings();
};



function reloadListings(){
        
    //var lobbies = astreaService.getLFGListings();



    console.log('running this');

    var lobbyay = {};
    lobbyay.avatar = "https://cdn.discordapp.com/attachments/667493434092945428/714714537202417664/Screenshot_112.png";
    lobbyay.name = "Skinnay";
    lobbyay.nickname = "every1 but zeus";
    lobbyay.currentPlayers = 1;
    lobbyay.maxPlayers = 8;
    lobbyay.status = 'IN GAME-WAITING'
    var listing = generateGameLobbyDiv(lobbyay);
    console.log(listing);
    document.getElementById('listings').innerHTML = "";
    

  }

  document.getElementById('lfgreload').onclick = async () => {
    reloadListings();

};




function generateGameLobbyDiv(lobby){

  var gameListing = document.createElement('div');
  gameListing.setAttribute('class','row bg-light border border-dark');


  //Profile Pic
  var avatarDiv = document.createElement('div');
  var avatarImg = document.createElement('img');
  avatarDiv.setAttribute('class', 'col-md-1 text-right');
  avatarImg.setAttribute('class','profile-crop profile-pic');
  avatarImg.src = lobby.avatar;
  avatarDiv.append(avatarImg);
  gameListing.append(avatarDiv);
  //Name
  var nameDiv = document.createElement('div');
  nameDiv.setAttribute('class', 'col-md-1 text-left')
  nameDiv.innerHTML = lobby.name;
  gameListing.append(nameDiv);
  //nameDiv
  var gameNickName = document.createElement('div');
  gameNickName.setAttribute('class', 'col-md-3 text-center');
  gameNickName.innerHTML = lobby.nickname;
  gameListing.append(gameNickName);
  //current playerCount
  var playerCount = document.createElement('div');
  playerCount.setAttribute('class', 'col-md-2 text-center');
  playerCount.innerHTML = lobby.currentPlayers +'/'+lobby.maxPlayers;
  gameListing.append(playerCount);
  //status
  var gameStatus = document.createElement('div');
  gameStatus.setAttribute('class', 'col-md-2 text-center');
  gameStatus.innerHTML = lobby.status;
  gameListing.append(gameStatus);
  //Join Button
  var joinButton = document.createElement('div');
  joinButton.setAttribute('class', 'col-md-2 text-center');
  joinButton.innerHTML = '<a type="button" onClick="onJoinAttempt('+lobby.id+')" class="btn btn-success">Join</a>';
  gameListing.append(joinButton);
  //Collapsable display players


  return gameListing;
}


function onJoinAttempt(gameId){
    
}