;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room29 0
)

(local
	local0
	local1
	local2
	aBird
	birdTimer
	wormTimer
	aWorm
	local7
)
(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance Room29 of Room
	(properties
		picture 29
	)
	
	(method (init)
		(= north 23)
		(= south 5)
		(= east 30)
		(= west 28)
		(= horizon 100)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 129))
		(self setRegions: FOREST)
		(if ((Inventory at: iWorm) ownedBy: 206)
			(Load VIEW 21)
			(Load VIEW 348)
		)
		(Load VIEW 17)
		(Load SOUND 51)
		(switch prevRoomNum
			(28
				(if (<= (ego y?) 156)
					(ego x: 1 y: 150)
				else
					(ego x: 1 y: 186)
				)
			)
			(30
				(if (<= (ego y?) horizon)
					(ego x: 318 y: (+ horizon 1))
				else
					(ego x: 318)
				)
			)
			(23
				(ego x: 264 y: (+ horizon 2))
			)
			(5 (ego y: 188))
			(0 (ego x: 290 y: 160))
		)
		(if
			(and
				(<= (Random 1 100) 50)
				((Inventory at: 19) ownedBy: 206)
			)
			(= aBird (Actor new:))
			(aBird
				view: 348
				loop: 2
				cel: 0
				posn: 257 165
				setScript: birdActions
				setCycle: Forward
				illegalBits: 0
				ignoreActors:
				ignoreHorizon:
				init:
			)
		)
		(ego init: view: 2)
		(curRoom setScript: wormActions)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= (= local0 (ego onControl: 0)) local7)
				(== (ego script?) 0)
				(== newRoomNum curRoomNum)
			)
			(= local7 local0)
			(if (& local0 $0004)
				(curRoom west: 0)
				(ego setScript: fallSouth)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said 'look/around')
							(Said 'look/room')
							(Said 'look[<around][/!*]')
						)
						(Print 29 0)
					)
					((Said 'capture,get/earthworm')
						(if (cast contains: aWorm)
							(if (< (ego distanceTo: aWorm) 15)
								(Face ego aWorm)
								(wormActions changeState: 10)
							else
								(Print 800 1)
							)
						else
							(Print 29 1)
						)
					)
					((Said 'look/crow,crow') (event claimed: FALSE))
					((Said 'look/robin,bird')
						(cond 
							((cast contains: aBird)
								(if (== (birdActions state?) 0)
									(Print 29 2)
								else
									(Print 29 3)
								)
							)
							((cast contains: crow) (event claimed: 0))
							(else (Print 29 4))
						)
					)
					((Said 'converse/robin,bird')
						(if (== (birdActions state?) 0)
							(Print 29 5)
						else
							(Print 29 6)
						)
					)
					((Said 'kill/robin,bird') (Print 29 7))
					((Said 'capture,get,kiss/robin,bird')
						(if
						(or (cast contains: aBird) (cast contains: crow))
							(Print 800 1)
						else
							(Print 29 4)
						)
					)
					((Said 'help/robin,bird') (Print 29 8))
					((Said 'deliver')
						(if (cast contains: aBird)
							(Print 29 9)
						else
							(Print 29 10)
						)
					)
					((Said 'look/earthworm')
						(cond 
							(
								(and
									(cast contains: aBird)
									((Inventory at: iWorm) ownedBy: 206)
									(== (birdActions state?) 0)
								)
								(Print 29 2)
							)
							(((Inventory at: iWorm) ownedBy: 29) (Print 29 11))
							((ego has: iWorm) ((Inventory at: iWorm) showSelf:))
							(else (Print 29 4))
						)
					)
					((Said 'look/dirt')
						(cond 
							(((Inventory at: iWorm) ownedBy: 29) (Print 29 11))
							(
							(and (== (birdActions state?) 0) (!= aBird 0)) (Print 29 2))
							(else (Print 29 12))
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(timers eachElementDo: #dispose 84)
		(if ((Inventory at: iWorm) ownedBy: 29)
			((Inventory at: iWorm) moveTo: 206)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance fallSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fallSound play:)
				(cond 
					((> (ego x?) 100) (= local1 12))
					((>= (ego x?) 65) (= local1 20))
					(else (= local1 30))
				)
				(if (> (+ (ego y?) local1) 188)
					(= local2 188)
				else
					(= local2 (+ (ego y?) local1))
				)
				(ego
					yStep: 6
					yStep: 6
					illegalBits: 0
					loop: (& (ego loop?) $0001)
					setCel: 0
					view: 17
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) local2 self
				)
			)
			(1
				(ego
					xStep: 3
					yStep: 2
					view: 21
					loop: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(ego view: 21 loop: 2 setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(curRoom west: 28)
				(ego illegalBits: cWHITE setCycle: Walk view: 2)
				(ego setScript: 0)
			)
		)
	)
)

(instance birdActions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(cast contains: aBird)
				(< (aBird distanceTo: ego) 30)
				(== (aBird loop?) 2)
			)
			(if (timers contains: birdTimer) (birdTimer dispose:))
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= birdTimer (Timer setReal: self 15))
			)
			(1
				(aBird cel: 255 loop: 3 setCycle: EndLoop self)
			)
			(2
				(= aWorm (Prop new:))
				(aWorm
					view: 348
					setLoop: 0
					setCycle: Forward
					ignoreActors:
					posn: (- (aBird x?) 8) (aBird y?)
					init:
				)
				(wormActions changeState: 1)
				(aBird
					setPri: 12
					setLoop: 1
					xStep: 7
					yStep: 4
					setMotion: MoveTo 182 51 self
				)
			)
			(3
				(aBird setMotion: MoveTo 20 -6 self)
			)
			(4
				(aBird dispose:)
				(= aBird NULL)
			)
		)
	)
)

(instance wormActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= wormTimer (Timer setReal: self 10))
				((Inventory at: iWorm) moveTo: 29)
			)
			(2
				(aWorm dispose:)
				((Inventory at: iWorm) moveTo: 206)
			)
			(10
				(wormTimer dispose:)
				(HandsOff)
				(ego view: 21 setMotion: 0 setCycle: EndLoop self)
			)
			(11
				(aWorm dispose:)
				(= aWorm 0)
				(ego setCycle: BegLoop self)
				(ego get: iWorm)
				(theGame changeScore: 2)
				(= gotItem TRUE)
			)
			(12
				(ego view: 2 setCycle: Walk)
				(HandsOn)
			)
		)
	)
)
