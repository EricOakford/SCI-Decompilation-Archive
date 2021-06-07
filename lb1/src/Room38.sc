;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room38 0
)
(synonyms
	(parrot bird)
	(room parlor)
)

(local
	local0
	[local1 2]
	local3
	local4
	local5
	firstTime
)
(instance Room38 of Room
	(properties
		picture 38
	)
	
	(method (init)
		(super init:)
		(= west 37)
		(= local0 0)
		(= firstTime (FirstEntry))
		(if (>= currentAct 5)
			(addToPics add: glass)
			(self setFeatures: glass)
			(Load VIEW 638)
		)
		(if (and (!= currentAct 0) (!= currentAct 4))
			(addToPics add: stool1)
		)
		(if (< currentAct 2) (decantar init: stopUpd:))
		(addToPics
			add:
				bar
				statue
				sofa1
				sofa2
				table1
				table2
				table3
				portrait
				horse
				chair1
				chair2
				chair3
				stand
				stool2
				couch
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures:
				sofa1
				sofa2
				table1
				table2
				table3
				portrait
				chair1
				chair2
				chair3
				couch
				horse
				statue
				bar
		)
		(chair4 init: stopUpd:)
		(if howFast
			(lamp1 setCycle: Forward init:)
			(lamp2 setPri: 4 setCycle: Forward init:)
			(parrot cycleSpeed: 1 setPri: 6 setCycle: Forward init:)
		else
			(parrot cycleSpeed: 1 setPri: 6 init: stopUpd:)
			(lamp1 init: stopUpd:)
			(lamp2 setPri: 4 init: stopUpd:)
		)
		(Fdoor
			cel: (if (== prevRoomNum 17) 2 else 0)
			ignoreActors: 1
			init:
			stopUpd:
		)
		(Bdoor
			cel: (if (== prevRoomNum 17) 2 else 0)
			ignoreActors: 1
			init:
			stopUpd:
		)
		(Door
			setLoop: 4
			illegalBits: 0
			x: (if (== prevRoomNum 50) 175 else 180)
			setPri: 4
			init:
			stopUpd:
		)
		(switch currentAct
			(0
				(= local3 1)
				(if (not (& global173 $0002))
					(if (== [global368 1] 1)
						(|= global173 $0002)
						(= [global368 1] 0)
						(self setRegions: 378)
					else
						(self setRegions: 217)
					)
				else
					(self setRegions: 378)
				)
			)
			(1 (self setRegions: 238))
			(3
				(if (and (< gameMinutes 3) (< global192 2))
					(self setRegions: 267)
				)
			)
			(4 (self setRegions: 272))
		)
		(if (!= prevRoomNum 50)
			(if (== prevRoomNum 37)
				(ego posn: 15 98)
			else
				(ego posn: 261 120)
				(if (not firstTime)
					(Fdoor cycleSpeed: 1 setCycle: BegLoop)
					(Bdoor cycleSpeed: 1 setCycle: BegLoop)
					(ego setMotion: MoveTo 247 120)
					(soundFX number: 44 play:)
				)
			)
			(ego view: 0 illegalBits: -32732 setPri: -1 init:)
		else
			(ego
				view: 0
				illegalBits: cWHITE
				setPri: 2
				posn: 188 80
				loop: 2
				init:
			)
			(if (== local3 0)
				(ego posn: 179 80)
				(self setScript: enterPanel)
			)
		)
	)
	
	(method (doit)
		(if firstTime
			(Print 38 0)
			(if (== prevRoomNum 17)
				(Fdoor cycleSpeed: 1 setCycle: BegLoop)
				(Bdoor cycleSpeed: 1 setCycle: BegLoop)
				(ego setMotion: MoveTo 250 120)
				(soundFX number: 44 play:)
			)
			(= firstTime 0)
		)
		(if
			(and
				(== prevRoomNum 17)
				(not local5)
				(== (Fdoor cel?) 0)
				(== (Bdoor cel?) 0)
			)
			(= local5 1)
			(Fdoor stopUpd:)
			(Bdoor stopUpd:)
		)
		(if (ego inRect: 265 122 290 130)
			(ego setPri: 10)
			(chair4 startUpd:)
		else
			(chair4 stopUpd:)
			(cond 
				((& (ego onControl: 0) cCYAN)
					(if (and (not script) (== (ego loop?) 0))
						(HandsOff)
						(self setScript: myDoor)
					)
					(ego setPri: 8)
				)
				((or (!= script exiting) (!= script enterPanel))
					(ego setPri: -1)
				)
			)
		)
		(if (& (ego onControl: origin) cGREEN)
			(curRoom newRoom: 17)
		)
		(if
			(and
				(& (ego onControl: origin) cMAGENTA)
				(== local3 0)
				(== global204 0)
			)
			(curRoom newRoom: 50)
		)
		(if (< (ego x?) 140)
			(= vertAngle 163)
		else
			(= vertAngle 137)
		)
		(super doit:)
	)
	
	(method (dispose)
		(cls)
		(DisposeScript AVOIDER)
		(DisposeScript 204)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(if (== (event type?) saidEvent)
			(DisposeScript SAVE)
			(if
				(and
					global208
					(Said
						'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
					)
				)
				(self setScript: (ScriptID 243 0))
				((self script?) handleEvent: event)
				(if (event claimed?) (return TRUE))
			)
			(cond 
				((Said '/panel,(door<hidden)>')
					(cond 
						((and (& global175 $0008) (Said 'open,move'))
							(if (not local3)
								(if (& (ego onControl: 0) cRED)
									(HandsOff)
									(self setScript: exiting)
								else
									(NotClose)
								)
							else
								(Print 38 1)
							)
						)
						((Said 'examine')
							(if (& global175 $0008)
								(Print 38 2)
							else
								(Print 38 3)
							)
						)
					)
				)
				((Said 'examine[<around,at][/room]')
					(Print 38 0)
				)
				((Said 'sit/bar,barstool')
					(Print 38 4)
				)
				((Said 'press,(examine<below)/monument')
					(Print 38 5)
				)
			)
		)
		(return (super handleEvent: event))
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance exiting of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global204 1)
				(Door setMotion: MoveTo 140 84 self)
				(soundFX number: 74 play:)
			)
			(1
				(if (ego inRect: 179 87 181 89)
					(= cycles 1)
				else
					(ego illegalBits: cWHITE setMotion: MoveTo 180 88 self)
				)
			)
			(2
				(ego illegalBits: cWHITE setMotion: MoveTo 180 82 self)
			)
			(3
				(ego setPri: 2)
				(Door setMotion: MoveTo 180 84 self)
				(soundFX number: 75 play:)
			)
			(4
				(HandsOn)
				(= global204 0)
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
				(= global204 1)
				(Door setMotion: MoveTo 140 84 self)
				(soundFX number: 74 play:)
			)
			(1
				(ego setMotion: MoveTo 179 90 self)
			)
			(2
				(ego setPri: -1 illegalBits: -32732)
				(Door setMotion: MoveTo 180 84 self)
				(soundFX number: 75 play:)
			)
			(3
				(HandsOn)
				(Print 38 6)
				(= global204 0)
				(client setScript: 0)
			)
		)
	)
)

