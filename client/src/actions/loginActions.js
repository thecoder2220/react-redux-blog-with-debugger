export const TOKEN_REFRESH = 'TOKEN_REFRESH'
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS'
export const LOGOUT_SUCCESS = 'LOGOUT_SUCCESS'
export const GET_USER_SUCCESS  = 'GET_USER_SUCCESS'

export function logUser(token) {
	return {
		type: LOGIN_SUCCESS,
		token 
	};
}


export function logOut() {
	return {
		type: LOGOUT_SUCCESS
	};
}

export function refreshToken(token) {
	return {
		type: TOKEN_REFRESH,
		token
	};
}

export function getUser(user) {
  return {
    type: GET_USER_SUCCESS,
    user
  };
}
