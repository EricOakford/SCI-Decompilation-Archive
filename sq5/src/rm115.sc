;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include game.sh) (include "115.shm")
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
	rm115 0
	guardTalker 2
)

(local
	local0
)
(instance rm115 of Room
	(properties
		noun N_ROOM
		picture 30
		style (| BLACKOUT FADEOUT)
		north 117
	)
	
	(method (init)
		(curRoom
			setRegions: rgStarCon
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						69 146
						164 146
						162 163
						225 163
						246 151
						319 152
						318 141
						261 141
						236 127
						196 108
						158 97
						132 95
						38 75
						32 90
						29 94
						30 96
						128 96
						132 97
						154 104
						167 116
						174 144
						71 144
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
		(cStar init: hide: setScript: sMoveStars)
		(monitor init: setCycle: Forward)
		(lStar init:)
		(machine init: setOnMeCheck: ftrControl cBROWN)
		(skylight init: setOnMeCheck: ftrControl cLBLUE)
		(big_window init: setOnMeCheck: ftrControl cYELLOW)
		(lower_level init: setOnMeCheck: ftrControl cLGREY)
		(space init: setOnMeCheck: ftrControl cLGREEN)
		(Ghead init: setScript: sMoveGuard)
		(cadet
			init:
			setMotion: MoveTo 7 189 cadet
			setCycle: Forward
			setLoop: 3
		)
		(bigmoon init: setOnMeCheck: ftrControl cBLUE)
		(lift init: setOnMeCheck: ftrControl cCYAN)
		(LoadMany RES_VIEW 115)
		(super init:)
		(switch prevRoomNum
			(119
				(curRoom setScript: sFrom119)
			)
			(else 
				(curRoom setScript: sFrom117)
				(if (not (Btst fSawElvis))
					(elvis
						setLoop: 0
						init:
						setCycle: Forward
						setMotion: MoveTo 330 146
					)
					(Bset fSawElvis)
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(SteppedOn ego cRED)
				(not (curRoom script?))
			)
			(curRoom setScript: sDontGoThere)
		)
		(if (and (SteppedOn ego cGREEN) (not (curRoom script?)))
			(curRoom setScript: sDownTheDrain)
		)
		(if
			(and
				(SteppedOn ego cMAGENTA)
				(not (curRoom script?))
				(ego setMotion: PolyPath 92 92)
			)
			(curRoom newRoom: 117)
		)
		(if (and (< (ego y?) 103) (not (curRoom script?)))
			(curRoom setScript: sLeaveHi)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript FLICKER)
	)
)

(instance sLeaveHi of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 151 98 self)
			)
			(1 (curRoom newRoom: 117))
		)
	)
)

(instance sDownTheDrain of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 84 145 self)
			)
			(1
				(theMusic2 number: 127 setLoop: 1 play: self)
				(ego
					setCycle: 0
					setScale: 0
					setStep: 5 5
					setLoop: 8
					setCel: 0
					setPri: 13
				)
				(elevator init: setCycle: Forward)
			)
			(2
				(theMusic2 number: 1281 setLoop: -1 play:)
				(ego setMotion: MoveTo 84 240 self)
				(elevator setMotion: MoveTo 84 240)
			)
			(3
				(theMusic2 number: 109 setLoop: 1 play: self)
			)
			(4
				(theGame handsOn:)
				(curRoom newRoom: 119)
			)
		)
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
				(ego setMotion: MoveTo 191 126 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFrom117 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo 1)
				(ego
					posn: 151 98
					init:
					setScale: Scaler 100 16 145 92
					setMotion: MoveTo 175 109 self
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
					posn: 84 240
					init:
					view: 1
					looper: 0
					setLoop: (- (NumLoops ego) 1)
					cel: 0
					setPri: 13
					moveSpeed: 6
					setCycle: 0
					ignoreActors: TRUE
					noun: 19
					setMotion: MoveTo 84 144 self
				)
				(elevator
					init:
					posn: 84 240
					setCycle: Forward
					ignoreActors: 1
				)
			)
			(2
				(NormalEgo 1 0)
				(ego
					setScale: Scaler 100 16 145 92
					setMotion: PolyPath 202 138 self
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

(instance sMoveStars of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(lStar show: posn: 200 82 setMotion: MoveTo 218 82 self)
			)
			(2
				(lStar hide:)
				(= seconds (Random 2 4))
			)
			(3
				(cStar show: posn: 46 8 setMotion: MoveTo 92 50 self)
			)
			(4
				(cStar hide:)
				(= seconds (Random 2 4))
			)
			(5
				(lStar show: posn: 246 107 setMotion: MoveTo 230 83 self)
			)
			(6
				(lStar hide:)
				(= seconds (Random 2 4))
			)
			(7
				(lStar show: posn: 236 70 setMotion: MoveTo 236 105 self)
			)
			(8
				(lStar hide:)
				(= seconds (Random 2 4))
			)
			(9 (= cycles 1) (= state -1))
		)
	)
)

