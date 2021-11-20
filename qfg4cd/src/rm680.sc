;;; Sierra Script 1.0 - (do not remove this comment)
(script# 680)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use Intrface)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm680 0
)

(local
	local0
	local1
)
(instance rm680 of GloryRm
	(properties
		picture 680
		west 628
	)
	
	(method (init)
		(if debugging
			(= local0 (GetNumber {Event #?}))
		else
			(= local0 (if (not (Btst 232)) 1 else 0))
		)
		(theMusic number: 680 setLoop: -1 play:)
		(RemapColors 2 253 140)
		(RemapColors 2 254 60)
		(ego
			posn: 163 42
			init:
			setScaler: Scaler 105 52 154 35
			normalize: 5
		)
		(pCoffinLid init: approachVerbs: 4)
		(pKatrina init: approachVerbs: 4 hide:)
		(fireSound play:)
		(pTorch1 setCycle: Fwd init:)
		(pTorch2 setCycle: Fwd init:)
		(pTorch3 setCycle: Fwd init:)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-300
						-300
						619
						-300
						619
						489
						302
						489
						162
						178
						147
						166
						161
						151
						189
						142
						223
						145
						224
						143
						186
						139
						156
						139
						145
						152
						85
						159
						39
						86
						194
						34
						191
						29
						189
						28
						23
						83
						32
						150
						39
						489
						-300
						489
					yourself:
				)
		)
		(fSconce1 init: approachVerbs: 4)
		(fSconce2 init: approachVerbs: 4)
		(fSconce3 init: approachVerbs: 4)
		(fShield init: approachVerbs: 4)
		(fMirror init: approachVerbs: 4)
		(fChair init: approachVerbs: 4)
		(fDesk init: approachVerbs: 4)
		(fStatue1 init: approachVerbs: 4)
		(fStatue2 init: approachVerbs: 4)
		(fPlant1 init: approachVerbs: 4)
		(fPlant2 init: approachVerbs: 4)
		(fCurtain1 init: approachVerbs: 4)
		(fUrn1 init: approachVerbs: 4)
		(fUrn2 init: approachVerbs: 4)
		(fCoffin init: approachVerbs: 4)
		(fTassles init: approachVerbs: 4)
		(fCurtain2 init: approachVerbs: 4)
		(fBedCurtain init: approachVerbs: 4)
		(fRoom init:)
		(fDoor init:)
		(curRoom setScript: sEnter)
		(katrinaTeller init: pKatrina 680 18 4)
		(theGame save: 1)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				local1
				(not (event modifiers?))
				(OneOf (event type?) 4 1)
				(not (curRoom script?))
			)
			(cond 
				((== ((theIconBar getCursor:) view?) 942) (event claimed: 1) (katrinaTeller doVerb: 4))
				(
					(and
						(== ((theIconBar getCursor:) view?) 905)
						(== ((theIconBar getCursor:) loop?) 4)
						(or
							(== ((theIconBar getCursor:) cel?) 6)
							(== ((theIconBar getCursor:) cel?) 7)
						)
					)
					(event claimed: 1)
					(curRoom setScript: sKatrinaKilled)
				)
				(else (event claimed: 1) (curRoom setScript: sKatrinaMad))
			)
		else
			(event claimed: 0)
			(super handleEvent: event &rest)
		)
	)
	
	(method (doVerb theVerb)
		(if (not local1)
			(switch theVerb
				(86 (messager say: 15 6 3))
				(93 (messager say: 15 6 3))
				(88 (messager say: 15 6 3))
				(37 (messager say: 15 6 3))
				(91 (messager say: 15 6 3))
				(21
					(ego use: 6)
					(curRoom setScript: (ScriptID 32) 0 21)
				)
				(1 (messager say: 1 1 0 0))
				(171
					(curRoom setScript: sKatrinaKilled)
				)
				(170
					(curRoom setScript: sKatrinaKilled)
				)
				(else  (super doVerb: theVerb))
			)
		else
			(switch theVerb
				(171
					(curRoom setScript: sKatrinaKilled)
				)
				(170
					(curRoom setScript: sKatrinaKilled)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 24)
			)
			(1
				(ego setMotion: PolyPath 102 165 self)
			)
			(2
				(ego normalize:)
				(if (== local0 1)
					(messager say: 15 6 1 1 self)
				else
					(self cue:)
				)
			)
			(3
				(if (and (== heroType 3) (== local0 1))
					(messager say: 15 6 2 1 self)
				else
					(self cue:)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTo670 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (fDoor approachX?) (fDoor approachY?) self
				)
			)
			(1 (curRoom newRoom: 670))
		)
	)
)

