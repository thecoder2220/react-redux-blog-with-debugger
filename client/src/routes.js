import React from 'react';
import { Route, IndexRoute } from 'react-router';

import App from './pages/App';
import PostsIndex from './pages/PostsIndex';
import SignUp from './pages/SignUp';
import Accueil from './pages/Accueil';

export default (
  <Route path="/" component={App}>
    <IndexRoute component={PostsIndex} />
    <Route path="/signup" component={SignUp} />
    <Route path="/accueil" component={Accueil} />
  </Route>
);