(instance feedParrot of Script
	
	(method (doit)
		(super doit:)
		(cond 
			((not local4)
				(if (and (== state 2) (== (parrot cel?) 2))
					(ego loop: 4 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
				)
			)
			((and (== state 4) (== (parrot cel?) 3))
				(ego loop: 2 cel: (ego lastCel:) setCycle: BegLoop)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or (!= (ego x?) 198) (!= (ego y?) 89))
					(ego
						setAvoider: (Avoider new:)
						setMotion: MoveTo 198 89 self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 57
					loop: (- 2 (<< local4 $0001))
					cel: 0
					illegalBits: 0
					setAvoider: 0
					setCycle: EndLoop self
				)
			)
			(2
				(soundFX number: 111 play:)
				(parrot
					loop: (- 3 (<< local4 $0001))
					cel: 0
					setCycle: EndLoop
				)
				(if local4
					(Print 38 7 #at 80 160 #dispose)
					(-- numCrackers)
					(= state 4)
					(= cycles 15)
				)
			)
			(3
				(Print 38 8 #at 80 160 #dispose)
				(= cycles 14)
			)
			(4
				(= local4 0)
				(cls)
				(parrot loop: 0 setCycle: Forward)
				(ego
					view: 11
					loop: 0
					illegalBits: -32732
					cycleSpeed: 0
					setCycle: Walk
				)
				(HandsOn)
				(client setScript: 0)
			)
			(5
				(cls)
				(parrot loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6
				(if global116
					(Print 38 9)
				else
					(switch currentAct
						(1 (Print 38 10))
						(2 (Print 38 11))
						(3 (Print 38 12))
						(4 (Print 38 13))
						(5 (Print 38 14))
						(6 (Print 38 15))
						(7 (Print 38 16))
					)
					(= global116 1)
				)
				(= state 3)
				(= cycles 1)
			)
		)
	)
)

(instance myDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego illegalBits: 0)
				(if (> (ego x?) 250)
					(ego setMotion: MoveTo 250 (ego y?) self)
				else
					(= cycles 1)
				)
			)
			(2
				(Bdoor cycleSpeed: 1 ignoreActors: 1 setCycle: EndLoop)
				(Fdoor cycleSpeed: 1 ignoreActors: 1 setCycle: EndLoop self)
				(soundFX number: 43 play:)
			)
			(3
				(ego setMotion: MoveTo (+ (ego x?) 50) 118)
			)
		)
	)
)

