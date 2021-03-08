;;; Sierra Script 1.0 - (do not remove this comment)
(script# 121)
(include game.sh) (include "121.shm")
(use Main)
(use Scaler)
(use RandCyc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm121 0
)

(instance rm121 of Room
	(properties
		picture 21
		style (| BLACKOUT FADEOUT)
		horizon -20
		vanishingX 140
		vanishingY -5
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(if (Btst fAttendedClass)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PContainedAccess
						init:
							72 189
							285 189
							243 154
							212 131
							162 110
							176 109
							176 108
							154 108
							143 106
							136 105
							137 111
							126 123
							124 130
							141 132
							137 148
							126 152
							97 155
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PContainedAccess
						init:
							51 189
							285 189
							243 154
							212 131
							162 110
							176 109
							176 108
							154 108
							143 106
							136 105
							137 111
							126 123
							93 155
						yourself:
					)
			)
		)
		(LoadMany RES_VIEW 140 108 113 110)
		(LoadMany RES_MESSAGE 121)
		(super init:)
		(NormalEgo 1)
		(ego setScale: Scaler 130 24 157 106 init:)
		(switch prevRoomNum
			(125
				(curRoom setScript: (ScriptID 109 5))
				(if (not (Btst fSawVaderAndObiWan))
					(darth init: setScript: sStarWars)
				)
			)
			(117
				(if (and (Btst fCleanedCrest) (not (Btst fSawMouseInHall)))
					(ego setScript: sMouseRun)
				)
				(ego posn: 178 108 hide:)
				(curRoom setScript: sEnterFromHallway)
			)
			(else 
				(curRoom setScript: (ScriptID rgStarCon 6))
			)
		)
		(exitToCrest init: approachVerbs: V_WALK V_DO addToPic:)
		(extraPanel init: addToPic:)
		(ratHole init: setOnMeCheck: ftrDefault)
		(ship1 init: addToPic:)
		(ship2 init: addToPic:)
		(ship3 init: addToPic:)
		(shipLight1 setCycle: Forward init:)
		(shipLight2 setCycle: Forward init:)
		(shipLight3 setCycle: Forward init:)
		(guy1 setCycle: Forward init:)
		(guy2 setCycle: Forward init:)
		(guy3 setCycle: Forward init:)
		(guy4 init:)
		(guy5 init:)
		(guy6 init:)
		(guy7 init: setScript: sBayGuys)
		(walkHandler addToFront: exitToCrest)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script 0)
			(
				(and
					(> (ego y?) 157)
					(< 90 (ego heading?))
					(< (ego heading?) 270)
				)
				(curRoom setScript: (ScriptID rgStarCon 3) 0 125)
			)
			(
				(and
					(IsObjectOnControl ego cLMAGENTA)
					(< (ego heading?) 180)
				)
				(curRoom setScript: sExitToCrest)
			)
			((< (ego y?) 108) (curRoom setScript: (ScriptID rgStarCon 4) 0 122))
		)
	)
	
	(method (dispose)
		(walkHandler delete: exitToCrest)
		(super dispose: &rest)
	)
)

(instance sStarWars of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(darth
					setLoop: -1
					setLoop: 1
					setMotion: MoveTo (+ (darth x?) 42) (darth y?) self
				)
			)
			(2 (= seconds 5))
			(3
				(Bset fSawVaderAndObiWan)
				(darth dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitToCrest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 168 108 self)
			)
			(1
				(ego setMotion: MoveTo 178 108 self)
			)
			(2 (curRoom newRoom: 117))
		)
	)
)

(instance sMouseRun of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego loop: 3 posn: 160 160 setMotion: MoveTo 160 145)
				(theMusic2 number: 141 setLoop: -1 play:)
				(mouse
					init:
					setCycle: Forward
					setStep: 13 13
					moveSpeed: 0
					cycleSpeed: 0
					setMotion: MoveTo 262 179 self
				)
			)
			(1
				(theMusic2 stop:)
				(mouse setLoop: 1 setCel: 0 setCycle: 0)
				(= seconds 2)
			)
			(2 (mouse setCycle: EndLoop self))
			(3
				(theGame handsOn:)
				(curRoom newRoom: 133)
			)
		)
	)
)

