;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Path)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room58 0
)
(synonyms
	(board floorboard)
	(bible book)
	(room chapel)
)

(local
	local0
	local1
	local2
	local3
	[local4 7] = [117 113 232 115 216 92 -32768]
	[local11 7] = [238 115 117 113 115 94 -32768]
	local18
)
(instance Room58 of Rm
	(properties
		picture 58
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 50)
		(LoadMany 132 43 44)
		(addToPics
			add: statueRight statueLeft
			eachElementDo: #init
			doit:
		)
		(Organ loop: -1 priority: -1 play:)
		(self
			setFeatures: Pew1 Pew2 Window1 Window2 Altar StainedGlass
		)
		(if (and (not global121) (>= currentAct 2))
			(if (and (!= currentAct 6) (== (Random 1 2) 1))
				(= global121 58)
			else
				(= global121 69)
			)
		)
		(Door
			cel: (if global133 (- (NumCels Door) 1) else 0)
			ignoreActors: 1
			stopUpd:
			init:
		)
		(if (or (== global155 11) (>= currentAct 2))
			(if howFast
				(flame1 setPri: 7 setCycle: Fwd init:)
				(flame2 setPri: 7 setCycle: Fwd init:)
				(flame3 setPri: 7 setCycle: Fwd init:)
			else
				(flame1 setPri: 7 init: stopUpd:)
				(flame2 setPri: 7 init: stopUpd:)
				(flame3 setPri: 7 init: stopUpd:)
			)
			(lamp cel: 0)
		)
		(lamp init: stopUpd:)
		(board
			cel:
				(if (== global134 2)
					(= local1 8)
					(- (NumCels board) 1)
				else
					(= local1 0)
				)
			setPri: (if (== global134 2) 9 else 2)
			ignoreActors: 1
			init:
		)
		(switch currentAct
			(1
				(LoadMany 130 985 983)
				(LoadMany 128 440 441)
				(if (or (== global155 10) (== global155 11))
					(Jeeves view: 441 illegalBits: 0 init:)
					(HandsOff)
					(Jeeves setScript: lightCandles)
					(Door cel: (- (NumCels Door) 1))
				)
			)
			(6
				(= global121 69)
				(self setRegions: 279)
			)
		)
		(if
			(and
				(== global121 58)
				(not (& deadGuests $0002))
				(not (& deadGuests $0040))
			)
			(= deadGuests (| deadGuests $0002))
			(self setRegions: 256)
		)
		(ego
			view: 0
			x: (if global133 115 else 226)
			y: (if global133 87 else 154)
			illegalBits: (| local1 (<< (^ global133 $0001) $0004) $8000)
			init:
		)
		(if global133 (HandsOff) (self setScript: myDoor))
		(self setFeatures: statueRight statueLeft lamp)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 58 0))
		(if
			(and
				(& (ego onControl: 1) $0004)
				(not local3)
				(not global133)
			)
			(= local3 1)
			(HandsOff)
			(self setScript: myDoor)
		)
		(if (& (ego onControl: 1) $0008)
			(if
			(and (== global134 0) (not local0) (not script))
				(Creak loop: 1 play:)
				(ego setMotion: 0)
				(Wait 60)
				(= local0 1)
				(Print 58 1)
			)
		else
			(= local0 0)
		)
		(if (& (ego onControl: 1) $0002)
			(= global133 0)
			(curRoom newRoom: 9)
		)
		(if
			(and
				(== (ego view?) 24)
				(== (ego loop?) 3)
				(> (ego cel?) 1)
				(< (ego cel?) 4)
			)
			(board cel: (- 3 (ego cel?)))
		)
		(if (and (== [global368 2] 700) (== currentAct 1))
			(if script
				(= [global368 2] 710)
			else
				(= local18 1)
				(HandsOff)
				(self setScript: lightCandles)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(DisposeScript 983)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1 [temp2 5])
		(if (event claimed?) (return))
		(if
			(or
				(& (ego onControl: 0) $0001)
				(& (ego onControl: 0) $0008)
			)
			(= temp1 1)
		else
			(= temp1 0)
		)
		(if (== (event type?) evSAID)
			(cond 
				((or (Said '/board>') (Said '//board>'))
					(= temp0 2)
					(if theInvItem
						(if (not haveInvItem) (return))
						(= temp0 (!= whichItem 7))
					)
					(cond 
						((Said 'examine') (if (== global134 2) (Print 58 2) else (Print 58 3)))
						(
							(and
								(!= temp0 1)
								(or
									(Said '(break,lift,force)[<(open,up)]//crowbar')
									(Said '(break,lift,force)<use<crowbar')
									(Said 'open<use<crowbar')
									(Said 'open//crowbar')
								)
							)
							(if (ego has: 7)
								(cond 
									((== global134 2) (AlreadyOpen))
									(temp1 (self setScript: PryBoard))
									(else (Print 58 4))
								)
							else
								(self setScript: tryBoard)
							)
						)
						(
							(and
								(== temp0 2)
								(Said 'get,open,move,drag,break,lift,force')
							)
							(if (== global134 0)
								(if temp1 (self setScript: tryBoard) else (Print 58 4))
							else
								(AlreadyOpen)
							)
						)
						(
							(or
								(Said '(break,lift,force)[<(open,up)]//poker')
								(Said '(break,lift,force)<use<poker')
								(Said 'open//poker')
							)
							(if (ego has: 6) (Print 58 5))
						)
						(
							(or
								(Said '(break,lift,force)[<(open,up)]//cane')
								(Said '(break,lift,force)<use<cane')
								(Said 'open//cane')
							)
							(if (ego has: 21) (Print 58 6))
						)
						((Said 'press,close')
							(cond 
								((< global134 2) (AlreadyClosed))
								(temp1 (self setScript: CloseBoard))
								(else (Print 58 4))
							)
						)
					)
				)
				((Said '/bible>')
					(cond 
						((!= global134 2) (DontSee) (event claimed: 1))
						((Said 'get,open,read,examine')
							(if (not local2)
								(= local2 1)
								(Bset 14)
								(self setScript: GetBible)
							)
						)
						((Said 'drop,(attach[<(back,down)])') (if local2 (self setScript: CloseBoard) else (DontHave)))
					)
				)
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]') (Print 58 0))
						((Said '/cross') (Print 58 7))
						((Said '/cemetery') (Print 58 8))
						((or (Said '/ceiling') (Said '<up')) (Print 58 9))
						((Said '/wall') (Print 58 10))
						((or (Said '/platform,dirt') (Said '<down')) (Print 58 3))
						((Said '/eyehole')
							(if (== global134 2)
								(if (< (ego distanceTo: board) 40)
									(Print 58 11)
								else
									(NotClose)
								)
							else
								(DontSee)
							)
						)
					)
				)
				((Said 'pray[/altar]') (if temp1 (self setScript: pray) else (Print 58 12)))
			)
		)
	)
	
	(method (newRoom n)
		(if (cast contains: Jeeves) (= global155 11))
		(super newRoom: n)
	)
)

