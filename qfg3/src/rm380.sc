;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include game.sh) (include "380.shm")
(use Main)
(use Target)
(use EgoDead)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm380 0
)

(local
	vinesGone
	local1
	egoGrabbed
	roomState
)

(enum ;room states
	firstTime
	vineTrap
	savedBat
	allDoneHere
)
(instance rm380 of Room
	(properties
		noun N_ROOM
		picture 380
	)
	
	(method (init)
		(HandsOff)
		(= thrownDaggers 0)
		(LoadMany RES_SCRIPT FORCOUNT JUMP)
		(ego
			x: 164
			y: 68
			setScale: Scaler 75 50 95 0
			init:
			normalize: 2
		)
		(super init:)
		(cSound number: 380 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						0 92
						85 92
						81 103
						60 109
						70 115
						70 136
						140 179
						177 184
						251 184
						293 152
						232 110
						141 96
						133 87
						178 80
						178 76
						227 76
						263 69
						287 71
						319 53
						319 1
						0 1
					yourself:
				)
		)
		(if (Btst fMeerbatDead)
			(= roomState vineTrap)
		)
		(if (Btst fMeerbatFreed)
			(= roomState savedBat)
		)
		(if (Btst fGotOpalAndFruit)
			(= roomState allDoneHere)
		)
		(holes init:)
		(sky init:)
		(switch roomState
			(firstTime
				(bat1 setPri: 14 init: stopUpd:)
				(bat2 setPri: 14 init: stopUpd:)
				(bat3 setPri: 14 init: stopUpd:)
				(smallBat setPri: 14 init: stopUpd:)
				(mainVine init:)
				(vine1 init: stopUpd:)
				(vine2 init: stopUpd:)
				(vine3 init: stopUpd:)
				(vine4 init: stopUpd:)
				(vine5 init: stopUpd:)
			)
			(vineTrap
				(mainVine init:)
				(ego code: ambushChek)
			)
			(savedBat
				(if (not (Btst fGotOpalAndFruit))
					(stoneRing setPri: 14 init: approachVerbs: V_DO stopUpd:)
				)
				;(self setScript: savedBatEntrance)	;EO: Restored unused script
				(smallBat setPri: 14 init: stopUpd: setScript: thankYou)
			)
		)
		(HandsOff)
		(self setScript: egoEnters)
	)
	
	(method (doit)
		(if
			(and
				(not (== curRoom script))
				(or
					(and
						(not (== (curRoom script?) egoEnters))
						(& (ego onControl: origin) cGREEN)
					)
					(< (ego y?) 65)
				)
			)
			(curRoom setScript: getOut)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fVisitedVines)
		(if gNewList
			(gNewList eachElementDo: #dispose)
		)
		(LoadMany FALSE FORCOUNT JUMP CASTFETCH)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_FLAME
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(V_FORCEBOLT
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(V_LIGHTNING
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(V_DAGGER
					(if (not (curRoom script?))
						(++ thrownDaggers)
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(V_ROCK
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(V_LOOK
					(if (not vinesGone)
						(messager say: N_ROOM V_LOOK)
					else
						(messager say: N_ROOM V_LOOK C_VINES_GONE)
					)
					(return TRUE)
				)
				(V_OPEN
					(messager say: N_CUE V_DOIT C_CANT_OPEN)
				)
				(V_DO
					(if
						(and
							(> ((User curEvent?) y?) 30)
							(== (grabEgo state?) -1)
						)
						(curRoom setScript: getRocks)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance getOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego get: iDaggers thrownDaggers)
				(= seconds 1)
			)
			(1
				(= thrownDaggers 0)
				(globalSound setLoop: 1)
				(curRoom newRoom: 150)
			)
		)
	)
)

(instance thankYou of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(client setCycle: EndLoop self)
			)
			(2
				(client dispose:)
			)
		)
	)
)

(instance savedBatEntrance of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 120 110 self)
			)
			(1
				(messager say: N_CUE V_DOIT C_SAVED_BAT 0 self)
			)
			(2
				(smallBat setCycle: EndLoop self)
			)
			(3
				(smallBat dispose:)
				(self dispose:)
			)
		)
	)
)

