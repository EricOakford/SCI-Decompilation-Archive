;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include sci.sh)
(use Main)
(use n451)
(use NewRoomCue)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm450 0
	oyster 1
	giveItemScript 2
	rightInvItem 3
	wrongInvItem 4
	toTheSea 5
	gnomeGroup 6
	snooze4 7
	proc450_8 8
)

(local
	egoX_2
	userCurEventY
	local2
	local3
	local4
	local5
	local6
)
(procedure (proc450_8 param1)
	(if param1
		(snoreSong stop:)
		(snooze1 view: 2002)
		(snooze4 view: 2002)
		(shimmer1 dispose:)
		(shimmer2 dispose:)
	else
		(snoreSong play:)
		(snooze1 view: 450 loop: 3 checkDetail:)
		(if ((ScriptID 40 0) oysterDozing?)
			(snooze4
				view: 450
				loop: 5
				init:
				setCycle: Fwd
				checkDetail:
			)
		)
		(shimmer1 init:)
		(shimmer2 init:)
	)
)

(procedure (localproc_100a param1)
	(tmpGnome
		view: (param1 view?)
		loop: (param1 loop?)
		cel: (param1 cel?)
		x: (param1 x?)
		y: (param1 y?)
		signal: (param1 signal?)
	)
	(if (cast contains: tmpGnome)
		(tmpGnome show:)
	else
		(tmpGnome init:)
	)
)

(procedure (localproc_2a46 param1)
	(switch local5
		(1
			((ScriptID 455 0) doVerb: param1)
		)
		(2
			((ScriptID 456 0) doVerb: param1)
		)
		(3
			((ScriptID 4561 0) doVerb: param1)
		)
		(4
			((ScriptID 457 0) doVerb: param1)
		)
		(5
			((ScriptID 458 0) doVerb: param1)
		)
	)
)

(instance rm450 of KQ6Room
	(properties
		picture 450
		horizon 70
		walkOffEdge 1
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 130 72 193 0 319 0 319 78 213 78
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						125
						72
						115
						80
						115
						92
						79
						97
						89
						111
						75
						128
						94
						141
						94
						150
						58
						150
						24
						189
						0
						189
						0
						0
						64
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						235
						147
						233
						140
						246
						124
						319
						124
						319
						189
						286
						189
						286
						153
						299
						147
						279
						144
						256
						151
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 125 137 94 137 90 134 93 131 124 131 129 134
					yourself:
				)
		)
		(theGame handsOff:)
		(super init: &rest)
		(oyster init:)
		(if
			(or
				(!= ((inventory at: 30) owner?) curRoomNum)
				((ScriptID 40 0) oysterDozing?)
			)
			(oyster setCel: 2)
			(snooze4 init: setCycle: Fwd)
		else
			(oyster setCel: 0)
			(oyBlink
				init:
				setPri: (oyster priority?)
				hide:
				setScript: oyBlinkScript
			)
		)
		(Lock 143 450 0)
		(soundFx2 number: 916 setLoop: -1 play:)
		(features
			add:
				farFoliage
				rock
				oysterBeds
				otherRocks
				smallRocks
				sky
				beach
				myPath
				oysterCouch
				ocean
			eachElementDo: #init
		)
		(shimmer1 init:)
		(shimmer2 init:)
		(snoreSong play:)
		(snooze1 init: setCycle: Fwd)
		(if (== ((inventory at: 50) owner?) curRoomNum)
			(mySentence init: setCycle: Fwd setScript: sentenceFloat)
		)
		(theMusic number: 915 setLoop: -1 play:)
		(ego setScale: Scaler 100 30 126 70 actions: egoDoVerb)
		(if (OneOf prevRoomNum 470 460)
			(curRoom setScript: egoEnters)
		)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			(
				(and
					(!= (ego view?) 900)
					(or
						(== (ego onControl: 1) 32)
						(== (ego onControl: 1) 64)
					)
				)
				(if
					(and
						(cast contains: mySentence)
						(> (mySentence y?) 158)
						(sentencePoly points?)
					)
					((curRoom obstacles?) delete: sentencePoly)
					(sentencePoly dispose: points: 0)
					(mySentence setScript: sentenceFloat)
				)
				(theGlobalSound stop:)
				(ego view: 900)
			)
			(
			(and (!= (ego view?) 308) (== (ego onControl: 1) 4))
				(if (cast contains: mySentence)
					(mySentence setMotion: 0 setScript: 0)
				)
				(cond 
					((!= (ego view?) 900) 0)
					((< (ego heading?) 135) (ego loop: 0))
					((> (ego heading?) 225) (ego loop: 1))
					(else (ego loop: 2))
				)
				(ego view: 308)
				(theGlobalSound number: 922 setLoop: -1 play:)
			)
			(
			(and (!= (ego view?) 3081) (== (ego onControl: 1) 2)) (ego view: 3081))
			(
				(and
					(not local2)
					(!= (ego view?) 3082)
					(== (ego onControl: 1) 8192)
				)
				(ego view: 3082)
			)
			((== (ego edgeHit?) 1) (curRoom newRoom: 470))
			((== (ego edgeHit?) 2) (curRoom setScript: egoExits))
			(
				(and
					(OneOf (ego onControl: 1) 64 128)
					(not (Btst 42))
				)
				(Bset 81)
				(theGlobalSound number: 451 setLoop: -1 play:)
				(self setScript: mainGnomeScript)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr 59)
		(LoadMany 0 455 456 4561 457 458)
		(if (!= ((inventory at: 30) owner?) curRoomNum)
			((ScriptID 40 0) oysterDozing: 0)
		)
		((ScriptID 40 0) setScript: 0)
		(if (cast contains: mySentence)
			(mySentence setMotion: 0)
		)
		(soundFx2 fade: 0 10 10)
		(super dispose:)
	)
	
	(method (cue)
		(if (cast contains: mySentence)
			(mySentence
				y: (+ (mySentence y?) 100)
				z: 0
				setScript: sentenceFloat
			)
		)
	)
	
	(method (notify param1)
		(cond 
			((or (not argc) (not param1)) 0)
			((not local5) (curRoom setScript: 130))
			(else
				(if (curRoom script?)
					((ScriptID 130) next: resetGnomes)
				)
				(curRoom setScript: 130)
			)
		)
		(ego actions: egoDoVerb)
	)
	
	(method (scriptCheck &tmp temp0)
		(= temp0 1)
		(if local5 (messager say: 0 0 194 1 0 899) (= temp0 0))
		(return temp0)
	)
)

