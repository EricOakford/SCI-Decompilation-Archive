;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
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
	Room48 0
)
(synonyms
	(notebook book)
	(luggage case bag)
	(drawer dresser)
	(room bedroom)
)

(local
	local0
)
(instance mySound of Sound)

(instance Room48 of Room
	(properties
		picture 48
	)
	
	(method (init)
		(= west 47)
		(super init:)
		(LoadMany 132 74 75)
		(if (== currentAct 6)
			(if (not (& global118 $0001))
				(Load FONT 41)
				(LoadMany SOUND 29 94 95 96)
				(Load SCRIPT 406)
				(Load VIEW 642)
			)
			(chair cel: 4)
			(stain setPri: 4 ignoreActors: 1 init:)
		)
		(addToPics
			add: lady bed1 bed2 chest desk table1 table2 suit1 suit2 sofa chair
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures:
				suit1
				suit2
				lady
				bed1
				chest
				bed2
				table1
				chair
				sofa
				table2
				desk
		)
		(if howFast
			(lamp1 setPri: 1 setCycle: Forward init:)
			(lamp2 setPri: 5 setCycle: Forward init:)
		else
			(lamp1 setPri: 1 init: stopUpd:)
			(lamp2 setPri: 5 init: stopUpd:)
		)
		(panel
			setLoop: 2
			setCel: 6
			x: (if (== prevRoomNum 50) 165 else 169)
			setPri: 4
			init:
			stopUpd:
		)
		(switch currentAct
			(0
				(if (> global199 0)
					(= local0 1)
					(self setRegions: 379)
				)
			)
			(1
				(= local0 1)
				(self setRegions: 241)
			)
			(2
				(if (and (& global118 $0004) (< [global368 2] 2))
					(|= global173 $0008)
					(= local0 1)
					(self setRegions: 260)
				else
					(HandsOff)
					(= local0 1)
					(self setRegions: 259)
				)
			)
			(5
				(= local0 1)
				(self setRegions: 276)
			)
			(6
				(notebook setPri: 9 init: stopUpd:)
			)
		)
		(if (!= prevRoomNum 50)
			(ego view: 0 posn: 8 96 illegalBits: (| cWHITE cCYAN) init:)
		else
			(ego
				view: 0
				illegalBits: cWHITE
				setPri: 2
				loop: 2
				posn: 175 79
				init:
			)
			(if (== local0 0)
				(ego posn: 169 79)
				(self setScript: enterPanel)
			)
		)
	)
	
	(method (doit)
		(if (and (not (& global173 $0008)) (== currentAct 6))
			(|= global173 $0008)
			(Print 48 0)
		)
		(if (FirstEntry)
			(Print 48 1)
		)
		(if (ego inRect: 110 84 143 107)
			(ego setPri: 5)
		else
			(ego setPri: -1)
		)
		(if (and (& (ego onControl: 0) cCYAN) (== global204 0))
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
		(DisposeScript 204)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?)
			(return (event claimed?))
		)
		(return
			(if (== (event type?) saidEvent)
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
							((and (& global175 $0080) (Said 'open,move'))
								(if (not local0)
									(if (& (ego onControl: 0) cGREEN)
										(HandsOff)
										(self setScript: exiting)
									else
										(NotClose)
									)
								else
									(Print 48 2)
								)
							)
							((Said 'examine')
								(if (& global175 $0080)
									(Print 48 3)
								else
									(Print 48 4)
								)
							)
						)
					)
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(if (>= currentAct 6)
									(Print 48 0)
								else
									(Print 48 1)
								)
							)
							((or (Said '/carpet,dirt,blood,stain') (Said '<down'))
								(if (== currentAct 6)
									(Print 48 5)
								else
									(event claimed: FALSE)
								)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance enterPanel of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global204 1)
				(panel setMotion: MoveTo 140 84 self)
				(mySound number: 74 loop: 1 play:)
			)
			(1
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 15) self)
			)
			(2
				(ego setPri: -1 illegalBits: (| cWHITE cCYAN))
				(panel setMotion: MoveTo 169 84 self)
				(mySound number: 75 loop: 1 play:)
			)
			(3
				(HandsOn)
				(Print 48 6)
				(= global204 0)
				(client setScript: 0)
			)
		)
	)
)

