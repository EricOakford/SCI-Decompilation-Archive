;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use StopWalk)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm1 0
)

(local
	i
	[ripple 3]
	rippleX = [51 170 288]
	rippleY = [155 156 158]
)
(instance rm1 of Room
	(properties
		picture 1
		horizon 78
		north 53
		east 83
		west 2
	)
	
	(method (init)
		(LoadMany VIEW 201 202 267 186 0)
		(LoadMany SOUND 78 79 80 11)
		(self keep: FALSE)
		(self style:
				(switch prevRoomNum
					(north WIPEDOWN)
					(west SCROLLLEFT)
					(east SCROLLRIGHT)
					(south WIPEUP)
					(else 
						(TheMenuBar state: TRUE)
						(if (CheckHowFast 1) 17 else 15)
					)
				)
		)
		(gate illegalBits: 0 ignoreHorizon: setPri: 3 stopUpd:)
		(self setRegions: MOAT)
		(for ((= i 0)) (< i 3) ((++ i))
			((= [ripple i] (Clone Ripple))
				view: 202
				cycleSpeed: 1
				setPri: 3
				x: [rippleX i]
				y: [rippleY i]
				setLoop: 0
				ignoreActors: 1
				sightAngle: 180
				closeRangeDist: 500
				longRangeDist: 500
				description: {ripples}
				init:
				stopUpd:
			)
			(if (>= howFast 1)
				([ripple i] setCycle: Forward)
			)
		)
		(super init:)
		(if
			(not
				(if (and (ego has: iMagicShield) (ego has: iMagicMirror)) (ego has: iChest))
			)
			(addToPics add: g1 g2 eachElementDo: #init doit:)
		)
		(addToPics add: urn1 urn2 eachElementDo: #init doit:)
		(if (Btst fInCartoon)
			(Bclr fInCartoon)
			(TheMenuBar draw:)
			(StatusLine enable:)
		)
		(switch prevRoomNum
			(200
				(curRoom setScript: exitCastle)
			)
			(west
				(ego posn: 3 (proc0_17 112 (ego y?) 100) init:)
				(gate init:)
				(NormalEgo)
			)
			(east
				(ego posn: 317 (proc0_17 113 (ego y?) 100) init:)
				(gate init:)
				(NormalEgo)
			)
			(else 
				(curRoom setScript: exitCastle)
			)
		)
		(vine1 init:)
		(vine2 init:)
		(vine3 init:)
		(vine4 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
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
		;this has been successfully decompiled
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'open,open/door,gate')
				(cond 
					((> (ego y?) 86)
						(Print 1 0)
					)
					((Btst 2)
						(Print 1 1)
					)
					((and (ego has: iMagicMirror) (ego has: iChest) (ego has: iMagicShield))
						(Print 1 2)
						((ScriptID 0 23) fade:)
						(SolvePuzzle fEndGame 3)
						(gate setScript: enterCastle)
					)
					(else
						(Print 1 3)
					)
				)
			)
			((Said 'close,shut/gate,door')
				(Print 1 4)
			)
			((Said 'knock/door,gate')
				(if (< (ego y?) 86)
					(Print 1 5)
				else
					(Print 1 0)
				)
			)
			((Said 'get,take,use/planter,caldron,planter') (Print 1 6))
			(
				(or
					(Said 'get,take<in/planter,planter,caldron')
					(Said 'hide<in/planter,planter,caldron')
				)
				(Print 1 7)
			)
			((Said 'look,check,talk,speak,kick,kill,kiss,attack/guard')
				(Print 1 8)
			)
			((MouseClaimed event 101 30 120 48 3)
				(Print 1 9)
			)
			((Said 'look,check/planter,caldron')
				(Print 1 10)
			)
			((Said 'look,check/ceder,vine,bury')
				(Print 1 11)
			)
			((Said 'look,check/blossom')
				(if (< (ego y?) 112)
					(Print 1 12)
				else
					(Print 1 13)
				)
			)
			((Said 'remove/ring')
				(if (Btst fInvisible)
					(if
						(not
							(and (ego has: iMagicShield) (ego has: iMagicMirror) (ego has: iChest))
						)
						(Print 1 14)
						(event claimed: FALSE)
					)
				)
				(event claimed: FALSE)
			)
			(else
				(super handleEvent: event)
			)
		)
	))