(instance sBringUpInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setLoop: 4 1)
				(openSound play:)
				(pCoffinLid setCycle: End self)
			)
			(1 (= seconds 2))
			(2
				(= local1 1)
				(cast eachElementDo: #hide)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(self cue:)
			)
			(3
				(vMyInset posn: 58 38 ignoreActors: 1 init:)
				(ego
					view: 612
					setLoop: 0 1
					setCel: 0
					posn: 132 166
					setPri: 163
					ignoreActors: 1
					setCycle: 0
					setScaler: Scaler 100 100 189 0
					show:
				)
				(theGame handsOn:)
				(user canInput: 0)
				(self dispose:)
			)
		)
	)
)

(instance sKatrinaKilled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(!= (ego view?) 612)
						(or
							(!= (ego x?) (fCoffin x?))
							(!= (ego y?) (fCoffin y?))
						)
					)
					(ego setMotion: PolyPath (fCoffin x?) (fCoffin y?) self)
				else
					(self cue:)
				)
			)
			(1 (ego setCycle: End self))
			(2 (= seconds 2))
			(3
				(vMyInset hide: dispose:)
				(ego hide:)
				(messager say: 18 4 4 1 self)
			)
			(4
				(ego
					posn: 237 147
					normalize:
					show:
					setScaler: Scaler 105 52 154 35
				)
				(cast eachElementDo: #show)
				(curRoom drawPic: 680 0)
				(= seconds 2)
			)
			(5
				(messager say: 15 6 9 1 self)
			)
			(6
				(pAdavis init: setCycle: End self)
			)
			(7 (= ticks 60))
			(8
				(messager say: 17 6 10 1 self)
			)
			(9
				(pAdavis
					view: 674
					setLoop: 0 1
					setCel: 0
					posn: 89 156
					setCycle: End self
				)
			)
			(10
				(pAdavis setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(11
				(spellSound play:)
				(pAdavis setCycle: Beg)
				(aProjectile init: setCycle: End self)
			)
			(12
				(aProjectile hide: dispose:)
				(ego
					view: 672
					setLoop: 0 1
					posn: 214 145
					cycleSpeed: 12
					setCycle: End
				)
				(pAdavis
					setLoop: 0 1
					setCel: 15
					cycleSpeed: 10
					setCycle: CT 12 -1 self
				)
			)
			(13
				(pAdavis setCycle: CT 15 1 self)
			)
			(14
				(pAdavis
					setLoop: 0 1
					setCel: 15
					cycleSpeed: 10
					setCycle: CT 12 -1 self
				)
			)
			(15
				(pAdavis setCycle: CT 15 1 self)
			)
			(16
				(pAdavis
					setLoop: 0 1
					setCel: 15
					cycleSpeed: 10
					setCycle: CT 12 -1 self
				)
			)
			(17
				(ego hide:)
				(pAdavis setCycle: CT 15 1 self)
			)
			(18 (EgoDead 11 680))
		)
	)
)

(instance sKatrinaKissed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 234)
				(= seconds 2)
			)
			(1
				(messager say: 18 4 8 1 self)
			)
			(2
				(curRoom setScript: sKatrinaMad)
			)
		)
	)
)

(instance sKatrinaWakes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 18 4 6 1 self)
			)
			(1
				(self setScript: sKatrinaMad)
			)
		)
	)
)

(instance sKatrinaFeels of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 18 4 7 0 self)
			)
			(1
				(self setScript: sKatrinaMad)
			)
		)
	)
)