(instance snoreSong of Sound
	(properties
		number 962
		loop -1
	)
)

(instance oysterGuts of Prop
	(properties
		x 60
		y 132
		noun 1
		view 4531
		cel 1
		priority 11
		signal $4010
	)
)

(instance pearlGlint of Prop
	(properties
		x 72
		y 133
		view 902
		loop 1
		priority 13
		signal $4010
	)
)

(instance snooze1 of Prop
	(properties
		x 66
		y 113
		z 20
		noun 8
		view 450
		loop 3
		signal $5000
		detailLevel 3
	)
	
	(method (checkDetail param1)
		(cond 
			((not detailLevel))
			(
				(<
					(if argc param1 else (theGame detailLevel:))
					detailLevel
				)
				(self cel: (- (self lastCel:) 1) stopUpd:)
			)
			(cycler (self startUpd:))
		)
	)
)

(instance snooze4 of Prop
	(properties
		x 103
		y 158
		z 40
		noun 8
		view 450
		loop 5
		signal $5000
		detailLevel 3
	)
	
	(method (checkDetail param1)
		(cond 
			((not detailLevel))
			(
				(<
					(if argc param1 else (theGame detailLevel:))
					detailLevel
				)
				(self cel: (- (self lastCel:) 1) stopUpd:)
			)
			(cycler (self startUpd:))
		)
	)
)

(instance oyster of Prop
	(properties
		x 60
		y 132
		noun 1
		approachDist 1000
		view 4531
		loop 2
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					(local5 (messager say: 1 0 1 1))
					((!= ((inventory at: 30) owner?) curRoomNum) (messager say: 1 1 7 1))
					(((ScriptID 40 0) oysterDozing?) (messager say: 1 1 8 1))
					(else (curRoom setScript: lookAtOyster))
				)
			)
			(5
				(cond 
					(
						(or
							(!= ((inventory at: 30) owner?) curRoomNum)
							((ScriptID 40 0) oysterDozing?)
						)
						(messager say: 1 5 2 1)
					)
					((Btst 107) (messager say: 1 5 3 0))
					(else (messager say: 1 5 4 0))
				)
			)
			(2
				(if (cast contains: gnomeGroup)
					(messager say: 1 0 1 1)
				else
					(ego setScript: oysterMessages 0 2)
				)
			)
			(42
				(if (cast contains: gnomeGroup)
					(messager say: 1 0 1 1)
				else
					(ego setScript: oysterMessages 0 42)
				)
			)
			(66 (messager say: 1 66 0 1))
			(30
				(if
					(or
						(!= ((inventory at: 30) owner?) curRoomNum)
						((ScriptID 40 0) oysterDozing?)
					)
					(messager say: 1 30 2 1)
				else
					(messager say: 1 30 9 1)
				)
			)
			(31
				(theGame handsOff:)
				(curRoom setScript: oyFluteScript)
			)
			(else 
				(cond 
					((cast contains: gnomeGroup) (messager say: 1 0 1 1))
					(
						(or
							(!= ((inventory at: 30) owner?) curRoomNum)
							((ScriptID 40 0) oysterDozing?)
						)
						(messager say: 1 5 2 1)
					)
					((Btst 107) (messager say: 1 0 3 1 self))
					(else (messager say: 1 0 4 1 self))
				)
			)
		)
	)
)

