const {remote} = require('electron');
require('bootstrap');
const authService = remote.require('./services/auth-service');
const authProcess = remote.require('./main/auth-process');
const astreaService = remote.require('./services/astrea-service');

const webContents = remote.getCurrentWebContents();

class Player {
    constructor(json){
      var dataArray = json["player"]["0"];
  
      this.game_count = dataArray["game_count"];
      this.inverted_time = dataArray["inverted_time"];
      this.level = dataArray["level"];
      this.play_time = dataArray["play_time"];
      this.player_id = dataArray["player_id"];
      this.player_name = dataArray["player_name"];
      this.player_number = dataArray["player_number"];
      this.total_2_pointers = dataArray["total_2_pointers"];
      this.total_3_pointers = dataArray["total_3_pointers"];
      this.total_assists = dataArray["total_assists"];
      this.total_goals = dataArray["total_goals"];
      this.total_interceptions = dataArray["total_interceptions"];
      this.total_passes = dataArray["total_passes"];
      this.total_points = dataArray["total_points"];
      this.total_saves = dataArray["total_saves"];
      this.total_shots_taken = dataArray["total_shots_taken"];
      this.total_steals = dataArray["total_steals"];
      this.total_stuns = dataArray["total_stuns"];
      this.total_wins = dataArray["total_wins"];
    }
}

function APICall(pname){
    var key = "7329fb8e-3572-4a6b-a76f-f40a51493652";
    var url = "https://ignitevr.gg/cgi-bin/EchoStats.cgi/get_player_stats?player_name=" + pname + "&fuzzy_search=True";
    return JSON.parse(httpGet(url, key));
}
  
function httpGet(url,key){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.setRequestHeader("x-api-key", key);
    xmlHttp.send(null);
    console.log(xmlHttp.responseText);
    return xmlHttp.responseText;
}

document.getElementById('submitButton').onclick = () => {
    p = document.getElementById('inputName').value;
    console.log("Submit Clicked w/ name: " + p);
    loadStats(p);
};

function clear(){
    document.getElementById("overallStatsText").innerHTML = "Overall Stats"
    document.getElementById("wr").innerHTML = "";
    document.getElementById("game_count").innerHTML = "";
    document.getElementById("lvl").innerHTML = "";
    document.getElementById("assists").innerHTML = "";
    document.getElementById("shotcompletion").innerHTML = "";
    document.getElementById("twopointers").innerHTML = "";
    document.getElementById("threepointers").innerHTML = "";
    document.getElementById("saves").innerHTML = "";
    document.getElementById("interceptions").innerHTML = "";
    document.getElementById("stuns").innerHTML = "";
    document.getElementById("steals").innerHTML = "";
}

function loadStats(name){
    var stats = APICall(name)
    //console.log(stats["player"]);
    clear();
    if (stats && stats["player"] && stats["player"].length > 0){
        p = new Player(stats);
        console.log(p.total_goals);
    
        var currentWinrate = p.total_wins / p.game_count;
        var shotCompletion = p.total_goals / p.total_shots_taken;
        document.getElementById("overallStatsText").innerHTML = "Overall Stats for " + p.player_name;
        //Overall
        document.getElementById("wr").innerHTML = ('Winrate:<br/>' + (currentWinrate*100).toFixed(2).toString() + "%");
        document.getElementById("game_count").innerHTML = ("Game Count:<br/>" + p.game_count);
        document.getElementById("lvl").innerHTML = ("Level:<br/>" + p.level);
        //Offensive
        document.getElementById("assists").innerHTML = ("Assists:<br/>" + p.total_assists);
        document.getElementById("shotcompletion").innerHTML = ('Shot Completion:<br/>' + (shotCompletion*100).toFixed(2).toString() + "%");
        document.getElementById("twopointers").innerHTML = ("Two Pointers:<br/>" + p.total_2_pointers);
        document.getElementById("threepointers").innerHTML = ("Three Pointers:<br/>" + p.total_3_pointers);
    
        //Defense
        document.getElementById("saves").innerHTML = ("Saves:<br/>" + p.total_saves);
        document.getElementById("interceptions").innerHTML = ("Interceptions:<br/>" + p.total_interceptions);
        document.getElementById("stuns").innerHTML = ("Stuns:<br/>" + p.total_stuns);
        document.getElementById("steals").innerHTML = ("Steals:<br/>" + p.total_steals);
    } else {
        document.getElementById("wr").innerHTML = "Invalid Name";
    }
   
}