;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include game.sh)
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
	henchState
	local1
)
(instance rm47 of Room
	(properties
		picture 47
		horizon 5
		east 48
		west 42
	)
	
	(method (init)
		(Load VIEW 410)
		(Load VIEW 411)
		(Load VIEW 413)
		(Load VIEW 511)
		(Load VIEW 406)
		(super init:)
		(addToPics add: aLumbrella aRumbrella aFeet doit:)
		(aWave
			setCycle: Forward
			cycleSpeed: 6
			isExtra: TRUE
			init:
		)
		(aPlane
			setPri: 3
			illegalBits: 0
			setStep: 1 1
			init:
			hide:
		)
		(aHench1
			setCycle: Walk
			setStep: 4 3
			ignoreControl: cYELLOW
			init:
			setAvoider: (Avoider new:)
		)
		(aHench2
			setCycle: Walk
			setStep: 4 3
			init:
			setAvoider: (Avoider new:)
		)
		(cond 
			((> 99 (ego y?))
				(ego y: 98)
			)
			((< 134 (ego y?))
				(ego y: 133)
			)
		)
		(NormalEgo)
		(ego x: 3 observeControl: cYELLOW init:)
		(if (and gotHaircutAtResort (== currentEgoView 151))
			(= henchState 8)
		)
		(self setRegions: BEACH setScript: rm47Script)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
)

(instance rm47Script of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (== henchState 0) (ego inRect: 86 2 333 140))
				(= henchState 1)
				(aHench1 setScript: hench1Script)
				(aHench2 setScript: hench2Script)
			)
			((and (== henchState 8) (ego inRect: 86 2 333 140))
				(= henchState 9)
				(Print 47 0)
				(Print 47 1 #at -1 130)
			)
			((== EAST (ego edgeHit?))
				(HandsOff)
				(theGame changeScore: 12)
				(Print 47 2)
				(Print 47 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'call/agent,man')
			(Print 47 4)
		)
		(if (Said 'look>')
			(if (Said '/man,agent')
				(Print 47 5)
			)
			(if (Said '[/airport,bush]')
				(Print 47 6)
			)
		)
	)
)

(instance hench1Script of Script
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
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(client setLoop: -1 setMotion: Chase ego 11 self)
			)
			(1
				(if (== henchState 1)
					(= currentStatus egoSTOPPED)
					(= henchState 2)
					(Print 47 7)
					(HandsOff)
					(ego stopUpd:)
					(aPlane show: setMotion: MoveTo 116 13)
					(= theY (- (ego y?) 1))
					(if (< (ego x?) (client x?))
						(= theX (+ (ego x?) 11))
						(client setLoop: 1)
					else
						(= theX (- (ego x?) 11))
						(client setLoop: 0)
					)
					(client
						view: 413
						ignoreActors:
						illegalBits: 0
						posn: theX theY
						setMotion: 0
						setPri: (+ (ego priority?) 1)
						cel: 0
						setCycle: EndLoop self
					)
				)
			)
			(2
				(= seconds 3)
				(cond 
					((== currentEgoView 151)
						(Print 47 8)
						(Print 47 9)
						(Print 47 10)
					)
					((== currentEgoView 150)
						(Print 47 11)
						(Print 47 9)
						(Print 47 12)
					)
					((== currentEgoView 149)
						(Print 47 13)
						(Print 47 9)
						(Print 47 14)
					)
					((== currentEgoView vEgo)
						(Print 47 15)
					)
					(else
						(Print 47 16)
					)
				)
			)
			(3
				(Print 47 17)
				(Print 47 18)
				(= seconds 4)
			)
			(4
				(Print 47 19)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 96)
			)
		)
	)
)

(instance aLumbrella of PicView
	(properties
		y 143
		x 181
		view 410
		cel 1
		priority 10
	)
)

(instance aRumbrella of PicView
	(properties
		y 124
		x 282
		view 410
		loop 1
		priority 8
	)
)

(instance aFeet of PicView
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

(instance aPlane of Actor
	(properties
		y 24
		x 322
		view 511
		loop 5
		signal ignrAct
	)
)

(instance aHench1 of Actor
	(properties
		y 133
		x 159
		view 411
		loop 1
	)
)

(instance aHench2 of Actor
	(properties
		y 151
		x 54
		view 411
	)
)
