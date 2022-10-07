;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include sci.sh)
(use Main)
(use CastleRoom)
(use NewFeature)
(use KQ6Print)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm850 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
)
(class NewProp of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		normal 1
	)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not normal)
				(& (event type?) evVERB)
				(self onMe: event)
				(& _approachVerbs (approachCode doit: (event message?)))
			)
			(CueObj
				state: 0
				cycles: 0
				client: self
				theVerb: (event message?)
			)
			(self doSpecial: (CueObj theVerb?))
			(event claimed: 1)
			(event claimed?)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (cue)
		(ego setMotion: PolyPath approachX approachY CueObj)
	)
	
	(method (doSpecial)
		(self cue:)
	)
)

(instance rm850 of CastleRoom
	(properties
		noun 3
		picture 850
		style $000a
		south 730
		vanishingX 124
		vanishingY 92
		minScaleSize 37
		minScaleY 111
		maxScaleY 177
		moveOtherGuard 1
	)
	
	(method (init)
		(LoadMany 128 881 883 850)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						181
						-10
						181
						-10
						-10
						319
						-10
						319
						189
						304
						179
						288
						179
						279
						182
						263
						182
						254
						179
						246
						179
						225
						164
						202
						164
						191
						152
						206
						152
						193
						143
						229
						143
						203
						129
						172
						129
						156
						124
						151
						116
						169
						116
						169
						112
						101
						112
						89
						124
						76
						129
						70
						129
						50
						141
						57
						141
						38
						155
						56
						155
						45
						166
						21
						166
					yourself:
				)
		)
		(if
			(and
				(ego has: 20)
				(== ((inventory at: 8) owner?) 870)
				(not ((ScriptID 80 0) tstFlag: 711 512))
				(> ((ScriptID 80 0) guardTimer?) 30)
			)
			((ScriptID 80 0) setFlag: 711 512 guardTimer: 30)
		)
		(portrait init: setPri: 0)
		(super init: &rest)
		((ScriptID 1015 6) talkWidth: 150 x: 15 y: 20)
		((ScriptID 1015 7) talkWidth: 135 x: 160 y: 20)
		(if (== ((inventory at: 27) owner?) 850) (bird init:))
		(ego
			init:
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(switch prevRoomNum
			(870
				(if (!= (theMusic number?) 700)
					(theMusic fadeTo: 700 -1)
				)
				(ego posn: 149 112)
			)
			(781
				(theMusic fadeTo: 700 -1)
				(ego
					posn: (vizierDoor approachX?) (vizierDoor approachY?)
				)
			)
			(880
				(ego loop: 1)
				(if (> (ego x?) 126)
					(ego posn: 173 130)
				else
					(ego posn: 187 140)
				)
			)
			(else 
				(= local6 1)
				(if
					(or
						(not ((ScriptID 80 0) tstFlag: 709 512))
						((ScriptID 80 0) tstFlag: 710 1)
						(not ((ScriptID 80 0) tstFlag: 709 128))
					)
					(ego posn: 230 185)
				else
					(ego posn: 160 185)
				)
				((ScriptID 80 0) setFlag: 709 128)
			)
		)
		((ScriptID 80 0)
			guard1Code: guardsCode
			guard2Code: guardsCode
		)
		(= spotEgoScr captureEgo)
		(if
			(or
				(not ((ScriptID 80 0) tstFlag: 709 512))
				(and
					((ScriptID 80 0) tstFlag: 710 1)
					(not ((ScriptID 80 0) tstFlag: 711 256))
				)
				(not ((ScriptID 80 0) tstFlag: 709 128))
			)
			((ScriptID 80 5)
				init:
				noun: 6
				loop: 3
				actions: guardDoVerb
				okToCheck: okToCheckCode
			)
			((ScriptID 80 5)
				signal: (& ((ScriptID 80 5) signal?) $efff)
			)
			((ScriptID 80 6)
				init:
				noun: 6
				loop: 3
				actions: guardDoVerb
				okToCheck: okToCheckCode
			)
			((ScriptID 80 6)
				signal: (& ((ScriptID 80 6) signal?) $efff)
			)
			(= local1 1)
		)
		(vizierDoor cel: (* (== prevRoomNum 781) 3) init:)
		(features
			add: floor roomFeatures studyDoor cassima_door pillar chairs hideFeature
			eachElementDo: #init
		)
		(cond 
			(
			(and local1 ((ScriptID 80 0) tstFlag: 711 128))
				((ScriptID 80 5) loop: 2 posn: 118 116 okToCheck: 0)
				((ScriptID 80 6) loop: 2 posn: 133 117 okToCheck: 0)
				(self setScript: walkOutAtWrongTime)
			)
			((not ((ScriptID 80 0) tstFlag: 709 128))
				((ScriptID 80 5) posn: 105 149)
				((ScriptID 80 6) posn: 143 150)
				(self setScript: shouldNotHaveComeOut)
			)
			(
				(and
					((ScriptID 80 0) tstFlag: 710 1)
					(not ((ScriptID 80 0) tstFlag: 711 256))
				)
				((ScriptID 80 5) okToCheck: 0 posn: 126 231)
				((ScriptID 80 6) okToCheck: 0 posn: 163 233)
				(self setScript: walkGuardsOnScreen)
			)
			((and local6 local1)
				(theGame handsOff:)
				(ego setSpeed: 8)
				(hideFeature doVerb: 5)
				((ScriptID 80 5) sightAngle: 180 posn: 105 149)
				((ScriptID 80 6) sightAngle: 180 posn: 143 150)
				(if (not ((ScriptID 80 0) tstFlag: 709 8))
					((ScriptID 80 0) setFlag: 709 8)
					(startGuardScr next: watchGuardsTalk)
					(watchGuardsTalk next: guardPatrol)
				else
					(startGuardScr next: guardPatrol)
				)
				((ScriptID 80 5) setScript: startGuardScr)
			)
			(else
				(cond 
					(((ScriptID 80 0) tstFlag: 709 2)
						((ScriptID 80 5) posn: 118 116)
						((ScriptID 80 6) posn: 133 117)
					)
					(((ScriptID 80 0) tstFlag: 711 256)
						(= spotEgoScr walkGuardsOnScreen)
						((ScriptID 80 0) clrFlag: 710 1 guardTimer: 2)
					)
					(local1
						((ScriptID 80 5) posn: 118 126)
						((ScriptID 80 6) posn: 133 126)
						(startGuardScr next: guardPatrol)
						((ScriptID 80 5) setScript: startGuardScr)
					)
				)
				(theGame handsOn:)
				(if (== prevRoomNum 781) (theGame handsOff:))
			)
		)
		(if (ego scaler?) ((ego scaler?) doit:))
		((ScriptID 80 0) setupGuards:)
		(if local1 (soundFx2 number: 702 loop: 1 play:))
	)
	
	(method (doit)
		(= local0 (ego onControl: 1))
		(cond 
			(script 0)
			(
				(and
					((User alterEgo?) edgeHit?)
					((ScriptID 80 0) tstFlag: 709 2)
				)
				(self setScript: weddingStarts)
			)
			((InRect 158 0 320 115 ego) (curRoom newRoom: 870))
			((& local0 $2000) (curRoom newRoom: 781))
			((& local0 $1200) (curRoom newRoom: 880))
		)
		(if
			(and
				(not ((ScriptID 80 0) tstFlag: 710 2))
				(< (ego y?) 117)
			)
			((ScriptID 80 0) setFlag: 710 2)
			(messager say: 3 3 17 0)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(roomConv add: -1 noun theVerb 0 1)
				1
				(if local1
					(roomConv add: -1 noun theVerb 21 1)
					1
				else
					(roomConv add: -1 noun theVerb 22 1)
					1
				)
				(roomConv init:)
				1
			)
			(2
				(if local1
					(messager say: noun theVerb 21)
					1
				else
					(messager say: noun theVerb 22)
					1
				)
			)
			(else 
				(super doVerb: theVerb &rest)
				1
			)
		)
	)
	
	(method (newRoom n)
		(if ((ScriptID 80 5) script?)
			((ScriptID 80 5) setScript: 0)
		)
		(if (!= n 730) ((ScriptID 80 0) clrFlag: 711 16384))
		(if ((ScriptID 80 0) tstFlag: 711 256)
			((ScriptID 80 0) guardTimer: 0)
		)
		(super newRoom: n &rest)
	)
	
	(method (warnUser param1)
		(switch param1
			(1
				(self setScript: weddingStartsNow)
			)
			(2
				(messager say: 1 0 24 0)
				(= spotEgoScr walkGuardsOnScreen)
				((ScriptID 80 0) setFlag: 711 256 guardTimer: 5)
			)
			(else 
				(super warnUser: param1 &rest)
			)
		)
	)
)

