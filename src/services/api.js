// api.js - API service layer connected to the Spring Cloud API Gateway

const API_BASE_URL = process.env.API_BASE_URL || 'http://localhost:8083';

async function getData(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error('Network response was not ok: ' + response.statusText);
    }
    return response.json();
}

async function postData(url, data) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        throw new Error('Network response was not ok: ' + response.statusText);
    }
    return response.json();
}

// Quiz endpoints
export function getQuizzes() {
    return getData(`${API_BASE_URL}/quiz`);
}

export function getQuiz(id, noOfQues) {
    return getData(`${API_BASE_URL}/quiz/${id}/noOfQues/${noOfQues}`);
}

export function submitAnswers(id, noOfQues, answers) {
    return postData(`${API_BASE_URL}/quiz/answer/${id}/noOfQues/${noOfQues}`, answers);
}

// Question endpoints
export function getQuestions(quizId) {
    return getData(`${API_BASE_URL}/question/quiz/${quizId}`);
}

// Result endpoints
export function getResults() {
    return getData(`${API_BASE_URL}/result`);
}

export function getResult(id) {
    return getData(`${API_BASE_URL}/result/${id}`);
}

export function createResult(resultData) {
    return postData(`${API_BASE_URL}/result`, resultData);
}

export { getData, postData };
