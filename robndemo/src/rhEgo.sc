;;; Sierra Script 1.0 - (do not remove this comment)
(script# RHEGO)
(include game.sh)
(use StopWalk)
(use User)
(use Actor)


(local
	headCel = [0 4 6 2 7 5 1 3]
)


(class rhEgo of Ego
	(properties
		_head 0
		normal TRUE
		moveHead TRUE
	)
	
	(method (dispose)
		;EO: These decompiled as --UNKNOWN-PROP-NAME--.
		;Referring to the full game's script, I have identified them.
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

(class rhActor of Actor
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
			(= cel [headCel (client loop?)])
			(= _cnt cycleSpeed)
			(self loop: (- (NumLoops self) 1) show:)
		)
		(self x: (client x?) y: (client y?))
	)
	
	(method (lookAround &tmp i)
		(if (and (client moveHead?) (< (-- _cnt) 1))
			(= _cnt cycleSpeed)
			(= cel (+ [headCel (client loop?)] (- (Random 0 2) 1)))
			(if (> cel 7)
				(= cel 0)
			)
			(if (< cel 0)
				(= cel 7)
			)
		)
	)
)
