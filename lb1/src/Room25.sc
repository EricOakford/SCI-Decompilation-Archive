;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include sci.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Wander)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room25 0
)
(synonyms
	(bird owl)
)

(local
	local0
	local1
	local2
	local3
)
(instance Room25 of Rm
	(properties
		picture 25
	)
	
	(method (init)
		(= horizon 84)
		(= east 26)
		(= north 13)
		(super init:)
		(Load rsSOUND 82)
		(if howFast
			(owlHead setScript: owl init:)
			(light1 setScript: showers init:)
			(light2 init:)
			(light3 init:)
			(flyCage left: -2 right: 82 bottom: 155 top: 100 init:)
			(Fly
				setLoop: 5
				cel: 0
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setCycle: Fwd
				cycleSpeed: 2
				setMotion: Wander 5
				init:
			)
			(Fly2
				setLoop: 5
				cel: 1
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setCycle: Fwd
				cycleSpeed: 2
				setMotion: Wander 5
				init:
			)
			(Fly3
				setLoop: 5
				cel: 2
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setCycle: Fwd
				cycleSpeed: 2
				setMotion: Wander 5
				init:
			)
			(Fly4
				setLoop: 5
				cel: 3
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setCycle: Fwd
				cycleSpeed: 2
				setMotion: Wander 5
				init:
			)
		else
			(owlHead loop: 4 cel: 2 addToPic:)
		)
		(self setRegions: 205 207 setFeatures: owlBody Barn House)
		(Load rsVIEW 35)
		(Thunder number: 17 loop: 0)
		(addToPics add: owlBody eachElementDo: #init doit:)
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(if
			(or
				(and (== currentAct 3) (!= global114 10))
				(and (== currentAct 6) (not (& global118 $0002)))
			)
			(self setRegions: 281)
		)
		(if (>= currentAct 4)
			(Foot ignoreActors: 1 init: stopUpd:)
		)
		(if
			(and
				(>= currentAct 4)
				(== ((inventory at: 4) owner?) 25)
			)
			(Pin init: stopUpd:)
		)
		(switch prevRoomNum
			(20 (ego posn: 305 119))
			(13 (ego posn: 171 119))
		)
		(ego view: 0 illegalBits: -32768 init:)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 25 0))
		(if (& (ego onControl: 0) $0002) (curRoom newRoom: 20))
		(if (& (ego onControl: 0) $0004) (curRoom newRoom: 13))
		(if
		(and (& (ego onControl: 1) $0008) (== local0 0))
			(= local0 1)
			(self setScript: sink)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 976)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(DisposeScript 990)
				(if (Said 'examine>')
					(cond 
						((Said '[<around,at][/room]') (Print 25 0))
						((Said '/drive') (Print 25 1))
						((Said '/bootprint')
							(if (== currentAct 4)
								(Print 25 2)
							else
								(event claimed: 0)
							)
						)
						((or (Said '/dirt') (Said '<down'))
							(cond 
								((cast contains: Pin) (Print 25 3))
								((== currentAct 4) (Print 25 4))
								(else (event claimed: 0))
							)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance showers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (= state 3)))
			(1
				(light1 setCycle: Fwd)
				(light2 setCycle: Fwd)
				(light3 setCycle: Fwd)
				(= cycles 7)
			)
			(2
				(light1 setCycle: End)
				(light2 setCycle: End)
				(light3 setCycle: End self)
			)
			(3 (Thunder loop: 1 play: self))
			(4
				(if (< (Random 1 100) 20) (= state 0))
				(= cycles 7)
			)
			(5 (= state 3) (= seconds 5))
		)
	)
)

(instance sink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(myMusic number: 82 loop: 1 play:)
				(ego
					view: 35
					cel: 0
					xStep: 1
					setMotion:
						MoveTo
						(switch (ego loop?)
							(0 (+ (ego x?) 10))
							(1 (- (ego x?) 10))
							(else  (ego x?))
						)
						(switch (ego loop?)
							(2 (+ (ego y?) 3))
							(3 (- (ego y?) 3))
							(else  (ego y?))
						)
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(1 (ego hide:) (= seconds 3))
			(2
				(= cIcon myIcon)
				(= deathLoop 5)
				(= deathCel 0)
				(= cyclingIcon 1)
				(EgoDead 25 5)
			)
		)
	)
)

