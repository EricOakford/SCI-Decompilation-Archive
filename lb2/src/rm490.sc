;;; Sierra Script 1.0 - (do not remove this comment)
(script# 490)
(include game.sh) (include "490.shm")
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use PChase)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm490 0
)

(local
	local0
	local1
)
(instance rm490 of LBRoom
	(properties
		noun N_ROOM
		picture 490
		east 440
		south 440
		west 440
		vanishingX 150
		vanishingY 100
	)
	
	(method (init)
		(LoadMany RES_VIEW 491 424 423 818 426 831)
		(LoadMany RES_SOUND 490 1)
		(ego
			init:
			normalize: (if (== currentAct 5) 426 else 831)
			setScale: Scaler 160 40 190 100
		)
		(if (== currentAct 5)
			(self setRegions: 94)
			(curRoom obstacles: (List new:))
			((ScriptID 2490 0) doit: (curRoom obstacles?))
		else
			(self setRegions: 90)
		)
		(switch prevRoomNum
			(south
				(self setScript: sEnterSouth)
			)
			(else 
				(ego posn: 160 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (Btst 143)
			(if (Btst 37)
				(zHead
					loop: 2
					cel: 1
					x: 231
					y: 133
					approachVerbs: 4 1 8
					init:
					setPri: 10
					approachX: 213
					approachY: 135
				)
				(self
					addObstacle:
						(= local0
							((Polygon new:)
								type: 2
								init: 252 133 236 143 201 132 227 130
								yourself:
							)
						)
				)
			else
				(zHead approachVerbs: 4 1 8 init:)
			)
		)
		(southExitFeature init:)
		(westExitFeature init:)
		(eastExitFeature init:)
		(genericHead init:)
		(floor init: setOnMeCheck: 1 16384 2)
		(if (== currentAct 5)
			(oriley
				init:
				setCycle: Walk
				setScale: Scaler 160 40 190 80
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (curRoom setScript: sExitSouth))
		)
	)
	
	(method (dispose)
		(DisposeScript 2490)
		(super dispose:)
	)
	
	(method (newRoom)
		(if (IsObject local0)
			((curRoom obstacles?) delete: local0)
			(local0 dispose:)
		)
		(super newRoom: &rest)
	)
	
	(method (notify)
		(if (== currentAct 5)
			(if (curRoom script?)
				((curRoom script?) next: sKillHer)
			else
				(curRoom setScript: sKillHer)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 320 190
					setHeading: 315
					setMotion: MoveFwd 40 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(cond 
					((> (ego x?) 310) (ego setHeading: 90))
					((< (ego x?) 20) (ego setHeading: 270))
					(else (ego setHeading: 180))
				)
				(ego setMotion: MoveFwd 100 self)
			)
			(2 (curRoom newRoom: 440))
		)
	)
)

(instance sOhNoMurder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (not (TriggerEvent -20222))
					((ScriptID 22 0) doit: -20222 self)
				else
					(= cycles 1)
				)
			)
			(2
				(WrapMusic pause: 1)
				(sWrapMusic init: -1 1 6)
				(= cycles 3)
			)
			(3
				(curRoom setInset: inHead)
				(= seconds 3)
			)
			(4
				(inHead setInset: inReaction)
				(noise number: 82 flags: 1 loop: 1 play: self)
				(= local1 1)
			)
			(5
				(inReaction dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKillHer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 186 150 self)
			)
			(2
				(theMusic number: 3 loop: 1 flags: 1 play:)
				(oriley
					moveSpeed: 4
					cycleSpeed: 4
					setMotion: PChase ego 25 self
				)
			)
			(3
				(Face ego oriley)
				(oriley view: 424 cel: 0 setCycle: EndLoop self)
			)
			(4
				(noise number: 80 flags: 1 loop: 1 play:)
				(ego view: 858 setCycle: EndLoop self)
			)
			(5
				(= deathReason 0)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sGetHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(curRoom
					addObstacle:
						(= local0
							((Polygon new:)
								type: 2
								init: 252 133 236 143 201 132 227 130
								yourself:
							)
						)
				)
				(zHead loop: 1 setCycle: EndLoop self)
			)
			(2
				(zHead setLoop: 2 cel: 2 posn: 211 131 setCycle: EndLoop self)
				(noise number: 490 flags: 1 play:)
			)
			(3
				(zHead setCycle: EndLoop self setMotion: MoveTo 231 133)
			)
			(4
				(zHead setCycle: CycleTo 1 1 self)
			)
			(5
				(Bset 37)
				(zHead
					approachX: 213
					approachY: 135
					setCycle: 0
					setPri: 10
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTheyComeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sWrapMusic dispose: 1)
				(= cycles 1)
			)
			(1
				(localSound dispose:)
				((ScriptID 32 0)
					room: 490
					init:
					view: 814
					x: 253
					y: 240
					setCycle: Walk
					setMotion: MoveTo 249 145 self
				)
				(= register ((ScriptID 90 3) room?))
				((ScriptID 90 3)
					moveTo: 490
					view: 818
					x: 280
					y: 240
					setMotion: MoveTo 290 141 self
				)
				(theMusic2 number: 350 flags: 1 loop: -1 play:)
			)
			(2 0)
			(3
				((ScriptID 32 0) setCycle: StopWalk -1)
				((ScriptID 90 3) loop: 7)
				(= cycles 1)
			)
			(4
				(Face ego (ScriptID 90 3))
				(= cycles 5)
			)
			(5
				(messager say: 1 0 1 0 self 1490)
			)
			(6
				((ScriptID 32 0)
					setCycle: Walk
					setMotion: PolyPath 275 300 self
				)
				((ScriptID 90 3) setMotion: PolyPath 302 300 self)
			)
			(7
				0
				(theMusic2 fade:)
				(WrapMusic pause: 0)
				((ScriptID 90 3) moveTo: register)
				(theGame handsOn:)
				(theGame points: 1 144)
				(Bset 36)
				(self dispose:)
			)
		)
	)
)

