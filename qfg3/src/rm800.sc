;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include sci.sh)
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
	local0
	local1
	[local2 4] = [0 -2 11 999]
	[local6 2]
	[local8 6] = [0 6 -7 9 10 999]
	[local14 4] = [0 3 8 999]
	[local18 3] = [0 -7 999]
	[local21 3]
)
(instance rm800 of Rm
	(properties
		noun 4
		picture 800
	)
	
	(method (init)
		(= [local6 0] @local2)
		(= [local21 0] @local8)
		(= [local21 1] @local14)
		(ego setScale: 189 x: -10 y: 182 noun: 2 init: normalize:)
		(egoTell init: ego @local2 @local6)
		(manuTell
			init: (ScriptID 41 1) @local8 @local21 @local18
		)
		(HandsOn)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						136
						189
						148
						184
						109
						184
						132
						161
						193
						162
						200
						156
						159
						159
						140
						155
						102
						155
						0
						167
						0
						189
					yourself:
				)
		)
		(super init:)
		(city init:)
		(skyTowers init:)
		(jungle init:)
		(tree init:)
		(walkHandler addToFront: city)
		((ScriptID 41 1)
			x: -20
			y: 175
			setScale:
			noun: 1
			ignoreActors: 1
			init:
		)
		(if (and (== prevRoomNum 550) (== battleResult 0))
			(EgoDead)
		)
		(if (== prevRoomNum 550)
			(cSound changeTo: 180)
			(ego x: 104 y: 171)
			((ScriptID 41 1) x: 157 y: 167)
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
			((< (ego x?) 10) (curRoom setScript: blockEgo))
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany 0 41)
		(walkHandler delete: city)
		(super dispose:)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 104 171)
				((ScriptID 41 1)
					setCycle: Walk
					setMotion: MoveTo 157 167 self
				)
			)
			(1
				(if (Btst 150)
					(messager say: 3 6 14 0 self)
				else
					(self cue:)
				)
			)
			(2
				((ScriptID 41 1) ignoreActors: 0)
				(messager say: 1 6 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance guardPatrols of Script
	(properties)
	
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
			(3 (= state -1) (= seconds 10))
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1
				(if (not local1)
					(messager say: 1 6 3)
				else
					(messager say: 1 6 2)
				)
				(ego setMotion: MoveTo 115 153 self)
			)
			(2
				(ego setPri: 1 setLoop: 3 setMotion: MoveTo 115 173 self)
			)
			(3 (curRoom newRoom: 810))
		)
	)
)

(instance blockEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (> local0 2)
					(messager say: 1 6 5)
				else
					(messager say: 1 6 4)
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
				(= monsterNum 575)
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
		noun 5
		view 570
	)
)

(instance city of Feature
	(properties
		x 121
		y 81
		noun 4
		nsTop 11
		nsBottom 152
		nsRight 243
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: egoExits)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance skyTowers of Feature
	(properties
		x 203
		y 19
		noun 9
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
		noun 7
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
		noun 8
		nsTop 46
		nsLeft 2
		nsBottom 149
		nsRight 42
		sightAngle 180
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (doChild)
		(return
			(switch query
				(-2
					(if (not (curRoom script?))
						(curRoom setScript: egoExits)
					)
				)
				(else  (return query))
			)
		)
	)
)

(instance manuTell of Teller
	(properties)
	
	(method (doChild)
		(return
			(switch query
				(-7
					(= local1 1)
					(super doChild: query)
				)
				(else  (return query))
			)
		)
	)
)
