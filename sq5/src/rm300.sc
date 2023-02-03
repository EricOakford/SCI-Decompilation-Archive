;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm300 0
)

(instance rm300 of Rm
	(properties
		noun 12
		picture 60
		vanishingX 80
		vanishingY -10
	)
	
	(method (init)
		(self setRegions: 350)
		(LoadMany 128 6 407 406 402 401 405 190 426)
		(wd40 view: 406 loop: 0 cel: 4 x: 283 y: 63 init: hide:)
		(NormalEgo 0)
		(ego actions: (ScriptID 350 1))
		(switch prevRoomNum
			(305
				(ego edgeHit: 0)
				(if (Btst 11)
					(treeBranch
						x: 43
						y: 28
						setCycle: 0
						setLoop: -1
						setLoop: 6
						moveSpeed: 0
						setStep: 3 13
						init:
						setScript: sStickFalling
					)
				)
				(curRoom setScript: sRogSwimToShore)
				(curRoom style: 13)
			)
			(310
				(curRoom setScript: sEnterFromTunnelA)
				(if
				(and (Btst 14) (not (Btst 15)) (not (Btst 16)))
					(flash1 init: setCycle: Osc)
					(flash2 init: setCycle: Osc)
					(flash3 init: setCycle: Osc)
				)
			)
			(else 
				(if (or (not (Btst 20)) (Btst 212))
					(theMusic1 number: 15 loop: -1 play: 0 fade: 127 10 5 0)
					(curRoom setScript: sBeamIn)
				else
					(ego
						posn: 243 150
						loop: 2
						setScale: Scaler 122 59 169 126
						init:
					)
					(theGame handsOn:)
				)
			)
		)
		(walkHandler addToFront: pond)
		(super init:)
		(theMusic3
			number: 403
			owner: self
			flags: 1
			init:
			setLoop: -1
			play: 90
		)
		(reflection init:)
		(bubbles init:)
		(lowerFalls init:)
		(if (and (Btst 16) (not (Btst 17)))
			(wd40Parts init:)
			(sEnterFromTunnelA next: sCliffyHere)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							140
							124
							147
							119
							157
							119
							200
							133
							213
							129
							182
							124
							189
							106
							186
							107
							160
							103
							154
							103
							145
							103
							131
							100
							124
							102
							120
							102
							79
							109
							73
							110
							40
							110
							34
							103
							39
							79
							30
							79
							0
							124
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							152
							124
							213
							141
							218
							146
							198
							150
							204
							153
							178
							167
							249
							171
							284
							144
							250
							134
							232
							133
							215
							133
							182
							124
							189
							106
							186
							107
							160
							103
							154
							103
							145
							103
							131
							100
							124
							102
							120
							102
							79
							109
							73
							110
							40
							110
							34
							103
							39
							79
							30
							79
							0
							124
						yourself:
					)
			)
		)
		(if (Btst 12) (treeBranch init:))
		(tunnelA init: setOnMeCheck: 1 2)
		(bigTree init: setOnMeCheck: 1 64)
		(ferns init: setOnMeCheck: 1 128)
		(smallTree init: setOnMeCheck: 1 256)
		(pond init: setOnMeCheck: 1 512)
		(wfall init: setOnMeCheck: 1 1024)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(Palette palANIMATE 240 248 5 249 255 8)
		(cond 
			(
				(and
					(== (= temp0 (ego onControl: 1)) 2)
					(not (curRoom script?))
				)
				(curRoom setScript: sATunnel)
			)
			(
				(and
					(== temp0 16)
					(not (Btst 16))
					(not (wd40 script?))
				)
				(wd40 setScript: sWD40Land)
			)
		)
	)
	
	(method (dispose)
		(theMusic3 dispose:)
		(walkHandler delete: pond)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: noun theVerb 0 (Random 1 3))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sBeamIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(SolvePuzzle 205 10)
				(= seconds 2)
			)
			(1
				(theMusic2 number: 260 setLoop: 1 play:)
				(ego
					view: 6
					posn: 240 150
					scaleSignal: 1
					scaleX: 120
					scaleY: 120
					init:
					setCycle: End self
				)
			)
			(2
				(NormalEgo 0)
				(ego
					posn: 243 150
					loop: 2
					setScale: Scaler 122 59 169 126
				)
				(= seconds 3)
			)
			(3
				(if (Btst 9) (= state (+ state 2)))
				(= cycles 1)
			)
			(4
				(messager say: 10 0 0 0 self)
			)
			(5 (curRoom newRoom: 320))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRogSwimToShore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 407
					loop: 0
					cel: 0
					x: 99
					y: 148
					setScale: 0
					init:
					setCycle: Osc 3 self
					cycleSpeed: 6
					moveSpeed: 6
				)
			)
			(1
				(ego loop: 4 cel: 0 x: 99 y: 148)
				(= ticks 5)
			)
			(2
				(ego
					cel: 0
					x: 103
					y: 150
					setCycle: Fwd
					setLoop: -1
					setLoop: 1
					setMotion: MoveTo 156 158 self
				)
			)
			(3
				(ego loop: 2 cel: 0 x: 199 y: 140 setCycle: End self)
			)
			(4
				(ego loop: 3 cel: 0 x: 212 y: 157 setCycle: CT 2 1 self)
			)
			(5
				(theMusic2 number: 519 setLoop: 1 play:)
				(ego setCycle: CT 6 1 self)
			)
			(6 (ego setCycle: End self))
			(7
				(ego cel: 0 setCycle: CT 2 1 self)
			)
			(8
				(theMusic2 number: 519 setLoop: 1 play:)
				(ego setCycle: CT 6 1 self)
			)
			(9 (ego setCycle: End self))
			(10
				(NormalEgo 0)
				(ego loop: 2 setScale: Scaler 122 59 169 126)
				(if (not (treeBranch script?)) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance sStickFalling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(treeBranch setMotion: MoveTo 40 150 self)
			)
			(1
				(theMusic2 number: 411 setLoop: 1 play:)
				(Bclr 11)
				(Bset 12)
				(treeBranch
					setLoop: 5
					x: 42
					y: 139
					setStep: 2 10
					moveSpeed: 7
					setMotion: MoveTo 174 142 self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sATunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 40 104 self)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 310)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromTunnelA of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 40 104
					setScale: Scaler 122 59 169 126
					init:
					setMotion: MoveTo 24 122 self
				)
			)
			(1 (ego setHeading: 180 self))
			(2
				(cond 
					(
					(and (Btst 14) (not (Btst 15)) (not (Btst 16))) (= next sWD40InPool) (self dispose:))
					((and (Btst 16) (not (Btst 17))) (= next sCliffyHere) (self dispose:))
					(else (theGame handsOn:) (self dispose:))
				)
			)
		)
	)
)