(instance blastVine of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= vinesGone TRUE)
				(HandsOff)
				(self setScript: (ScriptID PROJECTILE 0) self register)
			)
			(1
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance throwHook of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iVineFruit)
					(self dispose:)
				)
				(HandsOff)
				(ego setMotion: PolyPath 90 72 self)
			)
			(1
				(ego setMotion: PolyPath 93 89 self)
			)
			(2
				(ego view: 8 setLoop: 4 setCycle: EndLoop self)
				(globalSound number: 721 setLoop: 1 play: 127)
			)
			(3
				(grapThingy
					x: 95
					y: 79
					setLoop: 6
					setScale:
					init:
					setMotion: JumpTo 165 143 self
				)
			)
			(4
				(mainVine cel: 1)
				(mainVine setScript: vinesTwist)
				(ego setCycle: BegLoop)
				(grapThingy setMotion: JumpTo 95 79 self)
				(globalSound number: 511 setLoop: 1 play: 127)
			)
			(5
				(grapThingy dispose:)
				(messager say: N_CUE V_DOIT C_HOOKED_FRUIT 0 self)
			)
			(6
				(ego
					get: iVineFruit
					solvePuzzle: fGetVineFruit 8
					normalize:
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoHacksBat of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr fMeerbatFreed)
				(flutterBackToRock dispose:)
				(ego setMotion: PolyPath 104 178 self)
			)
			(1
				(ego view: 385 loop: 1 cel: 0 setCycle: EndLoop self)
				(globalSound number: 520 setLoop: 1 play: 127)
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(messager say: N_CUE V_DOIT C_KILL_BAT 0 self)
			)
			(4
				(if
					(and
						(== heroType FIGHTER)
						(not (ego has: iDispell))
						(ego code: ambushChek)
						(mainVine init: setCycle: BegLoop)
					)
				)
				(ego normalize:)
				(smallBat dispose:)
				(Bset fMeerbatDead)
				(Bset fCantBePaladin)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance flutterBackToRock of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smallBat loop: 6 cel: 8 setCycle: BegLoop self)
			)
			(1
				(globalSound number: 383 setLoop: 1 play: 127)
				(smallBat loop: 5 setMotion: JumpTo 79 167 self)
				(ego code: egoChek)
			)
			(2
				(smallBat loop: 1 cel: 0)
				(= seconds 30)
			)
			(3
				(globalSound stop:)
				(smallBat loop: 0 setCycle: EndLoop self)
			)
			(4
				(smallBat hide:)
				(self dispose:)
			)
		)
	)
)

(instance smallBatJump of Script
	
	(method (doit)
		(if (== vinesGone TRUE)
			(Bset fMeerbatFreed)
			(if (not (Btst fSaveMeerbat))
				(ego addHonor: 20)
			)
			(ego
				solvePuzzle: fSaveMeerbat 8 (| puzzleFIGHTER puzzlePALADIN)
			)
			(smallBat setCycle: 0)
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 7)
			)
			(1
				(ego code: 0)
				(smallBat loop: 2 setCycle: EndLoop self)
			)
			(2
				(globalSound number: 383 setLoop: 1 play: 127)
				(smallBat
					setLoop: 4
					setCycle: Forward
					setMotion: JumpTo 163 147 self
				)
			)
			(3
				(smallBat setMotion: MoveTo 192 126 self)
			)
			(4
				(mainVine setCycle: Forward)
				(vine4 setPri: (+ (smallBat priority?) 1) setCycle: Forward)
				(smallBat cycleSpeed: 6 loop: 4 setCycle: EndLoop self)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(5
				(HandsOn)
				(globalSound number: 382 setLoop: -1 play: 127)
				(if (> (theGame detailLevel:) 2)
					(smallBat setCycle: Forward)
				else
					(smallBat stopUpd:)
				)
				(= seconds 20)
			)
			(6
				(smallBat setCycle: EndLoop self)
			)
			(7
				(vine4 loop: 5 setCycle: EndLoop self)
			)
			(8
				(mainVine setCycle: 0)
				(smallBat hide:)
				(globalSound stop:)
				(Bset fMeerbatDead)
				(if (not egoGrabbed)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance ambush of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany RES_VIEW 6)
				(HandsOff)
				(vine1 loop: 2 cel: 5 init: setCycle: BegLoop)
				(vine2 loop: 3 cel: 5 init: setCycle: BegLoop)
				(vine3 loop: 2 cel: 5 init: setCycle: BegLoop)
				(vine4 loop: 3 cel: 4 init: setCycle: BegLoop)
				(vine5 loop: 2 cel: 5 init: setCycle: BegLoop)
				(mainVine setLoop: 0 setCycle: Forward)
				(globalSound number: 918 setLoop: 1 play: 127)
				(ego view: 11 setCycle: EndLoop self)
			)
			(1
				(vine1 loop: 0 setCycle: Forward)
				(vine2 loop: 0 setCycle: Forward)
				(vine3 loop: 0 setCycle: Forward)
				(vine4 loop: 0 setCycle: Forward)
				(vine5 loop: 0 setCycle: Forward)
				(ego cycleSpeed: 8 setCycle: ForwardCounter 3 self)
				(globalSound number: 918 setLoop: -1 play: 127)
			)
			(2
				(ego view: 6 setCycle: EndLoop self)
			)
			(3
				(EgoDead C_DEATH_AMBUSH 0 386 EndLoop)
				(self dispose:)
			)
		)
	)
)

