;;; Sierra Script 1.0 - (do not remove this comment)
(script# 314)
(include game.sh) (include "314.shm")
(use Main)
(use Teller)
(use Ware)
(use Procs)
(use Talker)
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
	rm314 0
	zaraTalker 1
)

(local
	potionCue
	timesLookedAtRoom
	local2
	local3
	local4
	familiarAwake
	local6
	local7
	local8
	local9
	[local10 7] = [0 -46 -16 29 -27 -40 999]
	[local17 4] = [0 21 34 999]
	[local21 3] = [0 22 999]
	[local24 6] = [0 -44 35 28 25 999]
	[local30 5] = [0 -41 -42 -38 999]
	[local35 3] = [0 20 999]
	[local38 3] = [0 -12 999]
	[local41 4] = [0 20 -13 999]
	[local45 5] = [0 32 -39 -33 999]
	[local50 3] = [0 19 999]
	[local53 5] = [0 -26 -15 14 999]
	[local58 8] = [0 17 23 30 45 37 -31 999]
	[local66 5] = [0 35 24 43 999]
	[local71 3] = [0 36 999]
	[local74 3] = [0 18 999]
	[local77 3] = [0 19 999]
	[local80 21]
	[local101 17] = [0 -46 -16 -27 -40 -44 -41 -42 -38 -12 -13 -39 -33 -26 -15 -31 999]
)

(enum -1
	noFunds
	buyNothing
	buyFetch
	buyFlame
	buyOpen
	buyHealing
	buyMana
	buyStamina
)

(enum 1
	cueHealingPotion
	cueManaPotion
	cueStaminaPotion
)

(procedure (CantBuySpell spell)
	(switch spell
		(FETCH (ego get: iSilver 40))
		(FLAMEDART (ego get: iSilver 60))
		(OPEN (ego get: iSilver 30))
	)
)

(procedure (LearnSpell spell)
	(return
		(cond 
			((and spell (not [egoStats MAGIC])) (CantBuySpell spell) (messager say: N_ZARA V_MONEY))
			((and spell (ego knows: spell)) (CantBuySpell spell) (messager say: N_ROOM V_MONEY C_ALREADYKNOW))
			(else
				(if spell
					(messager say: N_ROOM V_MONEY C_LEARNSPELL)
					(switch spell
						(FETCH (ego learn: FETCH 5))
						(FLAMEDART (ego learn: FLAMEDART 5))
						(OPEN (ego learn: OPEN 5))
					)
				)
				(return TRUE)
			)
		)
	)
)

