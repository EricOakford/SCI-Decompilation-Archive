;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room95 0
)
(synonyms
	(gull gull bird)
)

(local
	local0
	local1
	local2
	local3
	fisherman
	seagull
	[local6 2]
	ripple1
	ripple2
	ripple3
	local11
	caughtAFish
)
(instance fisherTheme of Sound
	(properties
		number 14
		priority -1
	)
)

(instance fallSound of Sound
	(properties
		number 51
		priority 3
	)
)

(instance Room95 of Room
	(properties
		picture 95
	)
	
	(method (init)
		(Load VIEW 2)
		(Load VIEW 8)
		(Load VIEW 19)
		(if (ego has: iFishingPole)
			(Load VIEW 12)
			(Load VIEW 13)
			(Load VIEW 14)
		)
		(Load VIEW 17)
		(Load VIEW 234)
		(Load VIEW 230)
		(Load VIEW 327)
		(super init:)
		(if isNightTime (curRoom overlay: 195))
		(self setRegions: GULL)
		(= local1 0)
		(= south (= north 31))
		(= east 7)
		(= west 31)
		(= horizon 72)
		(ego edgeHit: 0)
		(= local0 0)
		(= noWearCrown TRUE)
		(= ripple1 (Prop new:))
		(ripple1
			view: 650
			loop: 1
			cel: 1
			posn: 155 57
			setPri: 0
			ignoreActors:
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		(if howFast
			(= ripple2 (Prop new:))
			(= ripple3 (Prop new:))
			(ripple2
				view: 667
				loop: 3
				cel: 2
				posn: 216 178
				setPri: 0
				setCycle: Forward
				ignoreActors:
				cycleSpeed: 2
				init:
			)
			(ripple3
				view: 667
				loop: 6
				cel: 3
				posn: 209 169
				setPri: 0
				setCycle: Forward
				ignoreActors:
				cycleSpeed: 2
				init:
			)
		)
		(if (== fishermanState fisherInit) (= fishermanState fisherGoneFishing))
		(switch prevRoomNum
			(0
				(ego loop: 1 setPri: 13)
				(= currentStatus egoNormal)
				(ego view: 2 posn: 317 144)
				(= global107 9)
			)
			(7
				(if (== global107 9)
					(ego x: 318 y: 144)
					(ego illegalBits: -16384)
					(ego setPri: 13)
				else
					(ego view: 8 setCycle: Forward)
					(ego x: 318)
				)
			)
			(31
				(cond 
					((<= (ego y?) oldHorizon) (ego posn: (ego x?) (+ horizon (ego yStep?) 1)))
					((>= (ego y?) 189) (ego y: 188))
					((>= (ego x?) 319) (ego x: 1))
				)
				(if (<= (ego y?) horizon) (ego y: (+ horizon 2)))
				(ego view: 8 setCycle: Forward)
			)
		)
		(if (not isNightTime)
			(= seagull (Actor new:))
			(seagull
				posn: 278 124
				view: 327
				loop: 0
				cel: 0
				xStep: 8
				yStep: 6
				illegalBits: 0
				ignoreActors:
				ignoreHorizon:
				setPri: 13
				setAvoider: (Avoider new:)
				init:
			)
			(seagull setScript: gullActions)
		)
		(if (== fishermanState fisherGoneFishing)
			(= fisherman (Actor new:))
			(fisherTheme play:)
			(fisherman
				view: 233
				posn: 177 145
				setPri: 13
				loop: 0
				setCycle: Forward
				xStep: 3
				yStep: 2
				setScript: manActions
				init:
			)
			(= local3 1)
		)
		(ego xStep: 3 yStep: 2 init:)
		(if (and (== global107 9) (== local3 1))
			(ego observeControl: 32)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(or
					(and
						(cast contains: fisherman)
						(fisherman inRect: 249 138 305 148)
					)
					(and (== global107 9) (ego inRect: 249 138 305 148))
				)
				(== (gullActions state?) 0)
			)
			(gullActions changeState: 1)
		)
		(= local2 (ego onControl: 0))
		(if (and (== global107 9) (!= currentStatus 15))
			(cond 
				((& local2 $0010) (ego setScript: fallNorth))
				((& local2 $0004) (ego setScript: fallSouth))
				((& local2 $0008) (ego setScript: fallWest))
			)
		)
		(if (or (== local3 0) (!= global107 9))
			(ego ignoreControl: 32)
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'deliver>')
						(cond 
							((Said '/*/gull') (Print 95 0))
							((Said '/*[/seagull,man,man,man,man]>')
								(if (= inventorySaidMe (inventory saidMe:))
									(if (ego has: (inventory indexOf: inventorySaidMe))
										(Print 95 1)
									else
										(Print 95 2)
									)
								else
									(Print 95 3)
									(event claimed: TRUE)
								)
							)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/fish')
								(if (ego has: iFish)
									((Inventory at: iFish) showSelf:)
								else
									(Print 95 4)
								)
							)
							((Said '/dock') (Print 95 5))
							((Said '<under/dock') (Print 95 6))
							((Said '<under/water,ocean') (if (== (ego view?) 2) (Print 95 7) else (Print 95 8)))
							((Said '/barrel') (Print 95 9))
							((Said '/ocean,water') (Print 95 10))
							((Said '/gull,gull,gull')
								(if (cast contains: seagull)
									(Print 95 11)
								else
									(event claimed: FALSE)
								)
							)
							((Said '/hemp') (Print 95 12))
							((Said '/seagull,man,man,man,man')
								(if (== fishermanState fisherGoneFishing)
									(Print 95 13)
								else
									(Print 95 14)
								)
							)
							((Said '[<around][/room]')
								(if (== (ego view?) 2)
									(Print 95 15)
								else
									(Print 95 16)
								)
							)
						)
					)
				)
				(cond 
					((Said 'open/barrel') (Print 95 9))
					((or (Said 'drink') (Said 'get/drink'))
						(if (== (ego view?) 2)
							(Print 95 17)
						else
							(Print 95 18)
						)
					)
					((Said 'hop,(dive<in)')
						(if (== (ego view?) 2)
							(Print 95 19)
						else
							(Print 95 20)
						)
					)
					((Said 'dive,(bathe<under)[/ocean,water]') (Print 95 21))
					((Said 'bathe')
						(if (== (ego view?) 2)
							(Print 95 19)
						else
							(Print 95 22)
						)
					)
					((Said 'get/hemp') (Print 95 23))
					(
						(or
							(Said 'fish[<enter][/!*]')
							(Said 'capture,get/fish')
							(Said 'cast/pole')
						)
						(if (== global107 9)
							(curRoom setScript: fishing)
							(fishing changeState: 1)
						else
							(Print 95 24)
						)
					)
					((Said '/pole>')
						(cond 
							((ego has: iFishingPole)
								(cond 
									((Said 'bait') (event claimed: FALSE))
									((Said 'get') (AlreadyTook))
									((Said 'use') (Print 95 25))
								)
							)
							((not (cast contains: fisherman)) (event claimed: TRUE) (Print 95 26))
							((Said 'get') (Print 95 27))
							((Said 'rob') (Print 95 28))
						)
					)
					((Said '/gull>')
						(cond 
							((not (cast contains: seagull)) 0)
							((Said 'feed') (Print 95 29))
							((Said 'kiss') (Print 95 30))
							((Said 'converse') (Print 95 31))
							((Said 'capture,get') (Print 95 32))
						)
					)
					((Said '[/seagull,man,man,man,man]>')
						(cond 
							((Said 'get/*')
								(if (== fishermanState fisherGoneFishing)
									(Print 95 33)
								else
									(Print 95 34)
								)
							)
							((Said 'help') (Print 95 35))
							((Said 'kiss')
								(if (== fishermanState fisherGoneFishing)
									(Print 95 36)
								else
									(Print 95 34)
								)
							)
							((Said 'converse')
								(if (cast contains: fisherman)
									(Print 95 37)
								else
									(Print 95 38)
								)
							)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= noWearCrown FALSE)
		(ego baseSetter: 0)
		(super newRoom: newRoomNumber)
	)
)

