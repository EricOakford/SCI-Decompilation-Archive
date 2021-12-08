;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include game.sh) (include "280.shm")
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm280 0
)

;room states
(enum
	firstTime			;0
	kreeshaInParlor		;1
	kreeshaReading		;2
	afterOath			;3
	state4				;4
	backFromSimbani		;5
	makingAStaff		;6
	state7				;7
	becomingAPaladin	;8
)

(local
	saveRoomState
	local1 = [0 -2 -3 -4 -6 -7 -19 -20 -21 -22 -37 -39 -38 -40 -65 -70 -71 -74 -74 -64 999]
	[local22 11]
	local33 = [0 -4 -5 -7 -20 -65 -66 -69 -71 999]
	local43 = [0 72 999]
	local46 = [0 -5 999]
	local49 = [0 -66 34 999]
	local53 = [0 67 999]
	local56 = [0 -59 -58 56 57 999]
	local62 = [0 60 61 999]
	local66 = [0 68 -69 999]
	local70 = [0 26 999]
	local73 = [0 -78 -11 -19 -36 -75 -76 999]
	[local81 2]
	local83 = [0 -2 -8 -54 -3 -55 999]
	[local90 5]
	local95 = [0 -8 -3 -51 999]
	local100 = [0 -49 -51 999]
	local104 = [0 -50 -48 -18 999]
	local109 = [0 -53 -52 999]
)
(instance rm280 of Room
	(properties
		noun N_ROOM
		picture 280
		horizon 62
		picAngle 70
		vanishingX 168
		vanishingY -160
	)
	
	(method (init)
		(theIconBar enable:)
		(if (== prevRoomNum 270)
			(cSound hold: 0 setVol: 127)
		else
			(cSound number: 280 setLoop: -1 play: 127)
		)
		(= [local22 0] @local1)
		(= [local22 1] @local46)
		(= [local22 2] @local56)
		(= [local22 3] @local62)
		(= [local22 4] @local66)
		(= [local22 5] @local49)
		(= [local22 6] @local53)
		(= [local22 7] @local43)
		(= [local22 8] @local70)
		(= [local90 0] @local83)
		(= [local90 1] @local100)
		(= [local90 2] @local104)
		(= [local90 3] @local109)
		(= [local81 0] @local73)
		(ego
			init:
			normalize:
			x: -100
			y: -100
			noun: N_EGO_TELL
			edgeHit: 0
			setScale: 175
			changeGait: 0
		)
		(egoTell init: ego @local73 @local81)
		(kreeshaTell init: kreeshaTop @local1 @local22 @local33)
		(rakeeshTell
			init: (ScriptID RAKEESH_TALKER 1) @local83 @local90 @local95
		)
		(super init:)
		(FindTarget -10 -10)
		(urn init:)
		(chest init:)
		(pillar init:)
		(vases init:)
		(moreBooks init:)
		(plant init:)
		(shelf init:)
		(otherBottles init:)
		(boxes init:)
		(skulls init:)
		(bottles init:)
		(moreCandles init:)
		(cretianUrn init:)
		(pentagram init:)
		(leftPitcher init:)
		(stool init:)
		(books init:)
		(hornSkull init:)
		(leftCandles init:)
		(rightCandles init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 189
						0 189
						0 88
						3 88
						3 187
						317 187
						317 115
						295 115
						292 108
						295 100
						317 100
						316 92
						232 92
						232 96
						267 97
						272 107
						267 115
						37 115
						38 108
						52 97
						65 97
						65 93
						0 84
						0 0
					yourself:
				)
		)
		(cond 
			(
				(and
					(not (== heroType PALADIN))
					(not (Btst fHadPaladinCeremony))
					(> [egoStats HONOR] 149)
					(not (Btst fCantBePaladin))
					(== prevRoomNum 420)
				)
				(= saveRoomState kreeshaHomeState)
				(= kreeshaHomeState becomingAPaladin)
			)
			((== kreeshaHomeState state7)
				(= kreeshaHomeState state7)
			)
			((and (ego has: iWood) (not (Btst fCanSummonStaff)))
				(= saveRoomState kreeshaHomeState)
				(= kreeshaHomeState makingAStaff)
			)
			((and (== kreeshaHomeState afterOath) (Btst fVisitedSimbani))
				(= kreeshaHomeState backFromSimbani)
			)
			(
				(and
					(Btst fMetHarami)
					(not (== kreeshaHomeState kreeshaReading))
					(not (Btst fSawKreeshaReading))
					(not (Btst fRakeeshSworeOath))
				)
				(= kreeshaHomeState kreeshaReading)
			)
			((Btst fRakeeshSworeOath)
				(= kreeshaHomeState afterOath)
			)
			((and (Btst fMetHarami) (not (Btst fRakeeshSworeOath)))
				(= kreeshaHomeState kreeshaInParlor)
			)
			(
			(and (not (== kreeshaHomeState firstTime)) (not (Btst fMetHarami)))
			(= kreeshaHomeState kreeshaInParlor)
		)
			((== kreeshaHomeState firstTime)
				(= kreeshaHomeState firstTime)
			)
			(else
				(= kreeshaHomeState kreeshaInParlor)
			)
		)
		(if (== prevRoomNum 285)
			(self setScript: from285)
		else
			(switch kreeshaHomeState
				(firstTime
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									21 159
									177 157
									178 176
									14 179
								yourself:
							)
					)
					(self setScript: firstEntrance)
				)
				(kreeshaReading
					(Bset fSawKreeshaReading)
					(kreeshaTell init: kreesha @local1 @local22 @local33)
					(kreesha
						x: 169
						y: 146
						loop: 2
						cel: 0
						noun: 2
						actions: kreeshaTell
						init:
						ignoreActors: TRUE
						stopUpd:
					)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									98 133
									147 126
									173 130
									178 153
									123 161
									89 157
								yourself:
							)
					)
					(self setScript: doneChanger)
				)
				(makingAStaff
					(self setScript: enterWithWood)
				)
				(becomingAPaladin
					((ScriptID RAKEESH_TALKER 1) view: 963 loop: 1 x: 236 y: 130 init:)
					(ego x: 155 y: 142 init:)
					(kreesha x: 54 y: 182 init: addToPic:)
					(kreeshaTop
						x: (- (kreesha x?) 2)
						y: (- (kreesha y?) 35)
						setPri: 14
						init:
					)
					(self setScript: ceremonyScript)
				)
				(else 
					(self setScript: mostTimes)
				)
			)
		)
	)
	
	(method (doit)
		(cond 
			((self script?) 0)
			((ego script?) 0)
			((ego inRect: 255 89 319 102)
				(if (OneOf
						kreeshaHomeState
						firstTime
						kreeshaReading
						state4
						makingAStaff
						becomingAPaladin
					)
					(self setScript: bePolite)
				else
					(curRoom newRoom: 285)
				)
			)
			((ego inRect: 0 87 45 115)
				(curRoom setScript: egoExits)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany FALSE DPATH RAKEESH_TALKER UHURA_TALKER KREESHA_TALKER CASTJUGGLE)
		(if (== kreeshaHomeState firstTime)
			(= kreeshaHomeState kreeshaInParlor)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (curRoom script?)
			(super doVerb: theVerb)
		else
			(switch theVerb
				(V_FLAME
					(cond 
						(
							(or
								(cast contains: kreesha)
								(cast contains: (ScriptID RAKEESH_TALKER 1))
							)
							(messager say: N_CUE V_DOIT C_NO_SPELLS_NOW)
						)
						((ego castSpell: FLAMEDART)
							(self setScript: (ScriptID PROJECTILE 0) 0 V_FLAME)
						)
					)
				)
				(V_FORCEBOLT
					(cond 
						(
							(or
								(cast contains: kreesha)
								(cast contains: (ScriptID RAKEESH_TALKER 1))
							)
							(messager say: N_CUE V_DOIT C_NO_SPELLS_NOW)
						)
						((ego castSpell: FORCEBOLT)
							(self setScript: (ScriptID PROJECTILE 0) 0 V_FORCEBOLT)
						)
					)
				)
				(V_LIGHTNING
					(cond 
						(
							(or
								(cast contains: kreesha)
								(cast contains: (ScriptID RAKEESH_TALKER 1))
							)
							(messager say: N_CUE V_DOIT C_NO_SPELLS_NOW)
						)
						((ego castSpell: LIGHTNING)
							(self setScript: (ScriptID PROJECTILE 0) 0 V_LIGHTNING)
						)
					)
				)
				(V_CALM
					(if (ego castSpell: CALM)
						(curRoom setScript: (ScriptID CASTAREA))
					)
				)
				(V_TRIGGER
					(if (ego castSpell: TRIGGER)
						(messager say: N_CUE V_DOIT C_TRIGGER)
					)
				)
				(V_REVERSAL
					(if (ego castSpell: REVERSAL)
						(messager say: N_CUE V_DOIT C_REVERSAL)
					)
				)
				(V_LEVITATE
					((ScriptID LEVITATION 0) init:)
				)
				(V_JUGGLE
					(if (ego castSpell: JUGGLE)
						(curRoom setScript: (ScriptID CASTJUGGLE))
					)
				)
				(V_FETCH
					(if (ego castSpell: FETCH)
						(messager say: N_CUE V_DOIT C_FETCH)
					)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance from285 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: 288
					y: 108
					init:
					normalize:
					setMotion: MoveTo 277 141 self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance ceremonyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 1)
			)
			(1
				(messager say: N_CUE V_DOIT C_START_CEREMONY 0 self)
				(= paladinStat (- [egoStats HONOR] 9))
			)
			(2
				(messager say: N_RAKEESH V_DOIT C_BECOME_PALADIN 0 self)
			)
			(3
				((ScriptID RAKEESH_TALKER 1) setCycle: EndLoop self)
				(globalSound number: 240 play:)
			)
			(4
				(messager say: N_RAKEESH V_DOIT C_GET_SOULFORGE 0 self)
			)
			(5
				(ego setMotion: MoveTo 198 136 self)
			)
			(6
				(ego view: 31 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(7
				((ScriptID RAKEESH_TALKER 1) cel: 0)
				(ego setCycle: BegLoop self)
			)
			(8
				(ego view: 38 loop: 0 cel: 1 setCycle: EndLoop self)
			)
			(9
				(= seconds 1)
			)
			(10
				(ego setCycle: BegLoop self)
			)
			(11
				(ego normalize:)
				(messager say: N_RAKEESH V_DOIT C_HONOR 0 self)
			)
			(12
				(messager say: N_RAKEESH V_DOIT C_LETS_GO 0 self)
			)
			(13
				(Bset fHadPaladinCeremony)
				(ego solvePuzzle: fBecomePaladin 25 puzzleFIGHTER)
				(= kreeshaHomeState saveRoomState)
				(= heroType PALADIN)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance enterWithWood of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= kreeshaHomeState saveRoomState)
				(HandsOff)
				(ego x: 20 y: 110 init: setMotion: PolyPath 133 131 self)
				(kreesha x: 50 y: 180 loop: 0 cel: 0 init: addToPic:)
				(kreeshaTop
					x: (- (kreesha x?) 2)
					y: (- (kreesha y?) 35)
					setPri: 14
					init:
				)
			)
			(1
				(messager say: N_KREESHA V_DOIT C_START_RITUAL 0 self)
			)
			(2
				(messager say: N_KREESHA V_DOIT C_WOOD 0 self)
			)
			(3
				(ego setMotion: PolyPath 133 146 self)
			)
			(4
				(ego view: 4 cel: 0 setCycle: EndLoop self)
			)
			(5
				(stick init:)
				(ego setCycle: BegLoop self)
			)
			(6
				(ego normalize: setMotion: PolyPath 79 142 self)
			)
			(7
				(ego setHeading: 90 self)
			)
			(8
				(messager say: N_KREESHA V_DOIT C_CHANT 0 self)
			)
			(9
				(kreeshaTop setCycle: EndLoop self)
			)
			(10
				(globalSound number: 12 setLoop: 1 play:)
				(thatOldGlow init: setPri: 12 setCycle: Forward)
				(stick setPri: 13)
				(= seconds 5)
			)
			(11
				(messager say: N_KREESHA V_DOIT C_CALL_CIRCLE 0 self)
			)
			(12
				(globalSound number: 900 setLoop: 1 play:)
				(pentaGlow init: setPri: 11)
				(= seconds 3)
			)
			(13
				(globalSound number: 281 setLoop: 1 play:)
				(stick setMotion: MoveTo 155 96 self)
			)
			(14
				(messager say: N_KREESHA V_DOIT C_SPELLS_ON_STAFF 0 self)
			)
			(15
				(ego view: 15 learn: 31 10 drop: 44 setCycle: CycleTo 3 1 self)
				(Bset 69)
			)
			(16
				(pow
					x: 81
					y: 84
					setLoop: 2
					init:
					setScale:
					setPri: 14
					setCycle: Forward
				)
				(= seconds 1)
			)
			(17
				(ego setCycle: EndLoop)
				(globalSound number: 13 setLoop: 1 play:)
				(pow setStep: 6 5 setMotion: MoveTo 153 98 self)
			)
			(18
				(pow dispose:)
				(= seconds 1)
			)
			(19
				(messager say: N_KREESHA V_DOIT C_SPELL_AGAIN 0 self)
			)
			(20
				(ego view: 15 setCycle: CycleTo 3 1 self)
			)
			(21
				(pow
					x: 81
					y: 84
					setLoop: 4
					init:
					setScale:
					setPri: 14
					setCycle: Forward
				)
				(= seconds 1)
			)
			(22
				(ego setCycle: EndLoop)
				(globalSound number: 13 setLoop: 1 play:)
				(pow setStep: 6 5 setMotion: MoveTo 153 98 self)
			)
			(23
				(pow dispose:)
				(= seconds 1)
			)
			(24
				(messager say: N_KREESHA V_DOIT C_SPELL_AGAIN 0 self)
			)
			(25
				(ego view: 15 setCycle: CycleTo 3 1 self)
			)
			(26
				(pow
					x: 81
					y: 84
					setLoop: 2
					init:
					setScale:
					setPri: 14
					setCycle: Forward
				)
				(= seconds 1)
			)
			(27
				(ego setCycle: EndLoop)
				(globalSound number: 13 setLoop: 1 play:)
				(pow setStep: 6 5 setMotion: MoveTo 153 98 self)
			)
			(28
				(pow dispose:)
				(= seconds 1)
			)
			(29
				(messager say: N_KREESHA V_DOIT C_SPELL_AGAIN 0 self)
			)
			(30
				(ego view: 15 setCycle: CycleTo 3 1 self)
			)
			(31
				(pow
					x: 81
					y: 84
					setLoop: 4
					init:
					setScale:
					setPri: 14
					setCycle: Forward
				)
				(= seconds 1)
			)
			(32
				(ego setCycle: EndLoop)
				(globalSound number: 13 setLoop: 1 play:)
				(pow setStep: 6 5 setMotion: MoveTo 153 98 self)
			)
			(33
				(pow dispose:)
				(= seconds 1)
			)
			(34
				(messager say: N_KREESHA V_DOIT C_STAFF 0 self)
			)
			(35
				(stick dispose:)
				(ego view: 20 loop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 12 setLoop: 1 play:)
			)
			(36
				(ego loop: 2 setCycle: EndLoop self)
			)
			(37
				(messager say: N_KREESHA V_DOIT C_STAFF_MADE 0 self)
			)
			(38
				(pentaGlow dispose:)
				(= seconds 3)
			)
			(39
				(thatOldGlow dispose:)
				(kreeshaTop setCycle: BegLoop self)
			)
			(40
				(messager say: N_KREESHA V_DOIT 32 0 self)
			)
			(41
				(ego solvePuzzle: fMakeStaff 10 puzzleWIZARD)
				(ego normalize:)
				(curRoom newRoom: 270)
			)
		)
	)
)

(instance doneChanger of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 20 y: 110 init: setMotion: PolyPath 160 125 self)
			)
			(1
				(kreesha setCycle: EndLoop self)
			)
			(2
				(kreesha stopUpd:)
				(messager say: N_KREESHA V_DOIT C_DONE_CHANGER 0 self)
			)
			(3
				(if (or (Btst fFlamedHarami) (Btst fUsedCalmOnHarami))
					(messager say: N_KREESHA V_DOIT C_MAGIC 0 self)
				else
					(self cue:)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance mostTimes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 20 y: 110 init: setMotion: PolyPath 160 100 self)
			)
			(1
				(switch kreeshaHomeState
					(kreeshaInParlor
						(messager say: N_CUE V_DOIT C_IN_PARLOR 0 self)
					)
					(afterOath
						(messager say: N_CUE V_DOIT C_IN_PARLOR 0 self)
					)
					(backFromSimbani
						(messager say: N_CUE V_DOIT C_IN_PARLOR 0 self)
					)
					(state7
						(messager say: N_CUE V_DOIT C_IN_PARLOR 0 self)
					)
					(else
						(self cue:)
					)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance firstEntrance of Script
	
	(method (changeState newState &tmp [temp0 15])
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fInMainGame)
				(kreesha
					x: 74
					y: 176
					view: 282
					loop: 0
					cel: 0
					actions: kreeshaTell
					noun: N_KREESHA
					signal: ignrAct
					init:
					addToPic:
				)
				(kreeshaTop
					x: (- (kreesha x?) 2)
					y: (- (kreesha y?) 35)
					setPri: 13
					loop: 3
					cel: 0
					signal: (| ignrAct fixPriOn)
					init:
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(= seconds 5)
			)
			(1
				(globalSound number: 932 setLoop: -1 play: 127)
				(portal cycleSpeed: 10 setCycle: Forward init:)
				(= seconds 3)
			)
			(2
				(kreeshaTop setCycle: BegLoop)
				(ego x: 283 y: 115 setMotion: PolyPath 290 170 self)
			)
			(3
				((ScriptID UHURA_TALKER 1)
					x: 281
					y: 115
					view: 968
					loop: 2
					cel: 0
					origStep: 1026
					setScale: 165
					setCycle: Walk
					init:
					setMotion: PolyPath 281 123 self
				)
				(ego setLoop: 1)
				(ego setLoop: -1)
			)
			(4
				((ScriptID UHURA_TALKER 1) setMotion: PolyPath 180 130 self)
			)
			(5
				((ScriptID UHURA_TALKER 1)
					setLoop: 2
					ignoreActors: TRUE
				)
				(= cycles 2)
			)
			(6
				((ScriptID RAKEESH_TALKER 1)
					x: 285
					y: 115
					view: 281
					setLoop: 1
					cel: 0
					cycleSpeed: 6
					setScale: 165
					setCycle: Walk
					origStep: 1540
					noun: 3
					init:
					setMotion: MoveTo 255 155 self
				)
			)
			(7
				((ScriptID RAKEESH_TALKER 1)
					setLoop: 3
					x: (- ((ScriptID RAKEESH_TALKER 1) x?) 20)
					ignoreActors: 1
					setMotion: MoveTo 127 165 self
				)
				(kreeshaTop setCycle: EndLoop)
			)
			(8
				((ScriptID RAKEESH_TALKER 1) setLoop: 5 stopUpd:)
				(= seconds 2)
			)
			(9
				(kreeshaTop setCycle: BegLoop self)
			)
			(10
				(kreeshaTop stopUpd:)
				(portal dispose:)
				(globalSound stop:)
				(= cycles 2)
			)
			(11
				(messager say: N_RAKEESH V_DOIT C_LIONTAURS_GREET 0 self)
			)
			(12
				(messager say: N_RAKEESH V_DOIT C_UHURA 0 self)
			)
			(13
				((ScriptID UHURA_TALKER 1)
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 5
					moveSpeed: 5
					setMotion: MoveTo -7 ((ScriptID 34 1) y?) self
				)
			)
			(14
				((ScriptID UHURA_TALKER 1) dispose:)
				(if (== heroType PALADIN)
					(messager say: N_RAKEESH V_DOIT C_PALADIN 0 self)
				else
					(messager say: N_RAKEESH V_DOIT C_INTRODUCE_EGO 0 self)
				)
			)
			(15
				(if [egoStats MAGIC]
					(messager say: N_KREESHA V_DOIT C_HAVE_MAGIC 0 self)
				else
					(self cue:)
				)
			)
			(16
				(HandsOn)
				(portal dispose:)
				(self dispose:)
			)
		)
	)
)

