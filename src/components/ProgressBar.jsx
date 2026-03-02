import React from 'react';

const ProgressBar = ({ progress }) => {
    return (
        <div role="progressbar" aria-valuenow={progress} aria-valuemin="0" aria-valuemax="100">
            <div style={{ width: `${progress}%`, backgroundColor: 'blue', height: '20px' }} />
        </div>
    );
};

export default ProgressBar;