(instance lightCandles of Script
	(properties)
	
	(method (doit)
		(if
			(or
				(& (Jeeves onControl: 0) $0001)
				(& (Jeeves onControl: 0) $0040)
			)
			(Jeeves setPri: -1)
		else
			(Jeeves setPri: 2)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(== [global368 2] 699)
				(if local18
					(Door startUpd: cycleSpeed: 3 setCycle: End self)
					(Jeeves
						view: 440
						setPri: 2
						setCycle: Walk
						posn: 115 86
						init:
					)
				else
					(= cycles 1)
				)
			)
			(1
				(if local18
					(Jeeves
						setPri: -1
						setMotion: PathIn self
						setAvoider: (Avoid new:)
					)
					(if (ego inRect: 203 86 272 117)
						(ego setMotion: MoveTo 245 120)
					)
				else
					(HandsOff)
					(= cycles 1)
				)
			)
			(2
				(Jeeves
					view: 441
					cel: 0
					illegalBits: 0
					setCycle: CT 3 1 self
				)
			)
			(3
				(flame1 setPri: 7 setCycle: Fwd init:)
				(= cycles 17)
			)
			(4
				(Jeeves cel: 4)
				(flame2 setPri: 7 setCycle: Fwd init:)
				(= cycles 17)
			)
			(5
				(Jeeves cel: 5)
				(flame3 setPri: 7 setCycle: Fwd init:)
				(lamp cel: 0 forceUpd:)
				(= cycles 7)
			)
			(6 (Jeeves setCycle: End self))
			(7
				(Jeeves
					view: 440
					setCycle: Walk
					illegalBits: -32768
					setMotion: PathOut self
				)
				(if (ego inRect: 106 77 137 113)
					(ego setMotion: MoveTo 117 120)
				)
			)
			(8
				(if (== (Door cel?) 0)
					(Door startUpd: cycleSpeed: 3 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(9
				(Jeeves setMotion: MoveTo 115 86 self)
			)
			(10
				(Door startUpd: cycleSpeed: 3 setCycle: Beg self)
			)
			(11
				(Door stopUpd:)
				(HandsOn)
				(= global155 12)
				(Jeeves dispose:)
			)
		)
	)
)

(instance myDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if global133
					(= state 4)
					(self cue:)
				else
					(ego setMotion: MoveTo 115 100 self)
				)
			)
			(2
				(ego illegalBits: -32768 loop: 3)
				(Door startUpd: cycleSpeed: 3 setCycle: End self)
				(mySound number: 43 priority: 5 loop: 1 play:)
			)
			(3
				(ego setMotion: MoveTo 115 87 self)
			)
			(4
				(HandsOn)
				(= global133 1)
				(curRoom setScript: 0 newRoom: 9)
			)
			(5
				(ego setMotion: MoveTo 115 100 self)
			)
			(6
				(ego illegalBits: (| local1 $8010))
				(Door startUpd: setCycle: Beg self)
				(mySound number: 44 loop: 1 priority: 5 play:)
			)
			(7
				(= global133 0)
				(if (not (Jeeves script?)) (HandsOn))
				(Door stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance PryBoard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 1)
				(= global134 1)
				(HandsOff)
				(ego
					setAvoider: (Avoid new:)
					setMotion: MoveTo 101 133 self
				)
			)
			(1
				(ego
					view: 50
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(2
				(ego loop: 2 cel: 0 posn: 98 133 setCycle: End self)
			)
			(3
				(ego loop: 4 cel: 0 setCycle: Fwd)
				(= cycles 8)
			)
			(4
				(board setPri: 9 setCycle: End self)
				(= global134 2)
			)
			(5
				(Print 58 2)
				(ego loop: 2)
				(ego
					cel: (- (NumCels ego) 1)
					illegalBits: (| (ego illegalBits?) $0008)
					setCycle: Beg self
				)
			)
			(6
				(ego
					view: 0
					loop: 1
					cycleSpeed: 0
					setAvoider: 0
					setCycle: Walk
				)
				(= cycles 1)
			)
			(7
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance GetBible of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoid new:)
					setMotion: MoveTo 84 138 self
				)
			)
			(1
				(ego view: 17 cel: 0 loop: 1 setCycle: End self)
			)
			(2
				(ego
					view: 24
					cel: 0
					loop: 0
					posn: 83 138
					setAvoider: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(ego cel: 0 loop: 1 cycleSpeed: 2 setCycle: End self)
			)
			(4
				(Print 58 13)
				(Print 58 14 #font 4 #width 270)
				(ego cel: 0 loop: 2 setCycle: Fwd)
				(= cycles 30)
			)
			(5
				(ego loop: 0 setCycle: 0)
				(ego cel: (- (NumCels ego) 1))
				(Print 58 15)
				(Print 58 16 #font 4)
				(User canInput: 1)
				(self setScript: CloseBoard)
				(= cycles 1)
			)
			(6
				(if script
					(= state 5)
					(= cycles 1)
				else
					(client setScript: 0)
				)
			)
		)
	)
)

(instance CloseBoard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not local2)
					(ego
						setAvoider: (Avoid new:)
						setMotion: MoveTo 84 138 self
					)
				else
					(ego loop: 0)
					(ego cel: (- (NumCels ego) 1) setCycle: Beg self)
					(= state 1)
				)
			)
			(1
				(ego view: 17 cel: 0 loop: 1 setCycle: End self)
			)
			(2
				(ego
					view: 24
					cel: 0
					loop: 3
					posn: 83 138
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(if (!= (board cel?) 0)
					(= state 2)
				else
					(ego
						view: 0
						loop: 1
						illegalBits: (& (ego illegalBits?) $fff7)
						setAvoider: 0
						cycleSpeed: 0
						setCycle: Walk
					)
					(board setPri: 2)
				)
				(= cycles 1)
			)
			(4
				(if local2 (Print 58 17) else (Print 58 18))
				(HandsOn)
				(= global134 (= local2 0))
				(client setScript: 0)
			)
		)
	)
)

