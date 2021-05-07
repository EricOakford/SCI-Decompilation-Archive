;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room44 0
)
(synonyms
	(drawer chest dresser)
	(luggage case bag)
	(chute archway laundry)
	(room bedroom)
)

(local
	chuteIsOpen
	local1
	local2
	local3
	suitcaseIsClosed
	local5
	local6
	local7
)
(instance Room44 of Room
	(properties
		picture 44
	)
	
	(method (init)
		(= west 43)
		(= saveDisabled FALSE)
		(super init:)
		(SetCursor HAND_CURSOR TRUE 300 0)
		(cSound stop:)
		(Load FONT 4)
		(LoadMany VIEW 22 653 38)
		(LoadMany SOUND 74 75)
		(addToPics
			add:
				chest1
				chest2
				item3
				item4
				bed1
				bed2
				mirror
				sofa
				needle
				table2
				wingback
			eachElementDo: #init
			doit:
		)
		(lamp1 setPri: 13 init: stopUpd:)
		(lamp2 setPri: 13 init: stopUpd:)
		(if howFast
			(lamp1 startUpd: setCycle: Forward)
			(lamp2 startUpd: setCycle: Forward)
		)
		(self
			setRegions: 213
			setFeatures:
				mirror
				table2
				wingback
				sofa
				item4
				item3
				bed2
				bed1
				chest1
				chest2
				Fireplace
		)
		(suit1 setPri: 7 init: stopUpd:)
		(cond 
			((== currentAct 5)
				(= local6 1)
				(self setRegions: 274)
			)
			((== currentAct 0)
				(suit2 setPri: 11 ignoreActors: TRUE init:)
			)
			(else
				(suit2 setPri: 11 ignoreActors: TRUE init: stopUpd:)
			)
		)
		(if (and (== currentAct 3) (& global118 $0002))
			(= local6 1)
			(self setRegions: 265)
		)
		(if
			(and
				(== currentAct 6)
				(& global118 $0002)
				(not (Btst fFlag36))
			)
			(= local6 1)
			(self setRegions: 385)
		)
		(chute
			setLoop: 0
			setCel: 9
			yStep: 5
			illegalBits: 0
			setPri: 2
			ignoreActors: TRUE
			init:
			stopUpd:
			setScript: chuteActions
		)
		(if (== currentAct 0)
			(if (> global203 1) (= local6 1))
			(self setRegions: 230)
			(if (== global203 0)
				(self setRegions: 411)
				(= local5 1)
			)
		)
		(switch prevRoomNum
			(43
				(ego posn: 1 152)
			)
			(45
				(ego posn: 259 121)
			)
			(50
				(ego posn: 68 167)
				(= local7 1)
			)
		)
		(ego view: 0 ignoreActors: FALSE illegalBits: cWHITE init:)
	)
	
	(method (doit)
		(if local7
			(= local7 0)
			(Print 44 0)
		)
		(if (FirstEntry) (ego yStep: 2))
		(if (and (== global203 1) local5)
			((ScriptID 411) dispose:)
			(= local5 0)
		)
		(if (& (ego onControl: origin) cGREEN)
			(curRoom newRoom: 45)
		)
		(if (not local3)
			(if (and (< (ego x?) 51) (> (ego y?) 127))
				(ego setPri: 10)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			((< (ego x?) 30)
				(= vertAngle 0)
			)
			((< (ego x?) 140)
				(= vertAngle 163)
			)
			(else
				(= vertAngle 137)
			)
		)
		(if (> global203 1)
			(= local6 1)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(DisposeScript 990)
			(if
				(and
					global208
					(Said
						'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
					)
				)
				(self setScript: (ScriptID 243 0))
				((self script?) handleEvent: event)
			)
			(cond 
				((event claimed?))
				((Said '/panel,(door<hidden)>')
					(cond 
						((Said 'examine')
							(if (& global175 $0020)
								(Print 44 1)
							else
								(Print 44 2)
							)
						)
						((and (& global175 $0020) (Said 'open,move'))
							(if (not local6)
								(if (& (ego onControl: origin) cCYAN)
									(curRoom newRoom: 50)
								else
									(NotClose)
								)
							else
								(Print 44 3)
							)
						)
					)
				)
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]')
							(Print 44 4)
						)
						((Said '/mantel')
							(Print 44 5)
						)
						((Said '/wall')
							(Print 44 6)
						)
						((Said '<in/vanity,(nightstand<dressing)')
							(Print 44 7)
						)
						((Said '/vanity,(nightstand<dressing)')
							(Print 44 8)
						)
					)
				)
				((Said 'open/vanity,(nightstand<dressing)')
					(Print 44 7)
				)
				((Said 'get/diary')
					(cond 
						((ego has: iDiary)
							(AlreadyTook)
						)
						((< (ego distanceTo: suit2) 15)
							(if (>= currentAct 6)
								(HandsOff)
								(= suitcaseIsClosed 1)
								(= gotItem TRUE)
								(ego get: iDiary)
								(= local2 1)
								(suit2 setScript: OpenSuit)
							else
								(Print 44 9)
							)
						)
						(else
							(NotClose)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(cls)
		(super newRoom: n)
	)
)

(instance chuteActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
			)
			(1
				(User canControl: FALSE)
				(ego illegalBits: 0 setMotion: MoveTo 32 128 self)
			)
			(2
				(ego view: 22 loop: 0 cel: 0 setCycle: EndLoop)
				(myMusic number: 74 loop: 1 play:)
				(chute setMotion: MoveTo 19 167 self)
			)
			(3
				(Print 44 15)
				(= chuteIsOpen 1)
			)
			(4
				(chute setMotion: MoveTo 19 127)
				(= chuteIsOpen 0)
				(myMusic number: 75 loop: 1 play:)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego view: 0 loop: 1 setCycle: Walk illegalBits: -32768)
				(User canControl: TRUE)
			)
			(6
				(myMusic number: 9 loop: 1 play:)
				(ego
					setLoop: 2
					cel: 0
					setMotion: MoveTo 26 128
					setCycle: EndLoop self
				)
				(Print 44 16
					#at 160 152
					#font 4
					#width 125
					#mode teJustCenter
					#draw
					#dispose
				)
			)
			(7
				(= seconds 4)
			)
			(8
				(if (> global203 1)
					(cls)
					(= global172 99)
				)
				(ShakeScreen 10 5)
				(myMusic number: 47 loop: 1 play:)
				(= cycles 21)
			)
			(9
				(= global172 110)
				(= local3 1)
				(myMusic number: 57 loop: 1 play: self)
				(ego
					view: 38
					setLoop: 1
					posn: 20 200
					setCycle: Walk
					setPri: 9
					setMotion: MoveTo 25 -1 self
				)
			)
			(10
				(= cIcon myIcon)
				(= deathLoop 0)
				(= cyclingIcon TRUE)
			)
			(11
				(EgoDead 44 17)
			)
		)
	)
	
	(method (handleEvent event)
		(if (== (event type?) saidEvent)
			(cond 
				((event claimed?))
				((Said 'examine<(down,up)')
					(if chuteIsOpen
						(Print 44 10)
					else
						(event claimed: FALSE)
					)
				)
				((Said 'examine/chute')
					(if chuteIsOpen
						(Print 44 10)
					else
						(Print 44 11)
					)
				)
				((Said 'open/door,chute')
					(cond 
						(chuteIsOpen
							(Print 44 12)
						)
						((ego inRect: 10 127 40 135)
							(= state 0)
							(= cycles 1)
						)
						(else
							(NotClose)
						)
					)
				)
				((Said 'hop,crawl,go,enter,climb/chute')
					(if chuteIsOpen
						(HandsOff)
						(= state 5)
						(= cycles 1)
					else
						(Print 44 13)
					)
				)
				((or (Said 'stand') (Said 'close/door,chute'))
					(if chuteIsOpen
						(= cycles 1)
					else
						(Print 44 14)
					)
				)
			)
		)
	)
)