(instance bePolite of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_KREESHA V_DOIT C_BE_POLITE 0 self)
			)
			(1
				(ego setMotion: MoveTo 265 138 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance egoExits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((== kreeshaHomeState kreeshaReading)
						(messager say: N_KREESHA V_DOIT C_REMINDER 0 self)
					)
					((== kreeshaHomeState firstTime)
						(messager say: N_RAKEESH V_DOIT C_GOODBYE 0 self)
						(++ kreeshaHomeState)
					)
					(else
						(self cue:)
					)
				)
			)
			(1
				(curRoom newRoom: 270)
			)
		)
	)
)

(instance pow of Actor
	(properties
		view 21
		loop 2
		signal ignrAct
	)
)

(instance kreesha of Actor
	(properties
		x 246
		y 186
		noun N_KREESHA
		yStep 4
		view 282
		cycleSpeed 2
		xStep 5
	)
	
	(method (doVerb theVerb)
		(kreeshaTell doVerb: theVerb)
	)
)

(instance kreeshaTop of Prop
	(properties
		x 71
		y 142
		noun N_KREESHA
		view 282
		loop 3
		signal ignrAct
	)
)

(instance portal of Prop
	(properties
		x 285
		y 117
		view 280
		signal ignrAct
	)
)

(instance stick of Actor
	(properties
		x 155
		y 141
		view 790
		loop 1
		signal ignrAct
	)
)

