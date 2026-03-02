import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Navigation from './components/Navigation';
import HomePage from './pages/HomePage';
import QuizPage from './pages/QuizPage';
import ResultsPage from './pages/ResultsPage';
import './App.css';

const App = () => {
  return (
    <Router>
      <Navigation />
      <Switch>
        <Route path='/' exact component={HomePage} />
        <Route path='/quiz/:id' component={QuizPage} />
        <Route path='/results' component={ResultsPage} />
      </Switch>
    </Router>
  );
};

export default App;