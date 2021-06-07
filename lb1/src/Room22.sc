;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
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
	Room22 0
)
(synonyms
	(lamp ignite)
)

(local
	local0
	local1
)
(procedure (LowPrint)
	(Print &rest
		#at 160 80
		#font 4
		#width 125
		#mode teJustCenter
		#draw
		#dispose
	)
)

(procedure (HighPrint)
	(Print &rest
		#at 10 80
		#font 4
		#width 125
		#mode teJustCenter
		#draw
		#dispose
	)
)

(instance Room22 of Room
	(properties
		picture 22
	)
	
	(method (init)
		(= south 28)
		(= west 21)
		(= east 23)
		(= horizon 135)
		(= global102 0)
		(super init:)
		(Load FONT 4)
		(addToPics add: statue eachElementDo: #init doit:)
		(self setRegions: 206 setFeatures: statue House)
		(if (== currentAct 1)
			(self setRegions: 381)
		)
		(Thunder number: 17 loop: 0)
		(if howFast
			(strike init:)
			(Treflect init:)
			(Greflect init: setScript: showers)
			(cloud init:)
		)
		(if
			(and
				(or
					(== global114 8)
					(== global115 8)
					(== [global368 3] 1)
					(== [global368 1] 1)
				)
				(== currentAct 3)
				(!= global114 10)
			)
			(Load FONT 41)
			(LoadMany 143 406)
			(LoadMany VIEW 380 389 400 406 414 642)
			(LoadMany SOUND 19 29 94 95 96)
			(CHead setPri: 15 init: hide:)
			(Clarence
				posn: 292 161
				loop: 1
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				setCycle: Walk
				init:
			)
			(Rudy
				posn: 40 161
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				setCycle: Walk
				init:
			)
			(self setScript: fight)
		)
		(switch prevRoomNum
			(16 (ego posn: 161 139))
			(15 (ego posn: 61 139))
			(17 (ego posn: 261 139))
			(21
				(if (> (ego y?) 150)
					(ego posn: 1 162)
				else
					(ego posn: 1 139)
				)
			)
			(23
				(if (> (ego y?) 150)
					(ego posn: 305 162)
				else
					(ego posn: 305 139)
				)
			)
		)
		(ego view: 0 illegalBits: cWHITE init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 22 0)
		)
		(if
			(and
				(< global115 10)
				(not script)
				(== currentAct 3)
				(or (== [global368 3] 1) (== [global368 1] 1))
				(or (< (ego x?) 60) (> (ego x?) 250))
			)
			(= global115 17)
			(CHead setPri: 15 init: hide:)
			(Clarence
				posn: 340 161
				loop: 1
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				setCycle: Walk
				init:
			)
			(Rudy
				posn: -20 161
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				setCycle: Walk
				init:
			)
			(self setScript: fight)
		)
		(switch (ego onControl: FALSE)
			(cBLUE (curRoom newRoom: 16))
			(cGREEN (curRoom newRoom: 15))
			(cCYAN (curRoom newRoom: 17))
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 22 0)
							)
							((Said '/path')
								(Print 22 1)
							)
							((Said '/door,(lamp[<gallery])')
								(Print 22 2)
							)
						)
					)
					((Said 'open/door')
						(NotClose)
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

(instance showers of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (= local0 (Random 0 100)) 50)
					(strike setCycle: Forward)
					(if howFast
						(Treflect setCycle: Forward)
						(Greflect setCycle: Forward)
					)
				else
					(cloud setCycle: Forward)
				)
				(= cycles 7)
			)
			(1
				(if (< local0 50)
					(strike setCycle: EndLoop self)
					(if howFast
						(Treflect setCycle: EndLoop)
						(Greflect setCycle: EndLoop)
					)
				else
					(cloud setCycle: EndLoop self)
				)
			)
			(2 (Thunder loop: 1 play: self))
			(3
				(if (< (Random 1 100) 25)
					(= state -1)
				else
					(= state 2)
				)
				(= seconds 5)
			)
		)
	)
)