(instance sKatrinaMad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 235)
				(vMyInset x: 1000 hide:)
				(ego hide:)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(= seconds 2)
			)
			(1
				(ego
					posn: 237 147
					show:
					normalize:
					setPri: 142
					setScaler: Scaler 105 52 154 35
				)
				(cast eachElementDo: #show)
				(curRoom drawPic: 680)
				(= seconds 2)
			)
			(2
				(pCoffinLid setLoop: 5 1 setPri: 143)
				(pKatrina
					setPri: 144
					approachVerbs: 4
					show:
					setCycle: End self
				)
			)
			(3
				(messager say: 15 6 12 1 self)
			)
			(4
				(if (Btst 234)
					(messager say: 16 6 13 1 self)
				else
					(messager say: 16 6 14 1 self)
				)
			)
			(5 (curRoom newRoom: 670))
		)
	)
)

(instance aProjectile of Actor
	(properties
		x 90
		y 156
		priority 207
		fixPriority 1
		view 674
		loop 2
		signal $4001
	)
)

(instance pCoffinLid of Prop
	(properties
		noun 12
		approachX 218
		approachY 137
		x 245
		y 133
		priority 163
		fixPriority 1
		view 680
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 12 1 0 0)
		else
			(pKatrina doVerb: theVerb)
		)
	)
)

(instance pKatrina of Prop
	(properties
		noun 16
		approachX 218
		approachY 137
		x 236
		y 150
		priority 148
		fixPriority 1
		view 680
		loop 4
		signal $5001
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4) (curRoom setScript: sBringUpInset))
			((== theVerb 21) (messager say: 15 6 3))
			((== theVerb 37) (messager say: 15 6 3))
			((OneOf 91 79) (messager say: 15 6 3))
			(else (super doVerb: theVerb))
		)
	)
)

(instance pTorch1 of Prop
	(properties
		noun 2
		x 11
		y 13
		view 680
		loop 1
		cel 3
		detailLevel 2
	)
)

(instance pTorch2 of Prop
	(properties
		noun 2
		x 82
		y 11
		view 680
		loop 1
		cel 4
		detailLevel 2
	)
)

(instance pTorch3 of Prop
	(properties
		noun 2
		x 135
		y 8
		view 680
		loop 1
		cel 4
		detailLevel 2
	)
)

(instance pAdavis of Prop
	(properties
		noun 17
		x 88
		y 156
		view 680
		loop 3
	)
)

(instance pBloodyMouth of Prop
	(properties
		x 131
		y 166
		priority 174
		fixPriority 1
		view 613
		loop 1
		cel 1
		signal $4001
	)
)

(instance vMyInset of View
	(properties
		noun 16
		x 58
		y 38
		view 601
	)
)