(instance bar of RPicView
	(properties
		y 98
		x 147
		view 138
		loop 1
		priority 6
		signal ignrAct
	)
	
	(method (handleEvent event &tmp [str 75])
		(cond 
			((Said 'examine<below,behind,in/bar')
				(Print 38 17)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/bar'))
				(event claimed: TRUE)
				(cond 
					((>= currentAct 5)
						(Print (Format @str 38 18 38 19 38 20))
					)
					((< currentAct 2)
						(Print (Format @str 38 18 38 19 38 21))
					)
					(else
						(Print 38 19)
					)
				)
			)
		)
	)
)

(instance table3 of RPicView
	(properties
		y 167
		x 185
		view 138
		loop 1
		cel 1
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance statue of RPicView
	(properties
		y 90
		x 19
		view 138
		loop 1
		cel 2
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,move,rotate/monument')
				(Print 38 22)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/monument'))
				(event claimed: TRUE)
				(if (< (ego distanceTo: statue) 100)
					(Print 38 23)
				else
					(ParseName {horse})
				)
			)
		)
	)
)

(instance sofa1 of RPicView
	(properties
		y 166
		x 112
		view 138
		loop 3
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {sofa})
		)
	)
)

(instance sofa2 of RPicView
	(properties
		y 167
		x 257
		view 138
		loop 3
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {sofa})
		)
	)
)

(instance portrait of RPicView
	(properties
		y 54
		x 137
		view 138
		loop 2
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<behind,below/painting')
				(Print 38 24)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/painting')
					(Said 'examine/gertie,girl/painting')
				)
				(event claimed: TRUE)
				(Print 38 25)
			)
			((Said '/painting>')
				(cond 
					((Said 'get')
						(Print 38 26)
					)
					((Said 'open')
						(Print 38 27)
					)
				)
			)
			(
				(or
					(and (Said 'examine/eye>') (Said 'examine/gertie,girl'))
					(Said 'examine/eye[<gertie,girl,painting]')
					(Said 'examine/eye/gertie,girl')
				)
				(Print 38 28)
			)
		)
	)
)

