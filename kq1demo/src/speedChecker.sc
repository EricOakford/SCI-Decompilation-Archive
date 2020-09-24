;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)

(public
	speedChecker 0
)

(local
	doneTime
)
(instance fred of Actor)

(instance speedChecker of Room
	(properties
		picture vSpeed
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) $fc00))
		(fred
			view: vSpeed
			setLoop: 0
			illegalBits: 0
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(theGame setSpeed: 0)
		(= machineSpeed 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if (u< doneTime (GetTime))
			(cond 
				((<= machineSpeed 25)
					(= howFast slow)
				)
				((<= machineSpeed 50)
					(= howFast medium)
				)
				((<= machineSpeed 75)
					(= howFast fast)
				)
				(else
					(= howFast fastest)
				)
			)
			(theGame setSpeed: 5)
			(RedrawCast)
			(curRoom newRoom: startingRoom)
		)
	)
)
