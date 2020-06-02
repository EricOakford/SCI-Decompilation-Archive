;;; Sierra Script 1.0 - (do not remove this comment)
(script# 97)
(include system.sh) (include sci2.sh)
(include game.sh) (include "97.shm")
(use Main)
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
	rm97 0
	elsa97Talker 1
	yorick97Talker 2
)

(local
	local0
	tookPotions
	leaderOnScreen
	elsaOnScreen
	yorickOnScreen
	oldSortedFeatures
	wrongMove
)
(instance rm97 of Room
	(properties
		noun N_ROOM
		picture 97
	)
	
	(method (init)
		(LoadMany RES_VIEW 97 465 503)
		(LoadMany RES_SOUND 88 66)
		(cSound fade:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 93
						212 107
						220 128
						220 131
						100 131
						105 110
						90 109
						49 158
						223 163
						236 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189
						282 189
						232 117
						319 99
					yourself:
				)
		)
		(super init:)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures 0)
		(self setFeatures: desk rug)
		(SolvePuzzle f97EnterLeaderRoom 12)
		(door init: ignoreActors: 1 stopUpd:)
		(goldChest addToPic:)
		(torchL setCycle: Forward init:)
		(theEyes
			setCycle: Forward
			init:
			setPri: 1
			setScript: blinkScript
		)
		(liteOffOrnament setCycle: Forward init: ignoreActors: 1)
		(liteOffDragon setCycle: Forward init: ignoreActors: 1)
		(yorick init:)
		(potion1 init: stopUpd:)
		(potion2 init: stopUpd:)
		(mirror ignoreActors: init: stopUpd:)
		(switch prevRoomNum
			(172
				(HandsOff)
				(door setCel: 0)
				(ego init: loop: 1 posn: 204 154)
				(potion1
					approachVerbs:
						4
						34
						42
						44
						46
						16
						38
						21
						36
						39
						32
						29
						37
						22
						26
						14
						17
						27
						23
						31
						30
						40
						43
						45
						53
						11
						28
						20
						35
						15
						10
						24
						12
						18
						19
						47
						41
						33
				)
				(potion2
					approachVerbs:
						4
						34
						42
						44
						46
						16
						38
						21
						36
						39
						32
						29
						37
						22
						26
						14
						17
						27
						23
						31
						30
						40
						43
						45
						53
						11
						28
						20
						35
						15
						10
						24
						12
						18
						19
						47
						41
						33
				)
				(mirror
					approachVerbs:
						4
						34
						42
						44
						46
						16
						38
						21
						36
						39
						32
						29
						37
						22
						26
						14
						17
						27
						23
						31
						30
						40
						43
						45
						53
						11
						28
						20
						35
						15
						10
						24
						12
						18
						19
						47
						41
						33
				)
				(ChangeGait MOVE_WALK 0)
				(NormalEgo)
				(= elsaOnScreen TRUE)
				(leader
					init:
					setLoop: 5
					cel: 0
					setPri: 9
					posn: 151 155
					setScript: elsaIsBack
				)
			)
			(else 
				(= leaderOnScreen TRUE)
				(liteOffBird setCycle: Forward init: ignoreActors: 1)
				(liteOffShield setCycle: Forward init: ignoreActors: 1)
				(liteOffChest setCycle: Forward init: ignoreActors: 1)
				(gemChest1 setCycle: Forward init:)
				(gemChest2 setCycle: Forward init:)
				(ego init: hide: actions: egoActions setScript: egoEnters)
				(leader
					init:
					setPri: 12
					illegalBits: 0
					setScript: leaderVaults
				)
				(elsaSong init:)
			)
		)
	)
	
	(method (doit)
		(if (== (ego onControl: 1) 128)
			(if (ego has: iMagicMirror)
				(curRoom newRoom: 84)	;if ego has mirror, go to Antwerp area
			else
				(curRoom newRoom: 600) ;otherwise, go to the end of the game
			)
		)
		(if
			(and
				wrongMove
				(== (leader script?) leaderVaults)
				(< (leaderVaults state?) 7)
			)
			(leaderVaults changeState: 7)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn97)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAZZLE
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_ZAP
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_DETECT
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_TRIGGER
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_CALM
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_OPEN
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_FETCH
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_DISENCHANT
				(if leaderOnScreen
					(HandsOff)
					(ego setScript: spilledIt)
					(leader setScript: 0)
					(leaderVaults dispose:)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance potion1 of View
	(properties
		x 118
		y 111
		noun N_POTION
		approachX 132
		approachY 141
		view 97
		loop 11
		cel 1
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(= nightPalette 197)
		(PalVary PALVARYTARGET 197)
		(kernel_128 97)
		(super init:)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DO
				(if leaderOnScreen
					(= wrongMove TRUE)
				else
					(ego setScript: getPotions)
				)
			)
			(V_LOOK (messager say: N_POTION V_LOOK))
			(V_DISENCHANT
				(Bset fSavedElsa)
				(ego use: iDisenchant)
				(SolvePuzzle f172DispelLeader 35)
				(leader setScript: leaderChanges)
			)
			(V_DAGGER
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_ROCK
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_SWORD
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance potion2 of View
	(properties
		x 122
		y 110
		noun N_POTION
		approachX 132
		approachY 141
		view 97
		loop 11
		cel 1
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb param2)
		(potion1 doVerb: theVerb param2)
	)
)

(instance mirror of View
	(properties
		x 134
		y 112
		noun N_MIRROR
		approachX 155
		approachY 141
		view 97
		loop 11
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DISENCHANT
				(if leaderOnScreen
					(HandsOff)
					(ego setScript: spilledIt)
					(leader setScript: 0)
					(leaderVaults dispose:)
				else
					(super doVerb: theVerb)
				)
			)
			(V_DO
				(if leaderOnScreen (= wrongMove TRUE) else (ego setScript: getMirror))
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance theEyes of Prop
	(properties
		x 166
		y 62
		view 97
		loop 1
		cel 3
		detailLevel 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance torchL of Prop
	(properties
		x 227
		y 55
		noun N_TORCH
		view 97
		loop 2
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance liteOffOrnament of Prop
	(properties
		x 245
		y 88
		noun N_ORNAMENT
		view 98
		loop 1
		cel 2
		priority 7
		signal $0010
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance liteOffBird of Prop
	(properties
		x 72
		y 72
		noun N_BIRD
		view 98
		loop 2
		cel 1
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance liteOffChest of Prop
	(properties
		x 191
		y 89
		noun N_CHEST
		view 98
		loop 3
		cel 2
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance liteOffDragon of Prop
	(properties
		x 156
		y 78
		noun N_DRAGON
		view 98
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance liteOffShield of Prop
	(properties
		x 100
		y 48
		noun N_SHIELD
		view 98
		loop 4
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance door of Prop
	(properties
		x 235
		y 189
		noun N_DOOR
		view 97
		loop 8
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance goldChest of View
	(properties
		x 189
		y 105
		noun N_GOLDCHEST
		view 97
		cel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance gemChest1 of Prop
	(properties
		x 263
		y 133
		noun N_GEMCHEST1
		view 97
		loop 9
		detailLevel 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance gemChest2 of Prop
	(properties
		x 102
		y 177
		noun N_GEMCHEST2
		view 97
		loop 10
		cel 2
		priority 15
		signal $0010
		detailLevel 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance leader of Actor
	(properties
		x 148
		y 123
		noun N_ELSA
		view 97
		loop 4
		priority 9
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					(leaderOnScreen (messager say: N_ELSA V_LOOK))
					(elsaOnScreen (messager say: N_ELSA V_LOOK C_LOOKELSA 0))
					(else (messager say: N_ELSA V_LOOK C_NOTIMETOTALK))
				)
			)
			(V_DISENCHANT
				(Bset fSavedElsa)
				(ego use: iDisenchant)
				(SolvePuzzle f172DispelLeader 35)
				(self setScript: leaderChanges)
			)
			(V_FLAME
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_FETCH
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_DAZZLE
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_ZAP
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_DETECT
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_TRIGGER
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_CALM
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_OPEN
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_DAGGER
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_ROCK
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_SWORD
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
			(V_TALK
				(if leaderOnScreen
					(messager say: N_ELSA V_TALK)
				else
					(messager say: N_ELSA V_TALK C_NOTIMETOTALK)
				)
			)
			(else 
				(if leaderOnScreen (= wrongMove TRUE) else (super doVerb: theVerb))
			)
		)
	)
)

(instance yorick of Actor
	(properties
		x 276
		y 114
		noun N_YORICK
		view 97
		loop 6
	)
)

(instance brig1 of Actor
	(properties
		view 465
	)
)

(instance brig2 of Actor
	(properties
		view 465
	)
)

(instance brig3 of Actor
	(properties
		view 465
	)
)

(instance desk of Feature
	(properties
		x 169
		y 130
		z 13
		noun N_DESK
		nsTop 107
		nsLeft 129
		nsBottom 127
		nsRight 209
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DO
				(if (ego inRect: 100 108 233 145)
					(cond 
						((and (ego has: iMagicMirror) tookPotions) (messager say: N_DESK V_DO C_NOTHINGONDESK))
						(tookPotions (messager say: N_DESK V_DO C_MIRRORONDESK))
						((ego has: iMagicMirror) (messager say: N_DESK V_DO C_POTIONSONDESK))
						(else (messager say: N_DESK V_DO))
					)
				else
					(messager say: N_DESK V_DO C_NOTCLOSEENOUGH)
				)
			)
			(V_DISENCHANT
				(if leaderOnScreen
					(HandsOff)
					(ego setScript: spilledIt)
					(leader setScript: 0)
					(leaderVaults dispose:)
				else
					(super doVerb: theVerb)
				)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance rug of Feature
	(properties
		x 10
		y 160
		noun N_RUG
		nsBottom 189
		nsRight 319
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DISENCHANT)
			(if leaderOnScreen
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
			else
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance elsaSong of Sound
	(properties
		number 88
		priority 1
	)
)

(instance tromp of Sound
	(properties
		number 66
		priority 3
	)
)

(instance egoActions of Actions
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_DISENCHANT)
				(HandsOff)
				(ego setScript: spilledIt)
				(leader setScript: 0)
				(leaderVaults dispose:)
				(return TRUE)
			else
				(HandsOff)
				(= wrongMove TRUE)
				(return FALSE)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ChangeGait MOVE_WALK 0)
				(NormalEgo)
				(door setCycle: EndLoop self)
			)
			(1
				(ego
					posn: 261 240
					show:
					illegalBits: 0
					setMotion: MoveTo 261 176 self
				)
				(messager say: N_ROOM 0 C_CONFRONTLEADER 1)
			)
			(2
				(ego setMotion: MoveTo 227 160 self)
			)
			(3
				(leaderVaults cue:)
				(ego setMotion: MoveTo 198 143 self)
				(door setCycle: BegLoop)
			)
			(4 (NormalEgo 1) (self dispose:))
		)
	)
)

(instance getPotions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego get: iHealingPotion 2) ;merged duplicate entries into one
;				(ego get: iHealingPotion)
				(= tookPotions TRUE)
				(potion1 hide:)
				(potion2 hide:)
				(potion1 dispose:)
				(potion2 dispose:)
				(self cue:)
			)
			(1
				(messager say: N_POTION V_DO 0 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance getMirror of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego get: iMagicMirror)
				(mirror hide:)
				(mirror dispose:)
				(self cue:)
			)
			(1
				(messager say: N_MIRROR V_DO 0 0 self)
				(SolvePuzzle POINTS_TAKEMAGICMIRROR 10)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance spilledIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or (!= (ego x?) 198) (!= (ego y?) 143))
					(ego setMotion: PolyPath 198 143 self)
					(messager say: N_ROOM V_DISENCHANT 0 1)
				else
					(messager say: N_ROOM V_DISENCHANT 0 1 self)
				)
			)
			(1
				(ego setLoop: 1)
				(if (== (leader cel?) 7)
					(self changeState: 5)
				else
					(= ticks 60)
				)
			)
			(2
				(if (== (leader cel?) 7)
					(self changeState: 5)
				else
					(= ticks 90)
				)
			)
			(3
				(if (== (leader cel?) 7)
					(self changeState: 5)
				else
					(= ticks 90)
				)
			)
			(4
				(if (== (leader cel?) 7)
					(self changeState: 5)
				else
					(= ticks 90)
				)
			)
			(5
				(ego setMotion: 0 setCycle: 0)
				(messager say: N_ROOM V_DISENCHANT 0 2 self)
			)
			(6
				(client setScript: elsaKillsEgo)
			)
		)
	)
)

(instance leaderChanges of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ELSA V_DISENCHANT 0 1 self)
			)
			(1 (curRoom newRoom: 172))
		)
	)
)

