;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room36 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	firstTime
	userCanControl
	local8
)
(instance Room36 of Room
	(properties
		picture 36
	)
	
	(method (init)
		(super init:)
		(= east 37)
		(= firstTime (FirstEntry))
		(Load VIEW 16)
		(LoadMany SOUND 43 44 74 75)
		(= global120 0)
		(if
			(or
				(and (== currentAct 0) (== global199 2))
				(and (!= global154 4) (== currentAct 1))
				(== currentAct 2)
			)
			(LoadMany SOUND 200 201 202 203 204)
		)
		(self
			setFeatures:
				phono
				harp
				tromb
				trump
				item
				Piano
				bookcase
				mandolin
				cues
				ship
				billiard
				wingback
				sofa1
				sofa2
				table1
				table2
				portrait
		)
		(if howFast
			(lamp1 setCycle: Forward init:)
			(lamp2 setCycle: Forward init:)
		else
			(lamp1 init: stopUpd:)
			(lamp2 init: stopUpd:)
		)
		(Fdoor
			cel: (if (== prevRoomNum 15) 2 else 0)
			init:
			stopUpd:
		)
		(= gDoor Fdoor)
		(Bdoor
			cel: (if (== prevRoomNum 15) 2 else 0)
			init:
			stopUpd:
		)
		(= gMySound Bdoor)
		(panel
			x: (if (== prevRoomNum 49) 146 else 141)
			init:
			stopUpd:
		)
		(crank_ init: stopUpd:)
		(keys init: hide:)
		(roll init: stopUpd:)
		(switch currentAct
			(0
				(if (and (not (& global173 $0001)) (== [global368 0] 1))
					(= [global368 0] 0)
					(= [global368 2] 1800)
					(|= global173 $0001)
					(= global199 1)
				)
				(if (and (== global199 1) (== [global368 2] 1))
					(= [global368 2] 0)
					(= global199 2)
				)
				(switch global199
					(0
						(= local3 1)
						(self setRegions: 232)
					)
					(2
						(= local3 1)
						(= local0 1)
						(self setRegions: 233)
					)
				)
			)
			(1
				(= local3 1)
				(if (or (== global154 4) (== [global368 0] 1))
					(= global154 4)
					(= [global368 0] 0)
					(self setRegions: 245)
					(balls priority: 7)
				else
					(= local0 1)
					(= global199 2)
					(self setRegions: 233)
				)
			)
			(2
				(= local3 1)
				(= local0 1)
				(self setRegions: 233)
			)
			(3
				(if (not (& global141 $0004))
					(self setRegions: 382)
				)
			)
			(else  (= local3 0))
		)
		(addToPics
			add:
				balls
				phono
				sofa1
				sofa2
				table1
				table2
				portrait
				wingback
				harp
				tromb
				trump
				mandolin
				cues
				bookcase
				ship
				rim
				billiard
				item
			eachElementDo: #init
			doit:
		)
		(if (!= prevRoomNum 49)
			(if (== prevRoomNum 37)
				(ego posn: 305 98)
			else
				(ego posn: 60 119)
				(if (not firstTime)
					(ego setMotion: MoveTo 68 119)
					(= global202 2)
				)
			)
			(ego view: 0 illegalBits: (| cWHITE cGREEN) setPri: -1 init:)
		else
			(ego
				view: 0
				illegalBits: (| cWHITE cGREEN)
				setPri: 2
				loop: 2
				posn: 126 79
				init:
			)
			(ego posn: 138 79)
			(self setScript: enterPanel)
		)
	)
	
	(method (doit)
		(if (and (== currentAct 3) (== global217 0))
			(Print 36 0)
			(= global217 1)
		)
		(if (and (== currentAct 3) (== gMySound 1))
			(= local3 1)
		)
		(if (and (== currentAct 3) (== gMySound 0))
			(= local3 0)
		)
		(if firstTime
			(Print 36 1)
			(if (== prevRoomNum 15)
				(ego setMotion: MoveTo 68 119)
				(= global202 2)
			)
			(= firstTime 0)
		)
		(if
			(and
				(or (== global202 1) (== global202 2))
				(not (Fdoor script?))
			)
			(Fdoor setScript: DoorFunc)
		)
		(if (not script)
			(cond 
				((& (ego onControl: 0) cCYAN)
					(if (== (ego loop?) 1)
						(ego setPri: 8)
						(HandsOff)
						(self setScript: myDoor)
					)
				)
				((ego inRect: 46 120 55 131)
					(ego setPri: 10)
				)
				((not local5)
					(ego setPri: -1)
				)
			)
		)
		(if (& (ego onControl: origin) cGREEN)
			(ego illegalBits: cWHITE ignoreActors: FALSE)
			(curRoom newRoom: 15)
		)
		(if
			(and
				(& (ego onControl: origin) cMAGENTA)
				(== local3 0)
				(== global204 0)
			)
			(curRoom newRoom: 49)
		)
		(if (< (ego x?) 190)
			(= vertAngle 44)
		else
			(= vertAngle 10)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (< global160 4)
			(++ global160)
		else
			(= global160 0)
		)
		(DisposeScript 204)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(DisposeScript SAVE)
			(if
				(or
					(Said 'play/game,billiard')
					(Said 'ask/*/(game,billiard)<play<to')
				)
				(Print 36 2)
			)
			(= local8
				(if
					(or
						(!= global208 260)
						(and
							(not (Said 'ask[/rudolph]/actress<about>'))
							(not (Said 'ask[/actress]/rudolph<about>'))
						)
					)
					(if
						(or
							(not (& global208 $0044))
							(not (Said 'tell[/actress,attorney]/gertie<about>'))
							(not (& deadGuests deadGERTRUDE))
						)
					else
						(& global145 $0002)
					)
				else
					0
				)
			)
			(if
				(and
					global208
					local8
					(Said
						'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
					)
				)
				(self setScript: (ScriptID 243 0))
				((self script?) handleEvent: event)
				(if (event claimed?) (return))
			)
			(cond 
				((Said '/panel,(door<hidden)>')
					(cond 
						((and (& global175 $0004) (Said 'open,move'))
							(if (not local3)
								(if (& (ego onControl: 0) cRED)
									(HandsOff)
									(self setScript: exiting)
								else
									(NotClose)
								)
							else
								(Print 36 3)
							)
						)
						((Said 'examine')
							(if (& global175 $0004)
								(Print 36 4)
							else
								(Print 36 5)
							)
						)
					)
				)
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]')
							(if (== currentAct 3)
								(if (not (& global141 $0004))
									(if (ego has: iBrokenRecord)
										(Print 36 6)
									else
										(Print 36 0)
									)
								else
									(Print 36 1)
								)
							else
								(Print 36 1)
							)
						)
						((Said '/ball[<billiard]')
							(Print 36 7)
						)
						((Said '/bench[<piano]')
							(Print 36 8)
						)
						((or (Said '/dirt') (Said '<down'))
							(if (== currentAct 3)
								(cond 
									((ego has: iBrokenRecord)
										(if (not (& global141 $0004))
											(Print 36 6)
										else
											(event claimed: FALSE)
										)
									)
									((not (& global141 $0004))
										(Print 36 9)
									)
									(else
										(event claimed: FALSE)
									)
								)
							else
								(event claimed: FALSE)
							)
						)
						((Said '/door')
							(Print 36 10)
						)
						((Said '/record')
							(cond 
								((ego has: iBrokenRecord)
									(event claimed: FALSE)
								)
								((< (ego distanceTo: phono) 20)
									(Print 36 11)
								)
								(else
									(NotClose)
								)
							)
						)
					)
				)
				((Said 'get/ball')
					(Print 36 12)
				)
			)
		)
	)
	
	(method (newRoom n)
		(cls)
		(= saveDisabled FALSE)
		(super newRoom: n)
	)
)

