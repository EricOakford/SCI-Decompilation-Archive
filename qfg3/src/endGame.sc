;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include sci.sh)
(use Main)
(use Target)
(use EgoDead)
(use OccasionalCycle)
(use Talker)
(use Scaler)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	endGame 0
	deWizTalker 1
	deWiz 2
	gargoyle 3
	orb 4
	portal 5
	deMaster 6
	knockGarg 7
	castSpells 8
	zap 9
	deWizTimer 10
	walkOnGarg 11
	blastWiz 12
	touchPortal 13
	touchOrb 14
	blastOrb 15
	egoHit 16
	sFx 17
	pedestal 18
	noticeEgo 19
)

(local
	local0
	local1
	local2
)
(instance endGame of Rgn
	(properties
		modNum 850
		noun 18
	)
	
	(method (init)
		(egoActions init: ego)
		(cSound number: 851 setLoop: -1 play: 127)
		(ego
			x: -10
			y: 183
			actions: egoActions
			init:
			setScale: Scaler 92 63 189 130
			normalize:
			ignoreHorizon: 1
		)
		(HandsOn)
		(if (not (== heroType 1)) (theIconBar disable: 6 5 4))
		(super init:)
		(switch heroType
			(2
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								270
								67
								266
								76
								243
								76
								238
								66
								227
								66
								206
								90
								180
								90
								172
								74
								147
								76
								147
								87
								139
								92
								118
								92
								112
								85
								112
								80
								81
								85
								104
								101
								166
								94
								181
								105
								128
								114
								142
								121
								153
								120
								162
								127
								161
								139
								148
								145
								162
								165
								205
								167
								227
								189
								301
								189
								264
								167
								319
								152
								319
								56
								292
								59
							yourself:
						)
						((Polygon new:)
							type: 3
							init:
								0
								100
								0
								189
								112
								189
								104
								178
								116
								176
								108
								166
								88
								156
								77
								158
								69
								148
								52
								148
								48
								144
								55
								134
								45
								124
								28
								121
								45
								115
								35
								105
							yourself:
						)
				)
			)
			(1
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								270
								67
								266
								76
								243
								76
								238
								66
								227
								66
								206
								90
								180
								90
								172
								74
								147
								76
								147
								87
								139
								92
								118
								92
								112
								85
								112
								80
								81
								85
								104
								101
								166
								94
								181
								105
								128
								114
								142
								121
								153
								120
								162
								127
								161
								139
								148
								145
								162
								165
								205
								167
								227
								189
								301
								189
								264
								167
								319
								152
								319
								56
								292
								59
							yourself:
						)
						((Polygon new:)
							type: 3
							init:
								0
								144
								0
								189
								112
								189
								104
								178
								116
								176
								110
								171
								84
								173
								72
								162
								69
								148
								52
								148
								48
								144
							yourself:
						)
				)
			)
			(else 
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								206
								90
								177
								90
								170
								76
								151
								76
								151
								92
								110
								92
								108
								81
								84
								85
								104
								100
								166
								94
								183
								104
								128
								114
								142
								121
								153
								120
								162
								127
								161
								139
								148
								145
								162
								165
								205
								167
								227
								189
								301
								189
								264
								167
								319
								152
								319
								56
								282
								56
								282
								135
								277
								142
								253
								140
								239
								107
							yourself:
						)
						((Polygon new:)
							type: 3
							init:
								0
								144
								0
								189
								112
								189
								104
								178
								116
								176
								108
								166
								88
								156
								77
								158
								69
								148
								52
								148
								48
								144
							yourself:
						)
				)
			)
		)
		(if (not (== heroType 2))
			(gargoyle x: 93 y: 174 loop: 0 cel: 0 init:)
		)
		(if (== heroType 1) (gargoyle view: 853 loop: 1 cel: 0))
		(portal init: stopUpd:)
		(deWiz init: setPri: 12 stopUpd:)
		(orb init: stopUpd:)
		(if (== heroType 2) (pedestal view: 853 x: 217 y: 124))
		(pedestal init: stopUpd:)
		(ledge init:)
	)
	
	(method (doit)
		(CyclePalette 72 86 -1)
		(super doit:)
	)
	
	(method (dispose)
		(ego changeGait: 0)
		(if gNewList (gNewList dispose:))
		(LoadMany 0 851 852 853 854 33)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(56
				(if (not (curRoom script?))
					(Bset 84)
					(self setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(20
				(if (not (curRoom script?))
					(self setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(33
				(if (not (curRoom script?))
					(self setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(81
				(if (not (self script?))
					(self setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(88
				(if (not (self script?))
					(self setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(83
				(if (not (self script?))
					(self setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(80
				(messager say: 2 6 66 0 0 850)
			)
			(86
				(messager say: 2 6 66 0 0 850)
			)
			(78
				(messager say: 2 6 66 0 0 850)
			)
			(82
				(messager say: 2 6 67 0 0 850)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if (== (ego script?) walkOnGarg)
			(theIconBar disable: 1 5 4)
		)
		(super cue:)
	)
)

(instance knockGarg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== ((ScriptID 851 2) state?) 0) (self cue:))
					((== ((ScriptID 852 2) state?) 0) (self cue:))
					(
						(or
							(not (== (egoHit state?) -1))
							(not (== ((ScriptID 851 2) state?) -1))
							(not (== ((ScriptID 852 2) state?) -1))
						)
						0
					)
					(else (self cue:))
				)
			)
			(1
				(HandsOff)
				(= local0 1)
				(castSpells dispose:)
				(if (not (cast contains: (ScriptID 852 5)))
					(deWiz stopUpd:)
				)
				(ego changeGait: 1 setMotion: MoveTo 52 180 self)
			)
			(2
				(ego view: 36 cel: 0 setLoop: 0 setCycle: CT 3 1 self)
			)
			(3
				(sFx number: 850 play:)
				(gargoyle view: 871 cel: 0 setCycle: End self)
				(ego setCycle: End)
			)
			(4
				(sFx number: 920 play:)
				(ShakeScreen 10)
				(walkHandler addToFront: gargoyle)
				(gargoyle stopUpd:)
				(ego view: 0 changeGait: 0 normalize:)
				(if
					(and
						(not (Btst 91))
						(cast contains: deWiz)
						(or
							(not (== ((ScriptID 852 3) state?) -1))
							(not (== ((ScriptID 851 3) state?) -1))
						)
					)
					(deWiz setScript: castSpells)
				)
				(HandsOn)
				(theIconBar disable: 6 4 5)
				(self dispose:)
			)
		)
	)
)

(instance castSpells of Script
	(properties)
	
	(method (dispose)
		(if (cast contains: zap) (zap dispose:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: 1 4 5)
				(if (not (== heroType 1)) (theIconBar disable: 6))
				(deWiz
					view: 863
					cel: 0
					loop: (Random 0 6)
					setCycle: End self
				)
			)
			(1
				(deWiz view: 863 loop: 0 setCycle: CT 6 1 self)
			)
			(2
				(sFx number: 13 play:)
				(zap
					setLoop: (Random 0 4)
					x: 186
					y: 60
					setStep: 8 7
					cycleSpeed: 0
					moveSpeed: 0
					setScale:
					init:
					setMotion: MoveTo (ego x?) (- (ego y?) 35) self
				)
				(deWiz setCycle: End)
			)
			(3
				(if (> (zap loop?) 3)
					(zap loop: 10 setCycle: End self)
				else
					(zap loop: 9 setCycle: End self)
				)
			)
			(4
				(zap dispose:)
				(theIconBar enable: 1)
				(if (or (Btst 91) (Btst 84)) (EgoDead 47 850))
				(= state -1)
				(switch arcadeDifficulty
					(1 (= seconds 10))
					(2 (= seconds 7))
					(3 (= seconds 5))
				)
			)
		)
	)
)

(instance egoHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(not
						(if
							(and
								(== (blastWiz state?) -1)
								(== ((ScriptID 852 3) state?) -1)
							)
							(== ((ScriptID 851 3) state?) -1)
						)
					)
					0
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(sFx number: 930 play:)
				(if (ego takeDamage: (Random 5 15))
					(ego
						view: 29
						loop: 0
						cel: 0
						setMotion: 0
						setCycle: CT 6 1 self
					)
				else
					(EgoDead 47 850)
				)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego view: 0 normalize:)
				(HandsOn)
				(if (not (== heroType 1)) (theIconBar disable: 6 5 4))
				(if (== (blastWiz state?) 0) (blastWiz cue:))
				(if (== ((ScriptID 851 3) state?) 0)
					((ScriptID 851 3) cue:)
				)
				(if (== (knockGarg state?) 0) (knockGarg cue:))
				(if (== ((ScriptID 852 3) state?) 1)
					((ScriptID 852 3) cue:)
				)
				(= state -1)
				(self dispose:)
			)
		)
	)
)

(instance blastWiz of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(not (== (egoHit state?) -1))
						(== (self client?) (ScriptID 851 2))
						(== (self client?) (ScriptID 852 2))
					)
					0
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(Bset 15)
				(self setScript: (ScriptID 32 0) self register)
			)
			(2
				(Bclr 15)
				(if
					(not
						(if (and (> (ego view?) 17) (< (ego view?) 21)))
					)
					(ego normalize:)
				else
					(HandsOff)
				)
				(if (== (egoHit state?) 0) (egoHit cue:))
				(if (== ((ScriptID 853 1) state?) 3)
					((ScriptID 853 1) seconds: 3)
				)
				(if
					(and
						(not
							(if (and (> (ego view?) 17) (< (ego view?) 21)))
						)
						(== heroType 1)
					)
					(theIconBar enable: 6 8)
				)
				(if (not (== heroType 1)) (theIconBar enable: 8))
				(User controls: 1 input: 1)
				(if (== (ego script?) walkOnGarg)
					(theIconBar disable: 1 5 4 6)
				)
				(= state -1)
				(self dispose:)
			)
		)
	)
)

(instance deWizTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cast contains: (ScriptID 852 5)))
					(deWiz setCycle: OccasionalCycle 1 10 1550)
				)
				(switch arcadeDifficulty
					(1 (= seconds 90))
					(2 (= seconds 60))
					(3 (= seconds 30))
				)
			)
			(1
				(if (== (self script?) (ScriptID 852 6))
					((ScriptID 852 6) dispose:)
				)
				(deMaster view: 856 loop: 1 setPri: 6 init:)
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(= seconds 1)
			)
			(2 (EgoDead 2 850))
		)
	)
)

