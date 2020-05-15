;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmInsideDisco) ;610
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm610 0
)

(local
	[local0 4]
	globalSoundPrevSignal
	sharkTimer
	fish1Timer
	fish2Timer
	fish3Timer
	egoX
	egoY
)
(procedure (InitActors)
	(aTuna init: cycleSpeed: howFast moveSpeed: howFast)
	(aShark init: cycleSpeed: howFast moveSpeed: howFast)
	(aFish1 init: cycleSpeed: howFast moveSpeed: howFast)
	(aFish2 init: cycleSpeed: howFast moveSpeed: howFast)
	(aFish3 init: cycleSpeed: howFast moveSpeed: howFast)
	(alEyes
		init:
		cycleSpeed: (+ 40 howFast)
		setCycle: RandCycle
	)
	(rightGuyEyes
		init:
		cycleSpeed: (+ 40 howFast)
		setCycle: RandCycle
	)
	(rogerHead
		init:
		cycleSpeed: (+ 90 howFast)
		setCycle: RandCycle
	)
	(aShark init: cycleSpeed: howFast moveSpeed: howFast)
	(= sharkTimer 300)
	(= fish1Timer 500)
	(= fish2Timer 200)
	(= fish3Timer 100)
)

(procedure (DisposeActors)
	(sSharkChase dispose:)
	(sFish1 dispose:)
	(sFish2 dispose:)
	(sFish3 dispose:)
	(aFish1 dispose: delete:)
	(aFish2 dispose: delete:)
	(aFish3 dispose: delete:)
	(aShark dispose: delete:)
	(aTuna dispose: delete:)
	(alEyes dispose: delete:)
	(rightGuyEyes dispose: delete:)
	(rogerHead dispose: delete:)
)

