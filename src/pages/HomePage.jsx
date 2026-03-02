import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getQuizzes } from '../services/api';

const HomePage = () => {
    const [quizzes, setQuizzes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchQuizzes = () => {
        setLoading(true);
        setError(null);
        getQuizzes()
            .then(data => {
                setQuizzes(data);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchQuizzes();
    }, []);

    return (
        <main className="page-container">
            <h1>Available Quizzes</h1>
            {loading && <div className="spinner" aria-label="Loading quizzes" />}
            {error && (
                <div className="error-msg">
                    <p>Failed to load quizzes: {error}</p>
                    <button className="retry-btn" onClick={fetchQuizzes}>Retry</button>
                </div>
            )}
            {!loading && !error && (
                <div className="quiz-grid">
                    {quizzes.length === 0 ? (
                        <p>No quizzes available.</p>
                    ) : (
                        quizzes.map(quiz => (
                            <div className="quiz-card" key={quiz.id}>
                                <h3>{quiz.title}</h3>
                                <Link to={`/quiz/${quiz.id}`}>Start Quiz</Link>
                            </div>
                        ))
                    )}
                </div>
            )}
        </main>
    );
};

export default HomePage;