(instance thatOldGlow of Prop
	(properties
		x 157
		y 163
		view 283
		loop 1
		signal ignrAct
	)
)

(instance pentaGlow of Prop
	(properties
		x 157
		y 163
		view 283
		signal ignrAct
	)
)

(instance leftCandles of Feature
	(properties
		x 12
		y 45
		noun N_LEFT_CANDLES
		nsTop 35
		nsLeft 2
		nsBottom 55
		nsRight 22
		sightAngle 180
	)
)

(instance rightCandles of Feature
	(properties
		x 293
		y 46
		noun N_LEFT_CANDLES
		nsTop 37
		nsLeft 284
		nsBottom 55
		nsRight 303
		sightAngle 180
	)
)

(instance hornSkull of Feature
	(properties
		x 157
		y 17
		noun N_HORN_SKULL
		nsTop 5
		nsLeft 146
		nsBottom 30
		nsRight 168
		sightAngle 180
	)
)

(instance pentagram of Feature
	(properties
		x 157
		y 145
		noun N_PENTAGRAM
		nsTop 125
		nsLeft 92
		nsBottom 165
		nsRight 222
		sightAngle 180
	)
)

(instance leftPitcher of Feature
	(properties
		x 39
		y 73
		noun N_PITCHER
		nsTop 56
		nsLeft 33
		nsBottom 90
		nsRight 46
		sightAngle 180
	)
)

