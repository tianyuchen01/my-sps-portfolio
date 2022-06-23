// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Add to the page a hard-coded string "Stay curious!"
 */
function showStayCurious() {
    const stayCurious = "✨Stay curious!✨";
    const curiousContainer = document.getElementById('curious-container');
    curiousContainer.innerText = stayCurious;
}

/**
 * Fetch the json string from server and parse it. Randomly add a string to the page.
 */
async function showRandomFacts() {
    const responseFromServer = await fetch('/random');
    const facts = await responseFromServer.json();

    // Connect to the specified button in HTML
    const randomFactContainer = document.getElementById('random-fact-container');

    // Create random effect for the content of the button
    const randomIdx = Math.floor(Math.random() * facts.length);
    randomFactContainer.innerText = facts[randomIdx];
}

/** 
 * Fetch feedbacks from the server and add them to the DOM. 
 */
async function loadFeedbacks() {
    const responseFromServer = await fetch('/list-feedback');
    const feedbacks = await responseFromServer.json();

    const feedbackList = document.getElementById('feedback-list');

    feedbacks.forEach((feedback) => {
        // Create list element and add content to it
        var feedbackElement = document.createElement('li');
        feedbackElement.appendChild(document.createTextNode(feedback));
        // Append list element to the unordered list
        feedbackList.appendChild(feedbackElement);
    });
}
