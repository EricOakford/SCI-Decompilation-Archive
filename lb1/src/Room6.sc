;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room6 0
)

(local
	local0
	greetCued
)
(instance Room6 of Room
	(properties
		picture 6
	)
	
	(method (init)
		(= south 18)
		(= west 12)
		(= east 7)
		(super init:)
		(if (and (not (& global118 $0001)) (== currentAct 5))
			(Load FONT 41)
			(Load VIEW 642)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
		)
		(if howFast
			(chick1
				illegalBits: cGREEN
				ignoreActors: TRUE
				moveSpeed: 2
				setMotion: Wander 1
				setPri: 7
				init:
			)
			(chick2
				illegalBits: cGREEN
				ignoreActors: TRUE
				moveSpeed: 2
				setMotion: Wander 1
				setPri: 7
				init:
			)
		else
			(chick1
				illegalBits: cGREEN
				ignoreActors: TRUE
				setPri: 7
				init:
				stopUpd:
			)
			(chick2
				illegalBits: cGREEN
				ignoreActors: TRUE
				setPri: 7
				init:
				stopUpd:
			)
		)
		(addToPics add: coop eachElementDo: #init doit:)
		(Door
			cel: (if (== prevRoomNum 59) 3 else 0)
			init:
			stopUpd:
		)
		(= gDoor Door)
		(LoadMany SOUND 43 44 48)
		(Load VIEW 56)
		(self setFeatures: coop Window1 Window2)
		(if (>= currentAct 2)
			(light1 init: stopUpd:)
			(light2 init: stopUpd:)
		)
		(if (and (== currentAct 3) (not (== prevRoomNum 59)))
			(self setRegions: 266)
		else
			(Chair init: stopUpd:)
			(Celie view: 480)
		)
		(if (and (== currentAct 3) (< global115 2))
			(self setRegions: 203)
		)
		(switch prevRoomNum
			(7
				(ego posn: 310 151)
			)
			(12 (ego posn: 1 182))
			(59
				(ego posn: 238 101)
				(Door setScript: closeDoor)
			)
			(18 (ego posn: 148 185))
		)
		(ego illegalBits: cWHITE view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 6 0)
		)
		(if (& (ego onControl: FALSE) cYELLOW)
			(ego setPri: 6)
		else
			(ego setPri: -1)
		)
		(super doit:)
		(if (and (== global191 1) (not local0))
			(= local0 1)
			(Door show:)
		)
	)
	
	(method (dispose)
		(DisposeScript 976)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return))
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
				(if (event claimed?) (return))
			)
			(cond 
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]') (Print 6 0))
						((Said '/field') (Print 6 1))
						((Said '/fence') (Print 6 2))
						((Said '/archway') (Print 6 3))
						((Said '/path') (Print 6 4))
						((Said '/cabin') (Print 6 5))
						((Said '/gallery')
							(if (== currentAct 3)
								(Print 6 6)
							else
								(Print 6 7)
							)
						)
					)
				)
				((Said 'bang[/door]')
					(cond 
						((and (== currentAct 3) (not (== prevRoomNum 59)))
							(Print 6 8)
						)
						((& (ego onControl: FALSE) cRED)
							(if (not greetCued)
								(= greetCued TRUE)
								(ego setScript: knockDoor)
							)
						)
						(else
							(NotClose)
						)
					)
				)
				((Said 'climb/fence')
					(Print 6 9)
				)
			)
		)
		(super handleEvent: event)
	)
	
	(method (newRoom n)
		(if (== n 59)
			(cSound stop:)
		)
		(super newRoom: n)
	)
)

(instance firstGreet of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(= global195 2)
				(Print 6 11)
				(= seconds 4)
			)
			(1
				(Celie setPri: 4 loop: 2 posn: 239 93 init:)
				(Door setCycle: EndLoop self)
				(myMusic number: 43 loop: 1 play:)
			)
			(2
				(User canInput: TRUE)
				(Print 6 12)
				(= seconds 8)
			)
			(3
				(HandsOff)
				(Print 6 13)
				(Door setCycle: BegLoop self)
				(myMusic number: 44 loop: 1 play:)
			)
			(4
				(User canControl: TRUE canInput: TRUE)
				(= greetCued FALSE)
				(Celie dispose:)
				(client setScript: 0)
			)
			(5
				(Print 6 14)
				(ego put: iNecklace)
				(= global135 1)
				(curRoom newRoom: 59)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				(
					(or
						(Said 'hold,deliver/necklace[/celie]')
						(Said 'hold,deliver/necklace<celie')
					)
					(if (ego has: iNecklace)
						(if (== state 2)
							(= state 4)
							(= cycles 1)
						else
							(Print 6 10)
						)
					else
						(DontHave)
					)
				)
				((Said '*')
					(event claimed: TRUE)
					(= seconds 0)
					(= cycles 1)
				)
			)
		)
	)
)

(instance secondGreet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global195 2)
				(Celie setPri: 4 loop: 2 posn: 239 90 init:)
				(Door setCycle: EndLoop self)
				(myMusic number: 43 loop: 1 play:)
			)
			(1
				(Print 6 15)
				(= cycles 1)
			)
			(2
				(curRoom newRoom: 59)
			)
		)
	)
)