(instance hideEgo of Script
	(properties)
	
	(method (init)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(= register ((ScriptID 80 0) tstFlag: 709 8))
		(super init: &rest)
	)
	
	(method (doit)
		(if (and (ego mover?) (== state 3))
			(theGame handsOff:)
			(if (not local4)
				(= local4 ((User curEvent?) x?))
				(= local5 ((User curEvent?) y?))
				(= cycles 3)
			)
			(ego setMotion: 0)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 260 180 self)
			)
			(1 (ego setHeading: 225 self))
			(2
				(ego
					normal: 0
					view: 881
					setLoop: 2
					cel: 0
					x: 246
					y: 188
					setScale:
					scaleX: 148
					scaleY: 148
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(3
				(if (not local9)
					(if register (theGame handsOn:))
				else
					(= local9 0)
					(= cycles 1)
				)
			)
			(4
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				((ScriptID 80 5) sightAngle: 40)
				((ScriptID 80 6) sightAngle: 40)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego
					reset: 5 900
					posn: (hideFeature approachX?) (hideFeature approachY?)
				)
				(cond 
					(
						(and
							(CueObj client?)
							((CueObj client?) approachX?)
							(&
								((CueObj client?) _approachVerbs?)
								(approachCode doit: (CueObj theVerb?))
							)
						)
						(if (== (CueObj client?) hideFeature)
							(= local10 1)
							((CueObj client?) doVerb: (CueObj theVerb?))
						else
							(ego
								setMotion:
									PolyPath
									((CueObj client?) approachX?)
									(+ (ego z?) ((CueObj client?) approachY?))
									CueObj
							)
						)
					)
					(local4 (ego setMotion: PolyPath local4 local5))
				)
				(theGame handsOn:)
				(= local4 (= local5 (= register 0)))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (not (event modifiers?)) (User input?))
			(event claimed: 1)
			(if
				(and
					(== (theIconBar curIcon?) (theIconBar at: 3))
					local1
					(or
						((ScriptID 80 5) onMe: event)
						((ScriptID 80 6) onMe: event)
					)
				)
				(theGame handsOff:)
				(= local8 1)
				(CueObj
					state: 0
					cycles: 0
					client: (ScriptID 80 5)
					theVerb: 2
				)
				((CueObj client?) approachX: 218 approachY: 178)
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0)
				(messager say: 6 2 0 1 self)
				(= next talkToGuards)
			else
				(event claimed: 0)
			)
		)
		(event claimed?)
	)
)

