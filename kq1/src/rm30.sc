;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use StopWalk)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm30 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	[local11 35]
)
(instance rm30 of Room
	(properties
		picture 30
		horizon 80
		north 35
		east 31
		south 19
		west 29
	)
	
	(method (init &tmp temp0 temp1)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(if (>= howFast 1)
			(Load VIEW 205)
		)
		(Load VIEW 230)
		(if (not (ego has: iWalnut))
			(Load VIEW 1)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego
					posn:
						(proc0_17 319 (proc0_18 272 (ego x:) 248) 41)
						(+ horizon 2)
				)
			)
			(south
				(ego y: 188)
			)
			(west
				(ego posn:
					3
					(proc0_17
						188
						(proc0_18 130 (ego y:) 125)
						(+ horizon 2)
					)
				)
			)
			(east
				(ego posn:
					317
					(proc0_17
						178
						(proc0_18 141 (ego y:) 113)
						(+ horizon 2)
					)
				)
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(tree init:)
		(tree1 init:)
		(tree2 init:)
		(pineTree1 init:)
		(trunk init:)
		(smallBush init:)
		(farTree init:)
		(pineTree2 init:)
		(if (>= howFast 1)
			(squirrel
				setStep: 5 5
				setCycle: Walk
				setAvoider: Avoider
				ignoreActors:
				illegalBits: 0
				ignoreHorizon:
				init:
				stopUpd:
			)
		)
		(= local0 (* (+ 1 howFast) 8))
		(for ((= local6 0)) (< local6 local0) ((++ local6))
			(= [local11 local6] (Clone nutView))
			(while (< 36 (= temp0 (Random 10 190)) 130)
			)
			(while (< 164 (= temp1 (Random 145 185)) 172)
			)
			([local11 local6] posn: temp0 temp1 init: ignoreActors: stopUpd:)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
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
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check>')
				(cond 
					((or (Said '<around,down') (Said '/grass'))
						(if (< local10 local0)
							(if (ego inRect: 10 145 190 185)
								(Print 30 0)
							else
								(Print 30 1)
							)
						else
							(Print 30 2)
						)
					)
					((Said '[<at,around][/room,clearing,ceder]')
						(Print 30 3)
					)
					((Said '/ceder<pine')
						(Print 30 4)
					)
					((or (Said '/ceder') (Said '/ceder<nut'))
						(if (ego inRect: 10 145 190 185)
							(Print 30 5)
						else
							(Print 30 3)
						)
					)
					((Said '/nut,nut')
						(cond 
							((ego has: iWalnut)
								((inventory at: iWalnut) showSelf: ego)
							)
							((< local10 local0)
								(if (ego inRect: 10 145 190 185)
									(Print 30 0)
								else
									(Print 30 6)
								)
							)
							(else
								(Print 30 2)
							)
						)
					)
				)
			)
			((Said 'pick,get,take/nut<gold')
				(cond 
					((Btst fGotWalnut)
						(Print 30 7)
					)
					((and (ego has: iWalnut) (Btst fGotWalnut))
						(Print 30 8)
					)
					(else
						(Print 30 9)
					)
				)
			)
			((Said 'pick,get,take/nut,nut')
				(cond 
					((Btst fGotWalnut)
						(Print 30 10)
					)
					((ego has: iWalnut)
						(Print 30 11)
					)
					((Btst fInvisible)
						(Print 30 12)
					)
					((>= local10 local0)
						(Print 30 2)
					)
					(else
						(ego setScript: getWalnut)
					)
				)
			)
		)
	)
)