(instance fallSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= noWearCrown TRUE)
				(= global107 0)
				(fallSound play:)
				(ego
					illegalBits: 0
					yStep: 10
					setMotion: MoveTo (ego x?) (+ (ego y?) 34) self
					setPri: 15
					view: 17
					cel: 255
					setCycle: EndLoop
				)
			)
			(1
				(ego yStep: 2 setPri: -1)
				(ego
					view: 19
					loop: (& (ego loop?) $0001)
					illegalBits: cWHITE
					cel: 255
					setCycle: EndLoop self
				)
			)
			(2
				(ego view: 8 setCycle: Forward illegalBits: cWHITE setPri: -1)
				(HandsOn)
				(= global107 0)
				(ego setScript: 0)
			)
		)
	)
)

(instance fallNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global107 0)
				(fallSound play:)
				(ego
					illegalBits: 0
					setPri: 11
					posn: (ego x?) (- (ego y?) 5)
					yStep: 10
					view: 17
					cel: 255
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) (+ (ego y?) 20) self
				)
			)
			(1
				(ego
					view: 8
					setCycle: Forward
					setStep: 3 2
					setPri: -1
					illegalBits: cWHITE
				)
				(HandsOn)
				(ego setScript: 0)
			)
		)
	)
)

(instance fallWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global107 0)
				(fallSound play:)
				(ego illegalBits: 0)
				(ego view: 17 setCycle: Forward setStep: 10 8 cel: 0)
				(ego
					setMotion: MoveTo (- (ego x?) 30) (+ (ego y?) 20) self
				)
			)
			(1
				(ego
					view: 19
					loop: (& (ego loop?) $0001)
					illegalBits: cWHITE
					cel: 255
					setPri: -1
					setCycle: EndLoop self
				)
			)
			(2
				(ego view: 8 xStep: 3 yStep: 2 setCycle: Forward)
				(HandsOn)
				(ego setScript: 0)
			)
		)
	)
)

