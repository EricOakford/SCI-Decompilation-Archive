;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
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
	Room44 0
)

(local
	egoTickling
	bottle
	egoX
	local3
)
(instance tickleSound of Sound
	(properties
		number 60
	)
)

(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance bottleCage of Cage
	(properties
		top 144
		left 130
		bottom 175
		right 218
	)
)

(instance Room44 of Room
	(properties
		picture 44
		style DISSOLVE
	)
	
	(method (init)
		(= isIndoors TRUE)
		(super init:)
		(Load VIEW 52)
		(Load VIEW 17)
		(Load VIEW 19)
		(Load VIEW 7)
		(Load VIEW 6)
		(Load VIEW 53)
		(Load VIEW 49)
		(Load VIEW 21)
		(Load VIEW 73)
		(Load SOUND 51)
		(if (ego has: iPeacockFeather) (Load SOUND 60) (Load VIEW 51))
		(= noWearCrown 1)
		(ego view: 8 posn: 39 160 viewer: inWhale init:)
		((= egoTickling (Prop new:))
			view: 0
			posn: 161 75
			init:
			hide:
		)
		(if ((Inventory at: iGlassBottle) ownedBy: 44)
			(= bottle (Actor new:))
			(bottle
				view: 531
				posn: 201 166
				xStep: 1
				yStep: 1
				setLoop: 0
				ignoreActors: 0
				illegalBits: -32768
				observeControl: 4 1
				setCycle: Forward
				setMotion: Wander 4
				moveSpeed: 8
				cycleSpeed: 2
				observeBlocks: bottleCage
				init:
			)
		)
		(curRoom setScript: deadTimer)
		(Print 44 0)
		(Print 44 1)
		(= swallowedByWhale TRUE)
		(= local3 0)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: FALSE) $0004)
				(== (ego script?) 0)
				(== (deadTimer state?) 0)
				(<= (inWhale state?) 0)
			)
			(ego setScript: slide)
			(fallSound play:)
			(if (== (ego view?) 2)
				(slide changeState: 10)
			else
				(slide changeState: 1)
			)
		)
	)
	
	(method (dispose)
		(timers eachElementDo: #dispose 84)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'get,(look<in)/skeleton,man,person,james') (Print 44 2))
					((or (Said 'get,capture/fish') (Said 'fish')) (Print 44 3))
					((Said 'get,drink[/drink,water]') (Print 44 4))
					((Said 'get/boat') (Print 44 5))
					((Said 'get/bottle')
						(cond 
							(((Inventory at: iGlassBottle) ownedBy: 44)
								(if (< (ego distanceTo: bottle) 15)
									(Print 44 6)
									(bottle dispose:)
									(= gotItem TRUE)
									(ego get: 31)
								else
									(Print 800 1)
								)
							)
							((ego has: iGlassBottle) (Print 44 7))
							(else (Print 44 8))
						)
					)
					((Said '>,ignite,create,begin/fire') (Print 44 9))
					((Said 'look>')
						(cond 
							((Said '/fish') (Print 44 10))
							((Said '/boat') (Print 44 11))
							((Said '<under/water') (Print 44 12))
							((Said '/)') (Print 44 13))
							((Said '/tongue') (Print 44 14))
							((Said '/esophagus,lips,molar') (Print 44 15))
							((Said '/uvula') (Print 44 16))
							((Said '/skeleton,james,man,person') (Print 44 17))
							((Said '/water')
								(if ((Inventory at: iGlassBottle) ownedBy: 44)
									(Print 44 18)
								else
									(Print 44 19)
								)
							)
							((Said '[<up,around,at][/room,whale]') (Print 44 0) (Print 44 1))
						)
					)
					((Said 'hit/molar') (if (< (ego y?) 82) (Print 44 20) else (Print 800 1)))
					((Said 'hit/tongue') (Print 44 21))
					((Said 'hit/uvula,esophagus')
						(if (ego inRect: 140 71 178 86)
							(Print 44 22)
						else
							(Print 800 1)
						)
					)
					((Said 'climb') (Print 44 23))
					((or (Said 'call,help') (Said ',/&')) (Print 44 24))
					((Said '/') (Print 44 25))
					((Said 'bathe') (Print 44 26))
					((Said 'open,force/lips') (Print 44 27))
					((Said '>/fire') (Print 44 9))
					((Said 'tickle')
						(if (ego has: iPeacockFeather)
							(if (ego inRect: 140 71 178 86)
								(ego setScript: tickle)
								(tickle changeState: 1)
								(theGame changeScore: 5)
							else
								(Print 44 28)
							)
						else
							(Print 44 29)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance slide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(cond 
					((< (ego x?) 50) (= egoX (+ (ego x?) 20)))
					((> (ego x?) 270) (= egoX (- (ego x?) 20)))
					(else (= egoX (ego x?)))
				)
				(ego
					view: 52
					illegalBits: 0
					setLoop: 2
					setCycle: Forward
					yStep: 5
					xStep: 3
					setMotion: MoveTo egoX 145 self
				)
			)
			(2
				(ego view: 19 setLoop: 0 cel: 2 setCycle: EndLoop self)
			)
			(3
				(fallSound dispose:)
				(ego viewer: inWhale)
				(ego
					illegalBits: cWHITE
					setLoop: -1
					xStep: 2
					yStep: 1
					setScript: 0
				)
				(HandsOn)
			)
			(10
				(HandsOff)
				(ego view: 17 setLoop: (& (ego loop?) $0001))
				(ego illegalBits: 0 cel: 255 setCycle: EndLoop self)
			)
			(11 (self changeState: 1))
		)
	)
)

