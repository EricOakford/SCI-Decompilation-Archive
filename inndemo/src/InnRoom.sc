;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;*** INNROOM.SC
;***	  This is the code so you can skip rooms or go back.
;***											 - Robert W. Lindsley
;***
;*************************************************************

(script#	INNROOM)
(include game.sh)
(use Main)
(use Game)
(use Actor)


(class InnRoom kindof Room
	(properties
		nextRoom		NULL
		prevRoom		NULL
	)
;RWL	(method (init)
;RWL		(super init:	&rest)
;RWL		(forwardBut init:, setPri:	15)
;RWL		(reverseBut init:, setPri:	15)
;RWL	)
	(method (handleEvent evt)
		(if 
			(and
				(== (evt type?) keyDown)
				(or
					(== (evt message?) SPACEBAR)
					(== (evt message?) ENTER)
				)
				(not modelessDialog)
			)
			(evt claimed:	TRUE)
			(if (curRoom nextRoom?)
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	
;RWL		(forwardBut handleEvent:	evt)
;RWL		(reverseBut handleEvent:	evt)
	)
)

(instance reverseBut of Prop
	(properties
		view		1
		loop		0
		cel		0
		x			257
		y			0
	)
	(method (handleEvent evt)
		(if (self onMe:	evt)
			(evt claimed:	TRUE)
			(if (curRoom prevRoom?)
				(curRoom newRoom:	(curRoom prevRoom?))
			)
		)
	)
)

(instance forwardBut of Actor
	(properties
		view		1
		loop		0
		cel		1
		x			288
		y			0
	)
	(method (handleEvent evt)
		(if (or (self onMe:	evt)
				(and
					(== (evt type?) keyDown)
					(or
						(== (evt message?) SPACEBAR)
						(== (evt message?) ENTER)
					)
					(not modelessDialog)
				)
			)
			(evt claimed:	TRUE)
			(if (curRoom nextRoom?)
				(curRoom newRoom:	(curRoom nextRoom?))
			)
		)
	)
)
