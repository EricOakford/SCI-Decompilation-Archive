;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkEgo)
(include game.sh)
(use Main)
(use Talker)
(use Actor)
(use System)

(public
	egoInterrogater 0
)

(instance egoInterrogater of Talker
	(properties
		x 2
		y 0
		view 970
		disposeWhenDone 0
		talkWidth 200
		color 54
		back 0
		textX 105
		textY 20
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 egoEyes egoMouth &rest)
		(if (== interrogateSubject GRACE)
			(eyes view: (= view 9701))
			(mouth view: 9701)
		)
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
							(& (event type?) (| userEvent mouseDown joyDown))
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

(instance egoMouth of Prop
	(properties
		nsTop 43
		nsLeft 33
		view 970
		loop 1
	)
)

(instance egoEyes of Prop
	(properties
		nsTop 30
		nsLeft 39
		view 970
		loop 2
	)
)
