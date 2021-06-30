;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include game.sh) (include "480.shm")
(use Main)
(use LBRoom)
(use Inset)
(use Talker)
(use Scaler)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm480 0
	Rex 39
)

(local
	local0
	local1
	gEgoMoveSpeed
)
(instance rm480 of LBRoom
	(properties
		noun N_ROOM
		picture 480
		north 430
	)
	
	(method (init)
		(LoadMany RES_VIEW 423 424 741 426 482 442 483 481 480 831)
		(LoadMany RES_SOUND 52 483 480 481 482)
		(Load RES_SCRIPT 939)
		(ego
			init:
			normalize: (if (== currentAct 5) 426 else 831)
			setScale: Scaler 110 0 190 0
		)
		(if (== currentAct 5)
			(self setRegions: 94)
			(curRoom obstacles: (List new:))
			((ScriptID 2480 0) doit: (curRoom obstacles?))
		else
			(self setRegions: 90)
		)
		(switch prevRoomNum
			(north
				(ego x: 138 y: 121)
				(curRoom setScript: sEnterNorth)
			)
			(740
				(curRoom setScript: sChaseSequence)
				(lump init:)
				(rexMouth approachVerbs: 4 1 init:)
				(ego hide:)
				(steve init: hide:)
			)
			(else 
				(ego posn: 160 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(signButton approachVerbs: 1 4 init:)
		(painting init:)
		(dino init: setOnMeCheck: 1 8192)
		(rexMouth approachVerbs: 4 1)
		(rex approachVerbs: 4 1 setOnMeCheck: 1 16384 32 init:)
		(if (not (ego has: 18)) (bone init: stopUpd:))
		(dinoBones approachVerbs: 4 1 init:)
		(narrator x: 10 y: 10)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== (curRoom script?) sChaseSequence)
					(IsObjectOnControl ego 2)
				)
				((self script?) cue:)
			)
			(script)
			((IsObjectOnControl ego 2) (self setScript: sExitNorth))
			((IsObjectOnControl ego 4) (self setScript: sAroundTRexCCW))
			((IsObjectOnControl ego 32) (self setScript: sAroundTRexCW))
			((IsObjectOnControl ego 16) (self setScript: sAroundDinoCCW))
			((IsObjectOnControl ego 8) (self setScript: sAroundDinoCW))
		)
	)
	
	(method (dispose)
		(if local1 (sWrapMusic dispose: 1))
		(DisposeScript 2480)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(narrator modeless?)
				(or
					(and
						(== (event type?) keyDown)
						(OneOf (event message?) ESC ENTER)
					)
					(and
						(== (event type?) mouseDown)
						(not (event modifiers?))
					)
				)
			)
			(event claimed: TRUE)
			(if modelessDialog
				(modelessDialog dispose:)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (notify)
		(if (== currentAct 5)
			(if (curRoom script?)
				((curRoom script?) next: sCaughtYou)
			else
				(curRoom setScript: sCaughtYou)
			)
		)
	)
)

(instance sCaughtYou of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oriley
					init:
					view: 423
					posn: 137 122
					setPri: -1
					setCycle: Walk
					setScale: Scaler 100 100 190 0
				)
				(= cycles 1)
			)
			(1
				(theGame handsOff:)
				(= cycles 1)
			)
			(2
				(ego setMotion: PolyPath 219 179 self)
			)
			(3
				(theMusic number: 3 loop: 1 flags: 1 play:)
				(oriley setMotion: MoveTo 169 124 self)
			)
			(4
				(oriley setMotion: PolyPath 196 173 self)
			)
			(5
				(Face ego oriley)
				(= cycles 3)
			)
			(6
				(oriley view: 424 cel: 0 setCycle: EndLoop self)
			)
			(7
				(noise number: 80 flags: 1 loop: 1 play:)
				(ego view: 858 setCycle: EndLoop self)
			)
			(8
				(= deathReason 0)
				(curRoom newRoom: 99)
			)
		)
	)
)