(instance walkOnGarg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(castSpells dispose:)
				(walkHandler delete: gargoyle)
				(ego setMotion: MoveTo 128 169 self)
			)
			(1
				(if (or (Btst 91) (not (cast contains: deWiz)))
					(self cue:)
				else
					(messager say: 3 6 8 0 self 850)
				)
			)
			(2
				(if (or (Btst 91) (not (cast contains: deWiz)))
					(self cue:)
				else
					(gargArm init: setCycle: End self)
				)
			)
			(3
				(if (or (Btst 91) (not (cast contains: deWiz)))
					(self cue:)
				else
					(messager say: 2 6 9 0 self 850)
					(deWiz setScript: castSpells)
					(theIconBar advanceCurIcon:)
					(theGame setCursor: 941)
				)
			)
			(4
				(if (or (Btst 91) (not (cast contains: deWiz)))
					(self cue:)
				else
					(if (== heroType 3) (messager say: 3 6 14 0 0 850))
					(deWiz setScript: deWizTimer)
					(theIconBar enable: 3 9 8 2)
					(user canInput: 1)
				)
			)
			(5
				(if (or (Btst 91) (not (cast contains: deWiz)))
					(self cue:)
				else
					(HandsOff)
					(gargArm setCycle: Beg self)
				)
			)
			(6
				(gargArm dispose:)
				(ego setMotion: MoveTo 183 147 self)
			)
			(7
				(= local1 1)
				(HandsOn)
				(theIconBar disable: 6 5 4)
				(self dispose:)
			)
		)
	)
)