(instance stool of Feature
	(properties
		x 61
		y 94
		noun N_STOOL
		nsTop 81
		nsLeft 45
		nsBottom 108
		nsRight 78
		sightAngle 180
	)
)

(instance books of Feature
	(properties
		x 200
		y 39
		noun N_BOOKS
		nsTop 29
		nsLeft 175
		nsBottom 49
		nsRight 226
		sightAngle 180
	)
)

(instance moreCandles of Feature
	(properties
		x 228
		y 62
		noun N_CANDLES
		nsTop 49
		nsLeft 220
		nsBottom 75
		nsRight 236
		sightAngle 180
	)
)

(instance cretianUrn of Feature
	(properties
		x 135
		y 44
		noun N_CRETIAN_URN
		nsTop 33
		nsLeft 122
		nsBottom 55
		nsRight 148
		sightAngle 180
	)
)

(instance boxes of Feature
	(properties
		x 92
		y 73
		noun N_BOXES
		nsTop 65
		nsLeft 79
		nsBottom 82
		nsRight 106
		sightAngle 180
	)
)

(instance skulls of Feature
	(properties
		x 207
		y 12
		noun N_SKULLS
		nsTop 8
		nsLeft 179
		nsBottom 17
		nsRight 236
		sightAngle 180
	)
)

