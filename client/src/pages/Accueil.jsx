import React from 'react';
import { Component } from 'react';
import TitleToolbar from '../components/materialuimodified/TitleToolbar';

class Accueil extends Component {

  constructor (props) {
    super(props)
  }

  render() {
    <div>
      <TitleToolbar title="Accueil" forceUppercase large/>
    </div>
  }
}


export default Accueil;