(instance touchPortal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 225 120 self)
			)
			(1
				(ego view: 31 loop: 0 setCycle: End self)
			)
			(2
				(ego dispose:)
				(DrawPic 850 dpOPEN_PIXELATION)
				(= seconds 2)
			)
			(3
				(EgoDead 11 850)
				(self dispose:)
			)
		)
	)
)

(instance touchOrb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 243 141 self)
			)
			(1
				(ego view: 31 loop: 0 cel: 0 setCycle: End self)
			)
			(2
				(ego dispose:)
				(DrawPic 850 dpOPEN_PIXELATION)
				(= seconds 2)
			)
			(3
				(EgoDead 12 850)
				(self dispose:)
			)
		)
	)
)

(instance knockOrb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(deWizTimer dispose:)
				(if (< (ego x?) 100)
					(ego changeGait: 1 setMotion: MoveTo (ego x?) 141 self)
				else
					(self cue:)
				)
			)
			(1
				(ego changeGait: 1 setMotion: MoveTo 243 141 self)
			)
			(2
				(ego view: 36 loop: 0 cel: 0 setCycle: CT 4 1 self)
			)
			(3
				(sFx number: 850 play:)
				(orb moveSpeed: 0 setStep: 7 6 setMotion: MoveTo 247 80)
				(ego setCycle: End self)
			)
			(4
				(sFx number: 101 play:)
				(orb dispose:)
				(ego solvePuzzle: 341 20)
				(DrawPic 850 dpOPEN_PIXELATION)
				(= seconds 2)
			)
			(5
				(if (cast contains: (ScriptID 852 5))
					((ScriptID 852 4) dispose:)
					((ScriptID 852 5) dispose:)
				)
				(if (cast contains: deWiz)
					(deWiz
						setCycle: Walk
						setStep: 6 5
						moveSpeed: 0
						setPri: 14
						setMotion: MoveTo 243 89 self
					)
				else
					(self cue:)
				)
			)
			(6
				(if (cast contains: deWiz)
					(deWiz hide:)
					(DrawPic 850 dpOPEN_PIXELATION)
					(ego solvePuzzle: 340 10)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(7
				(portal setCycle: End self)
				(sFx number: 831 play:)
			)
			(8
				(portal loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(9
				(portal dispose:)
				(ego normalize:)
				(switch heroType
					(0
						(messager say: 2 6 57 0 self 850)
					)
					(3
						(messager say: 2 6 58 0 self 850)
					)
					(else 
						(messager say: 2 6 3 0 self 850)
					)
				)
			)
			(10 (= cycles 3))
			(11
				(Bset 117)
				(curRoom newRoom: 830)
			)
		)
	)
)

(instance blastOrb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(deWizTimer dispose:)
				(self setScript: (ScriptID 32 0) self register)
			)
			(1)
			(2
				(sFx number: 520 play:)
				(if (cast contains: (ScriptID 852 4))
					((ScriptID 852 5) dispose:)
					((ScriptID 852 4) dispose:)
				)
				(orb
					moveSpeed: 0
					setStep: 7 6
					setMotion: MoveTo 247 80 self
				)
			)
			(3
				(orb dispose:)
				(ego solvePuzzle: 341 20)
				(DrawPic 850 dpOPEN_PIXELATION)
				(= seconds 2)
			)
			(4
				(if (cast contains: deWiz)
					(deWiz
						setCycle: Walk
						setStep: 6 5
						moveSpeed: 0
						setPri: 14
						setMotion: MoveTo 243 89 self
					)
				else
					(self cue:)
				)
			)
			(5
				(if (cast contains: deWiz)
					(deWiz hide:)
					(DrawPic 850 dpOPEN_PIXELATION)
					(ego solvePuzzle: 340 10)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(6
				(portal setCycle: End self)
				(sFx number: 831 play:)
			)
			(7
				(portal loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(8
				(portal dispose:)
				(messager say: 2 6 3 0 self 850)
			)
			(9
				(Bset 117)
				(curRoom newRoom: 830)
			)
		)
	)
)

(instance deSummons of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 6 14 0 self 850)
			)
			(1
				(deWiz view: 861 loop: 1 cel: 5 setCycle: Beg self)
			)
			(2
				(deWiz loop: 0 setCycle: OccasionalCycle self 1 1 20)
				(curRoom setScript: deWizTimer)
			)
		)
	)
)

