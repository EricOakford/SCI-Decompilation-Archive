;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include game.sh) (include "850.shm")
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
	knockedGargoyle
	passedGargoyle
	local2
)
(instance endGame of Region
	(properties
		modNum 850
		noun N_ROOM
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
			ignoreHorizon: TRUE
		)
		(HandsOn)
		(if (not (== heroType MAGIC_USER))
			(theIconBar disable: ICON_CAST ICON_ACTIONS ICON_TALK)
		)
		(super init:)
		(switch heroType
			(THIEF
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PContainedAccess
							init:
								270 67
								266 76
								243 76
								238 66
								227 66
								206 90
								180 90
								172 74
								147 76
								147 87
								139 92
								118 92
								112 85
								112 80
								81 85
								104 101
								166 94
								181 105
								128 114
								142 121
								153 120
								162 127
								161 139
								148 145
								162 165
								205 167
								227 189
								301 189
								264 167
								319 152
								319 56
								292 59
							yourself:
						)
						((Polygon new:)
							type: PContainedAccess
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
			(MAGIC_USER
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PContainedAccess
							init:
								270 67
								266 76
								243 76
								238 66
								227 66
								206 90
								180 90
								172 74
								147 76
								147 87
								139 92
								118 92
								112 85
								112 80
								81 85
								104 101
								166 94
								181 105
								128 114
								142 121
								153 120
								162 127
								161 139
								148 145
								162 165
								205 167
								227 189
								301 189
								264 167
								319 152
								319 56
								292 59
							yourself:
						)
						((Polygon new:)
							type: PContainedAccess
							init:
								0 144
								0 189
								112 189
								104 178
								116 176
								110 171
								84 173
								72 162
								69 148
								52 148
								48 144
							yourself:
						)
				)
			)
			(else 
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PContainedAccess
							init:
								206 90
								177 90
								170 76
								151 76
								151 92
								110 92
								108 81
								84 85
								104 100
								166 94
								183 104
								128 114
								142 121
								153 120
								162 127
								161 139
								148 145
								162 165
								205 167
								227 189
								301 189
								264 167
								319 152
								319 56
								282 56
								282 135
								277 142
								253 140
								239 107
							yourself:
						)
						((Polygon new:)
							type: PContainedAccess
							init:
								0 144
								0 189
								112 189
								104 178
								116 176
								108 166
								88 156
								77 158
								69 148
								52 148
								48 144
							yourself:
						)
				)
			)
		)
		(if (not (== heroType THIEF))
			(gargoyle x: 93 y: 174 loop: 0 cel: 0 init:)
		)
		(if (== heroType MAGIC_USER)
			(gargoyle view: 853 loop: 1 cel: 0)
		)
		(portal init: stopUpd:)
		(deWiz init: setPri: 12 stopUpd:)
		(orb init: stopUpd:)
		(if (== heroType THIEF)
			(pedestal view: 853 x: 217 y: 124)
		)
		(pedestal init: stopUpd:)
		(ledge init:)
	)
	
	(method (doit)
		(CyclePalette 72 86 -1)
		(super doit:)
	)
	
	(method (dispose)
		(ego changeGait: MOVE_WALK)
		(if gNewList
			(gNewList dispose:)
		)
		(LoadMany FALSE 851 852 853 854 OCC_CYCLE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_MAGIC_SPEAR
				(if (not (curRoom script?))
					(Bset fSpearedWiz)
					(self setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_DAGGER
				(if (not (curRoom script?))
					(self setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_ROCK
				(if (not (curRoom script?))
					(self setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_FLAME
				(if (not (self script?))
					(self setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (not (self script?))
					(self setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (not (self script?))
					(self setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_CALM
				(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
			)
			(V_JUGGLE
				(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
			)
			(V_DAZZLE
				(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
			)
			(V_FETCH
				(messager say: N_REGION V_DOIT C_CANT_FETCH 0 0 850)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(if (== (ego script?) walkOnGarg)
			(theIconBar disable: ICON_WALK ICON_ACTIONS ICON_TALK)
		)
		(super cue:)
	)
)

(instance knockGarg of Script
	
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
				(= knockedGargoyle TRUE)
				(castSpells dispose:)
				(if (not (cast contains: (ScriptID 852 5)))
					(deWiz stopUpd:)
				)
				(ego changeGait: 1 setMotion: MoveTo 52 180 self)
			)
			(2
				(ego view: 36 cel: 0 setLoop: 0 setCycle: CycleTo 3 1 self)
			)
			(3
				(sFx number: 850 play:)
				(gargoyle view: 871 cel: 0 setCycle: EndLoop self)
				(ego setCycle: EndLoop)
			)
			(4
				(sFx number: 920 play:)
				(ShakeScreen 10)
				(walkHandler addToFront: gargoyle)
				(gargoyle stopUpd:)
				(ego view: 0 changeGait: MOVE_WALK normalize:)
				(if
					(and
						(not (Btst fChuckedSwordAtWiz))
						(cast contains: deWiz)
						(or
							(not (== ((ScriptID 852 3) state?) -1))
							(not (== ((ScriptID 851 3) state?) -1))
						)
					)
					(deWiz setScript: castSpells)
				)
				(HandsOn)
				(theIconBar disable: ICON_CAST ICON_TALK ICON_ACTIONS)
				(self dispose:)
			)
		)
	)
)

(instance castSpells of Script

	(method (dispose)
		(if (cast contains: zap)
			(zap dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ACTIONS)
				(if (not (== heroType MAGIC_USER))
					(theIconBar disable: ICON_CAST)
				)
				(deWiz
					view: 863
					cel: 0
					loop: (Random 0 6)
					setCycle: EndLoop self
				)
			)
			(1
				(deWiz view: 863 loop: 0 setCycle: CycleTo 6 1 self)
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
				(deWiz setCycle: EndLoop)
			)
			(3
				(if (> (zap loop?) 3)
					(zap loop: 10 setCycle: EndLoop self)
				else
					(zap loop: 9 setCycle: EndLoop self)
				)
			)
			(4
				(zap dispose:)
				(theIconBar enable: ICON_WALK)
				(if (or (Btst fChuckedSwordAtWiz) (Btst fSpearedWiz))
					(EgoDead C_DEATH_SPELL 850)
				)
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
						setCycle: CycleTo 6 1 self
					)
				else
					(EgoDead C_DEATH_SPELL 850)
				)
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(ego view: 0 normalize:)
				(HandsOn)
				(if (not (== heroType MAGIC_USER))
					(theIconBar disable: ICON_CAST ICON_ACTIONS ICON_TALK)
				)
				(if (== (blastWiz state?) 0)
					(blastWiz cue:)
				)
				(if (== ((ScriptID 851 3) state?) 0)
					((ScriptID 851 3) cue:)
				)
				(if (== (knockGarg state?) 0)
					(knockGarg cue:)
				)
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
				(Bset fCastingSpell)
				(self setScript: (ScriptID PROJECTILE 0) self register)
			)
			(2
				(Bclr fCastingSpell)
				(if
					(not
						(if (and (> (ego view?) 17) (< (ego view?) 21)))
					)
					(ego normalize:)
				else
					(HandsOff)
				)
				(if (== (egoHit state?) 0)
					(egoHit cue:)
				)
				(if (== ((ScriptID 853 1) state?) 3)
					((ScriptID 853 1) seconds: 3)
				)
				(if
					(and
						(not
							(if (and (> (ego view?) 17) (< (ego view?) 21)))
						)
						(== heroType MAGIC_USER)
					)
					(theIconBar enable: ICON_CAST ICON_INVENTORY)
				)
				(if (not (== heroType MAGIC_USER))
					(theIconBar enable: ICON_INVENTORY)
				)
				(User controls: TRUE input: TRUE)
				(if (== (ego script?) walkOnGarg)
					(theIconBar disable: ICON_WALK ICON_ACTIONS ICON_TALK ICON_CAST)
				)
				(= state -1)
				(self dispose:)
			)
		)
	)
)

(instance deWizTimer of Script
	
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
				(DrawPic (curRoom picture?) PIXELDISSOLVE)
				(= seconds 1)
			)
			(2
				(EgoDead C_DEATH_THERMONUCLEAR 850)
			)
		)
	)
)

(instance walkOnGarg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(castSpells dispose:)
				(walkHandler delete: gargoyle)
				(ego setMotion: MoveTo 128 169 self)
			)
			(1
				(if (or (Btst fChuckedSwordAtWiz) (not (cast contains: deWiz)))
					(self cue:)
				else
					(messager say: N_DEWIZ V_DOIT C_GARGOYLE_SPELL 0 self 850)
				)
			)
			(2
				(if (or (Btst fChuckedSwordAtWiz) (not (cast contains: deWiz)))
					(self cue:)
				else
					(gargArm init: setCycle: EndLoop self)
				)
			)
			(3
				(if (or (Btst fChuckedSwordAtWiz) (not (cast contains: deWiz)))
					(self cue:)
				else
					(messager say: N_REGION V_DOIT C_GARGOYLE_GRABS 0 self 850)
					(deWiz setScript: castSpells)
					(theIconBar advanceCurIcon:)
					(theGame setCursor: 941)
				)
			)
			(4
				(if (or (Btst fChuckedSwordAtWiz) (not (cast contains: deWiz)))
					(self cue:)
				else
					(if (== heroType PALADIN)
						(messager say: N_DEWIZ V_DOIT C_START_SUMMON 0 0 850)
					)
					(deWiz setScript: deWizTimer)
					(theIconBar enable: ICON_DO ICON_CONTROL ICON_INVENTORY ICON_LOOK)
					(user canInput: TRUE)
				)
			)
			(5
				(if (or (Btst fChuckedSwordAtWiz) (not (cast contains: deWiz)))
					(self cue:)
				else
					(HandsOff)
					(gargArm setCycle: BegLoop self)
				)
			)
			(6
				(gargArm dispose:)
				(ego setMotion: MoveTo 183 147 self)
			)
			(7
				(= passedGargoyle TRUE)
				(HandsOn)
				(theIconBar disable: ICON_CAST ICON_ACTIONS ICON_TALK)
				(self dispose:)
			)
		)
	)
)

(instance touchPortal of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 225 120 self)
			)
			(1
				(ego view: 31 loop: 0 setCycle: EndLoop self)
			)
			(2
				(ego dispose:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 2)
			)
			(3
				(EgoDead C_DEATH_PORTAL 850)
				(self dispose:)
			)
		)
	)
)

