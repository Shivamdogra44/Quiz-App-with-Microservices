import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './Home';
import Quiz from './Quiz';
import Results from './Results';

const App = () => {
  return (
    <Router>
      <Switch>
        <Route path='/' exact component={Home} />
        <Route path='/quiz' component={Quiz} />
        <Route path='/results' component={Results} />
      </Switch>
    </Router>
  );
};

export default App;