;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include sci.sh)
(use Main)
(use VelocityMover)
(use genetix)
(use Scaler)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	rm740 0
	sUseComm 20
)

(local
	[local0 4]
	local4
)
(instance theMusic3 of Sound
	(properties)
)

(instance rm740 of Rm
	(properties
		noun 8
		picture 113
	)
	
	(method (init)
		(LoadMany 143 number)
		(self setRegions: 31)
		(LoadMany 128 626 612)
		(bigRock init:)
		(bin init:)
		(machinery init:)
		(path init:)
		(sky init:)
		(palmTree init:)
		(lever init:)
		(flies init: setCycle: Fwd)
		(if (Btst 22)
			(theMusic2 number: 600 loop: -1 play:)
			(theMusic2 setVol: (Min 127 (Max 30 (- (ego y?) 32))))
			(exit730 init:)
			(walkHandler addToFront: self)
			(walkHandler addToFront: exit730)
			(if (== prevRoomNum 730)
				(curRoom setScript: sFly730)
			else
				(curRoom setScript: sFly730)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							110
							169
							276
							169
							272
							116
							180
							102
							170
							93
							166
							108
							176
							122
							110
							122
							93
							119
							69
							124
							0
							115
							2
							144
							104
							169
						yourself:
					)
			)
			(if (== prevRoomNum 730)
				(curRoom setScript: sHuman730)
			else
				(curRoom setScript: sHuman760)
			)
		)
		(super init:)
		(theGame handsOn:)
	)
	
	(method (doit)
		(if (Btst 22)
			(ego setLoop: (/ (+ (ego heading?) 90) 180))
			(theMusic2 setVol: (Min 127 (Max 30 (- (ego y?) 32))))
		)
		(if (and (not script) (not (Btst 22)))
			(switch (ego onControl: 1)
				(2
					(curRoom setScript: (ScriptID 31 3) 0 4)
				)
				(4
					(curRoom setScript: (ScriptID 31 3) 0 3)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: egoBody)
		(walkHandler delete: self)
		(walkHandler delete: exit730)
		(if local4 (Bset 23))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (Btst 22)
			(switch theVerb
				(3
					(proc31_2 mouseY)
					(if (< 10 mouseX)
						(ego setMotion: VelocityMover mouseX mouseY self 0)
					else
						(curRoom setScript: sExitLeft)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance sExitLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: VelocityMover -200 100 0 1)
				(= seconds 2)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 760)
			)
		)
	)
)

(instance sDoTrash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 117)
				(= seconds 2)
				(proc31_2 105)
				(ego setMotion: VelocityMover 167 105 0 0)
			)
			(1
				(myCliffy
					init:
					setPri: 1
					posn: 130 105
					setCycle: Walk
					setMotion: MoveTo 182 133 self
				)
			)
			(2
				(myCliffy setPri: 14 setMotion: MoveTo 151 134 self)
			)
			(3
				(myCliffy setMotion: MoveTo 108 134 self)
			)
			(4
				(myCliffy setMotion: MoveTo 108 124 self)
			)
			(5
				(myCliffy view: 612 setLoop: 2)
				(= cycles 3)
			)
			(6
				(myCliffy cel: 1)
				(= ticks 30)
			)
			(7
				(myCliffy cel: 2)
				(= ticks 30)
			)
			(8
				(myCliffy cel: 3)
				(lever setCel: 1)
				(= ticks 30)
			)
			(9
				(myCliffy setCel: 4)
				(lever setCel: 2)
				(= ticks 30)
			)
			(10
				(myCliffy cel: 5)
				(= ticks 30)
			)
			(11
				(theMusic3 init: number: 244 setLoop: 1 play:)
				(trash init: setCycle: End self)
			)
			(12
				(trash addToPic: dispose:)
				(egoBody
					view: 612
					x: 144
					y: 135
					init:
					setPri: 12
					setLoop: 1
				)
				(= seconds 2)
			)
			(13
				(egoBody cel: 0 setCycle: End self)
			)
			(14
				(egoBody setLoop: 6 setCycle: End self)
			)
			(15
				(myCliffy
					view: 20
					setHeading: 90
					setScale: Scaler 100 61 132 102
					setCycle: StopWalk -1
				)
				(= seconds 2)
			)
			(16
				(myCliffy setMotion: MoveTo 100 139 self)
			)
			(17
				(myCliffy setMotion: MoveTo 118 139 self)
			)
			(18
				(messager say: 3 1 0 0 self)
			)
			(19
				(walkHandler addToFront: egoBody)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sReEnergize of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: VelocityMover 140 105 self 1)
				(SolvePuzzle 237 10)
			)
			(1 (= seconds 1))
			(2 (messager say: 3 0 0 0 self))
			(3 (= seconds 2))
			(4
				(ego dispose:)
				(theMusic3 number: 260 setLoop: 1 play:)
				(theMusic2 stop:)
				(egoBody
					view: 604
					setLoop: 0
					cel: 15
					setScale: Scaler 100 61 132 102
					setCycle: Beg self
				)
			)
			(5 (egoBody setCycle: End self))
			(6
				(theMusic3 play:)
				(egoBody setCycle: End self)
			)
			(7 (egoBody setCycle: Beg self))
			(8
				(egoBody dispose:)
				(= seconds 2)
			)
			(9
				(theMusic3 play:)
				(NormalEgo 6)
				(ego
					init:
					cel: 0
					posn: 144 135
					setMotion: 0
					scaleX: 128
					scaleY: 128
					loop: 0
					setPri: 12
					setScale: Scaler 100 61 132 102
					setCycle: End self
				)
			)
			(10
				(Bclr 22)
				(theMusic3 dispose:)
				(= local4 1)
				(walkHandler delete: self)
				(walkHandler delete: exit730)
				(NormalEgo 0 2)
				(SolvePuzzle 236 50)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								110
								169
								276
								169
								272
								116
								170
								93
								166
								108
								176
								122
								110
								122
								93
								119
								69
								124
								0
								115
								2
								144
								104
								169
							yourself:
						)
				)
				(ego setHeading: 270 self)
				(myCliffy setPri: -1)
			)
			(11
				(messager say: 7 0 0 0 self)
			)
			(12
				(myCliffy setMotion: PolyPath 167 114 self)
			)
			(13
				(theGame handsOn:)
				(myCliffy setMotion: MoveTo 127 103 self)
			)
			(14 (self dispose:))
		)
	)
)