(instance sWD40Land of Script
	(properties)
	
	(method (doit)
		(if (SteppedOn ego 8192)
			(if (== state 0) (self dispose:))
			(if (fireBall mover?)
				(= state 20)
			else
				(= seconds 0)
				(= ticks 1)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 7))
			(1
				(if (not (InRect 0 106 184 128 ego))
					(self dispose:)
				else
					(theGame handsOff:)
					(if (== (curRoom script?) sATunnel)
						(sATunnel dispose:)
					)
					(windyBranch init: setCycle: End self)
					(theMusic2
						number: 401
						setLoop: 1
						play: 0
						fade: 127 10 10 0
					)
				)
			)
			(2
				(windyBranch
					loop: 5
					cel: 0
					x: 166
					y: 8
					setCycle: End self
				)
			)
			(3
				(windyBranch
					loop: 6
					cel: 0
					x: 215
					y: 45
					setCycle: End self
				)
			)
			(4
				(windyBranch
					loop: 7
					cel: 0
					x: 189
					y: 36
					setCycle: End self
				)
			)
			(5
				(windyBranch
					loop: 8
					cel: 0
					x: 255
					y: 62
					setCycle: End self
				)
			)
			(6
				(windyBranch view: 406 loop: 3 cel: 0 x: 253 y: 165)
				(theMusic1 number: 35 loop: -1 play:)
				(if (Btst 15)
					(wd40 view: 406 loop: 0 cel: 4 x: 283 y: 63 show:)
					(= ticks 10)
				else
					(wd40
						view: 406
						loop: 0
						cel: 0
						x: 250
						y: 110
						show:
						setCycle: CT 2 1 self
					)
					(= state (+ state 2))
				)
			)
			(7
				(wd40 x: 276 y: 72)
				(= ticks 10)
			)
			(8
				(wd40 x: 267 y: 85)
				(= ticks 10)
			)
			(9
				(windyBranch setCel: 1)
				(if (Btst 15)
					(wd40 x: 258 y: 97)
				else
					(wd40 setCel: 3)
				)
				(= ticks 10)
			)
			(10
				(windyBranch setCel: 2)
				(if (Btst 15)
					(wd40 x: 250 y: 110)
				else
					(wd40 setCel: 4)
				)
				(= ticks 10)
			)
			(11
				(windyBranch dispose:)
				(wd40 loop: 2 cel: 0 x: 218 y: 152 setCycle: End self)
			)
			(12
				(theMusic2 stop:)
				(theMusic3 play: 90)
				(wd40 loop: 1 cel: 0 setCycle: End self)
			)
			(13
				(if (not (Btst 66))
					(messager say: 1 0 0 0 self)
				else
					(= cycles 1)
				)
			)
			(14
				(theGame handsOn:)
				(Bset 66)
				(theMusic2 number: 402 setLoop: 1 play:)
				(fireBall
					view: 402
					loop: 0
					cel: 0
					x: 207
					y: 114
					setStep: 15 5
					moveSpeed: 0
					init:
					setMotion: MoveTo (+ (ego x?) 7) (- (ego y?) 10) self
				)
			)
			(15
				(theMusic2 number: 4021 setLoop: 1 play:)
				(fireBall view: 401 loop: 4 cel: 0 setCycle: End self)
			)
			(16
				(fireBall hide:)
				(= seconds 6)
			)
			(17
				(theMusic2 number: 402 setLoop: 1 play:)
				(fireBall
					view: 402
					loop: 0
					cel: 0
					x: 207
					y: 114
					show:
					setMotion: MoveTo (+ (ego x?) 7) (- (ego y?) 10) self
				)
			)
			(18
				(theMusic2 number: 4021 setLoop: 1 play:)
				(fireBall view: 401 loop: 4 cel: 0 setCycle: End self)
			)
			(19
				(fireBall hide:)
				(= seconds 4)
			)
			(20
				(theMusic2 number: 402 setLoop: 1 play:)
				(theGame handsOff:)
				(fireBall
					view: 402
					loop: 0
					cel: 0
					x: 207
					y: 114
					show:
					setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 18) self
				)
			)
			(21
				(fireBall dispose:)
				(cond 
					(
					(and (<= 45 (ego heading?)) (<= (ego heading?) 135)) (ego loop: 3))
					(
					(and (<= 136 (ego heading?)) (<= (ego heading?) 225)) (ego loop: 0))
					(
					(and (<= 226 (ego heading?)) (<= (ego heading?) 315)) (ego loop: 2))
					(else (ego loop: 1))
				)
				(ego view: 401 cel: 0 setCycle: End self)
			)
			(22
				(EgoDead 17)
				(self dispose:)
			)
		)
	)
)

