;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use OccCyc)
(use sBurnBabyBurn)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm260 0
)

(local
	local0
	gTheObj_2Y
	local2
	local3
	theTimeZone =  8
	local5 =  1
	gTheObj_2CycleSpeed
)
(instance rm260 of GloryRm
	(properties
		picture 260
		horizon 103
		north 250
		east 280
		south 270
	)
	
	(method (init)
		(= local2
			(cond 
				(
					(and
						(not
							(if (or (Btst 37) (Btst 41) (Btst 38) (Btst 39))
							else
								(Btst 48)
							)
						)
						(not (Btst 30))
					)
					1
				)
				(
					(and
						(not (Btst 37))
						(not (Btst 48))
						(Btst 30)
						(Btst 47)
						(>= Day 5)
						(!= prevRoomNum 250)
					)
					4
				)
				(
					(and
						(not
							(if
								(or
									(Btst 45)
									(Btst 37)
									(Btst 41)
									(Btst 38)
									(Btst 39)
								)
							else
								(Btst 48)
							)
						)
						(Btst 30)
						(not (Btst 47))
					)
					2
				)
				(
					(and
						(not
							(if
								(or
									(Btst 45)
									(Btst 37)
									(Btst 41)
									(Btst 38)
									(Btst 39)
								)
							else
								(Btst 48)
							)
						)
						(Btst 30)
						(Btst 47)
					)
					3
				)
				((and (Btst 38) (not (Btst 39))) 5)
				(
				(and (or (Btst 48) (Btst 39)) (not (Btst 40))) 6)
				((and (Btst 37) (not (Btst 40)) (>= Day 5)) 7)
				((and (Btst 41) (not (Btst 42))) 8)
				((and (Btst 43) (not (Btst 44))) 9)
				((and (Btst 45) (not (Btst 49))) 10)
				((Btst 40) 11)
				((and (Btst 30) (Btst 47)) 3)
				(else 3)
			)
		)
		(if (not (OneOf prevRoomNum 250 280))
			(theMusic number: 250 setLoop: -1 play:)
		)
		(ego init: normalize:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 189 0 84 9 84 9 177 63 177 63 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						319
						158
						252
						158
						252
						149
						287
						149
						287
						146
						223
						146
						223
						141
						215
						141
						215
						145
						192
						145
						180
						138
						180
						129
						97
						127
						97
						120
						78
						120
						78
						117
						44
						117
						44
						107
						17
						98
						17
						0
						319
						0
					yourself:
				)
		)
		(self
			addPoly:
				((Polygon new:) init: 0 98 31 98 58 111 0 111 yourself:)
				50
				((Polygon new:)
					init: 146 139 239 178 132 187 12 122
					yourself:
				)
				120
		)
		(if (and (OneOf local2 4 7) (<= timeODay 3))
			(hans init: approachVerbs: 4 2)
			(franz init: approachVerbs: 4 2)
			(ivan init: approachVerbs: 4 2 setScript: sGroupTalk)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 191 170 151 170 151 166 85 166 85 153 178 153
						yourself:
					)
			)
		)
		(if
		(and (>= timeODay 4) (not (cast contains: hans)))
			(Bclr 266)
			(closedWin init: approachVerbs: 4 80 42 28)
		else
			(Bset 266)
			(burgoWin init: approachVerbs: 4)
		)
		(if (== local2 5)
			(proc87_0)
		else
			(barrel init: approachVerbs: 4 24)
			(innSign init: approachVerbs: 4)
			(storeSign init: approachVerbs: 4)
			(burgoSign init: approachVerbs: 4)
			(innWindow init:)
			(storeWindow init: approachVerbs: 4)
			(innHighWin init: approachVerbs: 4)
			(burgoHighWin1 init: approachVerbs: 4)
			(burgoHighWin2 init: approachVerbs: 4)
			(archWay init:)
			(tree1 init:)
			(tree2 init:)
		)
		(if Night (lamp1 init:) (lamp2 init:))
		(innDoor init: approachVerbs: 4 42 28)
		(storeDoor init: approachVerbs: 4 42 28)
		(burgoDoor init: approachVerbs: 4 42 28)
		(if
			(or
				(and (<= 0 timeODay) (<= timeODay 3) (not (Btst 32)))
				(and (<= timeODay 3) (Btst 38) (Btst 39))
				(cast contains: hans)
			)
			(burgoMeister
				init:
				approachVerbs: 4 2
				setScript: sBurgoGibber
			)
		)
		(((ScriptID 6 0) new:)
			x: 28
			y: 49
			nsLeft: 0
			nsTop: 1
			nsBottom: 98
			nsRight: 56
			approachX: 15
			approachY: (curRoom horizon?)
			init:
		)
		(switch prevRoomNum
			(250
				(ego posn: 18 102 setScaler: Scaler 122 50 189 87)
				(= local0 34)
				(= gTheObj_2Y 126)
			)
			(270
				(ego posn: 265 240 setScaler: Scaler 122 50 189 87)
				(= local0 224)
				(= gTheObj_2Y 181)
			)
			(280
				(ego setScaler: Scaler 122 50 189 87 x: 312 y: 180)
				(= local0 (- (ego x?) 20))
				(= gTheObj_2Y (ego y?))
			)
			(300
				(ego setScaler: Scaler 122 50 189 87)
				(if (Btst 70)
					(closedWin hide:)
					(ego view: 352 loop: 0 cel: 10 x: 271 y: 148)
				else
					(ego loop: 2 posn: 222 136 setPri: -1)
					(burgoDoor cel: 8)
				)
			)
			(310
				(ego
					loop: 2
					posn: 180 119
					setScaler: Scaler 122 50 189 87
					setPri: 0
				)
			)
			(320
				(ego
					loop: 2
					posn: 76 111
					setScaler: Scaler 122 50 189 87
					setPri: 0
				)
			)
			(330
				(if (Btst 70)
					(ego loop: 2 posn: 77 132 setScaler: Scaler 122 50 189 87)
				else
					(= gTheObj_2CycleSpeed (ego cycleSpeed?))
					(ego
						view: 7
						setLoop: 3 1
						cel: 0
						posn: 100 107
						setScaler: Scaler 122 72 189 122
						setSpeed: 15
						setCycle: Walk
					)
				)
			)
			(else 
				(ego setScaler: Scaler 122 50 189 87 posn: 160 160)
			)
		)
		(if (cast contains: burgoMeister)
			(heroTeller
				init:
					ego
					260
					18
					128
					(switch local2
						(1 22)
						(4 33)
						(2 23)
						(3 24)
						(6 26)
						(7 32)
						(8 28)
						(9 29)
						(10 30)
						(11 31)
					)
			)
		)
		(super init: &rest)
		(cond 
			((OneOf prevRoomNum 250 270 280) (self setScript: sEnterScr))
			((== prevRoomNum 300)
				(if (Btst 70)
					(ego setScript: sOutBurgoWin)
				else
					(ego setScript: sOutBurgoDoor)
				)
			)
			((== prevRoomNum 310) (ego setScript: sOutStoreDoor))
			((== prevRoomNum 320) (ego setScript: sOutInnDoor))
			((== prevRoomNum 330)
				(if (Btst 70)
					(Bclr 70)
					(theGame handsOn:)
				else
					(ego setScript: sClimbDownInnWin)
				)
			)
			(else (theGame handsOn:))
		)
	)
	
	(method (doit)
		(if
			(and
				(!= theTimeZone timeODay)
				(not (curRoom script?))
				local5
			)
			(cond 
				((<= timeODay 3)
					(if (OneOf theTimeZone 7 8)
						(if (innDoor actions?)
							((innDoor actions?) dispose:)
							(innDoor actions: 0)
							(storeDoor actions: 0)
							(burgoDoor actions: 0)
						)
						(if (innDoor heading?)
							((innDoor heading?) dispose:)
							(innDoor heading: 0)
						)
						(if (burgoDoor heading?)
							((burgoDoor heading?) dispose:)
							(burgoDoor heading: 0)
						)
						(if (storeDoor heading?)
							((storeDoor heading?) dispose:)
							(storeDoor heading: 0)
						)
						(if
						(and (not (cast contains: hans)) (== theTimeZone 7))
							(curRoom setScript: sOpenWindow)
						)
						(innDoor
							heading:
								(((ScriptID 49 0) new:)
									init:
										((Polygon new:)
											type: 1
											init: 62 114 93 116 80 124 56 120
											yourself:
										)
										7
										3
										6
										sInInnDoor
									yourself:
								)
						)
						(burgoDoor
							heading:
								(((ScriptID 49 0) new:)
									init:
										((Polygon new:)
											type: 1
											init: 207 139 235 139 233 146 205 144
											yourself:
										)
										7
										3
										6
										sInBurgoDoor
									yourself:
								)
						)
						(storeDoor
							heading:
								(((ScriptID 49 0) new:)
									init:
										((Polygon new:)
											type: 1
											init: 165 127 187 127 177 137 161 133
											yourself:
										)
										7
										3
										6
										sInStoreDoor
									yourself:
								)
						)
					)
				)
				((<= timeODay 5)
					(if (OneOf theTimeZone 3 8)
						(if (innDoor heading?)
							((innDoor heading?) dispose:)
							(innDoor heading: 0)
							(storeDoor actions: 0)
							(burgoDoor actions: 0)
						)
						(if (burgoDoor heading?)
							((burgoDoor heading?) dispose:)
							(burgoDoor heading: 0)
						)
						(if (storeDoor heading?)
							((storeDoor heading?) dispose:)
							(storeDoor heading: 0)
						)
						(if (innDoor actions?)
							((innDoor actions?) dispose:)
							(innDoor actions: 0)
						)
						(if
						(and (not (cast contains: hans)) (== theTimeZone 3))
							(curRoom setScript: sCloseWindow)
						)
						(innDoorTeller init: innDoor 260 18 129 17)
					)
				)
				((and (<= timeODay 7) (OneOf theTimeZone 5 8))
					(if (innDoor actions?)
						((innDoor actions?) dispose:)
						(innDoor actions: 0)
					)
					(innDoorTeller init: innDoor 260 18 129 19)
					(burgoDoor actions: innDoorTeller)
					(storeDoor actions: innDoorTeller)
				)
			)
			(= theTimeZone timeODay)
		)
		(if
			(and
				(== (curRoom script?) (ScriptID 31 1))
				(>= (ego z?) 40)
			)
			(curRoom setScript: sLevitateIntoInn)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (innDoor heading?) ((innDoor heading?) dispose:))
		(if (burgoDoor heading?)
			((burgoDoor heading?) dispose:)
		)
		(if (storeDoor heading?)
			((storeDoor heading?) dispose:)
		)
		(if (innDoor actions?) ((innDoor actions?) dispose:))
		(if (burgoDoor actions?)
			((burgoDoor actions?) dispose:)
		)
		(if (storeDoor actions?)
			((storeDoor actions?) dispose:)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(89
				(if
				(and (>= timeODay 4) (not (cast contains: hans)))
					((ScriptID 31 0) init: 69 123 83 0 2)
				else
					(messager say: 16 6 65)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (newRoom n)
		(if (OneOf n 320 310 300 270) (theMusic fade: 0))
		(if
			(and
				(OneOf n 250 270 280 310 320 330)
				(not (== timeODay 0))
				(not (== theTimeZone 7))
				(not (>= timeODay 6))
			)
			(Bset 32)
		)
		(super newRoom: n 1 &rest)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo local0 gTheObj_2Y self)
			)
			(1
				(if (and (<= timeODay 3) (Btst 38) (Btst 39))
					(curRoom setScript: sHeroGuilty)
				else
					(= cycles 1)
				)
			)
			(2
				(if
					(and
						(cast contains: burgoMeister)
						(Btst 115)
						(not (Btst 311))
					)
					(Bset 311)
					(messager say: 11 6 118 0 self)
				else
					(= ticks 1)
				)
			)
			(3
				(if (cast contains: burgoMeister)
					(switch local2
						(1
							(Bset 30)
							(messager say: 11 6 21 0 self)
						)
						(2
							(Bset 47)
							(messager say: 11 6 22 0 self)
						)
						(3
							(messager say: 11 6 24 0 self)
						)
						(4
							(Bset 37)
							(messager say: 11 6 25 0 self)
						)
						(5
							(self setScript: (ScriptID 87 2))
						)
						(6
							(if (Btst 48)
								(messager say: 11 6 27 0 self)
							else
								(= cycles 1)
							)
						)
						(7
							(if (and (not (Btst 53)) (Btst 238))
								(Bset 53)
								(messager say: 21 6 31 0 self)
							else
								(messager say: 11 6 32 0 self)
							)
						)
						(8
							(Bset 42)
							(messager say: 11 6 33 0 self)
						)
						(9
							(Bset 44)
							(messager say: 11 6 34 0 self)
						)
						(10
							(Bset 49)
							(messager say: 11 6 35 0 self)
						)
						(11 (DailyMsg 11 6 36 self))
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(4
				(if
				(and (cast contains: burgoMeister) (== local2 6))
					(cond 
						((and (Btst 39) (not (Btst 48))) (Bset 384) (messager say: 11 6 28 0 self))
						((and (not (Btst 39)) (Btst 48)) (Bset 40) (Bset 39) (messager say: 11 6 30 0 self))
						((and (Btst 39) (Btst 48) (Btst 384)) (Bset 40) (messager say: 11 6 126 0 self))
						(else (Bset 40) (messager say: 11 6 29 0 self))
					)
				else
					(= cycles 1)
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBurgoGibber of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 1))
			(1
				(burgoMeister
					loop: 0
					setCycle: OccCyc self 1 5 500 (Random 1 3) self
				)
			)
			(2
				(burgoMeister
					loop: 1
					setCycle: OccCyc self 1 5 500 (Random 1 3) self
				)
			)
			(3 (= state 0) (= cycles 1))
		)
	)
)

(instance sInInnDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local5 0)
				(ego setMotion: PolyPath 76 121 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theMusic2 number: 960 loop: 1 play:)
				(innDoor setCycle: End self)
			)
			(3
				(ego setPri: 0 setMotion: MoveTo 76 111 self)
			)
			(4
				(innDoor cycleSpeed: 4 setCycle: Beg self)
			)
			(5
				(if (and (>= timeODay 4) (cast contains: hans))
					(Bset 70)
				)
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 3)
			)
			(6
				(theGame handsOn:)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance sOutInnDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 960 loop: 1 play:)
				(innDoor setCycle: End self)
			)
			(1
				(ego setPri: -1 setMotion: MoveTo 76 121 self)
			)
			(2
				(innDoor cycleSpeed: 4 setCycle: Beg self)
			)
			(3
				(theMusic2 number: 961 loop: 1 play:)
				(= local0 76)
				(= gTheObj_2Y 130)
				(= next sEnterScr)
				(= seconds 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInStoreDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local5 0)
				(ego setMotion: PolyPath 180 129 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theMusic2 number: 960 loop: 1 play:)
				(storeDoor setCycle: End self)
			)
			(3
				(ego setPri: 0 setMotion: MoveTo 180 119 self)
			)
			(4
				(storeDoor cycleSpeed: 4 setCycle: Beg self)
			)
			(5
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 3)
			)
			(6
				(theGame handsOn:)
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance sOutStoreDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 960 loop: 1 play:)
				(storeDoor setCycle: End self)
			)
			(1
				(ego setPri: -1 setMotion: MoveTo 180 129 self)
			)
			(2
				(storeDoor cycleSpeed: 4 setCycle: Beg self)
			)
			(3
				(theMusic2 number: 961 loop: 1 play:)
				(if (cast contains: hans)
					(= next sEnterScr)
					(= local0 180)
					(= gTheObj_2Y 139)
				)
				(= seconds 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInBurgoDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local5 0)
				(ego setHeading: 0 self)
			)
			(1
				(theMusic2 number: 960 loop: 1 play:)
				(burgoDoor setCycle: End self)
			)
			(2
				(ego setPri: 0 setMotion: MoveTo 222 136 self)
			)
			(3
				(theGame handsOn:)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance sOutBurgoDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 222 146 self)
			)
			(1
				(burgoDoor cycleSpeed: 4 setCycle: Beg self)
			)
			(2
				(theMusic2 number: 961 loop: 1 play:)
				(if (cast contains: hans)
					(= next sEnterScr)
					(= local0 222)
					(= gTheObj_2Y 153)
				)
				(= seconds 2)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInBurgoWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(Bset 70)
				(ego
					view: 352
					loop: 0
					cel: 0
					setSpeed: 8
					posn: 271 148
					setCycle: End self
				)
			)
			(1
				(ego setSpeed: gTheObj_2CycleSpeed)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance sOutBurgoWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 70)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego cycleSpeed: 9 setCycle: Beg self)
			)
			(1
				(closedWin show:)
				(messager say: 16 6 125 0 self)
			)
			(2
				(ego
					normalize: 3
					x: 269
					y: 149
					cycleSpeed: gTheObj_2CycleSpeed
					setMotion: MoveTo 230 157 self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKnockOnInnDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local5 0)
				(messager say: 18 129 9 0 self)
			)
			(1
				(theGame handsOn:)
				(self setScript: sInInnDoor)
			)
		)
	)
)

