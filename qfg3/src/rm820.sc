;;; Sierra Script 1.0 - (do not remove this comment)
(script# 820)
(include game.sh) (include "820.shm")
(use Main)
(use Target)
(use EgoDead)
(use GloryTalker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm820 0
	demonaTalker 1
)

(local
	doorSqueaky =  TRUE
	demonsCanHearEgo =  TRUE
	demonsAlerted
	doorUnlocked
	local4
)
(instance rm820 of Room
	(properties
		noun N_ROOM
		picture 820
		vanishingY -20
	)
	
	(method (init)
		(LoadMany RES_VIEW 820 821 822)
		(ego x: 122 y: 41 setScale: 150 normalize:)
		(HandsOn)
		(super init:)
		(cSound number: 820 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						225 36
						218 43
						148 36
						37 70
						32 81
						9 82
						3 92
						3 186
						115 186
						83 173
						57 152
						51 134
						56 115
						68 95
						52 87
						73 74
						88 79
						140 56
						187 62
						236 66
						282 71
						316 58
						316 50
						231 44
						237 38
					yourself:
				)
		)
		(brazier init:)
		(brazier2 init:)
		(brazier3 init:)
		(brazier4 init:)
		(doorWay init:)
		(stairs init:)
		(floor init:)
		(visage init:)
		(flame1 setCycle: Forward init:)
		(flame2 setPri: 6 setCycle: Forward init:)
		(flame3 setPri: 3 setCycle: Forward init:)
		(flame4 setPri: 5 setCycle: Forward init:)
		(door ignoreActors: 0 init:)
		(cond 
			((not (== prevRoomNum 550))
				(frac init: stopUpd:)
				(fric init: stopUpd:)
				(if (== heroType THIEF)
					(ego code: thiefGaitChek)
				else
					(ego code: demonTurnChek)
				)
				(curRoom setScript: egoEnters)
			)
			((and (== prevRoomNum 550) (== battleResult battleEGOLOST))
				(fric init: stopUpd:)
				(curRoom setScript: knockEmDead)
			)
			((and (== prevRoomNum 550) (not (== battleResult battleEGOLOST)))
				(fric
					x: 156
					y: 67
					view: 824
					signal: stopUpdOn
					init:
				)
				(ego
					x: 212
					y: 61
					init:
					solvePuzzle: fBeatFric 7 (| puzzleFIGHTER puzzlePALADIN))
				(curRoom setScript: afterFight)
			)
			(else
				(curRoom setScript: egoEnters)
			)
		)
		(if (and (== heroType PALADIN) (> (ego trySkill: LUCK 100) 0))
			(messager say: N_CUE V_DOIT C_NO_ESCAPE)
		)
	)
	
	(method (dispose)
		(ego code: 0)
		(if gNewList (gNewList eachElementDo: #dispose))
		(UnLoad RES_VIEW 820)
		(UnLoad RES_VIEW 821)
		(UnLoad RES_VIEW 822)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_REST
				(messager say: N_CUE V_DOIT C_CANT_SLEEP)
			)
			(V_CALM
				(self setScript: castCalm)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance closeCombat of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 2)
			)
			(1
				(= monsterHealth 320)
				(= monsterNum vDemon)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance lubeLock of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 230 40 self)
			)
			(1
				(ego solvePuzzle: fLubedDoor 5 puzzleTHIEF)
				(messager say: N_DOOR V_OIL NULL 0 self)
			)
			(2
				(= doorSqueaky FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance afterFight of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(if (== battleResult battleEGOLOST)
					(EgoDead)
				else
					(self cue:)
				)
			)
			(2
				(globalSound number: 931 setLoop: 1 play: 127)
				(= demonsCanHearEgo FALSE)
				(= doorSqueaky FALSE)
				(= doorUnlocked TRUE)
			)
		)
	)
)

(instance demonsDissolve of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 1)
			)
			(1
				(fric dispose:)
				(DrawPic (curRoom picture?) PIXELDISSOLVE)
				(= cycles 4)
			)
			(2
				(= demonsCanHearEgo 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance castCalm of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: (ScriptID CASTAREA 0) self)
			)
			(1
				(messager say: N_ROOM V_CALM NULL 0 self)
			)
			(2
				(= demonsCanHearEgo FALSE)
				(ego solvePuzzle: fCalmDemons 6 2 code: 0)
				(self dispose:)
			)
		)
	)
)

