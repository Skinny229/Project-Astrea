const request = require('request');
const url = require('url');
const envVariables = require('../env-variables');
const keytar = require('keytar');
const os = require('os');
//const jwtDecode = require('jwt-decode');
const jwt = require('jsonwebtoken');


const {apiIdentifier, auth0Domain, clientId} = envVariables;

const redirectUri = `file:///callback`;

const keytarService = 'electron-openid-oauth';
const keytarAccount = os.userInfo().username;

let accessToken = null;
let profile = null;
let refreshToken = null;

function getAccessToken() {
  return accessToken;
}

function getProfile() {
  return profile;
}

function getAuthenticationURL() {
  return 'https://' + auth0Domain + '/authorize?' +
    'audience=' + apiIdentifier + '&' +
    'scope=openid profile offline_access&' +
    'response_type=code&' +
    'client_id=' + clientId + '&' +
    'redirect_uri=' + redirectUri;
}

function refreshTokens() {
  return new Promise(async (resolve, reject) => {
    const refreshToken = await keytar.getPassword(keytarService, keytarAccount);

    if (refreshToken === " " || refreshToken === undefined) return reject();

    const refreshOptions = {
      method: 'POST',
      url: `https://${auth0Domain}/oauth/token`,
      headers: {'content-type': 'application/json'},
      body: {
        grant_type: 'refresh_token',
        client_id: clientId,
        refresh_token: refreshToken,
      },
      json: true,
    };

    request(refreshOptions, async function (error, response, body) {
      if (error || body.error) {
        await logout();
        return reject(error || body.error);
      }

      accessToken = body.access_token;
      //profile = jwtDecode(body.id_token);
	  //profile = JSON.parse(window.atob(body.id_token));
	  profile = jwt.decode(body.id_token);
      resolve();
    });
  });
}

function loadTokens(callbackURL) {
  return new Promise((resolve, reject) => {
    console.log("creating promise");
    const urlParts = url.parse(callbackURL, true);
    const query = urlParts.query;

    const exchangeOptions = {
      'grant_type': 'authorization_code',
      'client_id': clientId,
      'code': query.code,
      'redirect_uri': redirectUri,
    };

    const options = {
      method: 'POST',
      url: `https://${auth0Domain}/oauth/token`,
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(exchangeOptions),
    };

    request(options, async (error, resp, body) => {
      if (error || body.error) {
        console.log("Error obtaining Access_token");
        await logout();
        return reject(error || body.error);
      }

      const responseBody = JSON.parse(body);
      console.log("Response body: " + responseBody.accessToken);
      accessToken = responseBody.access_token;
      //profile = jwtDecode(responseBody.id_token);
	    //profile = JSON.parse(window.atob(body.id_token));
     profile = jwt.decode(body.id_token);
     
     console.log("profile: " + profile);

      refreshToken = responseBody.refresh_token;
	  if(refreshToken === undefined)
		  refreshToken = " ";

      keytar.setPassword(keytarService, keytarAccount, refreshToken);

      resolve();
    });
  });
}

async function logout() {
  await keytar.deletePassword(keytarService, keytarAccount);
  accessToken = null;
  profile = null;
  refreshToken = null;
}

function getLogOutUrl() {
  return `https://${auth0Domain}/v2/logout`;
}

module.exports = {
  getAccessToken,
  getAuthenticationURL,
  getLogOutUrl,
  getProfile,
  loadTokens,
  logout,
  refreshTokens,
};