(instance oyBlink of Prop
	(properties
		x 60
		y 132
		view 4531
		loop 1
		signal $4000
	)
)

(instance oyBlinkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 8)))
			(1 (client show:) (= cycles 6))
			(2 (client hide:) (self init:))
		)
	)
)

(instance oyFluteScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 140 130 self)
			)
			(1
				(if
					(or
						(!= ((inventory at: 30) owner?) curRoomNum)
						((ScriptID 40 0) oysterDozing?)
					)
					(messager say: 1 31 2 1 self)
				else
					(messager say: 1 31 9 0 self)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance lookAtOyster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 1 9 1 self)
			)
			(1
				(theGlobalSound number: 961 setLoop: 1 play:)
				(oysterGuts init:)
				(oyster setPri: 12 setLoop: 1 cel: 0 setCycle: End self)
			)
			(2
				(pearlGlint init: setCycle: End self)
			)
			(3
				(pearlGlint dispose:)
				(oyster setCycle: Beg self)
			)
			(4
				(oysterGuts dispose:)
				(oyster setLoop: 2 cel: 0 setPri: 11)
				(= cycles 2)
			)
			(5 (messager say: 1 1 9 2 self))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oysterMessages of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (== (ego x?) 140) (== (ego y?) 130))
					(= cycles 1)
				else
					(ego setMotion: PolyPath 140 130 self)
				)
			)
			(1
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if
					(and
						(== register 2)
						(== ((inventory at: 30) owner?) curRoomNum)
						(not (Btst 107))
					)
					(messager say: 1 2 10 1 self)
				else
					(= cycles 1)
				)
			)
			(3
				(if
					(and
						(== register 2)
						(== ((inventory at: 30) owner?) curRoomNum)
					)
					(oyster setPri: 12)
				)
				(= cycles 6)
			)
			(4
				(switch register
					(2
						(cond 
							(
								(or
									(!= ((inventory at: 30) owner?) curRoomNum)
									((ScriptID 40 0) oysterDozing?)
								)
								(messager say: 1 5 2 1 self)
							)
							((Btst 107) (messager say: 1 2 11 0 self))
							(else (Bset 107) (messager say: 1 2 10 2 self))
						)
					)
					(42
						(cond 
							((!= ((inventory at: 30) owner?) curRoomNum) (messager say: 1 42 7 1 self))
							((not (Btst 107)) (messager say: 1 42 4 1 self))
							(((ScriptID 40 0) oysterDozing?) (messager say: 1 42 8 1 self))
							((Btst 108)
								(mySentence y: (- (mySentence y?) 100) z: -100)
								(oyBlink dispose:)
								(proc450_8 1)
								(proc451_0 1)
								(self dispose:)
							)
							(else
								(Bset 108)
								(theGame givePoints: 2)
								(mySentence y: (- (mySentence y?) 100) z: -100)
								(oyBlink dispose:)
								(proc450_8 1)
								(proc451_0 0)
								(self dispose:)
							)
						)
					)
				)
			)
			(5
				(theGame handsOn:)
				(if (== register 2) (oyster setPri: 11))
				(self dispose:)
			)
		)
	)
)

(instance mySentence of Actor
	(properties
		x 160
		y 185
		noun 11
		view 4501
		priority 5
		signal $0010
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(cond 
			((or (== theVerb 1) (== theVerb 2)) (super doVerb: theVerb))
			((== theVerb 5)
				(cond 
					((cast contains: gnomeGroup) (messager say: 1 0 1 1))
					((cast contains: ego) (theGame handsOff:) (ego setScript: getSentence))
					(else (messager say: 11 1 0 1))
				)
			)
			(else (messager say: 11 0 0 1))
		)
	)
)

(instance shimmer1 of Prop
	(properties
		x 27
		y 169
		noun 7
		view 447
		priority 1
		signal $4010
		cycleSpeed 30
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init:)
	)
)

(instance shimmer2 of Prop
	(properties
		x 299
		y 171
		noun 7
		view 447
		loop 1
		priority 1
		signal $4010
		cycleSpeed 30
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init:)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance farFoliage of Feature
	(properties
		noun 3
		onMeCheck $0200
	)
)

