import React from 'react';

const HomePage = () => {
    const quizzes = [
        { id: 1, title: 'Quiz 1' },
        { id: 2, title: 'Quiz 2' },
        { id: 3, title: 'Quiz 3' }
    ];

    return (
        <div>
            <h1>Available Quizzes</h1>
            <ul>
                {quizzes.map(quiz => (
                    <li key={quiz.id}>{quiz.title}</li>
                ))}
            </ul>
        </div>
    );
};

export default HomePage;
