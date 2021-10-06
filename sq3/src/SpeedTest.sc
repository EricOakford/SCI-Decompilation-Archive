;;; Sierra Script 1.0 - (do not remove this comment)
(script# 777)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)

(public
	rm777 0
)

(local
	doneTime
)
(instance fred of Actor)

(instance rm777 of Room
	(properties
		picture 777
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) -1024)
		)
		(fred
			view: 778
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
			(theGame setSpeed: 5)
			(RedrawCast)
			(HandsOn)
			(curRoom newRoom: startingRoom)
		)
	)
)