(instance rm314 of Room
	(properties
		noun N_ROOM
		picture 314
		style DISSOLVE
		east 310
	)
	
	(method (init)
		(= [local80 0] @local10)
		(= [local80 1] @local17)
		(= [local80 2] @local21)
		(= [local80 3] @local24)
		(= [local80 4] @local30)
		(= [local80 5] @local35)
		(= [local80 6] @local38)
		(= [local80 7] @local41)
		(= [local80 8] @local45)
		(= [local80 9] @local50)
		(= [local80 10] @local53)
		(= [local80 11] @local58)
		(= [local80 12] @local66)
		(= [local80 13] @local71)
		(= [local80 14] @local74)
		(= [local80 15] @local77)
		(= [local80 16] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						152 189
						277 168
						191 132
						181 131
						161 130
						113 141
						51 163
						36 178
						0 180
					yourself:
				)
		)
		(Load RES_VIEW 314)
		(Load RES_VIEW 315)
		(LoadMany RES_SOUND 67 (SoundFX 45) (SoundFX 28))
		(super init:)
		(NormalEgo)
		(ego posn: 60 240 init: hide:)
		(self setRegions: TOWN)
		(HandsOff)
		(self setScript: firstScript)
		(shopMusic init: play:)
		(= local9 1)
		(self
			setFeatures:
				onClouds
				onScroll
				onBat
				onBottles
				onBooks
				onCrystalBall
				onMagicBall
				onBalanceScale
				onFloor
				onPentagon
				onBrownJar
				onGreenJar
				onAGreenJar
				onRedJar
				onBlueBottle
				onCeiling
				onMortar
		)
		;UPGRADE
;;;		(onClouds init:)
;;;		(onScroll init:)
;;;		(onBat init:)
;;;		(onBottles init:)
;;;		(onBooks init:)
;;;		(onCrystalBall init:)
;;;		(onMagicBall init:)
;;;		(onBalanceScale init:)
;;;		(onFloor init:)
;;;		(onPentagon init:)
;;;		(onBrownJar init:)
;;;		(onGreenJar init:)
;;;		(onAGreenJar init:)
;;;		(onRedJar init:)
;;;		(onBlueBottle init:)
;;;		(onCeiling init:)
;;;		(onMortar init:)
		
		(zaraTeller init: zara @local10 @local80 @local101)
		(onFloor init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((curRoom script?) 0)
			((and (> (ego x?) 160) (not familiarAwake)) (= familiarAwake TRUE) (familiar setScript: familiarScript))
			(
				(and
					(not (ego script?))
					local4
					(or (< (ego x?) 10) (> (ego y?) 185))
				)
				(= local4 0)
				(ego setScript: exitScript)
			)
			(
				(and
					(not (ego script?))
					(or (< (ego x?) 10) (> (ego y?) 185))
				)
				(ego setScript: egoExitScript)
			)
		)
		(if local6
			(cond 
				((> (ego x?) 170) (if local7 (= local7 0) (familiarScript changeState: 2)))
				((not local7) (= local7 1) (familiarScript changeState: 5))
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset VISITED_MAGICSHOP_INSIDE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(curRoom setScript: lookAtRoom)
			)
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onClouds of Feature
	(properties
		x 300
		y 80
		noun N_CLOUDS
		nsTop 8
		nsLeft 252
		nsBottom 85
		nsRight 306
	)
)

(instance onScroll of Feature
	(properties
		x 136
		y 133
		z 68
		noun N_SCROLL
		nsTop 61
		nsLeft 122
		nsBottom 70
		nsRight 151
	)
)

(instance onMagicBall of Feature
	(properties
		x 304
		y 129
		z 53
		noun N_MAGICBALL
		nsTop 68
		nsLeft 294
		nsBottom 84
		nsRight 315
	)
)

(instance onCrystalBall of Feature
	(properties
		x 131
		y 134
		z 47
		noun N_CRYSTALBALL
		nsTop 80
		nsLeft 124
		nsBottom 95
		nsRight 138
	)
)

(instance onBalanceScale of Feature
	(properties
		x 82
		y 146
		z 47
		noun N_BALANCESCALE
		nsTop 85
		nsLeft 64
		nsBottom 114
		nsRight 100
	)
)

(instance onFloor of Feature
	(properties
		x 143
		y 154
		noun N_FLOOR
		onMeCheck cBLUE
	)
)

(instance onPentagon of Feature
	(properties
		x 257
		y 95
		noun N_PENTAGON
		nsTop 87
		nsLeft 223
		nsBottom 104
		nsRight 291
	)
)

(instance onBrownJar of Feature
	(properties
		x 289
		y 171
		noun N_BROWNJAR
		nsTop 158
		nsLeft 270
		nsBottom 185
		nsRight 310
	)
)

(instance onGreenJar of Feature
	(properties
		x 171
		y 115
		noun N_GREENJAR
		nsTop 104
		nsLeft 162
		nsBottom 127
		nsRight 181
	)
)

(instance onAGreenJar of Feature
	(properties
		x 171
		y 115
		noun N_GREENJAR
		nsTop 111
		nsLeft 282
		nsBottom 140
		nsRight 295
	)
)

(instance onBlueBottle of Feature
	(properties
		x 245
		y 173
		noun N_BLUEBOTTLE
		nsTop 159
		nsLeft 228
		nsBottom 187
		nsRight 263
	)
)

(instance onRedJar of Feature
	(properties
		x 22
		y 160
		noun N_REDJAR
		nsTop 151
		nsLeft 8
		nsBottom 169
		nsRight 37
	)
)

(instance onCeiling of Feature
	(properties
		x 23
		y 136
		z 119
		noun N_CEILING
		nsTop 3
		nsLeft 161
		nsBottom 32
		nsRight 313
	)
)

(instance onToaster of Feature
	(properties
		noun N_TOASTER
		nsTop 88
		nsLeft 102
		nsBottom 99
		nsRight 115
	)
)

(instance onManta of Feature
	(properties
		noun N_MANTA
		nsTop 72
		nsLeft 230
		nsBottom 90
		nsRight 252
	)
)

(instance onTinsel1 of Feature
	(properties
		noun N_TINSEL
		nsTop 107
		nsLeft 209
		nsBottom 122
		nsRight 219
	)
)

(instance onTinsel2 of Feature
	(properties
		noun N_TINSEL
		nsTop 118
		nsLeft 230
		nsBottom 139
		nsRight 250
	)
)

(instance onBottles of Feature
	(properties
		x 130
		y 134
		z 91
		noun N_BOTTLES
		nsTop 34
		nsLeft 108
		nsBottom 53
		nsRight 152
	)
)

(instance onBooks of Feature
	(properties
		x 148
		y 134
		z 50
		noun N_BOOKS
		nsTop 76
		nsLeft 142
		nsBottom 92
		nsRight 155
	)
)

(instance onMortar of Feature
	(properties
		x 207
		y 179
		noun N_MORTAR
		nsTop 170
		nsLeft 189
		nsBottom 188
		nsRight 225
	)
)

(instance onAccoutrements of Feature
	(properties
		noun N_ACCOUTREMENTS
		nsTop 51
		nsLeft 230
		nsBottom 68
		nsRight 251
	)
)

(instance onBat of Feature
	(properties
		x 82
		y 149
		z 133
		noun N_BAT
		nsTop 11
		nsLeft 73
		nsBottom 22
		nsRight 91
	)
)

(instance onHead of Feature
	(properties
		noun N_HEAD
		nsTop 94
		nsLeft 66
		nsBottom 113
		nsRight 92
	)
)

(instance onOdds1 of Feature
	(properties
		noun N_ODDS
		nsTop 72
		nsLeft 67
		nsBottom 88
		nsRight 92
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 5)
					(1 (messager say: N_ODDS V_LOOK C_ODDS1))
					(2 (messager say: N_ODDS V_LOOK C_ODDS2))
					(3 (messager say: N_ODDS V_LOOK C_ODDS3))
					(4 (messager say: N_ODDS V_LOOK C_ODDS4))
					(5 (messager say: N_ODDS V_LOOK C_ODDS5))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onOdds2 of Feature
	(properties
		noun N_ODDS
		nsTop 109
		nsLeft 102
		nsBottom 121
		nsRight 111
	)
)

(instance onOdds3 of Feature
	(properties
		noun N_ODDS
		nsTop 53
		nsLeft 201
		nsBottom 86
		nsRight 219
	)
)

(instance familiarFoot of View
	(properties
		x 193
		y 51
		noun N_FAMILIAR
		view 314
		loop 8
		priority 9
		signal $4011
	)
	
	(method (doVerb theVerb)
		(familiar doVerb: theVerb &rest)
	)
)

(instance familiar of Prop
	(properties
		x 202
		y 59
		noun N_FAMILIAR
		view 314
		loop 6
		signal $4000
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (not familiarAwake)
					(messager say: N_FAMILIAR V_LOOK)
				else
					(messager say: N_FAMILIAR V_LOOK C_FAMILIARAWAKE)
				)
			)
			(V_TALK (messager say: N_FAMILIAR V_TALK))
			(V_DO (messager say: N_FAMILIAR V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance glowCoals of Prop
	(properties
		x 240
		y 115
		noun N_COALS
		view 314
		loop 4
		cel 1
	)
)

(instance fishBowl of Prop
	(properties
		x 308
		y 149
		noun N_FISHBOWL
		view 314
		loop 3
		cel 2
	)
)

(instance burnSent of Prop
	(properties
		x 58
		y 123
		noun N_INCENSE
		view 314
		loop 2
		priority 6
		signal $0010
	)
)

(instance magicFlash of Prop
	(properties
		x 295
		y 80
		view 314
		loop 5
		priority 15
		signal $0010
		cycleSpeed 8
	)
)

(instance zara of Prop
	(properties
		x 259
		y 100
		noun N_ZARA
		view 315
	)
	
	(method (init)
		(= nightPalette 1315)
		(PalVary PALVARYTARGET 1315)
		(super init:)
	)
)

(instance zaraTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_TALK
					(SolvePuzzle POINTS_TALKTOZARA 1)
					(super doVerb: theVerb &rest)
				)
				(V_MONEY
					((= wareList (List new:))
						add:
							((Clone Ware) name: {Fetch Spell} price: {40})
							((Clone Ware) name: {Flame Spell} price: {60})
							((Clone Ware) name: {Open Spell} price: {30})
							((Clone Ware) name: {Healing Potion} price: {50})
							((Clone Ware) name: {Power Potion} price: {75})
							((Clone Ware) name: {Vigor Potion} price: {25})
					)
					(switch ((ScriptID 551 0) doit:)
						(noFunds (messager say: N_ZARA V_MONEY C_NOFUNDS))
						(buyFetch
							(SolvePuzzle POINTS_LEARNFETCH 2 MAGIC_USER)
							(LearnSpell FETCH)
						)
						(buyFlame
							(SolvePuzzle POINTS_LEARNFLAMEDART 2 MAGIC_USER)
							(LearnSpell FLAMEDART)
						)
						(buyOpen
							(LearnSpell OPEN)
							(SolvePuzzle POINTS_LEARNOPEN 2 MAGIC_USER)
						)
						(buyHealing
							(= potionCue cueHealingPotion)
							(messager say: N_ZARA V_MONEY C_BUYSOMETHING)
							(ego setScript: cuedIt)
						)
						(buyMana
							(= potionCue cueManaPotion)
							(messager say: N_ZARA V_MONEY C_BUYSOMETHING)
							(ego setScript: cuedIt)
						)
						(buyStamina
							(= potionCue cueStaminaPotion)
							(messager say: N_ZARA V_MONEY C_BUYSOMETHING)
							(ego setScript: cuedIt)
						)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance firstScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego show: setCycle: Walk setMotion: MoveTo 91 176 self)
				(familiar init: stopUpd:)
				(glowCoals init: setCycle: Forward)
				(fishBowl init: setCycle: Forward)
				(burnSent init: setCycle: Forward)
			)
			(1
				(ego loop: 6 cel: 0 posn: 91 176)
				(= cycles 4)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theTeleport play:)
				(self cue:)
			)
			(1 (self cue:))
			(2
				(zara loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(zara loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4
				(theThunder play:)
				(= ticks 20)
			)
			(5
				(ego setMotion: PolyPath 60 240 self)
				(zara dispose:)
			)
			(6
				(HandsOn)
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance egoExitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 60 240 self)
			)
			(1
				(ego setMotion: PolyPath -20 (ego y?) self)
			)
			(2 (curRoom newRoom: 310))
		)
	)
)

