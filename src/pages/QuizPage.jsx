import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { getQuiz, submitAnswers } from '../services/api';
import Question from '../components/Question';
import ProgressBar from '../components/ProgressBar';

const DEFAULT_NUM_QUESTIONS = 10;

const QuizPage = () => {
    const { id } = useParams();
    const history = useHistory();
    const [questions, setQuestions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [answers, setAnswers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        setLoading(true);
        setError(null);
        getQuiz(id, DEFAULT_NUM_QUESTIONS)
            .then(data => {
                const qs = Array.isArray(data) ? data : (data.questions || []);
                setQuestions(qs);
                setAnswers(new Array(qs.length).fill(null));
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    }, [id]);

    const handleAnswer = (option) => {
        const updated = [...answers];
        updated[currentQuestionIndex] = option;
        setAnswers(updated);
    };

    const handleNext = () => {
        if (currentQuestionIndex < questions.length - 1) {
            setCurrentQuestionIndex(prev => prev + 1);
        }
    };

    const handlePrev = () => {
        if (currentQuestionIndex > 0) {
            setCurrentQuestionIndex(prev => prev - 1);
        }
    };

    const handleSubmit = () => {
        setSubmitting(true);
        submitAnswers(id, questions.length, answers)
            .then(result => {
                history.push('/results', { result });
            })
            .catch(err => {
                setError(err.message);
                setSubmitting(false);
            });
    };

    const progress = questions.length
        ? Math.round(((currentQuestionIndex + 1) / questions.length) * 100)
        : 0;

    if (loading) return <div className="page-container"><div className="spinner" aria-label="Loading quiz" /></div>;
    if (error) return <div className="page-container error-msg"><p>{error}</p></div>;
    if (questions.length === 0) return <div className="page-container"><p>No questions found for this quiz.</p></div>;

    const current = questions[currentQuestionIndex];

    return (
        <div className="page-container">
            <h1>Quiz</h1>
            <ProgressBar progress={progress} />
            <div className="quiz-container">
                <Question
                    question={current.questionTitle || current.question}
                    options={current.options || [current.option1, current.option2, current.option3, current.option4]}
                    selected={answers[currentQuestionIndex]}
                    onAnswer={handleAnswer}
                />
                <div className="quiz-navigation">
                    <button onClick={handlePrev} disabled={currentQuestionIndex === 0}>Previous</button>
                    <span>{currentQuestionIndex + 1} / {questions.length}</span>
                    {currentQuestionIndex < questions.length - 1 ? (
                        <button onClick={handleNext}>Next</button>
                    ) : (
                        <button onClick={handleSubmit} disabled={submitting}>
                            {submitting ? 'Submitting...' : 'Submit'}
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
};

export default QuizPage;
