;;; Sierra Script 1.0 - (do not remove this comment)
(script# 117)
(include game.sh) (include "117.shm")
(use Main)
(use FlickerCycler)
(use Talker)
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
	rm117 0
	guardTalker 2
)

(instance rm117 of Room
	(properties
		noun N_ROOM
		picture 29
		style (| BLACKOUT FADEOUT)
		horizon 91
		north 115
		west 121
		vanishingX 193
		vanishingY 91
	)
	
	(method (init)
		(curRoom
			setRegions: rgStarCon
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						191 185
						152 147
						250 147
						250 144
						150 142
						150 140
						148 128
						155 109
						172 100
						183 97
						204 95
						257 100
						263 88
						226 94
						227 93
						197 94
						155 98
						130 104
						101 113
						74 127
						58 136
						0 136
						0 185
					yourself:
				)
		)
		(warning init: stopUpd:)
		(ppLight setCycle: Forward init:)
		(tv setCycle: Oscillate init:)
		(lowLite1 setCycle: FlickerCycler init:)
		(lowLite2 setCycle: FlickerCycler init:)
		(hiLite1 setCycle: FlickerCycler init:)
		(hiLite2 setCycle: FlickerCycler init:)
		(hiLite3 setCycle: FlickerCycler init:)
		(hiLite4 setCycle: FlickerCycler init:)
		(hiLite5 setCycle: FlickerCycler init:)
		(hiLite6 setCycle: FlickerCycler init:)
		(hiLite7 setCycle: FlickerCycler init:)
		(bigmoon init: setOnMeCheck: ftrControl cBLUE)
		(space init: setOnMeCheck: ftrControl cYELLOW)
		(cStar init: setScript: sMoveStars)
		(lStar init:)
		(lift init: setOnMeCheck: ftrControl cCYAN)
		(Ghead init:)
		(machine init: setOnMeCheck: ftrControl cBROWN)
		(monitor init: setScript: sPlayMC)
		(skylight init: setOnMeCheck: ftrControl cLBLUE)
		(red_planet init: setOnMeCheck: ftrControl cWHITE)
		(lower_level init: setOnMeCheck: ftrControl cGREY)
		(cadet
			init:
			setMotion: MoveTo 316 185 cadet
			setCycle: Forward
			setLoop: 2
		)
		(LoadMany RES_VIEW 115)
		(super init:)
		(switch prevRoomNum
			(119
				(curRoom setScript: sFrom119)
			)
			(115
				(curRoom setScript: sFrom115)
			)
			(else 
				(NormalEgo 1 0)
				(ego
					posn: 10 140
					init:
					illegalBits: 0
					setScale: Scaler 100 16 155 92
					setMotion: MoveTo 106 140 self
				)
				(theGame handsOn:)
			)
		)
	)
	
	(method (doit)
		(if
			(and
				(IsObjectOnControl ego cRED)
				(not (curRoom script?))
			)
			(curRoom setScript: sDontGoThere)
		)
		(if
			(and
				(IsObjectOnControl ego cLGREEN)
				(not (curRoom script?))
			)
			(curRoom setScript: sDownTheDrain)
		)
		(if (and (< (ego y?) 103) (not (curRoom script?)))
			(curRoom setScript: sLeaveHi)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript FLICKER)
	)
)

(instance ppLight of Prop
	(properties
		x 231
		y 7
		view 114
		loop 13
		detailLevel 2
	)
)

(instance cStar of Actor
	(properties
		x 273
		y 9
		yStep 7
		view 114
		loop 5
		signal (| ignrAct ignrHrz fixedLoop)
		detailLevel 2
		illegalBits $0000
		xStep 7
		moveSpeed 0
	)
)

(instance lStar of Actor
	(properties
		x 34
		y 51
		yStep 1
		view 114
		loop 5
		cel 1
		signal (| ignrAct ignrHrz fixedLoop)
		detailLevel 2
		illegalBits $0000
		xStep 1
		moveSpeed 7
	)
)