(instance fight of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((not global216)
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
					((not (& global118 $0001))
						(|= global118 $0001)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((not (& global118 $0008))
						(|= global118 $0008)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(Rudy setMotion: MoveTo 135 183)
				(Clarence ignoreActors: TRUE setMotion: MoveTo 175 183 self)
				(myMusic number: 19 loop: 1 play:)
			)
			(2
				(Face ego Rudy)
				(Rudy view: 389 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(HighPrint 22 3)
				(Rudy loop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(LowPrint 22 4)
				(Rudy setCycle: 0)
				(Clarence view: 406 loop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(HighPrint 22 5)
				(Clarence setCycle: 0)
				(Rudy loop: 1 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(6
				(cls)
				(Rudy
					setLoop: 3
					setCycle: Walk
					setPri: 13
					setMotion: MoveTo 142 183
				)
				(Clarence
					ignoreActors: TRUE
					setCycle: Walk
					setLoop: 2
					setMotion: MoveTo 165 183 self
				)
			)
			(7
				(Rudy loop: 4 cel: 0 cycleSpeed: 2 setCycle: Forward)
				(Clarence loop: 3 cel: 0 cycleSpeed: 2 setCycle: Forward)
				(= cycles 14)
			)
			(8
				(Clarence loop: 8 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
				(Rudy loop: 6 cel: 0 cycleSpeed: 1 setCycle: EndLoop)
			)
			(9
				(HighPrint 22 6)
				(Rudy setCycle: BegLoop)
				(Clarence setCycle: BegLoop self)
			)
			(10
				(Rudy loop: 5 cel: 0 cycleSpeed: 0 setCycle: EndLoop)
				(Clarence loop: 4 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(11
				(cls)
				(Rudy setPri: -1 setCycle: BegLoop self)
			)
			(12
				(Clarence
					loop: 5
					cel: 0
					x: (+ (Clarence x?) 5)
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(13
				(Clarence
					loop: 6
					cel: 0
					cycleSpeed: 0
					x: (+ (Clarence x?) 5)
					setCycle: Forward
				)
				(Rudy loop: 1 cel: 0 setCycle: Forward)
				(HighPrint 22 7)
				(= seconds 4)
			)
			(14
				(cls)
				(Rudy
					view: 380
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo -20 163
				)
				(Clarence
					loop: 7
					cel: 0
					x: (+ (Clarence x?) 16)
					y: (+ (Clarence y?) 1)
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(15
				(CHead show: setCycle: Forward)
				(LowPrint 22 8)
				(Clarence
					view: 402
					loop: 3
					cel: 0
					cycleSpeed: 0
					setCycle: Forward
				)
				(= seconds 4)
			)
			(16
				(cls)
				(CHead setCycle: 0 dispose:)
				(Clarence
					view: 400
					setLoop: -1
					cycleSpeed: 1
					setCycle: Walk
					setMotion: MoveTo 280 163 self
				)
			)
			(17
				(Clarence
					view: 414
					loop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds 4)
			)
			(18
				(Clarence
					view: 400
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 340 163 self
				)
			)
			(19
				(= global115 12)
				(= global114 10)
				(= [global368 3] 0)
				(= [global368 1] 0)
				(Bset 45)
				(HandsOn)
				(Clarence dispose:)
				(Rudy dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance Clarence of Actor
	(properties
		y 163
		x 340
		view 400
	)
)

(instance CHead of Prop
	(properties
		y 184
		x 191
		z 40
		view 404
		loop 2
	)
)

(instance Rudy of Actor
	(properties
		y 163
		x -20
		view 380
	)
)

(instance strike of Prop
	(properties
		y 37
		x 295
		view 122
		loop 2
		cel 1
	)
)

(instance Treflect of Prop
	(properties
		y 53
		x 299
		view 122
		loop 3
		cel 1
		priority 2
	)
)

(instance Greflect of Prop
	(properties
		y 108
		x 299
		view 122
		loop 4
		cel 1
		priority 7
	)
)

(instance cloud of Prop
	(properties
		y 19
		x 25
		view 122
		loop 5
		cel 1
	)
)

(instance Thunder of Sound)

(instance myMusic of Sound)

(instance statue of RPicView
	(properties
		y 147
		x 165
		view 122
		priority 13
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/monument'))
				(event claimed: TRUE)
				(Print 22 9)
			)
			((Said '/monument>')
				(cond 
					((Said 'get')
						(Print 22 10)
					)
					((Said 'press,move')
						(Print 22 11)
					)
					((Said 'rotate')
						(Print 22 12)
					)
				)
			)
		)
	)
)

(instance House of RFeature
	(properties
		nsTop 6
		nsLeft 39
		nsBottom 96
		nsRight 284
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {house})
		)
	)
)
