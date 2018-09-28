import React, { Component } from 'react';
import { Link } from 'react-router';
import PropTypes from 'prop-types';
import { reduxForm, Field, SubmissionError } from 'redux-form';

import renderField from './renderField';
import { signInUser, signInUserFailure, signInUserSuccess } from '../actions/users';
import { alertActions } from '../actions/alertActions';
import { logUser } from '../actions/loginActions';
import { emailAndPasswordControl, storeLocalUser } from './loginApi'

//Client side validation
function validate (values) {
  var errors = {};
  var hasErrors = false;

  if (!values.name || values.name.trim() === '') {
    errors.name = 'Enter a name';
    hasErrors = true;
  }
  if (!values.username || values.username.trim() === '') {
    errors.username = 'Enter username';
    hasErrors = true;
  }
  /*if (!values.email || values.email.trim() === '') {
   errors.email = 'Enter email';
   hasErrors = true;
   } */
  if (!values.password || values.password.trim() === '') {
    errors.password = 'Enter password';
    hasErrors = true;
  }
  /* if (!values.confirmPassword || values.confirmPassword.trim() === '') {
   errors.confirmPassword = 'Enter Confirm Password';
   hasErrors = true;
   }

   if (values.confirmPassword && values.confirmPassword.trim() !== '' && values.password && values.password.trim() !== '' && values.password !== values.confirmPassword) {
   errors.password = 'Password And Confirm Password don\'t match';
   errors.password = 'Password And Confirm Password don\'t match';
   hasErrors = true;
   }*/
  return hasErrors && errors;
}

//For any field errors upon submission (i.e. not instant check)
const validateAndSignInUser = (values, dispatch) => {

  return dispatch(signInUser(values)).then((result) => {
    debugger;

    // Note: Error's "data" is in result.payload.response.data (inside "response")
    // success's "data" is in result.payload.data
    if (result.payload.response && result.payload.response.status !== 200) {
      dispatch(signInUserFailure(result.payload.response.data));
      throw new SubmissionError(result.payload.response.data);
    }


    //Store JWT Token to browser session storage
    //If you use localStorage instead of sessionStorage, then this w/ persisted across tabs and new windows.
    //sessionStorage = persisted only in current tab
    sessionStorage.setItem('jwtToken', result.payload.data.token);
    //let other components know that everything is fine by updating the redux` state
      dispatch(signInUserSuccess(result.payload.data)); //ps: this is same as dispatching RESET_USER_FIELDS
  });
};

class Header extends Component {
  static contextTypes = {
    router: PropTypes.object
  };

  componentWillReceiveProps (nextProps) {
    const {dispatch} = this.props;
    if (this.props.user.user && !nextProps.user.user) {//logout (had user(this.props.user.user) but no loger the case (!nextProps.user.user))
      console.log(" componentWillReceiveProps - cas A")
      this.context.router.push('/accueil');
    }

    if (nextProps.user.status === 'authenticated' && nextProps.user.user && !nextProps.user.error) {
      console.log(" componentWillReceiveProps - cas B")
      this.context.router.push('/accueil');
    }

    //error
    //Throw error if it was not already thrown (check this.props.user.error to see if alert was already shown)
    //If u dont check this.props.user.error, u may throw error multiple times due to redux-form's validation errors
    if (nextProps.user.status === 'signin' && !nextProps.user.user && nextProps.user.error && !this.props.user.error) {
      console.log(" componentWillReceiveProps - cas C")
      //alert(nextProps.user.error.message);
      /* throw new SubmissionError({
       username: 'User does not exist',
       _error: 'Identifiants incorrects'
       })*/
      dispatch(alertActions.error(nextProps.user.error.message));
    }
  }

  renderSignInLinks (authenticatedUser) {
    if (authenticatedUser) {
      return (
        <ul className="nav  nav-pills navbar-right">
          <li style={{paddingRight: '10px'}} role="presentation">
            <Link role="presentation" style={{color: '#996633', fontSize: '17px'}} to="/profile">
              {authenticatedUser.name}
            </Link>
          </li>
          <li style={{paddingRight: '10px'}} role="presentation">
            <a style={{color: '#996633', fontSize: '17px'}} onClick={this.props.logout} href="javascript:void(0)">
              Log out
            </a>
          </li>
        </ul>
      );
    }

    const {error, handleSubmit, submitting, alert} = this.props;
    return (
      <div className="jumbotron">
        <div className='container'>
          <div className="col-sm-8 col-sm-offset-2">
            {/* .alert-danger */}
            {alert.message &&
            <div className={`alert ${alert.type}`}>{alert.message}</div>
            }

            <form onSubmit={ handleSubmit(validateAndSignInUser) }>
              <Field
                name="username"
                type="text"
                component={ renderField }
                label="Username"/>
              <Field
                name="email"
                type="email"
                component={ renderField }
                label="Email(A implémenter plus tard)"/>
              <Field
                name="password"
                type="password"
                component={ renderField }
                label="Password"/>

              {error && <strong>{error}</strong>}

              <div className='buttonsLine'>
                <button
                  type="submit"
                  className="btn btn-primary"
                  disabled={ submitting }>
                  Login
                </button>
                <ul className="nav">
                  <li style={{paddingRight: '10px'}} role="presentation">
                    <Link role="presentation" style={{color: '#996633', fontSize: '17px'}} to="/signup">
                      Register
                    </Link>
                  </li>
                </ul>
              </div>
            </form>
          </div>
        </div>


      </div>
    );
  }

  renderLinks () {
    const {type, authenticatedUser} = this.props;
    if (type === 'posts_index') {
      return (
        <div className="container">
          {this.renderSignInLinks(authenticatedUser)}

        </div>
      );
    } else if (type === 'posts_new') {
      return (
        <div className="container">
          {this.renderSignInLinks(authenticatedUser)}
          <ul className="nav  nav-pills navbar-left">
            <li style={{paddingRight: '10px'}} role="presentation">
              <Link className="text-xs-right" style={{color: '#337ab7', fontSize: '17px'}} to="/">Back To Index</Link>
            </li>
          </ul>
        </div>
      );
    }

  };

  render () {

    return (
      <nav className="navbar navbar-default navbar-static-top">
        <div id="navbar" className="navbar-collapse collapse">
          {this.renderLinks()}

        </div>
      </nav>
    );
  }
}


export default reduxForm({
  form: 'Header', // a unique identifier for this form
  validate // <--- validation function given to redux-form
})(Header)