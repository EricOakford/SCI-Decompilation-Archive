;;; Sierra Script 1.0 - (do not remove this comment)
(script# LLEGO)
(include game.sh)
(use Main)
(use StopWalk)
(use User)
(use Actor)


(local
	theCel = [0 4 2 5 1 7 3 6]
)
(class LLEgo of Ego
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
	
	(method (egoSpeed newSpeed)
		(if argc
			(self cycleSpeed: newSpeed moveSpeed: newSpeed)
		else
			(self cycleSpeed: global169 moveSpeed: global169)
		)
	)
	
	(method (userSpeed)
	)
)

(class Head of Prop
	(properties
		client 0
		_cnt 0
		_offSet 0
	)
	
	(method (init whoCares)
		(self client: whoCares ignoreActors: TRUE)
		(= loop (- (NumLoops self) 1))
		(whoCares _head: self)
		(super init:)
		(self hide:)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(client normal?)
					(not (& (client signal?) actorHidden))
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
	
	(method (showSelf &tmp the_offSet)
		(if (& signal hideActor)
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
	
	(method (lookAround &tmp i)
		(if (and (client moveHead?) (< (-- _cnt) 1))
			(= _cnt cycleSpeed)
			(if (> (= i (+ _offSet (- (Random 0 2) 1))) 7)
				(= i 0)
			)
			(if (< i 0)
				(= i 7)
			)
			(= cel [theCel i])
		)
	)
)
