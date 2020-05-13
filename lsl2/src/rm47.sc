;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm47 0
)

(local
	aPlane
	aWave
	local2
	aHench1
	aHench2
	local5
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
		((View new:)
			view: 410
			loop: 0
			cel: 1
			posn: 181 143
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 410
			loop: 1
			cel: 0
			posn: 282 124
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 410
			loop: 0
			cel: 0
			posn: 200 125
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((= aWave (Prop new:))
			view: 406
			ignoreActors:
			posn: 268 49
			setCycle: Forward
			cycleSpeed: 6
			isExtra: 1
			init:
		)
		((= aPlane (Actor new:))
			view: 511
			setLoop: 5
			setCel: 0
			setPri: 3
			posn: 322 24
			illegalBits: 0
			ignoreActors:
			setStep: 1 1
			init:
			hide:
		)
		((= aHench1 (Actor new:))
			view: 411
			setLoop: 1
			setCycle: Walk
			setStep: 4 3
			ignoreControl: cYELLOW
			posn: 159 133
			init:
			setAvoider: (Avoider new:)
		)
		((= aHench2 (Actor new:))
			view: 411
			setLoop: 0
			setCycle: Walk
			setStep: 4 3
			posn: 54 151
			init:
			setAvoider: (Avoider new:)
		)
		(cond 
			((> 99 (ego y?)) (ego y: 98))
			((< 134 (ego y?)) (ego y: 133))
		)
		(NormalEgo)
		(ego x: 3 observeControl: cYELLOW init:)
		(if (and gotHaircutAtResort (== currentEgoView 151))
			(= local2 8)
		)
		(self setRegions: BEACH setScript: rm47Script)
	)
)

(instance rm47Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (== local2 0) (ego inRect: 86 2 333 140))
				(= local2 1)
				(aHench1 setScript: hench1Script)
				(aHench2 setScript: hench2Script)
			)
			((and (== local2 8) (ego inRect: 86 2 333 140)) (= local2 9) (Print 47 0) (Print 47 1 #at -1 152))
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
		(or (!= (event type?) saidEvent) (event claimed?))
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
	
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(client setLoop: -1 setMotion: Chase ego 11 self)
			)
			(1
				(if (== local2 1)
					(= currentStatus egoSTOPPED)
					(= local2 2)
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
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 96)
			)
		)
	)
)
