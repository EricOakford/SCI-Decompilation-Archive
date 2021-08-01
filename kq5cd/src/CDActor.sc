;;; Sierra Script 1.0 - (do not remove this comment)
(script# 931)
(include sci.sh)
(use Main)
(use Timer)
(use User)
(use Actor)
(use System)


(class MyLooper of Code
	(properties)
	
	(method (doit param1 param2)
		(EgoHeadMove param1 param2)
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		syncMouth 0
		client 0
		cycleCnt 0
		moveHead 1
		headCel {15372406}
	)
	
	(method (init param1)
		(= view (param1 view?))
		(= loop (- (NumLoops param1) 4))
		(self
			client: param1
			ignoreActors: 1
			posn:
				(param1 x?)
				(param1 y?)
				(CelHigh view (param1 loop?) (param1 cel?))
		)
		(param1 head: self)
		(super init:)
		(if moveHead (self cue: look:))
	)
	
	(method (doit)
		(= x (client x?))
		(= y (client y?))
		(= z
			(CelHigh (client view?) (client loop?) (client cel?))
		)
		(if (and moveHead (!= view (client view?)))
			(= view (client view?))
			(= loop (- (NumLoops client) 4))
		)
		(super doit:)
	)
	
	(method (show)
		(self look:)
		(super show: &rest)
	)
	
	(method (cue)
		(if
			(and
				(not (curRoom script?))
				moveHead
				(not (client mover?))
			)
			(client look: (- (Random 0 2) 1))
			(self look:)
		)
		(Timer set60ths: self (Random 60 150))
	)
	
	(method (look &tmp clientLoop clientLookingDir)
		(if
			(==
				(= clientLoop (client loop?))
				(- (NumLoops client) 1)
			)
			(= clientLoop (client cel?))
			(= clientLookingDir (client lookingDir?))
		else
			(= clientLookingDir 0)
		)
		(= cel
			(+
				(& (StrAt headCel clientLoop) $000f)
				clientLookingDir
			)
		)
	)
)

(class CDActor of Actor
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
		head 0
		gesture 0
		oldScript 0
		caller 0
		lookingDir 0
	)
	
	(method (init param1)
		(super init:)
		(self head: 0 gesture: (if argc param1 else 0))
	)
	
	(method (cue)
		(if head (head setCel: 0 syncMouth: 0 setCycle: 0))
		(if gesture (self setScript: oldScript))
		(if caller (caller cue:))
	)
)

(class Body of Ego
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
		head 0
		caller 0
		lookingDir 1
		normal 1
	)
	
	(method (init)
		(super init:)
		(if (not (IsObject head))
			((= head (Head new:)) init: self)
		)
		(= looper MyLooper)
	)
	
	(method (dispose)
		(= head 0)
		(super dispose:)
	)
	
	(method (posn param1 param2)
		(super posn: param1 param2 &rest)
		(if (IsObject head) (head doit:))
	)
	
	(method (setPri)
		(super setPri: &rest)
		(if (IsObject head) (head setPri: &rest))
	)
	
	(method (cue)
		(if (IsObject head)
			(head setCel: 0 syncMouth: 0 setCycle: 0)
		)
		(if caller (caller cue:))
	)
	
	(method (setHeading)
		(super setHeading: &rest)
		(if (IsObject head)
			(head cel: (& (StrAt (head headCel?) loop) $000f))
		)
	)
	
	(method (look theLookingDir)
		(= lookingDir theLookingDir)
	)
)