(instance touchOrb of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 243 141 self)
			)
			(1
				(ego view: 31 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego dispose:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 2)
			)
			(3
				(EgoDead C_DEATH_ORB 850)
				(self dispose:)
			)
		)
	)
)

(instance knockOrb of Script
	
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
				(ego view: 36 loop: 0 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(3
				(sFx number: 850 play:)
				(orb moveSpeed: 0 setStep: 7 6 setMotion: MoveTo 247 80)
				(ego setCycle: EndLoop self)
			)
			(4
				(sFx number: 101 play:)
				(orb dispose:)
				(ego solvePuzzle: fKnockedOrb 20)
				(DrawPic 850 PIXELDISSOLVE)
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
					(DrawPic 850 PIXELDISSOLVE)
					(ego solvePuzzle: fBeatDeWiz 10)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(7
				(portal setCycle: EndLoop self)
				(sFx number: 831 play:)
			)
			(8
				(portal loop: 1 setCycle: Forward)
				(= seconds 3)
			)
			(9
				(portal dispose:)
				(ego normalize:)
				(switch heroType
					(FIGHTER
						(messager say: N_REGION V_DOIT C_EXIT_FIGHTER 0 self 850)
					)
					(PALADIN
						(messager say: N_REGION V_DOIT C_EXIT_PALADIN 0 self 850)
					)
					(else 
						(messager say: N_REGION V_DOIT C_EXIT 0 self 850)
					)
				)
			)
			(10
				(= cycles 3)
			)
			(11
				(Bset fWonGame)
				(curRoom newRoom: 830)
			)
		)
	)
)

