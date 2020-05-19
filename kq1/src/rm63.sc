;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm63 0
)

(local
	local0
)
(procedure (FallDirection dir)
	(HandsOff)
	((ScriptID 0 23) fade:)
	(if (Btst fInvisible)
		(Print 63 13)
		(ego put: iMagicRing)
		(theGame changeScore: -3)
	)
	(ego
		view: 57
		illegalBits: 0
		loop: dir
		cel: 0
		setMotion: 0
		cycleSpeed: 1
	)
)

(procedure (FallSound)
	((ScriptID 0 23) number: 17 loop: 1 play:)
)

(instance rm63 of Room
	(properties
		picture 63
		horizon 62
		south 14
	)
	
	(method (init)
		(LoadMany VIEW 57 48)
		(LoadMany SOUND 17)
		(self style: WIPEUP)
		(super init:)
		(self setScript: climbUp)
		(if (not (if (Btst fGotGoldEgg) else (ego has: iGoldEgg)))
			(goldEgg init: setPri: 4)
		)
		(Bclr fFallFromTree)
		(nest init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
		(tree7 init:)
		(tree8 init:)
		(tree9 init:)
		(tree10 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
			((== (ego onControl: origin) cCYAN)
				(self setScript: longFallRight)
			)
			((== (ego onControl: origin) cRED)
				(self setScript: longFallLeft)
			)
			((== (ego onControl: origin) cLCYAN)
				(self setScript: longFrontFallLeft)
			)
			((== (ego onControl: origin) cGREY)
				(self setScript: fallSEast)
			)
			((== (ego onControl: origin) cBLUE)
				(self setScript: longFallSWest)
			)
			((== (ego onControl: origin) cLGREEN)
				(self setScript: longFallBack)
			)
			((== (ego onControl: origin) cYELLOW)
				(self setScript: longFallFront)
			)
			(
				(and
					(== (ego onControl: origin) cLBLUE)
					(Btst fInvisible)
					(not local0)
				)
				(= local0 1)
				(Print 63 0)
				(ego setMotion: 0 illegalBits: -32256)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(FadeBackgroundMusic)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'climb,scale/ceder')
				(Print 63 1)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,ceder,branch]')
						(Print 63 2)
					)
					((Said '<down')
						(Print 63 3)
					)
					((Said '/egg[<gold]')
						(cond 
							((ego has: iGoldEgg)
								(event claimed: FALSE)
							)
							(
								(and
									(== ((inventory at: iGoldEgg) owner?) curRoomNum)
									(< (ego distanceTo: goldEgg) 50)
								)
								(Print 63 4)
							)
							((not (< (ego distanceTo: goldEgg) 50))
								(Print 63 5)
							)
							(else
								(Print 63 6)
							)
						)
					)
					((Said '/nest')
						(cond 
							((not (ego inRect: 150 75 236 110))
								(Print 63 7)
							)
							((or (ego has: iGoldEgg) (Btst fGotGoldEgg))
								(Print 63 8)
							)
							(else
								(Print 63 9)
							)
						)
					)
				)
			)
			((Said 'get,take/nest')
				(Print 63 10)
			)
			((Said 'get,take/egg')
				(cond 
					((not (ego inRect: 188 75 236 94))
						(Print 63 7)
					)
					((or (ego has: iGoldEgg) (Btst fGotGoldEgg))
						(Print 63 11)
					)
					(else
						(self setScript: getEgg)
					)
				)
			)
		)
	)
)

(instance climbUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 48 posn: 50 201 init: setCycle: EndLoop self)
			)
			(1
				(NormalEgo)
				(ego view: 0 setMotion: MoveTo 50 184 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getEgg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 206 85 self)
			)
			(1 (ego loop: 3) (self cue:))
			(2
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(Print 63 12)
				(ego get: iGoldEgg)
				(goldEgg dispose:)
				(SolvePuzzle fGotGoldEgg 6)
				(self dispose:)
			)
		)
	)
)

(instance goldEgg of View
	(properties
		x 206
		y 66
		description {golden egg}
		view 633
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 63 4)
			)
		)
	)
)

(instance fallSEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection 1)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 3 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 1 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallSWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection loopE)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 1 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallFront of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection loopE)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 5 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection 8)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 9 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(swoosh
					posn: (ego x?) (+ (ego y?) 50)
					cycleSpeed: 1
					setPri: 3
					init:
				)
				(ego setPri: 3 setCycle: CycleTo 6 1 self)
			)
			(3
				(ego setCycle: EndLoop)
				(swoosh setCycle: EndLoop self)
			)
			(4
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fallNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection 8)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 9 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 1 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection 4)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 6 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 1 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection 5)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 7 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 1 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFrontFallLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallDirection 5)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallSound)
				(ego loop: 7 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(ego setPri: 5 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fellOffTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fFallFromTree)
				(curRoom newRoom: 14)
				(self dispose:)
			)
		)
	)
)

(instance crater of View
	(properties
		x 210
		y 161
		description {hole}
		view 34
	)
)

(instance swoosh of Prop
	(properties
		description {swoosh}
		view 57
		loop 9
		cel 4
	)
)

(instance tree1 of NewFeature
	(properties
		x 60
		y 107
		noun '/ceder,branch'
		nsTop 89
		nsLeft 50
		nsBottom 126
		nsRight 70
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 65
		y 145
		noun '/ceder,branch'
		nsTop 126
		nsLeft 54
		nsBottom 164
		nsRight 76
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 136
		y 84
		noun '/ceder,branch'
		nsTop 76
		nsLeft 121
		nsBottom 92
		nsRight 152
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 151
		y 106
		noun '/ceder,branch'
		nsTop 92
		nsLeft 133
		nsBottom 120
		nsRight 169
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 175
		y 89
		noun '/ceder,branch'
		nsTop 81
		nsLeft 152
		nsBottom 98
		nsRight 198
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 217
		y 84
		noun '/ceder,branch'
		nsTop 79
		nsLeft 197
		nsBottom 90
		nsRight 238
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 142
		y 134
		noun '/ceder,branch'
		nsTop 120
		nsLeft 124
		nsBottom 148
		nsRight 161
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree8 of NewFeature
	(properties
		x 123
		y 154
		noun '/ceder,branch'
		nsTop 139
		nsLeft 100
		nsBottom 169
		nsRight 146
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree9 of NewFeature
	(properties
		x 76
		y 165
		noun '/ceder,branch'
		nsTop 152
		nsLeft 52
		nsBottom 178
		nsRight 101
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance tree10 of NewFeature
	(properties
		x 47
		y 183
		noun '/ceder,branch'
		nsTop 178
		nsLeft 25
		nsBottom 189
		nsRight 69
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Up here in the tree, the main trunk is bent enough to allow you to walk carefully along its length. The leaves and branches hide the ground from view.}
	)
)

(instance nest of NewFeature
	(properties
		x 205
		y 68
		noun '/nest[<bird]'
		nsTop 64
		nsLeft 198
		nsBottom 72
		nsRight 213
		description {nest}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {}
	)
	
	(method (doLook)
		(cond 
			(
				(and
					(== ((inventory at: 15) owner?) curRoomNum)
					(<= (GetDistance (ego x?) (ego y?) 206 66 60) 50)
				)
				(Print 63 4)
			)
			((>= (GetDistance (ego x?) (ego y?) 206 66 60) 50)
				(Print 63 5)
			)
			(else
				(Print 63 6)
			)
		)
	)
)
