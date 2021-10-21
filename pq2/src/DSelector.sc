;;; Sierra Script 1.0 - (do not remove this comment)
(script# DSELECTOR)
(include game.sh)
(use Intrface)


(class DSelector kindof DItem
	;;; Selectors are a list of text items which can be scrolled.  The user
	;;; selects one of the items either by clicking directly on the item
	;;; or by scrolling a high-lighted bar to the selection.

	(properties
		type dSelector
		state 0
		font 0
		x 20				; width of text item (in characters)
		y 6				; number of items displayed in selector
		text 	0			; the text items to be selected from
		cursor 0			; the currently selected item
		lsTop	0			; first line of text shown
		mark	0			; the LINE of selector that is selected
	)

;;;	(methods
;;;		indexOf			; return index of this string
;;;		at					; return ptr to this index
;;;		advance			; move selector bar up
;;;		retreat			; move selector bar up
;;;	)


	(method (indexOf what &tmp ptr i)
		;; Return index of this string OR -1.

		(= ptr text)
		(for ((= i 0)) (< i 300) ((++ i))

			; check for end of data
			(if (== 0 (StrLen ptr))
				(return -1)
			)
			(if (not (StrCmp what ptr))
				(return i)
			)
			(+= ptr x)
		)
	)


	(method (at what)
		;; Return pointer to this index OR 0.

		(return (+ text (* x what)))
	)


	(method (setSize &tmp [r 4])
		(TextSize @[r 0] {M} font)
		(= nsBottom (+ nsTop 20 (* [r 2] y)))
		(= nsRight (+ nsLeft (/ (* [r 3] x 3) 4)))
		(= lsTop (= cursor text))
		(= mark 0)
	)


	(method (retreat lines &tmp redraw)
		;; Retreat requested (or to top) lines.

		(= redraw FALSE)
		(while lines
			; are we at top?
			(if (!= cursor text)
				(= redraw TRUE)
				(-= cursor x)

				; do we scroll up?
				(if mark
					(-- mark)
				else
					(-= lsTop x)			
				)
				(-- lines)
			else
				(break)
			)
		)
		(if redraw
			(self draw:)
		)
	)


	(method (advance lines &tmp redraw)
		;; Advance requested (or to end) lines.

		(= redraw FALSE)
		(while lines
			; is there another line?
			(if (StrAt cursor x)
				(= redraw TRUE)
				(+= cursor x)
	
				; do we scroll?
				(if (< (+ mark 1) y)
					(++ mark)
				else
					(+= lsTop x)
				)

				(-- lines)
			else
				(break)
			)

		)
		(if redraw
			(self draw:)
		)
	)


	(method (handleEvent event &tmp ret evtType evt newEvt i [r 4])
		;; Selectors are not really active so they always return 0,
		;; but they may claim the event.

		(if (event claimed?) (return 0))

		; remap some directions into arrows
		(if (== direction (event type?))
			(event type:keyDown)
			(switch (event message?)
				(dirS
					(event message:DOWNARROW)
				)
				(dirN
					(event message:UPARROW)
				)
				(else
					(event type:direction)
				)
			)
		)				

		(= ret 0)
		(switch (event type?)
			(keyDown
				(event claimed:TRUE)
				(switch (event message?)
					(HOMEKEY
						(self retreat: 50)
					)
					(ENDKEY
						(self advance: 50)
					)
					(PAGEDOWN
						(self advance: (- y 1))
					)
					(PAGEUP
						(self retreat: (- y 1))
					)
					(DOWNARROW
						(self advance: 1)
					)
					(UPARROW
						(self retreat: 1)
					)
					(else
						(event claimed:FALSE)
					)
				)
			)
			(mouseDown
				(if (self check: event)
					(event claimed:TRUE)

					; determine sub part
					(cond
						; top bar
						((< (event y?) (+ nsTop 10))
							(repeat
								(self retreat: 1)
								(breakif (not (StillDown)))
							)
						)

						; bottom bar
						((> (event y?) (- nsBottom 10))
							(repeat
								(self advance: 1)
								(breakif (not (StillDown)))
							)
						)

						; it is in the center
						(else
							; determine line height
							(TextSize @[r 0] {M} font)
							(= i (/ (- (event y?) (+ nsTop 10)) [r 2]))

							(if (> i mark)		
								; need to advance
								(self advance: (- i mark))
							else
								; need to retreat
								(self retreat: (- mark i))
							)
						)									
					)
				)
			)
		)			
		(return  (if (and (event claimed?) (& state dExit)) self  else 0))
	)
)