(instance sClimbUpInnWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 70)
				(if register
					(ego setMotion: PolyPath 11 132 self)
				else
					(ego setMotion: PolyPath 99 121 self)
				)
			)
			(1
				(if register
					(ego trySkill: 11 100)
					(Face ego (innDoor x?) (innDoor y?) self)
				else
					(= gTheObj_2CycleSpeed (ego cycleSpeed?))
					(ego
						view: 7
						loop: 3
						cel: 0
						posn: 100 121
						cycleSpeed: 9
						setScaler: Scaler 122 73 189 119
						setCycle: CT 2 1 self
					)
				)
			)
			(2
				(if register
					(ego view: 8 loop: 0 cel: 0 setCycle: End self)
				else
					(ego posn: 100 118 setCycle: End self)
				)
			)
			(3
				(if register
					(messager say: 8 33 2 0 self)
				else
					(ego posn: 100 116 cel: 0)
					(= cycles 2)
				)
			)
			(4
				(if (not register)
					(ego cycleSpeed: gTheObj_2CycleSpeed)
				)
				(= cycles 3)
			)
			(5 (curRoom newRoom: 330))
		)
	)
)

(instance sClimbDownInnWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 100 120 self)
			)
			(1
				(ego
					setSpeed: gTheObj_2CycleSpeed
					posn: 98 120
					setScaler: Scaler 122 50 189 87
					normalize: 3
				)
				(= cycles 2)
			)
			(2 (ego setHeading: 180 self))
			(3
				(if (and (== heroType 2) (not (Btst 505)))
					(ego solvePuzzle: 505 2 4)
				)
				(ego setMotion: MoveTo 98 129 self)
			)
			(4
				(theGame handsOn:)
				(self dispose: &rest)
			)
		)
	)
)