(instance oysterBeds of Feature
	(properties
		noun 8
		onMeCheck $0010
	)
)

(instance oysterCouch of Feature
	(properties
		onMeCheck $4000
	)
	
	(method (doVerb theVerb)
		(oyster doVerb: theVerb &rest)
	)
)

(instance otherRocks of Feature
	(properties
		noun 6
		onMeCheck $0100
	)
)

(instance smallRocks of Feature
	(properties
		noun 13
		onMeCheck $0400
	)
)

(instance sky of Feature
	(properties
		noun 10
		onMeCheck $0800
	)
)

(instance myPath of Feature
	(properties
		noun 9
		onMeCheck $1000
	)
)

(instance rock of Feature
	(properties
		noun 5
		onMeCheck $0088
	)
)

(instance beach of Feature
	(properties
		noun 10
		onMeCheck $0060
	)
)

(instance ocean of Feature
	(properties
		noun 7
		onMeCheck $2006
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp userCurEvent)
		(= userCurEvent (user curEvent?))
		(switch theVerb
			(3
				(if
					(and
						(or
							(== (ego onControl: 1) 32)
							(== (ego onControl: 1) 64)
						)
						(> (userCurEvent y?) 171)
					)
					(= userCurEventY 165)
					(if (< (userCurEvent x?) (ego x?))
						(= egoX_2 (- (ego x?) 30))
					else
						(= egoX_2 (+ (ego x?) 30))
					)
				else
					(= egoX_2 (userCurEvent x?))
					(= userCurEventY (userCurEvent y?))
				)
				(ego setScript: inToWater 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class Gnome of Actor
	(properties
		x 0
		y 127
		z 1
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
		signal $4000
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
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 6
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		gnomesItem 0
		msgCase 0
		failCase 0
		textCase 0
		EOLx 0
		FOLx 0
		gnomeScript 0
		startPoint 0
	)
)

(instance gnomeGroup of Actor
	(properties
		x 130
		y 71
		noun 4
		yStep 1
		view 454
		signal $4000
		cycleSpeed 12
		illegalBits $0000
	)
	
	(method (dispose)
		(if (IsObject scaler) (scaler dispose:))
		(= scaler 0)
		(super dispose:)
	)
	
	(method (cue)
		(if (IsObject scaler) (scaler dispose:))
		(= scaler 0)
		(super dispose:)
	)
)

(instance tmpGnome of Actor
	(properties)
)

(instance mainGnomeScript of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 3 19 1 self)
			)
			(1
				(ego setMotion: MoveTo 236 128 self)
			)
			(2
				(ego setHeading: 335)
				(= cycles 8)
			)
			(3
				(snoreSong stop:)
				(snooze1 dispose:)
				(if (cast contains: snooze4) (snooze4 dispose:))
				(gnomeGroup
					setScale: Scaler 100 30 126 70
					init:
					setCycle: Walk
					setMotion: MoveTo 96 126 self
				)
			)
			(4
				(ego setHeading: 270)
				(= cycles 8)
			)
			(5
				(messager say: 10 3 19 2 self)
			)
			(6
				(gnomeGroup
					setScale: 0
					view: 4541
					setLoop: 0
					cel: 0
					posn: 146 128
					setCycle: End self
				)
				(UnLoad 128 454)
			)
			(7
				(= local5 1)
				((ScriptID 455 0) init:)
				(gnomeGroup
					view: 459
					loop: -1
					setLoop: 0
					x: 155
					y: 126
					stopUpd:
					setScript: riddleAlex 0 (ScriptID 455 0)
				)
				(UnLoad 128 4541)
				(= cycles 2)
			)
			(8)
			(9
				(localproc_100a (ScriptID 455 0))
				((ScriptID 455 0) dispose:)
				(= cycles 2)
			)
			(10
				(DisposeScript 455)
				(UnLoad 128 455)
				(= cycles 2)
			)
			(11
				(= local5 2)
				((ScriptID 456 0) init:)
				(gnomeGroup
					view: 4591
					loop: -1
					setScript: riddleAlex 0 (ScriptID 456 0)
				)
				(tmpGnome view: 2002 hide:)
				(UnLoad 128 459)
				(= cycles 2)
			)
			(12)
			(13
				(ego setMotion: MoveTo (- (ego x?) 6) (ego y?) self)
			)
			(14 (= cycles 2))
			(15
				(localproc_100a (ScriptID 456 0))
				((ScriptID 456 0) dispose:)
				(= cycles 2)
			)
			(16
				(DisposeScript 456)
				(UnLoad 128 456)
				(= cycles 2)
			)
			(17
				(= local5 3)
				((ScriptID 4561 0) init:)
				(gnomeGroup
					view: 4592
					loop: -1
					setScript: riddleAlex 0 (ScriptID 4561 0)
				)
				(tmpGnome view: 2002 hide:)
				(UnLoad 128 4591)
				(= cycles 2)
			)
			(18)
			(19
				(ego setMotion: MoveTo (- (ego x?) 1) (ego y?) self)
			)
			(20 (= cycles 2))
			(21
				(localproc_100a (ScriptID 4561 0))
				((ScriptID 4561 0) dispose:)
				(= cycles 2)
			)
			(22
				(DisposeScript 4561)
				(UnLoad 128 4561)
				(UnLoad 128 8933)
				(= cycles 2)
			)
			(23
				(= local5 4)
				((ScriptID 457 0) init:)
				(gnomeGroup
					view: 4593
					loop: -1
					setScript: riddleAlex 0 (ScriptID 457 0)
				)
				(tmpGnome view: 2002 hide:)
				(UnLoad 128 4592)
				(= cycles 2)
			)
			(24)
			(25 (= cycles 4))
			(26
				(localproc_100a (ScriptID 457 0))
				((ScriptID 457 0) dispose:)
				(= cycles 2)
			)
			(27
				(DisposeScript 457)
				(UnLoad 128 457)
				(= cycles 2)
			)
			(28
				(= local5 5)
				((ScriptID 458 0) init:)
				(gnomeGroup
					view: 4594
					loop: -1
					setScript: riddleAlex 0 (ScriptID 458 0)
				)
				(tmpGnome view: 2002 hide:)
				(UnLoad 128 4593)
				(= cycles 2)
			)
			(29)
			(30
				(localproc_100a (ScriptID 458 0))
				((ScriptID 458 0) dispose:)
				(= cycles 2)
			)
			(31
				(DisposeScript 458)
				(UnLoad 128 458)
				(= local5 0)
				(gnomeGroup
					view: 4544
					setLoop: 1
					cel: 0
					posn: 110 128
					setCycle: End self
				)
				(tmpGnome dispose:)
				(UnLoad 128 4593)
			)
			(32
				(gnomeGroup
					view: 4540
					setScale: Scaler 100 25 126 70
					setLoop: 2
					x: 106
					y: 126
					setCycle: Walk
					setMotion: MoveTo 131 70 self
				)
				(UnLoad 128 4544)
			)
			(33
				(gnomeGroup dispose:)
				(ego
					view: 452
					setLoop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: End self
				)
				((ScriptID 40 0) alexInvisible: 0)
			)
			(34
				(if ((ScriptID 40 0) alexX?)
					(= egoX ((ScriptID 40 0) alexX?))
					(= egoY ((ScriptID 40 0) alexY?))
				else
					(= egoX (ego x?))
					(= egoY (ego y?))
				)
				(ego
					posn: egoX egoY
					setScale: Scaler 100 30 126 70
					reset: 1
				)
				(= ticks 4)
			)
			(35
				(theGame handsOn:)
				(snoreSong play:)
				(snooze1 init: setCycle: Fwd checkDetail:)
				(if ((ScriptID 40 0) oysterDozing?)
					(snooze4 init: setCycle: Fwd checkDetail:)
				)
				(NextAct)
				(Bset 42)
				(messager say: 2 83 14 3)
				(self dispose:)
				(theGlobalSound fade: 0 10 10)
				(DisposeScript 1037)
			)
		)
	)
)

