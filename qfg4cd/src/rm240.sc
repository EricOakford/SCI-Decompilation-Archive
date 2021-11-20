;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm240 0
)

(local
	local0
	theTimeZone
)
(instance rm240 of GloryRm
	(properties
		picture 330
		style $0400
		south 320
	)
	
	(method (init)
		(= local0
			(cond 
				((not (Btst 159)) 1)
				((ego has: 31) 4)
				((and (Btst 159) (not (Btst 160))) 2)
				((and (Btst 160) (not (Btst 130))) 3)
				((Btst 130) 5)
			)
		)
		(ego
			init:
			posn: 201 240
			normalize:
			setPri: 170
			setScaler: Scaler 150 85 163 112
		)
		(walkHandler addToFront: self)
		(if Night (nightWin init:))
		(if (not (Btst 428)) (chicken init: approachVerbs: 4))
		(foolsCap init:)
		(garlic1 init:)
		(garlic2 init:)
		(nightCap init:)
		(blanket init:)
		(rug init:)
		(chair init: approachVerbs: 4)
		(candle init:)
		(lamp init:)
		(theWindow init:)
		(bedTable init:)
		(gnome init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						145
						144
						79
						130
						54
						143
						130
						163
						130
						189
						0
						189
						0
						0
						319
						0
						319
						189
						239
						189
						235
						163
						274
						163
						274
						149
						265
						149
						248
						140
						248
						130
						209
						124
					yourself:
				)
		)
		(super init: &rest)
		(theMusic number: 240 setLoop: -1 play:)
		(self setScript: sEnterScr)
	)
	
	(method (doit)
		(if (and (not script) (!= timeODay theTimeZone))
			(= theTimeZone timeODay)
			(cond 
				((>= timeODay 4)
					(if (not (cast contains: nightWin))
						(nightWin init: approachVerbs: 4)
					)
				)
				((cast contains: nightWin) (nightWin dispose:))
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theMusic fade:)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== ((theIconBar getCursor:) view?) 940)
				(>= (event y?) 163)
			)
			(curRoom setScript: sExitScript)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 201 157 self)
			)
			(1
				(heroTeller
					init:
						ego
						240
						16
						128
						(switch local0
							(1 15)
							(2 19)
							(3 21)
							(4 22)
							(5 23)
						)
				)
				(switch local0
					(1
						(self setScript: sFirstVisit self)
					)
					(2
						(self setScript: sSecondVisit self)
					)
					(3
						(messager say: 13 6 (+ 49 (Random 0 4)) 0 self)
					)
					(4
						(self setScript: sGoodHumor self)
					)
					(5
						(messager say: 13 6 (+ 61 (Random 0 4)) 0 self)
					)
					(else  (= cycles 1))
				)
			)
			(2
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 204 162 self)
			)
			(1
				(switch local0
					(1
						(messager say: 13 6 5 0 self)
					)
					(2
						(messager say: 13 6 22 0 self)
					)
					(3
						(messager say: 13 6 (+ 53 (Random 0 4)) 0 self)
					)
					(4
						(Bset 161)
						(messager say: 13 6 60 0 self)
					)
					(else 
						(messager say: 13 6 (+ 66 (Random 0 4)) 0 self)
					)
				)
			)
			(2
				(ego setPri: 170 setMotion: MoveTo 204 240 self)
			)
			(3
				(theGame handsOn:)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance sFirstVisit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 159)
				(messager say: 13 6 1 0 self)
			)
			(1
				(gnome setCycle: End self)
				(ego setMotion: PolyPath 257 140 self)
			)
			(2 0)
			(3
				(Face ego (foolsCap x?) (foolsCap y?) self)
			)
			(4
				(ego
					view: 54
					loop: 4
					cel: 0
					posn: 261 140
					setCycle: End self
				)
			)
			(5 (= seconds 2))
			(6
				(messager say: 14 6 2 0 self)
			)
			(7 (= seconds 1))
			(8
				(gnome setLoop: 1 1 setCycle: End self)
				(ego cycleSpeed: 2 setCycle: Beg self)
			)
			(9 0)
			(10
				(gnome
					setLoop: 0 1
					setCel: (gnome lastCel:)
					setCycle: Beg self
				)
			)
			(11
				(messager say: 13 6 4 0 self)
			)
			(12
				(ego
					normalize:
					setSpeed: egoMoveSpeed
					setMotion: MoveTo 243 143 self
				)
			)
			(13
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSecondVisit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 160)
				(messager say: 13 6 19 0 self)
			)
			(1
				(ego setMotion: PolyPath 165 135 self)
			)
			(2
				(gnome
					view: 33
					loop: 4
					cel: 0
					posn: 134 104
					setCycle: End self
				)
			)
			(3
				(messager say: 14 6 20 0 self)
			)
			(4
				(ego view: 31 loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(5
				(gnome
					view: 241
					loop: 0
					cel: 0
					posn: 134 104
					setCycle: End self
				)
				(ego setCycle: CT 0 -1 self)
			)
			(6 0)
			(7
				(ego normalize:)
				(Face ego (foolsCap x?) (foolsCap y?) self)
			)
			(8
				(ego
					view: 33
					loop: 1
					cel: 0
					posn: 160 135
					setCycle: End self
				)
			)
			(9
				(gnome loop: 1 setCycle: End self)
				(ego loop: 3 posn: 163 135 setCycle: End self)
			)
			(10 0)
			(11
				(messager say: 13 6 21 0 self)
			)
			(12
				(gnome loop: 0 cel: 7 setCycle: Beg self)
			)
			(13
				(ego normalize: 5)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoodHumor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 130)
				(ego use: 31 solvePuzzle: 433 15 addHonor: 500)
				(messager say: 13 6 58 0 self)
			)
			(1
				(ego setMotion: PolyPath 165 135 self)
			)
			(2
				(ego view: 31 loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(3
				(messager say: 14 6 59 0 self)
			)
			(4 (ego setCycle: CT 0 -1 self))
			(5
				(ego normalize: 7)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sChokeChicken of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego solvePuzzle: 428 2 get: 36)
				(chicken hide:)
				(= cycles 2)
			)
			(1 (messager say: 8 4 0 0 self))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance gnomeTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 67 0))
	)
	
	(method (sayMessage)
		(if (== iconValue 12) (Bset 150))
		(super sayMessage: &rest)
	)
)

