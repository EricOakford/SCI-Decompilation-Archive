;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DIALOG.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	Some of the classes which implement the user interface of SCI.  These
;;;;	have been pulled out of Intrface.sc to save space.
;;;;
;;;;	Classes:
;;;;		DIcon
;;;;		DButton
;;;;		DEdit
;;;;		DSelector
;;;;		Controls
;;;;


(script# DIALOG)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(define	BMOD 16)		; width equalizer for buttons

(class DIcon 	kindof DItem
	;;; Icons are simply a view/loop/cel combination created by the view
	;;; editor VE.  They are generally not selectable.

	(properties
		type		dIcon
		view		0			; view number
		loop		0			; loop number
		cel		0 			; cel number
	)

	(method (setSize)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
)



(class DButton kindof DItem
	;;; Buttons are selectable items which a user clicks in with the mouse
	;;; or selects with the TAB key and ENTER in order to execute an action.

	(properties
		type		dButton
		state 	(| dActive dExit)
		text		0							;text displayed inside button
		font		SYSFONT					;should usally be left as the system font
	)

	(method (dispose isNotPtr)
		(if (and	text
					(or	(not argc)
							(not isNotPtr)
					)
				)
			(Memory MDisposePtr (self text?))
		)
		(super dispose:)
	)

	(method (setSize &tmp [r 4])
;		(define	BMOD 16)		; width equalizer for buttons

		(TextSize @[r 0] text font 0 NULL)

		; a button box is one pixel larger all around
		(+= [r 2] 2)
		(+= [r 3] 2)
		(= nsBottom (+ nsTop [r 2]))
		(= [r 3] (* (/ (+ [r 3] (- BMOD 1)) BMOD) BMOD))
		(= nsRight (+ [r 3] nsLeft))
	)
)



(class DEdit	kindof DItem
	;;; A text field which is editable by the user.

	(properties
		type		dEdit
		state 	dActive
		text		0				;default text when the edit item is drawn
		font		SYSFONT		;this is often changed to a user font
		max		0	  			;maximum number of characters allowed in field
		cursor	0				;cursor position in field
	)


	(method (track evt)
		(EditControl self evt)
		(return self)				;used to return 0, see Corey
	)

	(method (setSize &tmp [r 4])
		;; Size and set cursor position to the end of the text.

		; box is as sized by max * width of an "M"
		(= font inputFont)
		(TextSize @[r 0] {M} font 0 NULL)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft (/ (* [r 3] max 3) 4)))
		(= cursor (StrLen text))
	)
)


(class DSelector kindof DItem
	;;; Selectors are a list of text items which can be scrolled.  The user
	;;; selects one of the items either by clicking directly on the item
	;;; or by scrolling a high-lighted bar to the selection.

	(properties
		type 			dSelector
		state 		0
		font 			0
		x 				20			; width of text item (in characters)
		y 				6			; number of items displayed in selector
		text 			0			; the text items to be selected from
		cursor 		0			; the currently selected item
		topString 	0			; first line of text shown
		mark			0			; the LINE of selector that is selected
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
		(TextSize @[r 0] {M} font 0 NULL)
		(= nsBottom (+ nsTop 20 (* [r 2] y)))
		(= nsRight (+ nsLeft (/ (* [r 3] x 3) 4)))
		(= topString (= cursor text))
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
					(-= topString x)			
				)
				(-- lines)
			else
				(break)
			)
		)
		(return
			(if redraw
				(self draw:)
				TRUE
			;;else FALSE
			)
		)
	)

	(method (advance lines &tmp redraw)
		;; Advance requested (or to end) lines.

		; if empty, return
		(if (not (StrAt cursor 0))
			(return)
		)

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
					(+= topString x)
				)

				(-- lines)
			else
				(break)
			)

		)
		(return
			(if redraw
				(self draw:)
				TRUE
			;;else FALSE
			)
		)
	)

	(method (handleEvent event &tmp ret evtType evt newEvt i [r 4])
		;; Selectors are not really active so they always return 0,
		;; but they may claim the event.

		(if (event claimed?) (return 0))

		; remap some directions into arrows
;		(if (& (event type?) direction)
;			(event type:keyDown)
;			(switch (event message?)
;				(dirS
;					(event message:DOWNARROW)
;				)
;				(dirN
;					(event message:UPARROW)
;				)
;				(else
;					(event type:direction)
;				)
;			)
;		)				

		(= ret 0)
		(switch (event type?)
			(keyDown
				(event claimed:
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
							FALSE
						)
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
							(TextSize @[r 0] {M} font 0 NULL)
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


(class Controls kindof List

;;;	(methods
;;;		draw
;;;		handleEvent
;;;	)


	(method (draw)
		(self eachElementDo: #setSize:)
		(self eachElementDo: #draw:)
	)


	(method (handleEvent evt &tmp cont)
		;; Find and track an active control.

		(if (evt claimed?) (return 0))

		(if (= cont (self firstTrue: #handleEvent: evt))
			(if (not (cont checkState: dExit))
				(cont doit:)
				(= cont 0)
			)
		)
		(return cont)
	)
)
