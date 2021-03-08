;;; Sierra Script 1.0 - (do not remove this comment)
(script# 122)
(include game.sh) (include "122.shm")
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
	rm122 0
)

(local
	roomPts
)
(instance rm122 of Room
	(properties
		picture 21
		style (| BLACKOUT FADEOUT)
		horizon -20
		vanishingX 140
		vanishingY -5
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(LoadMany RES_VIEW 111 112 113 108 110)
		(LoadMany RES_MESSAGE 122)
		(if (Btst fAttendedClass)
			(curRoom
				addObstacle:
					((= roomPts (Polygon new:))
						type: PContainedAccess
						init:
							36 189
							287 189
							245 153
							219 134
							186 120
							157 109
							143 105
							136 104
							137 108
							133 115
							129 122
							151 126
							141 135
							104 144
							71 171
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((= roomPts (Polygon new:))
						type: PContainedAccess
						init:
							36 189
							287 189
							245 153
							219 134
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
		(super init:)
		(walkHandler addToFront: closetDoor)
		(NormalEgo 1)
		(ego setScale: Scaler 130 24 157 106 init:)
		(switch prevRoomNum
			(123
				(exitToSim init: addToPic:)
				(curRoom setScript: (ScriptID rgStarCon 6))
			)
			(121
				(exitToSim init: addToPic:)
				(curRoom setScript: (ScriptID rgStarCon 5))
			)
			(else 
				(exitToSim init:)
				(curRoom setScript: sEnterFromSim)
			)
		)
		(extraPanel init: approachVerbs: V_DO addToPic:)
		(if (and (Btst 163) (Btst 164))
			(closetDoor
				init:
				approachVerbs: V_DO
				ignoreActors: TRUE
				addToPic:
			)
		else
			(closetDoor
				init:
				approachVerbs: V_DO
				ignoreActors: TRUE
				stopUpd:
			)
		)
		(closetLeftEdge init: addToPic:)
		(closetRightEdge init: addToPic:)
		(ship1 init: addToPic:)
		(ship2 init: addToPic:)
		(ship3 init: addToPic:)
		(shipLight1 setCycle: Forward init:)
		(shipLight2 setCycle: Forward init:)
		(shipLight3 setCycle: Forward init:)
		(guy1 setCycle: Forward init:)
		(guy2 setCycle: Forward init:)
		(guy4 init:)
		(guy5 init:)
		(guy6 init:)
		(guy3 init: setScript: sBayGuys)
		(if (or (Btst fConesOnFloor) (Btst fScrubberOnFloor))
			(closetDoor setCel: (closetDoor lastCel:))
			(closetGarbage init:)
			((curRoom obstacles?) delete: roomPts)
			(roomPts dispose:)
			(curRoom
				addObstacle:
					((= roomPts (Polygon new:))
						type: PContainedAccess
						init:
							36 189
							287 189
							245 153
							212 149
							194 133
							186 120
							157 109
							143 105
							136 104
							137 108
							133 115
							129 122
							151 126
							141 135
							104 144
							71 171
						yourself:
					)
			)
			(if (Btst fScrubberOnFloor)
				(bucket setCel: 9 init:)
			)
			(if (Btst fConesOnFloor)
				(cones setCel: 5 init:)
			)
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
				(curRoom setScript: (ScriptID rgStarCon 3) 0 121)
			)
			((< (ego y?) 110)
				(curRoom setScript: (ScriptID rgStarCon 4) 0 123)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: closetDoor)
		(super dispose: &rest)
	)
)

(instance sEnterFromSim of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setPri: 14 setHeading: 270)
				(= seconds 1)
			)
			(1
				(exitToSim setCycle: EndLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(2
				(ego
					posn: 162 111
					setScale: Scaler 130 24 157 106
					init:
					setMotion: MoveTo 149 115 self
				)
			)
			(3
				(ego setPri: -1 setHeading: 180)
				(exitToSim setCycle: BegLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(4
				(exitToSim addToPic:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGarbageFalls of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset fJanitorClosetOpen)
				(Bset fConesOnFloor)
				(Bset fScrubberOnFloor)
				(ego setMotion: MoveTo 238 140 self)
			)
			(1
				(closetDoor setCycle: EndLoop self)
				(theMusic2 number: 103 loop: 1 play:)
			)
			(2
				(ego
					view: 112
					setLoop: 1
					cel: 0
					setScale: 0
					x: 238
					y: 140
					cycleSpeed: 10
					setCycle: EndLoop self
				)
				(theMusic2 number: 116 setLoop: 1 play:)
				(cones init: setCycle: EndLoop)
				(bucket init: setCycle: EndLoop)
			)
			(3
				(theMusic2 number: 102 setLoop: 1 play:)
				(= seconds 4)
			)
			(4
				(closetGarbage init:)
				(ego
					setLoop: 2
					setCel: 0
					posn: 227 142
					setCycle: EndLoop self
				)
			)
			(5
				(NormalEgo 1 0)
				(ego
					posn: 240 142
					loop: 0
					cel: 0
					setScale: Scaler 130 24 157 106
					setMotion: MoveTo 200 142 self
				)
			)
			(6
				(ego setHeading: 90)
				((curRoom obstacles?) delete: roomPts)
				(roomPts dispose:)
				(curRoom
					addObstacle:
						((= roomPts (Polygon new:))
							type: PContainedAccess
							init:
								36 189
								287 189
								245 153
								212 149
								194 133
								186 120
								157 109
								143 105
								136 104
								137 108
								133 115
								129 122
								151 126
								141 135
								104 144
								71 171
							yourself:
						)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKickGarbage of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(closetGarbage ignoreActors:)
				(ego setMotion: MoveTo 213 145 self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(ego
					view: 112
					setLoop: 3
					cel: 0
					setScale: 0
					posn: 204 145
				)
				(= ticks 4)
			)
			(3 (ego setCycle: CycleTo 8 1 self))
			(4
				(ego setCycle: EndLoop self)
				(closetGarbage setCel: 2 posn: 245 164)
				(theMusic2 number: 117 setLoop: 1 play:)
			)
			(5
				(closetGarbage setCel: 3 posn: 254 164)
				(theMusic2 number: 117 setLoop: 1 play:)
				(ego setCel: 8 setCycle: EndLoop self)
			)
			(6
				(closetGarbage setCel: 4 posn: 274 164)
				(theMusic2 number: 117 setLoop: 1 play:)
				(ego setCel: 8 setCycle: EndLoop self)
			)
			(7
				(closetDoor setCycle: BegLoop self)
				(closetGarbage dispose:)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(8
				(closetDoor addToPic:)
				(NormalEgo 1)
				(ego
					posn: 219 143
					setHeading: 90
					setScale: Scaler 130 24 157 106
				)
				((curRoom obstacles?) delete: roomPts)
				(roomPts dispose:)
				(curRoom
					addObstacle:
						((= roomPts (Polygon new:))
							type: PContainedAccess
							init:
								36 189
								287 189
								245 153
								219 134
								186 120
								157 109
								143 105
								136 104
								137 108
								133 115
								129 122
								151 126
								141 135
								104 144
								71 171
							yourself:
						)
				)
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
				(guy5
					setLoop: 8
					setCycle: Forward
					setMotion: MoveTo 66 94 self
				)
			)
			(2
				(guy5 setLoop: 9 setCycle: EndLoop self)
			)
			(3
				(guy5 setLoop: 10 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(4
				(guy3 setLoop: 4 setCel: 0 setMotion: MoveTo 87 112)
				(guy2 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(5
				(guy4 setLoop: 5 setCel: 0 setMotion: MoveTo 11 63)
				(= seconds (Random 2 5))
			)
			(6
				(guy6 setMotion: MoveTo 83 120)
				(guy1 setCycle: 0)
				(= seconds (Random 4 6))
			)
			(7
				(guy3 setLoop: 5 setMotion: MoveTo 1 78)
				(= seconds (Random 3 5))
			)
			(8
				(guy6 setMotion: MoveTo 4 67)
				(guy2 setCycle: 0)
				(= seconds (Random 1 3))
			)
			(9
				(guy4 setLoop: 4 setMotion: MoveTo 89 55)
				(guy1 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(10
				(= state (Random 3 5))
				(= cycles 1)
			)
		)
	)
)

(instance sPickupScrubber of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 196 137 self)
			)
			(1
				(ego view: 19 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(bucket dispose:)
				(Bclr fScrubberOnFloor)
				(ego get: iFloorScrubber)
				(SolvePuzzle f122GetScrubber 10)
				(messager say: N_SCRUBBER V_DO NULL 0 self)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(NormalEgo 1 0)
				(if (ego has: iSafetyCones)
					(= next sKickGarbage)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance sPickupCones of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 189 133 self)
			)
			(1
				(ego view: 19 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(cones dispose:)
				(Bclr fConesOnFloor)
				(ego get: iSafetyCones)
				(SolvePuzzle f122GetCones 10)
				(messager say: N_CONES V_DO NULL 0 self)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo 1 0)
				(if (ego has: iFloorScrubber)
					(= next sKickGarbage)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance closetGarbage of Actor
	(properties
		x 237
		y 164
		z 20
		noun N_GARBAGE
		view 112
		loop 4
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance closetDoor of Prop
	(properties
		x 242
		y 142
		noun N_CLOSET
		approachX 210
		approachY 140
		view 112
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(cond 
					((and (not (Btst fJanitorClosetOpen)) (Btst fAttendedClass))
						(curRoom setScript: sGarbageFalls)
					)
					((== (closetDoor cel?) (closetDoor lastCel:))
						(messager say: N_CLOSET V_DO C_OPEN 0)
					)
					(else
						(messager say: N_CLOSET V_DO NULL 0)
					)
				)
			)
			(V_DO
				(cond 
					((and (not (Btst fJanitorClosetOpen)) (Btst fAttendedClass))
						(curRoom setScript: sGarbageFalls)
					)
					((== (closetDoor cel?) (closetDoor lastCel:))
						(messager say: N_CLOSET V_DO C_OPEN 0)
					)
					(else
						(messager say: N_CLOSET V_DO NULL 0)
					)
				)
			)
			(V_LOOK
				(if (== (closetDoor cel?) (closetDoor lastCel:))
					(messager say: N_CLOSET V_LOOK C_OPEN 0)
				else
					(messager say: N_CLOSET V_LOOK C_CLOSED 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bucket of Prop
	(properties
		x 230
		y 140
		noun N_SCRUBBER
		view 112
		loop 6
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sPickupScrubber)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cones of Prop
	(properties
		x 220
		y 134
		z 10
		noun N_CONES
		view 112
		loop 5
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sPickupCones)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exitToSim of Prop
	(properties
		x 165
		y 112
		noun N_SIM
		approachX 160
		approachY 108
		view 111
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance guy1 of Prop
	(properties
		x 22
		y 79
		noun N_GUYS
		view 108
		loop 1
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy2 of Prop
	(properties
		x 73
		y 77
		noun N_GUYS
		view 108
		loop 2
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance guy3 of Actor
	(properties
		x 1
		y 78
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

(instance guy4 of Actor
	(properties
		x 89
		y 55
		noun N_GUYS
		yStep 1
		view 108
		loop 5
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance guy5 of Actor
	(properties
		x 89
		y 116
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

(instance guy6 of Actor
	(properties
		x 4
		y 67
		noun N_GUYS
		yStep 1
		view 108
		loop 11
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
		xStep 1
	)
)

(instance shipLight1 of Prop
	(properties
		x 14
		y 135
		noun N_SHIP1
		view 113
		loop 7
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fCleanedCrest)
					(messager say: N_SHIP1 V_LOOK C_AFTER_BEA 0)
				else
					(messager say: N_SHIP1 V_LOOK C_BEFORE_BEA 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shipLight2 of Prop
	(properties
		x 15
		y 89
		noun N_SHIP2
		view 113
		loop 8
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 9
		detailLevel 2
	)
)

(instance shipLight3 of Prop
	(properties
		x 31
		y 61
		noun N_SHIP3
		view 113
		loop 9
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		detailLevel 2
	)
)

(instance ship1 of View
	(properties
		x 14
		y 131
		noun N_SHIP1
		view 113
		loop 1
		signal (| ignrAct skipCheck)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fCleanedCrest)
					(messager say: N_SHIP1 V_LOOK C_AFTER_BEA 0)
				else
					(messager say: N_SHIP1 V_LOOK C_BEFORE_BEA 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ship2 of View
	(properties
		x 2
		y 61
		noun N_SHIP2
		view 113
		loop 1
		cel 1
		signal (| ignrAct skipCheck)
	)
)

(instance ship3 of View
	(properties
		x 32
		y 55
		noun N_SHIP3
		view 113
		loop 1
		cel 2
		priority 5
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance closetLeftEdge of View
	(properties
		x 242
		y 151
		noun N_CLOSET
		approachX 240
		approachY 142
		view 112
		loop 8
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (and (not (Btst fJanitorClosetOpen)) (Btst fAttendedClass))
					(curRoom setScript: sGarbageFalls)
				else
					(messager say: N_CLOSET V_DO NULL 0)
				)
			)
			(V_LOOK
				(if (== (closetDoor cel?) (closetDoor lastCel:))
					(messager say: N_CLOSET V_LOOK C_OPEN 0)
				else
					(messager say: N_CLOSET V_LOOK C_CLOSED 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closetRightEdge of View
	(properties
		x 242
		y 151
		noun N_CLOSET
		approachX 240
		approachY 142
		view 112
		loop 7
		priority 11
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (and (not (Btst fJanitorClosetOpen)) (Btst fAttendedClass))
					(curRoom setScript: sGarbageFalls)
				else
					(messager say: N_CLOSET V_DO NULL 0)
				)
			)
			(V_LOOK
				(if (== (closetDoor cel?) (closetDoor lastCel:))
					(messager say: N_CLOSET V_LOOK C_OPEN 0)
				else
					(messager say: N_CLOSET V_LOOK C_CLOSED 0)
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
		x 210
		y 99
		noun N_PANEL
		approachX 203
		approachY 128
		view 110
		loop 10
		priority 8
		signal (| ignrAct fixPriOn)
	)
)
