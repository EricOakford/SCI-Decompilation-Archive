;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	dustAct1 0
)
(synonyms
	(fifi person girl)
)

(local
	local0
	[local1 2]
)
(instance dustAct1 of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(= global195 16)
		(LoadMany 128 470 904)
		(Fifi
			view: 464
			setAvoider: (Avoid new:)
			illegalBits: -16378
			init:
			setScript: fifiActions
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(return (if (event claimed?) (return 1) else 0))
	)
)

(instance fifiActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Fifi
					view: 464
					setPri: -1
					setCycle: Walk
					illegalBits: -16378
					ignoreActors: 0
				)
				(Fifi
					setMotion:
						MoveTo
						(switch local0
							(0 225)
							(1 62)
							(2 106)
							(3 208)
							(4 151)
							(5 166)
							(6 196)
							(7 209)
						)
						(switch local0
							(0 153)
							(1 167)
							(2 115)
							(3 108)
							(4 93)
							(5 93)
							(6 93)
							(7 108)
						)
						self
				)
			)
			(1
				(Fifi
					view: 470
					setPri: (if (== local0 1) 14 else -1)
					cel: 0
					illegalBits: 0
					loop:
						(switch local0
							(0 1)
							(1 5)
							(2 1)
							(3 4)
							(4 1)
							(5 0)
							(6 4)
							(7 4)
						)
					setCycle: End self
				)
			)
			(2
				(Fifi
					loop:
						(switch local0
							(0 3)
							(1 7)
							(2 3)
							(3 6)
							(4 3)
							(5 2)
							(6 6)
							(7 6)
						)
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(3
				(Fifi cel: 2 setCycle: Beg self)
				(if (< local0 7) (++ local0) else (= local0 0))
				(= state -1)
			)
		)
	)
)

(instance Fifi of Act
	(properties
		y 140
		x 196
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'deliver,hold/*')
				(if (and theInvItem haveInvItem)
					(Print 380 0)
				else
					(DontHave)
				)
			)
			((Said 'ask,tell//*<about') (Print 380 0))
			(
			(or (MousedOn self event 3) (Said 'examine/fifi'))
				(event claimed: 1)
				(if (not (& global207 $0010))
					(= global207 (| global207 $0010))
					(= theTalker 5)
					(Say 0 380 1)
				else
					(Print 380 2)
				)
			)
			((Said '/fifi>')
				(cond 
					((Said 'get') (Print 380 3))
					((Said 'kill') (Print 380 4))
					((Said 'kiss') (Print 380 5))
					((Said 'embrace') (Print 380 6))
					((Said 'converse') (Print 380 0))
				)
			)
		)
	)
)