(instance lastGreet of Script

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
				(HandsOff)
				(= global195 2)
				(Celie
					setPri: 4
					loop: 2
					ignoreActors: 1
					posn: 239 90
					init:
				)
				(Door setCycle: EndLoop self)
				(myMusic number: 43 loop: 1 play:)
			)
			(2
				(Print 6 16)
				(= cycles 1)
			)
			(3
				(Door setCycle: BegLoop self)
				(myMusic number: 44 loop: 1 play:)
			)
			(4
				(HandsOn)
				(Door stopUpd:)
				(Celie stopUpd:)
				(= greetCued (= global195 0))
				(client setScript: 0)
			)
		)
	)
)

(instance knockDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 56 loop: 0 illegalBits: 0 setCycle: EndLoop self)
			)
			(1
				(myMusic number: 48 loop: 1 play:)
				(ego loop: 2 setCycle: Forward)
				(= cycles 6)
			)
			(2
				(cls)
				(ego view: 56 loop: 0 cel: 3 setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(ego view: 0 setCycle: Walk illegalBits: cWHITE loop: 3)
				(cond 
					(
						(and
							(>= currentAct 2)
							(!= currentAct 3)
							(< currentAct 6)
						)
						(cond 
							((== currentAct 5)
								(if (& global135 $0100)
									(Print 6 17)
									(= greetCued FALSE)
								else
									(|= global135 $0100)
									(Room6 setScript: lastGreet)
								)
							)
							((== global135 1)
								(Room6 setScript: secondGreet)
							)
							(else
								(Room6 setScript: firstGreet)
							)
						)
					)
					((== currentAct 7)
						(switch (Random 0 5)
							(0 (Print 6 18))
							(1 (Print 6 19))
							(2 (Print 6 20))
							(3 (Print 6 21))
							(4 (Print 6 22))
							(5 (Print 6 23))
						)
						(= greetCued FALSE)
					)
					(else
						(= greetCued FALSE)
						(Print 6 24)
					)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 93
		x 227
		view 106
		priority 5
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((Said 'break,force/door')
				(Print 6 25)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/door'))
				(event claimed: TRUE)
				(Print 6 26)
			)
			((Said 'open/door')
				(if (ego inRect: 221 92 259 108)
					(if (and (>= currentAct 2) (< currentAct 6))
						(Print 6 27)
					else
						(Print 6 28)
					)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance light1 of Prop
	(properties
		y 64
		x 198
		view 106
		loop 1
		cel 1
	)
)

(instance light2 of Prop
	(properties
		y 67
		x 283
		view 106
		loop 1
		cel 1
	)
)

(instance Chair of Prop
	(properties
		y 108
		x 270
		view 106
		loop 3
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((Said 'get/rocker,chair[<rocking]')
				(Print 6 29)
			)
			((Said 'boulder,sit[/chair,rocker]')
				(if (and (== currentAct 3) (!= prevRoomNum 59))
					(Print 6 30)
				else
					(Print 6 31)
				)
			)
			(
				(or
					(Said 'examine/rocker,chair[<rocking]')
					(MousedOn self event shiftDown)
				)
				(event claimed: TRUE)
				(if (== currentAct 3)
					(Print 6 32)
				else
					(Print 6 7)
				)
			)
		)
	)
)

(instance chick1 of Actor
	(properties
		y 106
		x 81
		view 259
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/chicken'))
				(Print 6 33)
				(event claimed: TRUE)
			)
			(
				(or
					(Said 'feed,deliver/chicken')
					(Said 'feed,deliver/*/chicken')
					(Said 'feed,deliver/*<chicken')
				)
				(if theInvItem
					(if haveInvItem
						(Print 6 34)
					else
						(DontHave)
					)
				else
					(Print 6 34)
				)
			)
			(
				(or
					(Said 'capture,get,detach/chicken')
					(Said 'open/coop')
				)
				(Print 6 35)
			)
			((Said 'converse/chicken')
				(Print 6 36)
			)
		)
	)
)

(instance chick2 of Actor
	(properties
		y 102
		x 94
		view 259
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (MousedOn self event shiftDown)
			(Print 6 33)
			(event claimed: TRUE)
		)
	)
)

(instance Celie of Prop
	(properties)
)

(instance coop of RPicView
	(properties
		y 126
		x 90
		view 106
		loop 2
		priority 8
		signal ignrAct
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			(
				(Said
					'examine<(below,behind)/(coop[<chicken]),(cabin<chicken)'
				)
				(Print 6 37)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/(coop[<chicken]),(cabin<chicken)')
				)
				(Print 6 38)
				(event claimed: TRUE)
			)
		)
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 45
		nsLeft 183
		nsBottom 66
		nsRight 213
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((Said 'break/window')
				(Print 6 39)
			)
			((Said 'examine<(in,through)/window,(cabin[<celie])')
				(if
					(or
						(ego inRect: 179 92 221 103)
						(ego inRect: 259 95 305 108)
					)
					(Print 6 40)
				else
					(NotClose)
				)
			)
			((Said 'open/window')
				(Print 6 41)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/window'))
				(event claimed: TRUE)
				(Print 6 42)
			)
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 46
		nsLeft 266
		nsBottom 68
		nsRight 297
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 6 42)
		)
	)
)

(instance myMusic of Sound)

(instance closeDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Door setCycle: BegLoop self)
				(myMusic number: 44 loop: 1 play:)
			)
			(1
				(Door stopUpd:)
				(client setScript: 0)
			)
		)
	)
)
