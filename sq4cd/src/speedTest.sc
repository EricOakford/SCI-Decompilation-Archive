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
	fastSpeed
)
(instance fred of Actor)

(instance speedTest of Room
	(properties
		picture 803
		style PLAIN
	)
	
	(method (init)
		(= versionFile (FileIO fileOpen {version} fRead))
		(FileIO fileFGets version 6 versionFile)
		(FileIO fileClose versionFile)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) $fc00))
		(fred
			view: 803
			setLoop: 0
			illegalBits: 0
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(= laptopPlug (Random 0 9))
		(= beamSetting 3417)
		(= speed 0)
		(= machineSpeed 0)
		(= fastSpeed (if (Btst fIsVGA) 90 else 40))
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ machineSpeed) 1) (= doneTime (+ 60 (GetTime))))
		(if
		(and (u< doneTime (GetTime)) (not (self script?)))
			(if (< machineSpeed fastSpeed)
				(= howFast 0)
				(theGame detailLevel: 1)
			else
				(= howFast 2)
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
			(1 (= speed 6) (= cycles 1))
			(2
				(= register 1)
				(while (<= register 5)
					(= [timeCodeXenon register] (Random 0 14))
					(++ register)
				)
				(curRoom newRoom: startingRoom)
			)
		)
	)
)
