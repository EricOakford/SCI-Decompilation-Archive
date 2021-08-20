;;; Sierra Script 1.0 - (do not remove this comment)
(script# 69)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm069 0
)

(local
	local0
	local1
	shipLeaving
)
(instance rm069 of Room
	(properties
		picture 69
		horizon 82
		north 66
		east 70
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(Load VIEW 92)
		(if (not (ego has: iMetalPole)) (anemo init:) (pole init:))
		(if (not ortegaWorkersLeft)
			(Load VIEW 93)
			(Load SOUND 38)
			(Load SOUND 44)
			(pirate1 init:)
			(pirate2 init:)
			(= local0 30)
		)
		(super init:)
		(switch prevRoomNum
			(66 (ego posn: 81 84 init:))
			(70
				(ego
					posn: 317 (if (< (ego y?) 80) 83 else (ego y?))
					init:
				)
			)
			(690
				(ego view: 0 loop: 0 cel: 0 posn: 70 155 init:)
				(ego setLoop: -1)
			)
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit &tmp egoOnControl [temp1 50])
		(super doit:)
		(if local1 (curRoom newRoom: 690))
		(if global219 (-- local0))
		(if (and (== local0 1) (not script))
			(= local0 0)
			(curRoom setScript: LeaveScript)
		)
		(if (not (curRoom script?))
			(cond 
				((== (= egoOnControl (ego onControl:)) 3) (ego setPri: 3 illegalBits: 0) (= fallingIntoLava 1))
				(
				(and (== egoOnControl 5) (not ortegaWorkersLeft)) (curRoom setScript: shootScript))
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/gun') (if ortegaWorkersLeft (Print 69 0) else (Print 69 1)))
					((Said '/[around,area]') (if ortegaWorkersLeft (Print 69 2) else (Print 69 3)))
					((Said '/craft') (if shipLeaving (Print 69 4) else (event claimed: 0)))
					(
						(Said
							'/station,device,device,equipment[<seismic,research]'
						)
						(if (ego has: iMetalPole) (Print 69 5) else (Print 69 6))
					)
					((Said '/pole')
						(if (InRoom iMetalPole)
							(Print 69 7)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/anemometer') (Print 69 8))
					((Said '/antenna') (Print 69 9))
					((Said '/box')
						(if (ego inRect: 220 141 254 155)
							(Print 69 10)
						else
							(Print 69 11)
						)
					)
					((Said '<in,in,through/scope')
						(if (ego inRect: 56 139 76 159)
							(curRoom setScript: ScopeScript)
						else
							(Print 69 12)
						)
					)
					((Said '/scope') (Print 69 13))
					((Said '/rock,boulder') (Print 69 14))
				)
			)
			((Said 'get>')
				(cond 
					(
						(Said
							'/antenna,station,device,equipment[<seismic,research]'
						)
						(Print 69 15)
					)
					((or (Said '/scope') (Said '/box')) (Print 69 16))
					((Said '/anemometer')
						(cond 
							((ego has: iMetalPole) (Print 69 17))
							((ego inRect: 230 129 260 143) (Print 69 18))
							(else (NotClose))
						)
					)
					((Said '/pole')
						(cond 
							((ego has: iMetalPole) (Print 69 19))
							((ego inRect: 230 129 260 143)
								(Print 69 20)
								(ego get: iMetalPole)
								(pole dispose:)
								(theGame changeScore: 10)
								(anemo dispose:)
							)
							(else (NotClose))
						)
					)
					((Said '/detonator')
						(cond 
							((ego has: iThermalDetonator) (Print 69 19))
							((ego inRect: 220 141 254 155) (Print 69 21) (theGame changeScore: 10) (ego get: iThermalDetonator))
							(else (NotClose))
						)
					)
				)
			)
			((Said 'conceal') (Print 69 22))
			((Said 'use/scope')
				(if (ego inRect: 56 139 76 159)
					(curRoom setScript: ScopeScript)
				else
					(Print 69 12)
				)
			)
			((Said 'attack/man,flunky') (if ortegaWorkersLeft (Print 69 23) else (Print 69 24)))
			((Said 'converse[/man,flunky]') (if ortegaWorkersLeft (Print 69 25) else (Print 69 26)))
			((Said 'open/box') (Print 69 27))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not script)
			(timers eachElementDo: #dispose)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance ScopeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(if (ego inRect: 67 139 76 149)
					(ego setMotion: MoveTo 63 155 self)
				else
					(= cycles 2)
				)
			)
			(1
				(ego setMotion: MoveTo 70 155 self)
			)
			(2
				(ego
					view: 92
					setLoop: 4
					cel: 0
					illegalBits: -32768
					setCycle: EndLoop self
				)
			)
			(3
				(= local1 1)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance shootScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 108 123 self)
				(pirate1 setScript: 0 setLoop: 3 setCycle: EndLoop)
			)
			(1
				(ego setCycle: 0)
				(jello play:)
				(pirate1 setLoop: 4 setCycle: EndLoop self)
			)
			(2
				(blast init: setCycle: EndLoop self)
				(pirate1 setLoop: 3 setCel: 5 setCycle: BegLoop)
			)
			(3
				(pirate1 stopUpd:)
				(blast dispose:)
				(ego view: 92 setLoop: 2 setCycle: Forward)
				(= seconds 5)
			)
			(4
				(Print 69 28)
				(EgoDead 0 0 7 15)
			)
		)
	)
)