(instance sChaseSequence of Script
	(properties)
	
	(method (doit)
		(if (and (== (self state?) 12) local0)
			(theGame handsOff:)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany RES_VIEW 482)
				(Load RES_SOUND 480)
				(theMusic2 stop:)
				(theMusic number: 482 loop: -1 flags: 1 play:)
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(rexMouth setCycle: EndLoop self)
				(noise number: 480 flags: 1 play:)
			)
			(2
				(if (Btst 46)
					(self setScript: sRatsFall self)
				else
					(= cycles 1)
				)
			)
			(3
				(lump cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego
					show:
					view: 482
					setLoop: 4
					yStep: 8
					cel: 0
					posn: 184 141
					setPri: 12
					setScale: Scaler 100 100 190 0
					setCycle: Forward
					setMotion: MoveTo 179 160 self
				)
			)
			(5
				(ego setLoop: 0 setCycle: EndLoop self)
				(= gEgoMoveSpeed (ego moveSpeed?))
			)
			(6
				(ego
					view: 831
					setLoop: 6
					yStep: 2
					posn: 171 158
					setCycle: Reverse
					setSpeed: 10
					setMotion: MoveTo 165 162 self
				)
			)
			(7
				(lump cel: 0 setCycle: EndLoop self)
			)
			(8
				(steve show: setCel: 0 yStep: 8 setLoop: 1)
				(= cycles 1)
			)
			(9
				(steve setMotion: MoveTo 179 160 self)
			)
			(10
				(ego
					normalize: 426
					setScale: Scaler 110 0 190 0
					setSpeed: gEgoMoveSpeed
				)
				(signButton
					approachX: 296
					approachY: 189
					approachVerbs: 4 1
				)
				(steve posn: 179 160 setCycle: EndLoop self)
			)
			(11
				(steve setLoop: 1 setMotion: MoveTo 160 158 self)
			)
			(12
				(theGame handsOn:)
				(= seconds 20)
			)
			(13
				(theGame handsOff:)
				(oriley init: setScale: Scaler 100 100 190 0 hide:)
				(if local0
					(self setScript: sOrileyCaught)
				else
					(self setScript: sKillThem)
				)
			)
		)
	)
)

(instance sOrileyCaught of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== (localSound number?) 483)
				(== (localSound prevSignal?) -1)
				(== (self state?) 15)
			)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame points: 1 138)
				(rexMouth setCycle: EndLoop self)
			)
			(1
				(lump cel: 0 setCycle: EndLoop self)
			)
			(2
				(rexMouth setCycle: EndLoop self)
				(noise number: 480 flags: 1 play:)
			)
			(3 (oriley show:) (= cycles 1))
			(4
				(theMusic stop:)
				(oriley
					setLoop: 0
					setPri: 15
					setMotion: MoveTo 182 120 self
				)
				(rexMouth setCycle: EndLoop self)
			)
			(5
				(= local1 1)
				(WrapMusic pause: 1)
				(sWrapMusic init: 0 1481 483)
				0
			)
			(6
				(rexMouth setCycle: BegLoop self)
				(oriley setCycle: EndLoop)
			)
			(7
				(oriley setCycle: CycleTo 3 -1 self)
			)
			(8 (oriley setCycle: EndLoop self))
			(9
				(= seconds 2)
				(steve ignoreActors: 1)
			)
			(10
				(ego setMotion: PolyPath 169 141 self)
			)
			(11 (= seconds 1))
			(12
				(ego
					view: 483
					loop: 13
					cel: 0
					setScale: 135
					setCycle: EndLoop self
				)
			)
			(13 (= seconds 3))
			(14
				((ScriptID 22 0) doit: 2)
				(theGame points: 1 151)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 485)
				(= seconds 5)
			)
			(15 0)
			(16
				(WrapMusic pause: 0)
				(curRoom newRoom: 26)
			)
		)
	)
)