(instance noticeEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 124)
				(ego code: 0)
				(messager say: 1 6 5 0 self 850)
			)
			(1
				(deMaster dispose:)
				(DrawPic 850 dpOPEN_PIXELATION)
				(= seconds 1)
			)
			(2
				(deWiz view: 861 loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(messager say: 3 6 6 0 self 850)
			)
			(4
				(deWiz view: 863 cel: 0 setCycle: End)
				(globalSound setLoop: 1 number: 13 play:)
				(zap
					setLoop: (Random 0 4)
					x: 186
					y: 60
					setStep: 8 7
					cycleSpeed: 0
					moveSpeed: 0
					setScale:
					init:
					setMotion: MoveTo (ego x?) (- (ego y?) 35) self
				)
			)
			(5
				(if (> (zap loop?) 3)
					(zap loop: 10 setCycle: End self)
				else
					(zap loop: 9 setCycle: End self)
				)
			)
			(6
				(zap dispose:)
				(if (== heroType 2) (EgoDead 47 850 857 End))
				(self dispose:)
			)
		)
	)
)

(instance zap of Actor
	(properties
		yStep 7
		view 21
		signal $4000
		xStep 8
	)
	
	(method (init)
		(super init:)
		(SetNowSeen self)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (ego onMe: zap) (not (Btst 82)) (not (Btst 89)))
			(if
				(and
					(not
						(if (and (> (ego view?) 17) (< (ego view?) 21)))
					)
					(not (castSpells script?))
				)
				(castSpells setScript: egoHit)
			)
		)
	)
)

