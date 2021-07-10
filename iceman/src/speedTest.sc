;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	speedTest 0
)

(local
	doneTime
	machineSpeed
	versionFile
)
(instance fred of Actor)

(instance speedTest of Room
	(properties
		picture 99
		style IRISIN
	)
	
	(method (init)
		(TheMenuBar state: FALSE)
		(HandsOff)
		(= versionFile (FOpen {version} 1))
		(FGets version 6 versionFile)
		(FClose versionFile)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) -1024)
		)
		(fred
			view: 799
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
		(if (and (u< doneTime (GetTime)) (not (self script?)))
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
			(self setScript: speedScript)
		)
	)
	
	(method (dispose)
		(HandsOn)
		(User blocks: 0)
		(super dispose:)
	)
)

(instance speedScript of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(theGame setSpeed: 6)
				(= cycles 1)
			)
			(2
				(TheMenuBar state: TRUE)
				(curRoom newRoom: startingRoom)
			)
		)
	)
)
