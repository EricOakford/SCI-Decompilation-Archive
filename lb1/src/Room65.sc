;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
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
	Room65 0
)
(synonyms
	(monument monument)
	(room garden)
)

(local
	local0
	valveCued
)
(procedure (TurnValve)
	(if (& (ego onControl: FALSE) cGREEN)
		(= local0 1)
		(Room65 setScript: bend)
	else
		(NotClose)
	)
)

(procedure (localproc_13c8)
	(Bset 48)
	(= global146 1)
	(Drop1
		loop: 7
		x: 161
		y: 125
		z: 2
		setCycle: Forward
		setScript: 0
	)
	(if howFast
		(water1 cycleSpeed: 1 setCycle: Forward)
		(water2 cycleSpeed: 1 setCycle: Forward)
	)
)

(procedure (localproc_141b)
	(Bclr 48)
	(= global146 0)
	(Drop1 loop: 9 cel: 0 x: 152 y: 125 z: 2)
	(if howFast
		(Drop1 cycleSpeed: 1 setScript: dripping)
		(water1 setPri: 3 cycleSpeed: 4)
		(water2 setPri: 3 cycleSpeed: 4)
	)
)

(instance Room65 of Room
	(properties
		picture 65
	)
	
	(method (init)
		(= south 18)
		(super init:)
		(cSound stop:)
		(LoadMany SOUND 78 125)
		(fountain init: stopUpd:)
		(addToPics add: urn urn1 eachElementDo: #init doit:)
		(self setFeatures: urn urn1)
		(Splash1 ignoreActors: 1 init: hide:)
		(Splash2 ignoreActors: 1 init: hide:)
		(Drop1 init:)
		(water1 init:)
		(water2 init:)
		(if (Btst 48)
			(localproc_13c8)
		else
			(localproc_141b)
		)
		(Trap ignoreActors: TRUE init:)
		(if ((inventory at: 13) ownedBy: 65)
			(shaft setPri: 8 ignoreActors: TRUE init: stopUpd:)
		)
		(statue setPri: 7 ignoreActors: TRUE init: stopUpd:)
		(ego view: 0 illegalBits: cWHITE init:)
		(if (== prevRoomNum 51)
			(ego posn: 273 138)
		else
			(ego posn: 50 186)
		)
		(if global147
			(Trap cel: (- (NumCels Trap) 1) setPri: 9 stopUpd:)
			(statue cel: 2)
			(ego observeControl: cBLUE cBROWN)
		else
			(Trap setPri: 4)
		)
		(if (== currentAct 7)
			(self setRegions: 280)
			(ego observeControl: cGREY)
		)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 65 0)
		)
		(if
			(and
				global147
				(& (ego onControl: origin) cCYAN)
				(not script)
			)
			(ego ignoreControl: cBROWN)
			(self setScript: goDown)
		)
		(if
			(and
				(& (ego onControl: origin) cRED)
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
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 65 0)
							)
							((Said '/archway')
								(Print 65 1)
							)
							((Said '/water')
								(Print 65 2)
							)
							((Said '/bush,bush')
								(Print 65 3)
							)
							((Said '/stair')
								(if global147
									(Print 65 4)
								else
									(Print 65 5)
								)
							)
							((Said '/passage')
								(if global147
									(Print 65 6)
								else
									(Print 65 5)
								)
							)
							((Said '/shaft')
								(if (ego inRect: 205 111 276 200)
									(if (== (inventory at: iValveHandle) 65)
										(Print 65 7)
									else
										(Print 65 8)
									)
								else
									(NotClose)
								)
							)
						)
					)
					((Said 'attach,attach,attach/control/shaft')
						(if (ego has: iCrank)
							(Print 65 9)
						else
							(DontHave)
						)
					)
					((Said 'attach,attach/valve,handle/shaft')
						(if (ego has: iValveHandle)
							(if (& (ego onControl: FALSE) cGREEN)
								(= valveCued TRUE)
								(self setScript: bend)
								((inventory at: iValveHandle) moveTo: curRoomNum)
							else
								(NotClose)
							)
						else
							(DontHave)
						)
					)
					((Said 'attach,attach/control/shaft')
						(if (ego has: iCrank)
							(if (& (ego onControl: FALSE) cGREEN)
								(Print 65 10)
							else
								(NotClose)
							)
						else
							(DontHave)
						)
					)
					((and (ego has: iValveHandle) (Said 'rotate,rotate/valve,handle'))
						(Print 65 11)
					)
					((Said 'rotate,rotate/valve,handle')
						(if (== ((inventory at: iValveHandle) owner?) 65)
							(TurnValve)
						else
							(DontHave)
						)
					)
					(
						(or
							(Said 'rotate<on/fountain')
							(Said 'rotate/fountain<on')
						)
						(cond 
							((Btst 48)
								(Print 65 12)
							)
							((== ((inventory at: iValveHandle) owner?) 65)
								(TurnValve)
							)
							(else
								(Print 65 13)
							)
						)
					)
					(
						(or
							(Said 'rotate<off/fountain')
							(Said 'rotate/fountain<off')
						)
						(cond 
							((not (Btst 48))
								(Print 65 14)
							)
							((== ((inventory at: iValveHandle) owner?) 65)
								(TurnValve)
							)
							(else
								(Print 65 13)
							)
						)
					)
					((Said 'feel,(attach<deliver)/fountain,water')
						(if (ego inRect: 101 99 241 165)
							(Print 65 15)
						else
							(NotClose)
						)
					)
					((Said 'move,press,drag,rotate/fountain')
						(Print 65 16)
					)
					((Said 'close/archway')
						(Print 65 17)
					)
					((Said 'open/archway')
						(Print 65 18)
					)
					((Said 'enter,go,(get<in),wade,climb/water,fountain')
						(Print 65 19)
					)
					((Said 'get/water')
						(Print 65 20)
					)
					((Said 'get/shaft')
						(if (& (ego onControl: FALSE) cGREEN)
							(Print 65 21)
						else
							(NotClose)
						)
					)
					((Said 'press,move,drag/shaft,square')
						(if (& (ego onControl: FALSE) cGREEN)
							(Print 65 22)
						else
							(NotClose)
						)
					)
					((Said 'rotate,rotate,spin,rotate/shaft')
						(if (& (ego onControl: FALSE) cGREEN)
							(Print 65 23)
						else
							(NotClose)
						)
					)
					((Said 'get,detach/valve,handle')
						(cond 
							((== ((inventory at: iValveHandle) owner?) 65)
								(Print 65 24)
							)
							((ego has: iValveHandle)
								(Print 65 25)
							)
							(else
								(Print 65 26)
							)
						)
					)
					((or (Said 'drink/water,fountain') (Said 'get/drink'))
						(Print 65 27)
					)
					((Said 'oil/shaft')
						(if (ego has: iOilcan)
							(Print 65 28)
						else
							(Print 65 29)
						)
					)
					((Said 'force/shaft')
						(if (ego has: iCrowbar)
							(Print 65 30)
						else
							(Print 65 31)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 51)
			(cSound stop:)
		)
		(super newRoom: n)
	)
)