(instance putDownBird of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(1 (= cycles 3))
			(2
				(messager say: 4 37 19 0 self)
			)
			(3
				(soundFx2 number: 930 loop: -1 play:)
				(ego
					normal: 0
					view: 883
					loop: 0
					cel: 0
					setScale:
					scaleX: 85
					scaleY: 85
					cycleSpeed: 8
					setCycle: Forward
				)
				(= seconds 5)
			)
			(4
				(soundFx2 loop: 1 stop:)
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5
				(bird init:)
				(theMusic fadeTo: 931 -1)
				(ego put: 27 850)
				(ego reset: 1)
				(theGame givePoints: 4)
				((ScriptID 80 0) setFlag: 711 16384)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance timerScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 7))
			(1
				(messager say: 1 0 2 1 self 880)
			)
			(2
				(if (& local0 (floor onMeCheck?))
					(theGame handsOff:)
					(curRoom spotEgo: (proc80_7))
					(self dispose:)
				else
					((ScriptID 80 6)
						setMotion: MoveTo (bird x?) (- (bird y?) 2)
					)
					((ScriptID 80 5)
						setMotion: MoveTo (- (bird x?) 30) (bird y?) self
					)
				)
			)
			(3
				((ScriptID 80 5) setMotion: MoveTo 129 144 self)
			)
			(4
				((ScriptID 80 5) setHeading: 90 self)
			)
			(5 (= cycles 4))
			(6
				(roomConv add: 880 1 0 2 2 add: 880 1 0 2 3 init: self)
			)
			(7
				((ScriptID 80 5)
					view: 884
					loop: 1
					cel: 0
					setCycle: CycleTo 4 1 self
				)
				(theMusic fadeTo: 700 -1)
			)
			(8
				(bird hide:)
				((ScriptID 80 5) setCycle: EndLoop self)
			)
			(9
				(roomConv
					add: 880 1 0 2 4
					add: 880 1 0 2 5
					add: 880 1 0 2 6
					add: 880 1 0 2 7
					add: 880 1 0 2 8
					add: 880 1 0 2 9
					add: 880 1 0 2 10
					add: 880 1 0 2 11
					add: 880 1 0 2 12
					init: self
				)
			)
			(10
				((ScriptID 80 5)
					view: 724
					loop: 4
					cel: 0
					setCycle: StopWalk -1
				)
				(self setScript: walkGuardsOffScreen self)
			)
			(11
				(messager say: 1 0 13 0 self)
			)
			(12
				(if (== (curRoom script?) hideEgo)
					(CueObj client: 0)
					(= local4 0)
					(hideEgo caller: self)
					(if (== ((curRoom script?) state?) 3)
						(hideEgo cue:)
					else
						(= local9 1)
					)
				else
					(= cycles 1)
				)
			)
			(13
				(curRoom spotEgo: (proc80_7))
				(self dispose:)
			)
		)
	)
)