(instance riddleAlex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gnomeGroup
					setLoop: (+ (gnomeGroup loop?) 1)
					x: (register FOLx?)
					cel: 0
				)
				(= cycles 4)
			)
			(1 (gnomeGroup stopUpd:))
			(2
				(theGlobalSound number: 452 setLoop: 1 play:)
				(register setLoop: 3 cel: 0 setCycle: End self)
			)
			(3
				(theGame handsOn:)
				(theIconBar disable: 0 1 3)
				(if (!= (ego heading?) 270) (ego setHeading: 270))
				(= seconds 12)
			)
			(4
				(if (!= (curRoom script?) mainGnomeScript)
					0
				else
					(theGame handsOff:)
					(Bset 59)
					(= cycles 6)
				)
			)
			(5
				((register gnomeScript?) start: (register startPoint?))
				(gnomeGroup setScript: (register gnomeScript?))
			)
		)
	)
)

(instance giveItemScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 egoX egoY temp4)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= egoX (ego x?))
				(= egoY (ego y?))
				((ScriptID 40 0) alexX: (ego x?))
				((ScriptID 40 0) alexY: (ego y?))
				(switch register
					(37
						(self setScript: giveGnomeBird self register)
					)
					(83
						(self setScript: inkOutAlex self register)
					)
					(31
						(self setScript: blowFlute self register)
					)
					(else 
						(switch register
							(47
								(= temp0 452)
								(= temp1 0)
								(= egoX (+ egoX 2))
								(= temp4 4)
							)
							(68
								(= temp4 4)
								(= temp0 452)
								(= temp1 4)
							)
							(else 
								(= temp0 452)
								(= temp1 3)
								(= temp4 4)
							)
						)
						(ego
							view: temp0
							setLoop: temp1
							normal: 0
							cel: 0
							x: egoX
							y: egoY
							cycleSpeed: 0
							setCycle: CT temp4 1 self
						)
						(UnLoad 128 900)
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance rightInvItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local5
					(1
						(if (not (Bset 145)) (theGame givePoints: 2))
						(UnLoad 128 8931)
					)
					(2
						(if (not (Bset 146)) (theGame givePoints: 2))
						(UnLoad 128 8932)
					)
					(3
						(if (not (Bset 147)) (theGame givePoints: 2))
						(UnLoad 128 8933)
					)
					(4
						(if (not (Bset 148)) (theGame givePoints: 2))
						(UnLoad 128 8934)
					)
					(5
						(if (not (Bset 149)) (theGame givePoints: 2))
						(UnLoad 128 8935)
					)
				)
				(theGlobalSound number: 451 setLoop: -1 play:)
				(register
					setLoop: 0
					cel: 3
					z: 0
					setPri: 8
					setMotion: MoveTo (register x?) (- (register y?) 4) self
				)
			)
			(1
				(register
					cel: 0
					setLoop: 5
					y: 125
					setCycle: Fwd
					setMotion: MoveTo (register EOLx?) 125 self
				)
			)
			(2
				(register loop: 0 cel: 2 setCycle: 0)
				(gnomeGroup setCycle: End self)
			)
			(3
				(register y: 126 cel: 0)
				(= ticks 4)
			)
			(4
				(mainGnomeScript cue:)
				(self dispose:)
			)
		)
	)
)

