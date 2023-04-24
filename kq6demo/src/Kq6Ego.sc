;;; Sierra Script 1.0 - (do not remove this comment)
(script# 914)
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
					(not (& signal fixedLoop))
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
	
	(method (setLoop param1 &tmp theLooper)
		(if
			(= theLooper
				(cond 
					((== argc 0) (super setLoop:) 0)
					((not (IsObject param1)) (super setLoop: param1 &rest) 0)
					(else param1)
				)
			)
			(if looper (looper dispose:))
			((= looper theLooper) init: self &rest)
		)
	)
	
	(method (setScale param1)
		(if argc
			(super setScale: param1 &rest)
		else
			(super setScale:)
		)
		(= scaleSignal (| scaleSignal $0004))
	)
	
	(method (setHeading theHeading &tmp temp0)
		(if argc (= heading theHeading))
		(cond 
			(setHeadingCode (setHeadingCode doit: heading &rest))
			(
				(and
					(IsObject (ego looper?))
					((ego looper?) isKindOf: EgoGroop)
					(not (looper dontHead?))
				)
				(looper
					doit: self heading (if (>= argc 2) [theHeading 1] else 0)
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
				(if (and (>= argc 2) (IsObject [theHeading 1]))
					([theHeading 1] cue: &rest)
				)
			)
		)
	)
	
	(method (put)
		(super put: &rest)
		(theIconBar curIcon: (theIconBar walkIconItem?))
	)
	
	(method (reset param1 param2)
		(if (> argc 0) (ego loop: param1))
		(ego
			view: (if (> argc 1) param2 else 900)
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
			normal: 1
			setSpeed: currentSpeed
		)
		(if (and oldScaleSignal (== view 900))
			(cond 
				((& oldScaleSignal $0002) (= scaleSignal oldScaleSignal) (= maxScale oldMaxScale))
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
	
	(method (findStep &tmp temp0 temp1 temp2)
		(= temp0 (if (= temp2 (/ scaleX 15)) else 1))
		(= temp1 (if (= temp2 (/ scaleY 63)) else 1))
		(if (or (!= temp0 xStep) (!= temp1 yStep))
			(self setStep: temp0 temp1 1)
		)
	)
)
