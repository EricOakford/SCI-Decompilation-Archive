;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
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
	Room8 0
)
(synonyms
	(bayou water)
)

(local
	local0
	local1
	local2
	local3
)
(instance Room8 of Rm
	(properties
		picture 8
	)
	
	(method (init)
		(= horizon 120)
		(= south 30)
		(= north 7)
		(super init:)
		(addToPics add: limb gator1 eachElementDo: #init doit:)
		(self setRegions: 205 setFeatures: gator1)
		(LoadMany 128 3 35 650)
		(Load rsSOUND 10 82)
		(if howFast
			(ripple1
				ignoreActors: 1
				cycleSpeed: 1
				setCycle: Fwd
				init:
			)
			(ripple2
				ignoreActors: 1
				cycleSpeed: 1
				setCycle: Fwd
				init:
			)
			(flyCage left: 160 right: 321 bottom: 191 top: 100 init:)
			(Fly
				setLoop: 6
				cel: 0
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setMotion: Wander 5
				cycleSpeed: 2
				setCycle: Fwd
				init:
			)
			(Fly2
				setLoop: 6
				cel: 1
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setMotion: Wander 5
				cycleSpeed: 2
				setCycle: Fwd
				init:
			)
			(Fly3
				setLoop: 6
				cel: 2
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setMotion: Wander 5
				cycleSpeed: 2
				setCycle: Fwd
				init:
			)
			(Fly4
				setLoop: 6
				cel: 3
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setMotion: Wander 5
				cycleSpeed: 2
				setCycle: Fwd
				init:
			)
			(Fly5
				setLoop: 6
				cel: 4
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: 1
				setMotion: Wander 5
				cycleSpeed: 2
				setCycle: Fwd
				init:
			)
		)
		(= local1 0)
		(if (and (== currentAct 3) (< global115 4))
			(self setRegions: 203)
		)
		(switch prevRoomNum
			(18 (ego posn: 1 130))
			(24 (ego posn: 1 170))
			(30 (ego posn: (ego x?) 188))
			(else  (ego posn: 5 125))
		)
		(ego view: 0 init:)
		(Gator
			cycleSpeed: 1
			init:
			stopUpd:
			setScript: gatorScript
		)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 8 0))
		(cond 
			((< (ego y?) 141) (= local3 1))
			((> (ego y?) 176) (= local3 2))
			((and (< (ego y?) 167) (> (ego y?) 151)) (= local3 3))
			(else (= local3 0))
		)
		(if
			(and
				(& (ego onControl: 1) $0002)
				(not local2)
				(not local0)
			)
			(= local2 1)
			(self setScript: sink)
		)
		(if (== (ego edgeHit?) 4)
			(if (< (ego y?) 165)
				(curRoom newRoom: 18)
			else
				(curRoom newRoom: 24)
			)
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
			(if
				(and
					(== (event type?) evSAID)
					(Said 'examine>')
					(Said '[<around,at][/room]')
				)
				(Print 8 0)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance gatorScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 1) (== (Gator cel?) 3))
			(myMusic number: 82 loop: 1 play:)
		)
		(if (> state 1)
			(cond 
				(local3 (if local1 (= cycles 1)))
				((not local1) (= cycles 1))
			)
			(if
				(and
					(< (ego distanceTo: Gator) 21)
					(== local1 0)
					(not local0)
					(& (ego onControl: 1) $0002)
				)
				(= local0 1)
				(self changeState: 6)
			)
		)
	)
	
	(method (changeState newState &tmp deathIconLastCel)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(Gator cycleSpeed: 2 setCycle: End self)
			)
			(2 (= local1 1))
			(3
				(= local1 0)
				(Gator
					illegalBits: -32671
					posn:
						(switch local3
							(1 105)
							(2 259)
							(3 167)
						)
						(switch local3
							(1 133)
							(2 173)
							(3 159)
						)
					loop: 5
					cycleSpeed: 0
					setCycle: End self
				)
			)
			(4
				(Gator loop: 6 setCycle: Fwd)
			)
			(5
				(= local1 (= state 1))
				(Gator loop: 5 cel: 3 setCycle: Beg self)
			)
			(6
				(HandsOff)
				(cSound stop:)
				(myMusic number: 10 loop: 1 play:)
				(if (== local3 1) (Gator setPri: 9))
				(Gator
					view: 3
					loop: 0
					cel: 3
					illegalBits: 0
					posn: (- (ego x?) 3) (+ (ego y?) 4)
					setCycle: End self
					init:
				)
				(ego hide:)
			)
			(7 (= seconds 3))
			(8
				(myMusic stop:)
				(deathIcon view: 650 loop: 0)
				(= deathIconLastCel (deathIcon lastCel:))
				(= cIcon deathIcon)
				(= deathLoop 0)
				(= deathCel deathIconLastCel)
				(EgoDead 8 1)
			)
		)
	)
)

(instance sink of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (> (ego cel?) 1)) (= local0 1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Room8 south: 0 north: 0)
				(HandsOff)
				(cSound stop:)
				(myMusic number: 82 loop: 1 play:)
				(ego
					view: 35
					cel: 0
					cycleSpeed: 3
					xStep: 3
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
					setCycle: End self
				)
			)
			(1 (ego hide:) (= seconds 3))
			(2
				(if (== (Gator loop?) 0)
					(myMusic stop:)
					(client setScript: 0)
				else
					(deathIcon view: 13 loop: 5 cel: 0)
					(= cIcon deathIcon)
					(= deathLoop 5)
					(= deathCel 0)
					(= cyclingIcon 1)
					(EgoDead 8 2)
				)
			)
		)
	)
)

(instance Gator of Act
	(properties
		y 148
		x 275
		view 108
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/alligator')) (event claimed: 1) (Print 8 3))
			((Said '/alligator>')
				(cond 
					((Said 'get,capture/alligator') (Print 8 4))
					((Said 'pat/alligator') (Print 8 5))
					((Said 'converse/alligator') (Print 8 6))
					((Said 'kiss/alligator') (Print 8 7))
					((Said 'kill/alligator') (Print 8 8))
				)
			)
			(
				(or
					(Said 'feed,deliver/alligator')
					(Said 'feed,deliver/*/alligator')
					(Said 'feed,deliver/*<alligator')
				)
				(if theInvItem
					(if haveInvItem (Print 8 9) else (DontHave))
				else
					(Print 8 9)
				)
			)
		)
	)
)

(instance ripple1 of Prop
	(properties
		y 110
		x 306
		view 108
	)
)

(instance ripple2 of Prop
	(properties
		y 140
		x 263
		view 108
		loop 1
	)
)

(instance myMusic of Sound
	(properties
		number 10
		loop 0
	)
)

(instance limb of RPicView
	(properties
		y 144
		x 148
		view 108
		loop 8
	)
)

(instance gator1 of RPicView
	(properties
		y 78
		x 265
		view 108
		loop 8
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 8 3)
		)
	)
)

(instance deathIcon of DCIcon
	(properties)
	
	(method (init)
		((= cycler (End new:)) init: self)
	)
)

(instance Fly of Act
	(properties
		y 123
		x 274
		view 130
	)
)

(instance Fly2 of Act
	(properties
		y 179
		x 297
		view 130
	)
)

(instance Fly3 of Act
	(properties
		y 139
		x 287
		view 130
	)
)

(instance Fly4 of Act
	(properties
		y 179
		x 257
		view 130
	)
)

(instance Fly5 of Act
	(properties
		y 139
		x 197
		view 130
	)
)

(instance flyCage of Cage
	(properties)
)