(instance exiting of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local5 1)
				(= global204 1)
				(panel setMotion: MoveTo 170 83 self)
				(ego illegalBits: cWHITE)
				(soundFX number: 74 loop: 1 play:)
			)
			(1
				(if (ego inRect: 137 87 139 89)
					(= cycles 1)
				else
					(ego illegalBits: cWHITE setMotion: MoveTo 138 88 self)
				)
			)
			(2
				(ego setMotion: MoveTo 138 79 self)
			)
			(3
				(ego setPri: 2)
				(panel setMotion: MoveTo 141 83 self)
				(soundFX number: 75 loop: 1 play:)
			)
			(4
				(HandsOn)
				(= global204 0)
				(client setScript: 0)
			)
		)
	)
)

(instance playPiano of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 1)
				(HandsOff)
				(ego ignoreControl: cWHITE)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 137 89 self)
			)
			(2
				(ego view: 16 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(crank_ hide:)
				(if (== global120 0)
					(= global120 1)
				)
				(windMusic loop: -1 play:)
				(ego loop: 0 cel: 0 setCycle: Forward)
				(= cycles 14)
			)
			(4
				(windMusic stop:)
				(crank_ show:)
				(ego view: 16 loop: 1 cel: 0 setCycle: BegLoop self)
			)
			(5
				(ego
					view: 0
					loop: 0
					setCycle: Walk
					observeControl: cWHITE
				)
				(HandsOn)
				(if howFast (keys show: setCycle: Forward))
				(roll setCycle: Forward)
				(myMusic number: (+ 200 global160) loop: 1 play: self)
			)
			(6
				(if (< global160 4)
					(++ global160)
				else
					(= global160 0)
				)
				(keys hide:)
				(roll stopUpd:)
				(= local1 0)
				(client setScript: 0)
			)
		)
	)
)

