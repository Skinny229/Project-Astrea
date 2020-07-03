const {remote} = require('electron');
const axios = require('axios');
const astreaService = removet.require('./services/astrea-service');

const webContents = remote.getCurrentWebContents();

webContents.on('dom-ready', () => {

    reloadListings();
  })

  document.getElementById('lfgreload').onclick = async () => {
    reloadListings();
};



function reloadListings(){
        
    var lobbies = astreaService.getLFGListings();

    for(var i = 0; i < lobbies.length; i++){

    }
  }