;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkGrace)
(include game.sh)
(use Main)
(use Talker)
(use Actor)
(use System)

(public
	graceInterrogater 0
)

(instance graceInterrogater of Talker
	(properties
		x 222
		y 85
		view 971
		disposeWhenDone 0
		talkWidth 200
		color 21
		back 0
		textX -210
		textY 30
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 herEyes herMouth &rest)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?))
				((== ticks -1)
					(return FALSE)
				)
				(else
					(if (not cueVal)
						(switch (event type?)
							(joyDown
								(= cueVal 0)
							)
							(mouseDown
								(= cueVal (& (event modifiers?) shiftDown))
							)
							(keyDown
								(= cueVal (== (event message?) ESC))
							)
						)
					)
					(if
						(or
							(& (event type?) (| userEvent joyDown mouseDown))
							(and
								(& (event type?) keyDown)
								(OneOf (event message?) ENTER ESC)
							)
						)
						(event claimed: TRUE)
						(self dispose: disposeWhenDone)
						(return TRUE)
					)
				)
			)
		)
	)
)

(instance herMouth of Prop
	(properties
		nsTop 46
		nsLeft 23
		view 971
		loop 1
	)
)

(instance herEyes of Prop
	(properties
		nsTop 35
		nsLeft 16
		view 971
		loop 2
	)
)