(instance enterPanel of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local5 1)
				(= global204 1)
				(panel setMotion: MoveTo 170 83 self)
				(soundFX number: 74 loop: 1 play:)
			)
			(1
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 15) self)
			)
			(2
				(ego setPri: -1 illegalBits: cWHITE)
				(panel setMotion: MoveTo 141 83 self)
				(soundFX number: 75 loop: 1 play:)
			)
			(3
				(HandsOn)
				(Print 36 13)
				(= global204 0)
				(= local5 0)
				(client setScript: 0)
			)
		)
	)
)

(instance DoorFunc of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= userCanControl (User canControl:))
				(User canControl: FALSE)
				(Bdoor setCycle: (if (== global202 1) EndLoop else BegLoop))
				(Fdoor
					setCycle: (if (== global202 1) EndLoop else BegLoop) self
				)
				(if (not local0)
					(doorMusic
						number: (if (== global202 1) 43 else 44)
						play:
					)
				)
			)
			(1
				(User canControl: userCanControl)
				(Bdoor stopUpd:)
				(Fdoor stopUpd:)
				(= global202 3)
				(client setScript: 0)
			)
		)
	)
)

(instance myDoor of Script

	(method (doit)
		(super doit:)
		(if (== global202 3)
			(= global202 0)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
				(ego setMotion: 0 ignoreActors: TRUE illegalBits: 0)
			)
			(1
				(if (< (ego x?) 68)
					(ego setMotion: MoveTo 80 119 self)
				else
					(= cycles 1)
				)
			)
			(2 (= global202 1))
			(3
				(ego setMotion: MoveTo (- (ego x?) 50) (ego y?))
			)
		)
	)
)

(instance balls of RPicView
	(properties
		y 102
		x 157
		view 136
		loop 9
		cel 5
		priority 9
		signal ignrAct
	)
)

(instance phono of RPicView
	(properties
		y 93
		x 96
		view 136
		loop 1
		cel 6
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/record')
				(Print 36 14)
			)
			(
				(or
					(Said
						'play,attach,control,rotate/handle,gramophone,music,record[/(gramophone,(player<record))<on]'
					)
					(Said
						'(rotate<on),(wind[<up])/gramophone,(player<record)'
					)
				)
				(if (not local0)
					(if (ego has: iBrokenRecord)
						(Print 36 15)
					else
						(Print 36 16)
					)
				else
					(Print 36 17)
				)
			)
			(
				(Said
					'open,(examine<in)/gramophone,armoire,(player<record)'
				)
				(if (< (ego distanceTo: record) 30)
					(Print 36 18)
				else
					(NotClose)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/gramophone,armoire,(player<record)')
				)
				(if (== currentAct 3)
					(Print 36 19)
				else
					(Print 36 20)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(instance sofa1 of RPicView
	(properties
		y 167
		x 54
		view 136
		loop 3
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance sofa2 of RPicView
	(properties
		y 167
		x 203
		view 136
		loop 3
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance table1 of RPicView
	(properties
		y 167
		x 126
		view 136
		loop 2
		cel 4
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance wingback of RPicView
	(properties
		y 94
		x 295
		view 136
		loop 2
		cel 2
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance harp of RPicView
	(properties
		y 48
		x 295
		view 136
		loop 1
		cel 4
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/guitar'))
				(event claimed: TRUE)
				(Print 36 21)
			)
			((Said 'get/guitar')
				(Print 36 22)
			)
			((Said 'play/guitar')
				(Print 36 22)
			)
		)
	)
)

(instance tromb of RPicView
	(properties
		y 45
		x 268
		view 136
		loop 1
		cel 5
		priority 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 36 21)
		)
	)
)

(instance trump of RPicView
	(properties
		y 42
		x 233
		view 136
		loop 1
		cel 3
		priority 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 36 21)
		)
	)
)

(instance mandolin of RPicView
	(properties
		y 56
		x 104
		view 136
		loop 1
		priority 4
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 36 21)
		)
	)
)