(instance leaderVaults of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (leader setLoop: 4 cel: 0))
			(1
				(elsaSong play:)
				(= ticks 90)
			)
			(2
				(leader setCycle: EndLoop self)
				(if (!= (ego script?) spilledIt)
					(HandsOn)
					(theIconBar disable: 1)
				)
			)
			(3
				(if (or (Btst fSavedElsa) (== wrongMove TRUE))
					(self changeState: 7)
				else
					(= ticks (+ 60 (* 60 (- 3 howFast))))
				)
			)
			(4
				(if (or (Btst fSavedElsa) (== wrongMove TRUE))
					(self changeState: 7)
				else
					(= ticks (+ 400 (* 300 (- 3 howFast))))
				)
			)
			(5
				(if (or (Btst fSavedElsa) (== wrongMove TRUE))
					(self changeState: 7)
				else
					(messager say: N_ROOM 0 9 2 self)
				)
			)
			(6
				(if (or (Btst fSavedElsa) (== wrongMove TRUE))
					(self changeState: 7)
				else
					(= ticks (+ 50 (* 150 (- 4 howFast))))
				)
			)
			(7
				(if (Btst fSavedElsa)
					(client setScript: leaderChanges)
				else
					(HandsOff)
					(self cue:)
				)
			)
			(8
				(client setScript: elsaKillsEgo)
			)
		)
	)
)

