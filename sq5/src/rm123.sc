;;; Sierra Script 1.0 - (do not remove this comment)
(script# 123)
(include game.sh) (include "123.shm")
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm123 0
)

(instance rm123 of Room
	(properties
		picture 21
		style (| BLACKOUT FADEOUT)
		horizon -20
		vanishingX 140
		vanishingY -5
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(LoadMany RES_VIEW 157 127 110 108 113)
		(LoadMany RES_MESSAGE 123)
		(if (Btst fSawArgument)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PContainedAccess
						init:
							36 189
							287 189
							245 153
							228 143
							203 138
							206 128
							194 124
							182 123
							163 120
							160 111
							143 105
							136 104
							137 108
							133 115
							104 144
							71 171
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PContainedAccess
						init:
							36 189
							287 189
							245 153
							228 143
							204 142
							201 134
							210 130
							186 120
							157 109
							143 105
							136 104
							137 108
							133 115
							104 144
							71 171
						yourself:
					)
			)
		)
		(classDoor init: approachVerbs: V_DO stopUpd:)
		(doorFrameLeft init: addToPic:)
		(doorFrameRight init: addToPic:)
		(extraPanel init: addToPic:)
		(bboard init: addToPic:)
		(ship1 init: addToPic:)
		(ship2 init: addToPic:)
		(ship3 init: addToPic:)
		(shipLight1 setCycle: Forward init:)
		(shipLight2 setCycle: Forward init:)
		(shipLight3 init: setScript: sShipLights)
		(guy1 setCycle: Forward init:)
		(guy2 setCycle: Forward init:)
		(guy3 setCycle: Forward init:)
		(guy4 init:)
		(guy5 init:)
		(guy6 init:)
		(guy7 init: setScript: sBayGuys)
		(NormalEgo 1)
		(ego setScale: Scaler 130 24 157 106)
		(switch prevRoomNum
			(122
				(ego init:)
				(curRoom setScript: (ScriptID rgStarCon 5))
			)
			(135
				(curRoom setScript: sLeaveClassroom)
			)
			(else 
				(ego init:)
				(curRoom setScript: (ScriptID rgStarCon 6))
			)
		)
		(super init:)
		(if (Btst fSawArgument)
			(cadet1 init: setScript: sCadet1Move)
			(cadet2 init: setScript: sCadet2Move)
			(cadet3 init: setScript: sCadet3Move)
			(cadet4 init:)
			(cadet5 init:)
		)
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
				(curRoom setScript: (ScriptID 109 3) 0 122)
			)
			((<= (ego y?) 106) (curRoom setScript: (ScriptID 109 4) 0 125))
		)
	)
)

(instance sLeaveClassroom of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(classDoor setCycle: EndLoop self)
			)
			(2
				(theMusic2 number: 103 setLoop: 1 play:)
				(ego
					setPri: 8
					loop: 1
					posn: 222 122
					init:
					setMotion: MoveTo 183 130 self
				)
			)
			(3
				(classDoor setCycle: BegLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(4
				(classDoor stopUpd:)
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCadet1Move of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (cadet1 setCycle: EndLoop self))
			(1 (= ticks (Random 90 360)))
			(2 (cadet1 setCycle: BegLoop self))
			(3 (= ticks (Random 60 100)))
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance sCadet2Move of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (cadet2 setCycle: EndLoop self))
			(1 (= ticks (Random 120 240)))
			(2 (cadet2 setCycle: BegLoop self))
			(3 (= ticks (Random 120 240)))
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance sCadet3Move of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (cadet3 setCycle: EndLoop self))
			(1 (= ticks (Random 120 240)))
			(2 (cadet3 setCycle: BegLoop self))
			(3 (= ticks (Random 120 240)))
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance sRogGetScore of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 173 119 self)
			)
			(1
				(ego
					view: 127
					loop: 1
					cel: 0
					x: 173
					y: 119
					setScale: 0
					setCycle: EndLoop self
				)
			)
			(2 (= seconds 3))
			(3
				(curRoom newRoom: 166)
				(self dispose:)
			)
		)
	)
)

(instance sGoIntoClass of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(classDoor setCycle: EndLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(1
				(ego setPri: 8 setMotion: MoveTo 225 122 self)
			)
			(2
				(classDoor setCycle: BegLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(3
				(curRoom newRoom: 135)
				(theGame handsOn:)
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
					setLoop: 8
					setCycle: Forward
					setMotion: MoveTo 50 122 self
				)
			)
			(2
				(guy7 setLoop: 9 setCycle: EndLoop self)
			)
			(3
				(guy7 setLoop: 10 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(4
				(guy4 setLoop: 4 setCel: 0 setMotion: MoveTo 90 104)
				(guy1 setCycle: 0)
				(= seconds (Random 4 7))
			)
			(5
				(guy5 setLoop: 5 setCel: 0 setMotion: MoveTo 0 128)
				(guy1 setCycle: Forward)
				(= seconds (Random 4 6))
			)
			(6
				(guy6 setMotion: MoveTo 0 66)
				(guy2 setCycle: 0)
				(= seconds (Random 4 6))
			)
			(7
				(guy4 setLoop: 5 setMotion: MoveTo 1 75)
				(guy3 setCycle: 0)
				(= seconds (Random 3 5))
			)
			(8
				(guy6 setMotion: MoveTo 87 114)
				(guy2 setCycle: Forward)
				(guy3 setCycle: Forward)
				(= seconds (Random 3 5))
			)
			(9
				(guy5 setLoop: 4 setMotion: MoveTo 53 145)
				(= seconds (Random 4 7))
			)
			(10
				(= state (Random 3 5))
				(= cycles 1)
			)
		)
	)
)

(instance sShipLights of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(shipLight4 init:)
				(shipLight5 init:)
				(shipLight6 init:)
				(shipLight7 init:)
				(= cycles 1)
			)
			(1
				(shipLight3 hide:)
				(shipLight4 hide:)
				(shipLight5 show:)
				(shipLight6 show:)
				(shipLight7 show:)
				(= seconds 3)
			)
			(2
				(shipLight3 show:)
				(shipLight4 show:)
				(shipLight5 hide:)
				(shipLight6 hide:)
				(shipLight7 hide:)
				(= seconds 2)
			)
			(3
				(= state 0)
				(= cycles 1)
			)
		)
	)
)

