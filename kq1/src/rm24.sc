;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use RFeature)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm24 0
)

(local
	local0
	local1
	local2
	local3 = [0 9 20 31 40]
	local8 = [4 88 149 101 152 114 144 128 145 5 207 147 194 146 180 145 166 145 149 144 5 180 165 167 165 152 162 141 158 128 157 4 34 164 47 162 61 158 70 155 5 8 147 25 144 40 140 58 143 71 144]
)
(instance rm24 of Room
	(properties
		picture 24
		horizon 57
		north 25
		east 23
		south 9
		west 17
	)
	
	(method (init)
		(if (not (Btst fGotClover))
			(Load VIEW 1)
		)
		(Load VIEW 224)
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
				(ego posn: (proc0_18 263 (ego x?) 48) (+ horizon 2))
			)
			(south
				(ego y: 188)
			)
			(west
				(ego posn: 3 (proc0_17 189 (ego y?) (+ horizon 2)))
			)
			(east
				(ego posn: 317 (proc0_17 189 (ego y?) (+ horizon 2)))
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(if (not (Btst fGotClover))
			(clover init: setScript: flashClover)
		)
		(addToPics
			add: cloverPatch ((Clone cloverPatch) cel: 1 x: 25 y: 143)
			eachElementDo: #init
			doit:
		)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(clover2 init:)
		(clover1 init:)
		(clover0 init:)
		(clover3 init:)
		(bush init:)
		(tree5 init:)
		(if
		(and (Btst fPlantedBeanstalk) (== roomWithBeanstalk curRoomNum))
			(stalkIn24 init:)
			(stalk2In24 init:)
			(features delete: tree3)
		)
		(if (>= howFast 1) (butterfly posn: 35 142 init:))
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
			((super handleEvent: event))
			((Said 'bury/bean')
				(if (and (not (Btst fPlantedBeanstalk)) (ego has: iBeans))
					(stalkIn24 init:)
					(stalk2In24 init:)
					(features delete: tree3)
					(event claimed: FALSE)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing]')
						(if (== roomWithBeanstalk curRoomNum)
							(Print 24 0)
						else
							(Print 24 1)
						)
					)
					((or (Said '<down') (Said '/grass'))
						(cond 
							((and (< (ego distanceTo: clover) 30) (not (ego has: iFourLeafClover)))
								(Print 24 2)
								(if (== roomWithBeanstalk curRoomNum)
									(Print 24 0)
								)
							)
							((< (ego distanceTo: cloverPatch) 20)
								(Print 24 3)
								(if (== roomWithBeanstalk curRoomNum)
									(Print 24 0)
								)
							)
							((== roomWithBeanstalk curRoomNum)
								(Print 24 0)
							)
							(else
								(Print 24 4)
							)
						)
					)
				)
			)
			((Said 'get,take,pick/blossom,blossom')
				(Print 24 5)
			)
			(
				(or
					(Said 'get,take,pick/clover[<leaf<4,unusual]')
					(Said 'get,take,pick/clover[<fourleaf,unusual]')
				)
				(cond 
					((Btst fGotClover)
						(Print 24 6)
					)
					((> (ego distanceTo: clover) 20)
						(CantReach)
					)
					((Btst fInvisible)
						(Print 24 7)
					)
					(else
						(curRoom setScript: getClover)
					)
				)
			)
			(
				(or
					(Said 'get,take,pick/clover[<leaf<3]')
					(Said 'get,take,pick/clover[<threeleaf]')
				)
				(cond 
					((Btst fGotClover)
						(Print 24 8)
					)
					((> (ego distanceTo: clover) 20)
						(CantReach)
					)
					((Btst fInvisible)
						(Print 24 7)
					)
					(else
						(Print 24 9)
					)
				)
			)
			((Said 'climb,scale/ceder')
				(Print 24 10)
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
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/butterfly')
				(self doVerb: verbLook event)
			)
			((Said 'get,take,capture,chase/butterfly')
				(Print 24 11)
			)
			((Said 'eat,consume/butterfly')
				(Print 24 12)
			)
			((Said 'talk,speak/butterfly')
				(Print 24 13)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 24 14)
			)
		)
	)
)

