;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
(use Motion)
(use Game)

(public
	rm99 0
)

(local
	doneTime
)
(instance rm99 of Room
	(properties
		picture 991
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(ego
			view: 991
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(theGame setSpeed: 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if (< doneTime (GetTime))
			(cond 
				((<= machineSpeed 30)
					(= howFast slow)
				)
				((<= machineSpeed 60)
					(= howFast medium)
				)
				(else
					(= howFast fast)
				)
			)
			(theGame setSpeed: 6)
			(HandsOn)
			(ego setStep: 3 2)
			(if (GameIsRestarting)
				(curRoom newRoom: 44)
			else
				(DebugTP curRoom)
			)
		)
	)
)