(instance sHuman730 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(NormalEgo 0)
				(ego
					init:
					setScale: Scaler 100 61 132 102
					posn: 127 104
					setMotion: MoveTo 192 121 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFly730 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(NormalFlyEgo 110 110)
				(cond 
					((not (Btst 79)) (theGame handsOn:) (self dispose:))
					((Btst 117)
						(myCliffy
							init:
							posn: 118 139
							setHeading: 90
							setScale: Scaler 100 61 132 102
							setCycle: StopWalk -1
						)
						(egoBody
							view: 612
							x: 144
							y: 135
							cel: 7
							init:
							setPri: 12
							setLoop: 6
						)
						(trash init: addToPic:)
						(lever init:)
						(walkHandler addToFront: egoBody)
						(= cycles 1)
					)
					(else (= next sDoTrash) (= cycles 2))
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sHuman760 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(NormalEgo 0)
				(ego
					init:
					setScale: Scaler 100 61 132 102
					posn: -20 123
					setMotion: MoveTo 44 128 self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFlyLeave730 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: VelocityMover 155 80 0 0)
				(= seconds 4)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 730)
			)
		)
	)
)

(instance myCliffy of MyActor
	(properties
		x -10
		y 127
		view 20
		loop 2
		priority 14
		signal $4010
	)
)

(instance lever of MyProp
	(properties
		x 121
		y 94
		view 612
		loop 4
		priority 8
		signal $4010
	)
)

(instance trash of MyActor
	(properties
		x 131
		y 123
		z 30
		view 612
		loop 3
		priority 8
		signal $4010
	)
)

(instance exit730 of MyFeature
	(properties
		x 271
		y 89
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(proc31_2 mouseY)
				(curRoom setScript: sFlyLeave730)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bigRock of MyFeature
	(properties
		x 62
		y 52
		noun 1
		onMeCheck $0100
	)
)

(instance bin of MyFeature
	(properties
		x 131
		y 99
		noun 2
		onMeCheck $0040
	)
)

(instance machinery of MyFeature
	(properties
		x 100
		y 181
		noun 5
		onMeCheck $0010
	)
)

(instance path of MyFeature
	(properties
		x 165
		y 128
		noun 6
		onMeCheck $0020
	)
)

(instance sky of MyFeature
	(properties
		x 210
		y 22
		noun 9
		onMeCheck $0080
	)
)

(instance palmTree of MyFeature
	(properties
		x 253
		y 71
		noun 10
		onMeCheck $0008
	)
)

(instance egoBody of MyActor
	(properties
		noun 2
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: sReEnergize)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flies of MyProp
	(properties
		x 90
		y 62
		noun 4
		view 612
		loop 5
		cel 4
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 22)
					(messager say: 4 1 1 0)
				else
					(messager say: 4 1 2 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sUseComm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 14 loop: 0 cel: 0 setCycle: End self)
				(theMusic2 number: 603 setLoop: 1 play:)
			)
			(1
				(messager say: 4 32 4 0 self 701)
			)
			(2 (ego setCycle: Beg self))
			(3
				(NormalEgo 0)
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)
