const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://db-5308.cs.dal.ca :8080',
      changeOrigin: true,
    })
  );
};