(instance chopVines of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= vinesGone TRUE)
				(grabEgo dispose:)
				(ego view: 385 loop: 0 cel: 0 setCycle: EndLoop self)
				(globalSound number: 520 setLoop: 1 play: 127)
			)
			(1
				(mainVine setScript: vinesGoDown)
				(ego setCycle: BegLoop self)
			)
			(2
				(if (not (cast contains: smallBat))
					(ego code: egoChek)
				)
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance vinesGoDown of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= vinesGone TRUE)
				(vine1 loop: 2 cel: 0 setCycle: EndLoop ignoreActors: TRUE)
				(vine2 loop: 3 cel: 0 setCycle: EndLoop ignoreActors: TRUE)
				(vine3 loop: 2 cel: 0 setCycle: EndLoop ignoreActors: TRUE)
				(vine4 loop: 3 cel: 0 setCycle: EndLoop ignoreActors: TRUE)
				(vine5 loop: 2 cel: 0 setCycle: EndLoop ignoreActors: TRUE)
				(mainVine
					loop: 1
					cel: 0
					setCycle: EndLoop self
					ignoreActors: TRUE
				)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(1
				(= seconds 1)
			)
			(2
				(vine1 stopUpd:)
				(vine2 stopUpd:)
				(vine3 stopUpd:)
				(vine4 stopUpd:)
				(vine5 stopUpd:)
				(mainVine stopUpd:)
				(if (Btst fMeerbatFreed)
					(smallBat setScript: flutterBackToRock)
				)
				(self dispose:)
			)
		)
	)
)

(instance grabEgo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Bset fPoisoned)
				(= egoGrabbed TRUE)
				(vine3 setCycle: EndLoop self)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(1
				(messager say: N_CUE V_DOIT C_GRAB_EGO 0 self)
			)
			(2
				(ego
					view: 11
					cycleSpeed: 8
					setCycle: Forward
					setMotion: MoveTo 115 93
				)
				(HandsOn)
				(theIconBar
					disable: ICON_WALK ICON_CAST ICON_ACTIONS ICON_DO
					advanceCurIcon:
				)
				(vine3 setCycle: Forward)
				(mainVine setCycle: Forward)
				(globalSound number: 918 setLoop: 1 play: 127)
				(= seconds 1)
			)
			(3
				(if (<= [egoStats HEALTH] 1)
					(EgoDead C_DEATH_VINES 0 386 EndLoop)
				)
				(ego takeDamage: 1)
				(= seconds 1)
			)
			(4
				(-= state 2)
				(self cue:)
			)
		)
	)
)

