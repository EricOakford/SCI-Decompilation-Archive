;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1010)
(include game.sh) (include "1010.shm")
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1010 0
)

(local
	[pukeX 20] = [3 37 57 115 142 175 227 21 61 84 103 130 182 223 254 29 144 162 256]
	[pukeY 20] = [21 50 92 100 88 37 7 26 88 97 96 32 7 70 75 124 114 101 37]
	[pukeLoop 20] = [4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 5 5 4]
	[pukeCel 22] = [0 1 2 3 4 5 6 0 1 2 3 4 5 6 7 8 9 10 11 7]
	local82
	local83
)
(class Puke of View
	(properties
		view 674
		priority 14
		signal (| ignrAct fixPriOn)
		array_index 19
	)
	
	(method (init)
		(self
			loop: [pukeLoop array_index]
			cel: [pukeCel array_index]
			x: [pukeX array_index]
			y: [pukeY array_index]
		)
		(super init: &rest)
		(self addToPic:)
	)
)

(instance rm1010 of Room
	(properties
		noun N_ROOM
		picture 122
		style FADEOUT
	)
	
	(method (init)
		(LoadMany RES_VIEW 672 664 673 678)
		(super init:)
		(if (!= (theMusic1 number?) 101)
			(theMusic1 number: 101 setLoop: -1 play: setVol: 65)
		else
			(theMusic1 setVol: 65)
		)
		(if (== goliathFloor 0)
			(= goliathFloor 8)
			(= global134 1)
			(= global135 13)
		)
		(puke5 array_index: 19)
		(puke6 array_index: 19)
		(switch (mod global135 6)
			(0
				(puke1 array_index: 0)
				(puke5 array_index: 1)
				(puke3 array_index: 9)
			)
			(1
				(puke1 array_index: 7)
				(puke3 array_index: 10)
			)
			(2
				(puke1 array_index: 11)
				(puke3 array_index: 3)
			)
			(3
				(puke1 array_index: 12)
				(puke3 array_index: 8)
			)
			(4
				(puke1 array_index: 5)
				(puke5 array_index: 6)
				(puke3 array_index: 15)
			)
			(5
				(puke1 array_index: 18)
				(puke3 array_index: 2)
			)
		)
		(switch (/ global135 6)
			(0
				(puke2 array_index: 13)
				(puke4 array_index: 11)
			)
			(1
				(puke2 array_index: 14)
				(puke4 array_index: 12)
			)
			(2
				(puke2 array_index: 13)
				(puke6 array_index: 14)
				(puke4 array_index: 8)
			)
			(3
				(puke2 array_index: 17)
				(puke4 array_index: 11)
				(puke6 array_index: 12)
			)
			(4
				(puke2 array_index: 4)
				(puke4 array_index: 2)
			)
			(5
				(puke2 array_index: 16)
				(puke4 array_index: 15)
			)
		)
		(puke1 init:)
		(puke2 init:)
		(puke3 init:)
		(puke4 init:)
		(puke5 init:)
		(puke6 init:)
		(switch (mod global135 4)
			(0
				(leftExit loop: 1 cel: 1 x: 41 y: 90)
				(rightExit loop: 0 cel: 2 x: 144 y: 93)
			)
			(1
				(leftExit loop: 1 cel: 1 x: 41 y: 90)
				(rightExit loop: 2 cel: 0 x: 139 y: 99 priority: 7)
				(if (== prevRoomNum 1000)
					(engineRoomOpening setCel: (engineRoomOpening lastCel:))
				)
				(engineRoomOpening init:)
				(rightDoorLeft init:)
			)
			(2
				(leftExit loop: 0 cel: 0 x: 43 y: 97)
				(rightExit loop: 0 cel: 2 x: 144 y: 93)
			)
			(3
				(leftExit loop: 0 cel: 0 x: 43 y: 97)
				(rightExit loop: 2 cel: 0 x: 139 y: 99 priority: 7)
				(if (== prevRoomNum 1000)
					(engineRoomOpening setCel: (engineRoomOpening lastCel:))
				)
				(engineRoomOpening init:)
				(rightDoorLeft init:)
			)
		)
		(leftExit init:)
		(rightExit init:)
		(if (> global135 17)
			(extraPanel1 loop: 0 cel: 4 x: 178 y: 133)
		else
			(extraPanel1 loop: 0 cel: 3 x: 173 y: 110)
		)
		(if (< (mod global135 6) 3)
			(extraPanel2 loop: 1 cel: 0 x: 17 y: 130)
		else
			(extraPanel2 loop: 1 cel: 3 x: 122 y: 105)
		)
		(if (mod global135 2)
			(extraPanel3 loop: 1 cel: 2 x: 81 y: 106)
		else
			(extraPanel3 loop: 4 cel: 7 x: 0 y: 0)
		)
		(extraPanel1 init: addToPic:)
		(extraPanel2 init: addToPic:)
		(extraPanel3 init: addToPic:)
		(if
			(or
				(and (== goliathFloor 1) (== global135 7))
				(and (== goliathFloor 2) (== global135 2))
				(and (== goliathFloor 3) (== global135 1))
				(and (== goliathFloor 4) (== global135 0))
				(and (== goliathFloor 4) (== global135 15))
				(and (== goliathFloor 5) (== global135 0))
				(and (== goliathFloor 6) (== global135 2))
				(and (== goliathFloor 6) (== global135 14))
				(and
					(== goliathFloor 7)
					(== global135 0)
					(== global134 1)
				)
				(and
					(== goliathFloor 7)
					(== global135 2)
					(!= global134 1)
				)
				(and
					(== goliathFloor 7)
					(== global135 7)
					(!= global134 1)
				)
				(and (== goliathFloor 8) (== global135 2) (< global134 2))
				(and (== goliathFloor 8) (== global135 0) (> global134 1))
				(and (== goliathFloor 9) (== global135 0))
			)
			(= local82 1)
			(turboBack init: addToPic:)
			(turboDoor init:)
			(floorNum x: 119 y: 108 loop: 6 init: addToPic:)
		else
			(= local82 0)
			(floorDeck init: addToPic:)
			(floorNum init: addToPic:)
		)
		(switch prevRoomNum
			(1020
				(curRoom setScript: sLeaveSubfloor)
			)
			(else 
				(curRoom setScript: sExitEngineRoom)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						167 189
						167 185
						191 179
						129 124
						120 124
						113 112
						88 112
						79 124
						71 124
						0 183
						0 189
					yourself:
				)
		)
		(grateArea init: setOnMeCheck: 1 cBLUE)
	)
	
	(method (doit &tmp theControl)
		(super doit: &rest)
		(= theControl (ego onControl: origin))
		(cond 
			(
				(and
					(InRect 0 179 319 189 ego)
					(not (curRoom script?))
					(not local83)
				)
				(if local82
					(curRoom setScript: sTurboPukoid 0 0)
				else
					(curRoom setScript: sBackPukoid 0 0)
				)
			)
			(
				(and
					(== theControl cCYAN)
					(not (curRoom script?))
					(not local83)
				)
				(if local82
					(curRoom setScript: sTurboPukoid 0 1)
				else
					(curRoom setScript: sBackPukoid 0 1)
				)
			)
			(
				(and
					(== theControl cRED)
					(not (curRoom script?))
					(not local83)
				)
				(curRoom setScript: sFrontPukoid 0 1)
			)
			(
				(and
					(== theControl cMAGENTA)
					(not (curRoom script?))
					(not local83)
				)
				(curRoom setScript: sFrontPukoid 0 0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun V_LOOK NULL (Random 1 3))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sEnterGrate of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 98 139 self)
			)
			(1 (ego setHeading: 180 self))
			(2
				(ego
					view: 672
					setLoop: -1
					setLoop: 1
					cel: 0
					x: 96
					y: 142
					setCycle: CycleTo 3 1 self
				)
			)
			(3
				(ego setCel: 4)
				(grate init:)
				(= ticks 20)
			)
			(4
				(ego setCel: 5)
				(grate setCel: 1)
				(= ticks 20)
			)
			(5
				(ego setCel: 6)
				(grate setCel: 2)
				(= ticks 20)
			)
			(6 (ego setCycle: EndLoop self))
			(7
				(ego hide:)
				(grate setCel: 3)
				(= ticks 20)
			)
			(8
				(theMusic2 number: 517 setLoop: 1 play: self)
				(grate setCel: 0)
			)
			(9 (= ticks 10))
			(10
				(SolvePuzzle f1010EnterGrate 100)
				(curRoom newRoom: 1020)
				(self dispose:)
			)
		)
	)
)

