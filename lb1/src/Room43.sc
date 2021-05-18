;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include sci.sh)
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
	Room43 0
)
(synonyms
	(room hall)
	(armoire closet)
	(ignite lamp)
	(drawer dresser)
)

(local
	local0
	local1
	local2
)
(instance Room43 of Rm
	(properties
		picture 43
	)
	
	(method (init)
		(super init:)
		(= horizon 100)
		(= south 47)
		(= north 73)
		(LoadMany 132 43 44 71)
		(addToPics
			add: vase cat statueL statueR lamp phone
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures: phone lamp cat statueL statueR vase Dresser1 Dresser2
		)
		(if howFast
			(lampL setPri: 3 setCycle: Fwd init:)
			(lampR setPri: 3 setCycle: Fwd init:)
		else
			(lampL setPri: 3 init: stopUpd:)
			(lampR setPri: 3 init: stopUpd:)
		)
		(Door
			cel: (if (or global153 (== prevRoomNum 73)) 3 else 0)
			init:
			stopUpd:
		)
		(doorR ignoreActors: 1 init: stopUpd:)
		(doorL ignoreActors: 1 init: stopUpd:)
		(wardL
			cel: (if (== prevRoomNum 49) 4 else 0)
			ignoreActors: 1
			setPri: 12
			init:
			stopUpd:
		)
		(wardR
			cel: (if (== prevRoomNum 50) 4 else 0)
			ignoreActors: 1
			setPri: 12
			init:
			stopUpd:
		)
		(switch prevRoomNum
			(49
				(ego illegalBits: -32768 posn: 62 164)
				(wardL setScript: closing)
			)
			(50
				(ego illegalBits: -32768 posn: 259 161)
				(wardR setScript: closing)
			)
			(42
				(ego illegalBits: -32692 posn: 73 132)
			)
			(44
				(ego illegalBits: -32692 posn: 251 132)
			)
			(73
				(ego illegalBits: -32692 posn: 159 107)
				(HandsOn)
				(if (not global153) (= local2 1))
			)
			(47 (ego illegalBits: -32692))
		)
		(if (== global153 0)
			(ego view: 0 setPri: -1 init:)
		else
			(HandsOff)
			(self setScript: leak)
		)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 43 0))
		(cond 
			(
				(and
					(not local1)
					(== prevRoomNum 73)
					(== (Door cel?) 0)
				)
				(= local1 1)
				(Door stopUpd:)
			)
			(local2
				(= local2 0)
				(Door setCycle: Beg)
				(myMusic number: 44 loop: 1 play:)
			)
		)
		(switch (ego onControl: 1)
			(32 (curRoom newRoom: 42))
			(16 (curRoom newRoom: 44))
			(2
				(if (and (not local0) (== (ego loop?) 3))
					(= local0 1)
					(self setScript: myDoor)
				)
			)
		)
		(cond 
			((< (ego x?) 130) (= vertAngle 20))
			((< (ego x?) 190) (= vertAngle 0))
			(else (= vertAngle 160))
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(return
			(if
			(and (== (event type?) evSAID) (Said 'examine>'))
				(cond 
					((Said '[<around,at][/room]') (Print 43 0))
					((Said '/door<hidden') (Print 43 1))
					((Said '/pedestal') (Print 43 2))
					((Said '/curtain') (Print 43 3))
					((or (Said '/dirt') (Said '<down')) (Print 43 4))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if
		(and (== currentAct 6) (& global118 $0004) (!= n 44))
			(Bset 36)
		)
		(if
		(and (or (Btst 38) (Btst 37)) (!= n 42) (!= n 49))
			(Bclr 38)
			(Bclr 37)
		)
		(if
			(and
				(or (== n 44) (== n 73))
				(== global203 1)
				(or (== prevRoomNum 44) (== prevRoomNum 73))
				(== [global368 4] 1)
				(not global125)
			)
			(= [global368 4] 50)
		)
		(if
			(and
				(!= n 73)
				(== gCurRoomNum_3 73)
				(== global201 200)
			)
			(++ global201)
			(= deadGuests (| deadGuests $0020))
		)
		(super newRoom: n)
	)
)

(instance Lcloset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(doorL hide:)
				(doorR hide:)
				(if (& (ego onControl: 0) $0080)
					(if (== global143 0) (Print 43 5))
					(if (ego inRect: 81 162 83 166)
						(= cycles 1)
					else
						(ego setMotion: MoveTo 82 164 self)
					)
				else
					(if (== global144 0) (Print 43 5))
					(if (ego inRect: 240 162 243 166)
						(= cycles 1)
					else
						(ego setMotion: MoveTo 241 164 self)
					)
				)
			)
			(1
				(Face ego client)
				(if (& (ego onControl: 0) $0080)
					(ego ignoreControl: 4)
				else
					(ego ignoreControl: 8)
				)
				(client cycleSpeed: 3 setCycle: End self)
				(myMusic number: 71 loop: 1 play:)
			)
			(2
				(client stopUpd:)
				(if (& (ego onControl: 0) $0080)
					(ego setMotion: MoveTo 40 (ego y?) self)
				else
					(ego setMotion: MoveTo 283 (ego y?) self)
				)
			)
			(3
				(client setScript: 0)
				(if (== (ego loop?) 1)
					(curRoom newRoom: 49)
				else
					(curRoom newRoom: 50)
				)
			)
		)
	)
)

