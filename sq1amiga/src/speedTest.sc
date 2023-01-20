;;; Sierra Script 1.0 - (do not remove this comment)
(script# 803)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	speedTest 0
)

(local
	doneTime
	machineSpeed
	versionFile
	mediumThreshold
	fastThreshold
)
(instance fred of Actor
	(properties)
)

(instance speedTest of Rm
	(properties
		picture 99
		style $0064
	)
	
	(method (init)
		(= versionFile (FileIO 0 {version} 1))
		(FileIO 5 version 6 versionFile)
		(FileIO 1 versionFile)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) -1024)
		)
		(fred
			view: 902
			setLoop: 0
			illegalBits: 0
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Fwd
			init:
		)
		(= speed 0)
		(= machineSpeed 0)
		(= fastThreshold
			(cond 
				((== numColors 32) 60)
				((not (if (<= 2 numColors) (<= numColors 16))) 110)
				(else 50)
			)
		)
		(= mediumThreshold
			(cond 
				((== numColors 32) 40)
				((not (if (<= 2 numColors) (<= numColors 16))) 65)
				(else 30)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if
		(and (u< doneTime (GetTime)) (not (self script?)))
			(cond 
				((< machineSpeed mediumThreshold) (= howFast 0) (theGame detailLevel: 1))
				((< machineSpeed fastThreshold) (= howFast 1) (theGame detailLevel: 2))
				(else (= howFast 2) (theGame detailLevel: 3))
			)
			(self setScript: speedScript)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance speedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1 (= speed 1) (= cycles 1))
			(2
				(if (== howFast 0)
					(theGame egoMoveSpeed: 0 setSpeed: 0)
				)
				(curRoom newRoom: restartRoom)
			)
		)
	)
)