(instance tryBoard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoid new:)
					setMotion: MoveTo 84 138 self
				)
			)
			(1
				(ego view: 17 cel: 0 loop: 1 setCycle: End self)
			)
			(2
				(ego
					view: 24
					cel: 0
					loop: 0
					posn: 83 138
					setAvoider: 0
					cycleSpeed: 2
					setCycle: CT 3 1 self
				)
			)
			(3
				(Print 58 19 #dispose)
				(ego loop: 4 cycleSpeed: 4 setCycle: Fwd)
				(= cycles 21)
			)
			(4 (ego setCycle: Beg self))
			(5
				(cls)
				(ego
					view: 17
					cel: 3
					loop: 1
					cycleSpeed: 2
					setCycle: Beg self
				)
			)
			(6
				(ego view: 0 loop: 1 cycleSpeed: 0 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance pray of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoid new:)
					setMotion: MoveTo 104 128 self
				)
			)
			(1
				(ego
					view: 59
					cel: 0
					loop: 0
					posn: 103 128
					setAvoider: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(2
				(Print 58 20)
				(ego setCycle: Beg self)
			)
			(3
				(ego view: 0 loop: 1 cycleSpeed: 0 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance statueLeft of RPicView
	(properties
		y 48
		x 147
		view 158
		loop 3
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/monument') (Print 58 21))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/monument,alcove')
				)
				(event claimed: 1)
				(Print 58 22)
			)
		)
	)
)

