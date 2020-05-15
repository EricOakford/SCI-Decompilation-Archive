;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED) ;803
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)

(public
	speedTest 0
)

(local
	doneTime
	machineSpeed
	versionFile
	fastSpeed
	mediumSpeed
)
(instance fred of Actor
	(properties)
)

(instance speedTest of Room
	(properties
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
			view: 854
			x: 0
			y: 99
			setStep: 1 1
			setMotion: MoveTo 319 99
			setCycle: Walk
			init:
		)
		(= speed 0)
		(= machineSpeed 0)
		(= fastSpeed (if (Btst fIsVGA) 90 else 60))
		(= mediumSpeed (if (Btst fIsVGA) 59 else 30))
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1) (= doneTime (+ 60 (GetTime))))
		(if (u< doneTime (GetTime))
			(cond 
				;EO: code modifications taken from speed patch
				((> machineSpeed fastSpeed)
					;(= howFast fast)
					(= howFast medium)
					(theGame detailLevel: 3)
					(= egoSpeed (+ 1 howFast))
					(= speed 2)
					;(= speed 1)
				)
				((> machineSpeed mediumSpeed)
					(= howFast medium)
					(theGame detailLevel: 2)
					(= egoSpeed (+ 1 howFast))
					(= speed 2)
					;(= speed 1)
				)
				(else
					(= howFast slow)
					(theGame detailLevel: 1)
					(= egoSpeed (+ 1 howFast))
					(= speed 0)
				)
			)
			(theGame egoMoveSpeed: egoSpeed)
			(ego moveSpeed: egoSpeed cycleSpeed: egoSpeed)
			(curRoom newRoom: startingRoom)
		)
	)
)
