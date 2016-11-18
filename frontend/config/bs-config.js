var proxy = require('http-proxy-middleware');

var apiProxy = proxy('/rest',  {
    target: 'http://localhost:8080/simple-contact-list',
    changeOrigin: true
});

module.exports = {
    server: {
        middleware: {
            1: apiProxy
        }
    }
}