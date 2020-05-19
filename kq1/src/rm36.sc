;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use System)

(public
	rm36 0
)

(procedure (NeedToMoveCloser)
	(Print 36 14)
)

(instance rm36 of Room
	(properties
		picture 36
		horizon 57
		north 45
		east 35
		south 29
		west 37
	)
	
	(method (init)
		(LoadMany VIEW 31 30)
		(Load SOUND 21)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
				(else  IRISOUT)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: 144 (+ horizon 2) init:)
				(NormalEgo)
			)
			(south
				(ego y: 188 init:)
				(NormalEgo)
			)
			(west
				(ego
					posn: 3 (proc0_17 188 (proc0_18 173 (ego y?) 167) 95)
					init:
				)
				(NormalEgo)
			)
			(east
				(ego
					posn: 317 (proc0_17 188 (proc0_18 130 (ego y?) 124) 75)
					init:
				)
				(NormalEgo)
			)
			(else 
				(self setScript: egoFromHole)
			)
		)
		(tree init:)
		(tree1 init:)
		(tree6 init:)
		(tree5 init:)
		(tree4 init:)
		(tree3 init:)
		(tree2 init:)
		(tree10 init:)
		(tree9 init:)
		(tree8 init:)
		(tree7 init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'reach/hole')
				(if (ego inRect: 142 125 175 131)
					(Print 36 0)
				else
					(NeedToMoveCloser)
				)
			)
			(
			(Said 'climb,scale,enter,go,(crawl[<in,in,through])/hole')
				(if (ego inRect: 142 125 175 131)
					(Print 36 1)
				else
					(NeedToMoveCloser)
				)
			)
			((Said 'get,take/glow')
				(if (ego inRect: 134 125 219 138)
					(Print 36 2)
				else
					(NeedToMoveCloser)
				)
			)
			((Said 'get,take/boulder')
				(if (ego inRect: 134 125 219 138)
					(Print 36 3)
				else
					(NeedToMoveCloser)
				)
			)
			((Said 'move,pull/boulder')
				(if (ego inRect: 134 125 219 138)
					(Print 36 4)
				else
					(NeedToMoveCloser)
				)
			)
			((or (Said 'look,check<in/hole') (Said 'look,check/hole') (MouseClaimed event 151 113 166 126))
				(cond 
					((ego inRect: 142 125 175 131)
						(Print 36 5)
					)
					((ego inRect: 134 125 219 138)
						(Print 36 6)
					)
					(else
						(NeedToMoveCloser)
					)
				)
			)
			(
				(or
					(Said 'look,check/boulder')
					(MouseClaimed event 133 113 215 126)
					(MouseClaimed event 111 100 245 113)
					(MouseClaimed event 134 91 200 99)
					(MouseClaimed event 156 85 193 90)
				)
				(Print 36 7)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 36 8)
					)
					((Said '/glow[<green]')
						(if (ego inRect: 142 125 175 131)
							(Print 36 9)
						else
							(Print 36 10)
						)
					)
					((Said '/boulder')
						(if (ego inRect: 134 125 219 138)
							(Print 36 11)
						else
							(Print 36 12)
						)
					)
					(else
						(super handleEvent: event)
					)
				)
			)
		)
	)
)

(instance egoFromHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					posn: 166 119
					view: 31
					setMotion: MoveTo 157 137 self
				)
			)
			(1
				((ScriptID 0 21) number: 21 init: play:)
				(ego
					view: 30
					cycleSpeed: 1
					cel: 6
					setMotion: 0
					setCycle: BegLoop self
				)
				(SolvePuzzle fExitedLepCave 1)
			)
			(2
				(NormalEgo)
				(ego loop: 2)
				(if
					(and
						(ego has: iMagicMirror)
						(ego has: iChest)
						(ego has: iMagicShield)
						(< curRoomNum 70)
						(!= curRoomNum 53)
					)
					(Print 36 13)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree10 of NewFeature
	(properties
		x 130
		y 74
		noun '/ceder'
		nsTop 58
		nsLeft 104
		nsBottom 90
		nsRight 156
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree9 of NewFeature
	(properties
		x 95
		y 83
		noun '/ceder'
		nsTop 78
		nsLeft 88
		nsBottom 89
		nsRight 103
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree8 of NewFeature
	(properties
		x 257
		y 83
		noun '/ceder'
		nsTop 68
		nsLeft 201
		nsBottom 98
		nsRight 313
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 237
		y 33
		noun '/ceder'
		nsTop -1
		nsLeft 156
		nsBottom 68
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 65
		y 32
		noun '/ceder'
		nsTop -1
		nsLeft 11
		nsBottom 65
		nsRight 119
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 32
		y 78
		noun '/ceder'
		nsTop 65
		nsLeft 4
		nsBottom 92
		nsRight 61
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 265
		y 112
		noun '/ceder'
		nsTop 98
		nsLeft 252
		nsBottom 127
		nsRight 278
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 36
		y 138
		noun '/ceder'
		nsTop 118
		nsLeft 10
		nsBottom 158
		nsRight 63
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 84
		y 151
		noun '/ceder'
		nsTop 145
		nsLeft 62
		nsBottom 158
		nsRight 107
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 28
		y 164
		noun '/ceder'
		nsTop 158
		nsLeft 20
		nsBottom 171
		nsRight 37
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)

(instance tree of NewFeature
	(properties
		x 5
		y 134
		noun '/ceder'
		nsTop 130
		nsBottom 139
		nsRight 10
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of trees are growing in this part of the forest.}
	)
)
