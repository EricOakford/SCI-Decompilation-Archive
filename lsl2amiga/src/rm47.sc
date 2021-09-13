;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include sci.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm47 0
)

(local
	local0
	local1
)
(instance rm47 of Rm
	(properties
		picture 47
		horizon 5
		east 48
		west 42
	)
	
	(method (init)
		(Load rsVIEW 410)
		(Load rsVIEW 411)
		(Load rsVIEW 413)
		(Load rsVIEW 511)
		(Load rsVIEW 406)
		(super init:)
		(addToPics add: aLumbrella aRumbrella aFeet doit:)
		(aWave setCycle: Fwd cycleSpeed: 6 isExtra: 1 init:)
		(aPlane setPri: 3 illegalBits: 0 setStep: 1 1 init: hide:)
		(aHench1
			setCycle: Walk
			setStep: 4 3
			ignoreControl: 16384
			init:
			setAvoider: (Avoid new:)
		)
		(aHench2
			setCycle: Walk
			setStep: 4 3
			init:
			setAvoider: (Avoid new:)
		)
		(cond 
			((> 99 (ego y?)) (ego y: 98))
			((< 134 (ego y?)) (ego y: 133))
		)
		(NormalEgo)
		(ego x: 3 observeControl: 16384 init:)
		(if
		(and gotHaircutAtResort (== currentEgoView 151))
			(= local0 8)
		)
		(self setRegions: 401 setScript: rm47Script)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
)

(instance rm47Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (== local0 0) (ego inRect: 86 2 333 140))
				(= local0 1)
				(aHench1 setScript: hench1Script)
				(aHench2 setScript: hench2Script)
			)
			((and (== local0 8) (ego inRect: 86 2 333 140)) (= local0 9) (Print 47 0) (Print 47 1 #at -1 130))
			((== 2 (ego edgeHit?))
				(HandsOff)
				(theGame changeScore: 12)
				(Print 47 2)
				(Print 47 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'call/agent,man') (Print 47 4))
		(if (Said 'look>')
			(if (Said '/man,agent') (Print 47 5))
			(if (Said '[/airport,bush]') (Print 47 6))
		)
	)
)

(instance hench1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aHench1 setMotion: MoveTo 138 134 self)
			)
			(1
				(aHench1 setScript: (henchScript new:))
			)
		)
	)
)

(instance hench2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aHench2 setMotion: MoveTo 123 152 self)
			)
			(1
				(aHench2 setScript: (henchScript new:))
			)
		)
	)
)

(instance henchScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(client setLoop: -1 setMotion: Chase ego 11 self)
			)
			(1
				(if (== local0 1)
					(= currentStatus 1000)
					(= local0 2)
					(Print 47 7)
					(HandsOff)
					(ego stopUpd:)
					(aPlane show: setMotion: MoveTo 116 13)
					(= temp1 (- (ego y?) 1))
					(if (< (ego x?) (client x?))
						(= temp0 (+ (ego x?) 11))
						(client setLoop: 1)
					else
						(= temp0 (- (ego x?) 11))
						(client setLoop: 0)
					)
					(client
						view: 413
						ignoreActors:
						illegalBits: 0
						posn: temp0 temp1
						setMotion: 0
						setPri: (+ (ego priority?) 1)
						cel: 0
						setCycle: End self
					)
				)
			)
			(2
				(= seconds 3)
				(cond 
					((== currentEgoView 151) (Print 47 8) (Print 47 9) (Print 47 10))
					((== currentEgoView 150) (Print 47 11) (Print 47 9) (Print 47 12))
					((== currentEgoView 149) (Print 47 13) (Print 47 9) (Print 47 14))
					((== currentEgoView 100) (Print 47 15))
					(else (Print 47 16))
				)
			)
			(3
				(Print 47 17)
				(Print 47 18)
				(= seconds 4)
			)
			(4
				(Print 47 19)
				(= currentStatus 23)
				(curRoom newRoom: 96)
			)
		)
	)
)

(instance aLumbrella of PV
	(properties
		y 143
		x 181
		view 410
		cel 1
		priority 10
	)
)

(instance aRumbrella of PV
	(properties
		y 124
		x 282
		view 410
		loop 1
		priority 8
	)
)

(instance aFeet of PV
	(properties
		y 125
		x 200
		view 410
		priority 8
		signal $4000
	)
)

(instance aWave of Prop
	(properties
		y 49
		x 268
		view 406
		signal $4000
	)
)

(instance aPlane of Act
	(properties
		y 24
		x 322
		view 511
		loop 5
		signal $4000
	)
)

(instance aHench1 of Act
	(properties
		y 133
		x 159
		view 411
		loop 1
	)
)

(instance aHench2 of Act
	(properties
		y 151
		x 54
		view 411
	)
)
