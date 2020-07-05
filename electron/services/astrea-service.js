const request = require('request');
const envVariables = require('../env-variables');
const authService = require('./auth-service');
const axios = require('axios');



const {apiIdentifier, auth0Domain, clientId, backendURL} = envVariables;


function getLFGGames(){
  return new Promise(async (resolve,reject) => {
   
  });
}

function updateLobby(){}

function updateEchoSession(){}


module.exports = {
  getLFGGames
};