(instance wrongInvItem of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(if ((ScriptID 40 0) alexInvisible?)
					(ego view: 452 setLoop: 1 cel: 0 setCycle: End self)
					((ScriptID 40 0) alexInvisible: 0)
				else
					(self cue:)
				)
			)
			(1
				(if ((ScriptID 40 0) alexX?)
					(= egoX ((ScriptID 40 0) alexX?))
					(= egoY ((ScriptID 40 0) alexY?))
				else
					(= egoX (ego x?))
					(= egoY (ego y?))
				)
				(if (!= (ego view?) 900)
					(ego reset: 1 posn: egoX egoY)
				)
				(= cycles 8)
			)
			(2 (self dispose:))
		)
	)
)

(instance toTheSea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 59)
				(curRoom drawPic: 450 -32758)
				(if (cast contains: mySentence)
					(mySentence setCycle: 0 setMotion: 0 setScript: 0)
				)
				(ego hide:)
				(theGlobalSound number: 457 setLoop: 1 play: self)
				(gnomeGroup
					view: 4542
					setLoop: 0
					cel: 0
					posn: 200 138
					init:
					setCycle: Fwd
				)
			)
			(1
				(soundFx number: 458 setLoop: 1 play:)
				(gnomeGroup setLoop: 1)
				(ego
					view: 4542
					setLoop: 2
					setPri: 15
					posn: 195 110
					normal: 0
					show:
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(2
				(curRoom style: 7)
				(switch local5
					(1 (DisposeScript 455))
					(2 (DisposeScript 456))
					(3 (DisposeScript 4561))
					(4 (DisposeScript 457))
					(5 (DisposeScript 458))
				)
				(= deathReason 35)
				(= cycles 10)
			)
			(3 (curRoom newRoom: 135))
		)
	)
)

(instance giveGnomeBird of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 883
					normal: 0
					posn: (- (ego x?) 1) (+ (ego y?) 1)
					loop: 0
					cycleSpeed: 50
				)
				(UnLoad 128 900)
				(= cycles 6)
			)
			(1
				(soundFx number: 930 setLoop: 1 play:)
				(ego cel: 0 setCycle: End self)
			)
			(2
				(if (< local4 4) (++ local4) (= state (- state 2)))
				(self cue:)
			)
			(3
				(soundFx number: 931 setLoop: 1 play: self)
				(UnLoad 132 930)
				(ego setLoop: 6 cel: 0 cycleSpeed: 0 setCycle: Fwd)
				(if (== local5 2)
					((ScriptID 456 0) setLoop: 1 setCycle: Fwd)
				)
			)
			(4
				(ego view: 883 normal: 0 setCycle: 0 loop: 0)
				(= cycles 6)
			)
			(5
				(soundFx number: 0)
				(UnLoad 132 931)
				(self dispose:)
			)
		)
	)
)

