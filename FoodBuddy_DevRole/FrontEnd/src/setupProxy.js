const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://csci5308vm14.research.cs.dal.ca:8080',
      changeOrigin: true,
    })
  );
};