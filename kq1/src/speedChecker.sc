;;; Sierra Script 1.0 - (do not remove this comment)
(script# 777)
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
(instance fred of Actor
	(properties
		signal $0000
	)
)

(instance speedChecker of Room
	(properties
		picture 777
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(Load VIEW 777)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) -1024)
		)
		(fred
			view: 777
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
				((<= machineSpeed 30) (= howFast 0))
				((<= machineSpeed 60) (= howFast 1))
				((<= machineSpeed 90) (= howFast 2))
				(else (= howFast 3))
			)
			(theGame
				setSpeed:
				(switch howFast
					(1 3)
					(0 1)
					(else  5)
				)
			)
			(RedrawCast)
			(HandsOn)
			(curRoom newRoom: startingRoom)
		)
	)
)
