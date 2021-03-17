;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	GAUGE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated:
;;;;
;;;;	A class to display a thermometer-like gauge in a Dialog, allowing
;;;;	the user to set a value.
;;;;
;;;;	Classes:
;;;;		Gauge


(script#	GAUGE)
(include game.sh)
(use Main)
(use DButton)
(use DText)
(use Plane)
(use String)
(use Dialog)


(define	BLOCKON		6)
(define	BLOCKOFF		7)

(local
	textI
	upI
	downI
	gaugeI
	okI
	normalI
	noI
	str
)



(class Gauge kindof Dialog
	(properties
		description 0		;text to tell the user what to do
		higher {up}			;text for the 'increase value' button
		lower {down}		;text for the 'decrease value' button
		normal 7				;default value of quantity being set
		minimum 0			;minimum value of quantity being set
		maximum 15			;maximum value of quantity being set
	)

;;;	(methods
;;;		update				;private -- used to update the display
;;;	)


	(method (init value &tmp dx dy)
		(= str (String newWith: 200 {}))
		;; Set up the Gauge Dialog.

		; give ourself the class SysPlane as our plane
		(= plane Plane)

		(self update:value)

		((= downI (DButton new:))
			text: lower,
			moveTo: MARGIN MARGIN,
			setSize:
		)
		(self add:downI, setSize:)
		((= gaugeI (DText new:))
			text:		str,
			moveTo:	(+ (downI nsRight?) MARGIN) MARGIN,
			font:		SYSFONT,
			setSize:
		)
		(self add:gaugeI, setSize:)
		((= upI (DButton new:))
			text: higher,
			moveTo:(+ (gaugeI nsRight?) MARGIN) MARGIN,
			setSize:
		)
		(self add:upI, setSize:)

		(+= nsBottom (* 2 MARGIN))

		((= okI (DButton new:))
			text: {OK},
			setSize:,
			moveTo: MARGIN nsBottom
		)
		((= normalI (DButton new:))
			text: {Normal},
			setSize:,
			moveTo: (+ (okI nsRight?) MARGIN) nsBottom
		)
		((= noI (DButton new:))
			text: {Cancel},
			setSize:,
			moveTo: (+ (normalI nsRight?) MARGIN) nsBottom
		)
		(self add:okI normalI noI, setSize:)
		(= dx (- (- nsRight (noI nsRight?)) MARGIN))

		((= textI (DText new:))
			text: description,
			font: smallFont,
			setSize: (- nsRight (* 2 MARGIN)),
			moveTo: MARGIN MARGIN
		)
		(= dy (+ (textI nsBottom?) MARGIN))
		(self add:textI)

		(upI move: 0 dy)
		(downI move: 0 dy)
		(gaugeI move: 0 dy)
		(okI move: dx dy)
		(normalI move: dx dy)
		(noI move: dx dy)

		(self	setSize:, center:, open: wTitled 15)
		(str dispose:)
	)


	(method (doit value &tmp i ret)
		;; Display the Gauge and let the user set the value.

		(self init:value)

		(= ret value)
		(repeat
			(self update:ret)
			(gaugeI draw:)

			(= i (super doit: okI))

			(cond
				((== i upI)
					;User wants to increase the value.
					(if (< ret maximum)
						(++ ret)
					)
				)
				((== i downI)
					;User wants to decrease the value.
					(if (> ret minimum)
						(-- ret)
					)
				)
				((== i okI)
					;Value is set.  Return.
					(break)
				)
				((== i normalI)
					;Reset to value which is considered 'normal'.
					(= ret normal)
				)
				((or (== i 0) (== i noI))
					;Bail out, setting the value back to that which was in
					;effect on entry.
					(= ret value)
					(break)
				)
			)
		)

		(self dispose:)
		(return ret)
	)


	(method (update value &tmp i range)
		;; Update the string which is the thermometer to reflect 'value'.

		(= range (- maximum minimum))
		(for	((= i 0))
				(< i range)
				((++ i))
			(str at: i (if (< i value) BLOCKON else BLOCKOFF))
		)
	)


	(method (handleEvent event)
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(LEFTARROW
						(event claimed:TRUE)
						(return downI)
					)
					(RIGHTARROW
						(event claimed:TRUE)
						(return upI)
					)
				)
			)
			(direction
				(switch (event message?)
					(dirW
						(event claimed:TRUE)
						(return downI)
					)
					(dirE
						(event claimed:TRUE)
						(return upI)
					)
				)
			)
		)
		(return (super handleEvent:event))
	)
)