(instance egoEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 80 self)
			)
			(1
				(= cycles 10)
			)
			(2
				(if (not (Btst fVisitedVines))
					(messager say: N_CUE V_DOIT C_FIRST_ENTRY 0 self)
				else
					(self cue:)
				)
			)
			(3
				(if (Btst fSenseDanger)
					(messager say: N_CUE V_DOIT C_SENSE_DANGER 0 self)
				else
					(self cue:)
				)
			)
			(4
				(cond 
					((== roomState savedBat)
						(ego setScript: savedBatEntrance)
						(HandsOn)
					)
					((and (Btst fMeerbatDead) (== heroType FIGHTER) (not (ego has: iDispell)))
						(ego code: ambushChek)
						(HandsOn)
					)
					((and (not (Btst fMeerbatDead)) (not (Btst fMeerbatFreed)))
						(bat1 setScript: bat1Hoark)
						(globalSound number: 381 setLoop: 1 play: 127)
						(ego code: egoChek)
					)
					(else
						(HandsOn)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance vinesTwist of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mainVine setCycle: EndLoop)
				(if (cast contains: vine1)
					(vine1 setCycle: EndLoop)
					(vine2 setCycle: EndLoop)
					(vine3 setCycle: EndLoop)
					(vine4 setCycle: EndLoop)
					(vine5 setCycle: EndLoop self)
				)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(1
				(mainVine setCycle: BegLoop)
				(if (cast contains: vine1)
					(vine1 setCycle: BegLoop)
					(vine2 setCycle: BegLoop)
					(vine3 setCycle: BegLoop)
					(vine5 setCycle: BegLoop)
				)
				(globalSound number: 918 setLoop: 1 play: 127)
				(= seconds 2)
			)
			(2
				(vine1 stopUpd:)
				(vine2 stopUpd:)
				(vine3 stopUpd:)
				(vine4 stopUpd:)
				(vine5 stopUpd:)
				(mainVine stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance bat1Hoark of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bat1 moveSpeed: 1 setLoop: 2 setCycle: EndLoop self)
			)
			(1
				(globalSound number: 383 setLoop: 1 play: 127)
				(bat1 setLoop: 4 setCycle: ForwardCounter 3 self)
			)
			(2
				(bat1 setCycle: EndLoop self)
			)
			(3
				(globalSound number: 383 setLoop: 1 play: 127)
				(bat1 setMotion: JumpTo 169 132 self)
			)
			(4
				(bat1 setCycle: BegLoop self)
			)
			(5
				(globalSound number: 383 setLoop: 1 play: 127)
				(mainVine setScript: vinesTwist)
				(bat1 setCycle: Forward setMotion: MoveTo 214 105 self)
			)
			(6
				(bat1 setMotion: MoveTo 360 110 self)
			)
			(7
				(globalSound number: 383 setLoop: 1 play: 127)
				(bat1 setLoop: 5 setMotion: JumpTo 42 160 self)
			)
			(8
				(bat1 setLoop: 2 setCycle: BegLoop self)
			)
			(9
				(globalSound number: 381 setLoop: 1 play: 127)
				(bat1 setLoop: 0 setCycle: 0 stopUpd:)
				(HandsOn)
				(if
					(and
						(Btst fVisitedVines)
						(not (Btst fMeerbatFreed))
						(not (Btst fMeerbatDead))
						(not (== vinesGone TRUE))
					)
					(smallBat setScript: smallBatJump)
				)
				(self dispose:)
			)
		)
	)
)

(instance batsComeOut of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(bat1 show: setCycle: BegLoop self)
			)
			(1
				(bat2 show: setCycle: BegLoop self)
			)
			(2
				(if (and (cast contains: smallBat) (not (Btst fMeerbatFreed)))
					(smallBat show: setCycle: BegLoop self)
				else
					(self cue:)
				)
			)
			(3
				(bat3 show: setCycle: BegLoop self)
			)
			(4
				(globalSound number: 381 setLoop: 1 play: 127)
				(= local1 0)
				(= seconds 2)
			)
			(5
				(bat1 stopUpd:)
				(bat2 stopUpd:)
				(bat3 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance theyHide of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 381 setLoop: 1 play: 127)
				(bat1 setCycle: EndLoop self)
			)
			(1
				(bat1 hide:)
				(if
					(and
						(cast contains: smallBat)
						(not (== (smallBat script?) flutterBackToRock))
						(not (Btst fMeerbatFreed))
					)
					(smallBat setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(if
					(and
						(cast contains: smallBat)
						(not (== (smallBat script?) flutterBackToRock))
						(not (Btst fMeerbatFreed))
					)
					(smallBat hide:)
				)
				(bat3 setCycle: EndLoop self)
			)
			(3
				(bat3 hide:)
				(bat2 setCycle: EndLoop self)
			)
			(4
				(bat2 hide:)
				(self dispose:)
			)
		)
	)
)

