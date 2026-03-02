import React from 'react';
import PropTypes from 'prop-types';

const ResultsPage = ({ score, totalQuestions }) => {
  return (
    <div>
      <h1>Quiz Results</h1>
      <p>Your Score: {score} out of {totalQuestions}</p>
      <p>{((score / totalQuestions) * 100).toFixed(2)}% - {score >= totalQuestions / 2 ? 'Great job!' : 'Better luck next time!'}</p>
      <h2>Score Analysis</h2>
      {/* Add additional analysis logic here */}
    </div>
  );
};

ResultsPage.propTypes = {
  score: PropTypes.number.isRequired,
  totalQuestions: PropTypes.number.isRequired,
};

export default ResultsPage;