(instance bottles of Feature
	(properties
		x 103
		y 15
		noun N_BOTTLES
		nsTop 5
		nsLeft 65
		nsBottom 25
		nsRight 141
		sightAngle 180
	)
)

(instance shelf of Feature
	(properties
		x 83
		y 34
		noun N_SHELF
		nsTop 26
		nsLeft 66
		nsBottom 43
		nsRight 100
		sightAngle 180
	)
)

(instance otherBottles of Feature
	(properties
		x 190
		y 64
		noun N_OTHER_BOTTLES
		nsTop 56
		nsLeft 178
		nsBottom 73
		nsRight 203
		sightAngle 180
	)
)

(instance plant of Feature
	(properties
		x 191
		y 78
		noun N_PLANT
		nsTop 74
		nsLeft 178
		nsBottom 82
		nsRight 204
		sightAngle 180
	)
)

(instance moreBooks of Feature
	(properties
		x 213
		y 65
		noun N_MORE_BOOKS
		nsTop 56
		nsLeft 207
		nsBottom 74
		nsRight 219
		sightAngle 180
	)
)

(instance vases of Feature
	(properties
		x 140
		y 65
		noun N_VASES
		nsTop 55
		nsLeft 111
		nsBottom 76
		nsRight 169
		sightAngle 180
	)
)