(instance sExamineHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(WrapMusic pause: 1)
				(if (not (== currentAct 5)) (mExamineMusic play:))
				(curRoom setInset: inHead self)
			)
			(1
				(mExamineMusic dispose:)
				(WrapMusic pause: 0)
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 150
		y 300
		view 423
	)
)

(instance zHead of Actor
	(properties
		x 190
		y 87
		noun 1
		approachX 189
		approachY 135
		view 491
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== currentAct 5)
			(messager say: 36 0 0 0)
		else
			(switch theVerb
				(1
					(if (Btst 36)
						(curRoom setScript: sExamineHead)
					else
						(curRoom setScript: sOhNoMurder)
					)
				)
				(8 (self doVerb: 1))
				(4
					(cond 
						((Btst 37) (messager say: 1 4 1))
						((not (Btst 36)) (messager say: 1 4 2))
						(else (curRoom setScript: sGetHead))
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance floor of Feature
	(properties)
)

(instance inHead of Inset
	(properties
		picture 495
		hideTheCast 1
		disposeNotOnMe 1
		noun 6
	)
	
	(method (init)
		(ego hide:)
		(zHead hide:)
		(super init: &rest)
		(theGame points: 1 144)
		(InFirstPerson 1)
	)
	
	(method (dispose)
		(ego show:)
		(zHead show:)
		(InFirstPerson 0)
		(if (not (Btst 36)) (curRoom setScript: sTheyComeIn))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(inHead dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inReaction of Inset
	(properties
		picture 555
		hideTheCast 1
		disposeNotOnMe 1
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 35
	)
)

(instance eastExitFeature of ExitFeature
	(properties
		nsTop 133
		nsLeft 314
		nsBottom 189
		nsRight 319
		cursor 14
		exitDir 2
		noun 35
	)
)

(instance westExitFeature of ExitFeature
	(properties
		nsTop 147
		nsBottom 189
		nsRight 5
		cursor 12
		exitDir 3
		noun 35
	)
)

(instance genericHead of Feature
	(properties
		y 5
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 13 4))
			(8 (messager say: 13 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(<= 59 mouseX)
						(<= mouseX 74)
						(<= 68 mouseY)
						(<= mouseY 82)
					)
					(= noun 2)
				)
				(
					(and
						(<= 39 mouseX)
						(<= mouseX 56)
						(<= 47 mouseY)
						(<= mouseY 65)
					)
					(= noun 9)
				)
				(
					(and
						(<= 136 mouseX)
						(<= mouseX 147)
						(<= 87 mouseY)
						(<= mouseY 99)
					)
					(= noun 19)
				)
				(
					(and
						(<= 152 mouseX)
						(<= mouseX 163)
						(<= 91 mouseY)
						(<= mouseY 101)
					)
					(= noun 20)
				)
				(
					(and
						(<= 63 mouseX)
						(<= mouseX 80)
						(<= 108 mouseY)
						(<= mouseY 125)
					)
					(= noun 14)
				)
				(
					(and
						(<= 276 mouseX)
						(<= mouseX 294)
						(<= 74 mouseY)
						(<= mouseY 91)
					)
					(= noun 31)
				)
				(
					(and
						(<= 182 mouseX)
						(<= mouseX 195)
						(<= 112 mouseY)
						(<= mouseY 124)
					)
					(= noun 27)
				)
				(
					(and
						(<= 146 mouseX)
						(<= mouseX 152)
						(<= 101 mouseY)
						(<= mouseY 113)
					)
					(= noun 3)
				)
				(
					(and
						(<= 88 mouseX)
						(<= mouseX 102)
						(<= 107 mouseY)
						(<= mouseY 122)
					)
					(= noun 15)
				)
				(
					(and
						(<= 133 mouseX)
						(<= mouseX 146)
						(<= 68 mouseY)
						(<= mouseY 84)
					)
					(= noun 4)
				)
				(
					(and
						(<= 74 mouseX)
						(<= mouseX 91)
						(<= 61 mouseY)
						(<= mouseY 76)
					)
					(= noun 16)
				)
				(
					(and
						(<= 139 mouseX)
						(<= mouseX 150)
						(<= 113 mouseY)
						(<= mouseY 125)
					)
					(= noun 10)
				)
				(
					(and
						(<= 177 mouseX)
						(<= mouseX 184)
						(<= 85 mouseY)
						(<= mouseY 96)
					)
					(= noun 26)
				)
				(
					(and
						(<= 243 mouseX)
						(<= mouseX 262)
						(<= 75 mouseY)
						(<= mouseY 93)
					)
					(= noun 30)
				)
				(
					(and
						(<= 89 mouseX)
						(<= mouseX 101)
						(<= 53 mouseY)
						(<= mouseY 66)
					)
					(= noun 17)
				)
				(
					(and
						(<= 165 mouseX)
						(<= mouseX 176)
						(<= 93 mouseY)
						(<= mouseY 103)
					)
					(= noun 23)
				)
				(
					(and
						(<= 185 mouseX)
						(<= mouseX 195)
						(<= 89 mouseY)
						(<= mouseY 99)
					)
					(= noun 25)
				)
				(
					(and
						(<= 279 mouseX)
						(<= mouseX 294)
						(<= 102 mouseY)
						(<= mouseY 119)
					)
					(= noun 24)
				)
				(
					(and
						(<= 153 mouseX)
						(<= mouseX 163)
						(<= 104 mouseY)
						(<= mouseY 114)
					)
					(= noun 18)
				)
				(
					(and
						(<= 312 mouseX)
						(<= mouseX 319)
						(<= 73 mouseY)
						(<= mouseY 88)
					)
					(= noun 29)
				)
				(
					(and
						(<= 68 mouseX)
						(<= mouseX 88)
						(<= 88 mouseY)
						(<= mouseY 104)
					)
					(= noun 12)
				)
				(
					(and
						(<= 245 mouseX)
						(<= mouseX 261)
						(<= 101 mouseY)
						(<= mouseY 117)
					)
					(= noun 28)
				)
				(
					(and
						(<= 148 mouseX)
						(<= mouseX 156)
						(<= 78 mouseY)
						(<= mouseY 89)
					)
					(= noun 21)
				)
				(
					(and
						(<= 159 mouseX)
						(<= mouseX 169)
						(<= 81 mouseY)
						(<= mouseY 93)
					)
					(= noun 22)
				)
				(
					(and
						(<= 134 mouseX)
						(<= mouseX 145)
						(<= 99 mouseY)
						(<= mouseY 112)
					)
					(= noun 11)
				)
				(
					(and
						(<= 313 mouseX)
						(<= mouseX 319)
						(<= 103 mouseY)
						(<= mouseY 118)
					)
					(= noun 33)
				)
				(
					(and
						(<= 178 mouseX)
						(<= mouseX 188)
						(<= 97 mouseY)
						(<= mouseY 109)
					)
					(= noun 32)
				)
			)
		)
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)

(instance mExamineMusic of Sound
	(properties
		flags $0001
		number 6
		loop -1
	)
)

(instance sWrapMusic of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound localSound)
		(super init: &rest)
	)
)

(instance localSound of Sound
	(properties)
)
