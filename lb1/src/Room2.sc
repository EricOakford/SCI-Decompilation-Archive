;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
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
	Room2 0
)
(synonyms
	(room cemetery)
)

(local
	local0
	local1
)
(instance Room2 of Room
	(properties
		picture 2
	)
	
	(method (init)
		(= east 9)
		(super init:)
		(= local0 -1)
		(Load SOUND 46)
		(Load SOUND 48)
		(Load VIEW 56)
		(addToPics add: dijon crouton doit:)
		(self setFeatures: Yard)
		(if howFast
			(light1 setScript: showers init: hide:)
			(light2 init: hide:)
			(light3 init: hide:)
		)
		(door1 setPri: 6 ignoreActors: 1 stopUpd: init:)
		(door2 stopUpd:)
		(if
		(and (< (Random 1 100) 25) (== prevRoomNum 9))
			(Load SOUND 12)
			(ghost setLoop: 3 setPri: 10 setScript: Spectre init:)
			(moodMusic number: 46 loop: -1 play:)
		)
		(if (== prevRoomNum 57)
			(HandsOff)
			(= tombDoorState 4)
			(ego posn: 164 102 setMotion: MoveTo 164 108 self)
			(door2 ignoreActors: 1 cel: (door2 lastCel:))
		else
			(ego loop: 1 posn: 310 126)
			(door2 cel: 0 ignoreActors: 0)
		)
		(door2 init:)
		(ego view: 0 illegalBits: -32704 init:)
		(= local1 1)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 2 0)
		)
		(cond 
			((& (ego onControl: origin) cRED)
				(ego setPri: -1)
				(curRoom newRoom: 57)
			)
			(
				(and
					(& (ego onControl: origin) cCYAN)
					(== tombDoorState 1)
					(== (ego loop?) 3)
					(== (door2 cel?) 0)
				)
				(= tombDoorState 2)
				(HandsOff)
				(door2 ignoreActors: TRUE)
				(ego setMotion: MoveTo 164 104 self)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(if
				(or
					(& (ego onControl:) cMAGENTA)
					(& (ego onControl:) cCYAN)
				)
				(cond 
					((Said 'examine<(in,around)/crypt')
						(Print 2 1)
					)
					((Said '/(crypt,door)>')
						(cond 
							((Said 'open')
								(cond 
									((& (ego onControl:) cMAGENTA)
										(= local0 0)
										(HandsOff)
										(ego illegalBits: cWHITE setMotion: MoveTo 43 118 self)
									)
									((& (ego onControl:) cCYAN)
										(Print 2 2)
									)
								)
							)
							((Said 'unbar')
								(if
									(or
										(& (ego onControl:) cMAGENTA)
										(and (& (ego onControl:) cCYAN) (== tombDoorState 1))
									)
									(Print 2 3)
								else
									(Print 2 4)
								)
							)
							((Said 'break,force')
								(if
									(or
										(& (ego onControl:) cMAGENTA)
										(and (& (ego onControl:) cCYAN) (== tombDoorState 1))
									)
									(Print 2 3)
								else
									(Print 2 5)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
			)
			(if (not (event claimed?))
				(cond 
					((Said 'examine,read/crypt')
						(Print 2 6)
					)
					((Said 'bang')
						(if (& (ego onControl: FALSE) cBLACK)
							(NotClose)
						else
							(self setScript: knockDoor)
						)
					)
					((Said 'examine,read/epitaph,gravestone')
						(Print 2 7)
					)
					((Said 'examine,follow/path')
						(Print 2 8)
					)
					((Said 'find,examine/ghost')
						(Print 2 9)
					)
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 2 0)
							)
							((Said '[<down][/dirt]')
								(Print 2 10)
							)
							((Said '/grave,monument')
								(Print 2 11)
							)
							((Said '<(in,around)/crypt')
								(NotClose)
							)
							((Said '/door')
								(Print 2 12)
							)
							((Said '/crypt')
								(NotClose)
							)
							((Said '/fence')
								(Print 2 13)
							)
							((Said '/archway')
								(Print 2 14)
							)
							((Said '/chapel')
								(Print 2 15)
							)
							((Said '/gravestone[/dirt]')
								(Print 2 16)
							)
						)
					)
					((Said 'move,get,press/gravestone,grave')
						(Print 2 17)
					)
					((Said 'get,capture/ghost')
						(Print 2 9)
					)
					((Said 'converse/ghost')
						(Print 2 18)
					)
					((Said 'climb/fence')
						(Print 2 19)
					)
					((Said 'open/crypt,door')
						(NotClose)
					)
					((Said 'open/archway')
						(AlreadyOpen)
					)
					((Said 'close/archway')
						(Print 2 20)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if (!= local0 -1)
			(switch (++ local0)
				(1
					(ego loop: 3)
					(door1 startUpd: setCycle: EndLoop self)
					(soundFX number: 77 loop: 1 play:)
				)
				(2
					(soundFX stop:)
					(Print 2 21)
					(ego setMotion: MoveTo 41 106 self)
				)
				(3
					(ego setMotion: MoveTo 18 104 self)
				)
				(4
					(Print 2 22)
					(ego setMotion: MoveTo 41 106 self)
				)
				(5
					(ego setMotion: MoveTo 43 118 self)
				)
				(6
					(door1 setCycle: BegLoop self)
					(soundFX number: 77 loop: 1 play:)
				)
				(7
					(soundFX stop:)
					(door1 stopUpd:)
					(ego observeControl: 64)
					(HandsOn)
				)
			)
		)
		(if (> tombDoorState 1)
			(switch (++ tombDoorState)
				(3
					(door2 startUpd: setCycle: EndLoop self)
				)
				(4
					(ego setPri: 5 setMotion: MoveTo 164 98)
					(= tombDoorState 1)
				)
				(5
					(door2 startUpd: ignoreActors: 0 setCycle: BegLoop self)
				)
				(6
					(door2 stopUpd:)
					(HandsOn)
					(= tombDoorState 1)
				)
			)
		)
	)
	
	(method (newRoom n)
		((ScriptID 208) keep: 0)
		(super newRoom: n)
	)
)

(instance Spectre of Script
	(properties)
	
	(method (doit &tmp temp0)
		(if (or (== state 1) (== state 2))
			(= temp0 (& (ghost cel?) 1))
			(ghost
				setPri: (+ (<< temp0 3) 6)
				posn: 171 (+ temp0 100)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ghost
					show:
					setCycle: Walk
					setMotion: MoveTo 171 100 self
				)
			)
			(1
				(ghost setCycle: Forward)
				(= seconds 3)
			)
			(2
				(ghost setLoop: 4 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(moodMusic fade:)
				(ghost setLoop: 5 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(4
				(moodMusic number: 12 loop: -1 play:)
				(ghost dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance showers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and local1 (not (ghost script?)))
					(light1 show: setCycle: Forward)
					(light2 show: setCycle: Forward)
					(light3 show: setCycle: Forward)
				else
					(= state 2)
				)
				(= cycles 7)
			)
			(1
				(light1 setCycle: EndLoop)
				(light2 setCycle: EndLoop)
				(light3 setCycle: EndLoop)
				(soundFX number: 17 loop: 1 play: self)
			)
			(2
				(if (< (Random 1 100) 25) (= state -1))
				(= seconds 3)
			)
			(3 (= state 1) (= seconds 5))
		)
	)
)

(instance knockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 2 23 #dispose)
				(ego view: 56 loop: 0 setCycle: EndLoop self)
			)
			(1
				(soundFX number: 48 loop: 1 play:)
				(ego loop: 2 setCycle: Forward)
				(= cycles 6)
			)
			(2
				(ego view: 56 loop: 0 cel: 3 setCycle: BegLoop)
				(= seconds 3)
			)
			(3
				(cls)
				(Print 2 24)
				(= cycles 1)
			)
			(4
				(cls)
				(ego view: 0 setCycle: Walk loop: 3)
				(client setScript: 0)
			)
		)
	)
)

(instance crouton of PicView
	(properties
		y 46
		x 165
		view 102
		loop 2
		priority 14
	)
)

(instance dijon of PicView
	(properties
		y 59
		x 44
		view 102
		loop 2
		cel 1
		priority 14
	)
)

(instance door1 of Prop
	(properties
		y 117
		x 28
		view 102
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 2 25)
		)
	)
)

(instance door2 of Prop
	(properties
		y 101
		x 154
		view 102
		loop 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 2 12)
		)
	)
)

(instance ghost of Actor
	(properties
		y 152
		x 161
		view 102
		signal ignrAct
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if
			(and
				(self script?)
				(or (MousedOn self event shiftDown) (Said 'examine/ghost'))
			)
			(event claimed: TRUE)
			(Print 2 26)
		)
	)
)

(instance light1 of Prop
	(properties
		y 33
		x 257
		view 102
		loop 6
		signal ignrAct
	)
)

(instance light2 of Prop
	(properties
		y 92
		x 281
		view 102
		loop 7
		signal ignrAct
	)
)

(instance light3 of Prop
	(properties
		y 126
		x 262
		view 102
		loop 8
		signal ignrAct
	)
)

(instance moodMusic of Sound
	(properties
		number 17
	)
)

(instance soundFX of Sound
	(properties
		number 17
		priority 5
	)
)

(instance Yard of RFeature
	(properties
		nsTop 107
		nsLeft 87
		nsBottom 179
		nsRight 252
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 2 11)
		)
	)
)
