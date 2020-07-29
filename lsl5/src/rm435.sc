;;; Sierra Script 1.0 - (do not remove this comment)
(script# 435)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm435 0
)

(instance rm435 of LLRoom
	(properties
		lookStr {Although you haven't really thought about, this isn't what you would have pictured an F.B.I. lab to be.}
		picture 430
		north 425
		east 445
	)
	
	(method (init)
		(ego init: normalize: 570)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0)
				(theMusic2 number: 436 loop: -1 play:)
			)
			(east
				(HandsOn)
				(self style: 11)
			)
			(west (ego edgeHit: 0))
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(northDoor init: stopUpd:)
		(if (not (ego has: 13))
			(hooterShooter init: stopUpd: approachVerbs: 3)
		)
		(door init:)
		(dartboard init:)
		(northBank init:)
		(southBank init:)
		(monitors init:)
		(plug init:)
		(outlet init:)
		(chair init:)
		(joystick init:)
		(circuitboard init:)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						116 0
						116 69
						116 80
						89 80
						9 131
						107 131
						111 128
						190 128
						190 136
						283 136
						294 147
						319 147
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						124 0
						319 0
						319 99
						138 99
						138 80
						124 80
					yourself:
				)
		)
	)
	
	(method (newRoom n)
		(if (== n (curRoom north?)) (theMusic2 fade:))
		(super newRoom: n)
	)
)

(instance sGetBra of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 441
					setLoop: 0
					setCel: 0
					cycleSpeed: 8
					moveSpeed: 8
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(SolvePuzzle 6 fGotHooterShooter)
				(hooterShooter dispose:)
				(ego get: iHooterShooter setCycle: EndLoop self)
			)
			(2
				(ego normalize:)
				(= ticks 67)
			)
			(3
				(TimePrint 435 0)
				(= seconds 3)
			)
			(4
				(TimePrint 435 1 #at -1 185)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance northDoor of Door
	(properties
		x 106
		y 77
		description {the exterior door}
		approachX 126
		approachY 85
		lookStr {This door leads to the street outside.}
		view 430
		entranceTo 425
		moveToX 120
		moveToY 75
		enterType 0
		exitType 0
	)
)

(instance hooterShooter of View
	(properties
		x 271
		y 96
		z 22
		description {the brassiere cannon}
		sightAngle 40
		approachX 261
		approachY 99
		lookStr {It appears the F.B.I. technician left his bra lying on the workbench!}
		view 430
		loop 3
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetBra)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance door of Feature
	(properties
		x 23
		y 80
		nsTop 47
		nsLeft 5
		nsBottom 114
		nsRight 42
		description {Desmond's door}
		sightAngle 40
		lookStr {This door leads to Inspector Desmond's office. It is presently locked.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 2)
			)
			(verbTalk
				(TimePrint 435 3)
				(TimePrint 435 4 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dartboard of Feature
	(properties
		x 64
		y 91
		z 40
		nsTop 37
		nsLeft 54
		nsBottom 65
		nsRight 75
		description {the dart board}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 435 5)
			)
			(verbDo
				(TimePrint 435 6)
				(TimePrint 435 7 #at -1 185 #width 280)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance northBank of Feature
	(properties
		x 234
		y 89
		z 55
		nsTop 1
		nsLeft 155
		nsBottom 77
		nsRight 319
		description {the laboratory tables}
		sightAngle 40
		lookStr {These tables are filled with highly-expensive scientific equipment, all paid for with your tax dollars!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 8)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance southBank of Feature
	(properties
		x 159
		y 125
		nsTop 118
		nsBottom 189
		nsRight 319
		description {the laboratory tables}
		sightAngle 40
		lookStr {These tables are filled with highly-expensive scientific equipment, all paid for with your tax dollars!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 8)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance monitors of Feature
	(properties
		x 190
		y 90
		z 49
		nsTop 24
		nsLeft 164
		nsBottom 59
		nsRight 216
		description {the monitors}
		sightAngle 40
		lookStr {These monitors are presently disconnected. When they were heavily used many years ago, it was only to play "Jawbreaker!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plug of Feature
	(properties
		x 83
		y 118
		z 100
		nsTop 3
		nsLeft 71
		nsBottom 33
		nsRight 96
		description {the giant plug}
		sightAngle 40
		lookStr {You wonder exactly what that giant plug plugs into.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 10)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance outlet of Feature
	(properties
		x 147
		y 77
		z 27
		nsTop 43
		nsLeft 142
		nsBottom 57
		nsRight 152
		description {the intercom}
		sightAngle 40
		lookStr {The lab technicians use this intercom to communicate with the front office and Commander Twit.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 11)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 169
		y 130
		nsTop 117
		nsLeft 152
		nsBottom 144
		nsRight 186
		description {the chair}
		sightAngle 40
		lookStr {This office chair prevents you from accessing the intricate equipment below it.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 12)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance joystick of Feature
	(properties
		x 121
		y 169
		z 34
		nsTop 126
		nsLeft 108
		nsBottom 145
		nsRight 135
		description {the joystick}
		sightAngle 40
		lookStr {Wow! What a joystick!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 13)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance circuitboard of Feature
	(properties
		x 68
		y 189
		z 9
		nsTop 172
		nsLeft 46
		nsBottom 189
		nsRight 91
		description {the circuit board}
		sightAngle 40
		lookStr {This looks like a giant circuit board.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 435 14)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
