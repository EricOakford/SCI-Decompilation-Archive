;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm49 0
)

(instance rm49 of Room
	(properties
		picture 49
		north 12
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(south DISSOLVE)
			)
		)
		(LoadMany VIEW 249 13 14 19)
		(if (Btst fRopeLowered) (Load VIEW 61))
		(LoadMany SOUND 14 24 9)
		((ScriptID 0 23) number: 9 loop: -1 play:)
		(super init:)
		(if (Btst fRopeLowered)
			(addToPics
				add:
					rope
					((Clone rope) x: 159 y: 41 yourself:)
					((Clone rope) cel: 2 x: 159 y: 62 yourself:)
					((Clone rope) cel: 0 x: 159 y: 83 yourself:)
					((Clone rope) x: 159 y: 104 yourself:)
					((Clone rope) cel: 2 x: 159 y: 125 yourself:)
					((Clone rope) cel: 0 x: 159 y: 146 yourself:)
				eachElementDo: #init
				doit:
			)
		)
		(if (and (Btst fBucketLowered) (not (Btst fGotBucketFromWell)))
			(bucket setPri: 3 init: stopUpd:)
		)
		(if (Btst fBucketFloats)
			(bucket setLoop: 4 posn: 171 180 setCycle: Forward init:)
		)
		(switch prevRoomNum
			(north
				(= egoInWater 0)
				(if (Btst fEgoInBucket)
					(ego posn: 159 145 init:)
				else
					(ego posn: 158 152 init:)
				)
			)
			(else 
				((ScriptID 0 21) number: 51 loop: -1 init: play:)
				(ego
					view: 13
					illegalBits: -24576
					setStep: 1 1
					setCycle: Forward
					setPri: -1
					posn: 158 184
					init:
				)
				(= egoInWater 4)
			)
		)
		(water1 init:)
		(well1 init:)
		(cond 
			((Btst fClimbingRope)
				(curRoom setScript: climbDownRope)
				(SolvePuzzle fClimbedDownWell2 1)
				(SolvePuzzle fClimbedDownWell 1)
			)
			((Btst fEgoInBucket)
				(curRoom setScript: fallInBucket)
				(SolvePuzzle fClimbedDownWell2 1)
				(SolvePuzzle fClimbedDownWell 1)
			)
			((== prevRoomNum north)
				(curRoom setScript: fallThru)
			)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((== (ego view?) 13)
				(if (and swimTimer (not (-- swimTimer)))
					(curRoom setScript: drowning)
				)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(SOUTH south)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'throw/dagger')
				(if (ego has: iDagger)
					(Print 49 0)
				else
					(DontHave)
				)
			)
			((Said 'climb,scale<down')
				(cond 
					((== (ego view?) 61)
						(Print 49 1)
					)
					((Btst fEgoInBucket)
						(Print 49 2)
					)
					(else
						(Print 49 3)
					)
				)
			)
			((Said 'rub/ring')
				(Print 49 4)
				(event claimed: TRUE)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,well,cave]')
						(cond 
							((>= egoInWater 4)
								(Print 49 5)
							)
							((Btst fEgoInBucket)
								(Print 49 6)
							)
							(else
								(Print 49 7)
							)
						)
					)
					((Said '<up')
						(Print 49 8)
					)
					((Said '<down')
						(if (>= egoInWater 4)
							(Print 49 9)
						else
							(Print 49 10)
							(event claimed: TRUE)
						)
					)
					((Said '/bucket')
						(if (Btst fEgoInBucket)
							(Print 49 11)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/algae,algae')
						(Print 49 12)
					)
				)
			)
			((Said 'swim<cease')
				(switch (ego view?)
					(13
						(Print 49 13)
					)
					(else
						(Print 49 14)
					)
				)
			)
			((Said 'swim')
				(switch (ego view?)
					(13
						(Print 49 15)
					)
					(61
						(Print 49 16)
					)
					(14
						(= egoInWater 4)
						((ScriptID 0 21) number: 51 loop: -1 play:)
						(ego
							show:
							view: 13
							illegalBits: -24576
							setStep: 1 1
							setCycle: Forward
							setLoop: -1
							setCel: -1
							setPri: -1
							cycleSpeed: 0
						)
						(= swimTimer 3000)
						(curRoom setScript: 0)
						(HandsOn)
					)
					(else
						(Print 49 17)
					)
				)
			)
			((Said 'dive')
				(switch (ego view?)
					(13
						(SolvePuzzle fDiveUnderwater 4)
						(curRoom newRoom: 52)
					)
					(else
						(Print 49 18)
					)
				)
			)
			((Said 'climb,scale/wall')
				(switch (ego view?)
					(13
						(= swimTimer 0)
						(curRoom setScript: climbWall)
					)
					(14
						(Print 49 19)
					)
					(else
						(Print 49 20)
					)
				)
			)
			((Said 'get,take/rope')
				(if (not (Btst fRopeLowered))
					(Print 49 21)
				else
					(Print 49 22)
				)
			)
			(
				(or
					(Said 'get,take,jump,climb,scale[<up]/rope')
					(Said 'climb,scale,get,take,jump[<up]//rope')
				)
				(if (not (Btst fRopeLowered))
					(Print 49 21)
				else
					(switch (ego view?)
						(61
							(= swimTimer 0)
							(curRoom setScript: climbUpRope)
						)
						(13
							(= swimTimer 0)
							(Print 49 23)
							(User canControl: FALSE)
							(ego
								view: 61
								ignoreHorizon: TRUE
								ignoreActors: TRUE
								setMotion: 0
								illegalBits: 0
								setLoop: 0
								setCel: 0
								posn: 158 102
								setStep: 1 2
								setCycle: Walk
								setPri: 4
							)
							(= egoInWater 0)
							(climbDownRope start: 1)
							(curRoom setScript: climbDownRope)
						)
						(14
							(Print 49 24)
						)
						(else
							(Print 49 25)
						)
					)
				)
			)
			((Said 'cut/rope')
				(cond 
					((not (Btst fRopeLowered))
						(Print 49 21)
					)
					((not (ego has: iDagger))
						(Print 49 26)
					)
					(else
						(Print 49 27)
					)
				)
			)
			((Said 'enter,(get,take,jump,climb,scale,sit<in,onto,in)/bucket')
				(cond 
					((Btst fBucketLowered)
						(switch (ego view?)
							(249
								(Print 49 28)
							)
							(14
								(Print 49 19)
							)
							(13
								(if (and (!= (bucket loop?) 4) (not (Btst fGotBucketFromWell)))
									(Print 49 29)
									(= swimTimer 0)
									(curRoom setScript: sitBucket)
								else
									(Print 49 30)
								)
							)
							(else 
								(if (!= (bucket loop?) 4)
									(= swimTimer 0)
									(curRoom setScript: sitBucket)
								else
									(Print 49 31)
								)
							)
						)
					)
					((Btst fBucketFloats)
						(Print 49 32)
					)
					(else
						(Print 49 33)
					)
				)
			)
			((Said 'climb,scale/water')
				(Print 49 34)
			)
			((Said 'climb,scale[<up]')
				(if (== (ego view?) 14)
					(Print 49 35)
				else
					(= swimTimer 0)
					(curRoom setScript:
						(switch (ego view?)
							(13 climbWall)
							(61 climbUpRope)
							(else  climbUpRope)
						)
					)
				)
			)
			((or (Said 'get,take/water') (Said 'fill/bucket'))
				(cond 
					((== (ego view?) 14)
						(Print 49 19)
					)
					((!= (ego view?) 13)
						(Print 49 36)
					)
					((ego has: iWaterBucket)
						(if (Btst fWaterInBucket)
							(Print 49 37)
						else
							(Print 49 38)
							(BucketState 1)
						)
					)
					(else
						(Print 49 39)
					)
				)
			)
			((or (Said 'get,take/drink') (Said 'drink/water'))
				(cond 
					((== (ego view?) 14)
						(Print 49 19)
					)
					((!= (ego view?) 13)
						(if (and (ego has: iWaterBucket) (Btst fWaterInBucket))
							(Print 49 40)
							(BucketState FALSE)
						else
							(Print 49 36)
						)
					)
					(else
						(Print 49 41)
					)
				)
			)
			((Said 'jump')
				(Print 49 42)
			)
			((Said 'get,take<out/bucket')
				(if (!= (ego view?) 249)
					(Print 49 43)
				else
					(Print 49 44)
				)
			)
			((Said 'get,take/bucket')
				(cond 
					((ego has: iWaterBucket)
						(Print 49 45)
					)
					((== 4 (bucket loop?))
						((ScriptID 0 21) number: 105 loop: 1 init: play:)
						(Print 49 46)
						(SolvePuzzle fGotBucket 2)
						(ego get: iWaterBucket)
						(Bclr fBucketFloats)
						(Bset fGotBucket)
						(bucket dispose:)
					)
					(else
						(Print 49 47)
					)
				)
			)
			((Said 'tie,drop,drop/bucket')
				(if (ego has: iWaterBucket)
					(Print 49 48)
				else
					(DontHave)
				)
			)
		)
	)
)