(instance walkGuardsOffScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 5) setMotion: MoveTo 126 176 self)
				((ScriptID 80 6) setMotion: MoveTo 163 176)
			)
			(1
				(if register (curRoom spotEgo: (proc80_7)))
				(self dispose:)
			)
		)
	)
)

(instance captureEgo of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 5) setScript: 0 setMotion: 0 okToCheck: 0)
				((ScriptID 80 6) setScript: 0 setMotion: 0 okToCheck: 0)
				(Face register ego self)
			)
			(1 (messager say: 1 0 9 1 self))
			(2
				(cond 
					((not (ego facingMe: (ScriptID 80 5))) (Face (ScriptID 80 5) ego self))
					((not (ego facingMe: (ScriptID 80 6))) (Face (ScriptID 80 6) ego self))
					(else (= cycles 1))
				)
			)
			(3 (Face ego register self))
			(4
				(roomConv add: -1 1 0 9 2 add: -1 1 0 9 3 init: self)
			)
			(5
				((proc80_7) setScript: (ScriptID 80 4) self 1)
			)
			(6
				(roomConv
					add: -1 1 0 9 4
					add: -1 1 0 9 5
					add: -1 1 0 9 6
					init: self
				)
			)
			(7 (curRoom newRoom: 820))
		)
	)
)

(instance walkGuardsOnScreen of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 1)
				((ScriptID 80 0) clrFlag: 710 1)
				((ScriptID 80 5)
					init:
					posn: 126 231
					setMotion: MoveTo 126 188 self
				)
				((ScriptID 80 6)
					init:
					posn: 163 233
					setMotion: MoveTo 163 188
				)
				((ScriptID 80 0) setupGuards:)
			)
			(1
				(messager say: 1 0 11 0 self)
			)
			(2
				(if (== (curRoom script?) hideEgo)
					(CueObj client: 0)
					(= local4 0)
					(hideEgo caller: self)
					(if (== (hideEgo state?) 3)
						(hideEgo cue:)
					else
						(= local9 1)
					)
				else
					(self cue:)
				)
			)
			(3
				(if (not (vizierDoor cel?))
					(curRoom spotEgoScr: captureEgo)
					(curRoom spotEgo: (proc80_7))
				else
					((ScriptID 80 0) setFlag: 709 1)
				)
			)
		)
	)
)

