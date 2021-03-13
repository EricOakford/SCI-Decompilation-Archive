;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include game.sh) (include "800.shm")
(use Main)
(use TellerIcon)
(use EgoDead)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm800 0
)

(local
	numDeadApemen
	toldAboutDoor
	local2 = [0 -2 11 999]
	[local6 2]
	local8 = [0 6 -7 9 10 999]
	local14 = [0 3 8 999]
	local18 = [0 -7 999]
	[local21 3]
)
(instance rm800 of Room
	(properties
		noun N_ROOM
		picture 800
	)
	
	(method (init)
		(= [local6 0] @local2)
		(= [local21 0] @local8)
		(= [local21 1] @local14)
		(ego setScale: 189 x: -10 y: 182 noun: 2 init: normalize:)
		(egoTell init: ego @local2 @local6)
		(manuTell
			init: (ScriptID MONkEY_TALKER 1) @local8 @local21 @local18
		)
		(HandsOn)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						136 189
						148 184
						109 184
						132 161
						193 162
						200 156
						159 159
						140 155
						102 155
						0 167
						0 189
					yourself:
				)
		)
		(super init:)
		(city init:)
		(skyTowers init:)
		(jungle init:)
		(tree init:)
		(walkHandler addToFront: city)
		((ScriptID MONkEY_TALKER 1)
			x: -20
			y: 175
			setScale:
			noun: N_MANU
			ignoreActors: TRUE
			init:
		)
		(if (and (== prevRoomNum 550) (== battleResult battleEGOLOST))
			;EO: Restored unused death message
			(EgoDead C_APEMAN 800)
		)
		(if (== prevRoomNum 550)
			(cSound changeTo: 180)
			(ego x: 104 y: 171)
			((ScriptID MONkEY_TALKER 1) x: 157 y: 167)
		)
		(curRoom setScript: egoEnters)
		(apeManGuard
			init:
			setScale: 250
			setCycle: Walk
			setScript: guardPatrols
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((< (ego x?) 10)
				(curRoom setScript: blockEgo)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany FALSE MONkEY_TALKER)
		(walkHandler delete: city)
		(super dispose:)
	)
)

(instance egoEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 104 171)
				((ScriptID MONkEY_TALKER 1)
					setCycle: Walk
					setMotion: MoveTo 157 167 self
				)
			)
			(1
				(if (Btst fSenseDanger)
					(messager say: N_PALADIN V_DOIT C_SENSE_DANGER 0 self)
				else
					(self cue:)
				)
			)
			(2
				((ScriptID MONkEY_TALKER 1)
					ignoreActors: FALSE
				)
				(messager say: N_MANU V_DOIT C_ENTER)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance guardPatrols of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(apeManGuard setCycle: Walk setMotion: MoveTo 261 70 self)
			)
			(1
				(apeManGuard loop: 2)
				(= seconds 5)
			)
			(2
				(apeManGuard setMotion: MoveTo 330 78 self)
			)
			(3
				(= state -1)
				(= seconds 10)
			)
		)
	)
)

(instance egoExits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 2)
			)
			(1
				(if (not toldAboutDoor)
					(messager say: N_MANU V_DOIT C_DOOR)
				else
					(messager say: N_MANU V_DOIT C_GOODBYE)
				)
				(ego setMotion: MoveTo 115 153 self)
			)
			(2
				(ego setPri: 1 setLoop: 3 setMotion: MoveTo 115 173 self)
			)
			(3
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance blockEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (> numDeadApemen 2)
					(messager say: N_MANU V_DOIT C_BLOCK_EGO)
				else
					(messager say: N_MANU V_DOIT C_APEMAN)
				)
				(ego setLoop: 1 setMotion: MoveTo 30 175 self)
			)
			(1
				(apeMan
					setScale:
					init:
					setCycle: Walk
					setMotion: MoveTo 10 170 self
				)
			)
			(2
				(= monsterNum vApeman)
				(= monsterHealth 180)
				(curRoom newRoom: 550)
				(HandsOn)
				(apeMan dispose:)
				(self dispose:)
			)
		)
	)
)

(instance apeMan of Actor
	(properties
		x -10
		y 170
		view 570
	)
)

(instance apeManGuard of Actor
	(properties
		x 319
		y 78
		noun N_APEMAN_GUARD
		view 570
	)
)

(instance city of Feature
	(properties
		x 121
		y 81
		noun N_CITY ;N_ROOM	;EO: restored unused message
		nsTop 11
		nsBottom 152
		nsRight 243
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(curRoom setScript: egoExits)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance skyTowers of Feature
	(properties
		x 203
		y 19
		noun N_TOWERS
		nsTop -4
		nsLeft 94
		nsBottom 43
		nsRight 313
		sightAngle 180
	)
)

(instance jungle of Feature
	(properties
		x 258
		y 153
		noun N_JUNGLE
		nsTop 118
		nsLeft 197
		nsBottom 189
		nsRight 319
		sightAngle 180
	)
)

(instance tree of Feature
	(properties
		x 22
		y 97
		noun N_TREE
		nsTop 46
		nsLeft 2
		nsBottom 149
		nsRight 42
		sightAngle 180
	)
)

(instance egoTell of Teller
	
	(method (doChild)
		(return
			(switch query
				(-2
					(if (not (curRoom script?))
						(curRoom setScript: egoExits)
					)
				)
				(else
					(return query)
				)
			)
		)
	)
)

(instance manuTell of Teller
	
	(method (doChild)
		(return
			(switch query
				(-7
					(= toldAboutDoor TRUE)
					(super doChild: query)
				)
				(else
					(return query)
				)
			)
		)
	)
)