(instance elvis of Actor
	(properties
		x 234
		y 135
		noun N_ELVIS
		view 119
	)
)

(instance cadet of Actor
	(properties
		x 119
		y 189
		noun N_CADET
		view 114
		loop 3
		priority 10
		signal fixPriOn
		detailLevel 2
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance cStar of Actor
	(properties
		x 273
		y 9
		yStep 7
		view 114
		loop 6
		signal (| ignrAct ignrHrz fixedLoop)
		detailLevel 2
		illegalBits 0
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
		loop 6
		cel 1
		signal (| ignrAct ignrHrz fixedLoop)
		detailLevel 2
		illegalBits 0
		xStep 1
		moveSpeed 7
	)
)

(instance ppLight of Prop
	(properties
		x 88
		y 7
		view 114
		loop 14
		detailLevel 2
	)
)

(instance tv of Prop
	(properties
		x 14
		y 85
		view 114
		loop 8
		cycleSpeed 40
		detailLevel 2
	)
)

(instance lowLite1 of Prop
	(properties
		x 8
		y 141
		view 114
		loop 10
		priority 15
		signal fixPriOn
		detailLevel 2
	)
)

(instance lowLite2 of Prop
	(properties
		x 8
		y 153
		view 114
		loop 10
		cel 1
		priority 15
		signal fixPriOn
		detailLevel 2
	)
)

(instance hiLite1 of Prop
	(properties
		x 38
		y 22
		view 114
		loop 12
		detailLevel 2
	)
)

(instance hiLite2 of Prop
	(properties
		x 37
		y 27
		view 114
		loop 12
		cel 1
		detailLevel 2
	)
)

(instance hiLite3 of Prop
	(properties
		x 24
		y 17
		view 114
		loop 12
		cel 2
		detailLevel 2
	)
)

(instance hiLite4 of Prop
	(properties
		x 19
		y 22
		view 114
		loop 12
		cel 3
		detailLevel 2
	)
)

(instance hiLite5 of Prop
	(properties
		x 8
		y 17
		view 114
		loop 12
		cel 4
		detailLevel 2
	)
)

(instance hiLite6 of Prop
	(properties
		x 19
		y 24
		view 114
		loop 12
		cel 5
		detailLevel 2
	)
)

(instance hiLite7 of Prop
	(properties
		x 17
		y 27
		view 114
		loop 12
		cel 6
		detailLevel 2
	)
)

(instance space of Feature
	(properties
		x 140
		y 9
		noun 13
		onMeCheck cLGREEN
	)
)

(instance warning of Prop
	(properties
		x 163
		y 4
		view 114
		loop 4
		detailLevel 2
	)
)

(instance Ghead of Prop
	(properties
		x 196
		y 167
		noun N_GUARD
		view 114
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance elevator of Actor
	(properties
		x 84
		y 144
		yStep 3
		view 115
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
		cycleSpeed 0
		illegalBits $0000
	)
	
	(method (doit)
		(if
			(and
				(< 63 (ego x?))
				(< (ego x?) 114)
				(< 134 (ego y?))
			)
			(self show:)
		else
			(self hide:)
		)
		(if (== (ego x?) 84) (self x: (ego x?) y: (ego y?)))
		(super doit: &rest)
	)
)

(instance lift of Feature
	(properties
		x 150
		y 190
		noun N_LIFT
		onMeCheck cCYAN
		approachX 223
		approachY 146
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sDownTheDrain)
			)
			(V_WALK
				(curRoom setScript: sDownTheDrain)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance bigmoon of Feature
	(properties
		x 250
		y 100
		noun N_MOON
		onMeCheck cBLUE
		approachX 203
		approachY 108
	)
)

(instance machine of Feature
	(properties
		x 10
		y 32
		noun N_MACHINE
		onMeCheck cBROWN
	)
)

(instance skylight of Feature
	(properties
		x 79
		y 34
		noun N_SKYLIGHT
		onMeCheck cLBLUE
	)
)

(instance monitor of Prop
	(properties
		x 264
		y 159
		noun N_MONITOR
		onMeCheck cGREY
		view 1142
		cel 4
		priority 15
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance big_window of Feature
	(properties
		x 273
		y 79
		noun N_WINDOW
		onMeCheck cYELLOW
	)
)

(instance lower_level of Feature
	(properties
		x 25
		y 166
		onMeCheck cLGREY
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
		x 245
		y 125
		talkWidth 300
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 255
			tailY: 115
			xOffset: 1
			isBottom: 1
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)