(instance cues of RPicView
	(properties
		y 122
		x 30
		view 136
		loop 1
		cel 8
		priority 9
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/rack,cue'))
				(event claimed: TRUE)
				(Print 36 23)
			)
			((Said 'get/cue[<billiard]')
				(Print 36 24)
			)
		)
	)
)

(instance bookcase of RPicView
	(properties
		y 112
		x 65
		view 136
		loop 2
		cel 3
		priority 7
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/bookcase'))
			(event claimed: TRUE)
			(Print 36 25)
		)
	)
)

(instance ship of RPicView
	(properties
		y 148
		x 127
		view 136
		loop 1
		cel 1
		priority 12
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/boat'))
				(event claimed: TRUE)
				(Print 36 26)
			)
			((Said 'get/boat')
				(Print 36 27)
			)
		)
	)
)

(instance item of RPicView
	(properties
		y 91
		x 226
		view 136
		loop 3
		cel 2
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance table2 of RPicView
	(properties
		y 90
		x 250
		view 136
		loop 2
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance rim of PicView
	(properties
		y 111
		x 155
		view 136
		loop 8
		priority 9
		signal ignrAct
	)
)

(instance billiard of RPicView
	(properties
		y 126
		x 155
		view 136
		loop 3
		cel 1
		priority 8
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine/(nightstand<billiard),billiard')
			)
			(event claimed: TRUE)
			(if (and (== currentAct 1) (== global154 4))
				(Print 36 28)
			else
				(Print 36 29)
			)
		)
	)
)

(instance portrait of RPicView
	(properties
		y 50
		x 182
		view 136
		loop 1
		cel 7
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<behind,below/painting')
				(Print 36 30)
			)
			((Said 'get/painting')
				(Print 36 31))
			((Said 'open/painting')
				(Print 36 32)
			)
			(
				(or
					(and
						(Said 'examine/eye>')
						(Said 'examine/actress,girl')
					)
					(Said 'examine/eye[<actress,girl,painting]')
					(Said 'examine/eye/actress,girl')
				)
				(Print 36 33)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/painting')
					(Said 'examine/actress,girl/painting')
				)
				(event claimed: TRUE)
				(Print 36 34)
			)
		)
	)
)

(instance Fdoor of Prop
	(properties
		y 116
		x 53
		view 201
		signal ignrAct
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {door})
		)
	)
)

(instance Bdoor of Prop
	(properties
		y 126
		x 43
		view 201
		loop 2
		priority 9
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {door})
		)
	)
)

(instance keys of Prop
	(properties
		y 75
		x 179
		view 136
		loop 7
		priority 5
		signal fixPriOn
	)
)

(instance roll of Prop
	(properties
		y 70
		x 180
		view 136
		loop 6
		priority 5
		signal fixPriOn
	)
)

(instance record of Prop
	(properties
		y 67
		x 98
		view 136
		loop 5
		priority 6
		signal fixPriOn
	)
)

(instance crank_ of Prop
	(properties
		y 69
		x 146
		view 136
		loop 2
		cel 5
		priority 5
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(MousedOn self event shiftDown)
					(Said
						'examine/control,handle,mechanism[<winding][/piano]'
					)
				)
				(event claimed: TRUE)
				(Print 36 35)
			)
			(
				(Said
					'(wind[<up]),control,rotate/piano,control,handle,mechanism[<winding][/piano]'
				)
				(if (& (ego onControl: origin) cRED)
					(if (== local0 0)
						(if (== local1 0)
							(keys setScript: playPiano)
						else
							(Print 36 36)
						)
					else
						(Print 36 37)
					)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 45
		x 119
		view 136
		cel 1
		priority 4
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance lamp2 of Prop
	(properties
		y 45
		x 249
		view 136
		priority 1
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance Piano of RFeature
	(properties
		nsTop 52
		nsLeft 152
		nsBottom 78
		nsRight 209
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)>')
				(cond 
					((Said '/piano')
						(Print 36 38)
					)
					((Said '/bench')
						(Print 36 39)
					)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/piano'))
				(event claimed: TRUE)
				(Print 36 40)
			)
			((Said 'play/piano')
				(Print 36 41)
			)
		)
	)
)

(instance panel of Actor
	(properties
		y 83
		view 136
		loop 2
		cel 6
		priority 3
		signal (| fixedLoop fixedCel fixPriOn)
		illegalBits $0000
	)
)

(instance myMusic of Sound)

(instance soundFX of Sound)

(instance pianoMusic of Sound)

(instance doorMusic of Sound)

(instance windMusic of Sound
	(properties
		number 39
	)
)
