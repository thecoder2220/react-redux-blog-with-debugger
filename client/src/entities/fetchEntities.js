exports.fetchFactory = fetchFactory;

var _slicedToArray = (function () {
  function sliceIterator (arr, i) {
    var _arr = [];
    var _n = true;
    var _d = false;
    var _e = undefined;
    try {
      for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) {
        _arr.push(_s.value);
        if (i && _arr.length === i) break;
      }
    } catch (err) {
      _d = true;
      _e = err;
    } finally {
      try {
        if (!_n && _i["return"]) _i["return"]();
      } finally {
        if (_d) throw _e;
      }
    }
    return _arr;
  }

  return function (arr, i) {
    if (Array.isArray(arr)) {
      return arr;
    } else if (Symbol.iterator in Object(arr)) {
      return sliceIterator(arr, i);
    } else {
      throw new TypeError("Invalid attempt to destructure non-iterable instance");
    }
  };
})();


var checkErrors = function checkErrors (_ref) {
  console.log("debut checkErrors")
  var _ref2 = _slicedToArray(_ref, 2),
    response = _ref2[0],
    json = _ref2[1];

  if (response.status === 400) {
    // conversion des erreurs

    // erreurs globales
    var globalErrors = (json.globalErrors || []).map(convertError).reduce(function (previousValue, currentValue) {
      return [].concat(_toConsumableArray(previousValue), [currentValue]);
    }, []);
    // erreurs sur les champs du formulaire

    var fieldErrors = (json.fieldErrors || []).map(function (error) {
      return _defineProperty({}, error.field, convertError(error));
    }).reduce(function (previousValue, currentValue) {
      return _extends({}, previousValue, currentValue);
    }, {});

    return Promise.reject(_extends({
      _error: globalErrors
    }, fieldErrors, {
      response: response,
      bodyError: json
    }));
  }

  console.log("fin checkErrors")
  return json;
};

var handleResponse = function handleResponse (response) {
  console.log("response.status="+response.status)
  // vérification du token
  if (response.status === 401) {
    // si le token n'est pas valide...
    return response.text().then(function (result) {
      // on déconnecte l'utilisateur
      Store.dispatch((0, _loginActions.logOut)());
      // et on rejette l'appel
      return Promise.reject(new Error(result));
    });
  }
  if (response.status === 204) {
    // no content
    return Promise.resolve();
  }
  if (!response.ok && response.status !== 400) {
    // si la réponse est en erreur sauf erreur de validation, on rejette l'appel
    return Promise.reject(response);
  }

  //console.log("response.json()="+response.json())
  // dans tous les autres cas, la réponse est traitée en JSON
  return Promise.all([response, response.json()]).then(checkErrors);
};

function fetchFactory (path) {
  var config = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
  var apiUrl = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : '';

  if (!config.headers) {
    config.headers = {};
  }
  if (!config.headers.Accept) {
    config.headers.Accept = 'application/json';
  }
  if (!config.headers['Content-Type']) {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
  }

  var token = localStorage.getItem('token') || null;
  console.log("token dans fetchfactory =" + token)
  if (token) {
    config.headers.Authorization = token;
  }

  return fetch('' + apiUrl + path, config).then(handleResponse);
}