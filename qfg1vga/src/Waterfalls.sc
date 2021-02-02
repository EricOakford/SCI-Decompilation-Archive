;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
(include game.sh) (include "82.shm")
(use Main)
(use CastFlame)
(use CastOpen)
(use CastDazzle)
(use Procs)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm82 0
)

(local
	theGName
	drinkWaterCount
	rockHitDoor
	rockHitDoorAgain
	seenByHenry
	rockKnockCount
	lookedAtDoor
	local7
	gEgoSignal
	gEgoPriority
	gEgoIllegalBits
	local11
	waterfallCue
	gEgoCycleSpeed
	gEgoMoveSpeed
	[local15 44] = [0 0 130 126 0 1 130 123 0 2 130 119 0 3 130 116 0 4 131 112 0 5 130 109 0 6 130 106 0 7 131 103 0 1 129 100 0 2 129 98 99 99 99 99]
	[local59 44] = [0 2 129 98 0 1 129 100 0 7 131 103 0 6 130 106 0 5 130 109 0 4 131 112 0 3 130 116 0 2 130 119 0 1 130 123 0 0 130 126 99 99 99 99]
)
(procedure (GetFlyingWater bool)
	(if (Btst fClimbedHenryCliff)
		(messager say: N_ROOM NULL C_CANTREACHWATER)
	else
		(curRoom setScript: getWater 0 bool)
	)
)