(instance inkOutAlex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 2 83 14 1 self)
			)
			(1
				(ego
					normal: 0
					view: 452
					setLoop: 5
					cel: 0
					normal: 0
					posn: (+ (ego x?) 2) (+ (ego y?) 1)
					cycleSpeed: 2
					setCycle: End self
				)
				(UnLoad 128 900)
			)
			(2
				(ego setLoop: 6 cel: 0 setCycle: End self)
			)
			(3
				(ego put: 51 450)
				(theIconBar curIcon: (theIconBar at: 0))
				((ScriptID 40 0) alexInvisible: 1)
				(self dispose:)
			)
		)
	)
)

(instance blowFlute of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(ego
					normal: 0
					view: 920
					setLoop: 1
					posn: (ego x?) (+ (ego y?) 1)
					cel: 0
					cycleSpeed: 6
				)
				(UnLoad 128 900)
				(= cycles 4)
			)
			(1
				(soundFx number: 942 setLoop: 1 play: self)
				(ego setCycle: Fwd)
			)
			(2
				(if ((ScriptID 40 0) alexX?)
					(= egoX ((ScriptID 40 0) alexX?))
					(= egoY ((ScriptID 40 0) alexY?))
				else
					(= egoX (ego x?))
					(= egoY (ego y?))
				)
				(ego reset: 1 posn: egoX egoY)
				(= cycles 4)
			)
			(3
				(UnLoad 132 942)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init: setScale: Scaler 100 30 126 70)
				(if (ego scaler?) ((ego scaler?) doit:))
				(switch prevRoomNum
					(460
						(ego posn: 345 90 setMotion: PolyPath 300 90 self)
					)
					(470
						(ego posn: 130 72 setMotion: PolyPath 134 101 self)
					)
					(else 
						(ego
							signal: (| (ego signal?) $4000)
							posn: 228 121
							loop: 0
						)
						(= ticks 6)
					)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath (+ (ego x?) 15) (ego y?) self)
			)
			(1 (curRoom newRoom: 460))
		)
	)
)

(instance inToWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(> (mySentence y?) 158)
						(not (sentencePoly points?))
					)
					(mySentence ignoreActors: 1)
					(curRoom
						addObstacle:
							(sentencePoly
								type: 2
								init:
									(- (mySentence x?) 18)
									(mySentence y?)
									(- (mySentence x?) 10)
									(- (mySentence y?) 5)
									(+ (mySentence x?) 17)
									(- (mySentence y?) 5)
									(+ (mySentence x?) 24)
									(mySentence y?)
									(+ (mySentence x?) 18)
									(+ (mySentence y?) 17)
									(- (mySentence x?) 10)
									(+ (mySentence y?) 17)
								yourself:
							)
					)
					(= cycles 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath egoX_2 userCurEventY self)
			)
			(2 (= cycles 4))
			(3
				(cond 
					((== register mySentence) (myConv add: -1 7 3 17 1 add: -1 7 3 17 2 init: self))
					(
						(or
							(== (ego onControl: 1) 4)
							(== (ego onControl: 1) 2)
						)
						(messager say: 7 3 16 1 self)
					)
					((== (ego onControl: 1) 8192) (myConv add: -1 7 3 17 1 add: -1 7 3 17 2 init: self))
					(else (= cycles 1))
				)
			)
			(4
				(if
					(or
						(== (ego onControl: 1) 8192)
						(== register mySentence)
					)
					(= cycles 1)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(5
				(= local2 1)
				(theGlobalSound number: 921 setLoop: 1 play:)
				(ego
					view: 309
					cel: 0
					normal: 0
					cycleSpeed: 6
					posn: (ego x?) (+ (ego y?) 15)
					setCycle: End self
				)
			)
			(6
				(theGame handsOn:)
				(= deathReason 34)
				(curRoom newRoom: 135)
			)
		)
	)
)

(instance sentencePoly of Polygon
	(properties)
)

(instance sentenceFloat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 20))
			(1
				(mySentence setStep: 1 1 setLoop: 2)
				(cond 
					((> (mySentence y?) 175) (mySentence setMotion: MoveTo 135 175 self))
					((> (mySentence y?) 158) (mySentence setMotion: MoveTo 120 154 self))
					(else (self dispose:))
				)
			)
			(2
				(if
					(and
						(< (mySentence y?) 158)
						(not (sentencePoly points?))
					)
					(mySentence ignoreActors: 1)
					(curRoom
						addObstacle:
							(sentencePoly
								type: 2
								init:
									(- (mySentence x?) 18)
									(mySentence y?)
									(- (mySentence x?) 10)
									(- (mySentence y?) 5)
									(+ (mySentence x?) 17)
									(- (mySentence y?) 5)
									(+ (mySentence x?) 24)
									(mySentence y?)
									(+ (mySentence x?) 18)
									(+ (mySentence y?) 17)
									(- (mySentence x?) 10)
									(+ (mySentence y?) 17)
								yourself:
							)
					)
				)
				(self init:)
			)
		)
	)
)

