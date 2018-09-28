import { fetchFactory } from '../entities/fetchEntities'

/* export  function emailAndPasswordControl (data) {

  console.log("data.username=" + data.username)
  console.log("data.password=" + data.password)
  //return fetchFactory('/demo/all')
  return fetchFactory(`/api/authbidon`).then((response) => {
    console.log("response après FetchFactory=" + response)
    return response
  })
} */

export  function emailAndPasswordControl (data) {

  const { username, password } = data
  return fetch ('/auth/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      username: username,
      password: password
    })
  }).then(response => {
    console.log("response après fetch POST=" + response)
    if (response.ok) {
      return response.json();
    }
    return;
  });
}

export function removeLocalUser () {
  localStorage.removeItem('token')
}

export function storeLocalUser (_ref) {
  let token = _ref.token
  localStorage.setItem('token', token)
}



  /*
   function login(data) {
   var username = data.login,
   password = data.password,
   rememberMe = data.rememberMe;

   return (0, _isomorphicFetch2.default)(SERVER_URL + '/auth/login', {
   method: 'POST',
   headers: {
   'Accept': 'application/json',
   'Content-Type': 'application/json'
   },
   body: JSON.stringify({
   username: username,
   password: password,
   rememberMe: rememberMe
   })
   }).then(function (response) {
   if (response.ok) {
   return response.json();
   }
   return;
   });
   }

+

   return fetch(`${SERVER_URL}/api/admin/files/${idDocument}`, config)
   .then((response) => {

   +


   var loginSubmit = function loginSubmit(data, dispatch) {
   return new Promise(function (resolve, reject) {
   return (0, _loginApi.login)(data).then(function (json) {
   if (json) {
   dispatch((0, _loginActions.logUser)(json.token));
   (0, _loginApi.storeLocalUser)(json);
   resolve(json.token);
   } else {
   reject({
   login: 'Mauvais couple identifiant / mot de passe'
   });
   }
   }).catch(function () {
   reject({
   login: 'Connexion impossible'
   });
   });
   }).then(function (token) {
   return (0, _loginApi.fetchUser)(token);
   }).then(function (user) {
   dispatch((0, _loginActions.getUser)(user));
   }).then(function () {
   dispatch((0, _reactRouterRedux.push)('/dashboard'));
   });
   };

   */