(instance rm82 of Room
	(properties
		noun N_ROOM
		picture 82
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany RES_VIEW 82 510 81 537 517)
		(LoadMany RES_SCRIPT 991 103)
		(LoadMany RES_SOUND 78 9 10 84 104 (if Night 72 else 71))
		(if (Btst HENRY_SAFE_TP) (Load RES_VIEW 531))
		(super init: &rest)
		(self
			setFeatures:
				onWater
				cliff
				ground
				ledge
				onFall
				theCliff
		)
		;UPGRADE
;;;		(onWater init:)
;;;		(cliff init:)
;;;		(ground init:)
;;;		(ledge init:)
;;;		(onFall init:)
;;;		(theCliff init:)
		
		(rocks init: approachVerbs: V_DO)
		(cSound stop:)
		(smlSplash1 init: setCycle: Forward)
		(smlSplash2 init: setCycle: Forward)
		(smlSplash3 init: setCycle: Forward)
		(smlSplash4 init: setCycle: Forward)
		(smlSplash5 init: setCycle: Forward)
		(smlSplash6 init: setCycle: Forward)
		(bottomSplash init: setCycle: Forward)
		(splashSound
			number: (if Night 72 else 71)
			init:
			play:
		)
		(rm82Sound number: 78 init:)
		(ladder setPri: 1 ignoreActors: init: stopUpd:)
		(NormalEgo)
		(if (== prevRoomNum 83)
			(cond 
				((Btst HENRY_DEADLY_TP)
					(hermitDoor setCel: 0 stopUpd:)
					(self setScript: deadlyTP)
				)
				((Btst HENRY_SAFE_TP)
					(curRoom
						addObstacle:
							(bottomPoly
								init:
									214 149
									214 146
									190 151
									162 153
									79 156
									52 171
									0 160
									0 0
									319 0
									319 189
									83 189
									113 178
									124 167
									198 152
								yourself:
							)
					)
					(hermitDoor setCel: 0 stopUpd:)
					(ego init: posn: 75 165 loop: 2 cel: 1 hide:)
					(self setScript: safeTP)
				)
				(else
					(hermitDoor setLoop: 2 setCel: 5)
					(ego init: posn: 128 69)
					(self
						addObstacle:
							(topPoly
								init:
									105 74
									67 85
									95 82
									108 86
									141 85
									147 82
									185 83
									137 74
								yourself:
							)
						setScript: fromHermit
					)
				)
			)
		else
			(curRoom
				addObstacle:
					(bottomPoly
						init:
							214 149
							214 146
							190 151
							162 153
							79 156
							52 171
							0 160
							0 0
							319 0
							319 189
							83 189
							113 178
							124 167
							198 152
						yourself:
					)
			)
			(self setScript: sEnterFromWest)
		)
		(hermitDoor init: stopUpd:)
	)
	
	(method (doit)
		(if
			(and
				(not (Btst 360))
				(> (Abs (- gameTime theGName)) 2)
			)
			(= theGName gameTime)
			(Palette PALCycle 232 239 -1 240 247 -1 248 254 -1)
		)
		(cond 
			(script)
			((ego script?) 0)
			(
				(and
					(Btst fClimbedHenryCliff)
					(& (ego onControl:) $0002)
					(not (ego script?))
					(not (== (ego script?) goodClimb))
					(not (Btst 276))
				)
				(curRoom setScript: sFallDown)
			)
			((== (ego edgeHit?) 4) (curRoom setScript: walkOut))
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr HENRY_DOOR_OPEN)
		(= disabledActions 0)
		(Bset fBeenIn82)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DETECT
				(if (CastSpell DETMAGIC)
					(self setScript: detectLadder)
				else
					(messager say: N_ROOM 0 C_SPELLNOTWORKING)
				)
			)
			(V_TRIGGER
				(if (CastSpell TRIGGER)
					(Bset HENRY_LADDER_KNOWN)
					(ladder setCycle: EndLoop)
				else
					(messager say: N_ROOM 0 C_SPELLNOTWORKING)
				)
			)
			(V_DAZZLE
				(if (CastSpell DAZZLE)
					(curRoom setScript: dazzleIt)
				else
					(messager say: N_ROOM 0 C_SPELLNOTWORKING)
				)
			)
			(V_OPEN
				(cond 
					((Btst HENRY_DOOR_OPEN) (messager say: N_ROOM V_OPEN C_ALREADYOPEN))
					((CastSpell OPEN)
						(cond 
							((and (Btst fClimbedHenryCliff) (> [egoStats OPEN] 5))
								(messager say: N_ROOM V_OPEN C_OPENFALLDOWN)
								(ego setMotion: MoveTo (ego x?) 60)
								(hermitDoor setScript: openFromBelow)
							)
							((TrySkill MAGIC 45) (hermitDoor setScript: openFromBelow))
						)
					)
					(else (messager say: N_ROOM V_OPEN C_OPENFAIL))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch waterfallCue
			(1
				(if (not lookedAtDoor) (= lookedAtDoor TRUE) (messager say: N_HERMITDOOR V_LOOK C_LOOKDOORAGAIN))
			)
		)
	)
)

