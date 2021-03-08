;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1000)
(include game.sh) (include "1000.shm")
(use Main)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1000 0
)

(local
	local0 =  1
	local1
	local2
)
(instance rm1000 of Room
	(properties
		noun N_ROOM
		picture 120
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 664 663 673 661 678 660)
		(NormalEgo 0)
		(switch prevRoomNum
			(1005
				(NormalEgo 664 2)
				(ego posn: 160 97 init: setCycle: Walk setScale: myScaler)
			)
			(1010
				(doorLeft x: 6)
				(doorRight x: 20)
				(curRoom setScript: sEnterFromHall)
			)
			(1040
				(curRoom setScript: sEnterFromHall)
			)
			(else 
				(= local2 1)
				(SolvePuzzle fEnteredDriveBay 20)
				(ego
					view: 663
					setLoop: -1
					setLoop: 0
					cel: 0
					x: 120
					y: 230
					setStep: 5 4
					scaleSignal: 1
					scaleX: 86
					scaleY: 86
					setPri: 1
					setCycle: 0
					init:
				)
				(curRoom setScript: sUpFromPod)
			)
		)
		(light1 init: setCycle: Forward)
		(light2 init: setCycle: Forward)
		(light3 init: setCycle: Forward)
		(light4 init: setCycle: Forward)
		(if (Btst fInstalledDistCap)
			(driveOnOffLight cel: 2)
		else
			(driveOnOffLight cel: 0)
		)
		(driveOnOffLight init:)
		(doorLeft init:)
		(doorRight init:)
		(console init:)
		(engine init: setOnMeCheck: ftrControl cLGREEN)
		(carb init: setOnMeCheck: ftrControl cBROWN)
		(catwalk init: setOnMeCheck: ftrControl cLMAGENTA)
		(guard init: hide: setScript: sGuardPatrol)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						36 148
						175 148
						186 137
						319 137
						297 96
						153 96
						156 98
						272 98
						294 133
						176 133
						160 144
						61 144
						43 127
						93 127
						75 123
						42 122
						16 100
						9 100
					yourself:
				)
		)
		(super init:)
		(theMusic1 number: 101 setLoop: -1 play:)
		(if local2
			(walkCheck init:)
		)
		(theGame handsOn:)
	)
	
	(method (doit)
		(Palette PALCycle 65 69 10)
		(if (and (< (ego y?) 100) (== local1 0))
			(= local1 1)
		)
		(cond 
			((and (IsObjectOnControl ego cBLUE) (not (curRoom script?)))
				(curRoom setScript: sExitToHall)
			)
			((and (IsObjectOnControl ego cGREEN) (not (curRoom script?)))
				(curRoom setScript: sDownLeftStairs)
			)
			((and (IsObjectOnControl ego cCYAN) (not (curRoom script?)))
				(if (< (ego y?) 139)
					(curRoom setScript: sUpDownLittleStairs 0 0)
				else
					(curRoom setScript: sUpDownLittleStairs 0 1)
				)
			)
			((and (InRect 277 100 319 132 ego) (not (curRoom script?)))
				(if (< (ego y?) 110)
					(curRoom setScript: sUpDownRightStairs 0 0)
				else
					(curRoom setScript: sUpDownRightStairs 0 1)
				)
			)
			(
				(and
					(InRect 15 135 90 142 ego)
					(not (ego scaler?))
					(not (curRoom script?))
				)
				(ego setScale: Scaler 100 22 145 103)
			)
			((and (InRect 15 143 90 150 ego) (ego scaler?))
				(ego setScale: 0)
			)
		)
		(super doit: &rest)
	)
)

(instance sGuardPatrol of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(or
				(and (> (guard y?) 104) (InRect 16 100 188 148 ego))
				(and (> (guard y?) 119) (InRect 188 133 319 137 ego))
			)
			(guard setMotion: 0 setCycle: 0)
			(= next sGuardShoots)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (* 10 local0)))
			(1
				(if (InRect 16 100 30 120 ego)
					(-- state)
					(= cycles 1)
				else
					(guard show:)
					(doorLeft x: 10)
					(doorRight x: 17)
					(theMusic2 number: 103 setLoop: 1 play:)
					(= ticks 20)
				)
			)
			(2
				(doorLeft x: 6)
				(doorRight x: 20)
				(= ticks 20)
			)
			(3
				(guard
					setPri: -1
					setLoop: -1
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 19 111 self
				)
			)
			(4
				(guard setLoop: 0 setCycle: Oscillate 1 self)
			)
			(5
				(guard
					setLoop: -1
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 26 120 self
				)
			)
			(6
				(guard setLoop: 0 setCycle: Oscillate 1 self)
			)
			(7
				(guard
					setLoop: -1
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 33 129 self
				)
			)
			(8
				(guard setLoop: 1 setCycle: EndLoop self)
			)
			(9
				(guard
					setLoop: -1
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 12 102 self
				)
			)
			(10
				(guard setPri: 1)
				(doorLeft x: 10)
				(doorRight x: 17)
				(theMusic2 number: 103 setLoop: 1 play:)
				(= ticks 6)
			)
			(11
				(doorLeft x: 13)
				(doorRight x: 14)
				(guard hide:)
				(= ticks 6)
			)
			(12
				(if (== local0 1)
					(= local0 3)
				else
					(= local0 1)
				)
				(= state -1)
				(= cycles 1)
			)
			(13
				(self dispose:)
			)
		)
	)
)