(instance OpenSuit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop self)
			)
			(1
				(= seconds 2)
			)
			(2
				(cond 
					(suitcaseIsClosed
						(Ok)
						(= suitcaseIsClosed FALSE)
					)
					(local2
						(if (ego has: iDiary)
							(Print 44 18)
						else
							(Print 44 19)
						)
					)
					(else
						(Print 44 20)
					)
				)
				(= cycles 1)
			)
			(3
				(client setCycle: BegLoop self)
			)
			(4
				(HandsOn)
				(client stopUpd: setScript: 0)
			)
		)
	)
)

(instance chest1 of RPicView
	(properties
		y 91
		x 43
		view 144
		cel 4
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine[<at]/drawer')
			)
			(event claimed: TRUE)
			(Print 44 21)
		)
	)
)

(instance chest2 of RPicView
	(properties
		y 91
		x 205
		view 144
		cel 4
	)
	
	(method (handleEvent event)
		(if (and (MousedOn self event shiftDown) (event claimed: FALSE))
			(event claimed: TRUE)
			(Print 44 21)
		)
	)
)

(instance bed1 of RPicView
	(properties
		y 106
		x 211
		view 144
		cel 7
		priority 7
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine[<at]/bed'))
			(event claimed: TRUE)
			(Print 44 22)
		)
	)
)