(instance getRocks of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 4
					loop: (mod (ego loop?) 2)
					setCycle: EndLoop self
				)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(messager say: N_CUE V_DOIT C_GET_ROCKS 0 self)
				(ego get: 23 (Random 2 5))
			)
			(2
				(Narrator y: register)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance grapThingy of Actor
	(properties
		view 21
		loop 6
		signal ignrAct
	)
)

(instance stoneRing of Prop
	(properties
		x 95
		y 185
		noun N_STONE_RING
		approachX 109
		approachY 163
		view 384
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(if (== (stoneRing cel?) 0)
						(messager say: N_STONE_RING V_LOOK)
					else
						(messager say: N_STONE_RING V_LOOK C_GOT_REWARD)
					)
					(return 1)
				)
				(V_DO
					(if (not (ego has: iVineFruit))
						(ego
							get: iVineFruit
							solvePuzzle: fGetVineFruit 8
						)
						(if (== (stoneRing cel?) 0)
							(stoneRing setCycle: EndLoop)
						)
					)
					(if (not (ego has: iOpal))
						(ego get: iOpal)
						(if (== (stoneRing cel?) 0)
							(stoneRing setCycle: EndLoop)
						)
					)
					(if (not (Btst fGotOpalAndFruit))
						(messager say: N_STONE_RING V_DO C_GET_OPAL)
					else
						(messager say: N_STONE_RING V_DO C_EMPTY)
					)
					(Bset fGotOpalAndFruit)
					(return TRUE)
				)
				(V_FETCH	;was 26, which is V_DISPEL
					(if (not (curRoom script?))
						(AutoTarget 95 180)
						(curRoom setScript: (ScriptID CASTFETCH 0) 0 mainVine)
					)
				)
				(-82
					(messager say: N_CUE V_DOIT 21)
					(if (not (ego has: iVineFruit))
						(ego solvePuzzle: fGetVineFruit 8 get: iVineFruit)
					)
					(if (not (ego has: iOpal)) (ego get: iOpal))
					(stoneRing setCel: 1)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance smallBat of Actor
	(properties
		x 79
		y 167
		noun N_SMALL_BAT
		view 383
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_HEALPILLS
				(if (< (self distanceTo: ego) 30)
					(messager say: N_CUE V_DOIT C_HEAL_BAT)
					(ego drop: iHealPills 1 addHonor: 20)
				else
					(messager say: N_CUE V_DOIT C_NOT_CLOSE)
				)
			)
			(V_SWORD
				(if
					(and
						(not (ego script?))
						(== (smallBat script?) flutterBackToRock)
					)
					(ego setScript: egoHacksBat)
				)
			)
			(V_DAGGER
				(if
					(and
						(not (ego script?))
						(== (smallBat script?) flutterBackToRock)
					)
					(ego setScript: egoHacksBat)
				else
					(super doVerb: theVerb)
				)
			)
			(V_FINE_DAGGER
				(if
					(and
						(not (ego script?))
						(== (smallBat script?) flutterBackToRock)
					)
					(ego setScript: egoHacksBat)
				)
			)
			(V_HEAL
				(if (Btst fMeerbatFreed)
					(messager say: N_CUE V_DOIT C_HEAL_BAT)
					(/= [egoStats STAMINA] 2)
					(ego addHonor: 20)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance bat3 of Prop
	(properties
		x 118
		y 184
		noun N_BAT
		view 382
		signal ignrAct
	)
)

(instance bat2 of Prop
	(properties
		x 58
		y 182
		noun N_BAT
		view 382
		signal ignrAct
	)
)

(instance bat1 of Actor
	(properties
		x 42
		y 160
		noun N_BAT
		view 382
		signal ignrAct
	)
)

(instance vine1 of TargProp
	(properties
		x 179
		y 160
		noun N_VINE
		view 380
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_DAGGER
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_ROCK
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_DO
				(if (not (curRoom script?))
					(ego setMotion: PolyPath 127 95)
				)
			)
			(V_SWORD
				(ego setMotion: PolyPath 127 95)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine2 of TargProp
	(properties
		x 112
		y 140
		noun N_VINE
		view 380
		cel 8
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(vine1 doVerb: theVerb)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine3 of TargProp
	(properties
		x 115
		y 95
		noun N_VINE
		view 380
		cel 8
		signal ignrAct
	)
	
	(method (doit)
		(if
			(and
				(not vinesGone)
				(not egoGrabbed)
				(< (GetDistance x y (ego x?) (ego y?) perspective) 20)
			)
			(self setScript: grabEgo)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(if (== (self script?) grabEgo)
					(ego setScript: chopVines)
				else
					(vine1 doVerb: theVerb)
				)
			)
			(V_SWORD
				(if (== (self script?) grabEgo)
					(ego setScript: chopVines)
				else
					(ego setMotion: PolyPath 127 95)
				)
			)
			(V_FINE_DAGGER
				(if (== (self script?) grabEgo)
					(ego setScript: chopVines)
				)
			)
			(V_FLAME
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			;NOTE: This case already exists, and will probably never be executed
;;;			(V_DAGGER
;;;				(if (not (curRoom script?))
;;;					(self setScript: blastVine 0 theVerb)
;;;				)
;;;			)
			(V_ROCK
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_DO
				(vine1 doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine4 of TargProp
	(properties
		x 194
		y 115
		noun N_VINE
		view 380
		cel 8
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(vine1 doVerb: theVerb)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine5 of TargProp
	(properties
		x 251
		y 158
		noun N_VINE
		view 380
		cel 8
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(vine1 doVerb: theVerb)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance mainVine of TargProp
	(properties
		x 165
		y 143
		noun N_MAIN_VINE
		view 381
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-82
				(messager say: N_CUE V_DOIT C_FETCH_FRUIT)
				(ego solvePuzzle: fGetVineFruit 8 get: iVineFruit)
				(self setScript: vinesTwist)
			)
			(V_FETCH
				(cond 
					((ego has: iVineFruit)
						(messager say: N_VINE V_DOIT C_DONT_NEED)
					)
					((not (curRoom script?))
						(AutoTarget 165 138)
						(curRoom setScript: (ScriptID CASTFETCH 0) 0 mainVine)
					)
				)
			)
			(V_GRAPNEL
				(cond 
					((ego has: iVineFruit)
						(messager say: N_CUE V_DOIT C_DONT_NEED)
					)
					(
						(and
							(not (vine3 script?))
							(not (curRoom script?))
							(not (ego has: iVineFruit))
						)
						(curRoom setScript: throwHook)
					)
				)
			)
			(V_DAGGER
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_ROCK
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_FLAME
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(V_DO
				(if (not (curRoom script?))
					(ego setMotion: PolyPath 129 95)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance holes of Feature
	(properties
		x 91
		y 172
		noun N_HOLES
		nsTop 155
		nsLeft 53
		nsBottom 189
		nsRight 130
		sightAngle 180
	)
)

(instance sky of Feature
	(properties
		x 159
		y 21
		noun N_SKY
		nsBottom 42
		nsRight 319
		sightAngle 180
	)
)

(instance egoChek of Code
	
	(method (doit)
		(cond 
			(
				(and
					(< (ego x?) 200)
					(> (ego y?) 91)
					(== local1 0)
					(not (bat1 script?))
				)
				(= local1 1)
				(curRoom setScript: theyHide)
			)
			((and (== local1 1) (> (ego x?) 127) (< (ego y?) 91))
				(= local1 0)
				(curRoom setScript: batsComeOut)
			)
		)
	)
)

(instance ambushChek of Code
	
	(method (doit)
		(if
			(and
				(not (mainVine script?))
				(< (ego distanceTo: mainVine) 30)
			)
			(mainVine setScript: ambush)
		)
	)
)