(instance bucket of Actor
	(properties
		x 159
		y 165
		view 249
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'enter,(get,take,climb,scale<in,onto,in)/bucket')
				(event claimed: FALSE)
			)
			((Said 'look,check/bucket')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 49 49)
			)
		)
	)
)

(instance rope of RPicView
	(properties
		x 159
		y 20
		view 249
		loop 1
		cel 1
		priority 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/rope')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 49 50)
			)
		)
	)
)

(instance fallThru of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(ego setStep: 1 (+ (ego yStep?) 1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					illegalBits: 0
					view: 14
					setLoop: 1
					setCel: 0
					posn: 158 -4
					setStep: 1 2
					setCycle: 0
					setPri: 1
					setMotion: MoveTo 158 184 self
				)
			)
			(1
				((ScriptID 0 21) number: 14 loop: 1 init: play:)
				(ego setCel: -1 ignoreActors: 0 setCycle: EndLoop self)
			)
			(2
				(= seconds 3)
			)
			(3
				(User canInput: TRUE)
				((ScriptID 0 21) number: 96 play:)
				(ego cycleSpeed: 1 setLoop: 0 setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego hide:)
				(= cycles 9)
			)
			(6
				(ego show: setLoop: 0 cel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(7
				(EgoDead {You've sunk your chances for saving Daventry.})
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'dive,swim')
				(Print 49 51)
			)
		)
	)
)

(instance climbDownRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 61
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					illegalBits: 0
					setLoop: 0
					setCel: 0
					posn: 158 -4
					setPri: 4
					setStep: 1 3
					setCycle: Walk
					setMotion: MoveTo 158 102 self
				)
			)
			(1
				(HandsOn)
				(User canControl: FALSE)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'enter,(climb,scale,get,take<in,in,onto)/bucket')
				(cond 
					((Btst fGotBucketFromWell)
						(Print 49 52)
					)
					((Btst fBucketLowered)
						(if (!= (bucket loop?) 4)
							(= swimTimer 0)
							(curRoom setScript: sitBucket)
						else
							(Print 49 31)
						)
					)
					((Btst fBucketFloats)
						(Print 49 32)
					)
					(else
						(Print 49 53)
					)
				)
			)
			((Said 'climb,scale[<up]')
				(= swimTimer 0)
				(curRoom setScript: climbUpRope)
			)
			(
				(or
					(Said 'jump')
					(Said 'exit[/bucket]')
					(Said 'free[/rope]')
					(Said 'go,enter,drop[<in][/water]')
					(and (== (ego view?) 61) (Said 'let//go'))
				)
				(= swimTimer 0)
				(curRoom setScript: jumpInWater)
			)
			((Said 'look,check>')
				(cond 
					((Said '<up')
						(event claimed: FALSE)
					)
					((Said '[<at,around][/room,well]')
						(Print 49 54)
					)
					((Said '/rope')
						(Print 49 55)
					)
					((Said '/water')
						(Print 49 56)
					)
					((Said '/bucket')
						(if (and (Btst fBucketLowered) (not (ego has: iWaterBucket)))
							(Print 49 57)
						else
							(event claimed: FALSE)
						)
					)
				)
			)
		)
	)
)