(instance chest of Feature
	(properties
		x 297
		y 84
		noun N_CHEST
		nsTop 73
		nsLeft 289
		nsBottom 95
		nsRight 306
		sightAngle 180
	)
)

(instance pillar of Feature
	(properties
		x 255
		y 61
		noun N_PILLAR
		nsTop 12
		nsLeft 242
		nsBottom 111
		nsRight 269
		sightAngle 180
	)
)

(instance urn of Feature
	(properties
		x 275
		y 73
		noun N_URN
		nsTop 56
		nsLeft 270
		nsBottom 91
		nsRight 281
		sightAngle 180
	)
)

(instance kreeshaTell of Teller

	(method (showDialog)
		(super
			showDialog:
				-3
				(== kreeshaHomeState firstTime)
				-5
				(== kreeshaHomeState firstTime)
				-7
				(== kreeshaHomeState firstTime)
				-2
				(== kreeshaHomeState firstTime)
				-4
				(< kreeshaHomeState kreeshaReading)
				-19
				(> kreeshaHomeState kreeshaReading)
				-20
				(if (== heroType MAGIC_USER) (> kreeshaHomeState kreeshaInParlor) else 0)
				-21
				(> kreeshaHomeState kreeshaInParlor)
				-22
				(> kreeshaHomeState kreeshaInParlor)
				-37
				(== kreeshaHomeState state7)
				-39
				(== kreeshaHomeState makingAStaff)
				-38
				(== kreeshaHomeState makingAStaff)
				-40
				(== kreeshaHomeState state7)
				-6
				(if (== kreeshaHomeState firstTime) else (== kreeshaHomeState state7))
				-58
				(if (== kreeshaHomeState firstTime) (== heroType MAGIC_USER) else 0)
				-74
				(== kreeshaHomeState makingAStaff)
				-71
				(== kreeshaHomeState makingAStaff)
				-70
				(== kreeshaHomeState makingAStaff)
				-74
				(== kreeshaHomeState makingAStaff)
				-65
				(== kreeshaHomeState kreeshaReading)
				-64
				(== kreeshaHomeState kreeshaReading)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-69
					(Bset fNeedStaff)
					(return query)
				)
				(-4
					(super doChild: query)
				)
				(-7
					(super doChild: query)
				)
				(-5
					(super doChild: query)
				)
				(-20
					(super doChild: query)
				)
				(-71
					(super doChild: query)
				)
				(-65
					(super doChild: query)
				)
				(-66
					(super doChild: query)
				)
				(else
					(return query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((or (== theVerb V_FLAME) (== theVerb V_FORCEBOLT) (== theVerb V_LIGHTNING))
				(messager say: N_CUE V_DOIT C_NO_ATTACK_KREESHA)
			)
			((== theVerb V_FETCH)
				(messager say: N_CUE V_DOIT C_FETCH)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance egoTell of Teller

	(method (showDialog)
		(super
			showDialog:
				-36
				(== kreeshaHomeState state7)
				-19
				(== kreeshaHomeState afterOath)
				-11
				(== kreeshaHomeState firstTime)
				-75
				(== kreeshaHomeState firstTime)
				-76
				(if (< kreeshaHomeState makingAStaff) (> kreeshaHomeState firstTime) else 0)
				-77
				(== kreeshaHomeState makingAStaff)
		)
	)
	
	(method (doChild)
		(return query)
	)
)

(instance rakeeshTell of Teller
	
	(method (showDialog)
		(super showDialog: -55 (== heroType PALADIN))
	)
	
	(method (doChild)
		(return
			(switch query
				(-8
					(super doChild: query)
				)
				(-3
					(super doChild: query)
				)
				(-51
					(super doChild: query)
				)
				(else
					(return query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((or (== theVerb V_FLAME) (== theVerb V_FORCEBOLT) (== theVerb V_LIGHTNING))
				(messager say: N_CUE V_DOIT C_NO_ATTACK_RAKEESH)
			)
			((== theVerb V_FETCH)
				(messager say: N_CUE V_DOIT C_FETCH)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)
