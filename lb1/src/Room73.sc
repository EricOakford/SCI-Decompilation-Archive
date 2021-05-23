;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
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
	Room73 0
)
(synonyms
	(room bathroom)
	(lil person girl)
	(shower bath)
)

(local
	local0
	local1
	firstTime
	local3
	pickingUp
)
(instance Room73 of Room
	(properties
		picture 73
	)
	
	(method (init)
		(= south 43)
		(super init:)
		(LoadMany VIEW 17 19 21 40 41 641 905)
		(LoadMany SOUND 26 76)
		(Load SCRIPT AVOIDER)
		(addToPics
			add: sink toilet bathtub basket settie
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures: basket sink toilet bathtub Cabinet settie Window1 Mirror
		)
		(towel setPri: 9 init: stopUpd:)
		(rope setPri: 13 init: stopUpd:)
		(if (== global203 1)
			(if (> [global368 4] 1)
				(LoadMany VIEW 500 505 905)
				(LoadMany 143 243 226)
				(= [global377 5] 226)
				(|= global208 $0020)
				(Lilian
					setAvoider: ((Avoider new:) offScreenOK: TRUE)
					init:
					setScript: perfume
				)
			else
				(= global125 1)
			)
		)
		(if
			(and
				(>= currentAct 6)
				(not (& deadGuests deadRUDY))
				(not (& deadGuests deadLILLIAN))
			)
			(cond 
				((== gCurRoomNum_3 73)
					(self setRegions: 278)
					(= local3 1)
				)
				((not (== gCurRoomNum_3 41))
					(switch (Random 1 2)
						(1
							(self setRegions: 278)
							(= local3 1)
						)
						(2
							(= gCurRoomNum_3 41)
						)
					)
				)
			)
		)
		(ego view: 0 posn: 178 188 observeControl: cYELLOW init:)
		(if global153
			(ego
				view: 40
				loop: 0
				cel: 0
				illegalBits: 0
				posn: 261 168
				setScript: wash
			)
		)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 73 0)
			(= firstTime TRUE)
		)
		(cond 
			((< (ego x?) 130)
				(= vertAngle 20)
			)
			((< (ego x?) 190)
				(= vertAngle 0)
			)
			(else
				(= vertAngle 160)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(if (>= currentAct 5)
				(cond 
					(
						(or
							(Said 'read,examine/bottle,label/monocle')
							(Said '(read,examine)<use<monocle/bottle,label')
						)
						(if (ego has: iMonocle)
							(if (< (ego distanceTo: basket) 30)
								(Bset fGotMonocle)
								(= pickingUp 1)
								(self setScript: pickUp)
							else
								(NotClose)
							)
						else
							(DontHave)
						)
					)
					((Said '/bottle>')
						(cond 
							((Said 'read')
								(Print 73 1)
							)
							((Said 'open,(examine<in)')
								(Print 73 2)
							)
							((Said 'examine')
								(if (< (ego distanceTo: basket) 20)
									(if (>= currentAct 5)
										(Print 73 3)
									else
										(Print 73 4)
									)
								else
									(NotClose)
								)
							)
							((Said 'get')
								(if (< (ego distanceTo: basket) 30)
									(self setScript: pickUp)
								else
									(NotClose)
								)
							)
						)
					)
					((Said 'read,examine/label,print')
						(Print 73 1)
					)
					((Said 'get,examine,detach/powder')
						(Print 73 2)
					)
				)
			)
			(if (event claimed?) (return))
			(cond 
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]')
							(Print 73 0)
						)
						((Said '/curtain<shower')
							(Print 73 5)
						)
						((Said '/soap')
							(Print 73 6)
						)
						((Said '/chair,bench')
							(Print 73 7)
						)
						((Said '/door')
							(Print 73 8)
						)
					)
				)
				((Said 'get/mirror')
					(Print 73 9)
				)
				((Said 'get/soap')
					(Print 73 10)
				)
				((Said 'get,(get<in)/shower,shower')
					(cond 
						((cast contains: Lilian)
							(Print 73 11)
						)
						(local3
							(Print 73 12)
						)
						((== currentAct 7)
							(Print 73 13)
						)
						(else
							(Print 73 14)
							(self setScript: shower)
						)
					)
				)
				((Said 'flush,drag/chain,toilet')
					(if (ego inRect: 230 160 281 170)
						(Print 73 15)
						(= local1 1)
						(self setScript: flushing)
					else
						(NotClose)
					)
				)
				((Said 'scrub/face')
					(Print 73 16)
				)
				(
					(or
						(Said 'rotate<on/water')
						(Said 'scrub/deliver')
						(Said 'scrub[<!*]')
					)
					(cond 
						((ego inRect: 61 121 121 151)
							(if (cast contains: Lilian)
								(Print 73 17)
							else
								(Print 73 15)
								(self setScript: wash)
							)
						)
						((ego inRect: 197 127 264 154)
							(if (cast contains: Lilian)
								(Print 73 11)
							else
								(Print 73 14)
								(self setScript: shower)
							)
						)
						(else
							(NotClose)
						)
					)
				)
				((or (Said 'drink') (Said 'drink/water[<sink,shower]'))
					(Print 73 18)
				)
				((Said 'sit,go,use/room,toilet')
					(if (cast contains: Lilian)
						(Print 73 19)
					else
						(ego loop: 2)
						(Print 73 20)
						(= global153 1)
						(curRoom newRoom: 43)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(HandsOff)
		(super newRoom: n)
	)
)

