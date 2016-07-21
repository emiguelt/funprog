function(sprite, spriteContainer) {
  var spriteMouseDowns = Observable.fromEvent(sprite, "mousedown"),
    spriteContainerMouseMoves = Observable.fromEvent(spriteContainer, "mousemove"),
    spriteContainerMouseUps = Observable.fromEvent(spriteContainer, "mouseup"),
    spriteMouseDrags =
      // For every mouse down event on the sprite...
      spriteMouseDowns.
        concatMap(function(contactPoint) {
          // ...retrieve all the mouse move events on the sprite container...
          return spriteContainerMouseMoves.
            // ...until a mouse up event occurs.
            takeUntil(spriteContainerMouseUps);
        });

  // For each mouse drag event, move the sprite to the absolute page position.
  spriteMouseDrags.forEach(function(dragPoint) {
    sprite.style.left = dragPoint.pageX + "px";
    sprite.style.top = dragPoint.pageY + "px";
  });
}
        