(instance onFall of Feature
	(properties
		x 248
		y 70
		noun N_WATERFALL
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_WATERFALL V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance ledge of Feature
	(properties
		x 113
		y 145
		z 72
		noun N_LEDGE
		nsTop 67
		nsLeft 58
		nsBottom 80
		nsRight 168
	)
)

(instance cliff of Feature
	(properties
		x 112
		y 112
		noun N_CLIFF
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((Btst fClimbedHenryCliff)
						(ego setScript: climbDown)
					)
					((Btst HENRY_LADDER_KNOWN)
						(ego setScript: goodClimb)
					)
					((TrySkill CLIMB 30)
						(ego setScript: goodClimb)
					)
					(else
						(ego setScript: badClimb)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onWater of Feature
	(properties
		x 242
		y 173
		noun N_WATER
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(GetFlyingWater 0)
			)
			(V_FLASK
				(if (ego has: iFlyingWater)
					(messager say: N_WATER V_FLASK NULL)
				else
					(GetFlyingWater 1)
				)
			)
			(else 
				(if
					(OneOf theVerb
						V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
						V_FAIRYDUST V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
						V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
						V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
						V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
						V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
						V_VASE V_VEGETABLES
					)
					(messager say: N_WATER 0 C_DONTHAVEFLASK)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance ground of Feature
	(properties
		x 100
		y 160
		noun N_GROUND
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setScript: getRock)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance rocks of Feature
	(properties
		x 150
		y 180
		noun N_ROCKS
		onMeCheck cCYAN
		approachX 149
		approachY 159
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setScript: getRock)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theCliff of Feature
	(properties
		x 50
		y 10
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(cliff doVerb: theVerb)
	)
)

(instance hermitHead of View
	(properties
		view 81
		loop 4
	)
)

(instance bottomSplash of Prop
	(properties
		x 267
		y 149
		noun N_BOTTOMSPLASH
		view 82
		cel 1
		priority pYELLOW
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_BOTTOMSPLASH V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance smlSplash1 of Prop
	(properties
		x 249
		y 83
		noun N_SMLSPLASH1
		view 82
		loop 1
		priority pYELLOW
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SMLSPLASH1 V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance smlSplash2 of Prop
	(properties
		x 261
		y 54
		noun N_SMLSPLASH2
		view 82
		loop 1
		cel 3
		priority pYELLOW
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SMLSPLASH2 V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance smlSplash3 of Prop
	(properties
		x 264
		y 86
		noun N_SMLSPLASH3
		view 82
		loop 1
		cel 3
		priority pYELLOW
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SMLSPLASH3 V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance smlSplash4 of Prop
	(properties
		x 296
		y 53
		noun N_SMLSPLASH4
		view 82
		loop 1
		cel 2
		priority pYELLOW
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SMLSPLASH4 V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance smlSplash5 of Prop
	(properties
		x 266
		y 112
		noun N_SMLSPLASH5
		view 82
		loop 1
		cel 3
		priority pYELLOW
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SMLSPLASH5 V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance smlSplash6 of Prop
	(properties
		x 277
		y 112
		noun N_SMLSPLASH6
		view 82
		loop 1
		cel 3
		priority pYELLOW
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_SMLSPLASH6 V_LOOK))
			(else 
				(onWater doVerb: theVerb &rest)
			)
		)
	)
)

(instance funkyEgo of Prop
	(properties
		priority pYELLOW
		signal $0010
	)
)

(instance ladder of Prop
	(properties
		x 129
		y 150
		noun N_LADDER
		view 82
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst HENRY_LADDER_KNOWN)
					(messager say: N_LADDER V_LOOK 0)
				else
					(messager say: N_LADDER V_LOOK C_DONTSEELADDER)
				)
			)
			(V_DO
				(cond 
					((Btst fClimbedHenryCliff) (ego setScript: climbDown))
					((Btst HENRY_LADDER_KNOWN) (ego setScript: goodClimb))
					(else (messager say: N_LADDER V_DO 0))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hermitDoor of Prop
	(properties
		x 96
		y 42
		z -25
		noun N_HERMITDOOR
		view 82
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_HERMITDOOR V_LOOK 0 1 curRoom)
				(= waterfallCue 1)
			)
			(V_DO
				(if (Btst fClimbedHenryCliff)
					(hermitDoor setScript: knockScript)
					(SolvePuzzle POINTS_KNOCKONHERMITDOOR 1)
				else
					(messager say: N_HERMITDOOR V_DO 0)
				)
			)
			(V_ROCK
				(if (Btst fClimbedHenryCliff)
					(messager say: N_HERMITDOOR V_ROCK 0)
				else
					(ego setScript: throwIt)
				)
			)
			(V_LOCKPICK
				(if (Btst fClimbedHenryCliff)
					(messager say: N_HERMITDOOR V_LOCKPICK 0)
				else
					(messager say: N_HERMITDOOR V_LOCKPICK C_CANTREACHDOOR)
				)
			)
			(V_THIEFKIT
				(if (Btst fClimbedHenryCliff)
					(messager say: N_HERMITDOOR V_THIEFKIT 0)
				else
					(messager say: N_HERMITDOOR V_THIEFKIT C_CANTREACHDOOR)
				)
			)
			(V_FLAME (curRoom setScript: dartIt))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rock of Actor
	(properties
		view 510
		illegalBits $0000
	)
)

(instance hermit of Actor
	(properties
		x 94
		y 60
		view 81
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_TALK)
			(messager say: N_ROOM V_TALK C_HERMITCANTHEAR)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance getWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(if (< (ego x?) 90)
					(ego setMotion: PolyPath 95 150 self)
				else
					(self cue:)
				)
			)
			(2
				(ego setMotion: MoveTo 212 152 self)
			)
			(3
				(ego view: 510 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(if (not (ego has: iFlask))
					(switch drinkWaterCount
						(0
							(messager say: N_ROOM 0 C_DRINKWATER1 1 self)
						)
						(1
							(messager say: N_ROOM 0 C_DRINKWATER2 1 self)
						)
						(else 
							(messager say: N_ROOM 0 C_DRINKWATER3 1 self)
						)
					)
					(++ drinkWaterCount)
				else
					(Bset fHaveFlyingWater)
					(SolvePuzzle POINTS_GETFLYINGWATER 3)
					(messager say: N_ROOM 0 C_FILLFLASK 1 self)
				)
			)
			(5
				(if (not (ego has: iFlask)) 0 else (ego get: iFlyingWater use: iFlask 1))
				(ego setCycle: BegLoop self)
			)
			(6
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(2 (curRoom newRoom: 81))
		)
	)
)

(instance badClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego illegalBits: 0 setMotion: MoveTo 131 152 self)
			)
			(2
				(ego
					view: 517
					setLoop: 0
					cel: 0
					posn: 129 121
					setCycle: Forward
					setMotion: MoveTo 129 106 self
				)
			)
			(3
				(ego setCel: 0 setCycle: 0 setMotion: MoveTo 129 121 self)
			)
			(4
				(messager say: N_ROOM 0 0 1 self)
				(ego setLoop: 3 view: 0 posn: 131 152)
				(NormalEgo)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance climbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 130 86 self)
			)
			(1
				(topPoly dispose:)
				((curRoom obstacles?) delete: topPoly)
				(ego setHeading: 90 self)
			)
			(2
				(ego
					view: 517
					setLoop: 1
					setCel: -1
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
				(= local11 0)
			)
			(3
				(if (== [local59 local11] 99)
					(self changeState: 5)
				else
					(ego
						setLoop: [local59 local11]
						cel: [local59 (++ local11)]
						posn: [local59 (++ local11)] [local59 (++ local11)]
					)
					(++ local11)
					(= cycles 3)
				)
			)
			(4 (self changeState: 3))
			(5
				(ego view: 0 posn: 129 155 setCycle: Walk loop: 3)
				(Bclr fClimbedHenryCliff)
				(= disabledActions 0)
				(if (not (Btst HENRY_LADDER_KNOWN))
					(messager say: N_ROOM 0 C_JUSTASEASY 1 self)
				else
					(self cue:)
				)
			)
			(6
				(curRoom
					addObstacle:
						(bottomPoly
							init:
								214 149
								214 146
								190 151
								162 153
								79 156
								52 171
								0 160
								0 0
								319 0
								319 189
								83 189
								113 178
								124 167
								198 152
							yourself:
						)
				)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance knockScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(Bset fKnockedOnHenryDoor)
				(rm82Sound number: 78 loop: 1 play:)
				(= cycles 5)
			)
			(2
				(rm82Sound play:)
				(= cycles 5)
			)
			(3
				(rm82Sound play:)
				(= cycles 5)
			)
			(4
				(messager say: N_ROOM 0 C_KNOCKKNOCK 0 self)
			)
			(5
				(HandsOn)
				(ego observeControl: 16 2 -32768 illegalBits: -32768)
				(= seconds 3)
			)
			(6 (= seconds 3))
			(7
				(hermitDoor setScript: hermitHits)
			)
		)
	)
)

(instance hermitHits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(if (Btst fKnockedOnHenryDoor)
					(Bclr fKnockedOnHenryDoor)
					(= cycles 15)
				else
					(self cue:)
				)
			)
			(2
				(if (not (ego script?)) (HandsOff))
				(self cue:)
			)
			(3
				(Bset HENRY_DOOR_OPEN)
				(ego illegalBits: 0 ignoreActors: setMotion: 0)
				(client view: 81 loop: 2 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(4
				(HandsOff)
				(cond 
					((ego inRect: 100 62 145 84)
						(rm82Sound number: 104 loop: 1 play:)
						(Bset 274)
						(ego setScript: sFallDown)
						(= seconds 4)
					)
					((ego inRect: 62 62 100 88) (Bset 276) (ego setPri: 0) (= ticks 60))
					(else (= ticks 60))
				)
			)
			(5
				(HandsOff)
				(client setCycle: EndLoop self)
			)
			(6
				(if (Btst 276)
					(rm82Sound number: 104 loop: 1 play:)
					(hermitDoor setPri: (+ (ego priority?) 1))
					(ego view: 82 setLoop: 6 setCel: 0 posn: 86 81)
				)
				(= cycles 5)
			)
			(7 (= cycles 10))
			(8
				(if
				(and (Btst fClimbedHenryCliff) (not (Btst 274)) (not (Btst 276)))
					(messager say: N_ROOM 0 C_COMEONIN 1 self)
					(ego setScript: goOnIn)
					(self dispose:)
				else
					(messager say: N_ROOM 0 C_DONTSEEANYBODY 1 self)
				)
			)
			(9
				(client setLoop: 3 cel: 0 setCycle: EndLoop)
				(= ticks 15)
			)
			(10
				(if (Btst 276)
					(ego
						view: 82
						setLoop: 6
						setPri: (+ (hermitDoor priority?) 1)
						cel: 0
					)
				)
				(self cue:)
			)
			(11
				(Bclr HENRY_DOOR_OPEN)
				(if (Btst 276) (ego setScript: squashed))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(hermit setPri: 15)
				(ego
					illegalBits: 0
					setPri: (- (hermitDoor priority?) 1)
					setMotion: MoveTo 133 81 self
				)
			)
			(2
				(ego setMotion: MoveTo 125 71 self)
			)
			(3
				(hermitHead dispose:)
				(hermitDoor setLoop: 3 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(4
				(ego hide:)
				(hermitDoor setCycle: CycleTo 5 1 self)
			)
			(5
				(rm82Sound number: 84 loop: 1 play:)
				(curRoom newRoom: 83)
			)
		)
	)
)

(instance squashed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1 (ego setCycle: EndLoop self))
			(2 (self cue:))
			(3 (messager say: N_ROOM 0 0 2 self))
			(4
				(if (TakeDamage 10)
					(= cycles 5)
				else
					(EgoDead 94 95 0 0 538)
				)
			)
			(5
				(Bclr 276)
				(ego
					setLoop: 8
					setCel: 3
					ignoreActors: 0
					posn: 100 78
					setCycle: EndLoop self
				)
			)
			(6
				(NormalEgo)
				(HandsOn)
				(= ticks 30)
			)
			(7 (self dispose:))
		)
	)
)

