; 2.4.3 Data-directed programming and additivity
; Whenever we deal with a set of generic operations that are common to a set of different types we are, in effect, dealing with a two-dimensional table that contains the possible operations on one axis and the possilbe types on the other axis. The entries in the table are the procedures that implement each operation for each type.

; Example:

;O          ______________Type______________
;p _________|_____Polar_____|__Rectangular_|
;e|real-part|real-part-polar|real-part-rect|
;r|imag-part|imag-part-polar|imag-part-rect|
;a|magnitude|magnitude-polar|magnitude-rect|
;t|angle    |angle-polar    |angle-rect    |
;i------------------------------------------          ;o
;n

; If new types are added the old implementation will no be modifed, just need to add a new column with the new functions (additivity)