(instance sKillThem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic number: 3 loop: 1 flags: 1 play:)
				(= cycles 1)
			)
			(1
				(lump cel: 0 setCycle: EndLoop self)
			)
			(2
				(oriley setLoop: 1 posn: 186 141 show:)
				(= cycles 1)
			)
			(3 (oriley setCycle: EndLoop self))
			(4
				(noise number: 52 flags: 1 loop: 1 play:)
				(oriley setLoop: 5 posn: 177 143 setCycle: EndLoop self)
			)
			(5
				(steve
					view: 483
					loop: 10
					cel: 0
					posn: 125 169
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(6
				(theMusic fade:)
				(cond 
					((<= (ego x?) 131) (oriley setLoop: 5))
					((and (<= (ego x?) 172) (>= (ego y?) 161)) (oriley setLoop: 5))
					((and (> (ego x?) 172) (>= (ego y?) 159)) (oriley setLoop: 6))
					((> (ego x?) 320) (oriley setLoop: 6))
					((and (>= (ego x?) 180) (<= (ego y?) 141)) (oriley setLoop: 7))
					(else (oriley setLoop: 8))
				)
				(oriley setCycle: EndLoop self)
				(noise number: 52 flags: 1 loop: 1 play:)
			)
			(7
				(= deathReason 10)
				(curRoom newRoom: 99)
			)
		)
	)
)

(instance sGetDinoBoneFromInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(inBone dispose:)
				(bone dispose:)
				(= cycles 1)
			)
			(1
				(theGame points: 1 139)
				(ego get: 18)
				((ScriptID 21 0) doit: 787)
				(self dispose:)
			)
		)
	)
)

(instance sGetDinoBone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 481
					setLoop: 0
					cel: 0
					setScale: Scaler 100 100 190 0
					setCycle: CycleTo 2 1 self
				)
				(bone dispose:)
			)
			(2 (ego setCycle: EndLoop self))
			(3
				(ego view: 831 loop: 7)
				(= cycles 1)
			)
			(4
				(theGame handsOn:)
				(ego
					setScale: Scaler 110 0 190 0
					normalize: (if (== currentAct 5) 426 else 831)
					get: 18
				)
				((ScriptID 21 0) doit: 787)
				(theGame points: 1 139)
				(self dispose:)
			)
		)
	)
)

(instance sRexTalks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(user canControl: 0)
				(theGame setCursor: waitCursor)
				(ego setHeading: 0)
				(rexMouth init:)
				(= seconds 2)
			)
			(1
				(theGame points: 1 138)
				(rexMouth setCycle: RandCycle)
				(narrator modeless: 1)
				(ego setMotion: PolyPath 255 184 self)
			)
			(2
				(noise number: 480 play: self)
			)
			(3
				(rexMouth setCycle: Oscillate)
				(messager say: 5 4 2 0 self)
			)
			(4
				(narrator modeless: 0 dispose: 1)
				(rexMouth stopUpd: setCycle: 0 cel: 0)
				(= cycles 5)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRatsFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (rat1 init:) (= cycles 1))
			(1
				(rat2 init:)
				(rat1 setLoop: 1 yStep: 7 setMotion: MoveTo 185 157 self)
			)
			(2
				(rat2 setLoop: 1 yStep: 7 setMotion: MoveTo 186 152 self)
				(rat1
					setLoop: 8
					cel: 1
					posn: 175 155
					setMotion: MoveTo 219 200 self
				)
			)
			(3 0)
			(4
				(rat1
					setLoop: 1
					posn: 204 93
					setMotion: MoveTo 185 157 self
				)
				(rat2
					setLoop: 8
					cel: 1
					posn: 171 160
					setMotion: MoveTo 228 200 self
				)
			)
			(5 0)
			(6
				(rat1
					setLoop: 8
					cel: 1
					posn: 175 155
					setMotion: MoveTo 219 200 self
				)
			)
			(7 (= seconds 1))
			(8
				(rat1 dispose:)
				(rat2 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 174 125 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 138 121 self)
			)
			(2
				(if
					(and
						(== currentAct 3)
						(TriggerEvent -20222 1)
						(not (Btst 72))
					)
					(curRoom newRoom: 435)
				else
					(curRoom newRoom: (curRoom north?))
				)
			)
		)
	)
)

