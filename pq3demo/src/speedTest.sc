;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED) ;99
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
	fastSpeed
)
(instance fred of Actor)

(instance speedTest of Room
	(properties
		picture 98
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
			view: 99
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
		(= fastSpeed 90)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if (and (u< doneTime (GetTime)) (not (self script?)))
			(if (< machineSpeed fastSpeed)
				(= howFast slow)
				(theGame detailLevel: 1)
			else
				(= howFast fast)
			)
			(self setScript: speedScript)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance speedScript of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Palette PALIntensity 0 255 100)
				(fred setMotion: 0)
				(= cycles 1)
			)
			(1
				(= speed 2)
				(= cycles 1)
			)
			(2
				(curRoom newRoom: 120)
			)
		)
	)
)