(instance climbUpRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fClimbingRope)
				(Bclr fEgoInBucket)
				((ScriptID 0 21) stop:)
				(if (== (ego view?) 249) (bucket init:))
				(HandsOff)
				(ego
					view: 61
					ignoreHorizon: 1
					ignoreActors: 1
					illegalBits: 0
					setLoop: 0
					setCel: 0
					posn: 158 102
					setStep: 1 2
					setCycle: Walk
					setPri: 4
					setMotion: MoveTo 158 -4 self
				)
			)
			(1
				(HandsOn)
				(= egoInWater 0)
				(curRoom newRoom: 12)
			)
		)
	)
)

(instance fallInBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 24 init: play: fade:)
				(= cycles 3)
			)
			(1
				(ego
					ignoreActors:
					illegalBits: 0
					view: 249
					setLoop: 0
					setCel: 1
					setPri: 4
					cycleSpeed: 1
					setMotion: MoveTo 159 165 self
				)
			)
			(2
				(Bset fBucketLowered)
				(= swimTimer 0)
				(curRoom setScript: sitBucket)
			)
		)
	)
)

(instance sitBucket of Script
	(properties)
	
	(method (init)
		(Bclr fClimbingRope)
		(super init: &rest)
		(HandsOn)
		(User canControl: FALSE)
		((ScriptID 0 21) stop:)
		(ego
			ignoreActors:
			illegalBits: 0
			view: 249
			setLoop: 0
			setCel: 1
			setCycle: 0
			setMotion: 0
			cycleSpeed: 0
			setPri: 4
			posn: 159 165
		)
		(= egoInWater 0)
		(bucket dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'climb,scale[<up]/rope')
				(Bclr fEgoInBucket)
				(bucket init: stopUpd:)
				(= swimTimer 0)
				(curRoom setScript: climbUpRope)
			)
			(
				(or
					(Said 'jump')
					(Said 'exit[/bucket]')
					(Said 'free[/rope]')
					(Said 'go,enter,drop[<in][/water]')
					(and (== (ego view?) 61) (Said 'let//go'))
				)
				(bucket init: stopUpd:)
				(Bclr fEgoInBucket)
				(= swimTimer 0)
				(curRoom setScript: jumpInWater)
			)
			((Said 'cut/rope')
				(if (ego has: iDagger)
					(bucket init: stopUpd:)
					(Bclr fEgoInBucket)
					(= swimTimer 0)
					(Bclr fBucketLowered)
					(curRoom setScript: cutOffBucket)
				else
					(Print 49 26)
				)
			)
			((Said 'stand')
				(Print 49 58)
			)
			((Said 'look,check>')
				(cond 
					((Said '<up')
						(event claimed: FALSE)
					)
					((Said '/rope')
						(Print 49 55)
					)
					((or (Said '/water') (Said '<down'))
						(Print 49 59)
					)
					((Said '[/around,well]')
						(Print 49 60)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)

(instance jumpInWater of Script

	(method (doit)
		(super doit: &rest)
		(if (< state 3) (ego setStep: 1 (+ (ego yStep?) 1)))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (ego view?) 61)
					(Bclr fClimbingRope)
					(ego posn: 164 146)
				)
				(ego
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					illegalBits: 0
					view: 14
					setLoop: 1
					setCel: 0
					setCycle: 0
					setPri: 13
					setStep: 1 4
					moveSpeed: 0
					setMotion: MoveTo 150 186 self
				)
			)
			(1
				((ScriptID 0 21) number: 14 loop: 1 init: play:)
				(ego setCel: -1 setCycle: EndLoop self)
			)
			(2
				(= egoInWater 4)
				(= seconds 3)
			)
			(3
				((ScriptID 0 21) number: 51 loop: -1 play:)
				(ego
					show:
					view: 13
					illegalBits: -24576
					setStep: 1 1
					setCycle: Forward
					setLoop: -1
					setCel: -1
					setPri: -1
					cycleSpeed: 0
					ignoreActors: 0
				)
				(= swimTimer 3000)
				(curRoom setScript: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance splashBucket of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(Bset fBucketFloats)
				(bucket
					view: 249
					setLoop: 2
					setCel: 0
					ignoreActors:
					setMotion: MoveTo 170 186 self
				)
			)
			(2
				((ScriptID 0 21) number: 14 loop: 1 init: play:)
				(bucket setLoop: 3 setCycle: EndLoop self)
			)
			(3
				(bucket
					setLoop: 4
					ignoreActors: 0
					setCycle: Forward
					setMotion: MoveTo 171 180 self
				)
			)
			(4
				(self dispose:)
			)
		)
	)
)