(instance shouldNotHaveComeOut of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0)
				(= cycles 4)
			)
			(1 (messager say: 1 0 3 0 self))
			(2
				(curRoom spotEgo: (proc80_7))
			)
		)
	)
)

(instance talkToGuards of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 5) okToCheck: 0)
				((ScriptID 80 6) okToCheck: 0)
				((ScriptID 80 5) approachX: 0 approachY: 0)
				(if (not local8)
					(messager say: 6 2 0 1 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if (& local0 $0080)
					(ego setMotion: PolyPath 218 178 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (not (ego facingMe: (ScriptID 80 5)))
					(Face ego (ScriptID 80 5) self)
				else
					(= cycles 1)
				)
			)
			(3 (= cycles 1))
			(4
				(messager say: 6 2 0 2 self oneOnly: 0)
			)
			(5
				(curRoom spotEgo: (proc80_7))
				(self dispose:)
			)
		)
	)
)

(instance startGuardScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 5)
					ignoreActors: 1
					setMotion: MoveTo 118 116 self
				)
				((ScriptID 80 6) setMotion: MoveTo 133 117)
			)
			(1 (self dispose:))
		)
	)
)

(instance watchGuardsTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (messager say: 1 0 1 0 self))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance guardPatrol of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 6)))
			(1 (= cycles 2))
			(2
				(if (not local7)
					((ScriptID 80 5) setMotion: MoveTo 102 145 self)
					((ScriptID 80 6) setMotion: MoveTo 143 145)
				else
					(self dispose:)
				)
			)
			(3
				(= state -1)
				((ScriptID 80 5) setMotion: MoveTo 115 114 self)
				((ScriptID 80 6) setMotion: MoveTo 130 114)
			)
		)
	)
)

(instance weddingStarts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic number: 701 loop: -1 play:)
				(= cycles 2)
			)
			(1 (messager say: 1 0 8 1 self))
			(2 (messager say: 1 0 8 2 self))
			(3 (curRoom newRoom: 730))
		)
	)
)

(instance weddingStartsNow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) setFlag: 711 16384)
				(= cycles 1)
			)
			(1 (messager say: 1 0 8 0 self))
			(2
				((ScriptID 80 5) setScript: walkGuardsOffScreen 0 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance walkOutAtWrongTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(if (cast contains: bird)
					(messager say: 1 0 33 0 self)
				else
					(messager say: 1 0 7 0 self)
				)
			)
			(2
				(curRoom spotEgo: (proc80_7))
			)
		)
	)
)

(instance lookThruKeyhole of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0
					(switch (CueObj client?)
						(vizierDoor 795)
						(studyDoor 798)
					)
				)
				((ScriptID 82 1)
					noun: ((CueObj client?) noun?)
					actions: keyHoleActions
					init: temp0 0 0 92 54
				)
				(= cycles 2)
			)
			(1
				(messager say: ((CueObj client?) noun?) 1 29)
			)
		)
	)
)

(instance bird of Prop
	(properties
		x 146
		y 144
		view 883
		loop 4
		scaleSignal $0001
		scaleX 96
		scaleY 96
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward ignoreActors: setScript: timerScr)
	)
)

(instance portrait of View
	(properties
		x 200
		view 850
		loop 6
		signal $6001
	)
	
	(method (init)
		(= y
			(if (OneOf ((inventory at: 26) owner?) 880 ego)
				112
			else
				100
			)
		)
		(super init: &rest)
	)
)