(instance statueRight of RPicView
	(properties
		y 56
		x 257
		view 158
		loop 3
		cel 1
		priority 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 58 22)
		)
	)
)

(instance lamp of Prop
	(properties
		y 52
		x 203
		view 158
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/candle') (Print 58 23))
			((Said 'ignite/candle') (Print 58 24))
			(
			(or (MousedOn self event 3) (Said 'examine/candle')) (event claimed: 1) (Print 58 25))
		)
	)
)

(instance Door of Prop
	(properties
		y 82
		x 131
		view 158
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/door<back')) (event claimed: 1) (Print 58 26))
			((Said 'examine/door<front') (Print 58 27))
			((Said 'examine/door') (Print 58 28))
		)
	)
)

(instance board of Prop
	(properties
		y 135
		x 53
		view 158
		loop 4
	)
)

(instance flame1 of Prop
	(properties
		y 35
		x 199
		view 158
		loop 2
	)
)

(instance flame2 of Prop
	(properties
		y 34
		x 204
		view 158
		loop 2
		cel 1
	)
)

(instance flame3 of Prop
	(properties
		y 36
		x 209
		view 158
		loop 2
		cel 1
	)
)

(instance Jeeves of Act
	(properties
		y 92
		x 216
		view 441
	)
)

(instance Creak of Sound
	(properties
		number 50
	)
)

(instance Organ of Sound
	(properties
		number 63
	)
)

(instance mySound of Sound
	(properties
		number 43
	)
)

(instance PathIn of Path
	(properties)
	
	(method (at param1)
		(return [local4 param1])
	)
)

(instance PathOut of Path
	(properties)
	
	(method (at param1)
		(return [local11 param1])
	)
)

(instance StainedGlass of RFeature
	(properties
		nsTop 39
		nsLeft 48
		nsBottom 76
		nsRight 89
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/window'))
			(event claimed: 1)
			(Print 58 29)
		)
	)
)

(instance Altar of RFeature
	(properties
		nsTop 94
		nsLeft 65
		nsBottom 124
		nsRight 90
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/altar') (Print 58 30))
			(
			(or (MousedOn self event 3) (Said 'examine/altar')) (event claimed: 1) (Print 58 31))
		)
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 21
		nsLeft 161
		nsBottom 64
		nsRight 189
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'break/window') (Print 58 32))
			((Said 'open/window') (Print 58 33))
			((Said 'examine<(out,through)/window') (Print 58 34))
			((MousedOn self event 3) (event claimed: 1) (Print 58 35))
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 24
		nsLeft 219
		nsBottom 65
		nsRight 245
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 58 35)
		)
	)
)

(instance Pew1 of RFeature
	(properties
		nsTop 78
		nsLeft 138
		nsBottom 108
		nsRight 214
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'sit[/down,bench]') (Print 58 36))
			((Said 'examine<below/bench') (Print 58 37))
			(
			(or (MousedOn self event 3) (Said 'examine/bench')) (event claimed: 1) (Print 58 38))
		)
	)
)

(instance Pew2 of RFeature
	(properties
		nsTop 124
		nsLeft 115
		nsBottom 174
		nsRight 214
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 58 38)
		)
	)
)