(instance exiting of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global204 1)
				(panel setMotion: MoveTo 140 84 self)
				(mySound number: 74 loop: 1 play:)
			)
			(1
				(if (ego inRect: 168 87 169 89)
					(= cycles 1)
				else
					(ego illegalBits: cWHITE setMotion: MoveTo 169 88 self)
				)
			)
			(2
				(ego illegalBits: cWHITE setMotion: MoveTo 169 79 self)
			)
			(3
				(ego setPri: 2)
				(panel setMotion: MoveTo 169 84 self)
				(mySound number: 75 loop: 1 play:)
			)
			(4
				(HandsOn)
				(= global204 0)
				(client setScript: 0)
			)
		)
	)
)

(instance LookNote of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (& global118 $0001))
						(|= global118 $0001)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(Print 48 7)
				(Print 48 8)
				(= cycles 1)
			)
			(2
				(client setScript: 0)
			)
		)
	)
)

(instance lady of RPicView
	(properties
		y 47
		x 138
		view 148
		loop 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<behind,below/painting')
				(Print 48 9)
			)
			((Said 'get/painting')
				(Print 48 10)
			)
			((Said 'open/painting')
				(Print 48 11)
			)
			(
				(or
					(and (Said 'examine/eye>') (Said 'examine/girl'))
					(Said 'examine/eye[<girl,painting]')
					(Said 'examine/eye/girl')
				)
				(Print 48 12)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/painting')
					(Said 'examine/girl/painting')
				)
				(event claimed: TRUE)
				(Print 48 13)
			)
		)
	)
)

(instance bed1 of RPicView
	(properties
		y 105
		x 208
		view 148
		loop 2
		priority 6
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {bed})
		)
	)
)

(instance chest of RPicView
	(properties
		y 89
		x 136
		view 148
		loop 1
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/luggage') (Print 48 14))
			((Said 'examine[<at]/luggage') (Print 48 15))
			((Said 'get/luggage') (Print 48 16))
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine[<at]/drawer')
				)
				(event claimed: TRUE)
				(Print 48 17)
			)
		)
	)
)

(instance bed2 of RPicView
	(properties
		y 107
		x 104
		view 148
		loop 2
		cel 1
		priority 6
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {bed})
		)
	)
)

(instance desk of RPicView
	(properties
		y 135
		x 283
		view 148
		loop 1
		cel 8
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/(drawer<desk),desk,(top[<desk])')
				(Print 48 18)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/desk'))
				(event claimed: TRUE)
				(if (== currentAct 5)
					(Print 48 19)
				else
					(Print 48 20)
				)
			)
		)
	)
)

(instance table1 of RPicView
	(properties
		y 90
		x 63
		view 148
		loop 1
		cel 2
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 48 17)
		)
	)
)

(instance chair of RPicView
	(properties
		y 137
		x 254
		view 148
		loop 2
		cel 3
		priority 9
	)
	
	(method (handleEvent event)
		(if (== currentAct 6)
			(cond 
				((Said 'get,straighten/chair')
					(Print 48 21)
				)
				((or (Said 'examine/chair') (MousedOn self event shiftDown))
					(event claimed: TRUE)
					(Print 48 22)
				)
			)
		)
		(if (MousedOn self event shiftDown)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance sofa of RPicView
	(properties
		y 157
		x 194
		view 148
		loop 1
		cel 7
		priority 11
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance table2 of RPicView
	(properties
		y 157
		x 133
		view 148
		loop 1
		cel 6
		priority 11
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine/nightstand>')
			)
			(cond 
				((and (== currentAct 2) (> [global368 2] 2))
					(Print 48 23)
					(event claimed: TRUE)
				)
				((MousedOn self event shiftDown) (ParseName {table})
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance suit1 of RPicView
	(properties
		y 113
		x 214
		view 148
		loop 1
		cel 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 48 15)
		)
	)
)

(instance suit2 of RPicView
	(properties
		y 100
		x 72
		view 148
		loop 1
		cel 4
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 48 15)
		)
	)
)

(instance notebook of Prop
	(properties
		y 111
		x 282
		view 148
		loop 1
		cel 9
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/desk')
					(Said 'examine/top[<desk]')
				)
				(event claimed: TRUE)
				(Print 48 24)
			)
			((Said 'rotate/page') (Print 48 25))
			((Said 'open/notebook') (Print 48 26))
			((Said 'close/notebook') (Print 48 27))
			((Said 'get/notebook') (Print 48 28))
			((Said 'examine,read/notebook')
				(if (ego inRect: 249 118 306 144)
					(Room48 setScript: LookNote)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 44
		x 72
		view 148
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance lamp2 of Prop
	(properties
		y 43
		x 202
		view 148
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance panel of Actor
	(properties
		y 84
		view 148
		illegalBits $0000
	)
)

(instance stain of Prop
	(properties
		y 140
		x 244
		view 148
		loop 2
		cel 5
	)
)
