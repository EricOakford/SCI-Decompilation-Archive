;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh) (include "13.shm")
(use Main)
(use Rest)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastCalm)
(use CastOpen)
(use CastDazzle)
(use Target)
(use Procs)
(use GloryObstacles)
(use PChase)
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
	rm13 0
)

(enum ;ogre chest state
	chestNotKnown
	chestPicked
	chestForced
	chestLocked
	chestEmpty
)


(local
	[ogrePts 14]
	ogreChestState
)
(instance rm13 of Room
	(properties
		noun N_ROOM
		picture 13
		style DISSOLVE
	)
	
	(method (init)
		(if (and (Btst fBeatOgre) (not (Btst fOgreGone)))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							320 0
							320 189
							1 189
							0 186
							196 176
							272 133
							275 97
							250 97
							235 76
							214 70
							207 101
							180 111
							164 109
							121 131
							97 131
							82 124
							81 110
							91 103
							0 102
							0 0
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							319 0
							319 189
							0 189
							0 186
							195 176
							272 133
							275 97
							250 98
							234 76
							214 70
							209 105
							181 113
							122 102
							0 102
						yourself:
					)
			)
		)
		(Load SCRIPT GLORY_OBSTACLES PCHASE)
		(if (Btst fOgreChestKnown)
			(cond 
				((Btst fSearchedOgreChest)
					(= ogreChestState chestEmpty)
				)
				((Btst fOpenedOgreChest)
					(= ogreChestState chestEmpty)
				)
				(else
					(= ogreChestState chestLocked)
				)
			)
		else
			(= ogreChestState 0)
		)
		(ogre posn: 145 110)
		(if (not (Btst fBeenIn13))
			(= ogreHealth MAX_HP_OGRE)
		)
		(if (== prevRoomNum vOgre)
			(if (<= (= ogreHealth monsterHealth) 0)
				(Bset fBeatOgre)
			)
		else
			(= monsterHealth ogreHealth)
		)
		(cond 
			((not (Btst fBeatOgre))
				(LoadMany VIEW vOgre 456 510)
				(Load SCRIPT CHASE)
				(= monsterNum vOgre)
			)
			((> Day ogreDeathDay)
				(Bset fOgreGone)
			)
		)
		(cSound fade:)
		(super init:)
		(features add: cave rocks trees)
		(Bclr fBearFriendly)
		(NormalEgo)
		(ego init:)
		(cond 
			((== prevRoomNum vOgre)
				(if (<= monsterHealth 0)
					(ogre posn: 97 124)
					(ego view: 4 posn: 66 123 loop: 0 cel: 0 setHeading: 90)
					(self setScript: ogreDies)
					(= monsterNum 0)
				else
					(ego
						posn: 170 170
						loop: 1
						setMotion: PolyPath 30 160
						cel: 4
					)
					(|= disabledActions ACTION_REST)
					(ChangeGait MOVE_RUN FALSE)
					(ogre init: posn: 176 123 setScript: ogreVSego)
				)
			)
			((Btst fBeatOgre)
				(if (not (Btst fOgreGone))
					(ogre
						view: 456
						loop: 0
						setCel: 7
						posn: 97 124
						init:
						stopUpd:
					)
					(cond 
						((Btst fOpenedOgreChest)
							(aChest
								setCel: 2
								posn: (+ (ogre x?) 30) (- (ogre y?) 3)
								setPri: 8
								ignoreActors:
								init:
							)
						)
						((Btst fOgreChestKnown)
							(aChest
								setCel: 0
								posn: (+ (ogre x?) 30) (- (ogre y?) 3)
								setPri: 8
								ignoreActors:
								init:
							)
						)
						(else
							(aChest
								setCel: 0
								posn: (+ (ogre x?) 30) (- (ogre y?) 3)
								setPri: 8
								ignoreActors:
								hide:
							)
						)
					)
				)
			)
			(else
				(cond 
					((== ogreDay Day) (if (== ogreTime timeODay)
						(ogre posn: 145 110))
					)
					(
						(>
							(+= ogreHealth (* (- Day ogreDay) 25))
							MAX_HP_OGRE
						)
						(= ogreHealth MAX_HP_OGRE)
					)
				)
				(= monsterHealth ogreHealth)
				(= disabledActions (| disabledActions ACTION_REST))
				(ogre init: setScript: ogreVSego)
			)
		)
		(switch prevRoomNum
			(14
				(ego posn: 227 85 setScript: goOutside)
			)
			(171
				(ego posn: 227 85 setScript: goOutside)
			)
			(else 
				(if (not (== prevRoomNum vOgre))
					(ego posn: 2 140 setScript: goOnIn)
				)
			)
		)
	)
	
	(method (doit)
		(cond 
			((and (== (ego onControl: origin) cCYAN) (< (ego y?) 85))
				(curRoom newRoom: 14)
			)
			((and (<= (ego x?) 10) (not (ego script?)))
				(ego setScript: moveOnOut)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn13)
		(= disabledActions 0)
		(DisposeScript GLORY_OBSTACLES)
		(DisposeScript PCHASE)
		(if (!= newRoomNum vOgre)
			(= monsterNum 0)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM V_LOOK)
			)
			(V_SLEEP
				(if (Btst fBeatOgre)
					(EgoRests 10 1)
				else
					(messager say: N_ROOM V_SLEEP)
				)
			)
			(V_DETECT
				(messager say: N_ROOM V_DETECT)
			)
			(V_DAZZLE
				(cond 
					((Btst fOgreGone)
						(messager say: N_ROOM V_DAZZLE C_OGRE_GONE)
					)
					((Btst fBeatOgre)
						(messager say: N_ROOM V_DAZZLE C_OGRE_DEAD)
					)
					(else
						(ogre setScript: ogreDazzled)
					)
				)
			)
			(V_FLAME
				(if (Btst fBeatOgre)
					(CastDart 0)
				else
					(CastDart ogre)
				)
			)
			(V_CALM
				(cond 
					((Btst fOgreGone)
						(messager say: N_ROOM V_CALM C_OGRE_GONE)
					)
					((Btst fBeatOgre)
						(messager say: N_ROOM V_CALM C_OGRE_DEAD)
					)
					(else (ogre setScript: ogreCalmed))
				)
			)
			(V_OPEN
				(cond 
					((and (Btst fBeatOgre) (not (Btst fOpenedOgreChest)))
						(self setScript: doTheOpen)
					)
					((not (Btst fBeatOgre))
						(messager say: N_ROOM V_OPEN C_OGRE_DEAD)
					)
					((Btst fSearchedOgreChest)
						(messager say: N_ROOM V_OPEN C_ALREADY_LOOTED)
					)
					(else
						(messager say: N_ROOM V_OPEN C_CHEST_FULL)
					)
				)
			)
			(V_FETCH
				(if (not (Btst fOgreGone))
					(messager say: N_ROOM V_FETCH C_OGRE_GONE)
				else
					(messager say: N_ROOM V_FETCH C_NOTHING_TO_FETCH)
				)
			)
			(V_ZAP
				(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
				(if (or (ego has: iDagger) (ego has: iSword))
					(messager say: N_ROOM V_ZAP C_CAST_ZAP)
				else
					(messager say: N_ROOM V_ZAP C_NO_WEAPON)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance cave of Feature
	(properties
		x 50
		y 100
		noun N_CAVE
		onMeCheck cCYAN
	)
)

(instance rocks of Feature
	(properties
		x 190
		y 100
		noun N_ROCKS
		onMeCheck cBLUE
	)
)

(instance trees of Feature
	(properties
		x 90
		y 90
		noun N_TREES
		onMeCheck cGREEN
	)
)

(instance aChest of Prop
	(properties
		noun N_CHEST
		view 456
		loop 1
		cel 1
		signal (| ignrAct skipCheck)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fOpenedOgreChest)
					(if (Btst fSearchedOgreChest)
						(messager say: N_CHEST V_LOOK C_CHEST_OPEN)
					else
						(messager say: N_CHEST V_LOOK C_CHEST_FULL)
					)
				else
					(messager say: N_CHEST V_LOOK C_CHEST_CLOSED)
				)
			)
			(V_DO
				(ego setScript: egoSearch)
			)
			(V_THIEFKIT
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_CHEST V_THIEFKIT C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_CHEST V_THIEFKIT C_OGRE_DEAD)
					)
					((not [egoStats PICK])
						(messager say: N_CHEST V_THIEFKIT C_CANT_UNLOCK)
					)
					((TrySkill PICK 0 lockPickBonus)
						(= ogreChestState chestPicked)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_LOCKPICK
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_CHEST V_LOCKPICK C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_CHEST V_LOCKPICK C_OGRE_DEAD)
					)
					((not [egoStats PICK])
						(messager say: N_CHEST V_LOCKPICK C_CANT_UNLOCK)
					)
					((TrySkill PICK 0 lockPickBonus)
						(= ogreChestState chestPicked)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_BRASSKEY
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_CHEST V_BRASSKEY C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_CHEST V_BRASSKEY C_OGRE_DEAD)
					)
					(else
						(messager say: N_CHEST V_BRASSKEY C_CANT_UNLOCK)
					)
				)
			)
			(V_ROCK
				(if (Btst fOpenedOgreChest)
					(messager say: N_CHEST V_ROCK)
				else
					(= ogreChestState chestForced)
					(ego setScript: egoSearch)
				)
			)
			(V_SWORD
				(if (Btst fOpenedOgreChest)
					(messager say: N_CHEST V_SWORD)
				else
					(= ogreChestState chestForced)
					(ego setScript: egoSearch)
				)
			)
			(V_DAGGER
				(if (Btst fOpenedOgreChest)
					(messager say: N_CHEST V_SWORD)
				else
					(= ogreChestState chestForced)
					(ego setScript: egoSearch)
				)
			)
			(V_CANDELABRA
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_CHEST V_CANDELABRA C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_CHEST V_CANDELABRA C_OGRE_DEAD)
					)
					(else
						(= ogreChestState chestForced)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_CANDLESTICKS	;was 56, but the messages refer to verb 44, which is V_CANDLESTICKS
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_CHEST V_CANDLESTICKS C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_CHEST V_CANDLESTICKS C_OGRE_DEAD)
					)
					(else
						(= ogreChestState chestForced)
						(ego setScript: egoSearch)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ogre of TargActor
	(properties
		noun N_OGRE
		yStep 3
		view vOgre
		loop 1
		cel 6
		cycleSpeed 24
		xStep 5
		moveSpeed 24
		targDeltaX -12
		targDeltaY -5
	)
	
	(method (init)
		(= nightPalette 1455)
		(PalVary PALVARYTARGET 1455)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((Btst fBeatOgre)
						(if (cast contains: aChest)
							(if (Btst fOpenedOgreChest)
								(if (Btst fSearchedOgreChest)
									(messager say: N_OGRE V_LOOK C_ALREADY_LOOTED)
								else
									(messager say: N_OGRE V_LOOK C_CHEST_FULL)
								)
							else
								(messager say: N_OGRE V_LOOK C_OGRE_DEAD)
							)
						else
							(messager say: N_OGRE V_LOOK C_OGRE_DEAD 1)
						)
					)
					((not (Btst fBeatOgre))
						(messager say: N_OGRE V_LOOK C_OGRE_ALIVE 0 self)
					)
				)
			)
			(V_DO
				(if (Btst fBeatOgre)
					(ego setScript: egoSearch)
				else
					(messager say: N_OGRE V_DO C_OGRE_ALIVE)
				)
			)
			(V_RATIONS
				(if (Btst fBeatOgre)
					(messager say: N_OGRE V_RATIONS C_OGRE_DEAD)
				else
					(messager say: N_OGRE V_RATIONS C_FEED_OGRE)
				)
			)
			(V_DAGGER
				(= zapPower 0)
				(cond 
					((Btst fBeatOgre)
						(messager say: N_OGRE V_DAGGER C_ALREADY_DEAD)
					)
					((not (ego has: iDagger))
						(messager say: N_OGRE V_DAGGER)
					)
					((not (Btst fBeatOgre))
						(Face ego ogre)
						(ThrowKnife ogre)
					)
					(else
						(ThrowKnife 0)
					)
				)
			)
			(V_THIEFKIT
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE V_THIEFKIT C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_OGRE V_THIEFKIT C_OGRE_DEAD)
					)
					((not [egoStats PICK])
						(messager say: N_OGRE V_THIEFKIT C_CANT_UNLOCK)
					)
					((TrySkill PICK 0 lockPickBonus)
						(= ogreChestState chestPicked)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_LOCKPICK
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE V_LOCKPICK C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_OGRE V_LOCKPICK C_OGRE_DEAD)
					)
					((not [egoStats PICK])
						(messager say: N_OGRE V_LOCKPICK C_CANT_UNLOCK)
					)
					((TrySkill PICK 0 lockPickBonus)
						(= ogreChestState chestPicked)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_BRASSKEY
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE V_BRASSKEY C_CHEST_OPEN))
					((not (Btst fBeatOgre))
						(messager say: N_OGRE V_BRASSKEY C_OGRE_DEAD)
					)
					(else
						(messager say: N_OGRE V_BRASSKEY)
					)
				)
			)
			(V_ROCK
				(cond 
					((Btst fBeatOgre)
						(messager say: N_OGRE V_DAGGER C_ALREADY_DEAD)
					)
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE V_ROCK)
					)
					((not (Btst fBeatOgre))
						(if (TrySkill THROW 25)
							(ThrowRock ogre)
						else
							(ThrowRock 0)
						)
					)
					(else
						(= ogreChestState chestForced)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_SWORD
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE V_SWORD C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(curRoom newRoom: vOgre)
					)
					(else
						(= ogreChestState chestForced)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_CANDELABRA	;was 54, but messages refer to 42, which is V_CANDELABRA
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE V_CANDELABRA C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_OGRE V_CANDELABRA C_OGRE_DEAD)
					)
					(else
						(= ogreChestState chestForced)
						(ego setScript: egoSearch)
					)
				)
			)
			(55
				(cond 
					((Btst fOpenedOgreChest)
						(messager say: N_OGRE 56 C_CHEST_OPEN)
					)
					((not (Btst fBeatOgre))
						(messager say: N_OGRE 56 C_OGRE_DEAD)
					)
					(else
						(= ogreChestState chestForced)
						(ego setScript: egoSearch)
					)
				)
			)
			(V_DAZZLE
				(cond 
					((Btst fOgreGone)
						(messager say: N_OGRE V_DAZZLE C_OGRE_GONE)
						;case was 1 (C_CHEST_OPEN), but this is a script error. The correct case is now used.
					)
					((Btst fBeatOgre)
						(messager say: N_OGRE V_DAZZLE C_OGRE_DEAD)
					)
					(else
						(ogre setScript: ogreCalmed)
					)
				)
			)
			(V_FLAME
				(if (Btst fBeatOgre)
					(CastDart 0)
				else
					(CastDart ogre)
				)
			)
			(V_CALM
				(cond 
					((Btst fOgreGone)
						(messager say: N_OGRE V_CALM C_OGRE_GONE)
					)
					((Btst fBeatOgre)
						(messager say: N_OGRE V_CALM C_OGRE_DEAD)
					)
					(else
						(ogre setScript: ogreCalmed)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt damage)
		(if (not (Btst fBeatOgre))
			(if (<= (-= monsterHealth damage) 0)
				(Bset fBeatOgre)
				(HandsOff)
				(self setScript: ogreDies)
			else
				(= ogreHealth monsterHealth)
			)
		)
	)
)

