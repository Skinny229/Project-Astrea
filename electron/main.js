const {app} = require('electron');
const { autoUpdater } = require('electron-updater');
const {createAuthWindow} = require('./main/auth-process');
const createAppWindow = require('./main/app-process');
const isDev = require('electron-is-dev');
const authService = require('./services/auth-service');

 autoUpdater.logger = require('electron-log');
 autoUpdater.logger.transports.file.level = 'info';


async function showWindow() {
  autoUpdater.checkForUpdatesAndNotify()
  try {
    await authService.refreshTokens();
    console.log('going here');
    return createAppWindow();
  } catch (err) {
    createAuthWindow();
  }
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', showWindow);

// Quit when all windows are closed.
app.on('window-all-closed', () => {
  app.quit();
});