(instance demonsTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fric setCycle: EndLoop self)
			)
			(1
				(messager say: N_FRIC V_DOIT C_DEMONS_TALK 0 self)
			)
			(2
				(fric setCycle: BegLoop self)
			)
			(3
				(frac setCycle: EndLoop self)
			)
			(4
				(frac setCycle: BegLoop)
				(self dispose:)
			)
		)
	)
)

(instance demonsTurn of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_FRIC V_DOIT C_HEAR_NOISE 0 self)
			)
			(1
				(fric loop: 2 setCycle: EndLoop)
				(frac loop: 3 setCycle: EndLoop self)
			)
			(2
				(messager say: N_FRIC V_DOIT C_SEE_EGO 0 self)
			)
			(3
				(= monsterNum vDemon)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance tryToPickLock of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 230 40 self)
			)
			(1
				(if doorUnlocked
					(messager say: N_DOOR V_TOOLKIT C_ALREADY_UNLOCKED 0 self)
				else
					(ego useSkill: PICK 200)
					(= doorUnlocked TRUE)
					(messager say: N_DOOR V_TOOLKIT C_PICK_LOCK)
					(HandsOn)
					(self dispose:)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance castOpenOnDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(AutoTarget 235 20)
				(self setScript: (ScriptID CASTOPEN 0) self)
			)
			(1
				(ego solvePuzzle: fCastOpenOnDoor 4 puzzleWIZARD)
				(messager say: N_DOOR V_OPEN NULL 0 self)
			)
			(2
				(= doorUnlocked TRUE)
				(= doorSqueaky FALSE)
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance hearNoise of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if demonsAlerted
					(messager say: N_FRIC V_DOIT C_ALERTED 0 self)
				else
					(messager say: N_FRIC V_DOIT C_BORED 0 self)
				)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance messWithDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 230 40 self)
			)
			(1
				(cond 
					((== demonsCanHearEgo TRUE)
						(fric setScript: demonsTurn)
					)
					((== doorUnlocked FALSE)
						(messager say: N_DOOR V_DO C_DOOR_LOCKED)
						(HandsOn)
						(self dispose:)
					)
					((== doorSqueaky TRUE)
						(fric setScript: demonsTurn)
					)
					(else
						(if (cast contains: frac)
							(= demonsAlerted FALSE)
							(self setScript: hearNoise self)
						)
						(= cycles 1)
					)
				)
			)
			(2
				(door setCycle: EndLoop self ignoreActors: TRUE)
				(globalSound number: 821 setLoop: 1 play:)
			)
			(3
				(ego setMotion: MoveTo 230 30 self)
			)
			(4
				(if (not local4)
					(ego solvePuzzle: fPickDoorLock 5 puzzleTHIEF)
				)
				(curRoom newRoom: 830)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(secretDoor init: setCycle: EndLoop self)
				(globalSound number: 8 setLoop: 1 play: 127)
			)
			(1
				(ego init: setMotion: MoveTo 129 51 self)
			)
			(2
				(fric setScript: demonsTalk self)
			)
			(3
				(secretDoor setCycle: BegLoop self)
			)
			(4
				(if (Btst fSenseDanger)
					(messager say: N_CUE V_DOIT C_SENSE_DANGER 0 self)
				else
					(self cue:)
				)
			)
			(5
				(secretDoor dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance theyAttack of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_FRIC V_DOIT C_DEMONS_ATTACK 0 self)
			)
			(1
				(= monsterNum vDemon)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance knockEmDead of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 1)
			)
			(1
				(EgoDead)
			)
		)
	)
)

(instance flame1 of Prop
	(properties
		x 20
		y 43
		noun N_FLAME1	;EO: Added back in		
		view 820
		loop 2
		signal ignrAct
		detailLevel 3
	)
)

(instance flame2 of Prop
	(properties
		x 74
		y 53
		noun N_FLAME2	;EO: Added back in
		view 820
		loop 2
		cel 2
		signal ignrAct
		detailLevel 3
	)
)

(instance flame3 of Prop
	(properties
		x 157
		y 33
		noun N_FLAME3	;EO: Added back in
		view 820
		loop 2
		cel 1
		signal ignrAct
		detailLevel 3
	)
)

(instance flame4 of Prop
	(properties
		x 258
		y 44
		noun N_FLAME4	;EO: Added back in
		view 820
		loop 2
		cel 4
		signal ignrAct
		detailLevel 3
	)
)

