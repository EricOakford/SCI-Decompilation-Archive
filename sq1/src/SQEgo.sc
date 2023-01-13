;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQEGO) ;813
(include game.sh)
(use StopWalk)
(use User)
(use Actor)


(local
	headCel = [0 4 2 5 1 7 3 6]
)
(class SQEgo of Ego
	(properties
		_head 0
		normal TRUE
		moveHead TRUE
	)
	
	(method (dispose)
		(if _head
			(_head dispose:)
		)
		(super dispose:)
	)
	
	(method (stopUpd)
		(if _head
			(_head stopUpd:)
		)
		(super stopUpd:)
	)
	
	(method (hide)
		(if _head
			(_head hide:)
		)
		(super hide:)
	)
	
	(method (headView theView)
		(_head view: theView)
		(if (not (& (_head signal?) hideActor))
			(_head showSelf:)
		)
	)
)

(class Head of Prop
	(properties
		client 0
		_cnt 0
		_offSet 0
	)
	
	(method (init owner)
		(self client: owner ignoreActors: TRUE)
		(= loop (- (NumLoops self) 1))
		(owner _head: self)
		(super init:)
		(self hide:)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(client normal?)
					(not (& (client signal?) hideActor))
					(client isStopped:)
					(IsObject (client cycler?))
					((client cycler?) isKindOf: StopWalk)
					(== (client view?) ((client cycler?) vStopped?))
				)
				(if (and (& signal notUpd) (not (& signal startUpdOn)))
					(if (& (client signal?) notUpd)
						(return)
					else
						(self startUpd:)
					)
				)
				(self showSelf:)
				(self lookAround:)
			)
			((not (& signal hideActor))
				(self hide:)
			)
		)
		(super doit:)
	)
	
	(method (showSelf &tmp i)
		(if (& signal hideActor)
			(self cel: (client loop?))
			(= _cnt cycleSpeed)
			(for ((= i 0)) (<= i 7) ((++ i))
				(if (== cel [headCel i]) (= _offSet i))
			)
		)
		(self
			setPri: (client priority?)
			view: (client view?)
			loop: (- (NumLoops self) 1)
			x: (client x?)
			y: (client y?)
			z: (CelHigh view (client loop?) (client cel?))
			show:
		)
	)
	
	(method (lookAround &tmp i)
		(if (and (client moveHead?) (< (-- _cnt) 1))
			(= _cnt cycleSpeed)
			(if (> (= i (+ _offSet (- (Random 0 2) 1))) 7)
				(= i 0)
			)
			(if (< i 0)
				(= i 7)
			)
			(= cel [headCel i])
		)
	)
)