(instance elsaKillsEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leader
					setLoop: 3
					cycleSpeed: 1
					setPri: 9
					setCycle: EndLoop self
				)
				(ego setMotion: 0 setCycle: 0)
			)
			(1 (= ticks 36))
			(2
				(leader cycleSpeed: 1 setCycle: BegLoop self)
			)
			(3
				(ego view: 503 setLoop: 0 setCel: 0 cycleSpeed: 4 y: 141)
				(= ticks 60)
			)
			(4 (ego setCycle: EndLoop self))
			(5
				(yorick
					setLoop: 6
					cel: 0
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 230 114 self
				)
			)
			(6
				(messager say: N_ROOM 0 C_KILLED 0 self)
			)
			(7 (EgoDead 62 63))
		)
	)
)

(instance blinkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 100 300)))
			(1
				(theEyes setCycle: Forward)
				(= ticks (Random 12 72))
			)
			(2 (theEyes setCycle: BegLoop self))
			(3 (self changeState: 0))
		)
	)
)

(instance elsaIsBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ChangeTheCursor 3)
				(= ticks 120)
			)
			(1
				(messager say: N_ROOM 0 C_ELSAISBACK 1 self)
			)
			(2
				(messager say: N_ROOM 0 C_ELSAISBACK 2 self)
			)
			(3
				(messager say: N_ROOM 0 C_ELSAISBACK 3 self)
			)
			(4
				(messager say: N_ROOM 0 C_ELSAISBACK 4 self)
			)
			(5
				(messager say: N_ROOM 0 C_ELSAISBACK 5 self)
			)
			(6
				(yorick
					setLoop: 6
					cel: 0
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 230 114 self
				)
				(= yorickOnScreen TRUE)
			)
			(7
				(messager say: N_ROOM 0 C_ELSAISBACK 6 self)
			)
			(8
				(messager say: N_ROOM 0 C_ELSAISBACK 7 self)
			)
			(9
				(messager say: N_ROOM 0 C_ELSAISBACK 8 self)
			)
			(10
				(messager say: N_ROOM 0 C_ELSAISBACK 9 self)
			)
			(11
				(messager say: N_ROOM 0 C_ELSAISBACK 10 self)
			)
			(12
				(messager say: N_ROOM 0 C_ELSAISBACK 11 self)
			)
			(13
				(messager say: N_ROOM 0 C_ELSAISBACK 12 self)
			)
			(14
				(tromp number: (SoundFX 66) init: loop: 4 play:)
				(messager say: N_ROOM 0 C_ELSAISBACK 13 self)
			)
			(15
				(leader setLoop: 5 cel: 0 setCycle: EndLoop self)
				(= elsaOnScreen FALSE)
				(liteOffBird setCycle: Forward init: ignoreActors: 1)
				(liteOffShield setCycle: Forward init: ignoreActors: 1)
				(liteOffChest setCycle: Forward init: ignoreActors: 1)
				(gemChest1 setCycle: Forward init:)
				(gemChest2 setCycle: Forward init:)
			)
			(16
				(yorick setLoop: 7 cel: 0 setCycle: EndLoop self)
				(HandsOn)
				(ChangeTheCursor 3)
				(tromp number: (SoundFX 66) loop: 4 play:)
				(= yorickOnScreen FALSE)
			)
			(17
				(messager say: N_ROOM 0 C_ELSAISBACK 14 self)
			)
			(18
				(yorick dispose:)
				(ego ignoreActors: 1)
				(tromp loop: 6 play:)
				(= ticks (* 800 (- 5 howFast)))
			)
			(19
				(tromp loop: 6 play:)
				(= ticks (* 700 (- 5 howFast)))
			)
			(20
				(HandsOff)
				(if (and (> (ego x?) 280) (< (ego y?) 130))
					(ego setMotion: MoveTo 340 (ego y?))
				else
					(self cue:)
				)
			)
			(21
				(door setCycle: EndLoop)
				(brig1
					init:
					setCycle: Walk
					posn: 259 230
					setMotion: MoveTo 259 190
				)
				(brig2
					init:
					setCycle: Walk
					posn: 265 240
					setMotion: MoveTo 265 199
				)
				(brig3
					init:
					setCycle: Walk
					posn: 259 255
					setMotion: MoveTo 259 214 self
				)
			)
			(22 (EgoDead 60 61))
		)
	)
)