(instance goDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 260 133 self)
			)
			(1
				(curRoom newRoom: 51)
			)
		)
	)
)

(instance dripping of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Drop1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(water1 cel: 0 setCycle: EndLoop)
				(water2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(if (not (Btst 48))
					(= state -1)
					(= seconds 2)
				else
					(client setScript: 0)
				)
			)
		)
	)
)

(instance bend of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 250 116 self)
			)
			(1
				(ego view: 165 cel: 0 loop: 6)
				(= seconds 2)
			)
			(2
				(if local0
					(= local0 0)
					(shaft hide:)
					(ego setCycle: EndLoop self)
					(myMusic number: 125 loop: 1 play:)
					(if (not global146)
						(Print 65 32)
						(localproc_13c8)
					else
						(Print 65 33)
						(localproc_141b)
					)
				)
				(if valveCued
					(Print 65 34)
					(shaft setPri: 8 ignoreActors: TRUE init: stopUpd:)
					(= valveCued 0)
					(= cycles 1)
				)
			)
			(3
				(shaft show:)
				(ego view: 0 illegalBits: cWHITE loop: 1 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance myDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if global147
					(ego
						illegalBits: 0
						setAvoider: (Avoider new:)
						setMotion: MoveTo 237 91 self
					)
				else
					(ego
						illegalBits: 0
						setAvoider: (Avoider new:)
						setMotion: MoveTo 202 90 self
					)
				)
			)
			(1
				(ego loop: 2 hide:)
				(if global147
					(statue loop: 1 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
					(Trap setPri: 9 cycleSpeed: 1 setCycle: EndLoop)
				else
					(statue loop: 8 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
					(Trap cycleSpeed: 1 setCycle: BegLoop)
				)
				(myMusic number: 78 loop: 1 play:)
			)
			(2
				(if global147
					(statue loop: 2 cel: 2 stopUpd:)
				else
					(statue loop: 2 cel: 1 stopUpd:)
				)
				(ego
					show:
					view: 165
					loop: 5
					cel: 0
					illegalBits: cWHITE
					setCycle: EndLoop self
				)
			)
			(3
				(if global147
					(Print 65 35)
					(ego
						view: 0
						loop: 2
						setCycle: Walk
						setAvoider: 0
						observeControl: cBLUE cBROWN
					)
					(= global147 1)
				else
					(ego
						view: 0
						loop: 2
						setCycle: Walk
						setAvoider: 0
						ignoreControl: cBLUE
					)
					(Trap cel: 0 setPri: 4 ignoreActors: TRUE init: stopUpd:)
					(= global147 0)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance Trap of Prop
	(properties
		y 128
		x 282
		view 165
		cel 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'close/trapdoor')
				(if global147
					(Print 65 36)
				else
					(Print 65 37)
				)
			)
			((Said 'open/trapdoor')
				(if global147
					(AlreadyOpen)
				else
					(Print 65 37)
				)
			)
			((Said 'examine<(in,down)/trapdoor')
				(if global147
					(if (< (ego distanceTo: Trap) 60)
						(Print 65 38)
					else
						(NotClose)
					)
				else
					(Print 65 5)
				)
			)
			((Said 'examine<down')
				(if (and global147 (< (ego distanceTo: Trap) 60))
					(Print 65 38)
				else
					(event claimed: FALSE)
				)
			)
			(
				(or
					(Said 'examine,find/trapdoor')
					(MousedOn self event shiftDown)
				)
				(event claimed: TRUE)
				(if global147
					(Print 65 39)
				else
					(Print 65 5)
				)
			)
		)
	)
)