(instance rm610 of LLRoom
	(properties
		lookStr {The disco is filled with remarkably similar guys, all looking for remarkably similar girls.}
		picture rmInsideDisco
		south rmOutsideDisco
	)
	
	(method (init &tmp theOsc)
		(= theOsc Oscillate)
		(= theOsc ForwardCounter)
		(LoadMany VIEW 610 612 614 611 613)
		(LoadMany SOUND 610 611 321 711)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 283 189 290 180 219 176
						121 179 115 172 118 159 216 136 276 136 278 116
						300 110 249 107 239 100 249 84 271 75 198 75 177 80
						148 77 107 78 89 84 71 84 37 95 40 109 12 125 29 139
						102 139 113 157 103 164 41 167 28 186 95 186 96 189
					yourself:
				)
		)
		(= sharkTimer (Random 400 700))
		(= fish1Timer (Random 100 300))
		(= fish2Timer (Random 150 350))
		(= fish3Timer (Random 200 400))
		(switch prevRoomNum
			(rmOutsideDisco
				(theMusic fade:)
				(soundFx number: 610 vol: 127 loop: -1 flags: mNOPAUSE play:)
				(self south: prevRoomNum)
				(ego init:)
			)
			(rmFawnCloseup
				(Bset fLookedAtFawn)
				(ego
					normal: 0
					init:
					view: 610
					setLoop: 11
					setCel: 3
					x: 220
					y: 73
				)
				(cond 
					((Btst fMakeFawnDance)
						(globalSound prevSignal: 0)
						(Load RES_PALETTE 610)
						(HandsOff)
						(if (not (Btst fIsVGA))
							(floorLight1 init:)
							(floorLight2 init:)
						)
						(curRoom setScript: sDance)
					)
					((Btst fMakeFawnSplit) (curRoom setScript: sFawnIsHistory))
					(else (HandsOn))
				)
			)
			(else 
				(soundFx number: 610 vol: 127 loop: -1 flags: mNOPAUSE play:)
				(ego init: x: 165 y: 100)
			)
		)
		(if (not (Btst fMakeFawnDance)) (InitActors))
		(louZerr init:)
		(man2 init:)
		(man3 init:)
		(man4 init:)
		(rogerMan init:)
		(coral1 init:)
		(coral2 init:)
		(coral3 init:)
		(coral5 init:)
		(boat init:)
		(lowe init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(skirvin init:)
		(aquarium init:)
		(chair init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(if (not (Btst fNoFawnInDisco))
			(aFawn
				cycleSpeed: howFast
				moveSpeed: howFast
				init:
				stopUpd:
				approachVerbs: verbDo verbUse verbZipper verbTaste
			)
		)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(ego mover?)
				(== (ego view?) 610)
				(== (ego loop?) 11)
			)
			(= egoX ((User curEvent?) x?))
			(= egoY ((User curEvent?) y?))
			(HandsOff)
			(curRoom setScript: sStandUp)
		)
		(cond 
			(
				(and
					(!= (curRoom script?) sDance)
					(== sharkTimer 0)
					(!= (aShark script?) sSharkChase)
				)
				(aShark setScript: sSharkChase)
			)
			(
				(and
					(!= (curRoom script?) sDance)
					(!= sharkTimer 0)
					(!= (aShark script?) sSharkChase)
				)
				(-- sharkTimer)
			)
			((== sharkTimer 0) (= sharkTimer (Random 200 500)))
		)
		(if
			(and
				(!= (curRoom script?) sDance)
				(== fish1Timer 0)
				(!= (aFish1 script?) sFish1)
			)
			(= fish1Timer (Random 300 500))
			(aFish1 setScript: sFish1)
		else
			(-- fish1Timer)
		)
		(if
			(and
				(!= (curRoom script?) sDance)
				(== fish2Timer 0)
				(!= (aFish2 script?) sFish2)
			)
			(= fish2Timer (Random 300 500))
			(aFish2 setScript: sFish2)
		else
			(-- fish2Timer)
		)
		(return
			(if
				(and
					(!= (curRoom script?) sDance)
					(== fish3Timer 0)
					(!= (aFish3 script?) sFish3)
				)
				(= fish3Timer (Random 300 500))
				(aFish3 setScript: sFish3)
			else
				(-- fish3Timer)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance sSitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 220 75 self)
			)
			(1
				(ego view: 610 setLoop: 11 setCel: 0)
				(= cycles 12)
			)
			(2
				(if
					(and
						(== (aFawn view?) 612)
						(== (aFawn loop?) 4)
						(not (Btst fSatWithFawn))
					)
					(SolvePuzzle fSatWithFawn 1)
					(Print 610 0)
					(Print 610 1)
					(drumFillSFX play:)
					(Print 610 2 #at -1 140)
				)
				(= cycles 11)
			)
			(3
				(ego view: 610 setLoop: 11 setCycle: EndLoop self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance sStandUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(NormalEgo 2)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 5) self)
			)
			(2
				(HandsOn)
				(if
					(and
						(IsObject (CueObj client?))
						(!= (CueObj client?) chair)
						((CueObj client?) approachX?)
					)
					(ego
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							((CueObj client?) approachY?)
							CueObj
					)
				else
					(ego setMotion: PolyPath egoX egoY)
				)
				(self dispose:)
			)
		)
	)
)