(instance throwIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(if (< (ego x?) 90)
					(ego illegalBits: 0 setMotion: MoveTo 60 183 self)
				else
					(ego illegalBits: 0 setMotion: MoveTo 178 155 self)
				)
			)
			(2
				(ego
					view: 510
					setLoop: (if (< (ego x?) 90) 3 else 2)
					cel: 0
				)
				(= cycles 2)
			)
			(3 (ego setCycle: CycleTo 3 1 self))
			(4
				(rock
					setLoop: 4
					setPri: 15
					setStep: 35 20
					ignoreActors:
					ignoreHorizon:
					setCycle: Forward
					posn:
						(if (< (ego x?) 90)
							(+ (ego x?) 12)
						else
							(- (ego x?) 12)
						)
						(- (ego y?) 37)
					init:
				)
				(ego setCycle: EndLoop self)
			)
			(5
				(if (and (TrySkill THROW 25) (not (Btst HENRY_DOOR_OPEN)))
					(++ rockKnockCount)
					(= rockHitDoor TRUE)
					(rock
						setMotion:
							MoveTo
							(+ (hermitDoor x?) (Random 20 30))
							(+ (hermitDoor y?) (Random 20 30))
							self
					)
				else
					(rock
						setMotion:
							MoveTo
							(if (< (ego x?) 90)
								(+ (hermitDoor x?) (Random 60 85))
							else
								(- (hermitDoor x?) (Random 25 50))
							)
							(- (hermitDoor y?) (Random 20 30))
							self
					)
				)
			)
			(6
				(NormalEgo)
				(ego loop: (if (< (ego x?) 90) 0 else 1))
				(self cue:)
			)
			(7
				(rm82Sound number: 78 loop: 1 play:)
				(rock
					setMotion:
						JumpTo
						(cond 
							((< (ego x?) 90) (if rockHitDoor 40 else 225))
							(rockHitDoor 140)
							(else 0)
						)
						(if (< (ego x?) 90) 145 else 140)
						self
				)
			)
			(8
				(rock dispose:)
				(= cycles 2)
			)
			(9
				(if rockHitDoor
					(if rockHitDoorAgain
						(messager say: N_ROOM 0 C_KNOCKROCK2 1 self)
					else
						(= rockHitDoorAgain TRUE)
						(messager say: N_ROOM 0 C_KNOCKROCK1 1 self)
					)
				else
					(self cue:)
				)
			)
			(10
				(if (not rockHitDoor)
					(messager say: N_ROOM 0 C_ROCKMISSED 1 self)
				else
					(= rockHitDoor FALSE)
					(self cue:)
				)
			)
			(11
				(if (== rockKnockCount 3)
					(= rockKnockCount 0)
					(if seenByHenry
						(messager say: N_ROOM 0 C_ANNOYED)
					else
						(messager say: N_ROOM 0 C_SOMEONETHERE)
					)
					(hermitDoor setScript: answerKnock)
				else
					(HandsOn)
				)
				(ego use: iRock 1 setScript: 0)
			)
		)
	)
)