(instance owl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 7)))
			(1
				(if (== (owlHead cel?) 0)
					(owlHead setCycle: End self)
					(= local2 (Random 0 3))
				else
					(owlHead setCycle: Beg self)
					(= state -1)
				)
			)
			(2
				(if local2 (= cycles 1) else (= seconds (Random 2 5)))
			)
			(3
				(if local2
					(owlHead loop: 4)
					(= seconds (Random 2 5))
				else
					(= state 0)
					(= cycles 1)
				)
			)
			(4
				(owlHead loop: 3)
				(= cycles 5)
			)
			(5
				(if (-- local2) (= state 2) else (= state 0))
				(= cycles 1)
			)
		)
	)
)

(instance lookFoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (ego loop?) 3)
					(ego view: 125 cel: 0 loop: 7 setCycle: End self)
				else
					(ego view: 125 cel: 0 loop: 6 setCycle: End self)
				)
			)
			(1
				(Print 25 6 #icon 640 0 0)
				(Bset 4)
				(= cycles 1)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego view: 0 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego Pin)
				(= cycles 2)
			)
			(1
				(if (== (ego loop?) 3)
					(ego view: 125 cel: 0 loop: 7 setCycle: End self)
				else
					(ego view: 125 cel: 0 loop: 6 setCycle: End self)
				)
			)
			(2
				(Print 25 7)
				(Pin hide:)
				(= gotItem 1)
				(ego get: 4)
				(= cycles 2)
			)
			(3 (ego setCycle: Beg self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(client dispose: setScript: 0)
			)
		)
	)
)

(instance owlBody of RPicView
	(properties
		y 117
		x 88
		view 125
		loop 4
		cel 1
		priority 15
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {owl})
		)
	)
)

(instance light1 of Prop
	(properties
		y 47
		x 28
		view 125
		cel 1
	)
)

(instance light2 of Prop
	(properties
		y 102
		x 28
		view 125
		loop 1
		cel 1
	)
)

(instance light3 of Prop
	(properties
		y 68
		x 106
		view 125
		loop 2
		cel 1
	)
)

(instance owlHead of Prop
	(properties
		y 93
		x 96
		view 125
		loop 3
		priority 15
		signal $0010
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'deliver,feed,hold>')
				(if
				(or (Said '/*<bird') (Said '/bird') (Said '/*/bird'))
					(event claimed: 1)
					(if theInvItem
						(if haveInvItem (Print 25 8) else (DontHave))
					else
						(Print 25 8)
					)
				)
			)
			((Said 'converse/bird') (Print 25 9))
			((Said 'capture,get/bird') (Print 25 10))
			(
			(or (MousedOn self event 3) (Said 'examine/bird')) (event claimed: 1) (Print 25 11))
		)
	)
)

(instance Foot of Prop
	(properties
		y 128
		x 210
		view 125
		loop 8
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'examine<use<monocle/bootprint')
					(Said '(examine<at),examine/bootprint/monocle<with')
				)
				(if (ego has: 1)
					(if (< (ego distanceTo: Foot) 10)
						(if (>= currentAct 4)
							(HandsOff)
							(= local3 1)
							(self setScript: lookFoot)
						else
							(Print 25 12)
						)
					else
						(NotClose)
					)
				else
					(DontHave)
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/bootprint')) (event claimed: 1) (Print 25 4))
		)
	)
)

(instance Pin of Prop
	(properties
		y 128
		x 190
		view 125
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/pin<rolling')
				)
				(event claimed: 1)
				(Print 25 13)
			)
			((Said 'get/pin<rolling')
				(if (< (ego distanceTo: Pin) 20)
					(self setScript: pickUp)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance Thunder of Sound
	(properties)
)

(instance myMusic of Sound
	(properties)
)

(instance myIcon of DCIcon
	(properties
		view 13
		loop 5
		cycleSpeed 16
	)
	
	(method (init)
		((= cycler (End new:)) init: self)
	)
)

(instance Fly of Act
	(properties
		y 123
		x 74
		view 125
	)
)

(instance Fly2 of Act
	(properties
		y 150
		x 37
		view 125
	)
)

(instance Fly3 of Act
	(properties
		y 139
		x 17
		view 125
	)
)

(instance Fly4 of Act
	(properties
		y 130
		x 67
		view 125
	)
)

(instance flyCage of Cage
	(properties)
)

(instance Barn of RFeature
	(properties
		nsTop 47
		nsLeft 101
		nsBottom 76
		nsRight 144
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/barn'))
			(event claimed: 1)
			(Print 25 14)
		)
	)
)

(instance House of RFeature
	(properties
		nsTop 63
		nsLeft 292
		nsBottom 93
		nsRight 319
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/cabin'))
			(event claimed: 1)
			(Print 25 15)
		)
	)
)