(instance bed2 of RPicView
	(properties
		y 153
		x 269
		view 144
		cel 8
		priority 11
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (and (MousedOn self event shiftDown) (event claimed: FALSE))
			(event claimed: TRUE)
			(Print 44 22)
		)
	)
)

(instance mirror of RPicView
	(properties
		y 147
		x 280
		view 144
		priority 10
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'examine<in/mirror')
					(Said 'examine<at/reflection')
				)
				(if (< (ego distanceTo: mirror) 80)
					(= theTalker talkLAURA)
					(Say 0 44 23)
				else
					(NotClose)
				)
			)
			((Said 'examine<(behind,below)/mirror')
				(Print 44 24)
			)
			((Said 'get/mirror')
				(Print 44 25)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/mirror'))
				(Print 44 26)
				(event claimed: TRUE)
			)
		)
	)
)

(instance sofa of RPicView
	(properties
		y 169
		x 172
		view 144
		cel 1
		priority 13
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance wingback of RPicView
	(properties
		y 148
		x 113
		view 144
		cel 2
		priority 10
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance item3 of RPicView
	(properties
		y 116
		x 251
		view 144
		cel 3
		priority 8
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance item4 of RPicView
	(properties
		y 42
		x 129
		view 144
		cel 5
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(and (Said 'examine/eye>') (Said 'examine/colonel'))
					(Said 'examine/eye<colonel')
					(Said 'examine/eye/colonel')
				)
				(Print 44 27)
			)
			(
				(or
					(and (Said 'examine/eye>') (Said 'examine/girl'))
					(Said 'examine/eye[<girl,painting]')
					(Said 'examine/eye/girl')
				)
				(Print 44 28)
			)
			((Said 'move,get/painting')
				(Print 44 29)
			)
			((Said 'examine<(behind,below)/painting')
				(Print 44 30)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/painting,colonel')
					(Said 'examine/girl,fellow,colonel/painting')
				)
				(event claimed: TRUE)
				(Print 44 31)
			)
		)
	)
)

(instance needle of PicView
	(properties
		y 78
		x 26
		view 144
		cel 6
		priority 9
	)
)

(instance table2 of RPicView
	(properties
		y 169
		x 127
		view 144
		cel 3
		priority 13
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 30
		x 240
		view 144
		loop 3
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
		y 53
		x 301
		view 144
		loop 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance suit1 of Prop
	(properties
		y 88
		x 208
		view 144
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/cloth')
				(if (ego inRect: 245 131 290 160)
					(Print 44 32)
				else
					(Print 44 33)
				)
			)
			((Said 'change,wear,(attach<on)/cloth')
				(if (ego inRect: 245 131 290 160)
					(Print 44 34)
				else
					(Print 44 33)
				)
			)
			((Said '(examine<through),search/cloth')
				(if (ego inRect: 245 131 290 160)
					(Print 44 35)
				else
					(Print 44 36)
				)
			)
			((Said '/(luggage,cloth)>')
				(cond 
					((Said 'open,(examine<in)')
						(cond 
							((ego inRect: 245 131 290 140)
								(cond 
									((< currentAct 5)
										(Print 44 9)
									)
									((== currentAct 5)
										(Print 44 37)
									)
									(else
										(HandsOff)
										(= local2 1)
										(= local1 0)
										(suit2 setScript: OpenSuit)
									)
								)
							)
							(
								(or
									(ego inRect: 241 154 320 200)
									(ego inRect: 178 0 320 98)
								)
								(Print 44 38)
							)
							((< (ego distanceTo: suit1) 25)
								(= local1 1)
								(= local2 0)
								(HandsOff)
								(suit1 setScript: OpenSuit)
							)
							(else (NotClose))
						)
					)
					((Said 'examine')
						(Print 44 39)
					)
					((Said 'close')
						(AlreadyClosed)
					)
					((Said 'get')
						(if
							(or
								(ego inRect: 245 131 290 160)
								(< (ego distanceTo: suit1) 25)
							)
							(Print 44 40)
						else
							(NotClose)
						)
					)
				)
			)
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(ParseName {suitcase})
			)
		)
	)
)

(instance suit2 of Prop
	(properties
		y 133
		x 265
		view 144
		loop 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {suitcase})
		)
	)
)

(instance chute of Actor
	(properties
		y 127
		x 19
		view 144
		cel 9
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/door'))
			(event claimed: TRUE)
			(Print 44 6)
		)
	)
)

(instance Fireplace of RFeature
	(properties
		nsTop 47
		nsLeft 108
		nsBottom 88
		nsRight 154
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {fireplace})
		)
	)
)

(instance myMusic of Sound)

(instance myIcon of DCIcon
	(properties
		view 653
		cycleSpeed 16
	)
)