(instance answerKnock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(hermitDoor view: 82 loop: 2 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(2
				(hermitDoor setCycle: EndLoop self)
				(hermit
					setLoop: 0
					init:
					posn: 120 74
					ignoreActors:
					setCycle: Walk
					setMotion: MoveTo 128 80
				)
			)
			(3
				(if seenByHenry
					(messager say: N_ROOM 0 C_YOUAGAIN 1 self)
				else
					(messager say: N_ROOM 0 C_HELLOTHERE)
					(= seenByHenry 1)
					(ladder setCycle: EndLoop self)
					(Bset HENRY_LADDER_KNOWN)
				)
			)
			(4
				(hermit setLoop: 1 setMotion: MoveTo 118 65 self)
			)
			(5
				(ladder setCycle: BegLoop self)
				(hermit setPri: (- (hermitDoor priority?) 1))
				(hermitDoor setCycle: CycleTo 4 -1 self)
			)
			(6
				(hermitDoor setCycle: BegLoop self)
			)
			(7
				(HandsOn)
				(hermit dispose:)
				(self dispose:)
			)
		)
	)
)

(instance getRock of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 40])
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(= gEgoSignal (ego signal?))
				(= gEgoPriority (ego priority?))
				(= gEgoIllegalBits (ego illegalBits?))
				(ego
					view: 510
					setLoop: (if (== (ego loop?) 0) 0 else 1)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(Message MsgGet 82 N_ROOM 0 0 3 @temp0)
				(Print addText: @temp0 init:)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego get: iRock 10)
				(NormalEgo)
				(HandsOn)
				(ego
					priority: gEgoPriority
					illegalBits: gEgoIllegalBits
					signal: gEgoSignal
				)
				(client setScript: 0)
			)
		)
	)
)

