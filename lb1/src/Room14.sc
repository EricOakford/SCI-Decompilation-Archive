;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room14 0
)

(local
	[local0 2]
	local2
	[local3 11]
	local14
	local15
)
(instance Room14 of Room
	(properties
		picture 14
	)
	
	(method (init)
		(= west 13)
		(= east 10)
		(super init:)
		(LoadMany VIEW 1 25 56)
		(LoadMany SOUND 41 43 44 47 48 97)
		(if (not global206)
			(Load VIEW 47)
		)
		(Chair
			setPri: 10
			ignoreActors: TRUE
			cel: (if global206 2 else 0)
			init:
			stopUpd:
		)
		(Door
			cel: (if (== prevRoomNum 63) 2 else 0)
			init:
			stopUpd:
		)
		(Rope setPri: 14 init: stopUpd:)
		(if howFast
			(Squirel init: setScript: runAway)
			(Splash1 ignoreActors: TRUE init: hide:)
			(Splash2 ignoreActors: TRUE init: hide:)
		)
		(self
			setFeatures: Window1 Window2 Window3 Window4 Tower Chapel House
		)
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(if (and (== currentAct 6) (not (& global118 $0002)))
			(self setRegions: 281)
		)
		(switch prevRoomNum
			(21 (ego posn: 276 188))
			(20 (ego posn: 48 188))
			(3 (ego posn: 276 100))
			(9 (ego posn: 76 113))
			(63
				(ego posn: 227 132 loop: 2)
				(self setScript: myDoor)
			)
			(10 (ego posn: 310 138))
		)
		(ego view: 0 illegalBits: cWHITE init:)
	)
	
	(method (doit)
		(if
			(FirstEntry)
			(Print 14 0)
		)
		(if (& (ego onControl: FALSE) cRED)
			(curRoom newRoom: 3)
		)
		(if (& (ego onControl: FALSE) cCYAN)
			(curRoom newRoom: 9)
		)
		(if
			(and
				(& (ego onControl: origin) cYELLOW)
				(!= (ego mover?) 0)
				howFast
			)
			(switch (ego loop?)
				(2
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
			)
		)
		(if (== (ego edgeHit?) SOUTH)
			(if (< (ego x?) 192)
				(curRoom newRoom: 20)
			else
				(curRoom newRoom: 21)
			)
		)
		(if local15
			(switch (Rope cel?)
				(2
					(Chair posn: 56 126 forceUpd:)
				)
				(1
					(Chair posn: 56 128 forceUpd:)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 14 0)
							)
							((Said '/path,(boulder<stepping)')
								(Print 14 1)
							)
						)
					)
					((Said 'get,get,chop,untie,examine/hemp')
						(Print 14 2)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 63)
			(cSound stop:)
			(ego illegalBits: cWHITE setPri: -1 setLoop: -1)
		)
		(super newRoom: n)
	)
)

(instance myDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local2
					(ego illegalBits: 0 setMotion: MoveTo 236 130 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if local2
					(ego setPri: 8 loop: 3)
					(Door cycleSpeed: 3 setCycle: EndLoop self)
					(soundFX number: 43 loop: 1 play:)
				else
					(Door cycleSpeed: 3 setCycle: BegLoop self)
					(soundFX number: 44 loop: 1 play:)
				)
			)
			(2
				(Door stopUpd:)
				(if local2
					(ego
						view: 25
						cel: 0
						setLoop: 0
						setMotion: MoveTo 210 130
						setCycle: EndLoop self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(if local2
					(if
						(and
							(== currentAct 6)
							(== prevRoomNum 63)
							(& global118 $0002)
						)
						(&= global118 $00fb)
					)
					(curRoom newRoom: 63)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance comeIn of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 56 loop: 0 setCycle: EndLoop self)
			)
			(1
				(Print 14 3 #at 40 140 #draw #dispose)
				(soundFX number: 48 loop: 1 play:)
				(ego loop: 2 setCycle: Forward)
				(= cycles 14)
			)
			(2
				(cls)
				(ego view: 56 loop: 0 cel: 3 setCycle: BegLoop self)
			)
			(3
				(if (== currentAct 6)
					(Print 14 4)
				else
					(Print 14 5)
				)
				(= cycles 1)
			)
			(4
				(= local2 1)
				(ego view: 0 setCycle: Walk setScript: myDoor)
				(client setScript: 0)
			)
		)
	)
)

