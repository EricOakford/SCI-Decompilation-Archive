;;; Sierra Script 1.0 - (do not remove this comment)
(script# 371)
(include sci.sh)
(use Main)
(use Print)


(class AnimatePrint of Print
	(properties
		dialog 0
		window 0
		title 0
		mode 0
		font -1
		width 0
		x -1
		y -1
		ticks 0
		caller 0
		retValue 0
		modeless 0
		first 0
		saveCursor 0
		myMouth 0
		myEyes 0
		theLength 0
	)
	
	(method (init)
		(= modeless 1)
		(= ticks 120)
		(if myMouth (myMouth init: theLength))
		(if myEyes (myEyes init:))
		(super init:)
	)
	
	(method (dispose)
		(if myMouth (myMouth dispose:))
		(if myEyes (myEyes dispose:))
		(super dispose:)
		((curRoom script?) cue:)
	)
	
	(method (addText param1 param2 param3 param4)
		(= width 230)
		(= theLength
			(/
				(Message msgSIZE curRoomNum param1 param2 param3 param4)
				12
			)
		)
		(super addText: param1 param2 param3 param4 &rest)
	)
)
