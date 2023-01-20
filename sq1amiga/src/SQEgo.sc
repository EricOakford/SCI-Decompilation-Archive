;;; Sierra Script 1.0 - (do not remove this comment)
(script# 813)
(include sci.sh)
(use StopWalk)
(use User)
(use Actor)


(local
	[theCel 8] = [0 4 2 5 1 7 3 6]
)
(class SQEgo of Ego
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $2000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		edgeHit 0
		_head 0
		normal 1
		moveHead 1
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
		(if (not (& (_head signal?) $0008)) (_head showSelf:))
	)
)

(class Head of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		client 0
		_cnt 0
		_offSet 0
	)
	
	(method (init param1)
		(self client: param1 ignoreActors: 1)
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
					(not (& (client signal?) $0008))
					(client isStopped:)
					(IsObject (client cycler?))
					((client cycler?) isKindOf: StopWalk)
					(== (client view?) ((client cycler?) vStopped?))
				)
				(if (and (& signal $0004) (not (& signal $0002)))
					(if (& (client signal?) $0004)
						(return)
					else
						(self startUpd:)
					)
				)
				(self showSelf:)
				(self lookAround:)
			)
			((not (& signal $0008)) (self hide:))
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
			view: (client view?)
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