(instance tv of Prop
	(properties
		x 306
		y 85
		view 114
		loop 7
		cycleSpeed 40
		detailLevel 2
	)
)

(instance lowLite1 of Prop
	(properties
		x 312
		y 141
		view 114
		loop 9
		priority 15
		signal fixPriOn
		detailLevel 2
	)
)

(instance lowLite2 of Prop
	(properties
		x 312
		y 153
		view 114
		loop 9
		cel 1
		priority 15
		signal fixPriOn
		detailLevel 2
	)
)

(instance hiLite1 of Prop
	(properties
		x 282
		y 22
		view 114
		loop 11
		detailLevel 2
	)
)

(instance hiLite2 of Prop
	(properties
		x 282
		y 27
		view 114
		loop 11
		cel 1
		detailLevel 2
	)
)

(instance hiLite3 of Prop
	(properties
		x 296
		y 17
		view 114
		loop 11
		cel 2
		detailLevel 2
	)
)

(instance hiLite4 of Prop
	(properties
		x 301
		y 22
		view 114
		loop 11
		cel 3
		detailLevel 2
	)
)

(instance hiLite5 of Prop
	(properties
		x 311
		y 17
		view 114
		loop 11
		cel 4
		detailLevel 2
	)
)

(instance hiLite6 of Prop
	(properties
		x 301
		y 24
		view 114
		loop 11
		cel 5
		detailLevel 2
	)
)

(instance hiLite7 of Prop
	(properties
		x 302
		y 27
		view 114
		loop 11
		cel 6
		detailLevel 2
	)
)

(instance Ghead of Prop
	(properties
		x 133
		y 160
		noun N_GUARD
		view 114
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance warning of Prop
	(properties
		x 29
		y 4
		view 114
		loop 4
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance cadet of Actor
	(properties
		x 197
		y 185
		noun N_CADET
		yStep 1
		view 114
		loop 2
		priority 5
		signal (| ignrAct ignrHrz fixPriOn)
		xStep 1
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance elevator of Actor
	(properties
		x 231
		y 144
		yStep 3
		view 115
		cel 1
		priority 12
		signal (| ignrAct fixPriOn)
		cycleSpeed 0
		illegalBits $0000
	)
	
	(method (doit)
		(if
			(and
				(< 211 (ego x?))
				(< (ego x?) 250)
				(< 134 (ego y?))
			)
			(self show:)
		else
			(self hide:)
		)
		(if (== (ego x?) 233)
			(self x: (ego x?) y: (ego y?))
		)
		(super doit: &rest)
	)
)

(instance lift of Feature
	(properties
		x 150
		y 190
		noun N_LIFT
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sDownTheDrain)
			)
			(V_WALK
				(curRoom setScript: sDownTheDrain)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bigmoon of Feature
	(properties
		x 34
		y 79
		noun N_MOON
		onMeCheck $0002
	)
)

(instance space of Feature
	(properties
		x 140
		y 9
		noun N_SPACE
		onMeCheck cYELLOW
	)
)

(instance sDontGoThere of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Ghead setCycle: BegLoop)
				(= cycles 3)
			)
			(1 (messager say: N_CANT_GO V_LOOK NULL 0 self))
			(2
				(warning startUpd: hide:)
				(= cycles 3)
			)
			(3
				(warning show:)
				(= cycles 3)
			)
			(4
				(warning hide:)
				(= cycles 3)
			)
			(5
				(warning show:)
				(= cycles 3)
			)
			(6
				(warning hide:)
				(= cycles 3)
			)
			(7
				(warning show: stopUpd:)
				(Ghead setCycle: EndLoop)
				(ego setMotion: MoveTo 113 130 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLeaveHi of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 181 98 self)
			)
			(1 (curRoom newRoom: 115))
		)
	)
)