(instance moveButterfly of Script
	;NOTE: This script seems to be bugged, and may hang the game.
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				;this appears to be the problematic code
;;;				(if (< (Random 1 100) 10)
;;;					(repeat
;;;						(!= (= local2 (Random 0 4)) local0)
;;;					)
;;;					(= local0 local2)
;;;				)
				(= local1 (Random 1 [local8 [local3 local0]]))
				(butterfly setMotion: MoveTo
						[local8 (+ [local3 local0] (- (* local1 2) 1))]
						[local8 (+ [local3 local0] (* local1 2))]
						self
				)
			)
			(1
				(butterfly setCycle: Forward)
				(= cycles (Random 5 20))
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance clover of Prop
	(properties
		x 42
		y 137
		view 224
		loop 1
	)
	
	(method (handleEvent event)
		(if (event claimed?)
			(return)
		)
		(if
			(or
				(Said 'look,check/garden[<clover]')
				(Said 'look,check/clover[<unusual]')
				(MousedOn self event shiftDown)
			)
			(if (< (ego distanceTo: clover) 50)
				(Print 24 15)
			else
				(Print 24 16)
			)
			(event claimed: TRUE)
		)
	)
)

(instance cloverPatch of RPicView
	(properties
		x 49
		y 147
		description {clover patch}
		view 224
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (event claimed?)
			(return)
		)
		(if (MousedOn self event shiftDown)
			(Print 24 17)
			(event claimed: TRUE)
		)
	)
)

(instance flashClover of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 50 400)))
			(1 (clover setCycle: EndLoop self))
			(2 (clover setCycle: BegLoop self))
			(3 (self changeState: 0))
		)
	)
)

(instance getClover of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego clover)
				(= cycles 2)
			)
			(1
				(ego view: 1 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(ego get: iFourLeafClover)
				(clover setScript: 0 startUpd: dispose:)
				(SolvePuzzle fGotClover 2)
				(= cycles 4)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 192
		y 34
		noun '/ceder'
		nsTop -1
		nsLeft 135
		nsBottom 69
		nsRight 250
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely fruit trees have blossomed early this year.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 163
		y 81
		noun '/ceder'
		nsTop 70
		nsLeft 157
		nsBottom 93
		nsRight 169
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely fruit trees have blossomed early this year.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 207
		y 88
		noun '/ceder'
		nsTop 70
		nsLeft 200
		nsBottom 107
		nsRight 214
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely fruit trees have blossomed early this year.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 124
		y 41
		noun '/ceder'
		nsTop 21
		nsLeft 115
		nsBottom 62
		nsRight 134
		description {three-leaf clover}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Lovely fruit trees have blossomed early this year.}
	)
)

(instance clover0 of NewFeature
	(properties
		x 50
		y 145
		noun '/garden[/clover]'
		nsTop 124
		nsBottom 166
		nsRight 100
		description {three-leaf clover}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A jumble of clover grows in this patch of the forest.}
	)
)

(instance clover1 of NewFeature
	(properties
		x 144
		y 160
		noun '/garden[/clover]'
		nsTop 152
		nsLeft 100
		nsBottom 169
		nsRight 188
		description {three-leaf clover}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The clover patch is thick with small three-leaf clovers.}
	)
)

(instance clover2 of NewFeature
	(properties
		x 160
		y 144
		noun '/garden[/clover]'
		nsTop 136
		nsLeft 100
		nsBottom 153
		nsRight 221
		description {three-leaf clover}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The clover patch is thick with small three-leaf clovers.}
	)
)

(instance clover3 of NewFeature
	(properties
		x 114
		y 132
		noun '/garden[/clover]'
		nsTop 129
		nsLeft 100
		nsBottom 135
		nsRight 128
		description {three-leaf clover}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The clover patch is thick with small three-leaf clovers.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 107
		y 18
		noun '/ceder'
		nsTop -1
		nsLeft 85
		nsBottom 38
		nsRight 129
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Graceful trees grow in profusion all throughout Daventry.}
	)
)

(instance bush of NewFeature
	(properties
		x 86
		y 48
		noun '/bush'
		nsTop 39
		nsLeft 56
		nsBottom 58
		nsRight 117
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A small ordinary bush flourishes in the shade of the trees.}
	)
)

(instance stalkIn24 of NewFeature
	(properties
		x 192
		y 34
		nsTop -1
		nsLeft 120
		nsBottom 64
		nsRight 177
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This mighty beanstalk stretches up so high, it vanishes into the clouds above.}
	)
	
	(method (init)
		(features addToFront: self)
	)
)

(instance stalk2In24 of NewFeature
	(properties
		x 192
		y 34
		nsTop 65
		nsLeft 122
		nsBottom 144
		nsRight 196
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This mighty beanstalk stretches up so high, it vanishes into the clouds above.}
	)
	
	(method (init)
		(features addToFront: self)
	)
)