(instance familiarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(familiar setCycle: EndLoop self)
			)
			(1
				(familiarFoot init:)
				(familiar setLoop: 7 posn: 200 61 cel: 0)
				(= local6 1)
				(if (> (ego x?) 170)
					(self cue:)
				else
					(familiar stopUpd:)
				)
			)
			(2
				(familiar setCycle: EndLoop self)
			)
			(3
				(familiar stopUpd:)
				(if (not local4)
					(magicFlash init: setCycle: CycleTo 7 1 self)
				else
					(self cue:)
				)
			)
			(4
				(if (not local4)
					(theThunder number: (SoundFX 45) init: play:)
					(magicFlash init: setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(5
				(magicFlash dispose:)
				(zara loop: 0 init: setCycle: EndLoop self)
				(= local4 1)
			)
			(6
				(familiar loop: 7 setCycle: BegLoop self)
			)
			(7
				(familiar stopUpd:)
				(if (not (Btst VISITED_MAGICSHOP_INSIDE))
					(self cue:)
				else
					(= local2 1)
					(messager say: N_ZARA 0 C_WELCOMEBACK 1 self)
				)
			)
			(8
				(if (== local2 0)
					(messager say: N_ZARA 0 C_FIRSTMEET 1 self)
				else
					(self cue:)
				)
				(= local2 1)
			)
			(9 (HandsOn) (self dispose:))
		)
	)
)