(instance ogreVSego of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ogreDay Day)
				(= ogreTime timeODay)
				(= cycles 12)
			)
			(1
				(if (== (ego view?) 6)
					(ogre setCycle: Walk setMotion: PChase ego 20 self)
				else
					(ogre
						setCycle: Walk
						moveSpeed: 16
						cycleSpeed: 16
						setMotion: PChase ego 20 self
					)
				)
			)
			(2
				(if (not (Btst fBeatOgre))
					(HandsOff)
					(messager say: N_ROOM NULL NULL 1 self)
				else
					(self cue:)
				)
			)
			(3
				(HandsOff)
				(if (Btst fBeatOgre)
					(self dispose:)
				)
				(= seconds 2)
			)
			(4
				(cond 
					((IsObject gClient)
						(-- state)
						(= ticks 5)
						(gClient dispose:)
					)
					((not (Btst fBeatOgre))
						(= ogreX (ogre x?))
						(= ogreY (ogre y?))
						(curRoom newRoom: vOgre)
					)
					(else
						(HandsOn)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance ogreDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 30)
			)
			(1
				(if (ego inRect: 72 94 153 132)
					(NormalEgo)
					(theGame setCursor: waitCursor)
					(ego
						setMotion: PolyPath (- (ego x?) 30) (+ (ego y?) 30)
					)
				)
				(= ticks 30)
			)
			(2
				(HandsOff)
				(if (> (ego x?) 105)
					(ego setHeading: 270)
				else
					(ego setHeading: 90)
				)
				(= ogreDeathDay Day)
				(if (not (if (== (ogre x?) 97) (== (ogre y?) 124)))
					(ogre setMotion: PolyPath 97 124 self)
				else
					(self cue:)
				)
			)
			(3
				(ogre setMotion: 0)
				(ogre
					view: 456
					setLoop: 0
					setCel: 0
					illegalBits: 0
					init:
					setPri: 7
					setCycle: CycleTo 3 1 self
				)
			)
			(4
				(ShakeScreen 1)
				(ogre setCel: 4)
				(= ticks 15)
			)
			(5
				(ogre setCel: 5)
				(= ticks 15)
			)
			(6
				(crash number: (SoundFX 66) init: play:)
				(ShakeScreen 1)
				(= [ogrePts 0] 134)
				(= [ogrePts 1] 98)
				(= [ogrePts 2] 158)
				(= [ogrePts 3] 108)
				(= [ogrePts 4] 147)
				(= [ogrePts 5] 117)
				(= [ogrePts 6] 96)
				(= [ogrePts 7] 130)
				(= [ogrePts 8] 81)
				(= [ogrePts 9] 111)
				(= [ogrePts 10] 115)
				(= [ogrePts 11] 103)
				(= [ogrePts 12] 30583)
				(= [ogrePts 13] 0)
				(= seconds 1)
			)
			(7
				(AddMovingObstacle @ogrePts 2)
				(HandsOn)
				(Bset fBeatOgre)
				(= ogreChestState chestNotKnown)
				(= ogreX (ogre x?))
				(= ogreY (ogre y?))
				(ogre stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance egoSearch of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fOgreChestKnown)
				(if (ego inRect: 151 19 222 79)
					(self dispose:)
				else
					(HandsOff)
					(ogre
						illegalBits: 0
						ignoreActors: TRUE
					)
					(ego
						ignoreActors: TRUE
						ignoreControl: cWHITE
						illegalBits: 0
					)
					(self cue:)
				)
			)
			(1
				(if (or (>= (ogre x?) 184) (>= (ogre y?) 157))
					(ego
						setMotion: PolyPath (- (ogre x?) 1) (- (ogre y?) 19) self
					)
				else
					(ego
						setMotion: PolyPath (+ (ogre x?) 41) (+ (ogre y?) 6) self
					)
				)
			)
			(2
				(Face ego ogre)
				(if (or (>= (ogre x?) 184) (>= (ogre y?) 157))
					(ego
						view: 510
						setPri: (+ (ogre priority?) 4)
						setLoop: 0
						setCycle: EndLoop self
					)
				else
					(ego
						view: 510
						setPri: (+ (ogre priority?) 4)
						setLoop: 1
						setCycle: EndLoop self
					)
				)
			)
			(3
				(switch ogreChestState
					(chestNotKnown
						(if
							(and
								(== curRoomNum daggerRoom)
								(or missedDaggers hitDaggers [invDropped iDagger])
							)
							(messager say: N_ROOM NULL C_GET_DAGGERS)
						)
						(= [invDropped iDagger]
							(= hitDaggers (= missedDaggers (= daggerRoom 0)))
						)
						(messager say: N_ROOM NULL C_REVEAL_CHEST 0 self)
						(if (Btst fOpenedOgreChest)
							(= ogreChestState chestEmpty)
						else
							(= ogreChestState chestLocked)
						)
						(if (not (cast contains: aChest))
							(aChest
								setCel: 0
								posn: (+ (ogre x?) 30) (- (ogre y?) 3)
								setPri: (+ (ogre priority?) 1)
								ignoreActors:
								init:
							)
						else
							(aChest show:)
						)
					)
					(chestPicked
						(ego get: iGold 1 get: iSilver 43)
						(messager say: N_ROOM NULL C_CHEST_PICKED 1 self)
						(Bset fOpenedOgreChest)
						(Bset fSearchedOgreChest)
						(= ogreChestState chestEmpty)
						(aChest setCel: 2 stopUpd:)
					)
					(chestForced
						(ego get: iGold 1 get: iSilver 43)
						(messager say: N_ROOM NULL C_CHEST_FORCED 0 self)
						(Bset fOpenedOgreChest)
						(Bset fSearchedOgreChest)
						(aChest setCel: 2 stopUpd:)
						(= ogreChestState chestEmpty)
					)
					(chestLocked
						(messager say: N_ROOM NULL C_CHEST_LOCKED 1 self)
					)
					(chestEmpty
						(if (Btst fSearchedOgreChest)
							(messager say: N_ROOM NULL C_ALREADY_LOOTED 1 self)
						else
							(ego get: iGold 1 get: iSilver 43)
							(messager say: N_ROOM NULL C_CHEST_FORCED 2 self)
							(Bset fSearchedOgreChest)
						)
					)
				)
			)
			(4
				(switch ogreChestState
					(chestNotKnown
						(if
							(and
								(== curRoomNum daggerRoom)
								(or missedDaggers hitDaggers [invDropped iDagger])
							)
							(ego get: iDagger (+ missedDaggers hitDaggers [invDropped iDagger]))
							(self cue:)
						)
					)
					(chestPicked
						(ego get: iGold 1 get: iSilver 43)
						(self cue:)
					)
					(chestForced
						(ego get: iGold 1 get: iSilver 43)
						(self cue:)
					)
					(chestEmpty
						(if (not (Btst fSearchedOgreChest))
							(ego get: iGold 1 get: iSilver 43)
						)
						(self cue:)
					)
					(else
						(self cue:)
					)
				)
			)
			(5
				(ego setPri: (+ (ogre priority?) 4) setCycle: BegLoop self)
			)
			(6
				(NormalEgo)
				(if (and (>= (ogre x?) 184) (>= (ogre y?) 157))
					(ego
						setCycle: Walk
						setPri: (+ (ogre priority?) 4)
						setMotion: MoveTo (- (ego x?) 15) (ego y?) self
					)
				else
					(ego
						setCycle: Walk
						setPri: (+ (ogre priority?) 4)
						setMotion: MoveTo (+ (ego x?) 15) (ego y?) self
					)
				)
			)
			(7
				(Face ego ogre)
				(= seconds 2)
			)
			(8
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance doTheOpen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego ogre)
				(= seconds 4)
			)
			(1
				(CastOpen)
				(= seconds 5)
			)
			(2
				(cond 
					((not (cast contains: aChest))
						(messager say: N_ROOM NULL C_NOTHING_TO_OPEN 1 self)
					)
					((Btst fOgreGone)
						(messager say: N_ROOM NULL C_OGRE_GONE 0 self)
					)
					((Btst fBeatOgre)
						(cond 
							((Btst fOpenedOgreChest)
								(messager say: N_ROOM NULL C_ALREADY_UNLOCKED 0 self)
							)
							((> [egoStats OPEN] 10)
								(aChest setPri: 9 setCycle: EndLoop self)
								(= ogreChestState chestEmpty)
								(Bset fOpenedOgreChest)
							)
							(else (messager say: N_ROOM NULL C_SPELL_TOO_WEAK 1 self))
						)
					)
					(else
						(messager say: N_ROOM NULL C_FEED_OGRE 1 self)
					)
				)
			)
			(3
				(Face ego ogre)
				(= seconds 1)
			)
			(4
				(if (and (> [egoStats OPEN] 10) (Btst fOpenedOgreChest))
					(messager say: N_ROOM NULL C_SPELL_UNLOCKS_CHEST 1 self)
				else
					(self cue:)
				)
			)
			(5
				(HandsOn)
				(if (Btst fOpenedOgreChest)
					(aChest setPri: 9 setCel: 2 stopUpd:)
				)
				(self dispose:)
			)
		)
	)
)

(instance ogreDazzled of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastDazz)
				(ogre setMotion: 0 cel: 2)
				(= seconds 3)
			)
			(1
				(RedrawCast)
				(= seconds 1)
			)
			(2
				(messager say: N_ROOM NULL C_OGRE_DAZZLED 1 self)
			)
			(3
				(= seconds 14)
			)
			(4
				(messager say: N_ROOM NULL C_OGRE_RECOVERS)
				(client setScript: ogreVSego)
			)
		)
	)
)

(instance ogreCalmed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastCalm)
				(= ticks 6)
			)
			(1
				(ogre setMotion: 0 cel: 2)
				(RedrawCast)
				(= seconds 14)
			)
			(2
				(messager say: N_ROOM NULL C_OGRE_RECOVERS)
				(client setScript: ogreVSego)
			)
		)
	)
)

(instance moveOnOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1
				(curRoom newRoom: 12)
			)
		)
	)
)

(instance goOnIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 20 140 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goOutside of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 224 121 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance crash of Sound
	(properties
		priority 14
	)
)
