;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkSarge)
(include game.sh)
(use Main)
(use Talker)
(use Actor)
(use System)

(public
	sargeInterrogater 0
)

(instance sargeInterrogater of Talker
	(properties
		x 222
		y 85
		view 972
		disposeWhenDone 0
		talkWidth 200
		color 60
		back 0
		textX -210
		textY 30
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 hisEyes hisMouth &rest)
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

(instance hisMouth of Prop
	(properties
		nsTop 35
		nsLeft 19
		view 972
		loop 1
	)
)

(instance hisEyes of Prop
	(properties
		nsTop 20
		nsLeft 18
		view 972
		loop 2
	)
)