(instance lookAtRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 30))
			(1
				(messager say: N_ROOM V_LOOK 7 1 self)
			)
			(2
				(switch (++ timesLookedAtRoom)
					(1
						(messager say: N_ROOM V_LOOK 7 2 self)
					)
					(2
						(messager say: N_ROOM V_LOOK 7 3 self)
					)
					(3
						(messager say: N_ROOM V_LOOK 7 4 self)
					)
					(4
						(messager say: N_ROOM V_LOOK 7 5 self)
					)
					(5
						(messager say: N_ROOM V_LOOK 7 6 self)
					)
					(6
						(messager say: N_ROOM V_LOOK 7 7 self)
					)
					(7
						(messager say: N_ROOM V_LOOK 7 8 self)
					)
					(8
						(messager say: N_ROOM V_LOOK 7 9 self)
					)
					(9
						(messager say: N_ROOM V_LOOK 7 10 self)
					)
				)
				(if (== timesLookedAtRoom 9) (= timesLookedAtRoom 0))
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance cuedIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch potionCue
					(cueHealingPotion (ego get: iHealingPotion))
					(cueManaPotion (ego get: iManaPotion))
					(cueStaminaPotion (ego get: iStaminaPotion))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)

(instance shopMusic of Sound
	(properties
		flags $ffff
		number 67
		loop -1
	)
)

(instance theThunder of Sound
	(properties
		number 45
	)
)

(instance theTeleport of Sound
	(properties
		number 28
	)
)

(instance zaraTalker of Talker
	(properties
		x 10
		y 10
		view 1314
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2314)
		(PalVary PALVARYTARGET 2314)
		(= font userFont)
		(super init: zaraBust zaraEye zaraMouth &rest)
	)
)

(instance zaraBust of Prop
	(properties
		view 1314
	)
)

(instance zaraMouth of Prop
	(properties
		nsTop 36
		nsLeft 45
		view 1314
		loop 1
	)
)

(instance zaraEye of Prop
	(properties
		nsTop 21
		nsLeft 44
		view 1314
		loop 2
	)
)