(instance blastOrb of Script
	
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
				(ego solvePuzzle: fKnockedOrb 20)
				(DrawPic 850 PIXELDISSOLVE)
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
					(DrawPic 850 PIXELDISSOLVE)
					(ego solvePuzzle: fBeatDeWiz 10)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(6
				(portal setCycle: EndLoop self)
				(sFx number: 831 play:)
			)
			(7
				(portal loop: 1 setCycle: Forward)
				(= seconds 3)
			)
			(8
				(portal dispose:)
				(messager say: N_REGION V_DOIT C_EXIT 0 self 850)
			)
			(9
				(Bset fWonGame)
				(curRoom newRoom: 830)
			)
		)
	)
)

(instance deSummons of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_DEWIZ V_DOIT C_START_SUMMON 0 self 850)
			)
			(1
				(deWiz view: 861 loop: 1 cel: 5 setCycle: BegLoop self)
			)
			(2
				(deWiz loop: 0 setCycle: OccasionalCycle self 1 1 20)
				(curRoom setScript: deWizTimer)
			)
		)
	)
)

(instance noticeEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fWizNoticesEgo)
				(ego code: 0)
				(messager say: N_DELORD V_DOIT C_NOTICE_EGO 0 self 850)
			)
			(1
				(deMaster dispose:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 1)
			)
			(2
				(deWiz view: 861 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_DEWIZ V_DOIT C_NOTICE_EGO2 0 self 850)
			)
			(4
				(deWiz view: 863 cel: 0 setCycle: EndLoop)
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
					(zap loop: 10 setCycle: EndLoop self)
				else
					(zap loop: 9 setCycle: EndLoop self)
				)
			)
			(6
				(zap dispose:)
				(if (== heroType THIEF)
					(EgoDead C_DEATH_SPELL 850 857 EndLoop)
				)
				(self dispose:)
			)
		)
	)
)

