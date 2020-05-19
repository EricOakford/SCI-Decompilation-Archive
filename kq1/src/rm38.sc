;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm38 0
)

(local
	local0
	local1
	local2
	[local3 8] = [0 11 22 31 40 53 62 73]
	[local11 84] = [5 20 125 34 123 50 120 68 119 78 118 5 66 127 79 125 90 121 103 120 113 120 4 102 101 116 99 128 101 139 99 4 159 97 171 99 182 95 193 99 6 207 100 220 99 233 99 245 100 257 101 268 103 4 197 107 187 108 177 111 167 114 5 167 126 180 123 191 129 203 126 210 132 5 231 130 240 133 253 136 266 134 282 133]
)
(instance rm38 of Room
	(properties
		picture 38
		north 43
		east 37
		south 27
		west 39
	)
	
	(method (init)
		(if (>= howFast 1)
			(Load VIEW 304)
		)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego
					posn: (proc0_17 261 (proc0_18 149 (ego x?) 80) 35) 59
				)
			)
			(south (ego y: 188))
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 59))
			)
			(east
				(ego posn: 317 (proc0_17 188 (ego y?) 59))
			)
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(if (>= howFast 1)
			(butterfly posn: 259 94 init:)
		)
		(flowers init:)
		(flowers1 init:)
		(flowers2 init:)
		(flowers3 init:)
		(flowers4 init:)
		(flowers5 init:)
		(tree init:)
		(bush init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			(
				(and
					(& (ego onControl: origin) cLBLUE)
					(!= (ego script?) climbHill)
				)
				(ego setScript: climbHill)
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
				(self newRoom: nRoom)
			)
			(
				(and
					(& (ego onControl: origin) cBLUE)
					(!= (ego priority?) 1)
					(!= (ego script?) climbHill)
					(not (Btst fClimbing))
				)
				(ego setPri: 1)
			)
			(
				(and
					(not (& (ego onControl: origin) cBLUE))
					(== (ego priority?) 1)
					(!= (ego script?) climbHill)
				)
				(ego setPri: -1)
			)
			(
				(and
					(& (theGoat onControl: origin) cBLUE)
					(!= (theGoat priority?) 1)
				)
				(theGoat setPri: 1)
			)
			(
				(and
					(not (& (theGoat onControl: origin) cBLUE))
					(== (theGoat priority?) 1)
				)
				(theGoat setPri: -1)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((or (Said 'look,check<down') (Said 'look,check/grass'))
				(Print 38 0)
				(if (== roomWithBeanstalk curRoomNum)
					(Print 38 1)
				)
			)
			((Said 'smell,sniff/blossom')
				(if (ego inRect: 60 80 260 120)
					(Print 38 2)
				else
					(Print 38 3)
				)
			)
			((Said 'get,take,pick/blossom')
				(Print 38 4)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing]')
						(if (!= curRoomNum roomWithBeanstalk)
							(Print 38 5)
						else
							(Print 38 6)
						)
					)
					((Said '/blossom')
						(Print 38 7)
					)
				)
			)
			((Said 'get,take,capture,chase/butterfly')
				(Print 38 8)
			)
		)
	)
)

(instance butterfly of Actor
	(properties
		view 304
	)
	
	(method (init)
		(self
			ignoreControl:
			ignoreHorizon:
			ignoreActors:
			setCycle: Walk
			setAvoider: Avoider
			illegalBits: 0
		)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if (< (Random 1 100) 25)
			(butterfly
				posn: (+ x (- 3 (Random 0 6))) (+ y (- 2 (Random 0 4)))
			)
		)
		(if (== (butterfly script?) 0)
			(butterfly setScript: moveButterfly)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/butterfly')
				(self doVerb: verbLook event)
			)
			((Said 'talk,speak/butterfly')
				(Print 38 9)
			)
			((Said 'eat,consume/butterfly')
				(Print 38 10)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 38 11)
			)
		)
	)
)

(instance moveButterfly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (Random 1 100) 10)
					(repeat
						(!= (= local2 (Random 0 7)) local0)
					)
					(= local0 local2)
				)
				(= local1 (Random 1 [local11 [local3 local0]]))
				(butterfly
					setMotion:
						MoveTo
						[local11 (+ [local3 local0] (- (* local1 2) 1))]
						[local11 (+ [local3 local0] (* local1 2))]
						self
				)
			)
			(1
				(butterfly setCycle: Forward)
				(= cycles (Random 5 20))
			)
			(2 (self dispose:))
		)
	)
)

(instance climbHill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					illegalBits: 0
					setLoop: 3
					setPri: 0
					setCycle: Walk
					setMotion: MoveTo (ego x?) 55 self
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance flowers of NewFeature
	(properties
		x 71
		y 122
		noun '/blossom'
		nsTop 120
		nsLeft 10
		nsBottom 124
		nsRight 133
		description {flowers}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {What a pretty bed of wildflowers.}
	)
)

(instance flowers1 of NewFeature
	(properties
		x 82
		y 117
		noun '/blossom'
		nsTop 115
		nsLeft 37
		nsBottom 120
		nsRight 127
		description {flowers}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {What a pretty bed of wildflowers.}
	)
)

(instance flowers2 of NewFeature
	(properties
		x 61
		y 127
		noun '/blossom'
		nsTop 124
		nsLeft 30
		nsBottom 130
		nsRight 93
		description {flowers}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {What a pretty bed of wildflowers.}
	)
)

(instance tree of NewFeature
	(properties
		x 115
		y 21
		noun '/ceder'
		nsTop -1
		nsLeft 85
		nsBottom 43
		nsRight 145
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {An ordinary tree stands watch at the top of the hill.}
	)
)

(instance flowers3 of NewFeature
	(properties
		x 179
		y 100
		noun '/blossom'
		nsTop 94
		nsLeft 86
		nsBottom 106
		nsRight 272
		description {flowers}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {What a pretty bed of wildflowers.}
	)
)

(instance flowers4 of NewFeature
	(properties
		x 179
		y 120
		noun '/blossom'
		nsTop 106
		nsLeft 148
		nsBottom 135
		nsRight 211
		description {flowers}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {What a pretty bed of wildflowers.}
	)
)

(instance flowers5 of NewFeature
	(properties
		x 256
		y 130
		noun '/blossom'
		nsTop 123
		nsLeft 211
		nsBottom 137
		nsRight 301
		description {flowers}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {What a pretty bed of wildflowers.}
	)
)

(instance bush of NewFeature
	(properties
		x 104
		y 51
		noun '/bush'
		nsTop 44
		nsLeft 60
		nsBottom 59
		nsRight 149
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {There's a bit of undergrowth scattered around the base of the tree.}
	)
)