(instance vizierDoor of NewProp
	(properties
		x 59
		y 111
		noun 9
		sightAngle 40
		approachX 79
		approachY 127
		view 850
		signal $4001
	)
	
	(method (init)
		(if cel (self setScript: closeMe))
		(super init: &rest)
		(if local1
			(if (not ((ScriptID 80 0) tstFlag: 709 2))
				(self approachVerbs: 5)
				(= normal 0)
			)
		else
			(self approachVerbs: 2 5)
		)
	)
	
	(method (doVerb theVerb)
		(if (not local1)
			(switch theVerb
				(5
					(if (not ((ScriptID 80 0) tstFlag: 711 1024))
						((ScriptID 80 0) setFlag: 711 1024)
						(messager say: noun theVerb 31 1)
					)
					(curRoom setScript: openVizDoor)
				)
				(1
					(if (not local2)
						(++ local2)
						(= _approachVerbs
							(| _approachVerbs (approachCode doit: 1))
						)
						(messager say: 10 theVerb 27)
					else
						(curRoom setScript: (ScriptID 82) 0 lookThruKeyhole)
					)
				)
				(2
					(messager say: noun theVerb 22)
				)
				(else 
					(if (== (approachCode doit: theVerb) -32768)
						(messager say: 9 0)
					else
						(super doVerb: theVerb)
					)
				)
			)
		else
			(switch theVerb
				(1
					(if (not local2)
						(++ local2)
						(messager say: 10 theVerb 27)
					else
						(messager say: 10 1 28)
					)
				)
				(5
					(if ((ScriptID 80 0) tstFlag: 709 2)
						(messager say: 9 theVerb 25)
					else
						(messager say: noun theVerb 21)
					)
				)
				(2
					(messager say: noun theVerb 21)
				)
				(else 
					(if (== (approachCode doit: theVerb) -32768)
						(messager say: 9 0)
					else
						(curRoom spotEgo: (proc80_7))
					)
				)
			)
		)
	)
	
	(method (onMe event)
		(if (== (theIconBar curIcon?) (theIconBar at: 2))
			(if (== (ego view?) 881)
				(= sightAngle 26505)
			else
				(= sightAngle 40)
			)
		)
		(super onMe: event)
	)
	
	(method (cue)
		(soundFx2 flags: 1 stop:)
		(if cel (proc80_2 4) else (self setPri: -1 stopUpd:))
	)
	
	(method (doSpecial param1)
		(switch param1
			(5
				(messager say: 10 5 21 0 NewProp)
			)
			(else  (super doSpecial:))
		)
	)
)

(instance openVizDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(soundFx2 number: 901 loop: 1 flags: 0 play:)
				(vizierDoor setPri: 10 setCycle: EndLoop self)
			)
			(2
				(vizierDoor cue:)
				(self dispose:)
			)
		)
	)
)

(instance closeMe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(vizierDoor setPri: 10 setCycle: BegLoop self)
			)
			(2
				(soundFx2 number: 902 loop: 1 play:)
				(if ((ScriptID 80 0) tstFlag: 709 128)
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance studyDoor of NewFeature
	(properties
		x 91
		y 114
		z 11
		noun 10
		nsTop 93
		nsLeft 88
		nsBottom 114
		nsRight 94
		sightAngle 360
		approachX 96
		approachY 115
	)
	
	(method (init)
		(super init: &rest)
		(if local1
			(if (not ((ScriptID 80 0) tstFlag: 709 2))
				(self approachVerbs: 5)
				(= normal 0)
			)
		else
			(self approachVerbs: 2 5)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((not local3)
						(++ local3)
						(messager say: noun theVerb 27)
						(if (not local1)
							(= _approachVerbs
								(| _approachVerbs (approachCode doit: 1))
							)
						)
					)
					(local1 (messager say: noun theVerb 28))
					(else (curRoom setScript: (ScriptID 82) 0 lookThruKeyhole))
				)
			)
			(2
				(if local1
					(messager say: 9 2 21 0)
				else
					(Bset 59)
					(messager say: noun theVerb 22 0 self)
				)
			)
			(5
				(if ((ScriptID 80 0) tstFlag: 709 2)
					(messager say: 9 theVerb 25)
				else
					(messager say: noun theVerb 22)
				)
			)
			(64
				(if local1
					(messager say: noun theVerb 21)
				else
					(messager say: noun theVerb 22)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
				(if
					(and
						(== (theIconBar curIcon?) (theIconBar at: 2))
						(or
							(and (== (ego view?) 881) (= sightAngle 26505))
							(= sightAngle 40)
						)
					)
				else
					1
				)
			else
				0
			)
		)
	)
	
	(method (doSpecial param1)
		(switch param1
			(5
				(messager say: 10 5 21 0 NewFeature)
			)
			(else  (super doSpecial:))
		)
	)
	
	(method (cue)
		(Bclr 59)
	)
)