(instance sFawnIsHistory of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(aFawn startUpd:)
				(= cycles 1)
			)
			(1 (aFawn setCycle: BegLoop self))
			(2
				(aFawn
					view: 612
					loop: 2
					cel: 1
					x: 174
					y: 79
					setCycle: Walk
					setMotion: PolyPath 166 249 self
				)
			)
			(3
				(Bset fNoFawnInDisco)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDance of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(= globalSoundPrevSignal (globalSound prevSignal?))
		(cond 
			(
			(and (>= state 26) (>= (globalSound prevSignal?) 80)) (self cue:))
			(
			(and (== state 21) (>= (globalSound prevSignal?) 70)) (self cue:))
			(
			(and (== state 20) (>= (globalSound prevSignal?) 60)) (self cue:))
			(
			(and (== state 16) (>= (globalSound prevSignal?) 50)) (self cue:))
			(
			(and (== state 9) (>= (globalSound prevSignal?) 40)) (self cue:))
			(
			(and (== state 6) (>= (globalSound prevSignal?) 30)) (self cue:))
			(
			(and (== state 3) (>= (globalSound prevSignal?) 20)) (self cue:))
			(
			(and (== state 1) (>= (globalSound prevSignal?) 10)) (self cue:))
		)
		(cond 
			((and (Btst fIsVGA) (>= state 1) (<= state 27)) (Palette 6 64 79 1))
			(
			(and (not (Btst fIsVGA)) (>= state 1) (<= state 27))
				(floorLight1 x: (Random 110 223) y: (Random 90 135))
				(floorLight2 x: (Random 110 223) y: (Random 90 135))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fIsVGA))
					(floorLight1 setCycle: Forward)
					(floorLight2 setCycle: Forward)
				)
				(soundFx fade: 0 12 20 1 self)
			)
			(1
				(DisposeActors)
				(globalSound
					number: 611
					vol: 127
					loop: 1
					flags: 0
					play:
					hold: 10
				)
				(if
				(and (== (ego view?) 610) (== (ego loop?) 11))
					(ego startUpd: setCycle: BegLoop)
				)
				(aFawn startUpd: setCycle: BegLoop)
			)
			(2
				(NormalEgo 2)
				(ego egoSpeed: egoSpeed setMotion: MoveTo 151 108 self)
			)
			(3
				(globalSound release:)
				(ego setHeading: 180)
			)
			(4
				(globalSound hold: 20)
				(aFawn
					cycleSpeed: howFast
					moveSpeed: howFast
					view: 612
					loop: 2
					cel: 1
					x: 164
					y: 79
					setCycle: Walk
					setMotion: PolyPath 183 108 self
				)
			)
			(5
				(aFawn x: (+ (aFawn x?) 1000))
				(ego
					normal: 0
					x: 141
					y: 108
					view: 613
					setLoop: 0
					setCycle: EndLoop self
				)
			)
			(6 (globalSound release:))
			(7
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(8
				(ego setLoop: 4 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(9
				(ego setCycle: EndLoop self)
				(aFawn
					view: 613
					setLoop: 5
					setCel: 0
					x: 137
					y: 65
					yStep: 20
					setPri: 4
					setCycle: Forward
					setMotion: MoveTo 139 -40
				)
				(ego view: 611 setLoop: 0 setCel: 0 setCycle: Forward)
			)
			(10
				(ego
					setLoop: 1
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 180 108 self
				)
			)
			(11
				(if (== (aFawn y?) -40) (aFawn stopUpd:))
				(ego setLoop: 5 setCel: 0)
				(= cycles 15)
			)
			(12
				(ego
					setLoop: 2
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 120 108 self
				)
			)
			(13
				(ego setLoop: 6 setCel: 0)
				(= cycles 15)
			)
			(14
				(ego
					setLoop: 1
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 154 108 self
				)
			)
			(15
				(ego setLoop: 7)
				(= cycles 5)
			)
			(16
				(ego setLoop: 9 setCycle: Forward)
			)
			(17
				(aFawn
					startUpd:
					x: 135
					setCycle: Forward
					setMotion: MoveTo 135 61 self
				)
			)
			(18
				(aFawn x: (+ (aFawn x?) 1000))
				(ego
					view: 613
					setLoop: 4
					x: 139
					y: 108
					setCel: 2
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(19
				(ego view: 613 setLoop: 2 setCel: 2 setCycle: BegLoop self)
			)
			(20
				(ego
					egoSpeed: egoSpeed
					view: 611
					setLoop: 10
					x: 153
					y: 108
					setCycle: Forward
				)
				(aFawn view: 613 setLoop: 6 x: 122 y: 108 setCycle: Forward)
			)
			(21
				(ego egoSpeed: (* 2 egoSpeed) setLoop: 4 setCycle: Forward)
				(aFawn view: 612 setLoop: 0 setCel: 2 stopUpd:)
			)
			(22
				(ego view: 611 setLoop: 3 cycleSpeed: 4 setCycle: Forward)
				(= seconds 1)
			)
			(23
				(aFawn
					startUpd:
					view: 612
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath 174 79
				)
				(ego cycleSpeed: 3)
				(= seconds 1)
			)
			(24
				(ego cycleSpeed: 2)
				(= seconds 1)
			)
			(25
				(ego cycleSpeed: 1)
				(= seconds 1)
			)
			(26 (ego cycleSpeed: 0))
			(27
				(ego setLoop: 0 setCel: 4)
				(globalSound prevSignal: 0)
				(= cycles 2)
			)
			(28
				(if (Btst fIsVGA)
					(Palette PAL_MATCH 610 2)
				else
					(floorLight1 dispose:)
					(floorLight2 dispose:)
				)
				(sfxCheer play:)
				(= seconds 10)
			)
			(29
				(aFawn
					startUpd:
					view: 612
					setLoop: 4
					setCel: 0
					setPri: 3
					setCycle: EndLoop
				)
				(NormalEgo 2)
				(soundFx play:)
				(InitActors)
				(Bset fDancedWithFawn)
				(Bclr fMakeFawnDance)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance sSharkChase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aTuna
					init:
					x: 334
					y: 43
					setLoop: 1
					cycleSpeed: howFast
					moveSpeed: howFast
					setCycle: Forward
					setMotion: MoveTo 138 43 self
				)
				(= cycles 30)
			)
			(1
				(aShark
					init:
					x: 40
					y: 25
					setPri: 0
					cycleSpeed: howFast
					moveSpeed: howFast
					setLoop: 2
					setCycle: Forward
					setMotion: MoveTo 54 25
				)
			)
			(2
				(aShark xStep: 25 setMotion: MoveTo 350 43 self)
				(aTuna setLoop: 0 xStep: 25 setMotion: MoveTo 360 43)
			)
			(3 (= cycles 60))
			(4
				(aTuna
					setLoop: 0
					x: -11
					y: 43
					setPri: 1
					setMotion: MoveTo 85 32 self
				)
			)
			(5
				(aTuna setLoop: 1 setPri: 0 setMotion: MoveTo 40 32 self)
			)
			(6 (= cycles 5))
			(7
				(aShark
					setLoop: 2
					x: -17
					y: 43
					xStep: 5
					setPri: 1
					setMotion: MoveTo 139 24 self
				)
			)
			(8
				(aShark setCycle: Forward)
				(= cycles 15)
			)
			(9
				(aShark setLoop: 3 setCycle: Forward)
				(= cycles 30)
			)
			(10
				(aShark
					setLoop: 2
					setCycle: Forward
					setMotion: MoveTo 350 43 self
				)
			)
			(11
				(aTuna
					setLoop: 0
					setPri: 0
					xStep: 3
					setMotion: MoveTo 85 32 self
				)
			)
			(12
				(aTuna setLoop: 10 setCycle: Forward)
				(= cycles 20)
			)
			(13
				(aTuna setLoop: 1 setPri: 1 setMotion: MoveTo -11 43 self)
			)
			(14
				(aShark dispose:)
				(aTuna dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sFish1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= (aFish1 x?) 100)
					(aFish1
						init:
						setLoop: 5
						setCycle: Forward
						setMotion: MoveTo -30 (aFish1 y?) self
					)
				else
					(aFish1
						setLoop: 4
						setCycle: Forward
						setMotion: MoveTo 340 (aFish1 y?) self
					)
				)
			)
			(1 (= cycles (Random 200 700)))
			(2
				(aFish1 dispose:)
				(self init:)
			)
		)
	)
)

