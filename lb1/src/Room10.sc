;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
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
	Room10 0
)

(local
	local0
	local1
	local2
	local3
)
(instance Room10 of Rm
	(properties
		picture 10
	)
	
	(method (init)
		(= south 21)
		(= east 15)
		(= north 3)
		(super init:)
		(= local2 (FirstEntry))
		(self
			setRegions: 206
			setFeatures: Window1 Window2 Window3
		)
		(LoadMany 132 43 44)
		(Door
			cel: (if (== prevRoomNum 31) 3 else 0)
			setPri: 6
			ignoreActors: 1
			init:
			stopUpd:
		)
		(= gDoor Door)
		(if howFast
			(Splash1 ignoreActors: 1 init: hide:)
			(Splash2 ignoreActors: 1 init: hide:)
		)
		(switch prevRoomNum
			(4 (ego posn: 56 50))
			(3 (ego posn: 1 68))
			(31
				(ego posn: 154 121)
				(if (not (FirstEntry)) (ego setScript: comeDown))
			)
			(15 (ego posn: 317 181))
			(21 (ego posn: 160 185))
		)
		(ego view: 0 illegalBits: -32766 init:)
		(if (== currentAct 1) (self setRegions: 381))
		(if (== currentAct 5) (self setRegions: 277))
		(if
			(and
				(>= currentAct 2)
				(< currentAct 4)
				(!= global114 0)
			)
			(self setRegions: 202)
		)
		(if
			(or
				(and (== currentAct 3) (== global114 0))
				(and (== currentAct 6) (not (& global118 $0002)))
			)
			(self setRegions: 281)
		)
		(if (and (== currentAct 1) (== global154 0))
			(= [global368 0] 1800)
			(= local3 1)
			(LoadMany 128 400 420 404)
			(Clarence init:)
			(CHead setPri: 10 init:)
			(Wilbur init:)
			(WHead setPri: 10 init:)
			(self setScript: Argue)
		)
	)
	
	(method (doit)
		(if local2
			(= local2 0)
			(Print 10 0)
			(if (== prevRoomNum 31) (ego setScript: comeDown))
		)
		(if (& (ego onControl: 0) $0004)
			(if (and (== currentAct 1) (== global154 14))
				(= global154 2)
			)
			(curRoom newRoom: 4)
		)
		(if (& (ego onControl: 1) $0002) (curRoom newRoom: 31))
		(if
			(and
				(& (ego onControl: 1) $0020)
				(or (== (ego loop?) 0) (== (ego loop?) 3))
				(== local0 0)
			)
			(= local0 1)
			(self setScript: myDoor)
		)
		(if (== (ego edgeHit?) 4)
			(if (and (== currentAct 1) (== global154 14))
				(= global154 2)
			)
			(if (< (ego y?) 80)
				(curRoom newRoom: 3)
			else
				(curRoom newRoom: 14)
			)
		)
		(if
			(and
				(& (ego onControl: 1) $4000)
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
							setCycle: End
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: End
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: End
						)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room<!*]') (Print 10 0))
							((Said '/fence') (Print 10 1))
							((Said '/path') (Print 10 2))
							((Said '/stair') (Print 10 3))
							((Said '/up') (Print 10 4))
						)
					)
					((Said 'climb/fence') (Print 10 5))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(cond 
			((== n 31) (cSound stop:) (ego setLoop: -1))
			((and (== currentAct 1) (< global154 3)) (= global154 3))
		)
		(super newRoom: n)
	)
)

(instance Argue of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(== prevRoomNum 31)
						(or (!= (ego x?) 118) (!= (ego y?) 144))
					)
					(= state -1)
				)
				(= cycles 1)
			)
			(1
				(User canControl: 0 canInput: 0)
				(cond 
					((not (& (ego onControl: 1) $0001)) (= state 0))
					((not global216) (= state 0))
					((not (& global118 $0001))
						(= global118 (| global118 $0001))
						(self setScript: (ScriptID 406 0))
						(= state 0)
					)
					((self script?) (= state 0))
				)
				(= cycles 3)
			)
			(2
				(Wilbur loop: 0 cel: 0 setCycle: End self)
			)
			(3
				(Wilbur loop: 2 setCycle: Fwd)
				(WHead setCycle: Fwd)
				(Print 10 6 #at 25 145 #dispose)
				(= seconds 5)
			)
			(4
				(Wilbur loop: 0 cel: 3 setCycle: Beg)
				(Clarence loop: 1 setCycle: End self)
				(WHead setCycle: 0)
			)
			(5
				(cls)
				(Wilbur setCycle: 0)
				(Clarence loop: 3 setCycle: Fwd)
				(CHead setCycle: Fwd)
				(Print 10 7 #at 55 145 #dispose)
				(= seconds 2)
			)
			(6
				(cls)
				(Print 10 8 #at 25 145 #dispose)
				(Clarence loop: 1 cel: 2 setCycle: Beg)
				(= seconds 1)
			)
			(7
				(CHead hide:)
				(WHead hide:)
				(Clarence
					view: 400
					setCycle: Walk
					setMotion: MoveTo 135 135 self
				)
				(Wilbur
					view: 420
					setCycle: Walk
					setMotion: MoveTo 135 135
				)
			)
			(8
				(cls)
				(Clarence setMotion: MoveTo 150 122 self)
				(Wilbur setMotion: MoveTo 150 123 self)
			)
			(9
				(= global154 1)
				(Door setCycle: End self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(10
				(Clarence illegalBits: 0 setMotion: MoveTo 190 117)
				(Wilbur illegalBits: 0 setMotion: MoveTo 177 119 self)
			)
			(11
				(Door setCycle: Beg self)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(12
				(Door stopUpd:)
				(= cycles 1)
			)
			(13 (HandsOn) (= seconds 20))
			(14
				(== global154 3)
				(client setScript: 0)
			)
		)
	)
)

(instance comeDown of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 3))
			(1
				(ego setMotion: MoveTo 118 144 self)
				(Door cycleSpeed: 1 setCycle: Beg)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(2
				(if (not local3) (HandsOn))
				(if (== gCurRoomNum_2 curRoomNum)
					(if (!= currentAct 3) (Door stopUpd:))
				else
					(Door stopUpd:)
				)
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
				(ego setLoop: 0 setMotion: MoveTo 135 134 self)
			)
			(1
				(ego setMotion: MoveTo 146 120 self)
			)
			(2
				(ego setMotion: 0 illegalBits: -32768)
				(Door cycleSpeed: 1 ignoreActors: 1 setCycle: End self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(3
				(ego
					ignoreActors: 1
					setMotion: MoveTo (+ (ego x?) 50) 121
				)
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 123
		x 168
		view 117
		loop 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {door})
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
		nsTop 31
		nsLeft 98
		nsBottom 96
		nsRight 120
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 56
		nsLeft 195
		nsBottom 138
		nsRight 219
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Window3 of RFeature
	(properties
		nsTop 59
		nsLeft 269
		nsBottom 138
		nsRight 295
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Wilbur of Act
	(properties
		y 142
		x 64
		view 425
	)
)

(instance Clarence of Act
	(properties
		y 142
		x 97
		view 402
		loop 1
	)
)

(instance CHead of Prop
	(properties
		y 102
		x 95
		view 404
		loop 2
	)
)

(instance WHead of Prop
	(properties
		y 103
		x 62
		view 404
		loop 4
	)
)

(instance myMusic of Sound
	(properties)
)
