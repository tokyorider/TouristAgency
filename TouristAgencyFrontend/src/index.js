import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import MainMenuPage from './components/main-menu-page';
import ToristsPage from './components/entity-pages/tourists-page';
import ExcursionsPage from './components/entity-pages/exursions-page';
import HotelsPage from './components/entity-pages/hotels-page';
import CargoesPage from './components/entity-pages/cargoes-page';
import ToursPage from './components/entity-pages/tours-page';

import './index.css';

class App extends React.Component {
  render() {
    return (
      <Router>
        <div className="App">
           <Route path="/" exact component={MainMenuPage}/>
           <Route path="/tourists" exact component={ToristsPage}/>
           <Route path="/tours" exact component={ToursPage}/>
           <Route path="/excursions" exact component={ExcursionsPage}/>
           <Route path="/hotels" exact component={HotelsPage}/>
           <Route path="/cargoes" exact component={CargoesPage}/>
        </div>
      </Router>
    )
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('root')
);