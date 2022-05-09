Promises, new in ES6, are objects that represent the not-yet-available result of an asynchronous operation. The keywords async and await were introduced in ES2017 and provide new syntax that simplifies asynchronous programming by allowing you to structure your Promise-based code as if it was synchronous. Finally, asynchronous iterators and the for/await loop were introduced in ES2018 and allow you to work with streams of asynchronous events using simple loops that appear synchronous.


## 13.1 Asynchronous Programming with Callbacks


At its most fundamental level, asynchronous programming in JavaScript is done with *callbacks*. A callback is a function that you write and then pass to some other function. That other function then invokes (“calls back”) your function when some condition is met or some (asynchronous) event occurs. The invocation of the callback function you provide notifies you of the condition or event, and sometimes, the invocation will include function arguments that provide additional details. This is easier to understand with some concrete examples, and the subsections that follow demonstrate various forms of callback-based asynchronous programming using both client-side JavaScript and Node.


Node also defines a number of event-based APIs. The following function shows how to make an HTTP request for the contents of a URL in Node. It has two layers of asynchronous code handled with event listeners. Notice that Node uses an `on()` method to register event listeners instead of `addEventListener()`:

```javascript
const https = require("https");

// Read the text content of the URL and asynchronously pass it to the callback.
function getText(url, callback) {
    // Start an HTTP GET request for the URL
    request = https.get(url);

    // Register a function to handle the "response" event.
    request.on("response", response => {
        // The response event means that response headers have been received
        let httpStatus = response.statusCode;

        // The body of the HTTP response has not been received yet.
        // So we register more event handlers to to be called when it arrives.
        response.setEncoding("utf-8");  // We're expecting Unicode text
        let body = "";                  // which we will accumulate here.

        // This event handler is called when a chunk of the body is ready
        response.on("data", chunk => { body += chunk; });

        // This event handler is called when the response is complete
        response.on("end", () => {
            if (httpStatus === 200) {   // If the HTTP response was good
                callback(null, body);   // Pass response body to the callback
            } else {                    // Otherwise pass an error
                callback(httpStatus, null);
            }
        });
    });

    // We also register an event handler for lower-level network errors
    request.on("error", (err) => {
        callback(err, null);
    });
}
```

## 13.2 Promises

A Promise is an object that represents the result of an asynchronous computation. That result may or may not be ready yet, and the Promise API is intentionally vague about this: there is no way to synchronously get the value of a Promise; you can only ask the Promise to call a callback function when the value is ready. If you are defining an asynchronous API like the getText() function in the previous section, but want to make it Promise-based, omit the callback argument, and instead return a Promise object. The caller can then register one or more callbacks on this Promise object, and they will be invoked when the asynchronous computation is done.

So, at the simplest level, Promises are just a different way of working with callbacks. However, there are practical benefits to using them. One real problem with callback-based asynchronous programming is that it is common to end up with callbacks inside callbacks inside callbacks, with lines of code so highly indented that it is difficult to read. Promises allow this kind of nested callback to be re-expressed as a more linear Promise chain that tends to be easier to read and easier to reason about.

Another problem with callbacks is that they can make handling errors difficult. If an asynchronous function (or an asynchronously invoked callback) throws an exception, there is no way for that exception to propagate back to the initiator of the asynchronous operation. This is a fundamental fact about asynchronous programming: it breaks exception handling. The alternative is to meticulously track and propagate errors with callback arguments and return values, but this is tedious and difficult to get right. Promises help here by standardizing a way to handle errors and providing a way for errors to propagate correctly through a chain of promises.

### 13.2.1 Using Promises

With the advent of Promises in the core JavaScript language, web browsers have begun to implement Promise-based APIs. In the previous section, we implemented a getText() function that made an asynchronous HTTP request and passed the body of the HTTP response to a specified callback function as a string. Imagine a variant of this function, getJSON(), which parses the body of the HTTP response as JSON and returns a Promise instead of accepting a callback argument. We will implement a getJSON() function later in this chapter, but for now, let’s look at how we would use this Promise-returning utility function:

```javascript
getJSON(url).then(jsonData => {
    // This is a callback function that will be asynchronously
    // invoked with the parsed JSON value when it becomes available.
});
```

getJSON() starts an asynchronous HTTP request for the URL you specify and then, while that request is pending, it returns a Promise object. The Promise object defines a then() instance method. Instead of passing our callback function directly to getJSON(), we instead pass it to the then() method. When the HTTP response arrives, the body of that response is parsed as JSON, and the resulting parsed value is passed to the function that we passed to then().

```javascript
// Suppose you have a function like this to display a user profile
function displayUserProfile(profile) { /* implementation omitted */ }

// Here's how you might use that function with a Promise.
// Notice how this line of code reads almost like an English sentence:
getJSON("/api/user/profile").then(displayUserProfile);

```