(instance enterCastle of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21)
					number: 78
					priority: 15
					loop: 1
					init:
					play:
				)
				(gate
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					startUpd:
					setMotion: MoveTo 160 25 self
				)
			)
			(1
				((ScriptID 0 21) stop:)
				(gate stopUpd:)
				((ScriptID 0 23) number: 11 loop: -1 play:)
				(if (Btst fInvisible)
					(Print 1 15)
					(Bclr fInvisible)
					(ego view: 0)
					(NormalEgo)
				)
				(ego
					illegalBits: 0
					ignoreActors:
					setLoop: 3
					setMotion: MoveTo (ego x?) 76 self
				)
			)
			(2
				(SolvePuzzle fEndGame 3)
				(HandsOn)
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance exitCastle of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gate posn: 160 25 ignoreActors: init: stopUpd:)
				(ego
					setPri: -1
					view: 0
					loop: 2
					illegalBits: 0
					ignoreHorizon:
					posn: 164 76
					setCycle: StopWalk 2
					init:
					setMotion: MoveTo 164 86 self
				)
			)
			(1
				((ScriptID 0 21) number: 79 init: play:)
				(gate setMotion: MoveTo 160 75 self)
			)
			(2
				((ScriptID 0 21) number: 80 play:)
				(gate setMotion: MoveTo 160 80 self)
			)
			(3
				(gate ignoreActors: 0 stopUpd:)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance g1 of RPicView
	(properties
		x 203
		y 85
		description {guard}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 186
		signal notUpd
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/guard,man')
				(self doVerb: verbLook)
			)
			((Said 'talk,speak,ask/guard,man')
				(Print 1 16)
			)
			((Said 'give[/anyword]/guard')
				(Print 1 17)
			)
			((Said 'attack,kick,kill/guard')
				(Print 1 18)
			)
			((Said 'kiss,hug/guard')
				(Print 1 19)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 20)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance g2 of RPicView
	(properties
		x 119
		y 85
		description {guard}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 186
		cel 1
		signal notUpd
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 20)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gate of Actor
	(properties
		x 160
		y 80
		view 201
		signal notUpd
		illegalBits $0000
	)
	
	(method (init)
		(self yStep:
			(cond 
				((CheckHowFast 2) 2)
				((CheckHowFast 1) 4)
				((CheckHowFast 0) 7)
			)
		)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/gate,door')
				(self doVerb: verbLook)
			)
			((Said 'enter/castle,gate,door')
				(Print 1 21)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 22)
			)
		)
	)
)

(instance urn1 of RPicView
	(properties
		x 35
		y 103
		description {urn}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 267
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check/planter,planter,planter,caldron')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 23)
			)
		)
	)
)

(instance urn2 of RPicView
	(properties
		x 292
		y 103
		description {urn}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 267
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 23)
			)
		)
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/moat,water,brook')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 24)
			)
		)
	)
)

(instance vine1 of NewFeature
	(properties
		x 37
		y 24
		noun '/vine,ivy,bury,ceder'
		nsTop -1
		nsBottom 50
		nsRight 74
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine2 of NewFeature
	(properties
		x 35
		y 56
		noun '/vine,ivy,bury,ceder'
		nsTop 50
		nsLeft 29
		nsBottom 62
		nsRight 41
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine3 of NewFeature
	(properties
		x 282
		y 24
		noun '/vine,ivy,bury,ceder'
		nsLeft 246
		nsBottom 49
		nsRight 319
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine4 of NewFeature
	(properties
		x 284
		y 56
		noun '/vine,ivy,bury,ceder'
		nsTop 50
		nsLeft 279
		nsBottom 62
		nsRight 290
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)
