;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQEGO) ;813
(include game.sh)
(use StopWalk)
(use Ego)
(use Actor)


(local
	[theCel 8] = [0 4 2 5 1 7 3 6]
)
(class SQEgo of Ego
	(properties
		_head 0
		normal 1
		moveHead 1
	)
	
	(method (init)
		(super init:)
	)
	
	(method (dispose)
		(if _head (_head dispose:))
		(super dispose:)
	)
	
	(method (stopUpd)
		(if _head (_head stopUpd:))
		(super stopUpd:)
	)
	
	(method (hide)
		(if _head (_head hide:))
		(super hide:)
	)
	
	(method (headView param1)
		(_head view: param1)
		(if (not (& (_head signal?) hideActor)) (_head showSelf:))
	)
)

(class Head of Prop
	(properties
		client 0
		_cnt 0
		_offSet 0
	)
	
	(method (init param1)
		(self client: param1 ignoreActors: TRUE)
		(= loop (- (NumLoops self) 1))
		(param1 _head: self)
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
			((not (& signal hideActor)) (self hide:))
		)
		(super doit:)
	)
	
	(method (showSelf &tmp the_offSet)
		(if (& signal $0008)
			(self cel: (client loop?))
			(= _cnt cycleSpeed)
			(= the_offSet 0)
			(while (<= the_offSet 7)
				(if (== cel [theCel the_offSet]) (= _offSet the_offSet))
				(++ the_offSet)
			)
		)
		(self
			setPri: (client priority?)
			loop: (- (NumLoops self) 1)
			x: (client x?)
			y: (client y?)
			z: (CelHigh view (client loop?) (client cel?))
			show:
		)
	)
	
	(method (lookAround &tmp temp0)
		(if (and (client moveHead?) (< (-- _cnt) 1))
			(= _cnt cycleSpeed)
			(if
			(> (= temp0 (+ _offSet (- (Random 0 2) 1))) 7)
				(= temp0 0)
			)
			(if (< temp0 0) (= temp0 7))
			(= cel [theCel temp0])
		)
	)
)
