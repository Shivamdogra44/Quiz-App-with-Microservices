import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = ({ quizzes }) => {
    return (
        <div>
            <h1>Available Quizzes</h1>
            <ul>
                {quizzes.map((quiz) => (
                    <li key={quiz.id}>
                        <Link to={`/quiz/${quiz.id}`}>{quiz.title}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default HomePage;
