import React from 'react';

const Question = ({ question, options, selected, onAnswer }) => {
    return (
        <div className="question-container">
            <h2>{question}</h2>
            <div className="options">
                {options.map((option, index) => (
                    <button
                        key={index}
                        className={selected === option ? 'selected' : ''}
                        onClick={() => onAnswer(option)}
                    >
                        {option}
                    </button>
                ))}
            </div>
        </div>
    );
};

export default Question;