(instance secretDoor of Prop
	(properties
		x 122
		y 42
		noun N_SECRET_DOOR
		view 820
		signal ignrAct
	)
)

(instance door of Prop
	(properties
		x 222
		y 35
		noun N_DOOR
		view 820
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setScript: messWithDoor)
			)
			(V_OPEN
				(ego setScript: castOpenOnDoor)
			)
			(V_OIL
				(ego setScript: lubeLock)
			)
			(V_TOOLKIT
				(ego setScript: tryToPickLock)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance fric of TargProp
	(properties
		x 154
		y 103
		noun N_FRIC
		view 821
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (== prevRoomNum 550))
					(curRoom setScript: theyAttack)
				else
					(messager say: N_CUE V_DOIT C_FRIC_DEAD)
				)
			)
			(V_DAGGER
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_FLAME
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_ROCK
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt)
		(curRoom setScript: closeCombat)
	)
)

(instance frac of TargProp
	(properties
		x 193
		y 110
		noun N_FRAC
		view 821
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (== prevRoomNum 550))
					(curRoom setScript: theyAttack)
				else
					(messager say: N_CUE V_DOIT C_FRIC_DEAD)
				)
			)
			(V_DAGGER
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_FLAME
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(V_ROCK
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID PROJECTILE 0) 0 theVerb)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt)
		(curRoom setScript: closeCombat)
	)
)

(instance demonaTalker of GloryTalker
	(properties
		x 10
		y 10
		view 822
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 100
		backColor 42
	)
	
	(method (init)
		(super init: demonaBust demonaEyes demonaMouth &rest)
	)
)

(instance demonaMouth of Prop
	(properties
		nsTop 31
		nsLeft 60
		view 822
	)
)

(instance demonaEyes of Prop
	(properties
		nsTop 24
		nsLeft 49
		view 822
		loop 2
	)
)

(instance demonaBust of View
	(properties
		nsTop 14
		nsLeft 53
		view 822
		loop 3
	)
)

(instance brazier of Feature
	(properties
		x 23
		y 58
		noun N_FLAME1
		nsTop 45
		nsLeft 14
		nsBottom 71
		nsRight 33
		sightAngle 180
	)
)

(instance brazier2 of Feature
	(properties
		x 76
		y 64
		noun N_FLAME2
		nsTop 48
		nsLeft 67
		nsBottom 81
		nsRight 86
		sightAngle 180
	)
)

(instance brazier3 of Feature
	(properties
		x 158
		y 42
		noun N_FLAME3
		nsTop 34
		nsLeft 149
		nsBottom 51
		nsRight 168
		sightAngle 180
	)
)

(instance brazier4 of Feature
	(properties
		x 260
		y 56
		noun N_FLAME4
		nsTop 47
		nsLeft 251
		nsBottom 66
		nsRight 269
		sightAngle 180
	)
)

(instance doorWay of Feature
	(properties
		x 229
		y 21
		noun N_DOORWAY
		nsTop 1
		nsLeft 205
		nsBottom 41
		nsRight 254
		sightAngle 180
	)
)

(instance floor of Feature
	(properties
		x 189
		y 155
		noun N_FLOOR
		nsTop 134
		nsLeft 148
		nsBottom 177
		nsRight 230
		sightAngle 180
	)
)

(instance stairs of Feature
	(properties
		x 44
		y 136
		noun N_STAIRS
		nsTop 84
		nsBottom 189
		nsRight 88
		sightAngle 180
	)
)

(instance visage of Feature
	(properties
		x 291
		y 145
		noun N_VISAGE
		nsTop 114
		nsLeft 270
		nsBottom 177
		nsRight 313
		sightAngle 180
	)
)

(instance thiefGaitChek of Code
	
	(method (doit)
		(if (== egoGait MOVE_SNEAK)
			(= demonsCanHearEgo FALSE)
		else
			(= demonsCanHearEgo TRUE)
		)
		(if
			(and
				(not (curRoom script?))
				(== demonsCanHearEgo TRUE)
				(ego inRect: 180 20 200 80)
			)
			(= local4 1)
			(curRoom setScript: demonsTurn)
		)
	)
)

(instance demonTurnChek of Code
	
	(method (doit)
		(if
			(and
				(not (curRoom script?))
				(== demonsCanHearEgo TRUE)
				(or
					(ego inRect: 180 20 200 80)
					(ego inRect: 0 80 135 189)
				)
			)
			(curRoom setScript: demonsTurn)
		)
	)
)
