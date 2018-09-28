import { combineReducers } from 'redux';
import PostsReducer from './reducer_posts';
import UserReducer from './reducer_user';
import ValidateUserFieldsReducer from './reducer_validateUserFields';
import { reducer as formReducer } from 'redux-form';

import { alert as alertReducer } from './alertReducer';

const rootReducer = combineReducers({
  user: UserReducer,
  validateFields: ValidateUserFieldsReducer,
  posts: PostsReducer, //<-- Posts
  form: formReducer, // <-- redux-form
  alert:alertReducer
});

export default rootReducer;
