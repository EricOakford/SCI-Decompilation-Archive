;;; Sierra Script 1.0 - (do not remove this comment)
(script# 385)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	RudySearch 0
)
(synonyms
	(rudolph person fellow)
)

(local
	[local0 6] = [246 158 76 150 192 112]
	local6
	local7
	local8
)
(instance Rudy of Act
	(properties
		y 146
		x 173
		view 380
	)
	
	(method (handleEvent event)
		(cond 
			(
			(and (not (& global207 $0100)) (MousedOn self event 3))
				(event claimed: 1)
				(= global207 (| global207 $0100))
				(Say 0 385 0)
			)
			(
				(and
					(& global207 $0100)
					(or (MousedOn self event 3) (Said 'examine/rudolph'))
				)
				(event claimed: 1)
				(Print 385 1)
			)
			((Said 'ask,tell//*<about')
				(= theTalker 9)
				(switch local8
					(0 (Say 1 385 2) (++ local8))
					(1 (Print 385 3))
				)
			)
			((Said 'hold,deliver/*')
				(if (and theInvItem haveInvItem)
					(Print 385 3)
				else
					(DontHave)
				)
			)
			((Said '/rudolph>')
				(cond 
					((Said 'converse')
						(= theTalker 9)
						(switch local7
							(0 (Say 1 385 4))
							(1 (Say 1 385 5))
							(else  (Print 385 6))
						)
						(++ local7)
					)
					((Said 'get') (Print 385 7))
					((Said 'kill') (Print 385 8))
					((Said 'kiss,embrace') (Print 385 9))
				)
			)
			((Said 'flirt//rudolph<with') (Print 385 10))
		)
	)
)

(instance RudySearch of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(= global195 256)
		(Load rsVIEW 393)
		(self setScript: searching)
		(Rudy
			setAvoider: ((Avoid new:) offScreenOK: 1)
			illegalBits: -32768
			init:
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
		(if (event claimed?) (return))
	)
)

(instance searching of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0004))
						(= global118 (| global118 $0004))
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(Rudy
					view: 380
					cycleSpeed: 0
					setCycle: Walk
					setMotion:
						MoveTo
						[local0 (* local6 2)]
						[local0 (+ (* local6 2) 1)]
						self
				)
			)
			(2
				(Rudy view: 393 cel: 0 cycleSpeed: 1 setCycle: End self)
			)
			(3 (= seconds 3))
			(4
				(Rudy setCycle: Beg self)
				(if (< local6 2) (++ local6) (= state -1))
			)
			(5
				(Rudy
					view: 380
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo -20 152 self
				)
			)
			(6
				(Rudy dispose:)
				(client setScript: 0)
			)
		)
	)
)