(instance sWD40InPool of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 15)
				(theMusic2 number: 145 setLoop: -1 play:)
				(= seconds 5)
			)
			(1
				(flash1 dispose:)
				(flash2 dispose:)
				(flash3 dispose:)
				(theMusic2 fade:)
				(= seconds 2)
			)
			(2 (splash init:) (= cycles 1))
			(3
				(splash cel: 1)
				(wd40
					view: 408
					loop: 6
					cel: 0
					x: 116
					y: 128
					setStep: 2 10
					moveSpeed: 0
					show:
				)
				(theMusic1 number: 35 loop: -1 play:)
				(theMusic2 number: 401 setLoop: 1 play:)
				(= ticks 5)
			)
			(4
				(splash cel: 2)
				(wd40 cel: 1 x: 120 y: 109)
				(= ticks 5)
			)
			(5
				(splash cel: 3)
				(wd40
					setLoop: -1
					setLoop: 6
					setCel: 2
					x: 128
					y: 86
					setMotion: MoveTo 159 -55 self
				)
				(= cycles 1)
			)
			(6 (splash dispose:))
			(7
				(theMusic2 stop:)
				(theMusic3 play: 90)
				(messager say: 17 0 0 0 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCliffyHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 260 setLoop: 1 play:)
				(cliffy init: setCycle: End self)
			)
			(1
				(cliffy view: 20 loop: 2 setHeading: 0 self)
			)
			(2
				(cliffy view: 190 loop: 6 cel: 0 setCycle: Osc 2 self)
			)
			(3
				(ego setMotion: PolyPath 172 128 self)
				(cliffy setScript: sCliffy)
			)
			(4 (ego setHeading: 180 self))
			(5 (messager say: 8 0 0 0 self))
			(6
				(messager say: 14 0 (if (ego has: 20) 3 else 4) 0 self)
			)
			(7
				(Bset 17)
				(if (ego has: 20)
					(theMusic2 number: 260 setLoop: 1 play:)
					(ego
						view: 6
						loop: 0
						cel: (ego lastCel:)
						setCycle: Beg self
					)
					(= global130 6)
				else
					(= global130 7)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(8
				(Bset 9)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance sCliffy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 5)))
			(1
				(cliffy view: 26 loop: (Random 0 2) cel: 0)
				(if (== (cliffy loop?) 1)
					(cliffy setCycle: End self)
				else
					(= state -1)
					(cliffy setCycle: Osc 1 self)
				)
			)
			(2 (= seconds 3))
			(3
				(= state -1)
				(cliffy setCycle: Beg self)
			)
			(4 (self dispose:))
		)
	)
)