(instance getSentence of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mySentence setMotion: 0 setScript: 0)
				(if
					(and
						(> (mySentence y?) 158)
						(not (sentencePoly points?))
					)
					(mySentence ignoreActors: 1)
					(curRoom
						addObstacle:
							(sentencePoly
								type: 2
								init:
									(- (mySentence x?) 18)
									(mySentence y?)
									(- (mySentence x?) 10)
									(- (mySentence y?) 5)
									(+ (mySentence x?) 17)
									(- (mySentence y?) 5)
									(+ (mySentence x?) 24)
									(mySentence y?)
									(+ (mySentence x?) 18)
									(+ (mySentence y?) 17)
									(- (mySentence x?) 10)
									(+ (mySentence y?) 17)
								yourself:
							)
					)
				)
				(if
					(and
						(or
							(== (ego onControl: 1) 32)
							(== (ego onControl: 1) 64)
						)
						(> (mySentence y?) 158)
					)
					(= local3 1)
					(messager say: 11 5 20 1 self)
				else
					(self cue:)
				)
			)
			(1
				(ego
					setMotion: PolyPath (mySentence x?) (- (mySentence y?) 11) self
				)
			)
			(2
				(ego setHeading: 180)
				(= cycles 8)
			)
			(3
				(cond 
					((and local3 (> (mySentence y?) 158)) (messager say: 11 5 20 2 self))
					((> (mySentence y?) 158) (messager say: 11 5 21 1 self))
					(else (self cue:))
				)
			)
			(4
				(cond 
					((and local3 (> (mySentence y?) 158)) (= local3 0) (theGame handsOn:) (self dispose:))
					((> (mySentence y?) 160)
						(= egoX_2 (ego x?))
						(= userCurEventY (+ (ego y?) 1))
						(self dispose:)
						(mySentence
							setStep: 20 15
							setLoop: 2
							setMotion: MoveTo (- (mySentence x?) 28) (mySentence y?)
						)
						(curRoom setScript: inToWater 0 mySentence)
					)
					(else (self cue:))
				)
			)
			(5
				(curRoom setScript: pickUpSentence self)
				((curRoom obstacles?) delete: sentencePoly)
				(sentencePoly dispose:)
				(self dispose:)
			)
		)
	)
)

(instance pickUpSentence of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 311
					setLoop: 1
					normal: 0
					cel: 0
					setPri: (ego priority?)
					posn: (- (ego x?) 2) (+ (ego y?) 11)
					cycleSpeed: 6
					setCycle: CT 2 1 self
				)
			)
			(1
				(mySentence dispose:)
				(soundFx number: 924 setLoop: 1 play:)
				(ego setCycle: End self)
			)
			(2
				(theGame givePoints: 1)
				(ego
					reset: 2
					posn: (+ (ego x?) 2) (- (ego y?) 11)
					get: 50
				)
				(= cycles 8)
			)
			(3
				(messager say: 11 5 0 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance resetGnomes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local5
					(1 (mainGnomeScript start: 8))
					(2 (mainGnomeScript start: 12))
					(3 (mainGnomeScript start: 18))
					(4 (mainGnomeScript start: 24))
					(5 (mainGnomeScript start: 29))
				)
				(curRoom setScript: mainGnomeScript)
				((gnomeGroup script?)
					state: (- ((gnomeGroup script?) state?) 1)
					cue:
				)
			)
		)
	)
)

(instance egoDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(12
					(if
						(or
							(== (ego onControl: 1) 32)
							(== (ego onControl: 1) 64)
						)
						(if (curRoom script?)
							((ScriptID 130) next: resetGnomes)
						)
						(curRoom setScript: 130)
						(return 1)
					else
						(curRoom setScript: 130)
						(return 1)
					)
				)
				(31
					(if local5
						(localproc_2a46 theVerb)
						(return 1)
					else
						(return 0)
					)
				)
				(37
					(if local5
						(localproc_2a46 theVerb)
						(return 1)
					else
						(return 0)
					)
				)
				(83
					(if local5
						(localproc_2a46 theVerb)
						(return 1)
					else
						(return 0)
					)
				)
				(else  (return 0))
			)
		)
	)
)
