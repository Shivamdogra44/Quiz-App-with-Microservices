import React, { useState, useEffect } from 'react';

const QuizPage = () => {
    const [questions, setQuestions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [timer, setTimer] = useState(30); // 30 seconds timer

    useEffect(() => {
        // Example questions, you can replace this with an API call
        const fetchedQuestions = [
            { question: 'What is the capital of France?', options: ['Paris', 'London', 'Berlin', 'Madrid'], answer: 'Paris' },
            { question: 'What is 2 + 2?', options: ['3', '4', '5', '6'], answer: '4' },
        ];
        setQuestions(fetchedQuestions);

        // Timer logic
        const timerInterval = setInterval(() => {
            setTimer(prev => {
                if (prev <= 1) {
                    clearInterval(timerInterval);
                    return 0;
                }
                return prev - 1;
            });
        }, 1000);

        return () => clearInterval(timerInterval);
    }, []);

    const handleNextQuestion = () => {
        if (currentQuestionIndex < questions.length - 1) {
            setCurrentQuestionIndex(prev => prev + 1);
            setTimer(30); // Reset timer
        } // You might want to add logic to handle end of quiz
    };

    return (
        <div>
            <h1>Quiz Page</h1>
            <h2>{questions[currentQuestionIndex]?.question}</h2>
            <ul>
                {questions[currentQuestionIndex]?.options.map((option, index) => (
                    <li key={index}>{option}</li>
                ))}
            </ul>
            <div>Timer: {timer} seconds</div>
            <button onClick={handleNextQuestion}>Next Question</button>
        </div>
    );
};

export default QuizPage;