(instance sGetBranch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 203 138 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego view: 19 loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(ego get: 17)
				(SolvePuzzle 206 10)
				(Bclr 12)
				(treeBranch dispose:)
				(ego setCycle: Beg self)
			)
			(4
				(NormalEgo 0)
				(ego loop: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wd40 of Actor
	(properties
		x 10
		y 10
		noun 4
		modNum 301
		view 400
		priority 14
		signal $6010
	)
)

(instance treeBranch of Actor
	(properties
		x 174
		y 142
		noun 13
		yStep 6
		view 407
		loop 5
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetBranch)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fireBall of Actor
	(properties
		x 273
		y 59
		yStep 5
		view 402
		xStep 15
		moveSpeed 0
	)
)

(instance bubbles of Prop
	(properties
		x 43
		y 137
		view 405
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance reflection of Prop
	(properties
		x 116
		y 133
		view 405
		priority 13
		signal $4010
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance lowerFalls of Prop
	(properties
		x 104
		y 184
		view 405
		loop 2
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance windyBranch of Prop
	(properties
		x 118
		y 27
		view 406
		loop 4
		signal $4000
	)
)

(instance splash of Prop
	(properties
		x 110
		y 144
		view 408
		priority 15
		signal $4010
	)
)

(instance flash1 of Prop
	(properties
		x 93
		y 151
		view 408
		loop 1
		cel 3
		cycleSpeed 15
	)
)

(instance flash2 of Prop
	(properties
		x 108
		y 165
		view 408
		loop 2
		cel 1
		cycleSpeed 10
	)
)

(instance flash3 of Prop
	(properties
		x 173
		y 174
		view 408
		loop 4
		cel 3
		cycleSpeed 5
	)
)

(instance cliffy of Actor
	(properties
		x 217
		y 139
		noun 6
		view 33
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 96 49 154 117)
	)
)

(instance wd40Parts of View
	(properties
		x 181
		y 136
		noun 16
		view 426
		loop 2
		cel 9
	)
	
	(method (init)
		(super init:)
		(wd40Part1 init:)
		(wd40Part2 init:)
		(wd40Part3 init:)
		(wd40Part4 init:)
		(wd40Part5 init:)
		(wd40Part6 init:)
		(wd40Part7 init:)
		(wd40Part8 init:)
		(wd40Part9 init:)
	)
)

(instance wd40Part1 of View
	(properties
		x 156
		y 127
		noun 16
		view 426
		loop 2
	)
)

(instance wd40Part2 of View
	(properties
		x 197
		y 124
		noun 16
		view 426
		loop 2
		cel 1
	)
)

(instance wd40Part3 of View
	(properties
		x 208
		y 127
		noun 16
		view 426
		loop 2
		cel 2
	)
)

(instance wd40Part4 of View
	(properties
		x 255
		y 139
		noun 16
		view 426
		loop 2
		cel 3
	)
)

(instance wd40Part5 of View
	(properties
		x 221
		y 158
		noun 16
		view 426
		loop 2
		cel 4
	)
)

(instance wd40Part6 of View
	(properties
		x 199
		y 141
		noun 16
		view 426
		loop 2
		cel 5
	)
)

(instance wd40Part7 of View
	(properties
		x 247
		y 152
		noun 16
		view 426
		loop 2
		cel 6
	)
)

(instance wd40Part8 of View
	(properties
		x 273
		y 149
		noun 16
		view 426
		loop 2
		cel 7
	)
)

(instance wd40Part9 of View
	(properties
		x 225
		y 145
		noun 16
		view 426
		loop 2
		cel 8
	)
)

(instance tunnelA of Feature
	(properties
		x 25
		y 103
		noun 5
		onMeCheck $0002
	)
)

(instance bigTree of Feature
	(properties
		x 241
		y 105
		noun 2
		onMeCheck $0040
	)
)

(instance ferns of Feature
	(properties
		x 204
		y 166
		noun 7
		onMeCheck $0080
	)
)

(instance smallTree of Feature
	(properties
		x 153
		y 138
		noun 9
		onMeCheck $0100
	)
)

(instance pond of Feature
	(properties
		x 119
		y 133
		noun 11
		onMeCheck $0200
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 16)
					(messager say: noun theVerb 2 0)
				else
					(messager say: noun theVerb 1 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wfall of Feature
	(properties
		x 57
		y 114
		noun 15
		onMeCheck $0400
	)
)

(instance theMusic3 of Sound
	(properties
		flags $0001
	)
)
