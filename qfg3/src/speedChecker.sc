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
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) -1024))
		(fred
			view: 936
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
				((<= machineSpeed 40)
					(= howFast medium)
				)
				((<= machineSpeed 60)
					(= howFast fast)
				)
				(else
					(= howFast fastest)
				)
			)
			(theGame setSpeed: 5)
			(Animate (cast elements?) FALSE)
			(curRoom newRoom: startingRoom)
		)
	)
)
