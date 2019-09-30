export default {

  title: 'Jbone CMS',

  cookieExpires: 1,

  useI18n: true,

  baseUrl: {
    dev: 'http://jbone-sm-gateway.majunwei.com:10005',
    pro: 'http://jbone-sm-gateway.majunwei.com:10005'
  },
  sso: {
    dev: {
      OAUTH_REDIRECT_URI : 'http://jbone-cms-admin.majunwei.com:50002/',
      SSO_BASE_URL : 'http://jbone-cas.majunwei.com:30001/cas/',
      OAUTH_CLIENT_ID : 'cmsadmin',
      OAUTH_CLIENT_SECRET : 'cmsadmin'
    },
    pro: {
      OAUTH_REDIRECT_URI : 'http://jbone-cms-admin.majunwei.com:50002/',
      SSO_BASE_URL : 'http://jbone-cas.majunwei.com:30001/cas/',
      OAUTH_CLIENT_ID : 'cmsadmin',
      OAUTH_CLIENT_SECRET : 'cmsadmin'
    }
  },

  homeName: 'home',

  plugin: {
    'error-store': {
      showInHeader: true, // 设为false后不会在顶部显示错误日志徽标
      developmentOff: true // 设为true后在开发环境不会收集错误信息，方便开发中排查错误
    }
  }
}
