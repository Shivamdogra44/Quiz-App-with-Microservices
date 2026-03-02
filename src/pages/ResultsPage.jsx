import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const ResultsPage = () => {
  const location = useLocation();
  const result = (location.state && location.state.result) || {};
  const score = result.score !== undefined ? result.score : result.correctAnswers;
  const totalQuestions = result.totalQuestions !== undefined
    ? result.totalQuestions
    : result.noOfQuestions;

  if (score === undefined || totalQuestions === undefined) {
    return (
      <div className="page-container">
        <div className="results-container">
          <h1>Quiz Results</h1>
          <p>No result data available.</p>
          <Link to="/">Back to Home</Link>
        </div>
      </div>
    );
  }

  const percentage = ((score / totalQuestions) * 100).toFixed(2);
  const passed = score >= totalQuestions / 2;

  return (
    <div className="page-container">
      <div className="results-container">
        <h1>Quiz Results</h1>
        <div className="score">{score} / {totalQuestions}</div>
        <p className="verdict">
          {percentage}% — {passed ? 'Great job!' : 'Better luck next time!'}
        </p>
        <Link to="/">Back to Home</Link>
      </div>
    </div>
  );
};

export default ResultsPage;