(instance elsa97Talker of Talker
	(properties
		x 10
		y 10
		view 1097
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2097)
		(PalVary PALVARYTARGET 2097)
		(kernel_128 1097)
		(= font userFont)
		(self modeless: 0)
		(super init: elsaBust elsaEye elsaMouth &rest)
	)
)

(instance elsaBust of Prop
	(properties
		view 1097
	)
)

(instance elsaMouth of Prop
	(properties
		nsTop 50
		nsLeft 61
		view 1097
		loop 1
	)
)

(instance elsaEye of Prop
	(properties
		nsTop 32
		nsLeft 53
		view 1097
		loop 2
	)
)

(instance yorick97Talker of Talker
	(properties
		x 186
		y 10
		view 1096
		talkWidth 260
		textX -161
		textY 110
	)
	
	(method (init)
		(= font userFont)
		(= nightPalette 2096)
		(PalVary PALVARYTARGET 2096)
		(kernel_128 1096)
		(self modeless: 0)
		(super init: yorickBust yorickEye yorickMouth &rest)
	)
)

(instance yorickBust of Prop
	(properties
		view 1096
		cel 2
	)
)

(instance yorickMouth of Prop
	(properties
		nsTop 39
		nsLeft 34
		view 1096
		loop 1
	)
)

(instance yorickEye of Prop
	(properties
		nsTop 26
		nsLeft 33
		view 1096
		loop 2
	)
)