(class nutView of View
	(properties
		description {nut}
		sightAngle 90
		closeRangeDist 50
		longRangeDist 100
		view 230
		loop 0
		cel 0
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((MousedOn self event shiftDown)
				(Print 30 13)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance squirrel of Actor
	(properties
		noun '/squirrel'
		closeRangeDist 320
		longRangeDist 320
		shiftClick 1
		view 205
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(< (Random 1 100) 3)
				(not (squirrel script?))
				(not local2)
			)
			(squirrel setScript: squirrelDash)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'throw,throw/boulder,pebble,pebble[/squirrel]')
				(cond 
					((or (< x 0) (> x 320))
						(Print 30 14)
					)
					((ego has: iPebbles)
						(Print 30 15)
						(PebbleCount)
					)
					(else
						(Print 30 16)
					)
				)
			)
			((Said 'give/nut,nut/squirrel')
				(if (or (< x 0) (> x 320))
					(Print 30 17)
				else
					(Print 30 18)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'look,check/squirrel'))
				(if
				(and (> (squirrel x?) 0) (< (squirrel x?) 320))
					(Print 30 19)
				else
					(Print 30 20)
				)
			)
			((Said 'get,take/squirrel')
				(if (or (< x 0) (> x 320))
					(Print 30 17)
				else
					(Print 30 21)
				)
			)
			((Said 'shoot/squirrel[/shot]')
				(cond 
					((not (ego has: iSlingshot))
						(Print 30 22)
					)
					((not (ego has: iPebbles))
						(Print 30 23)
					)
					(else
						(Print 30 24)
					)
				)
			)
			((Said 'kill,shoot/squirrel')
				(Print 30 25)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance squirrelDash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< local10 local0)
					(= local4 ([local11 local10] y?))
					(= local3 ([local11 local10] x?))
				else
					(if (Random 0 1)
						(= local4 (Random 145 163))
					else
						(= local4 (Random 169 185))
					)
					(if (Random 0 1)
						(= local3 (Random 10 47))
					else
						(= local3 (Random 118 190))
					)
				)
				(if (< local3 83)
					(squirrel posn: -30 local4)
					(= local5 1)
				else
					(squirrel posn: 350 (Random 140 170))
					(= local5 0)
				)
				(squirrel setMotion: MoveTo local3 local4 self)
			)
			(1
				(if (< local10 local0)
					([local11 local10] posn: -1 -1)
					([local11 local10] hide:)
					(++ local10)
				)
				(if local5
					(squirrel setMotion: MoveTo -30 local4 self)
				else
					(squirrel setMotion: MoveTo 350 (Random 140 170) self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance getWalnut of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(> state 0)
				(== ([local11 local8] x?) -1)
				(not local1)
			)
			(if (< local10 local0)
				(Print 30 26)
				(Print 30 27)
				(= local1 1)
				(self changeState: 0)
			else
				(Print 30 28)
				(self changeState: 5)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local7 9999)
				(= local6 0)
				(while (< local6 local0)
					(if
					(< (= local9 (ego distanceTo: [local11 local6])) local7)
						(= local7 local9)
						(= local8 local6)
					)
					(++ local6)
				)
				(if (< (ego distanceTo: [local11 local8]) 25)
					(self cue:)
				else
					(Print 30 29)
					(HandsOn)
					(self dispose:)
				)
			)
			(1
				(if (< (ego x?) ([local11 local8] x?))
					(ego
						view: 0
						setCycle: StopWalk 2
						setAvoider: (Avoider offScreenOK: 0)
						setMotion:
							MoveTo
							(- ([local11 local8] x?) 7)
							([local11 local8] y?)
							self
					)
				else
					(ego
						view: 0
						setCycle: StopWalk 2
						setAvoider: Avoider
						setMotion:
							MoveTo
							(+ ([local11 local8] x?) 7)
							([local11 local8] y?)
							self
					)
				)
			)
			(2
				(ego
					view: 1
					loop: (if (< (ego x?) ([local11 local8] x?)) 0 else 1)
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(3
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(Print 30 30)
				(ego get: iWalnut)
				([local11 local8] hide:)
				(SolvePuzzle fGotWalnut 3)
				(= cycles 4)
				(ego setAvoider: 0)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree of NewFeature
	(properties
		x 104
		y 36
		noun '/ceder[<nut]'
		nsBottom 72
		nsRight 209
		description {walnut tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		contClick 371
		lookStr {This large, healthy tree is chock full of plump walnuts.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 84
		y 115
		noun '/ceder[<nut]'
		nsTop 72
		nsLeft 67
		nsBottom 158
		nsRight 102
		description {walnut tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		contClick 371
		lookStr {This large, healthy tree is chock full of plump walnuts.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 82
		y 162
		noun '/ceder[<nut]'
		nsTop 157
		nsLeft 56
		nsBottom 168
		nsRight 108
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		contClick 371
		lookStr {This large, healthy tree is chock full of plump walnuts.}
	)
)

(instance pineTree1 of NewFeature
	(properties
		x 272
		y 103
		noun '/ceder[<pine]'
		nsTop 71
		nsLeft 239
		nsBottom 135
		nsRight 306
		description {pineTree1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are just two garden-variety pine trees, with nothing to offer but shade.}
	)
)

(instance pineTree2 of NewFeature
	(properties
		x 265
		y 50
		noun '/ceder[<pine]'
		nsTop 31
		nsLeft 246
		nsBottom 70
		nsRight 284
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are just two garden-variety pine trees, with nothing to offer but shade.}
	)
)

(instance trunk of NewFeature
	(properties
		x 20
		y 99
		noun '/chest'
		nsTop 72
		nsLeft 6
		nsBottom 127
		nsRight 35
		description {trunk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's nothing particularly interesting about this old gray tree.}
	)
)

(instance smallBush of NewFeature
	(properties
		x 255
		y 171
		noun '/chest'
		nsTop 159
		nsLeft 213
		nsBottom 183
		nsRight 298
		description {small bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Some typical Daventry bushes flourish in the shade of the nearby walnut tree.}
	)
)

(instance farTree of NewFeature
	(properties
		x 269
		y 13
		noun '/chest'
		nsTop -1
		nsLeft 220
		nsBottom 27
		nsRight 318
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are trees growing nearly everywhere you look in Daventry.}
	)
)