(instance sLeaveSubfloor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 672
					setLoop: -1
					setLoop: 1
					setScale: Scaler 177 26 189 108
					setCycle: 0
					setCel: 6
					x: 96
					y: 142
					init:
				)
				(grate cel: 2 init:)
				(= seconds 2)
			)
			(1
				(ego setCel: 5)
				(grate setCel: 1)
				(= ticks 15)
			)
			(2
				(ego setCel: 4)
				(grate setCel: 0)
				(= ticks 15)
			)
			(3
				(ego setCycle: BegLoop self)
				(grate dispose:)
			)
			(4
				(ego
					view: 664
					cel: 0
					setLoop: -1
					loop: 2
					setCycle: Walk
					setScale: Scaler 177 26 189 108
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitEngineRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 664
					cel: 0
					setLoop: -1
					loop: 2
					posn: 161 132
					setPri: -1
					setCycle: Walk
					setScale: Scaler 177 26 189 108
					init:
					setMotion: MoveTo 130 132 self
				)
			)
			(1
				(theMusic2 number: 103 setLoop: 1 play:)
				(engineRoomOpening setCycle: BegLoop self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterEngineRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 103 setLoop: 1 play:)
				(engineRoomOpening setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 161 132 self)
			)
			(2
				(curRoom newRoom: 1000)
				(self dispose:)
			)
		)
	)
)