(instance horse of RPicView
	(properties
		y 147
		x 186
		view 138
		loop 1
		cel 3
		priority 13
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/blaze')
				(Print 38 29)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/blaze'))
				(event claimed: TRUE)
				(Print 38 30)
			)
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 138
		x 87
		view 138
		loop 2
		cel 1
		priority 10
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance table1 of RPicView
	(properties
		y 137
		x 112
		view 138
		loop 2
		cel 3
		priority 10
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 138
		x 211
		view 138
		loop 2
		cel 2
		priority 10
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance chair3 of RPicView
	(properties
		y 137
		x 137
		view 138
		loop 2
		cel 1
		priority 10
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
		y 138
		x 234
		view 138
		loop 2
		cel 3
		priority 10
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance chair4 of Prop
	(properties
		y 138
		x 265
		view 138
		loop 2
		cel 1
		priority 10
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance stand of RPicView
	(properties
		y 93
		x 222
		view 138
		loop 1
		cel 7
		priority 5
	)
)

(instance couch of RPicView
	(properties
		y 89
		x 67
		view 138
		loop 3
		cel 2
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance decantar of Prop
	(properties
		y 98
		x 116
		z 32
		view 138
		loop 1
		cel 5
		priority 6
	)
	
	(method (handleEvent event)
		(if (not (event claimed?))
			(cond 
				(
					(or
						(Said 'drink/[<alcohol]')
						(Said 'drink,pour,get/decanter,alcohol,drink')
						(Said 'open/decanter,alcohol')
					)
					(Print 38 31)
				)
				((Said 'examine<in/decanter')
					(Print 38 32)
				)
				((Said 'examine/alcohol')
					(Print 38 33)
				)
				((or (MousedOn self event 3) (Said 'examine/decanter'))
					(event claimed: TRUE)
					(Print 38 21)
				)
			)
			(if (event claimed?)
				(Bset 29)
			)
		)
	)
)

(instance glass of RPicView
	(properties
		y 102
		x 167
		z 32
		view 138
		loop 1
		cel 4
		priority 6
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<in/glass')
				(Print 38 34)
			)
			((Said 'get/glass')
				(Print 38 35)
			)
			(
				(or
					(Said 'examine<use<monocle/glass')
					(Said 'examine,examine[<at]/glass/monocle<with')
				)
				(if (ego has: iMonocle)
					(if (ego inRect: 155 91 185 108)
						(Print 38 36 #icon 638 0 0)
						(Bset fExaminedGlass)
					else
						(NotClose)
					)
				else
					(DontHave)
				)
			)
			((Said 'examine/fingerprint/glass')
				(if (ego inRect: 155 91 185 105)
					(Print 38 37)
				else
					(NotClose)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(and
						(not (Said 'examine/glass/monocle>'))
						(Said 'examine/glass')
					)
				)
				(event claimed: TRUE)
				(if (ego inRect: 155 91 185 105)
					(Print 38 38)
				else
					(Print 38 39)
				)
			)
		)
	)
)

(instance stool1 of RPicView
	(properties
		y 102
		x 168
		view 138
		loop 3
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance stool2 of RPicView
	(properties
		y 102
		x 138
		view 138
		loop 3
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance Fdoor of Prop
	(properties
		y 116
		x 267
		view 201
		loop 1
		priority 8
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/door'))
			(event claimed: TRUE)
			(Print 38 40)
		)
	)
)

(instance Bdoor of Prop
	(properties
		y 127
		x 277
		view 201
		loop 3
		priority 9
	)
)

(instance parrot of Prop
	(properties
		y 39
		x 221
		view 238
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'hold,feed,deliver>')
				(cond 
					(
						(or
							(Said '/parrot[/!*]')
							(and (Said '/food')
								(ego inRect: 197 88 240 98)
							)
						)
						(Print 38 41)
					)
					(
						(or
							(Said '/parrot/*>')
							(Said '/*/parrot>')
							(Said '/*<parrot>')
							(and (Said '/*/!*>')
								(ego inRect: 197 88 240 98)
							)
						)
						(cond 
							((or (Said '/cracker') (Said '//cracker'))
								(if (ego has: iCrackers)
									(if (> numCrackers 0)
										(if (ego inRect: 197 88 240 98)
											(= local4 1)
											(parrot setScript: feedParrot)
										else
											(NotClose)
										)
									else
										(Print 38 42)
									)
								else
									(DontHave)
								)
							)
							((and theInvItem haveInvItem)
								(Print 38 43)
							)
							(else
								(DontHave)
							)
						)
						(event claimed: TRUE)
					)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/parrot,birdstand,stand')
				)
				(Print 38 44)
				(event claimed: TRUE)
			)
			((Said '/parrot>')
				(cond 
					((Said 'capture,get,pat')
						(if (ego inRect: 197 88 240 98)
							(= local4 0)
							(parrot setScript: feedParrot)
						else
							(NotClose)
						)
					)
					((Said 'converse')
						(switch (Random 0 3)
							(0 (Print 38 45))
							(1 (Print 38 46))
							(2 (Print 38 47))
							(3 (Print 38 48))
						)
					)
					((Said 'kiss') (Print 38 49))
					((Said 'kill') (Print 38 50))
				)
			)
		)
	)
)

(instance closeUp of Prop
	(properties
		y 58
		x 155
		view 638
	)
)

(instance lamp1 of Prop
	(properties
		y 41
		x 72
		view 138
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
		y 41
		x 202
		view 138
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance Door of Actor
	(properties
		y 84
		view 138
	)
)

(instance soundFX of Sound
	(properties
		priority 5
	)
)
