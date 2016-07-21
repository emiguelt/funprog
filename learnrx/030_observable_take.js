function(button) {
  var buttonClicks = Observable.fromEvent(button, "click");

  // Use take() to listen for only one button click
  // and unsubscribe.
  buttonClicks.take(1).
    // Insert take() call here
    forEach(function(clickEvent) {
      alert("Button was clicked once. Stopping Traversal.");
    });
}
    