(instance sMoveStars of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(lStar show: posn: 34 51 setMotion: MoveTo 62 79 self)
			)
			(2
				(lStar hide:)
				(= seconds (Random 2 4))
			)
			(3
				(cStar show: posn: 273 9 setMotion: MoveTo 232 51 self)
			)
			(4
				(cStar hide:)
				(= seconds (Random 2 4))
			)
			(5
				(lStar show: posn: 59 58 setMotion: MoveTo 7 58 self)
			)
			(6
				(lStar hide:)
				(= seconds (Random 2 4))
			)
			(7
				(lStar show: posn: 19 111 setMotion: MoveTo 55 111 self)
			)
			(8
				(lStar hide:)
				(= seconds (Random 2 4))
			)
			(9 (= cycles 1) (= state -1))
		)
	)
)

(instance sDownTheDrain of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 233 144 self)
			)
			(2
				(elevator init: setCycle: Forward)
				(theMusic2 number: 127 setLoop: 1 play: self)
				(ego
					setCycle: 0
					setScale: 0
					setStep: 5 5
					setLoop: 8
					setCel: 0
					setPri: 13
				)
			)
			(3
				(theMusic2 number: 1281 setLoop: -1 play:)
				(ego setMotion: MoveTo 233 240 self)
			)
			(4
				(theMusic2 number: 109 setLoop: 1 play: self)
			)
			(5
				(theGame handsOn:)
				(curRoom newRoom: 119)
			)
		)
	)
)

(instance sFrom115 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo 1 2)
				(ego
					view: 1
					posn: 181 98
					init:
					illegalBits: 0
					setScale: Scaler 100 16 155 92
					setMotion: MoveTo 149 109 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFrom119 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 127 setLoop: 1 play: self)
			)
			(1
				(theMusic2 number: 128 setLoop: 1 play:)
				(ego
					init:
					view: 1
					posn: 233 240
					looper: 0
					setLoop: (- (NumLoops ego) 1)
					setPri: 13
					moveSpeed: 6
					cel: 1
					setCycle: 0
					ignoreActors: 1
					noun: 19
					setMotion: MoveTo 233 144 self
				)
				(elevator
					init:
					posn: 233 240
					setCycle: Forward
					ignoreActors: TRUE
				)
			)
			(2
				(NormalEgo 1 1)
				(ego
					setScale: Scaler 100 16 155 92
					setMotion: PolyPath 119 143 self
				)
			)
			(3
				(theGame handsOn:)
				(elevator dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sMoveGuard of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 8)))
			(1 (Ghead setCycle: EndLoop self))
			(2 (= seconds (Random 2 8)))
			(3 (Ghead setCycle: BegLoop self))
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance red_planet of Feature
	(properties
		x 217
		y 121
		noun N_RED_PLANET
		onMeCheck cWHITE
	)
)

(instance machine of Feature
	(properties
		x 311
		y 40
		noun N_MACHINE
		onMeCheck cBROWN
	)
)

(instance skylight of Feature
	(properties
		x 245
		y 31
		noun N_SKYLIGHT
		onMeCheck cLBLUE
	)
)

(instance monitor of Prop
	(properties
		x 13
		y 161
		noun 6
		onMeCheck cLGREY
		view 1142
		loop 1
		priority 15
		signal fixPriOn
		cycleSpeed 14
	)
)

(instance sPlayMC of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (monitor setCycle: EndLoop self))
			(1
				(monitor cel: 0)
				(= state -1)
				(= seconds 3)
			)
		)
	)
)

(instance lower_level of Feature
	(properties
		x 297
		y 163
		onMeCheck cGREY
	)
	
	(method (init)
		(if (Btst fCleanedCrest)
			(= noun N_LOWER_CLEAN)
		else
			(= noun N_LOWER_DIRTY)
		)
		(super init:)
	)
)

(instance guardTalker of Narrator
	(properties
		x 77
		y 126
		talkWidth 100
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 77
			tailY: 121
			xOffset: -5
			isBottom: 1
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)