(instance sEnterFromHallway of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 10)
			)
			(1
				(ego show: posn: 178 108 setMotion: MoveTo 150 108 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBayGuys of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 4 7)))
			(1
				(guy7
					setLoop: 11
					setCycle: Forward
					setMotion: MoveTo 42 94 self
				)
			)
			(2
				(guy7 setLoop: 12 setCycle: EndLoop self)
			)
			(3
				(guy7 setLoop: 13 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(4
				(guy4 setLoop: 8 setCycle: Forward setMotion: MoveTo 0 89)
				(guy1 setCycle: 0)
				(= seconds (Random 4 7))
			)
			(5
				(guy5 setLoop: 4 setCel: 0 setMotion: MoveTo 50 99)
				(guy2 setCycle: 0)
				(= seconds (Random 4 7))
			)
			(6
				(guy6 setLoop: 5 setCel: 0 setMotion: MoveTo 3 136)
				(= seconds (Random 4 7))
			)
			(7
				(guy5 setLoop: 5 setMotion: MoveTo 0 82)
				(guy3 setCycle: 0)
				(guy1 setCycle: Forward)
				(= seconds (Random 3 5))
			)
			(8
				(guy4 setMotion: MoveTo 57 39)
				(= seconds (Random 4 7))
			)
			(9
				(guy6 setLoop: 4 setMotion: MoveTo 29 136)
				(guy2 setCycle: Forward)
				(guy3 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(10
				(= state (Random 3 5))
				(= cycles 1)
			)
		)
	)
)

(instance mouse of Actor
	(properties
		x 196
		y 119
		noun N_MOUSE
		view 140
		scaleSignal scalable
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 100 25 179 116)
	)
)

(instance guy1 of Prop
	(properties
		x 87
		y 86
		noun N_GUYS
		view 108
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy2 of Prop
	(properties
		x 47
		y 136
		noun N_GUYS
		view 108
		loop 2
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy3 of Prop
	(properties
		x 66
		y 82
		noun N_GUYS
		view 108
		loop 7
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy4 of Actor
	(properties
		x 57
		y 39
		noun N_GUYS
		yStep 1
		view 108
		loop 8
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy5 of Actor
	(properties
		y 82
		noun N_GUYS
		yStep 1
		view 108
		loop 4
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy6 of Actor
	(properties
		x 29
		y 136
		noun N_GUYS
		yStep 1
		view 108
		loop 5
		priority 7
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy7 of Actor
	(properties
		x -5
		y 79
		noun N_GUYS
		yStep 1
		view 108
		loop 11
		priority 13
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance shipLight1 of Prop
	(properties
		x 63
		y 70
		noun N_SHIP1
		view 113
		loop 4
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 12
		detailLevel 2
	)
)

(instance shipLight2 of Prop
	(properties
		x 10
		y 68
		noun N_SHIP2
		view 113
		loop 5
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 9
		detailLevel 2
	)
)

(instance shipLight3 of Prop
	(properties
		x 21
		y 124
		noun N_SHIP3
		view 113
		loop 6
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance darth of Actor
	(properties
		x 141
		y 108
		view 144
		loop 1
		priority 6
		signal (| ignrAct ignrHrz fixPriOn)
		xStep 2
	)
	
	(method (init)
		(obiwan init: setCycle: RTRandCycle)
		(super init:)
		(self setCycle: RTRandCycle)
	)
)

(instance obiwan of Prop
	(properties
		view 144
		priority 6
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doit)
		(self x: (+ (darth x?) 30) y: (+ (darth y?) 2))
		(if (> (self x?) 187) (self dispose:))
		(super doit:)
	)
)

(instance ship1 of View
	(properties
		x 43
		y 56
		noun N_SHIP1
		view 113
		signal (| ignrAct skipCheck)
	)
)

(instance ship2 of View
	(properties
		x 9
		y 67
		noun N_SHIP2
		view 113
		cel 1
		signal (| ignrAct skipCheck)
	)
)

(instance ship3 of View
	(properties
		x 2
		y 118
		noun N_SHIP3
		view 113
		cel 2
		signal (| ignrAct skipCheck)
	)
)

(instance exitToCrest of View
	(properties
		x 165
		y 113
		noun N_HALL
		approachX 170
		approachY 110
		view 110
		loop 1
		priority 5
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: sExitToCrest)
			)
			(4
				(curRoom setScript: sExitToCrest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance extraPanel of View
	(properties
		x 245
		y 135
		noun N_PANEL
		view 110
		loop 11
		priority 14
		signal ignrAct
	)
)

(instance ratHole of Feature
	(properties
		noun N_RATHOLE
		nsTop 158
		nsLeft 278
		nsBottom 168
		nsRight 289
	)
)