(instance deadlyTP of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					init:
					view: 537
					illegalBits: 0
					posn: 247 44
					setLoop: 0
					hide:
				)
				(rm82Sound number: 28 loop: 1 init: play:)
				(funkyEgo
					view: 81
					setLoop: 5
					cel: 2
					posn: 248 42
					init:
					setCycle: CycleTo 9 1 self
				)
			)
			(2
				(ego init: show:)
				(= cycles 5)
			)
			(3
				(funkyEgo setCycle: EndLoop self)
			)
			(4
				(funkyEgo dispose:)
				(ego setCycle: CycleTo 2 1 self)
			)
			(5
				(ego
					ignoreActors:
					illegalBits: 0
					yStep: 10
					setPri: (- (bottomSplash priority?) 2)
					setMotion: MoveTo 270 156 self
				)
			)
			(6 (EgoDead 164 165 2 0 537)) ;Reversed loop and cel to show correct death icon (alarmed Hero).
		)
	)
)

(instance fromHermit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: PolyPath 144 77 self
				)
			)
			(2
				(hermitDoor setCycle: BegLoop self)
			)
			(3
				(rm82Sound number: 84 loop: 1 play:)
				(Bset fClimbedHenryCliff)
				(ego illegalBits: -32768)
				(= ticks 3)
			)
			(4
				(hermitDoor stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openFromBelow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 2) self)
			)
			(1
				(CastOpen ego)
				(= seconds 5)
			)
			(2
				(HandsOff)
				(Bset HENRY_DOOR_OPEN)
				(hermitDoor view: 82 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(cond 
					((ego inRect: 100 62 145 84)
						(rm82Sound number: 104 loop: 1 play:)
						(Bset 274)
						(ego setScript: sFallDown)
						(= seconds 4)
					)
					((ego inRect: 62 62 100 88) (Bset 276) (ego setPri: 0) (= ticks 60))
					(else (self cue:))
				)
			)
			(4 (messager say: N_ROOM 0 0 4 self))
			(5 (client setCycle: BegLoop self))
			(6
				(Bclr HENRY_DOOR_OPEN)
				(HandsOn)
				(hermitDoor stopUpd: setScript: 0)
			)
		)
	)
)