(instance sFish2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= (aFish2 x?) 100)
					(aFish2
						init:
						setLoop: 7
						setCycle: Forward
						setMotion: MoveTo -30 (aFish2 y?) self
					)
				else
					(aFish2
						setLoop: 6
						setCycle: Forward
						setMotion: MoveTo 340 (aFish2 y?) self
					)
				)
			)
			(1 (= cycles (Random 200 700)))
			(2
				(aFish2 dispose:)
				(self init:)
			)
		)
	)
)

(instance sFish3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= (aFish3 x?) 100)
					(aFish3
						init:
						setLoop: 9
						setCycle: Forward
						setMotion: MoveTo -30 (aFish3 y?) self
					)
				else
					(aFish3
						setLoop: 8
						setCycle: Forward
						setMotion: MoveTo 340 (aFish3 y?) self
					)
				)
			)
			(1 (= cycles (Random 200 700)))
			(2
				(aFish3 dispose:)
				(self init:)
			)
		)
	)
)

(instance sfxCheer of Sound
	(properties
		flags mNOPAUSE
		number 711
	)
)

(instance aTuna of Actor
	(properties
		x 334
		y 43
		description {the fish}
		lookStr {What a great idea! Live fish in an aquarium.}
		view 610
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aShark of Actor
	(properties
		x 40
		y 25
		description {Jaws}
		lookStr {It's just a small shark!}
		view 610
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aFawn of Person
	(properties
		x 168
		y 77
		description {the girl}
		approachX 199
		approachY 75
		view 612
		loop 4
		cel 4
		priority 3
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse (Print 610 3))
			(verbTalk
				(if
				(and (== (ego view?) 610) (== (ego loop?) 11))
					(Print 610 3)
				else
					(Print 610 4)
				)
			)
			(verbDo
				(cond 
					((Btst fDancedWithFawn) (Print 610 5))
					((Btst fLookedAtFawn)
						(Print 610 6)
						(Print 610 7)
						(SolvePuzzle fFawnDancePoints 5)
						(HandsOff)
						(curRoom setScript: sDance)
					)
					(else (Print 610 8) (Print 610 9))
				)
			)
			(verbZipper (Print 610 10))
			(verbTaste (Print 610 11))
			(verbLook
				(cond 
					(
						(and
							(not (Btst fNoFawnInDisco))
							(== (ego view?) 610)
							(== (ego loop?) 11)
						)
						(curRoom newRoom: rmFawnCloseup)
					)
					((and (not (Btst fGaveFawnEverything)) (Btst fNoFawnInDisco)) (Print 610 12))
					((not (Btst fNoFawnInDisco)) (Print 610 13) (Print 610 14))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance aFish1 of Actor
	(properties
		x -30
		y 41
		description {the fish}
		lookStr {Look at those fish go!}
		view 610
		loop 4
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 1
		xStep 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 15))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aFish2 of Actor
	(properties
		x 340
		y 30
		description {the fish}
		lookStr {Look at those fish go!}
		view 610
		loop 7
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 1
		xStep 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 15))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aFish3 of Actor
	(properties
		x -25
		y 41
		description {the fish}
		lookStr {Look at those fish go!}
		view 610
		loop 8
		priority 1
		signal (| ignrAct fixPriOn)
		detailLevel 1
		xStep 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 15))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance floorLight1 of Actor
	(properties
		x 155
		y 112
		view 610
		loop 12
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance floorLight2 of Actor
	(properties
		x 155
		y 112
		view 610
		loop 12
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance alEyes of Prop
	(properties
		x 13
		y 64
		view 614
		cycleSpeed 30
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(lowe doVerb: theVerb theItem)
	)
)

(instance rightGuyEyes of Prop
	(properties
		x 289
		y 61
		view 614
		loop 1
		priority 5
		signal fixPriOn
		cycleSpeed 30
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rogerHead of Prop
	(properties
		x 9
		y 136
		view 614
		loop 2
		cycleSpeed 50
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 235
		y 64
		nsTop 54
		nsLeft 211
		nsBottom 75
		nsRight 259
		description {the chair}
		sightAngle 40
		approachX 220
		approachY 73
		lookStr {It looks like the best seat in the house.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sSitDown)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance boat of Feature
	(properties
		x 194
		y 36
		nsTop 29
		nsLeft 167
		nsBottom 43
		nsRight 221
		description {the sunken boat}
		sightAngle 40
		lookStr {"...on a three-hour tour!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lowe of Feature
	(properties
		x 18
		y 73
		nsTop 55
		nsBottom 92
		nsRight 36
		description {Al Lowe}
		sightAngle 40
		approachX 46
		approachY 97
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 610 16)
				(Print 610 17 #at -1 140)
			)
			(3 (Print 610 18))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance skirvin of Feature
	(properties
		x 77
		y 60
		nsTop 41
		nsLeft 65
		nsBottom 79
		nsRight 90
		description {Bill Skirvin}
		sightAngle 40
		lookStr {You wonder if Patti knows Bill is hanging around with Al Lowe again!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 19))
			(verbTalk
				(Print 610 20)
				(Print 610 21)
				(Print 610 22 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aquarium of Feature
	(properties
		x 159
		y 21
		nsBottom 43
		nsRight 319
		description {the aquarium}
		sightAngle 40
		lookStr {A few fish swim around in the gigantic aquarium.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 23))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance louZerr of Feature
	(properties
		x 237
		y 167
		z 36
		nsTop 114
		nsLeft 225
		nsBottom 148
		nsRight 250
		description {Kevin Ray}
		sightAngle 40
		lookStr {Kevin is that handsome gent near the divider.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 24))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance man2 of Feature
	(properties
		x 300
		y 167
		z 28
		nsTop 122
		nsLeft 288
		nsBottom 157
		nsRight 313
		description {Barry Smith}
		sightAngle 40
		lookStr {Barry Smith thought he could hide out here, instead of drawing for a living!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 25))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance man3 of Feature
	(properties
		x 11
		y 137
		nsTop 116
		nsBottom 159
		nsRight 23
		description {Mike}
		sightAngle 40
		lookStr {Mike feels good like a programmer should.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 26))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance man4 of Feature
	(properties
		x 84
		y 140
		nsTop 123
		nsLeft 73
		nsBottom 157
		nsRight 95
		description {Oliver Brelsford}
		sightAngle 40
		lookStr {Oliver Brelsford's neck must be getting stiff by now, staring across the room.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 27))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rogerMan of Feature
	(properties
		x 282
		y 76
		nsTop 53
		nsLeft 267
		nsBottom 99
		nsRight 298
		description {Roger Hardy}
		sightAngle 40
		lookStr {Have you ever seen Roger in a chair high enough so his legs weren't scrambled?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 28))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance coral1 of Feature
	(properties
		x 225
		y 160
		nsTop 142
		nsLeft 131
		nsBottom 178
		nsRight 319
		description {the coral room divider}
		sightAngle 40
		lookStr {It's not often you see coral used for a room divider.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 29))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance coral2 of Feature
	(properties
		x 11
		y 99
		nsTop 86
		nsBottom 112
		nsRight 22
		description {the coral}
		sightAngle 40
		lookStr {It's not often you see coral used for a room divider.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 29))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance coral3 of Feature
	(properties
		x 113
		y 56
		nsTop 42
		nsLeft 94
		nsBottom 71
		nsRight 132
		description {the coral}
		sightAngle 40
		lookStr {It's not often you see coral used for a room divider.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 29))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance coral5 of Feature
	(properties
		x 306
		y 118
		nsTop 102
		nsLeft 294
		nsBottom 135
		nsRight 319
		description {the coral}
		sightAngle 40
		lookStr {It's not often you see coral used for a room divider.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 610 29))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance drumFillSFX of Sound
	(properties
		flags mNOPAUSE
		number 321
	)
)