(instance zap of Actor
	(properties
		yStep 7
		view 21
		signal ignrAct
		xStep 8
	)
	
	(method (init)
		(super init:)
		(SetNowSeen self)
	)
	
	(method (doit)
		(super doit:)
		(if (and (ego onMe: zap) (not (Btst fDeWizBattle)) (not (Btst fDeWizElectrocuted)))
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
		noun N_PILLAR_A
		modNum 850
		view 852
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((and (ego has: iShield) (not knockedGargoyle))
						(self setScript: knockGarg)
					)
					((and knockedGargoyle (== (walkOnGarg state?) -1))
						(ego setScript: walkOnGarg)
					)
					(else
						(super doVerb: theVerb)
					)
				)
			)
			(V_SHIELD
				(if (and (ego has: iShield) (not knockedGargoyle))
					(self setScript: knockGarg)
				)
			)
			(V_WALK
				(if (and knockedGargoyle (== (walkOnGarg state?) -1))
					(ego setScript: walkOnGarg)
				)
			)
			(V_DISPEL
				(if knockedGargoyle
					(walkOnGarg cue:)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance deWiz of TargActor
	(properties
		x 187
		y 115
		noun N_DEWIZ
		modNum 850
		view 861
		signal ignrAct
	)
	
	(method (dispose)
		(ego addHonor: 50)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb V_FLAME V_FORCEBOLT V_LIGHTNING V_DAGGER V_SWORD V_ROCK)
				(cond 
					((== ((ScriptID 853 2) state?) 11)
						(EgoDead C_DEATH_FIRE 850 857 EndLoop)
					)
					((and (== theVerb V_SWORD) (not (== heroType PALADIN)))
						0	;do nothing if not a paladin
					)
					((and (not (curRoom script?)) (== theVerb V_SWORD))
						(curRoom setScript: (ScriptID 852 3))
						(if (== (deWiz script?) castSpells)
							(castSpells dispose:)
						)
					)
					((== (ego script?) (ScriptID 851 2))
						((ScriptID 851 2) setScript: blastWiz 0 theVerb)
					)
					((== (ego script?) (ScriptID 852 2))
						((ScriptID 852 2) setScript: (ScriptID 852 3) 0 theVerb)
					)
					((not (ego script?))
						(FindTarget
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
						(FindTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(curRoom setScript: blastWiz 0 theVerb)
					)
				)
			)
			((== theVerb V_MAGIC_SPEAR)
				(Bset fSpearedWiz)
				(cond 
					((== (ego script?) walkOnGarg)
						(walkOnGarg
							setScript: (ScriptID 851 3) walkOnGarg theVerb
						)
					)
					((not (ego script?))
						(ego setScript: (ScriptID 851 3) 0 theVerb)
					)
					(else
						(curRoom setScript: (ScriptID 851 3) 0 theVerb)
					)
				)
			)
			((and (== heroType PALADIN) (== theVerb V_SWORD))
				(cond 
					((== (ego script?) walkOnGarg)
						(walkOnGarg
							setScript: (ScriptID 852 3) walkOnGarg theVerb
						)
					)
					((not (ego script?))
						(ego setScript: (ScriptID 852 3) walkOnGarg theVerb)
					)
				)
			)
			(
				(and
					(== heroType PALADIN)
					(== theVerb V_DO)
					knockedGargoyle
					(not (== ((ScriptID 852 3) state?) -1))
				)
				((ScriptID 852 3) cue:)
			)
			((== theVerb V_DISPEL)
				(messager say: N_DEWIZ V_DISPEL C_DISPEL_WONT_WORK 0 0 850)
			)
			((== theVerb V_GRAPNEL)
				(if (not (> (ego x?) 290))
					(messager say: N_REGION V_DOIT C_DONT_HAVE_CLEAR_SHOT 0 0 850)
				else
					(ego setScript: (ScriptID 854 2))
				)
			)
			((and (== theVerb V_DO) (cast contains: (ScriptID 852 5)))
					(EgoDead C_DEATH_THERMONUCLEAR 850)
			)
			(else (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(cond 
			((not (Btst fWizNoticesEgo))
				(curRoom setScript: noticeEgo)
			)
			((Btst fSpearedWiz)
				(if (not (== ((ScriptID 851 3) state?) -1))
					((ScriptID 851 3) cue:)
				else
					(ego drop: iMagicSpear)
					((ScriptID 851 3) start: 2)
					(if (== (ego script?) walkOnGarg)
						(walkOnGarg setScript: (ScriptID 851 3))
					else
						(ego setScript: (ScriptID 851 3))
					)
				)
			)
			((Btst fChuckedSwordAtWiz)
				((ScriptID 852 3) cue:)
			)
			(
				(==
					(if (and (> (ego view?) 17) (< (ego view?) 21)))
					1
				)
				((curRoom script?) setScript: (ScriptID 853 3))
			)
			((== heroType MAGIC_USER)
				(messager say: N_DEWIZ V_DOIT C_WIZ_PROTECTED_MAGIC 0 0 850)
			)
			(else
				(messager say: N_DEWIZ V_DOIT C_WIZ_PROTECTED 0 0 850)
			)
		)
	)
)

(instance portal of Prop
	(properties
		x 246
		y 106
		noun N_PORTAL
		modNum 850
		view 856
		priority 1
		signal (| ignrAct fixPriOn notUpd stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (and knockedGargoyle (not (cast contains: deWiz)))
					(curRoom setScript: touchPortal)
				else
					(super doVerb: theVerb)
				)
			)
			(V_ROCK
				(curRoom doVerb: theVerb)
			)
			(V_FLAME
				(curRoom doVerb: theVerb)
			)
			(V_FORCEBOLT
				(curRoom doVerb: theVerb)
			)
			(V_LIGHTNING
				(curRoom doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance orb of TargActor
	(properties
		x 267
		y 115
		z 20
		noun N_ORB
		modNum 850
		view 860
		loop 1
		signal ignrAct
	)
	
	(method (dispose)
		(ego addHonor: 100)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (and knockedGargoyle passedGargoyle)
					(curRoom setScript: touchOrb)
				else
					(super doVerb: theVerb)
				)
			)
			(V_SHIELD
				(if (== (ego script?) (ScriptID 851 2))
					((ScriptID 851 2) dispose:)
				)
				(if
					(and
						(not passedGargoyle)
						(== knockedGargoyle TRUE)
						(not (== (ego script?) walkOnGarg))
					)
					(ego setScript: walkOnGarg)
				)
				(if
					(and
						(== knockedGargoyle TRUE)
						(not (== (ego script?) walkOnGarg))
					)
					(curRoom setScript: knockOrb)
				else
					(super doVerb: theVerb)
				)
			)
			(V_FORCEBOLT
				(if (== ((ScriptID 853 2) state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				else
					(curRoom setScript: blastOrb 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (== ((ScriptID 853 2) state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				else
					(curRoom setScript: blastOrb 0 theVerb)
				)
			)
			(V_MAGIC_SPEAR
				(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
			)
			(V_GRAPNEL
				(messager say: N_REGION V_DOIT C_WiZ_PROTECTS_ORB 0 0 850)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(cond 
			((cast contains: deWiz)
				(messager say: N_REGION V_DOIT C_ORB_UNAFFECTED 0 0 850)
			)
			((== (curRoom script?) blastOrb)
				(blastOrb cue:)
			)
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
		signal (| ignrAct fixPriOn notUpd stopUpdOn)
	)
)

(instance pedestal of TargProp
	(properties
		x 266
		y 139
		noun N_PEDESTAL
		modNum 850
		view 860
		signal (| ignrAct ignrHrz)
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
		(eyes setCycle: Forward)
		(brows setCycle: Forward)
		(bubbles setCycle: Forward)
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
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DISPEL
				(messager say: N_GARGOYLE V_DISPEL C_DISPEL_WONT_WORK 0 0 850)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ledge of Feature
	(properties
		x 98
		y 165
		noun N_LEDGE
		nsTop 150
		nsLeft 74
		nsBottom 181
		nsRight 123
		sightAngle 180
	)
)

(instance egoActions of Actions
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(not knockedGargoyle)
					(== theVerb V_SHIELD)
					(cast contains: deWiz)
					(not (== (castSpells script?) egoHit))
				)
				(ego setScript: (ScriptID 851 2))
			)
			(
				(and
					(not knockedGargoyle)
					(== theVerb V_SHIELD)
					(== heroType PALADIN)
					(cast contains: deWiz)
					(not (== (castSpells script?) egoHit))
				)
				(ego setScript: (ScriptID 852 2))
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance sFx of Sound)