(instance dazzleIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1 (CastDazzle ego self))
			(2 (messager say: N_ROOM 0 0 5 self))
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance dartIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastFlame 0 self)
			)
			(1 (messager say: N_ROOM 0 0 6 self))
			(2 (self dispose:))
		)
	)
)

(instance detectLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(if (not (Btst HENRY_LADDER_KNOWN))
					(Bset HENRY_LADDER_KNOWN)
					(ladder setCycle: EndLoop self)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(2 (ladder setCycle: BegLoop self))
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance rm82Sound of Sound
	(properties
		number 28
	)
)

(instance splashSound of Sound
	(properties
		flags $ffff
		loop -1
	)
)

(instance goodClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst HENRY_LADDER_KNOWN)) (messager say: N_ROOM 0 C_LOOKTOCLIMB))
				(NormalEgo)
				(ego illegalBits: 0 ignoreActors:)
				(theGame setCursor: waitCursor)
				(ego view: 0 setMotion: PolyPath 129 155 self)
				(= local11 0)
			)
			(1
				(if (== [local15 local11] 99)
					(self changeState: 3)
				else
					(ego
						view: 517
						setLoop: [local15 local11]
						cel: [local15 (++ local11)]
						posn: [local15 (++ local11)] [local15 (++ local11)]
						setCycle: 0
					)
					(++ local11)
					(= ticks 10)
				)
			)
			(2 (self changeState: 1))
			(3
				(bottomPoly dispose:)
				((curRoom obstacles?) delete: bottomPoly)
				(curRoom
					addObstacle:
						(topPoly
							init:
								105 74
								67 85
								95 82
								108 86
								141 85
								147 82
								185 83
								137 74
							yourself:
						)
				)
				(ego
					setLoop: 1
					posn: 130 80
					setCel: -1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(if (not (Btst HENRY_LADDER_KNOWN))
					(messager say: N_ROOM 0 C_THATWASEASY 1 self)
				else
					(self cue:)
				)
			)
			(5
				(= disabledActions
					(| (= disabledActions (| disabledActions ACTION_RUN)) ACTION_SNEAK)
				)
				(if (not (== (ego view?) 0)) (ChangeGait MOVE_WALK 0))
				(= ticks 60)
			)
			(6
				(HandsOn)
				(NormalEgo)
				(theGame setCursor: 942)
				(Bset fClimbedHenryCliff)
				(= cycles 2)
			)
			(7 (self dispose:))
		)
	)
)