(instance swinging of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global206 1)
				(ego setMotion: MoveTo 52 144 self)
			)
			(1
				(Print 14 6 #at 90 10 #dispose)
				(ego
					view: 47
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 0 setMotion: MoveTo 52 147)
				(Chair setCel: 0 setMotion: MoveTo 56 128)
				(Rope setCycle: EndLoop self)
			)
			(3
				(soundFX number: 41 loop: 1 play:)
				(ego loop: 1 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
				(Chair setCycle: EndLoop)
			)
			(4
				(cls)
				(soundFX number: 47 loop: 1 play:)
				(= local15 1)
				(Rope setCycle: Forward)
				(ShakeScreen 5 5)
				(= cycles 14)
			)
			(5
				(= local15 0)
				(Rope setCycle: BegLoop)
				(ego loop: 2 cel: 0 setCycle: Forward)
				(soundFX number: 97 loop: 1 play:)
				(= seconds 5)
			)
			(6
				(cls)
				(Chair stopUpd:)
				(Rope stopUpd:)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(7
				(ego view: 0 loop: 2 setLoop: -1 setCycle: Walk)
				(= cycles 2)
			)
			(8
				(Print 14 7)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance runAway of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 8))
			)
			(1
				(Squirel setCycle: EndLoop self)
			)
			(2
				(Squirel posn: (+ (Squirel x?) 23) (Squirel y?))
				(if (< (++ local14) 3) (= state 0))
				(= cycles 1)
			)
			(3
				(Squirel loop: 2 cycleSpeed: 2 setCycle: Forward)
				(= cycles 7)
			)
			(4
				(Squirel loop: 0 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(5
				(Squirel posn: (+ (Squirel x?) 23) (Squirel y?))
				(if (< (++ local14) 5) (= state 3))
				(= cycles 1)
			)
			(6
				(Squirel dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance noOneHome of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 56 loop: 0 setCycle: EndLoop self)
			)
			(1
				(ego loop: 2 setCycle: Forward)
				(= cycles 12)
				(soundFX number: 48 loop: 1 play:)
			)
			(2
				(cls)
				(Print 14 8)
				(ego view: 56 loop: 0 cel: 3 setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(ego view: 0 setCycle: Walk loop: 3)
				(client setScript: 0)
			)
		)
	)
)

(instance Squirel of Prop
	(properties
		y 111
		x 12
		view 206
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,capture/squirrel')
				(Print 14 9)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/squirrel'))
				(event claimed: TRUE)
				(Print 14 10)
			)
		)
	)
)

(instance Rope of Prop
	(properties
		y 62
		x 100
		view 114
		loop 2
	)
)

(instance Chair of Actor
	(properties
		y 126
		x 56
		view 114
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'press/swing')
				(Print 14 11)
			)
			(
				(or
					(Said 'swing')
					(Said 'go,climb,(get<(in,on))/swing')
					(Said 'sit[/swing]')
				)
				(cond 
					(global206
						(Print 14 12)
					)
					((ego inRect: 27 140 77 167)
						(ego setScript: swinging)
					)
					(else
						(NotClose)
					)
				)
			)
			((Said 'get/swing')
				(Print 14 13)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/swing'))
				(event claimed: TRUE)
				(if global206
					(Print 14 14)
				else
					(Print 14 15)
				)
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 125
		x 232
		view 114
		loop 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'break/door')
				(Print 14 16)
			)
			((Said 'unbar/door')
				(if (>= currentAct 7)
					(Print 14 17)
				else
					(Print 14 18)
				)
			)
			((Said 'bang[/door]')
				(if (& (ego onControl: origin) cBLUE)
					(if
						(or
							(== currentAct 2)
							(and (== currentAct 6) (not (& global118 $0002)))
						)
						(HandsOff)
						(ego setScript: comeIn)
					else
						(HandsOff)
						(ego setScript: noOneHome)
					)
				else
					(NotClose)
				)
			)
			((Said 'open/door')
				(if (& (ego onControl: origin) cBLUE)
					(if (>= currentAct 2)
						(= local2 1)
						(ego setScript: myDoor)
					else
						(Print 14 19)
					)
				else
					(NotClose)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/door'))
				(event claimed: TRUE)
				(Print 14 20)
			)
		)
	)
)

(instance Splash1 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Splash2 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 83
		nsLeft 133
		nsBottom 95
		nsRight 144
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<(in,through)/window,playhouse')
				(if (& (ego onControl: FALSE) cBROWN)
					(Print 14 21)
				else
					(NotClose)
				)
			)
			((Said 'break/window')
				(Print 14 22)
			)
			((Said 'open/window')
				(Print 14 23)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/window'))
				(event claimed: TRUE)
				(Print 14 24)
			)
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 93
		nsLeft 164
		nsBottom 108
		nsRight 176
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 14 24)
		)
	)
)

(instance Window3 of RFeature
	(properties
		nsTop 95
		nsLeft 195
		nsBottom 106
		nsRight 207
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 14 24)
		)
	)
)

(instance Window4 of RFeature
	(properties
		nsTop 87
		nsLeft 240
		nsBottom 99
		nsRight 251
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 14 24)
		)
	)
)

(instance Tower of RFeature
	(properties
		nsTop 21
		nsLeft 242
		nsBottom 45
		nsRight 252
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/tower'))
			(event claimed: TRUE)
			(Print 14 25)
		)
	)
)

(instance Chapel of RFeature
	(properties
		nsTop 29
		nsLeft 106
		nsBottom 46
		nsRight 130
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/chapel'))
			(event claimed: TRUE)
			(Print 14 26)
		)
	)
)

(instance House of RFeature
	(properties
		nsTop 47
		nsLeft 131
		nsBottom 121
		nsRight 255
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine/playhouse,cabin')
			)
			(event claimed: TRUE)
			(Print 14 27)
		)
	)
)

(instance soundFX of Sound
	(properties
		number 48
		priority 3
	)
)
