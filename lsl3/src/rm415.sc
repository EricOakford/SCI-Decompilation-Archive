;;; Sierra Script 1.0 - (do not remove this comment)
(script# 415)
(include game.sh)
(use Main)
(use n021)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm415 0
)

(instance rm415 of Room
	(properties
		picture 415
		east 410
		west 420
	)
	
	(method (init)
		(Load VIEW 415)
		(Load VIEW 416)
		(Load VIEW 417)
		(Load VIEW 418)
		(Load VIEW 419)
		(super init:)
		(self setRegions: CASINO_MIRROR)
		(cond 
			((== prevRoomNum 410)
				(ego posn: 318 143)
			)
			((or (== currentStatus egoSHOWGIRL)(> (ego y?) 180))
				(ego posn: 1 143)
			)
			(else
				(ego posn: 31 122)
			)
		)
		(NormalEgo)
		(ego init:)
		(aCraps init: isExtra: TRUE)
		(aCard init: isExtra: TRUE)
		(if (> machineSpeed 16)
			(aWalker init:)
			(aAlterEgo init:)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) cBLUE)
				(and (== currentStatus egoSHOWGIRL) (& (ego onControl:) cGREEN))
			)
			(curRoom newRoom: 420)
		)
	)
)

(instance aAlterEgo of Actor
	(properties
		view 700
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			view: (ego view?)
			setPri: 4
			posn: (ego x?) 122
			ignoreHorizon:
			ignoreActors:
		)
	)
	
	(method (doit)
		(aAlterEgo
			view: (ego view?)
			loop:
			(switch (ego loop?)
				(loopN loopS)
				(loopS loopN)
				(else  (ego loop?))
			)
			cel: (ego cel?)
			x: (ego x?)
			y: (- 125 (/ (- (ego y?) 127) 2))
		)
		(super doit:)
	)
)

(instance aCraps of Extra
	(properties
		y 62
		x 198
		view 414
		loop 2
		cel 11
		cycleSpeed 0
		cycleType ExtraEndLoop
		hesitation 11
		pauseCel 11
		minPause 22
		maxPause 111
	)
)

(instance aCard of Extra
	(properties
		y 59
		x 160
		view 414
		minPause 11
		maxPause 55
		minCycles 15
		maxCycles 44
	)
)

(instance aWalker of Actor
	(properties
		y 15
		x 119
		view 414
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setCycle: Walk
			setStep: 2 1
			setScript: WalkerScript
		)
	)
)

(instance WalkerScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(= seconds (Random 2 6))
			)
			(1
				(switch (Random 0 6)
					(0
						(aWalker
							view: (Random 415 419)
							posn: 251 15
							setPri: 1
							setMotion: MoveTo 119 15 self
						)
					)
					(1
						(aWalker
							view: (Random 415 419)
							posn: 119 15
							setPri: 1
							setMotion: MoveTo 251 15 self
						)
					)
					(2
						(aWalker
							view: 415
							posn: 186 33
							setPri: 0
							setMotion: MoveTo 186 142 self
						)
					)
					(3
						(aWalker
							view: 415
							posn: 186 142
							setPri: 0
							setMotion: MoveTo 186 33 self
						)
					)
					(else
						(= seconds 2)
					)
				)
				(= state -1)
			)
		)
	)
)