(instance closing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(doorR hide:)
				(doorL hide:)
				(ego
					setMotion: MoveTo (if (== prevRoomNum 49) 82 else 239) (ego y?) self
				)
			)
			(1
				(client setCycle: Beg self)
				(myMusic number: 71 loop: 1 play:)
			)
			(2
				(client stopUpd:)
				(ego illegalBits: -32692)
				(HandsOn)
				(doorR show:)
				(doorL show:)
				(client setScript: 0)
			)
		)
	)
)

(instance leak of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(Door setCycle: Beg self)
				(myMusic number: 44 loop: 1 play:)
			)
			(2 (= seconds 3))
			(3 (Print 43 6) (= cycles 1))
			(4
				(Door setCycle: End self)
				(myMusic number: 43 loop: 1 play:)
			)
			(5
				(curRoom newRoom: 73)
				(client setScript: 0)
			)
		)
	)
)

(instance Lopen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((< (ego x?) 160)
						(if (ego inRect: 80 161 82 163)
							(= cycles 1)
						else
							(ego setMotion: MoveTo 81 162 self)
						)
					)
					((ego inRect: 239 164 241 166) (= cycles 1))
					(else (ego setMotion: MoveTo 240 165 self))
				)
			)
			(1
				(Face ego client)
				(client setCycle: End self)
				(= cycles 7)
			)
			(2 (Print 43 7) (= cycles 1))
			(3 (client setCycle: Beg self))
			(4
				(HandsOn)
				(client stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance myDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0 canInput: 0)
				(ego setMotion: 0 illegalBits: -32768)
				(myMusic number: 43 loop: 1 play:)
				(Door cycleSpeed: 1 ignoreActors: 1 setCycle: End self)
			)
			(1
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 50))
			)
		)
	)
)

(instance vase of RPicView
	(properties
		y 106
		x 189
		view 143
		cel 3
		priority 7
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/vase') (Print 43 8))
			((Said 'examine<in/vase') (Print 43 9))
			(
			(or (MousedOn self event 3) (Said 'examine/vase')) (event claimed: 1) (Print 43 10))
		)
	)
)

(instance cat of RPicView
	(properties
		y 106
		x 129
		view 143
		cel 2
		priority 7
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/cat') (Print 43 11))
			(
			(or (MousedOn self event 3) (Said 'examine/cat')) (event claimed: 1) (Print 43 12))
		)
	)
)

(instance statueL of RPicView
	(properties
		y 71
		x 98
		view 143
		priority 3
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,move/monument') (Print 43 13))
			((Said '(examine<below),behind/monument') (Print 43 14))
			(
			(or (MousedOn self event 3) (Said 'examine/monument')) (event claimed: 1) (Print 43 15))
		)
	)
)

(instance statueR of RPicView
	(properties
		y 71
		x 220
		view 143
		cel 1
		priority 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 43 15)
		)
	)
)

(instance lamp of RPicView
	(properties
		y 189
		x 45
		view 143
		loop 1
		priority 14
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance phone of RPicView
	(properties
		y 189
		x 279
		view 143
		loop 1
		cel 1
		priority 14
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance doorR of Prop
	(properties
		y 161
		x 265
		view 243
		loop 4
		priority 13
	)
)

(instance doorL of Prop
	(properties
		y 161
		x 58
		view 243
		loop 3
		priority 13
	)
)

(instance wardL of Prop
	(properties
		y 158
		x 53
		view 243
		priority 13
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 43 16)
		)
	)
)

(instance wardR of Prop
	(properties
		y 158
		x 270
		view 243
		loop 1
		priority 13
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<behind/armoire')
				(if
					(or
						(ego inRect: 41 151 70 177)
						(ego inRect: 248 151 272 177)
					)
					(Print 43 17)
				else
					(NotClose)
				)
			)
			(
				(or
					(Said 'move,drag,press/armoire')
					(Said 'open<(drag,press)/armoire')
				)
				(cond 
					((& (ego onControl: 0) $0080) (wardL setScript: Lcloset))
					((& (ego onControl: 0) $0100) (wardR setScript: Lcloset))
					(else (NotClose))
				)
			)
			((Said '(examine<below),behind/armoire') (Print 43 18))
			(
				(or
					(Said 'open/door<armoire')
					(Said 'search,open,(examine<in)/armoire')
				)
				(cond 
					((& (ego onControl: 0) $0080) (doorL setScript: Lopen))
					((& (ego onControl: 0) $0100) (doorR setScript: Lopen))
					(else (NotClose))
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/armoire')) (event claimed: 1) (Print 43 16))
		)
	)
)

(instance lampR of Prop
	(properties
		y 72
		x 242
		view 143
		loop 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance lampL of Prop
	(properties
		y 72
		x 80
		view 143
		loop 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance Door of Prop
	(properties
		y 101
		x 142
		view 243
		loop 2
		priority 6
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/door'))
			(event claimed: 1)
			(Print 43 19)
		)
	)
)

(instance Dresser1 of RFeature
	(properties
		nsTop 79
		nsLeft 76
		nsBottom 104
		nsRight 113
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'search,open,(examine<in)/drawer') (Print 43 20))
			(
			(or (MousedOn self event 3) (Said 'examine/drawer')) (event claimed: 1) (Print 43 21))
		)
	)
)

(instance Dresser2 of RFeature
	(properties
		nsTop 79
		nsLeft 189
		nsBottom 104
		nsRight 238
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 43 21)
		)
	)
)

(instance myMusic of Sound
	(properties)
)
