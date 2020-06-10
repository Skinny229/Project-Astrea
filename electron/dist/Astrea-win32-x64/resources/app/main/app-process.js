const {BrowserWindow} = require('electron');

function createAppWindow() {
  let win = new BrowserWindow({
    width: 1000,
    height: 600,
    webPreferences: {
      nodeIntegration: true
    },
  });

  win.loadFile('./renderers/home.html');
  console.log("Try this");

  win.on('closed', () => {
    win = null;
  });
}

module.exports = createAppWindow;
