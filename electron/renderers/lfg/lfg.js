const { remote } = require('electron');
//var jquery = remote.require('jquery');
require('jquery');
require('bootstrap');
const axios = require('axios');
const astreaService = remote.require('./services/astrea-service');
const authService = remote.require('./services/auth-service');
const mainWindow = remote.require('./main.js');


const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {

  //  reloadListings();

})

document.getElementById('lfgreload').onclick = async () => {
  reloadListings();
};


async function reloadListings() {


  /*
  var lobbyay = {};
  lobbyay.avatar = "https://cdn.discordapp.com/attachments/667493434092945428/714714537202417664/Screenshot_112.png";
  lobbyay.name = "Skinnay";
  lobbyay.nickname = "every1 but zeus";
  lobbyay.currentPlayers = 1;
  lobbyay.maxPlayers = 8;
  lobbyay.status = 'IN GAME-WAITING'
  var listing = generateGameLobbyDiv(lobbyay);
  console.log(listing);
  */

  var listingsDiv = document.getElementById('listings');

  //Clear the inside
  listingsDiv.innerHTML = "";


  //Retrieve Available lobby Ids
  getLFGLobbyIds();

  //Retrieve lobby Data

  //Append th


  async function getLFGLobbyIds() {


    axios.get('http://localhost:3000/api/lfg/lobbies', {
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

    console.log('got here');
    console.log(lobbyArr.lobbyids);
    var ids = lobbyArr.lobbyids;
    var listingsDiv = document.getElementById('listings');
    for (var i = 0; i < ids.length; i++) {
      axios.get('http://localhost:3000/api/lfg/lobbydata', {
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

  var gameListing = document.createElement('div');
  gameListing.setAttribute('class', 'row bg-light border border-dark');


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
  //Join Button
  var joinButton = document.createElement('div');
  joinButton.setAttribute('class', 'col-md-2 text-center');
  joinButton.innerHTML = '<a type="button" onClick="onJoinAttempt(' + lobby.id + ')" class="btn btn-success">Join</a>';
  gameListing.append(joinButton);
  //Collapsable display players


  return gameListing;
}


function onJoinAttempt(gameId) {

}