(instance heroTeller of Teller
	(properties)
)

(instance gnome of Prop
	(properties
		noun 13
		x 134
		y 104
		priority 53
		fixPriority 1
		view 241
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(gnomeTeller
			init:
				self
				240
				16
				149
				(switch local0
					(1 15)
					(2 19)
					(3 21)
					(4 22)
					(5 23)
				)
		)
	)
)

(instance nightWin of View
	(properties
		sightAngle 180
		x 220
		y 52
		fixPriority 1
		view 240
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(theWindow doVerb: theVerb &rest)
	)
)

(instance foolsCap of View
	(properties
		noun 3
		x 136
		y 130
		priority 196
		fixPriority 1
		view 240
		signal $4000
	)
)

(instance garlic1 of View
	(properties
		noun 7
		x 164
		y 19
		fixPriority 1
		view 240
		cel 1
		signal $4000
	)
)

(instance garlic2 of View
	(properties
		noun 1
		x 273
		y 19
		fixPriority 1
		view 240
		cel 4
		signal $4000
	)
)

(instance chicken of View
	(properties
		noun 8
		approachX 167
		approachY 144
		x 161
		y 106
		fixPriority 1
		view 240
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sChokeChicken)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance nightCap of View
	(properties
		noun 6
		x 72
		y 76
		fixPriority 1
		view 240
		cel 3
		signal $4000
	)
)

(instance blanket of View
	(properties
		noun 5
		x 195
		y 92
		fixPriority 1
		view 240
		cel 5
		signal $4000
	)
)

(instance rug of View
	(properties
		noun 2
		x 224
		y 127
		fixPriority 1
		view 240
		cel 6
		signal $4000
	)
)

(instance chair of View
	(properties
		noun 4
		approachX 256
		approachY 145
		x 285
		y 93
		fixPriority 1
		view 240
		cel 7
		signal $4000
	)
)

(instance candle of Feature
	(properties
		noun 9
		nsLeft 49
		nsTop 90
		nsRight 66
		nsBottom 108
		sightAngle 180
		x 57
		y 99
	)
)

(instance lamp of Feature
	(properties
		noun 10
		nsLeft 216
		nsTop 43
		nsRight 239
		nsBottom 62
		sightAngle 180
		x 227
		y 52
	)
)

(instance theWindow of Feature
	(properties
		noun 11
		nsLeft 215
		nsTop 49
		nsRight 283
		nsBottom 100
		sightAngle 180
		x 249
		y 74
	)
)

(instance bedTable of Feature
	(properties
		noun 12
		nsLeft 41
		nsTop 95
		nsRight 76
		nsBottom 122
		sightAngle 180
		x 58
		y 108
	)
)
