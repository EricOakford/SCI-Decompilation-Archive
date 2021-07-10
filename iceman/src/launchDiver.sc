;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	launchDiver 0
)

(instance launchDiver of Rm
	(properties
		picture 50
		east 51
		west 51
	)
	
	(method (init)
		(super init:)
		(HandsOn)
		(User canControl: 0)
		(ego
			view: 50
			setLoop: (if (ego has: 6) 1 else 3)
			posn: 170 58
			setPri: -1
			xStep: 1
			yStep: 1
			cycleSpeed: 1
			ignoreActors: 0
			init:
			setCycle: Fwd
			setScript: egoLeavesSubScript
			put: 2
		)
		(bubbles init:)
		(soundBubbles
			number: 60
			init:
			owner: self
			play: egosBubbleScript
		)
		(= theQueuedSound 8800)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/water]') (Print 50 0))
			(
				(Said
					'examine,look[<at]/gear,scuba,coat,wetsuit,equipment'
				)
				(Print 50 1)
			)
		)
	)
)

(instance egoLeavesSubScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 50 2)
				(if (ego has: 6) (Print 50 3))
				(ego setMotion: MoveTo -5 58)
				(= cycles 160)
			)
			(1 (Print 50 4))
		)
	)
)

(instance egosBubbleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(bubbles
					x: (- (ego x?) 4)
					y: (ego y?)
					show:
					setPri: (ego priority?)
					setMotion: MoveTo (ego x?) 5 self
				)
			)
			(2
				(bubbles hide:)
				(= cycles 2)
			)
			(3 (self init:))
		)
	)
)

(instance bubbles of Act
	(properties
		yStep 1
		view 50
		loop 4
	)
	
	(method (init)
		(self
			setCycle: Walk
			setLoop: 4
			ignoreActors:
			illegalBits: 0
			setScript: egosBubbleScript
		)
		(super init:)
		(self hide:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/bubbles]') (Print 50 5))
		)
	)
)

(instance soundBubbles of Sound
	(properties
		number 60
		priority 1
		loop -1
	)
)