(instance sGuardShoots of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch (guard loop?)
					(0 (guard setCycle: BegLoop self))
					(1 (guard setCycle: BegLoop self))
					(3
						(guard
							setLoop: 1
							setCel: (guard lastCel:)
							setCycle: BegLoop self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(guard view: 673)
				(cond 
					((< (ego x?) 60)
						(guard setLoop: 2)
						(pukeShot
							x: (- (guard x?) (/ (* 10 (guard scaleX?)) 128))
							y: (- (guard y?) (/ (* 43 (guard scaleY?)) 128))
						)
					)
					((< (ego x?) 145)
						(guard setLoop: 4)
						(pukeShot
							x: (+ (guard x?) (/ (* 9 (guard scaleX?)) 128))
							y: (- (guard y?) (/ (* 38 (guard scaleY?)) 128))
						)
					)
					(else
						(guard setLoop: 0)
						(pukeShot
							x: (+ (guard x?) (/ (* 16 (guard scaleX?)) 128))
							y: (- (guard y?) (/ (* 40 (guard scaleY?)) 128))
						)
					)
				)
				(guard cel: 0 setCycle: EndLoop)
				(theMusic2 number: 519 setLoop: 1 play:)
				(pukeShot
					init:
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
			(2
				(pukeShot setLoop: 9 setCycle: EndLoop)
				(ego view: 6501 cel: 0 setCycle: EndLoop self)
			)
			(3
				(EgoDead deathGENETIXGUARD)
				(self dispose:)
			)
		)
	)
)

(instance sDownLeftStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 663
					setLoop: -1
					setLoop: 1
					cel: 0
					setStep: 5 4
					setScale: 0
					scaleX: 86
					scaleY: 86
					scaleSignal: 1
					setPri: 1
					setCycle: Walk
					setMotion: MoveTo 120 230 self
				)
			)
			(1
				(messager say: N_DOWN_LEFT_STAIRS V_WALK NULL 0 self)
			)
			(2
				(ego setLoop: 0 setMotion: MoveTo 116 188 self)
			)
			(3
				(walkCheck init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpFromPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(ego setLoop: 0 setMotion: MoveTo 116 188 self)
			)
			(2
				(walkCheck init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 664
					x: 13
					y: 102
					setScale: Scaler 100 22 145 103
					setCycle: Walk
					init:
					setMotion: MoveTo 14 110 self
				)
			)
			(1
				(doorLeft x: 10)
				(doorRight x: 17)
				(theMusic2 number: 103 setLoop: 1 play:)
				(= ticks 20)
			)
			(2
				(doorLeft x: 13)
				(doorRight x: 14)
				(walkCheck dispose:)
				(= ticks 20)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitToHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(doorLeft x: 10)
				(doorRight x: 17)
				(theMusic2 number: 103 setLoop: 1 play:)
				(= ticks 20)
			)
			(1
				(doorLeft x: 6)
				(doorRight x: 20)
				(= ticks 20)
			)
			(2
				(ego setMotion: MoveTo 13 102 self)
			)
			(3
				(curRoom newRoom: 1010)
				(self dispose:)
			)
		)
	)
)