(instance gargoyle of Prop
	(properties
		x 93
		y 174
		noun 6
		modNum 850
		view 852
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((and (ego has: 5) (not local0)) (self setScript: knockGarg))
					((and local0 (== (walkOnGarg state?) -1)) (ego setScript: walkOnGarg))
					(else (super doVerb: theVerb))
				)
			)
			(15
				(if (and (ego has: 5) (not local0))
					(self setScript: knockGarg)
				)
			)
			(3
				(if (and local0 (== (walkOnGarg state?) -1))
					(ego setScript: walkOnGarg)
				)
			)
			(26
				(if local0 (walkOnGarg cue:))
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance deWiz of TargActor
	(properties
		x 187
		y 115
		noun 3
		modNum 850
		view 861
		signal $4000
	)
	
	(method (dispose)
		(ego addHonor: 50)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 81 83 88 20 11 33)
				(cond 
					((== ((ScriptID 853 2) state?) 11) (EgoDead 18 850 857 End))
					((and (== theVerb 11) (not (== heroType 3))) 0)
					((and (not (curRoom script?)) (== theVerb 11))
						(curRoom setScript: (ScriptID 852 3))
						(if (== (deWiz script?) castSpells)
							(castSpells dispose:)
						)
					)
					((== (ego script?) (ScriptID 851 2)) ((ScriptID 851 2) setScript: blastWiz 0 theVerb))
					((== (ego script?) (ScriptID 852 2)) ((ScriptID 852 2) setScript: (ScriptID 852 3) 0 theVerb))
					((not (ego script?))
						(AutoTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(ego setScript: blastWiz 0 theVerb)
					)
					(
						(or
							(== (ego script?) egoHit)
							(== (ego script?) walkOnGarg)
						)
						(AutoTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(curRoom setScript: blastWiz 0 theVerb)
					)
				)
			)
			((== theVerb 56)
				(Bset 84)
				(cond 
					((== (ego script?) walkOnGarg)
						(walkOnGarg
							setScript: (ScriptID 851 3) walkOnGarg theVerb
						)
					)
					((not (ego script?)) (ego setScript: (ScriptID 851 3) 0 theVerb))
					(else (curRoom setScript: (ScriptID 851 3) 0 theVerb))
				)
			)
			((and (== heroType 3) (== theVerb 11))
				(cond 
					((== (ego script?) walkOnGarg)
						(walkOnGarg
							setScript: (ScriptID 852 3) walkOnGarg theVerb
						)
					)
					((not (ego script?)) (ego setScript: (ScriptID 852 3) walkOnGarg theVerb))
				)
			)
			(
				(and
					(== heroType 3)
					(== theVerb 4)
					local0
					(not (== ((ScriptID 852 3) state?) -1))
				)
				((ScriptID 852 3) cue:)
			)
			((== theVerb 26) (messager say: 3 26 10 0 0 850))
			((== theVerb 16)
				(if (not (> (ego x?) 290))
					(messager say: 2 6 37 0 0 850)
				else
					(ego setScript: (ScriptID 854 2))
				)
			)
			(
			(and (== theVerb 4) (cast contains: (ScriptID 852 5))) (EgoDead 2 850))
			(else (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(cond 
			((not (Btst 124)) (curRoom setScript: noticeEgo))
			((Btst 84)
				(if (not (== ((ScriptID 851 3) state?) -1))
					((ScriptID 851 3) cue:)
				else
					(ego drop: 45)
					((ScriptID 851 3) start: 2)
					(if (== (ego script?) walkOnGarg)
						(walkOnGarg setScript: (ScriptID 851 3))
					else
						(ego setScript: (ScriptID 851 3))
					)
				)
			)
			((Btst 91) ((ScriptID 852 3) cue:))
			(
				(==
					(if (and (> (ego view?) 17) (< (ego view?) 21)))
					1
				)
				((curRoom script?) setScript: (ScriptID 853 3))
			)
			((== heroType 1) (messager say: 3 6 43 0 0 850))
			(else (messager say: 3 6 29 0 0 850))
		)
	)
)

(instance portal of Prop
	(properties
		x 246
		y 106
		noun 19
		modNum 850
		view 856
		priority 1
		signal $4015
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and local0 (not (cast contains: deWiz)))
					(curRoom setScript: touchPortal)
				else
					(super doVerb: theVerb)
				)
			)
			(33 (curRoom doVerb: theVerb))
			(81 (curRoom doVerb: theVerb))
			(83 (curRoom doVerb: theVerb))
			(88 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance orb of TargActor
	(properties
		x 267
		y 115
		z 20
		noun 15
		modNum 850
		view 860
		loop 1
		signal $4000
	)
	
	(method (dispose)
		(ego addHonor: 100)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and local0 local1)
					(curRoom setScript: touchOrb)
				else
					(super doVerb: theVerb)
				)
			)
			(15
				(if (== (ego script?) (ScriptID 851 2))
					((ScriptID 851 2) dispose:)
				)
				(if
					(and
						(not local1)
						(== local0 1)
						(not (== (ego script?) walkOnGarg))
					)
					(ego setScript: walkOnGarg)
				)
				(if
					(and
						(== local0 1)
						(not (== (ego script?) walkOnGarg))
					)
					(curRoom setScript: knockOrb)
				else
					(super doVerb: theVerb)
				)
			)
			(83
				(if (== ((ScriptID 853 2) state?) 11)
					(EgoDead 18 850 857 End)
				else
					(curRoom setScript: blastOrb 0 theVerb)
				)
			)
			(88
				(if (== ((ScriptID 853 2) state?) 11)
					(EgoDead 18 850 857 End)
				else
					(curRoom setScript: blastOrb 0 theVerb)
				)
			)
			(56
				(curRoom setScript: (ScriptID 32 0) 0 theVerb)
			)
			(16
				(messager say: 2 6 49 0 0 850)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(cond 
			((cast contains: deWiz) (messager say: 2 6 42 0 0 850))
			((== (curRoom script?) blastOrb) (blastOrb cue:))
			(else
				(deWizTimer dispose:)
				(blastOrb start: 2)
				(curRoom setScript: blastOrb)
			)
		)
	)
)