(instance cutOffBucket of Script
	
	(method (doit)
		(super doit: &rest)
		(if (< state 3) (ego setStep: 1 (+ (ego yStep?) 1)))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bucket setScript: splashBucket)
				(ego
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					illegalBits: 0
					view: 14
					setLoop: 1
					setCel: 0
					setCycle: 0
					setPri: 13
					setStep: 1 4
					moveSpeed: 0
					setMotion: MoveTo 150 186 self
				)
			)
			(1
				((ScriptID 0 21) number: 14 loop: 1 init: play:)
				(ego setCel: -1 ignoreActors: 0 setCycle: EndLoop self)
			)
			(2
				(= seconds 3)
			)
			(3
				((ScriptID 0 21) number: 51 loop: -1 play:)
				(ego
					show:
					view: 13
					illegalBits: -24576
					setStep: 1 1
					setCycle: Forward
					setLoop: -1
					setCel: -1
					setPri: -1
					cycleSpeed: 0
				)
				(= swimTimer 3000)
				(= egoInWater 4)
				(curRoom setScript: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fBucketFloats)
					(Print 49 61)
					(self dispose:)
				else
					(HandsOff)
					(ego
						view: 13
						setStep: 1 1
						illegalBits: 0
						setMotion: MoveTo 159 176 self
					)
				)
			)
			(1
				((ScriptID 0 21) stop:)
				(ego
					setPri: 1
					view: 19
					setLoop: 0
					posn: 159 180
					setStep: 2 2
					setCycle: Forward
					setMotion: MoveTo 159 156 self
				)
			)
			(2
				(ego setCycle: 0 setMotion: MoveTo 159 180 self)
			)
			(3
				(Print 49 62)
				((ScriptID 0 21) number: 51 loop: -1 init: play:)
				(ego
					illegalBits: -24576
					view: 13
					setStep: 1 1
					setCel: -1
					setLoop: -1
					setCycle: Forward
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance well1 of NewFeature
	(properties
		x 158
		y 94
		noun '/(side[<well]),wall'
		nsTop -1
		nsLeft 117
		nsBottom 189
		nsRight 200
		description {well}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The sides of the well are damp and covered with algae and lichen.}
	)
)

(instance water1 of NewFeature
	(properties
		x 158
		y 183
		noun '/water[<well]'
		nsTop 177
		nsLeft 128
		nsBottom 189
		nsRight 188
		description {water}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The well-water is cold and clear.}
	)
)

(instance drowning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				((ScriptID 0 21) number: 96 loop: 1 init: play:)
				(ego
					setMotion: 0
					setLoop: 0
					view: 14
					cel: 5
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego cel: 5 setCycle: EndLoop self)
			)
			(2
				(ego cel: 5 setCycle: EndLoop self)
			)
			(3 (ego hide:) (= seconds 4))
			(4
				(if (not swimTimer)
					(EgoDead
						{After swimming for a long time, your strength ebbs and your arms and legs grow weary.__As your life swims before your eyes, you decide to...}
					)
				else
					(EgoDead
						{You splash around for awhile, but unfortunately that won't keep your head above water.__As you go down for the third time, a sense of peace washes over you...}
					)
				)
			)
		)
	)
)
