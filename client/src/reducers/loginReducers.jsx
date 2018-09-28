import * as actions from '../actions/loginActions'

export default function (localStorage) {

  const token = localStorage.getItem('token');

  const initialState = {
    token: token,
    authenticated: false
  }

  return (state = initialState, action = {}) => {

    switch (action.type) {
      case actions.LOGIN_SUCCESS:
      case actions.TOKEN_REFRESH:
        return {
          ...state,
          token: action.token,
          authenticated: true
        }
      case actions.GET_USER_SUCCESS:
        return {
          ...state,
          user: action.user,
        }
      case actions.LOGOUT_SUCCESS:
        // à faire (0, _loginApi.removeLocalUser)();
        return {};
      default:
        return state;
    }
  }
}