(instance sBackPukoid of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: N_HALT NULL NULL 0 self)
			)
			(1
				(ego view: 6501 cel: 0 setCycle: CycleTo 1 1)
				(guard
					view: 673
					x: 135
					y: 121
					init:
					setPri: 1
					setStep: 8 2
					setCel: 0
					setScale: Scaler 177 26 189 108
					setMotion: MoveTo 117 121 self
				)
			)
			(2
				(if register
					(guard setLoop: 5)
					(pukeShot
						x: (+ (guard x?) (/ (* 9 (guard scaleX?)) 128))
						y: (- (guard y?) (/ (* 38 (guard scaleY?)) 128))
					)
				else
					(guard setLoop: 2)
					(pukeShot
						x: (- (guard x?) (/ (* 10 (guard scaleX?)) 128))
						y: (- (guard y?) (/ (* 43 (guard scaleY?)) 128))
					)
				)
				(guard cel: 0 setCycle: EndLoop)
				(theMusic2 number: 519 setLoop: 1 play:)
				(pukeShot
					init:
					setPri: 6
					setLoop: -1
					setLoop: 8
					setCel: 0
					setCycle: 0
					setStep: 20 10
					setMotion:
						MoveTo
						(- (ego x?) (/ (* 5 (ego scaleX?)) 128))
						(- (ego y?) (/ (* 29 (ego scaleY?)) 128))
						self
				)
			)
			(3
				(pukeShot setLoop: 9 setCycle: EndLoop self)
				(ego setCycle: EndLoop self)
				(= local83 1)
			)
			(4 0)
			(5
				(EgoDead deathGENETIXGUARD)
				(self dispose:)
			)
		)
	)
)

(instance sTurboPukoid of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(guard
					view: 673
					x: 100
					y: 122
					init:
					setPri: 1
					setScale: Scaler 177 26 189 108
					setCel: 0
				)
				(if register (guard setLoop: 5) else (guard setLoop: 2))
				(turboDoor setCycle: EndLoop self)
			)
			(1 (messager say: N_HALT NULL NULL 0 self))
			(2
				(ego view: 6501 cel: 0 setCycle: CycleTo 1 1)
				(if register
					(pukeShot
						x: (+ (guard x?) (/ (* 9 (guard scaleX?)) 128))
						y: (- (guard y?) (/ (* 38 (guard scaleY?)) 128))
					)
				else
					(pukeShot
						x: (- (guard x?) (/ (* 10 (guard scaleX?)) 128))
						y: (- (guard y?) (/ (* 43 (guard scaleY?)) 128))
					)
				)
				(guard setCycle: EndLoop)
				(theMusic2 number: 519 setLoop: 1 play:)
				(pukeShot
					init:
					setPri: 4
					setLoop: -1
					setLoop: 8
					setCel: 0
					setCycle: 0
					setStep: 20 10
					setMotion:
						MoveTo
						(- (ego x?) (/ (* 5 (ego scaleX?)) 128))
						(- (ego y?) (/ (* 29 (ego scaleY?)) 128))
						self
				)
			)
			(3
				(pukeShot setLoop: 9 setCycle: EndLoop self)
				(ego setCycle: EndLoop self)
				(= local83 1)
			)
			(4 0)
			(5
				(EgoDead deathGENETIXGUARD)
				(self dispose:)
			)
		)
	)
)