(instance deMaster of View
	(properties
		x 210
		y 17
		modNum 850
		view 873
		loop 1
		priority 14
		signal $4015
	)
)

(instance pedestal of TargProp
	(properties
		x 266
		y 139
		noun 16
		modNum 850
		view 860
		signal $5000
	)
	
	(method (doVerb theVerb)
		(orb doVerb: theVerb)
	)
	
	(method (getHurt)
		(orb getHurt:)
	)
)

(instance deWizTalker of Talker
	(properties
		x 10
		y 10
		view 862
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 100
	)
	
	(method (init)
		(super init: deWizBust deWizEyes deWizMouth &rest)
	)
	
	(method (doit)
		(self cycle: brows cycle: bubbles)
		(super doit: &rest)
	)
	
	(method (dispose)
		(brows setCycle: 0 dispose:)
		(bubbles setCycle: 0 dispose:)
		(super dispose:)
	)
	
	(method (startText)
		(eyes setCycle: Fwd)
		(brows setCycle: Fwd)
		(bubbles setCycle: Fwd)
		(super startText:)
	)
)

(instance brows of Prop
	(properties
		nsTop 32
		nsLeft 28
		view 862
		loop 3
		cycleSpeed 96
	)
)

(instance bubbles of Prop
	(properties
		nsTop 12
		nsLeft 42
		view 862
		loop 4
		cycleSpeed 24
	)
)

(instance deWizMouth of Prop
	(properties
		nsTop 51
		nsLeft 32
		view 862
	)
)

(instance deWizEyes of Prop
	(properties
		nsTop 42
		nsLeft 37
		view 862
		loop 2
	)
)

(instance deWizBust of View
	(properties
		view 862
		loop 1
	)
)

(instance gargArm of Prop
	(properties
		x 122
		y 182
		view 858
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(26
				(messager say: 4 26 10 0 0 850)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ledge of Feature
	(properties
		x 98
		y 165
		noun 17
		nsTop 150
		nsLeft 74
		nsBottom 181
		nsRight 123
		sightAngle 180
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(not local0)
					(== theVerb 15)
					(cast contains: deWiz)
					(not (== (castSpells script?) egoHit))
				)
				(ego setScript: (ScriptID 851 2))
			)
			(
				(and
					(not local0)
					(== theVerb 11)
					(== heroType 3)
					(cast contains: deWiz)
					(not (== (castSpells script?) egoHit))
				)
				(ego setScript: (ScriptID 852 2))
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance sFx of Sound
	(properties)
)