(instance manActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 4) setReal: self 15)
			)
			(1
				(fisherman view: 234 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(2
				(fisherman loop: 1 cel: 255 setCycle: EndLoop self)
			)
			(3
				(fisherman
					view: 230
					setCycle: Walk
					setMotion: MoveTo 319 146 self
				)
				(= local3 0)
			)
			(4
				(= minuteMetFisherman gameMinutes)
				(= secondMetFisherman gameSeconds)
				(= hourMetFisherman gameHours)
				(= fishermanState fisherComingHome)
				(fisherman dispose:)
				(= fisherman NULL)
			)
		)
	)
)

(instance gullActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(seagull view: 327 cel: 255 loop: 0 setCycle: EndLoop self)
			)
			(2
				(seagull loop: 1 cel: 0 setCycle: Forward)
				(seagull setMotion: MoveTo -10 25 self)
			)
			(3 (seagull dispose:))
		)
	)
)

(instance fishing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego baseSetter: (ScriptID 0 1))
				(= currentStatus 15)
				(cond 
					(
					(and (ego has: iFishingPole) ((Inventory at: iWorm) ownedBy: 666)) (self changeState: 9))
					((ego has: iFishingPole) (self changeState: 19))
					(else
						(Print 95 39)
						(= currentStatus egoNormal)
						(fishing changeState: 0)
					)
				)
			)
			(9
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 144 self)
			)
			(10
				(ego setMotion: MoveTo 189 145 self)
			)
			(11
				(Print 95 40 #at -1 15)
				(ego
					view: 13
					loop: 2
					cel: 3
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(12
				(ego view: 12 loop: 0 cycleSpeed: 0 setCycle: Forward)
				((ScriptID 0 4) setReal: self 7)
			)
			(13
				(if (< (Random 1 100) 50)
					(ego loop: 1)
					(Print 95 41 #at -1 15)
					(self changeState: 14)
					((Inventory at: iFishingPole) loop: 0)
				else
					(self changeState: 50)
				)
			)
			(14
				(ego
					view: 14
					loop: 1
					cel: 255
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds 4)
			)
			(15
				(= gotItem TRUE)
				(ego
					view: 2
					setLoop: -1
					loop: 1
					cycleSpeed: 0
					setCycle: Walk
					baseSetter: 0
				)
				(= currentStatus egoNormal)
				(HandsOn)
				((Inventory at: iFish) moveTo: ego)
				(theGame changeScore: 3)
				((Inventory at: iWorm) moveTo: 777)
				(curRoom setScript: 0)
			)
			(19
				(HandsOff)
				(if (and (<= (ego y?) 141) (>= (ego x?) 289))
					(ego setMotion: MoveTo (ego x?) 143 self)
				else
					(self changeState: 20)
				)
			)
			(20
				(HandsOff)
				(ego setMotion: MoveTo 189 145 self)
			)
			(21
				(Print 95 42 #at -1 15)
				(ego
					view: 13
					loop: 1
					cel: 3
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(22
				(ego view: 12 loop: 0 cycleSpeed: 0 setCycle: Forward)
				((ScriptID 0 4) setReal: self 7)
			)
			(23
				(if (and (< (Random 1 100) 50) (== caughtAFish FALSE))
					(ego loop: 1)
					(self changeState: 24)
				else
					(Print 95 43 #at -1 15)
					(self changeState: 30)
				)
			)
			(24
				(ego
					view: 14
					loop: 0
					cel: 255
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds 4)
			)
			(25
				(Print 95 44 #at -1 15)
				(= caughtAFish TRUE)
				(ego
					view: 2
					setLoop: -1
					loop: 1
					cycleSpeed: 0
					setCycle: Walk
					baseSetter: 0
				)
				(= currentStatus egoNormal)
				(HandsOn)
				(curRoom setScript: 0)
			)
			(30
				(ego
					view: 13
					cel: 255
					loop: 1
					cycleSpeed: 2
					setCycle: EndLoop
				)
				(= seconds 3)
			)
			(31
				(ego
					view: 2
					setLoop: -1
					cycleSpeed: 0
					loop: 1
					setCycle: Walk
					baseSetter: 0
				)
				(= currentStatus egoNormal)
				(HandsOn)
				(curRoom setScript: 0)
			)
			(50
				(ego
					view: 13
					cel: 255
					loop: 2
					cycleSpeed: 2
					setCycle: EndLoop
				)
				((ScriptID 0 4) setReal: self 4)
			)
			(51
				(Print 95 45 #at -1 15)
				(ego
					view: 2
					loop: 1
					cycleSpeed: 0
					setCycle: Walk
					baseSetter: 0
				)
				(= currentStatus egoNormal)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)