(instance sAroundTRexCCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(ego setMotion: PolyPath 320 110 self)
			)
			(2
				(ego setMotion: PolyPath 220 119 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAroundTRexCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(ego setMotion: PolyPath 320 110 self)
			)
			(2
				(ego setMotion: PolyPath 234 181 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAroundDinoCCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(ego setPri: 15 setMotion: PolyPath 145 250 self)
			)
			(2
				(ego setMotion: PolyPath 234 181 self)
			)
			(3
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAroundDinoCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(ego setMotion: PolyPath 225 250 self)
			)
			(2
				(ego setPri: 15 setMotion: PolyPath 9 176 self)
			)
			(3
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookBones of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 7 1 0 0 self))
			(1
				(curRoom setInset: inBone)
				(self dispose:)
			)
		)
	)
)

(instance rexMouth of Prop
	(properties
		x 230
		y 59
		noun 3
		approachX 160
		approachY 160
		view 482
		loop 2
		priority 12
		signal $0011
	)
)

(instance bone of View
	(properties
		x 32
		y 128
		view 481
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(dinoBones doVerb: &rest)
	)
)

(instance dinoBones of Feature
	(properties
		x 1
		y 140
		noun 7
		nsTop 128
		nsLeft 20
		nsBottom 145
		nsRight 65
		sightAngle 40
		approachX 56
		approachY 152
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((ego has: 18) (messager say: 7 1 1))
					((== (curRoom script?) sChaseSequence) (messager say: 9 1 5))
					(else (curRoom setScript: sLookBones))
				)
			)
			(4
				(cond 
					((ego has: 18) (messager say: 7 4 1))
					((== (curRoom script?) sChaseSequence) (messager say: 9 4 5))
					(else (curRoom setScript: sGetDinoBone))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inBone of Inset
	(properties
		view 480
		x 2
		y 121
		disposeNotOnMe 1
		modNum 15
		noun 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetDinoBoneFromInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 191
		y 113
		view 483
		cel 3
		priority 12
		signal $0010
	)
)

(instance steve of Actor
	(properties
		x 185
		y 142
		noun 8
		view 482
		loop 1
		priority 12
		signal $0010
	)
)

(instance lump of Prop
	(properties
		x 258
		y 75
		noun 2
		view 480
		loop 1
		priority 12
		signal $0010
	)
)

(instance rat1 of Actor
	(properties
		x 204
		y 93
		yStep 4
		view 741
		loop 1
		priority 12
		signal $4010
		xStep 4
	)
)

(instance rat2 of Actor
	(properties
		x 200
		y 90
		yStep 4
		view 741
		loop 1
		priority 12
		signal $4010
		xStep 4
	)
)

(instance signButton of Feature
	(properties
		x 296
		y 148
		noun 5
		nsTop 143
		nsLeft 290
		nsBottom 154
		nsRight 303
		sightAngle 40
		approachX 258
		approachY 181
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== (curRoom script?) sChaseSequence)
					(= local0 1)
				else
					(curRoom setScript: sRexTalks)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 71
		y 90
		noun 4
		nsTop 53
		nsLeft 18
		nsBottom 128
		nsRight 124
		sightAngle 40
	)
)

(instance dino of Feature
	(properties
		y 100
		noun 6
	)
)

(instance rex of Feature
	(properties
		x 277
		y 149
		noun 2
		approachX 160
		approachY 160
	)
)

(instance Rex of Narrator
	(properties
		x 10
		y 10
		talkWidth 150
		modeless 1
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance noise of Sound
	(properties)
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