(instance statue of Prop
	(properties
		y 78
		x 224
		view 165
		loop 2
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'lift,get/monument')
				(Print 65 40)
			)
			((Said 'move,press,drag,rotate,rotate/monument')
				(if (& (ego onControl: FALSE) cMAGENTA)
					(if (== global146 1)
						(if (not global147)
							(= global147 1)
						else
							(= global147 0)
						)
						(statue setScript: myDoor)
					else
						(Print 65 41)
					)
				else
					(NotClose)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/base,monument,monument')
				)
				(event claimed: TRUE)
				(if (ego inRect: 205 111 276 200)
					(if (== ((inventory at: iCrank) owner?) curRoomNum)
						(Printf 65 42 65 7)
					else
						(Printf 65 42 65 43)
					)
				else
					(Printf 65 42 65 44)
				)
			)
		)
	)
)

(instance urn of RPicView
	(properties
		y 46
		x 98
		view 165
		loop 2
		priority 3
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/urn')
				(Print 65 45)
			)
			((Said 'move/urn')
				(Print 65 46)
			)
			((Said 'examine<in/urn')
				(cond 
					((< (ego distanceTo: urn) 30)
						(Print 65 47)
					)
					((< (ego distanceTo: urn1) 25)
						(Print 65 47)
					)
					(else
						(NotClose)
					)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/urn,pedestal')
				)
				(event claimed: TRUE)
				(Print 65 48)
			)
		)
	)
)

(instance urn1 of RPicView
	(properties
		y 153
		x 286
		view 165
		loop 2
		priority 14
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 65 48)
		)
	)
)

(instance fountain of Prop
	(properties
		y 124
		x 161
		view 165
		loop 2
		cel 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<in/fountain,water')
				(if (ego inRect: 75 89 251 166)
					(Print 65 49)
				else
					(NotClose)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/fountain'))
				(Print 65 50)
				(event claimed: TRUE)
			)
		)
	)
)

(instance Drop1 of Prop
	(properties
		y 125
		x 152
		z 2
		view 165
		loop 9
	)
)

(instance water1 of Prop
	(properties
		y 132
		x 162
		view 165
		loop 3
	)
)

(instance water2 of Prop
	(properties
		y 132
		x 162
		view 165
		loop 4
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

(instance shaft of Prop
	(properties
		y 95
		x 243
		view 165
		loop 2
		cel 3
	)
)

(instance myMusic of Sound)
