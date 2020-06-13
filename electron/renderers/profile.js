const {remote} = require('electron');
const axios = require('axios');
const authService = remote.require('./services/auth-service');
const authProcess = remote.require('./main/auth-process');
const mainWindow = remote.require('./main.js');

const webContents = remote.getCurrentWebContents();

function APICall(pname){
    var key = "7329fb8e-3572-4a6b-a76f-f40a51493652";
    var url = "https://ignitevr.gg/cgi-bin/EchoStats.cgi/get_player_stats?player_name=" + pname + "&fuzzy_search=True";
    return JSON.parse(httpGet(url, key));
};

function httpGet(url,key){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.setRequestHeader("x-api-key", key);
    xmlHttp.send(null);
    return xmlHttp.responseText;
};

function loadStats(playername) {
    var statsJSON = APICall(playername);
    console.log(statsJSON);
    
    
    //console.log(statsJSON["player"]["0"]["game_count"]);
    var currentWinrate = statsJSON["player"]["0"]["total_wins"] / statsJSON["player"]["0"]["game_count"];
    document.getElementById("wr").innerHTML = ('Winrate:<br/>' + (currentWinrate*100).toFixed(2).toString() + "%");

    var shotCompletion = (statsJSON["player"]["0"]["total_goals"]) / statsJSON["player"]["0"]["total_shots_taken"];
    document.getElementById("shotcompletion").innerHTML = ('Shot Completion:<br/>' + (shotCompletion*100).toFixed(2).toString() + "%");
}


webContents.on('dom-ready', () => {
    const profile = authService.getProfile();
    document.getElementById('picture').src = profile.picture;
    document.getElementById('name').innerHTML = ('Welcome, '+ profile.name);

    loadStats(profile.name);

  });
  

document.getElementById('logoutandexit').onclick = async () => {
    await authProcess.createLogoutWindow();
    remote.getCurrentWindow().close();
};


