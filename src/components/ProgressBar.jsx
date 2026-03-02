import React from 'react';

const ProgressBar = ({ progress }) => {
    return (
        <div
            className="progress-bar-wrapper"
            role="progressbar"
            aria-valuenow={progress}
            aria-valuemin="0"
            aria-valuemax="100"
        >
            <div className="progress-bar-fill" style={{ width: `${progress}%` }} />
        </div>
    );
};

export default ProgressBar;