(instance sLevitateIntoInn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Bset 70) (= cycles 1))
			(1 (curRoom newRoom: 330))
		)
	)
)

(instance sToolkitOnWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 37 42 19 0 self)
			)
			(1
				(if (== (ego trySkill: 9 250) 1)
					(Bset 266)
					(closedWin hide:)
					(curRoom setScript: sInBurgoWin)
				else
					(messager say: 37 42 20 0 self)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(closedWin dispose:)
				(burgoMeister
					view: 261
					loop: 1
					cel: 11
					x: 273
					y: 130
					init:
					setCycle: Beg self
				)
			)
			(1
				(heroTeller
					init:
						ego
						260
						18
						128
						(switch local2
							(1 22)
							(2 23)
							(4 33)
							(3 24)
							(6 26)
							(7 32)
							(8 28)
							(9 29)
							(10 30)
							(11 31)
						)
				)
				(Bset 266)
				(burgoWin init: approachVerbs: 4)
				(burgoMeister
					view: 307
					x: 273
					y: 150
					approachVerbs: 4 2
					setScript: sBurgoGibber
				)
				(= cycles 1)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 32)
					(burgoMeister
						setScript: 0
						view: 261
						loop: 0
						cel: 0
						x: 273
						y: 130
						init:
						setCycle: End self
					)
				else
					(burgoMeister setScript: 0)
					(= cycles 1)
				)
			)
			(1
				(burgoMeister
					view: 261
					loop: 1
					cel: 0
					x: 273
					y: 130
					setCycle: End self
				)
			)
			(2
				(if (ego actions?) ((ego actions?) dispose:))
				(Bclr 266)
				(closedWin init: approachVerbs: 4 80 42 28)
				(burgoMeister dispose:)
				(= cycles 1)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenOnBurgoWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 266)
				(messager say: 37 80 0 0 self)
			)
			(1
				(closedWin hide:)
				(ego setMotion: PolyPath 269 149 self)
			)
			(2
				(self setScript: sInBurgoWin)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGroupTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(self changeState: (Random 1 3))
			)
			(1 1 (hans setCycle: End self))
			(2 2 (franz setCycle: End self))
			(3 3 (ivan setCycle: End self))
			(4
				4
				(= state -1)
				(= seconds (Random 3 12))
			)
		)
	)
)