(instance inWhale of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (ego script?) 0)
			(cond 
				((& (ego onControl: TRUE) $0200) (ego view: 8 xStep: 2 yStep: 1 setCycle: Forward))
				((& (ego onControl: TRUE) $0008) (ego view: 7 xStep: 2 yStep: 1 setCycle: Walk))
				((& (ego onControl: TRUE) $0800) (ego view: 6 xStep: 2 yStep: 1 setCycle: Walk))
				((& (ego onControl: TRUE) $0001)
					(if (== (ego view?) 2)
						(slide changeState: 1)
					else
						(ego view: 53 setCycle: Walk setStep: 1 1)
					)
				)
				(
					(and
						(!= (ego view?) 2)
						(& (ego onControl: TRUE) $0010)
						(== (ego script?) 0)
						(<= state 0)
						(<= (deadTimer state?) 0)
					)
					(self changeState: 1)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(if (!= (ego view?) 2)
					(HandsOff)
					(ego
						viewer: 0
						view: 49
						setMotion: 0
						loop: (& (ego loop?) $0001)
						cel: 4
						setCycle: BegLoop self
					)
				else
					(self changeState: 0)
				)
			)
			(2
				(ego view: 21 cel: 6 setCycle: BegLoop self)
			)
			(3
				(ego view: 2 setStep: 2 1 setCycle: Walk viewer: inWhale)
				(= state 0)
				(HandsOn)
			)
		)
	)
)

(instance tickle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(timers eachElementDo: #dispose 84)
				(curRoom script: 0)
				(tickleSound play: self)
				(ego setMotion: 0 viewer: 0)
				(Face ego egoTickling)
				(ego loop: (& (ego loop?) $0001))
				(ego view: 51 loop: (& (ego loop?) $0001) setCycle: Forward)
			)
			(2
				(Print 44 30)
				(ego setLoop: -1 setCel: -1)
				(HandsOn)
				(= noWearCrown 0)
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance deadTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iPeacockFeather)
					((ScriptID 0 5) setReal: self 1 4)
				else
					((ScriptID 0 5) setReal: self 2 1)
				)
			)
			(1
				(ego viewer: 0)
				(++ local3)
				(if (and (> (inWhale state?) 0) (< local3 4))
					(-- state)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(2
				(HandsOff)
				(sounds eachElementDo: #stop 0)
				(cond 
					((< (ego x?) 50) (= egoX (+ (ego x?) 20)))
					((> (ego x?) 270) (= egoX (- (ego x?) 20)))
					(else (= egoX (ego x?)))
				)
				(cond 
					((== (ego view?) 2) (self changeState: 10))
					((== (ego view?) 53)
						(fallSound play:)
						(ego
							view: 52
							setLoop: 2
							setCycle: Forward (ego yStep: 5)
							setMotion: MoveTo egoX 145 self
						)
					)
					(else (self changeState: 4))
				)
			)
			(3
				(ego view: 19 setLoop: 0 cel: 2 setCycle: EndLoop self)
			)
			(4 (self changeState: 20))
			(10
				(fallSound play:)
				(ego
					setLoop: (& (ego loop?) $0001)
					view: 17
					cel: 255
					setCycle: EndLoop self
				)
			)
			(11
				(ego
					view: 52
					setLoop: 2
					setCycle: Forward
					yStep: 5
					xStep: 3
					illegalBits: 0
					setMotion: MoveTo egoX 145 self
				)
			)
			(12 (self changeState: 3))
			(20
				(ego view: 73 setLoop: 0 cel: 6 setCycle: EndLoop self)
			)
			(21
				(ego hide:)
				(Print 44 31)
				((ScriptID 0 5) setReal: self 5)
			)
			(22 (= dead TRUE))
		)
	)
)
