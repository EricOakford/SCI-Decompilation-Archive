;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm460 0
)
(synonyms
	(man attendant arnold)
)

(local
	local0
	manResponse
)
(instance rm460 of Room
	(properties
		picture 460
		north 470
		west 400
	)
	
	(method (init)
		(Load VIEW (+ 715 (* 100 playingAsPatti)))
		(Load SCRIPT REVERSE)
		(Load SOUND 460)
		(Load SOUND 461)
		(super init:)
		(addToPics add: atpDoor doit:)
		(if (not playingAsPatti) (aMan init:))
		(aButton setPri: 9 init: stopUpd:)
		(aLightLeft setScript: (LightScript new:) init:)
		(aLightRight setScript: (LightScript new:) init:)
		(aDoor setCel: 0 ignoreActors: init: stopUpd:)
		(self
			setLocales: (+ 80 playingAsPatti)
			setScript: RoomScript
		)
		(NormalEgo)
		(if (== prevRoomNum 470)
			(HandsOff)
			(ego illegalBits: 0 posn: 70 133 loop: 2 init:)
			(aDoor setCel: 255)
			(aLightLeft setCel: 0)
			(RoomScript changeState: 13)
		else
			(ego posn: 1 164 init:)
		)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(= currentStatus 15)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 94 139 self
				)
			)
			(2
				(ego
					cycleSpeed: 1
					view: (+ 715 (* 100 playingAsPatti))
					setLoop: 2
					setCycle: EndLoop self
				)
			)
			(3
				(aButton setCel: 1 stopUpd:)
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo loopN)
				(HandsOff)
				((aLightLeft script?) changeState: 3)
			)
			(5
				(= seconds 0)
			)
			(6
				(aLightLeft setScript: 0)
				(soundFX number: 460 loop: 1 play:)
				(aDoor setCycle: EndLoop self)
			)
			(7
				(aDoor stopUpd:)
				(= cycles 10)
			)
			(8
				(ego illegalBits: 0 setMotion: MoveTo 70 135 self)
			)
			(9
				(ego setMotion: MoveTo 72 128 self)
			)
			(10
				(ego setLoop: 2)
				(= cycles 14)
			)
			(11
				(soundFX number: 461 loop: 1 play:)
				(aDoor setCycle: BegLoop)
				(= seconds 3)
			)
			(12
				(music fade:)
				(curRoom newRoom: 470)
			)
			(13
				(ego setMotion: MoveTo 70 154 self)
			)
			(14
				(soundFX number: 461 loop: 1 play:)
				(aDoor setCycle: BegLoop self)
			)
			(15
				(aDoor stopUpd:)
				(NormalEgo)
				(= currentStatus egoNORMAL)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'drag')
					(Said 'get,use/elevator')
					(Said 'board,open/elevator,door')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cBLUE))
						(Print 460 0)
					)
					(else
						(RoomScript changeState: 1)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/palm')
						(Print 460 1)
					)
					((Said '/carpet,carpet')
						(Print 460 2)
					)
					((Said '/elevator,door,burn')
						(Printf 460 3
							(+ 1 (aLightLeft cel?)) (+ 1 (aLightRight cel?))
						)
					)
					(
						(and
							playingAsPatti
							(or (Said '/buffet,man') (Said '//buffet,man'))
						)
						(Print 460 4)
					)
					((Said '[/area,area,hotel]')
						(Printf 460 5
							(if playingAsPatti
								{}
							else
								{A clerk stands bored behind the counter.}
							)
						)
					)
				)
			)
		)
	)
)

(instance ManScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 4 8))
			)
			(1
				(aMan
					illegalBits: 0
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 257 162 self
				)
			)
			(2
				(= seconds (Random 4 8))
			)
			(3
				(aMan setMotion: MoveTo 233 140 self)
			)
			(4
				(self changeState: 0)
			)
			(5
				(aMan setMotion: MoveTo 249 155 self)
			)
			(6
				(aMan cycleSpeed: 4 setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(7
				(aMan setCel: 0)
				(= cycles 2)
			)
			(8
				(cond 
					(register
						(switch register
							(101 (Print 460 12))
							(102 (Print 460 13))
						)
					)
					(playingAsPatti
						(switch manResponse
							(0 (Print 460 14))
							(1 (Print 460 15))
							(2 (Print 460 16))
							(3 (Print 460 17))
						)
						(if (> (++ manResponse) 3)
							(= manResponse 0)
						)
					)
					(else
						(switch manResponse
							(0 (Print 460 18))
							(1 (Print 460 19))
							(2 (Print 460 20))
							(3 (Print 460 21))
						)
						(if (> (++ manResponse) 3)
							(= manResponse 0)
						)
					)
				)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said '/casino,gamble,gambling') (Said '//casino,gamble,gambling'))
				(Print 460 6)
				(ManScript changeState: 5 register: 102)
			)
			((Said 'address')
				(cond 
					((& (ego onControl:) cCYAN)
						(Print 460 7)
					)
					((& (ego onControl:) cGREEN)
						(Print 460 8)
						(ManScript changeState: 5)
					)
					(else
						(NotClose)
					)
				)
			)
			(
				(and
					(not (Said 'look>'))
					(or (Said '/key,penthouse,area') (Said 'enroll') (Said '//key,penthouse,area'))
				)
				(Print 460 9)
				(ManScript changeState: 5 register: 101)
			)
			((Said 'look/man,buffet,man')
				(Print 460 10)
				(Print 460 11 #at -1 144)
			)
		)
	)
)

(instance aButton of Prop
	(properties
		y 117
		x 102
		view 460
		loop 1
	)
)

(instance aLightLeft of Prop
	(properties
		x 69
	)
)

(instance aLightRight of Prop
	(properties
		x 135
	)
)

(instance aDoor of Prop
	(properties
		y 132
		x 69
		view 460
		priority 9
	)
)

(instance atpDoor of PicView
	(properties
		y 132
		x 135
		view 460
		priority 9
	)
)

(instance aMan of Actor
	(properties
		y 140
		x 233
		view 462
		loop 1
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setPri: 4
			setCycle: Walk
			setScript: ManScript
		)
	)
)

(instance LightScript of Script
	;EO: Code tweaked to fix original speed bug, using decompiled NRS script
	(method (changeState newState &tmp theCel)
		(switch (= state newState)
			(0
				(client
					view: 460
					setCel: (Random 2 9)
					setLoop: 2
					cycleSpeed: 2
					;cycleSpeed: (/ machineSpeed 2)
					setPri: 9
					y: 60
				)
				(= seconds 3)
			)
			(1
				(= seconds (Random 4 10))
			)
			(2
				(= theCel (client cel?))
				(while (== (client cel?) theCel)
					(= theCel (Random 1 8))
				)
				(client
					setCycle: CycleTo theCel (if (> theCel (client cel?)) 1 else -1) self
				)
				(= state 0)
			)
			(3
				(if (== (client cel?) 0)
					(client setCel: 0)
					(= state 5)
				)
				(= seconds 3)
			)
			(4
				(client
					cycleSpeed: 2
					;cycleSpeed: (/ machineSpeed 2)
					setCycle: CycleTo 0 -1 self
				)
			)
			(5
				(= seconds 3)
			)
			(6
				(RoomScript changeState: 6)
			)
		)
	)
)