(instance fRoom of Feature
	(properties
		noun 1
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 5
		y 10
	)
	
	(method (doVerb theVerb)
		(if (not local1)
			(switch theVerb
				(86 (messager say: 15 6 3))
				(93 (messager say: 15 6 3))
				(88 (messager say: 15 6 3))
				(37 (messager say: 15 6 3))
				(91 (messager say: 15 6 3))
				(84 (messager say: 15 6 3))
				(21
					(ego use: 6)
					(curRoom setScript: (ScriptID 32) 0 21)
				)
				(1 (messager say: 1 1 0 0))
				(171
					(curRoom setScript: sKatrinaKilled)
				)
				(170
					(curRoom setScript: sKatrinaKilled)
				)
				(else  (super doVerb: theVerb))
			)
		else
			(switch theVerb
				(171
					(curRoom setScript: sKatrinaKilled)
				)
				(170
					(curRoom setScript: sKatrinaKilled)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance fDoor of Feature
	(properties
		noun 1
		nsLeft 158
		nsTop 1
		nsRight 193
		nsBottom 43
		sightAngle 180
		approachX 175
		approachY 38
		x 193
		y 20
		z 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (curRoom setScript: sTo670))
			(-80
				(curRoom setScript: sTo670)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fSconce1 of Feature
	(properties
		noun 2
		nsLeft 2
		nsTop -1
		nsRight 29
		nsBottom 32
		sightAngle 180
		x 15
		y 15
	)
)

(instance fSconce2 of Feature
	(properties
		noun 2
		nsLeft 77
		nsRight 97
		nsBottom 30
		sightAngle 180
		x 87
		y 15
	)
)

(instance fSconce3 of Feature
	(properties
		noun 2
		nsLeft 133
		nsRight 149
		nsBottom 21
		sightAngle 180
		x 141
		y 10
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 173 31 210 31 210 40 173 40
						yourself:
					)
					3
					6
					0
					sTo670
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fShield of Feature
	(properties
		noun 3
		nsLeft 235
		nsTop -1
		nsRight 264
		nsBottom 28
		sightAngle 180
		x 249
		y 84
		z 71
	)
)

(instance fMirror of Feature
	(properties
		noun 4
		nsLeft 151
		nsTop 90
		nsRight 173
		nsBottom 112
		sightAngle 180
		x 162
		y 101
	)
)

(instance fChair of Feature
	(properties
		noun 5
		nsLeft 169
		nsTop 103
		nsRight 186
		nsBottom 133
		sightAngle 180
		x 177
		y 118
	)
)

(instance fDesk of Feature
	(properties
		noun 6
		nsLeft 142
		nsTop 113
		nsRight 173
		nsBottom 122
		sightAngle 180
		x 157
		y 117
	)
)

(instance fStatue1 of Feature
	(properties
		noun 7
		nsTop 136
		nsRight 30
		nsBottom 183
		sightAngle 180
		x 15
		y 159
	)
)

(instance fStatue2 of Feature
	(properties
		noun 7
		nsLeft 116
		nsTop 107
		nsRight 142
		nsBottom 144
		sightAngle 180
		x 129
		y 125
	)
)

(instance fPlant1 of Feature
	(properties
		noun 8
		nsTop 33
		nsRight 30
		nsBottom 136
		sightAngle 180
		x 15
		y 84
	)
)

(instance fPlant2 of Feature
	(properties
		noun 8
		nsLeft 94
		nsTop 61
		nsRight 118
		nsBottom 144
		sightAngle 180
		x 106
		y 102
	)
)

(instance fCurtain1 of Feature
	(properties
		noun 9
		nsLeft 208
		nsTop 52
		nsRight 226
		nsBottom 123
		sightAngle 180
		x 217
		y 87
	)
)

(instance fUrn1 of Feature
	(properties
		noun 10
		nsLeft 253
		nsTop 144
		nsRight 269
		nsBottom 169
		sightAngle 180
		x 261
		y 156
	)
)

(instance fUrn2 of Feature
	(properties
		noun 11
		nsLeft 274
		nsTop 142
		nsRight 292
		nsBottom 168
		sightAngle 180
		x 283
		y 155
	)
)

(instance fCoffin of Feature
	(properties
		noun 12
		nsLeft 210
		nsTop 123
		nsRight 291
		nsBottom 143
		sightAngle 180
		approachX 218
		approachY 137
		x 250
		y 133
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 12 1 0 0)
		else
			(pKatrina doVerb: theVerb)
		)
	)
)

(instance fTassles of Feature
	(properties
		noun 13
		nsLeft 294
		nsRight 319
		nsBottom 91
		sightAngle 180
		x 306
		y 45
	)
)

(instance fCurtain2 of Feature
	(properties
		noun 9
		nsLeft 294
		nsTop 91
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 306
		y 140
	)
)

(instance fBedCurtain of Feature
	(properties
		noun 14
		nsLeft 222
		nsRight 299
		nsBottom 143
		sightAngle 180
		x 260
		y 71
	)
)

(instance katrinaTeller of Teller
	(properties
		title 1
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(6
				(self clean:)
				(curRoom setScript: sKatrinaWakes)
			)
			(7
				(self clean:)
				(curRoom setScript: sKatrinaFeels)
			)
			(4
				(self clean:)
				(curRoom setScript: sKatrinaKilled)
			)
			(8
				(self clean:)
				(curRoom setScript: sKatrinaKissed)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases: 4 (if (ego has: 50) (ego has: 49) else 0)
		)
	)
)

(instance fireSound of Sound
	(properties
		number 965
		loop -1
	)
)

(instance openSound of Sound
	(properties
		number 972
	)
)

(instance spellSound of Sound
	(properties
		number 934
	)
)
