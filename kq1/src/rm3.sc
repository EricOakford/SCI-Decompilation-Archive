;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Block)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm3 0
)

(local
	local0 =  1
)
(instance rockBlock of Block
	(properties
		top 110
		left 143
		bottom 121
		right 181
	)
)

(instance rm3 of Room
	(properties
		picture 3
		horizon 100
		north 14
		east 2
		south 46
		west 4
	)
	
	(method (init)
		(LoadMany VIEW 203 1 18)
		(Load SOUND 13)
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
				(ego posn: 142 (+ horizon 2))
			)
			(south
				(if (< (ego y?) 220)
					(ego y: (proc0_17 289 (ego y?) 309))
				else
					(ego y: (proc0_17 66 (ego y?) 150))
				)
				(ego y: 188)
			)
			(east
				(ego posn: 317 158)
			)
			(west
				(ego posn: 3 137)
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(ego observeBlocks: rockBlock)
		(if (cast contains: theGoat)
			(theGoat observeBlocks: rockBlock)
		)
		(if (not (Btst fTookDagger))
			(Load VIEW 1)
		)
		(if (Btst fMovedRock)
			(rock cel: 3 setPri: 9 posn: 172 122)
			(rockBlock left: 151 top: 118 right: 189 bottom: 127)
		else
			(Load VIEW 18)
		)
		(rock init: ignoreActors: stopUpd:)
		(rock1 init:)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
	)
	
	(method (doit &tmp temp0)
		(if (!= local0 (ego onControl: origin))
			(if (== (= local0 (ego onControl: origin)) 512)
				(ego z: -8 setPri: 7)
			else
				(ego z: 0 setPri: -1)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((MouseClaimed event 141 110 185 117)
				(if (Btst fMovedRock)
					(Print 3 0)
					(event claimed: TRUE)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,ceder,clearing]')
						(if (Btst fMovedRock)
							(Print 3 1)
						else
							(Print 3 2)
						)
					)
					((or (Said '<down') (Said '/grass'))
						(cond 
							(
								(and
									(Btst fMovedRock)
									(or
										(ego inRect: 129 105 192 116)
										(ego inRect: 146 115 183 125)
									)
								)
								(if (Btst fTookDagger)
									(Print 3 3)
								else
									(Print 3 4)
								)
							)
							((not (Btst fMovedRock))
								(Print 3 5)
							)
							(else
								(Print 3 6)
							)
						)
					)
					((Said '/hole[<dark,shallow]')
						(cond 
							(
								(and
									(Btst fMovedRock)
									(or
										(ego inRect: 129 105 192 116)
										(ego inRect: 146 115 183 125)
									)
								)
								(if (Btst fTookDagger)
									(Print 3 7)
								else
									(Print 3 4)
								)
							)
							((Btst fMovedRock)
								(Print 3 8)
							)
							(else
								(Print 3 9)
							)
						)
					)
					((Said '/dirt')
						(if
							(and
								(Btst fMovedRock)
								(Btst fTookDagger)
								(or
									(ego inRect: 129 105 192 116)
									(ego inRect: 146 115 183 125)
								)
							)
							(Print 3 10)
						)
					)
					((Said '/dagger')
						(cond 
							((and (not (Btst fTookDagger)) (Btst fMovedRock))
								(Print 3 11)
							)
							((not (Btst fMovedRock))
								(Print 3 12)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said '/carving')
						(if (ego has: iDagger)
							(Print 3 13)
						else
							(Print 3 14)
						)
					)
				)
			)
			((Said 'climb,scale[<in]/hole[<shallow]')
				(cond 
					((!= 0 (ego z?))
						(Print 3 15)
					)
					((Btst fMovedRock)
						(Print 3 16)
					)
					(else
						(Print 3 17)
					)
				)
			)
			((Said 'get,take,pick/dagger')
				(cond 
					((== roomWithDeadGoat curRoomNum)
						(event claimed: FALSE)
					)
					((and (Btst fTookDagger) (not (ego has: iDagger)))
						(Print 3 18)
					)
					((Btst fTookDagger)
						(Print 3 19)
					)
					((Btst fInvisible)
						(Print 3 20)
					)
					((== (ego view?) (if (Btst fLittleEgo) 23 else 16))
						(Print 3 21)
					)
					((Btst fMovedRock)
						(if (ego inRect: 130 105 191 124)
							(curRoom setScript: getDagger)
						else
							(Print 3 22)
						)
					)
					(else
						(Print 3 23)
					)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance rock of Actor
	(properties
		x 169
		y 120
		yStep 1
		view 203
		xStep 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'kick/boulder[<!*]')
				(Print 3 24)
			)
			((Said 'look,check/(boulder,pebble)[<!*]')
				(event claimed: FALSE)
			)
			((or (Said 'look,check/(boulder,pebble)[<!*]') (MousedOn self event shiftDown))
				(if (Btst fMovedRock)
					(Print 3 25)
					(event claimed: TRUE)
				else
					(event claimed: TRUE)
					(Print 3 26)
				)
			)
			((Said 'get,take/(boulder,pebble)[<!*]')
				(Print 3 27)
			)
			;EO: this case already exists, and will probably never be executed
			((Said 'get,take/(boulder,pebble)[<!*]')
				(if (< (ego distanceTo: rock) 30)
					(Print 3 28)
				else
					(Print 3 29)
				)
			)
			((Said 'lift/boulder[<!*]')
				(if (ego inRect: 129 105 192 116)
					(Print 3 30)
				else
					(Print 3 31)
				)
			)
			((Said 'move,pull,pull/(boulder,pebble)[<!*]')
				(cond 
					((Btst fMovedRock)
						(Print 3 32)
					)
					((Btst fInvisible)
						(Print 3 33)
					)
					((== (ego view?) (if (Btst fLittleEgo) 23 else 16))
						(Print 3 21)
					)
					((ego inRect: 129 105 192 116)
						(curRoom setScript: moveRock)
					)
					((ego inRect: 146 115 183 125)
						(curRoom setScript: crushedByRock)
					)
					(else
						(Print 3 31)
					)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance moveRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop:
					(cond 
						((< (ego y?) 112) 2)
						((> (ego y?) 120) 3)
						((< (ego x?) 144) 0)
						(else 1)
					)
				)
				(= cycles 4)
			)
			(1
				(ego view: 1 ignoreActors: setCycle: EndLoop self)
			)
			(2
				(SolvePuzzle fMovedRock 2)
				((ScriptID 0 21) number: 13 loop: 1 init: play:)
				(ego stopUpd:)
				(rock
					xStep: (if (>= howFast 1) 1 else 4)
					yStep: (if (>= howFast 1) 1 else 4)
					cycleSpeed: 1
					setCycle: EndLoop
					setPri: 9
					setMotion: MoveTo 172 122 self
				)
			)
			(3
				(rock stopUpd:)
				((ScriptID 0 21) number: 106 play:)
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(rockBlock left: 151 top: 118 right: 189 bottom: 127)
				(RedrawCast)
				(Print 3 34)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance crushedByRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego 169 120)
				(= cycles 4)
			)
			(1
				(ego view: 1 ignoreActors: setCycle: EndLoop self)
				(rock ignoreActors:)
			)
			(2
				((ScriptID 0 21) number: 18 loop: 1 init: play:)
				(ego
					view: 18
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(rock
					illegalBits: 0
					cycleSpeed: 2
					setCycle: EndLoop
					setMotion: MoveTo 177 131 self
				)
			)
			(3
				(EgoDead
					{The moving rock rolls downhill...and right into you.___A crushing defeat.}
				)
			)
		)
	)
)

(instance getDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 150 114 self)
			)
			(1
				(ego view: 1 setCycle: EndLoop self)
			)
			(2
				(SolvePuzzle fTookDagger 5)
				(ego get: iDagger)
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(= cycles 6)
			)
			(3
				(ego illegalBits: cWHITE setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(RedrawCast)
				(Print 3 35)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rock1 of NewFeature
	(properties
		x 131
		y 117
		noun '/boulder<little'
		nsTop 110
		nsLeft 121
		nsBottom 124
		nsRight 142
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This rock is less distinctive than the one next to it.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 160
		y 40
		noun '/ceder'
		nsTop -1
		nsBottom 82
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a dense grove of trees here.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 28
		y 135
		noun '/ceder'
		nsTop 82
		nsBottom 189
		nsRight 56
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a dense grove of trees here.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 72
		y 121
		noun '/ceder'
		nsTop 82
		nsLeft 55
		nsBottom 160
		nsRight 90
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a dense grove of trees here.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 235
		y 135
		noun '/ceder'
		nsTop 82
		nsLeft 200
		nsBottom 189
		nsRight 270
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a dense grove of trees here.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 104
		y 95
		noun '/ceder'
		nsTop 82
		nsLeft 89
		nsBottom 109
		nsRight 119
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a dense grove of trees here.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 294
		y 94
		noun '/ceder'
		nsTop 82
		nsLeft 269
		nsBottom 106
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a dense grove of trees here.}
	)
)
