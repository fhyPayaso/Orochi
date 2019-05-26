import { routerRedux } from 'dva/router';
import { stringify } from 'qs';
import { userLogin, getFakeCaptcha } from '@/services/api';
import { setAuthority } from '@/utils/authority';
import { getPageQuery } from '@/utils/utils';
import { reloadAuthorized } from '@/utils/Authorized';

export default {
  namespace: 'login',

  state: {
    status: undefined,
  },

  effects: {
    // 登录请求处理
    *login({ payload }, { call, put }) {
      // 进行网络请求
      const response = yield call(userLogin, payload);
      console.log(response)
      yield put({
        type: 'changeLoginStatus',
        payload: response,
      });
      // 登录成功
      if (response.code === 1000) {
          reloadAuthorized(); // 权限处理?
        //   const urlParams = new URL(window.location.href);
        //   const params = getPageQuery();
        //   let { redirect } = params;
        //   if (redirect) {
        //     const redirectUrlParams = new URL(redirect);
        //     if (redirectUrlParams.origin === urlParams.origin) {
        //       redirect = redirect.substr(urlParams.origin.length);
        //       if (redirect.match(/^\/.*#/)) {
        //         redirect = redirect.substr(redirect.indexOf('#') + 1);
        //       }
        //     } else {
        //       redirect = null;
        //     }
        //   }
        // yield put(routerRedux.replace(redirect || '/'));
        // 登录成功后进行页面跳转？
        yield put(routerRedux.replace('/apps'));
      }
    },

    *getCaptcha({ payload }, { call }) {
      yield call(getFakeCaptcha, payload);
    },

    *logout(_, { put }) {
      yield put({
        type: 'changeLoginStatus',
        payload: {
          status: false,
          currentAuthority: 'guest',
        },
      });
      reloadAuthorized();
      const { redirect } = getPageQuery();
      // redirect
      if (window.location.pathname !== '/user/login' && !redirect) {
        yield put(
          routerRedux.replace({
            pathname: '/user/login',
            search: stringify({
              redirect: window.location.href,
            }),
          })
        );
      }
    },
  },

  reducers: {
    changeLoginStatus(state, { payload }) {
      // setAuthority(payload.currentAuthority);
      setAuthority('admin');
      return {
        ...state,
        response: payload
      };
    },
  },
};