(instance LeaveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0 canInput: 1)
				(ego setCycle: 0 setMotion: 0)
				(pirate2
					setScript: 0
					setLoop: 0
					setCycle: Walk
					ignoreControl: 1
					setMotion: MoveTo 71 158 self
				)
				(pirate1
					setScript: 0
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 329 (pirate1 y?)
				)
			)
			(1
				(pirate2 setMotion: MoveTo 329 (pirate2 y?) self)
			)
			(2
				(pirate1 dispose:)
				(pirate2 dispose:)
				(Print 69 29)
				(zoom play:)
				(= ortegaWorkersLeft TRUE)
				(= seconds 3)
			)
			(3
				(= shipLeaving 1)
				(ship init: setMotion: MoveTo 290 25 self)
			)
			(4
				(ship setCel: 1 setMotion: MoveTo 241 25 self)
			)
			(5
				(ship setCel: 2 setMotion: MoveTo 228 24 self)
			)
			(6
				(ship setCel: 3 setMotion: MoveTo 230 17 self)
			)
			(7
				(ship setCel: 4 setMotion: MoveTo 238 13 self)
			)
			(8
				(ship setCel: 5 setMotion: MoveTo 245 10 self)
			)
			(9
				(ship setCel: 6 setMotion: MoveTo 255 0 self)
			)
			(10
				(= shipLeaving FALSE)
				(ship dispose:)
				(ego setCycle: Walk)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance p1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1 (client setCycle: EndLoop self))
			(2
				(client stopUpd:)
				(self changeState: 0)
			)
		)
	)
)

(instance p2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1 (client setCycle: EndLoop self))
			(2
				(client stopUpd:)
				(self changeState: 0)
			)
		)
	)
)

(instance pirate1 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 93
			setLoop: 2
			setCel: 0
			setPri: -1
			posn: 215 126
			ignoreActors: 1
			setScript: p1Script
		)
	)
)

(instance pirate2 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 93
			setLoop: 6
			setCel: 0
			setPri: -1
			posn: 75 154
			ignoreActors: 1
			setScript: p2Script
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look/flunky,man,clerk') (Print 69 30))
	)
)

(instance ship of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 92
			setLoop: 3
			setCel: 0
			setStep: 10 5
			setPri: 15
			x: 332
			y: 25
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			ignoreControl: TRUE
		)
	)
)

(instance blast of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 93
			setLoop: 5
			setCel: 0
			setPri: 8
			posn: 153 110
			ignoreActors: TRUE
		)
	)
)

(instance pole of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 92
			posn: 248 134
			setLoop: 1
			setCel: 0
			setPri: 10
			ignoreActors: TRUE
		)
	)
)

(instance anemo of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 92
			setLoop: 0
			setCel: 0
			setPri: 11
			posn: 247 99
			ignoreActors: TRUE
			setCycle: Forward
			cycleSpeed: 0
		)
	)
)

(instance jello of Sound
	(properties
		number 44
		priority 1
	)
)

(instance zoom of Sound
	(properties
		number 38
		priority 1
	)
)