(instance sFrontPukoid of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: N_HALT NULL NULL 0 self)
			)
			(1
				(ego view: 6501 cel: 0 setCycle: CycleTo 1 1)
				(guard
					view: 672
					x: (+ 43 (* register 105))
					y: 190
					init:
					setPri: 15
					setStep: 8 12
					setLoop: 3
					setCel: 0
					setMotion: MoveTo (+ 43 (* register 105)) 138 self
				)
			)
			(2
				(theMusic2 number: 519 setLoop: 1 play:)
				(pukeShot
					x: (+ (guard x?) 12)
					y: 180
					init:
					setPri: 14
					setLoop: -1
					setLoop: 8
					setCel: 0
					setCycle: 0
					setStep: 20 10
					setMotion:
						MoveTo
						(- (ego x?) (/ (* 5 (ego scaleX?)) 128))
						(- (ego y?) (/ (* 29 (ego scaleY?)) 128))
						self
				)
			)
			(3
				(pukeShot setLoop: 9 setCycle: EndLoop self)
				(ego setCycle: EndLoop self)
				(= local83 1)
			)
			(4 0)
			(5
				(EgoDead deathGENETIXGUARD)
				(self dispose:)
			)
		)
	)
)

(instance grate of View
	(properties
		x 73
		y 159
		noun N_GRATE
		view 672
		loop 2
		signal ignrAct
	)
)

(instance guard of Actor
	(properties
		x 21
		y 189
		noun N_GUARD
		view 672
		loop 4
		signal ignrAct
	)
)

(instance pukeShot of Actor
	(properties
		yStep 24
		view 678
		signal ignrAct
		xStep 12
		moveSpeed 0
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 177 26 189 108)
	)
)

(instance engineRoomOpening of Prop
	(properties
		x 140
		y 96
		view 674
		loop 3
		priority 4
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== prevRoomNum 1000)
					(curRoom setScript: sEnterEngineRoom)
				else
					(messager say: N_EXIT V_DO C_LOCKED 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance puke1 of Puke)

(instance puke2 of Puke)

(instance puke3 of Puke)

(instance puke4 of Puke)

(instance puke5 of Puke)

(instance puke6 of Puke)

(instance leftExit of View
	(properties
		noun N_EXIT
		view 674
		signal ignrAct
	)
)

(instance rightExit of View
	(properties
		noun N_EXIT
		view 674
		priority 3
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance rightDoorLeft of View
	(properties
		x 139
		y 99
		view 674
		loop 10
		priority 3
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance extraPanel1 of View
	(properties
		view 674
		signal ignrAct
	)
)

(instance extraPanel2 of View
	(properties
		view 674
		signal ignrAct
	)
)

(instance extraPanel3 of View
	(properties
		view 674
		signal ignrAct
	)
)

(instance floorDeck of View
	(properties
		x 269
		y 107
		view 674
		cel 5
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance floorNum of View
	(properties
		x 281
		y 124
		view 674
		loop 7
		cel 5
		priority 3
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(if (and (< 0 goliathFloor) (< goliathFloor 10))
			(self cel: (- goliathFloor 1))
		else
			(self cel: 7)
		)
		(super init: &rest)
	)
)

(instance turboBack of View
	(properties
		x 74
		y 100
		view 674
		loop 8
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance turboDoor of Prop
	(properties
		x 90
		y 95
		view 674
		loop 9
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance grateArea of Feature
	(properties
		x 106
		y 137
		noun 2
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sEnterGrate)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
