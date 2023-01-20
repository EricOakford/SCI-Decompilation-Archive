;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED) ;803
(include game.sh)
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
(instance fred of Actor)

(instance speedTest of Room
	(properties
		picture 99
		style PLAIN
	)
	
	(method (init)
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 6 versionFile)
		(FileIO fileClose versionFile)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) $fc00)
		)
		(fred
			view: 902
			setLoop: 0
			illegalBits: 0
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(= speed 0)
		(= machineSpeed 0)
		(= fastThreshold
			(if (not (if (<= 2 numColors) (<= numColors 16)))
				110
			else
				50
			)
		)
		(= mediumThreshold
			(if (not (if (<= 2 numColors) (<= numColors 16)))
				65
			else
				30
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1) (= doneTime (+ 60 (GetTime))))
		(if (and (u< doneTime (GetTime)) (not (self script?)))
			(cond 
				((< machineSpeed mediumThreshold)
					(= howFast slow)
					(theGame detailLevel: 1)
				)
				((< machineSpeed fastThreshold)
					(= howFast medium)
					(theGame detailLevel: 2)
				)
				(else (= howFast fast)
					(theGame detailLevel: 3)
				)
			)
			(self setScript: speedScript)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance speedScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(= speed 1)
				(= cycles 1)
			)
			(2
				(curRoom newRoom: restartRoom)
			)
		)
	)
)
