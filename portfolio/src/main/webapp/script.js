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
 * Add a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * Add to the page a hard-coded string "Stay curious!"
 */
function showStayCurious() {
    const stayCurious = "✨Stay curious!✨";
    const curiousContainer = document.getElementById('curious-container');
    curiousContainer.innerText = stayCurious;
}

/**
 * Fetch the Hello greeting from the server and add it to the page.
 */
async function showHelloString() {
    const responseFromServer = await fetch('/hello');
    const textFromResponse = await responseFromServer.text();

    const helloContainer = document.getElementById('hello-container');
    helloContainer.innerText = textFromResponse;
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
