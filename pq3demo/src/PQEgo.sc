;;; Sierra Script 1.0 - (do not remove this comment)
(script# PQEGO)
(include game.sh)
(use Main)
(use Grooper)
(use User)
(use Actor)

(public
	Body 0
)

(local
	headCel = [6 0 4 5 1 7 4 2 5 7 3 6 0 4 2 2 5 1 3 6 0 1 7 3]
	local24 =  3
	theXOffset = [0 -1 0 0 0 0 0 0 0 -1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0 1 0 0 0 0 0 0 0 1]
	theYOffset = [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0 1 1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 4 0 0 1 1 0 0 0 0 0 0 0 0 -1 0 0 0 0 -1]
)
(class Body of Ego
	(properties
		head 0
		normal TRUE
		moveHead TRUE
	)
	
	(method (doit &tmp bodyLoop temp1)
		(super doit:)
		(cond 
			((self isStopped:)
				(if
					(and
						(!=
							(= bodyLoop (self loop?))
							(= temp1 (- (NumLoops self) 1))
						)
						(cast contains: self)
						(IsObject (self cycler?))
						(not ((self cycler?) isKindOf: GradualCycler))
						(self normal?)
					)
					(self loop: temp1 cel: bodyLoop)
				)
			)
			(
				(and
					(== (self loop?) (- (NumLoops self) 1))
					(not (& signal $0800))
				)
				(self loop: (self cel?))
			)
		)
	)
	
	(method (dispose)
		(if head (head dispose:))
		(super dispose:)
	)
	
	(method (stopUpd)
		(if head (head stopUpd:))
		(super stopUpd:)
	)
	
	(method (hide)
		(if head (head hide:))
		(super hide:)
	)
)

(class Head of Prop
	(properties
		client 0
		cnt 0
		offSet 0
		xOffset 0
		yOffset 0
		rand 0
	)
	
	(method (init owner)
		(self
			view: (owner view?)
			client: owner
			ignoreActors: 1
		)
		(= loop (- (NumLoops self) 2))
		(owner head: self)
		(super init:)
		(self hide: signal: (| (self signal?) fixPriOn))
	)
	
	(method (doit)
		(cond 
			(
				(and
					(client normal?)
					(not (& (client signal?) hideActor))
					(client isStopped:)
					(== (client loop?) (- (NumLoops client) 1))
				)
				(if (and (& signal notUpd) (not (& signal startUpdOn)))
					(if (& (client signal?) notUpd)
						(return)
					else
						(self startUpd:)
					)
				)
				(self showSelf:)
			)
			((not (& signal hideActor))
				(self hide:)
			)
		)
		(super doit:)
	)
	
	(method (showSelf &tmp i temp1)
		(= temp1 0)
		(if (& signal hideActor)
			(= cel (client cel?))
			(= rand 0)
			(= offSet (+ (* (client cel?) 3) 1))
			(= cnt cycleSpeed)
		)
		(if
			(and
				(< (-- cnt) 1)
				(client moveHead?)
				(!= (NumLoops self) 6)
			)
			(= cnt cycleSpeed)
			(= cel
				[headCel (+ offSet (= rand (- (Random 0 2) 1)))]
			)
		)
		(for ((= i 0)) (< i (* 25 local24)) ((+= i 25))
			(if (== (client view?) [theXOffset i])
				(= temp1 1)
				(break)
			)
		)
		(if temp1
			(= xOffset [theXOffset (+ 1 i offSet rand)])
			(= yOffset [theYOffset (+ 1 i offSet rand)])
		else
			(= xOffset (= yOffset 0))
		)
		(self view: (client view?) priority: (client priority?))
		(self
			loop: (- (NumLoops self) 2)
			x: (+ (client x?) xOffset)
			y: (client y?)
			z: (+ (CelHigh view (client loop?) (client cel?)) yOffset)
			show:
		)
	)
)