(instance bottomPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance topPoly of Polygon
	(properties
		type PContainedAccess
	)
)

(instance sEnterFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(ego init: posn: -15 170 setMotion: MoveTo 40 175 self)
			)
			(2 (NormalEgo) (= cycles 2))
			(3
				(if (not (Btst fBeenIn82))
					(messager say: N_ROOM 0 C_FIRSTENTRY 1 self)
				else
					(= cycles 2)
				)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance sFallDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(topPoly dispose:)
				((curRoom obstacles?) delete: topPoly)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(ego setLoop: 2 setMotion: 0 setCycle: 0)
				(= ticks 10)
			)
			(2
				(curRoom
					addObstacle:
						(bottomPoly
							init:
								214 149
								214 146
								190 151
								162 153
								79 156
								52 171
								0 160
								0 0
								319 0
								319 189
								83 189
								113 178
								124 167
								198 152
							yourself:
						)
				)
				(rm82Sound number: 9 loop: 1 play:)
				(ego view: 82 setLoop: 7 setCel: 0 setCycle: 0)
				(= ticks 20)
			)
			(3
				(ego
					view: 82
					setLoop: 7
					setCel: 1
					posn: (ego x?) (+ (ego y?) 23)
				)
				(= ticks 10)
			)
			(4
				(ego
					view: 82
					setLoop: 7
					setCel: 2
					posn: (ego x?) (+ (ego y?) 21)
				)
				(= ticks 10)
			)
			(5
				(ego
					view: 82
					setLoop: 7
					setCel: 2
					posn: (ego x?) (+ (ego y?) 21)
				)
				(= ticks 10)
			)
			(6
				(ego view: 82 setLoop: 7 posn: (ego x?) (+ (ego y?) 5))
				(rm82Sound number: 10 loop: 1 play: self)
				(ShakeScreen 2)
				(= ticks 30)
			)
			(7
				(ego setLoop: 8 setCel: 2)
				(= ticks 30)
			)
			(8
				(if (not (TakeDamage 10))
					(EgoDead 68 69 2 5 517)
				else
					(ego view: 82 setCel: 3 posn: (+ (ego x?) 5) (ego y?))
					(= ticks 20)
				)
			)
			(9 (ego setCycle: EndLoop self))
			(10
				(NormalEgo)
				(ego posn: (- (ego x?) 2) (+ (ego y?) 4) loop: 2)
				(= ticks 20)
			)
			(11
				(= disabledActions 0)
				(if (Btst 274) (Bclr 274) else (messager say: N_ROOM 0 16))
				(= cycles 2)
			)
			(12
				(if (not (hermitDoor script?)) (HandsOn))
				(Bclr fClimbedHenryCliff)
				(Bclr 276)
				(self dispose:)
			)
		)
	)
)

(instance safeTP of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(funkyEgo
					view: 81
					setLoop: 5
					cel: 2
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					ignoreActors:
					init:
					setCycle: CycleTo 9 1 self
				)
			)
			(2
				(ego init: show:)
				(funkyEgo setCycle: EndLoop self)
			)
			(3
				(funkyEgo dispose:)
				(Bclr fClimbedHenryCliff)
				(Bclr 241)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)
