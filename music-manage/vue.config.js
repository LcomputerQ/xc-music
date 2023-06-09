
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    proxy: {
      '/api': {
        target: 'http://43.138.167.39:3000',
        pathRewrite: { '^/api': '' },//路径改写
      },
      '/serve': {
        target: 'http://localhost:8888',
        pathRewrite: { '^/serve': '' },//路径改写
      },
    }
  },
  lintOnSave:false
})