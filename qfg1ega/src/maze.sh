;;; Sierra Script 1.0 - (do not remove this comment)
; Mages Maze defines
(define ChaseRange 30)


; Priority States
(define LowPri 4) ;pRED
(define HighPri 7)	;pLGREY

	; Directions
(enum
	mazeN
	mazeNE
	mazeE
	mazeSE
	mazeS
	mazeSW
	mazeW
	mazeNW
)
; Bug Sizes
(enum
	smallBug
	mediumBug
	largeBug
)
; doCommands
(enum
	mmazeNOTHING
	mmazeCHOOSE
	mmazeFETCH
	mmazeOPEN
	mmazeTRIGGER
	mmazeFLAME
)
