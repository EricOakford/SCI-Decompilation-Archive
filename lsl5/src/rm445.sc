;;; Sierra Script 1.0 - (do not remove this comment)
(script# 445)
(include game.sh)
(use Main)
(use LLRoom)
(use Polygon)
(use Feature)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm445 0
)

(instance rm445 of LLRoom
	(properties
		lookStr {The F.B.I. laboratory is completely deserted. Perhaps they had more experiments than experimenters?}
		picture 440
		east 450
		west 435
	)
	
	(method (init)
		(Load VIEW 441)
		(ego init: normalize:)
		(if (== ((Inventory at: iDataMan) owner?) 445)
			(theDataMan init: stopUpd: approachVerbs: verbDo)
		)
		(if (== ((Inventory at: iBiazPak) owner?) 445)
			(dataPak init: stopUpd: approachVerbs: verbDo)
		)
		(if (== ((Inventory at: iHammerPak) owner?) 445)
			(dataPak2 init: stopUpd: approachVerbs: verbDo)
		)
		(machine init:)
		(desk init:)
		(computer init:)
		(contraption init:)
		(southBank init:)
		(door init:)
		(books init:)
		(duckF init:)
		(switch prevRoomNum
			(west
				(HandsOn)
				(self style: SCROLLLEFT)
			)
			(else 
				(HandsOn)
				(ego posn: 278 104 normalize: edgeHit: 0 setHeading: 270)
			)
		)
		(super init:)
		(HandsOn)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 140
						136 140
						161 150
						190 150
						231 130
						251 138
						305 125
						293 113
						263 99
						0 99
						0 0
						319 0
						319 189
					yourself:
				)
		)
		(theMusic2 number: 436 setLoop: -1 play:)
	)
)

(instance sGetData of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 441
					setLoop: (if register 1 else 0)
					setCel: 0
					cycleSpeed: 12
					moveSpeed: 12
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(switch register
					(0
						(ego get: iDataMan)
						(SolvePuzzle 5 fGotDataMan)
						(theDataMan dispose:)
					)
					(1
						(ego get: iBiazPak)
						(SolvePuzzle 13 fGotBiazFax)
						(dataPak dispose:)
					)
					(2
						(ego get: iHammerPak)
						(SolvePuzzle 13 fGotHammerFax)
						(dataPak2 dispose:)
					)
				)
				(ego setCycle: EndLoop self)
			)
			(2
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance theDataMan of View
	(properties
		x 94
		y 88
		z 13
		description {the DataMan}
		approachX 82
		approachY 99
		lookStr {A tiny electronic display device lies on the counter.}
		view 440
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetData 0 0)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance dataPak of View
	(properties
		x 72
		y 88
		z 14
		description {the DataPak}
		approachX 82
		approachY 99
		lookStr {What appears to be some sort of ROM cartridge lies on the counter.}
		view 440
		cel 1
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetData 0 1)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance dataPak2 of View
	(properties
		x 72
		y 88
		z 12
		description {the DataPak}
		approachX 82
		approachY 99
		lookStr {What appears to be some sort of ROM cartridge lies on the counter.}
		view 440
		cel 2
		priority 6
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetData 0 2)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance door of View
	(properties
		x 282
		y 99
		description {Doctor Phil Hopian's door}
		lookStr {This door leads back into Dr. Phil Hopian's office. After what you just went through, you're in no hurry to go back in there!}
		view 440
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 445 0)
			)
			(verbTalk
				(TimePrint 445 1)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance machine of Feature
	(properties
		x 215
		y 47
		nsTop -1
		nsLeft 170
		nsBottom 95
		nsRight 261
		description {the machine}
		sightAngle 40
		lookStr {This machine does something, you feel quite certain.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 445 2)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance computer of Feature
	(properties
		x 133
		y 91
		z 33
		nsTop 45
		nsLeft 119
		nsBottom 71
		nsRight 148
		description {the computer}
		sightAngle 40
		lookStr {If only you had paid attention when Larry chattered on and on about his computer. You know nothing about computers and are sure you'll be unable to do anything with it.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 445 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance contraption of Feature
	(properties
		x 114
		y 160
		z 34
		nsTop 115
		nsLeft 94
		nsBottom 137
		nsRight 134
		description {the chemistry set}
		sightAngle 40
		lookStr {This is where the technician made the flatulence powder. (Un)Fortunately, he left his equipment thoroughly clean.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 445 4)
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
		y 166
		nsTop 144
		nsBottom 189
		nsRight 319
		description {the workbench}
		sightAngle 40
		lookStr {Lots of chemicals, beakers, and junk food wrappers fill this workbench.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (or (Btst fSeeVibratorDemonstration) (Btst fSeeBraDemonstration) (Btst fSeeFartDemonstration))
					(TimePrint 445 5)
				else
					(TimePrint 445 6)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance books of Feature
	(properties
		x 41
		y 88
		z 30
		nsTop 49
		nsLeft 28
		nsBottom 68
		nsRight 55
		description {the books}
		sightAngle 40
		lookStr {These books are filled with Latin phrases. The only Latin phrases you know would get a sailor thrown out of a bar in Tijuana!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 445 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance desk of Feature
	(properties
		x 81
		y 87
		z 17
		nsTop 54
		nsBottom 88
		nsRight 162
		description {the desk}
		sightAngle 40
		lookStr {This desk doesn't contain any secret documents; they all were burned in the big fire last week!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 445 8)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance duckF of Feature
	(properties
		x 43
		y 189
		z 42
		nsTop 134
		nsLeft 23
		nsBottom 160
		nsRight 63
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb verbLook)
			(TimePrint 445 9)
		else
			(southBank doVerb: theVerb theItem)
		)
	)
)