(instance sHeroGuilty of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 11 6 124 0 self)
			)
			(1 (EgoDead 124 0 0 0 912))
		)
	)
)

(instance heroTeller of Teller
	(properties)
)

(instance burgoTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 65 0))
	)
	
	(method (sayMessage)
		(if (== iconValue 82) (ego get: 61) (Bset 183))
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				82
				(if (OneOf local2 1 2 3) (not (Btst 183)) else 0)
				89
				(not (Btst 49))
		)
	)
)

(instance hansTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 76 0))
	)
	
	(method (doVerb)
		(switch (CueObj client?)
			(hans
				(= talker (ScriptID 76 0))
			)
			(franz
				(= talker (ScriptID 77 0))
			)
			(ivan
				(= talker (ScriptID 78 0))
			)
		)
		(super doVerb: &rest)
	)
)

(instance innDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(if (== iconValue 9)
			(self clean:)
			(curRoom setScript: sKnockOnInnDoor)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super
			showCases: 12 (if (ego has: 13) else (ego has: 24))
		)
	)
)

(instance closedWin of View
	(properties
		noun 37
		sightAngle 180
		x 273
		y 130
		priority 10
		fixPriority 1
		view 261
		loop 2
		signal $4000
	)
	
	(method (handleEvent event)
		(if (== (event message?) KEY_P)
			(= approachX 223)
			(= approachY 181)
		else
			(= approachX 269)
			(= approachY 149)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(42
				(ego setScript: sToolkitOnWin)
			)
			(80
				(if (ego castSpell: 20)
					(= projX 272)
					(= projY 110)
					(curRoom setScript: (ScriptID 13 0) 0 self)
				)
			)
			(-80
				(curRoom setScript: sOpenOnBurgoWin)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lamp1 of View
	(properties
		sightAngle 180
		x 192
		y 101
		priority 140
		fixPriority 1
		view 260
		loop 4
		signal $4000
	)
)

(instance lamp2 of View
	(properties
		sightAngle 180
		x 244
		y 99
		view 260
		loop 5
		signal $4000
	)
)

(instance burgoMeister of Prop
	(properties
		noun 11
		approachX 242
		approachY 146
		x 273
		y 150
		fixPriority 1
		view 307
		signal $4001
		cycleSpeed 10
	)
	
	(method (init)
		(super init: &rest)
		(burgoTeller
			init:
				self
				260
				18
				131
				(switch local2
					(1 22)
					(2 23)
					(4 25)
					(3 24)
					(6 26)
					(7 27)
					(8 28)
					(9 29)
					(10 30)
					(else  31)
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(36
				(if
					(and
						(not (Btst 43))
						(== ((inventory at: 19) state?) 3)
					)
					(messager say: 11 6 34)
				else
					(super doVerb: &rest)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hans of Prop
	(properties
		approachX 134
		approachY 174
		x 121
		y 164
		view 264
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(hansTeller
			init: self 260 18 132
			(switch local2
				(4 34)
				(7 35)
			)
		)
	)
)

(instance franz of Prop
	(properties
		approachX 134
		approachY 174
		x 163
		y 168
		view 264
		loop 1
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= actions hansTeller)
	)
)

(instance ivan of Prop
	(properties
		approachX 134
		approachY 174
		x 148
		y 160
		view 264
		loop 2
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= actions hansTeller)
	)
)

(instance innDoor of Prop
	(properties
		noun 2
		sightAngle 180
		x 84
		y 116
		priority 1
		fixPriority 1
		view 260
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4)
			(42
				(if (> timeODay 5)
					(messager say: 2 28 2)
				else
					(messager say: 2 28 1)
				)
			)
			(28
				(if (> timeODay 5)
					(messager say: 2 28 2)
				else
					(messager say: 2 28 1)
				)
			)
			(80
				(if Night
					(messager say: 2 80 2)
				else
					(messager say: 2 80 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance storeDoor of Prop
	(properties
		noun 3
		sightAngle 180
		x 185
		y 126
		priority 1
		fixPriority 1
		view 260
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (OneOf timeODay 4 5) (messager say: 3 4 2))
			)
			(42
				(if (> timeODay 5)
					(messager say: 2 28 2)
				else
					(messager say: 2 28 1)
				)
			)
			(28
				(if (> timeODay 5)
					(messager say: 2 28 2)
				else
					(messager say: 2 28 1)
				)
			)
			(80
				(if Night
					(messager say: 3 80 2)
				else
					(messager say: 3 80 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance burgoDoor of Prop
	(properties
		noun 4
		sightAngle 180
		approachX 223
		approachY 141
		x 232
		y 134
		priority 1
		fixPriority 1
		view 260
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (OneOf timeODay 4 5) (messager say: 4 4 2))
			)
			(42
				(if Night
					(messager say: 2 28 2)
				else
					(messager say: 2 28 1)
				)
			)
			(28
				(if Night
					(messager say: 2 28 2)
				else
					(messager say: 2 28 1)
				)
			)
			(80
				(if Night
					(messager say: 4 80 2)
				else
					(messager say: 4 80 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance barrel of Feature
	(properties
		noun 1
		nsTop 167
		nsRight 53
		nsBottom 189
		sightAngle 180
		approachX 22
		approachY 177
		x 26
		y 178
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(24
				(ego drop: 9 1 get: 10 1)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance innSign of Feature
	(properties
		noun 5
		nsLeft 36
		nsTop 33
		nsRight 58
		nsBottom 54
		sightAngle 180
		x 47
		y 143
		z 100
	)
)

(instance storeSign of Feature
	(properties
		noun 6
		nsLeft 91
		nsTop 50
		nsRight 117
		nsBottom 75
		sightAngle 180
		x 104
		y 62
	)
)

(instance burgoSign of Feature
	(properties
		noun 7
		nsLeft 134
		nsTop 36
		nsRight 162
		nsBottom 63
		sightAngle 180
		x 148
		y 149
		z 100
	)
)

(instance innWindow of Feature
	(properties
		noun 8
		nsLeft 63
		nsTop 29
		nsRight 81
		nsBottom 58
		sightAngle 180
		x 72
		y 43
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if Night
					(if (== (ego trySkill: 11 225) 1)
						(curRoom setScript: sClimbUpInnWin)
					else
						(messager say: 16 6 13)
					)
				else
					(messager say: 16 6 66)
				)
			)
			(33
				(if Night
					(curRoom setScript: sClimbUpInnWin 0 1)
				else
					(messager say: 16 6 66)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance storeWindow of Feature
	(properties
		noun 9
		nsLeft 115
		nsTop 85
		nsRight 157
		nsBottom 115
		sightAngle 180
		x 136
		y 100
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if Night
					(messager say: 9 1 2)
				else
					(messager say: 9 1 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance innHighWin of Feature
	(properties
		noun 10
		nsLeft 119
		nsTop 45
		nsRight 155
		nsBottom 75
		sightAngle 180
		x 137
		y 60
	)
)

(instance burgoWin of Feature
	(properties
		noun 12
		nsLeft 251
		nsTop 86
		nsRight 294
		nsBottom 129
		sightAngle 180
		approachX 269
		approachY 149
		x 272
		y 107
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
				(and (<= 0 timeODay) (<= timeODay 3) (not (Btst 32)))
					(burgoMeister doVerb: theVerb &rest)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(42 (messager say: 12 42 68))
			(80 (messager say: 12 42 68))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance burgoHighWin1 of Feature
	(properties
		noun 13
		nsLeft 173
		nsTop 29
		nsRight 214
		nsBottom 63
		sightAngle 180
		x 193
		y 46
	)
)

(instance burgoHighWin2 of Feature
	(properties
		noun 13
		nsLeft 232
		nsTop 30
		nsRight 280
		nsBottom 59
		sightAngle 180
		x 256
		y 44
	)
)

(instance archWay of Feature
	(properties
		noun 14
		nsTop 47
		nsRight 58
		nsBottom 112
		sightAngle 180
		x 29
		y 79
	)
)

(instance tree1 of Feature
	(properties
		noun 15
		nsLeft 148
		nsTop 2
		nsRight 318
		nsBottom 55
		sightAngle 180
		x 233
		y 28
	)
)

(instance tree2 of Feature
	(properties
		noun 15
		nsLeft 288
		nsTop 56
		nsRight 319
		nsBottom 154
		sightAngle 180
		x 310
		y 105
	)
)