(instance cadet1 of Prop
	(properties
		x 174
		y 117
		noun N_CADETS
		view 157
		loop 1
		signal ignrAct
		detailLevel 2
	)
)

(instance cadet2 of Prop
	(properties
		x 185
		y 119
		noun N_CADETS
		view 157
		signal ignrAct
		detailLevel 2
	)
)

(instance cadet3 of Prop
	(properties
		x 168
		y 118
		noun N_CADETS
		view 157
		loop 2
		signal ignrAct
		detailLevel 2
	)
)

(instance classDoor of Prop
	(properties
		x 212
		y 127
		noun N_CLASS_DOOR
		approachX 208
		approachY 126
		view 110
		loop 5
		priority 7
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (Btst fAttendedClass))
					((ScriptID rgStarCon 1) dispose:)
					(curRoom setScript: sGoIntoClass)
				else
					(messager say: N_CLASS_DOOR V_DO C_AFTER_CLASS 0)
				)
			)
			(V_LOOK
				(if (Btst fAttendedClass)
					(messager say: N_CLASS_DOOR V_LOOK C_AFTER_CLASS 0)
				else
					(messager say: N_CLASS_DOOR V_LOOK C_BEFORE_CLASS 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance guy1 of Prop
	(properties
		x 57
		y 109
		noun N_ASTROMECHS
		view 108
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy2 of Prop
	(properties
		x 24
		y 101
		noun N_ASTROMECHS
		view 108
		loop 7
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy3 of Prop
	(properties
		x 17
		y 108
		noun N_ASTROMECHS
		view 108
		loop 2
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy4 of Actor
	(properties
		x 1
		y 75
		noun N_ASTROMECHS
		yStep 1
		view 108
		loop 4
		priority 4
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy5 of Actor
	(properties
		x 53
		y 145
		noun N_ASTROMECHS
		yStep 1
		view 108
		loop 5
		priority 4
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy6 of Actor
	(properties
		x 87
		y 114
		noun N_ASTROMECHS
		yStep 1
		view 108
		loop 11
		priority 4
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy7 of Actor
	(properties
		x 113
		y 108
		noun N_ASTROMECHS
		yStep 1
		view 108
		loop 8
		priority 4
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance shipLight1 of Prop
	(properties
		x 6
		y 48
		noun N_SHIP1
		view 113
		loop 10
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 12
		detailLevel 2
	)
)

(instance shipLight2 of Prop
	(properties
		x 63
		y 75
		noun N_SHIP3
		view 113
		loop 12
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 9
		detailLevel 2
	)
)

(instance shipLight3 of Prop
	(properties
		x 5
		y 137
		noun N_SHIP2
		view 113
		loop 11
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance shipLight4 of Prop
	(properties
		x 64
		y 136
		noun N_SHIP2
		view 113
		loop 11
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance shipLight5 of Prop
	(properties
		x 5
		y 93
		noun N_SHIP2
		view 113
		loop 11
		cel 1
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance shipLight6 of Prop
	(properties
		x 63
		y 92
		noun N_SHIP2
		view 113
		loop 11
		cel 1
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance shipLight7 of Prop
	(properties
		x 33
		y 40
		noun N_SHIP2
		view 113
		loop 11
		cel 1
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance cadet4 of View
	(properties
		x 183
		y 116
		noun N_CADETS
		view 157
		loop 3
		signal ignrAct
	)
)

(instance cadet5 of View
	(properties
		x 180
		y 120
		noun N_CADETS
		view 157
		loop 4
		signal ignrAct
	)
)

(instance doorFrameLeft of View
	(properties
		x 212
		y 127
		noun N_LOCKER
		view 110
		loop 3
		priority 7
		signal (| ignrAct fixPriOn)
	)
)

(instance doorFrameRight of View
	(properties
		x 212
		y 127
		noun N_LOCKER
		view 110
		loop 4
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance ship1 of View
	(properties
		x 10
		y 40
		noun N_SHIP1
		view 113
		loop 2
		signal (| ignrAct skipCheck)
	)
)

(instance ship2 of View
	(properties
		x 6
		y 92
		noun N_SHIP2
		view 113
		loop 2
		cel 1
		signal (| ignrAct skipCheck)
	)
)

(instance ship3 of View
	(properties
		x 65
		y 73
		noun N_SHIP3
		view 113
		loop 2
		cel 2
		signal (| ignrAct skipCheck)
	)
)

(instance bboard of View
	(properties
		x 185
		y 105
		noun N_BOARD
		view 110
		loop 2
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (Btst fSawArgument)
					(curRoom setScript: sRogGetScore)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(if (Btst fSawArgument)
					(curRoom setScript: sRogGetScore)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance extraPanel of View
	(properties
		x 250
		y 124
		noun N_LOCKER
		view 110
		loop 9
		priority 5
		signal (| ignrAct fixPriOn)
	)
)