(instance perfume of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Lilian loop: 7 cel: 0 cycleSpeed: 1 setCycle: Forward)
				(mySound number: 76 loop: 1 play:)
				(= cycles 2)
			)
			(1
				(if firstTime
					(= theTalker talkLILLIAN)
					(Say 1 73 21)
				)
				(= cycles 20)
			)
			(2
				(towel hide:)
				(mySound stop:)
				(Lilian loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3
				(Lilian loop: 8 cel: 0 setCycle: EndLoop self)
			)
			(4
				(Lilian loop: 9 setCycle: Forward)
				(= cycles 30)
			)
			(5
				(Lilian
					loop: 8
					cel: (- (NumCels Lilian) 1)
					setCycle: BegLoop self
				)
			)
			(6
				(Lilian loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(7
				(Lilian loop: 6 setCycle: BegLoop self)
			)
			(8
				(Lilian loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(9
				(towel show:)
				(Lilian view: 505 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(10
				(Lilian loop: 1 cel: 0 setCycle: Forward)
				(= cycles 30)
			)
			(11
				(Lilian loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(12
				(Lilian loop: 3 cel: 0 setCycle: Forward)
				(= cycles 30)
			)
			(13
				(Lilian loop: 0 cel: 3 setCycle: BegLoop self)
			)
			(14
				(HandsOff)
				(Lilian view: 500 setCycle: Walk cycleSpeed: 0)
				(if (ego inRect: 140 130 160 140)
					(Lilian setMotion: MoveTo 147 160 self)
				else
					(Lilian setMotion: MoveTo 147 (Lilian y?) self)
				)
			)
			(15
				(Lilian setMotion: MoveTo 147 240 self)
			)
			(16
				(HandsOn)
				(++ global203)
				(= [global368 4] 1)
				(Lilian dispose:)
				(= global125 1)
				(client setScript: 0)
			)
		)
	)
)

(instance shower of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo 205 148 self
				)
			)
			(1
				(ego view: 19 loop: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: 1 setCycle: EndLoop self)
			)
			(3
				(ego loop: 2 setCycle: EndLoop self)
			)
			(4
				(bra setCycle: EndLoop init:)
				(ego view: 21 loop: 0 setCycle: EndLoop self)
			)
			(5
				(curRoom newRoom: 215)
				(client setScript: 0)
			)
		)
	)
)

(instance flushing of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 261 165 self)
			)
			(1
				(if (not local1)
					(= global153 1)
					(ego setScript: wash)
					(client setScript: 0)
				else
					(= cycles 1)
				)
			)
			(2
				(ego view: 40 loop: 2 setCycle: EndLoop self)
			)
			(3
				(rope hide:)
				(mySound number: 26 loop: 1 play:)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(rope show:)
				(ego loop: 2 cel: 2 setCycle: BegLoop self)
			)
			(6
				(ego
					view: 0
					loop: 0
					setCycle: Walk
					posn: (- (ego x?) 2) (ego y?)
					illegalBits: cWHITE
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance wash of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if global153
					(ego view: 40 loop: 0 setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(if global153
					(rope hide:)
					(mySound number: 26 loop: 1 play:)
					(ego loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(if global153
					(ego setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(3
				(if global153
					(rope show:)
					(ego loop: 0 cel: 2 setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(4
				(ego
					view: 0
					setCycle: Walk
					setAvoider: (Avoider new:)
					ignoreControl: cYELLOW
					setMotion: MoveTo 100 134 self
				)
			)
			(5
				(ego setPri: 11 view: 41 loop: 5 setCycle: Forward)
				(mySound number: 76 loop: -1 play:)
				(= seconds 5)
			)
			(6
				(mySound stop:)
				(towel hide:)
				(ego setPri: -1 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(7
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(8
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(9
				(ego loop: 3 cel: 0 setCycle: BegLoop self)
			)
			(10
				(ego
					loop: 2
					cel: (- (NumCels ego) 1)
					setCycle: BegLoop self
				)
			)
			(11
				(ego loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(12
				(ego loop: 4 setCycle: BegLoop self)
			)
			(13
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(14
				(towel show:)
				(ego
					view: 0
					illegalBits: cWHITE
					observeControl: cYELLOW
					setAvoider: 0
					setCycle: Walk
				)
				(HandsOn)
				(= global153 0)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 116 128 self)
			)
			(1
				(ego
					view: 17
					cel: 0
					loop: 3
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(if pickingUp
					(= pickingUp FALSE)
					(Print 73 22 #icon 641 0 0)
				else
					(Print 73 23)
					(Print 73 24)
					(Print 73 25)
					(Print 73 26)
				)
				(= cycles 2)
			)
			(4 (ego setCycle: EndLoop self))
			(5 (ego setCycle: BegLoop self))
			(6
				(HandsOn)
				(ego view: 0 loop: 3 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)

(instance sink of RPicView
	(properties
		y 141
		x 81
		view 173
		priority 9
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<in/sink')
				(Print 73 27)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/sink'))
				(event claimed: TRUE)
				(Print 73 28)
			)
		)
	)
)

(instance toilet of RPicView
	(properties
		y 174
		x 271
		view 173
		cel 2
		priority 13
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/toilet')
				(Print 73 29)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/toilet'))
				(event claimed: TRUE)
				(Print 73 30)
			)
		)
	)
)

(instance bathtub of RPicView
	(properties
		y 151
		x 241
		view 173
		cel 1
		priority 10
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<in/shower')
				(if local3
					(Print 73 31)
				else
					(Print 73 32)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/shower'))
				(event claimed: TRUE)
				(if local3
					(Print 73 31)
				else
					(Print 73 33)
				)
			)
		)
	)
)

(instance settie of RPicView
	(properties
		y 125
		x 161
		view 173
		cel 3
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/bench')
				(Print 73 34)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/chair,bench')
				)
				(event claimed: TRUE)
				(Print 73 7)
			)
		)
	)
)

(instance basket of RPicView
	(properties
		y 122
		x 117
		view 173
		cel 6
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/(basket[<wastepaper,garbage]),(can[<garbage])')
				(Print 73 35)
			)
			(
				(Said
					'examine<in/(basket[<wastepaper,garbage]),(can[<garbage])'
				)
				(if (< (ego distanceTo: basket) 20)
					(if (>= currentAct 5)
						(Print 73 3)
					else
						(Print 73 4)
					)
				else
					(NotClose)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/(basket[<wastepaper,garbage]),(can[<garbage])')
				)
				(event claimed: TRUE)
				(Print 73 36)
			)
		)
	)
)

(instance bra of Prop
	(properties
		y 148
		x 210
		view 19
		loop 3
	)
)

(instance towel of Prop
	(properties
		y 95
		x 91
		view 173
		cel 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/towel,(cloth<scrub)')
				(Print 73 37)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/towel,(cloth<scrub)')
				)
				(event claimed: TRUE)
				(Print 73 38)
			)
		)
	)
)

(instance rope of Prop
	(properties
		y 134
		x 269
		view 173
		cel 5
	)
)

(instance Lilian of Actor
	(properties
		y 134
		x 97
		view 505
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/lil'))
				(if (not (& global207 $0020))
					(|= global207 $0020)
					(= theTalker talkLILLIAN)
					(Say 0 73 39)
				else
					(Print 73 40)
				)
				(event claimed: TRUE)
			)
			((Said 'converse,ask,tell')
				(Print 73 41)
			)
			((Said 'hold,deliver,get,kill,kiss,embrace,flirt>')
				(Room73 setScript: (ScriptID 243 0))
				((Room73 script?) handleEvent: event)
			)
		)
	)
)

(instance Cabinet of RFeature
	(properties
		nsTop 95
		nsLeft 51
		nsBottom 152
		nsRight 67
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/armoire[<medicine,linen]')
				(Print 73 42)
			)
			((Said 'examine<behind/armoire[<medicine,linen]')
				(Print 73 43)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/armoire[<medicine,linen]')
				)
				(event claimed: TRUE)
				(Print 73 44)
			)
		)
	)
)

(instance Mirror of RFeature
	(properties
		nsTop 68
		nsLeft 68
		nsBottom 94
		nsRight 84
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'examine<in/mirror')
					(Said 'examine/reflection')
				)
				(if (< (ego distanceTo: sink) 40)
					(= theTalker talkLAURA)
					(Say 0 73 45)
				else
					(NotClose)
				)
			)
			((Said 'examine<(behind,below)/mirror')
				(Print 73 46)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/mirror'))
				(event claimed: TRUE)
				(Print 73 47)
			)
		)
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 43
		nsLeft 98
		nsBottom 97
		nsRight 224
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {window})
		)
	)
)

(instance mySound of Sound)