(instance sUpLeftStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: -1
					setLoop: 0
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 73 125 self
				)
			)
			(1
				(ego
					view: 664
					cel: 0
					setLoop: -1
					loop: 1
					setPri: -1
					setScale: Scaler 100 22 145 103
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) 5) (ego y?) self
				)
			)
			(2
				(walkCheck dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpDownRightStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(ego
						view: 663
						cel: 0
						setLoop: -1
						setLoop: 0
						setStep: 5 4
						cycleSpeed: 4
						y: 132
						setCycle: Walk
						setMotion: PolyPath (- (ego x?) 23) 98 self
					)
				else
					(ego
						view: 663
						cel: 0
						setLoop: -1
						setLoop: 1
						setScale: 0
						setCycle: Walk
						setMotion: PolyPath (+ (ego x?) 21) 136 self
					)
				)
			)
			(1
				(if register
					(ego
						view: 664
						cel: 0
						setLoop: -1
						loop: 1
						cycleSpeed: 6
						setCycle: Walk
						setScale: myScaler
						setMotion: PolyPath 237 98 self
					)
				else
					(ego
						view: 664
						cel: 0
						setLoop: -1
						loop: 1
						setCycle: Walk
						setMotion: PolyPath 280 136 self
					)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpDownLittleStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not register)
					(ego setMotion: PolyPath (- (ego x?) 15) (ego y?) self)
				else
					(= cycles 1)
				)
			)
			(1
				(if register
					(ego
						view: 664
						setLoop: -1
						setLoop: 8
						cel: 0
						x: 168
						y: 139
						setCycle: EndLoop self
					)
				else
					(ego
						view: 664
						setLoop: -1
						setLoop: 9
						cel: 0
						x: 170
						y: 135
						setCycle: EndLoop self
					)
				)
			)
			(2
				(ego view: 664 cel: 0 setLoop: -1 setCycle: Walk)
				(if register
					(ego
						loop: 0
						cel: 6
						x: 190
						y: 136
						setMotion: MoveTo 208 136 self
					)
				else
					(ego
						loop: 1
						cel: 1
						x: 155
						y: 145
						setMotion: MoveTo 130 145 self
					)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance guard of Actor
	(properties
		x 12
		y 102
		noun N_GUARD
		view 661
		loop 2
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
		moveSpeed 10
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 100 22 145 103)
	)
)

(instance pukeShot of Actor
	(properties
		view 678
		signal ignrAct
		moveSpeed 0
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 100 22 145 103)
	)
)

(instance light1 of Prop
	(properties
		x 141
		y 81
		noun N_ROOM
		view 660
		signal ignrAct
		cycleSpeed 10
	)
)

(instance light2 of Prop
	(properties
		x 143
		y 77
		noun N_ROOM
		view 660
		loop 1
		signal ignrAct
	)
)

(instance light3 of Prop
	(properties
		x 155
		y 80
		noun N_ROOM
		view 660
		loop 2
		signal ignrAct
		cycleSpeed 14
	)
)

(instance light4 of Prop
	(properties
		x 156
		y 83
		noun N_ROOM
		view 660
		loop 2
		signal ignrAct
		cycleSpeed 10
	)
)

(instance driveOnOffLight of Prop
	(properties
		x 165
		y 78
		noun N_ROOM
		view 660
		loop 3
		signal ignrAct
	)
)

(instance doorLeft of View
	(properties
		x 13
		y 93
		noun N_DOOR
		view 660
		loop 4
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance doorRight of View
	(properties
		x 14
		y 93
		noun N_DOOR
		view 660
		loop 4
		cel 1
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance catwalk of Feature
	(properties
		x 150
		y 84
		noun N_CATWALK
		onMeCheck cLMAGENTA
	)
)

(instance carb of Feature
	(properties
		x 150
		y 84
		noun N_CARBURATOR
		onMeCheck cBROWN
	)
)

(instance engine of Feature
	(properties
		x 150
		y 84
		noun N_ENGINE
		onMeCheck cLGREEN
	)
)

(instance console of Feature
	(properties
		x 150
		y 84
		noun N_CONSOLE
		nsTop 74
		nsLeft 120
		nsBottom 97
		nsRight 184
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (InRect 153 92 174 100 ego)
					(curRoom newRoom: 1005)
				else
					(messager say: N_CONSOLE V_DO C_CANT_REACH 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance walkCheck of Feature
	(properties
		x 150
		y 84
		nsBottom 189
		nsRight 319
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: walkCheck)
	)
	
	(method (dispose)
		(walkHandler delete: walkCheck)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(curRoom setScript: sUpLeftStairs)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance myScaler of Scaler
	
	(method (init theClient)
		(if argc (= client theClient))
		(self doit:)
	)
	
	(method (doit)
		(if (> (ego x?) 250)
			(ego
				scaleSignal: scalable
				scaleX: scaleBase
				scaleY: scaleBase
			)
		else
			(ego
				scaleSignal: scalable
				scaleX: (- scaleBase (/ (* 2 (- 250 (ego x?))) 3))
				scaleY: (ego scaleX?)
			)
		)
	)
)
