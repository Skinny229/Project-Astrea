const { remote } = require('electron');
//var jquery = remote.require('jquery');
require('jquery');
require('bootstrap');
const axios = require('axios');
const { setCurrentLobbyFocus } = require('../../services/astrea-service');
const astreaService = remote.require('./services/astrea-service');
const appProcess = remote.require('./main/app-process');
const authService = remote.require('./services/auth-service');


const webContents = remote.getCurrentWebContents();

var backendAddress = astreaService.getBackendURL();

webContents.on('dom-ready', () => {

  //reloadListings();

})

document.getElementById('lfgreload').onclick = async () => {
  reloadListings();
};


document.getElementById('creategame').onclick = async () => {
  var createLobbyAddress = backendAddress + 'api/lfg/createlobby';
  axios.get(createLobbyAddress, {
        headers: {
          'Authorization': `Bearer ${authService.getAccessToken()}`,
        },
      }).then((response) => {
        appProcess.goToLobby();
      }).catch((error) => {
        if (error) throw new Error(error);
      });
};


async function reloadListings() {




  var listingsDiv = document.getElementById('listings');

  //Clear the inside
  listingsDiv.innerHTML = "";


  getLFGLobbyIds();

  async function getLFGLobbyIds() {
    var lobbyIdAddress = backendAddress + 'api/lfg/lobbies';
    axios.get(lobbyIdAddress, {
      headers: {
        'Authorization': `Bearer ${authService.getAccessToken()}`,
      },
    }).then((response) => {

      getLFGLobbyDetails(response.data);
    }).catch((error) => {
      if (error) throw new Error(error);
    });


  }

  async function getLFGLobbyDetails(lobbyArr) {
    var lobbyDataAddress = backendAddress + 'api/lfg/lobbydata';
    console.log('got here');
    console.log(lobbyArr.lobbyids);
    var ids = lobbyArr.lobbyids;
    var listingsDiv = document.getElementById('listings');
    for (var i = 0; i < ids.length; i++) {
      axios.get(lobbyDataAddress, {
        headers: {
          'Authorization': `Bearer ${authService.getAccessToken()}`,
        },
        params: {
          "id": ids[i]
        }
      }).then((response) => {
        listingsDiv.append(generateGameLobbyDiv(response.data));
      }).catch((error) => {
        if (error) throw new Error(error);
      });
    }
  }

}

function generateGameLobbyDiv(lobby) {

  var cardHeader = document.createElement('div');
  cardHeader.setAttribute('class','card');

  var cardHeader2 = document.createElement('div');
  cardHeader2.setAttribute('class','card-header');
  cardHeader.append(cardHeader2);

  var gameListing = document.createElement('div');
  gameListing.setAttribute('class', 'row bg-light');


  //Profile Pic
  var avatarDiv = document.createElement('div');
  var avatarImg = document.createElement('img');
  avatarDiv.setAttribute('class', 'col-md-1 text-right');
  avatarImg.setAttribute('class', 'profile-crop profile-pic');
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
  playerCount.innerHTML = lobby.currentPlayers + '/' + lobby.maxPlayers;
  gameListing.append(playerCount);

  //status
  var gameStatus = document.createElement('div');
  gameStatus.setAttribute('class', 'col-md-2 text-center');
  gameStatus.innerHTML = lobby.status;
  gameListing.append(gameStatus);

  //ButtonSection
  var buttonSection = document.createElement('div');
  buttonSection.setAttribute('class', 'col-md-2 text-center');

  //Info button
  var infoButton = document.createElement('a');
  infoButton.setAttribute('class', 'btn btn-primary');
  infoButton.setAttribute('data-toggle', 'collapse');
  infoButton.setAttribute('href', ('#info-'+lobby.id));
  infoButton.innerHTML = "Game Info";
  buttonSection.append(infoButton);
  //Join button
  var joinButton = document.createElement('a');
  joinButton.setAttribute('type', 'button');
  joinButton.setAttribute('class', 'btn btn-success');
  joinButton.setAttribute('onClick', 'onJoinAttempt("'+ lobby.id+'")');
  joinButton.setAttribute('href', 'lobby.html');
  joinButton.innerHTML = 'Join';
  buttonSection.append(joinButton);

  gameListing.append(buttonSection);


  //Collapsable display players
  var infoButtonDropDown = document.createElement('div');
  infoButtonDropDown.setAttribute('class', 'collapse');
  infoButtonDropDown.setAttribute('id',('info-'+ lobby.id));
  var playerList = "";
  playerList+=lobby.players[0].discordName;
  for(var i = 1; i < lobby.players.length; i++)
    playerList += ", ";
  infoButtonDropDown.innerHTML = 'Players: ' + playerList;
  cardHeader.append(infoButtonDropDown);

  
  //Add the full Game listing to the main cardheader
  cardHeader2.append(gameListing);
  
  return cardHeader;
}


function onJoinAttempt(gameId) {
  var lobbyDataEndPoint = astreaService.getBackendURL() + 'api/lfg/joinlobby?id='+gameId;
    axios.get(lobbyDataEndPoint, {
        
        headers: {
          'Authorization': `Bearer ${authService.getAccessToken()}`,
        }
      }).then((response) => {
         
      }).catch((error) => {
        if (error) throw new Error(error);
      });
    }
  
