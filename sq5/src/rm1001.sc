;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1001)
(include game.sh) (include "1001.shm")
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
	rm1001 0
)

(local
	local0
)
(instance rm1001 of Room
	(properties
		noun N_ROOM
		picture 120
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 660 665 4 6)
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
		(switch prevRoomNum
			(1005
				(ego
					view: 4
					posn: 165 98
					loop: 2
					setStep: 5 3
					setScale: myScaler
					setCycle: Walk
					init:
				)
				(theGame handsOn:)
			)
			(else 
				(curRoom setScript: sBeamIn)
			)
		)
		(theMusic1 number: 101 setLoop: -1 play:)
	)
	
	(method (doit)
		(if (and (< (ego y?) 100) (== local0 0))
			(= local0 1)
		)
		(cond 
			((and (IsObjectOnControl ego cBLUE) (not (curRoom script?)))
				(curRoom setScript: sExitToHall)
			)
			(
				(and
					(InRect 277 100 319 110 ego)
					(not (curRoom script?))
				)
				(curRoom setScript: sDownRightStairs)
			)
			(
				(and
					(InRect 264 130 268 142 ego)
					(not (curRoom script?))
				)
				(curRoom setScript: sUpRightStairs)
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

(instance sBeamIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(theMusic2 number: 260 setLoop: 1 play:)
				(ego
					view: 6
					loop: 0
					cel: 0
					x: 100
					y: 145
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(theMusic2 number: 603 setLoop: 1 play: self)
			)
			(3
				(messager say: N_BEAM_IN V_COMMUNICATOR NULL 0 self)
			)
			(4
				(ego
					view: 4
					loop: 3
					cel: 0
					setStep: 5 3
					setCycle: Walk
					setMotion: PolyPath 120 145 self
				)
			)
			(5
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
				(if (not (Btst fInstalledDistCap))
					(= next sShipBlows)
					(self dispose:)
				)
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
				(ego setPri: 1)
				(doorLeft x: 10)
				(doorRight x: 17)
				(theMusic2 number: 103 setLoop: 1 play:)
				(= ticks 20)
			)
			(4
				(doorLeft x: 13)
				(doorRight x: 14)
				(= ticks 20)
			)
			(5
				(theGame handsOn:)
				(curRoom newRoom: 1040)
				(self dispose:)
			)
		)
	)
)

(instance sShipBlows of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ShakeScreen 5 2)
				(= seconds 2)
			)
			(1
				(theMusic2 number: 203 setLoop: 1 play:)
				(PalVary PALVARYSTART 2100 1)
				(= seconds 2)
			)
			(2
				(EgoDead deathFORGOTDISTCAP)
				(self dispose:)
			)
		)
	)
)

(instance sDownRightStairs of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 665
					cel: 0
					x: 273
					y: 92
					setPri: 10
					setScale: 0
					setLoop: -1
					setLoop: 2
					setCycle: EndLoop self
				)
			)
			(1
				(theMusic2 number: 152 setLoop: 1 play:)
				(ego
					setCel: 5
					setLoop: -1
					setLoop: 2
					setCycle: 0
					setMotion: MoveTo 284 116 self
				)
			)
			(2
				(ego
					cel: 0
					x: 273
					y: 122
					setLoop: -1
					setLoop: 3
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					view: 4
					setLoop: -1
					loop: 1
					cel: 0
					x: 267
					y: 136
					setStep: 5 3
					setPri: -1
					setCycle: Walk
					setMotion: MoveTo 247 136 self
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpRightStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 665
					cel: 0
					setLoop: -1
					loop: 1
					x: 279
					y: 106
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					view: 4
					cel: 3
					setLoop: -1
					loop: 7
					x: 269
					y: 98
					setStep: 5 3
					setCycle: Walk
					setScale: myScaler
					setMotion: MoveTo 259 98 self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
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

(instance myScaler of Scaler
	(properties)
	
	(method (init c)
		(if argc
			(= client c)
		)
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