(instance floor of NewFeature
	(properties
		noun 4
		onMeCheck $0042
	)
	
	(method (init)
		(super init: &rest)
		(= normal (not local1))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(37
				((ScriptID 80 0) setFlag: 709 256)
				(= local7 1)
				(curRoom setScript: putDownBird)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
				(cond 
					(
						(and
							(== (theIconBar curIcon?) (theIconBar useIconItem?))
							(== (theIconBar curInvIcon?) (inventory at: 27))
							(= _approachVerbs -32768)
							(= approachX 161)
							(= approachY 143)
						)
					)
					((not (= _approachVerbs 0)) (if (not (= approachX 0)) (not (= approachY 0))))
				)
			else
				0
			)
		)
	)
	
	(method (doSpecial param1)
		(switch param1
			(37
				(messager say: 4 37 20 0 self)
			)
			(else  (super doSpecial:))
		)
	)
)

(instance cassima_door of Feature
	(properties
		x 155
		y 109
		z 7
		heading 180
		noun 11
		nsTop 94
		nsLeft 152
		nsBottom 110
		nsRight 159
		approachX 154
		approachY 111
	)
	
	(method (onMe event)
		(if (super onMe: event)
			(if
			(and (== (ego view?) 881) (= sightAngle 26505))
			else
				(= sightAngle 45)
			)
		)
	)
)

(instance pillar of Feature
	(properties
		x 189
		y 134
		z 32
		noun 8
		nsTop 72
		nsLeft 183
		nsBottom 133
		nsRight 196
	)
	
	(method (onMe event)
		(if
			(and
				(super onMe: event)
				(or
					(and (== (ego view?) 881) (= sightAngle 26505))
					(= sightAngle 45)
				)
			)
			(if (InRect 175 71 206 135 event)
			else
				(InRect 191 135 206 144 event)
			)
		)
	)
)

(instance chairs of Feature
	(properties
		noun 19
		onMeCheck $0300
	)
	
	(method (onMe event)
		(= x (event x?))
		(= y (event y?))
		(= sightAngle 26505)
		(super onMe: event)
	)
)

(instance hideFeature of Feature
	(properties
		nsTop 64
		nsLeft 253
		nsBottom 177
		nsRight 319
		approachX 260
		approachY 180
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(cond 
					(local10 (= local10 0))
					((!= (curRoom script?) hideEgo) (curRoom setScript: hideEgo))
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance roomFeatures of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (onMe event &tmp temp0)
		(cond 
			(
				(or
					(and
						(&
							$0004
							(= temp0 (OnControl 4 (event x?) (event y?)))
						)
						(= noun 16)
					)
					(and (& $0008 temp0) (= noun 15))
				)
			)
			((& $0020 temp0) (= noun 13))
		)
	)
)

(instance guardsCode of Code
	(properties)
	
	(method (doit param1)
		(return
			(if (and (!= (ego view?) 881) (& local0 $0042))
			else
				(& (param1 onControl: 1) $0040)
			)
		)
	)
)

(instance okToCheckCode of Code
	(properties)
	
	(method (doit param1)
		(return
			(if
				(or
					(and
						(< 90 (param1 heading?))
						(< (param1 heading?) 270)
					)
					(<= (ego y?) (param1 y?))
				)
			else
				(<= (ego y?) 129)
			)
		)
	)
)

(instance guardDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(cond 
			((== theVerb 2) (curRoom setScript: talkToGuards))
			(((ScriptID 80 0) tstFlag: 709 2)
				(cond 
					((OneOf theVerb 5 1) (messager say: 6 theVerb 25))
					((== (approachCode doit: theVerb) -32768) (messager say: 6 0 25))
					(else (= temp0 0))
				)
			)
			((== theVerb 37)
				(CueObj client: floor state: 0 cycles: 0 theVerb: 37)
				(KQ6Print posn: -1 10 say: 0 6 37 0 1 init:)
				(ego
					setMotion: PolyPath (floor approachX?) (floor approachY?) CueObj
				)
			)
			(else (= temp0 0))
		)
		(return temp0)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance keyHoleActions of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(switch theVerb
			(1
				(messager say: ((CueObj client?) noun?) theVerb 29 2)
			)
			(else  (= temp0 0))
		)
		(return temp0)
	)
)
