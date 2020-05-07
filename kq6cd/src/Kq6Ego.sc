;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQ6EGO) ;914
(include game.sh)
(use Main)
(use EgoGroop)
(use Scaler)
(use Grooper)
(use Ego)
(use Motion)

(public
	Body 0
)

(class Body of Ego
	(properties
		normal TRUE
		currentSpeed 0
		setHeadingCode 0
		oldScaleSignal 0
		oldMaxScale 0
		oldBackSize 0
		oldFrontY 0
		oldBackY 0
		oldXStep 6
		oldYStep 2
	)
	
	(method (doit &tmp theLoop temp1)
		(super doit:)
		(cond 
			((self isStopped:)
				(if
					(and
						(!= (= theLoop loop) (= temp1 (- (NumLoops self) 1)))
						(cast contains: self)
						(or (== (ego view?) 900) (== (ego view?) 308))
						(IsObject cycler)
						(not (cycler isKindOf: GradualCycler))
						normal
					)
					(self loop: temp1 cel: theLoop)
				)
			)
			(
				(and
					normal
					(== loop (- (NumLoops self) 1))
					(not (& signal $0800))
				)
				(self loop: cel)
			)
		)
	)
	
	(method (dispose)
		(= oldScaleSignal
			(= oldMaxScale
				(= oldBackSize (= oldFrontY (= oldBackY 0)))
			)
		)
		(self setScale: 0)
		(super dispose:)
	)
	
	(method (setLoop newLoop &tmp theLooper)
		(if
			(= theLooper
				(cond 
					((== argc 0) (super setLoop:) 0)
					((not (IsObject newLoop)) (super setLoop: newLoop &rest) 0)
					(else newLoop)
				)
			)
			(if looper (looper dispose:))
			((= looper theLooper) init: self &rest)
		)
	)
	
	(method (setScale theY)
		(if argc
			(super setScale: theY &rest)
		else
			(super setScale:)
		)
		(= scaleSignal (| scaleSignal noStepScale))
	)
	
	(method (setHeading h &tmp temp0)
		(if argc (= heading h))
		(cond 
			(setHeadingCode (setHeadingCode doit: heading &rest))
			(
				(and
					(IsObject (ego looper?))
					((ego looper?) isKindOf: EgoGroop)
					(not (looper dontHead?))
				)
				(looper
					doit: self heading (if (>= argc 2) [h 1] else 0)
				)
			)
			(else
				(if (IsObject (ego looper?))
					(if (not ((ego looper?) isKindOf: EgoGroop))
						(= temp0 1)
					else
						(= temp0 0)
					)
				else
					(= temp0 1)
				)
				(if temp0 (DirLoop self heading))
				(if (and (>= argc 2) (IsObject [h 1]))
					([h 1] cue: &rest)
				)
			)
		)
	)
	
	(method (put)
		(super put: &rest)
		(theIconBar curIcon: (theIconBar walkIconItem?))
	)
	
	(method (reset theLoop theView)
		(if (> argc 0) (ego loop: theLoop))
		(ego
			view: (if (> argc 1) theView else 900)
			signal: 4096
			z: 0
			setLoop: -1
			setLoop: theEgoGroop
			setPri: -1
			setMotion: 0
			illegalBits: 0
			ignoreActors: 0
			ignoreHorizon: 1
			setStep: oldXStep oldYStep
			setCycle: Walk
			normal: TRUE
			setSpeed: currentSpeed
		)
		(if (and oldScaleSignal (== view 900))
			(cond 
				((& oldScaleSignal autoScale) (= scaleSignal oldScaleSignal) (= maxScale oldMaxScale))
				((or oldMaxScale oldBackSize oldFrontY oldBackY)
					(ego
						setScale: Scaler oldMaxScale oldBackSize oldFrontY oldBackY
					)
				)
				(else (ego setScale:))
			)
			(= oldScaleSignal
				(= oldMaxScale
					(= oldBackSize (= oldFrontY (= oldBackY 0)))
				)
			)
		)
	)
	
	(method (findStep &tmp txStep tyStep stepVal)
		(= txStep (if (= stepVal (/ scaleX 15)) else 1))
		(= tyStep (if (= stepVal (/ scaleY 63)) else 1))
		(if (or (!= txStep xStep) (!= tyStep yStep))
			(self setStep: txStep tyStep 1)
		)
	)
)
