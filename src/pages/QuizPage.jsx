import React, { useState, useEffect } from 'react';
import './QuizPage.css';

const QuizPage = () => {
  const [questions, setQuestions] = useState([]);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [timer, setTimer] = useState(60);

  useEffect(() => {
    // Fetch quiz questions
    const fetchQuestions = async () => {
      // Replace with your API endpoint or mock data
      const response = await fetch('/api/quiz-questions');
      const data = await response.json();
      setQuestions(data);
    };

    fetchQuestions();
  }, []);

  useEffect(() => {
    // Timer logic
    const countdown = setInterval(() => {
      setTimer((prevTimer) => (prevTimer > 0 ? prevTimer - 1 : prevTimer));
    }, 1000);

    return () => clearInterval(countdown);
  }, []);

  const handleNextQuestion = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    }
  };

  const handlePreviousQuestion = () => {
    if (currentQuestionIndex > 0) {
      setCurrentQuestionIndex(currentQuestionIndex - 1);
    }
  };

  return (
    <div className="quiz-page">
      <h1>Quiz</h1>
      <p>Time Remaining: {timer} seconds</p>
      {questions.length > 0 && (
        <div>
          <h2>{questions[currentQuestionIndex].question}</h2>
          <ul>
            {questions[currentQuestionIndex].options.map((option, index) => (
              <li key={index}>{option}</li>
            ))}
          </ul>
        </div>
      )}
      <div className="navigation-controls">
        <button onClick={handlePreviousQuestion} disabled={currentQuestionIndex === 0}>Previous</button>
        <button onClick={handleNextQuestion} disabled={currentQuestionIndex === questions.length - 1}>Next</button>
      </div>
    </div>
  );
};

export default QuizPage;
