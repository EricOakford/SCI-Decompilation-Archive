;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64003)
(include sci.sh)
(use Main)
(use TPSound)
(use String)
(use Actor)
(use System)


(procedure (localproc_00fc param1 param2 &tmp temp0)
	(= temp0 (if (== param2 -1) 0 else param2))
	(switch param1
		(0
			(if (!= (scoreOnes loop?) temp0)
				(UpdateScreenItem
					(scoreOnes loop: temp0 cel: 0 yourself:)
				)
			)
		)
		(1
			(if (!= (scoreTens loop?) temp0)
				(UpdateScreenItem
					(scoreTens loop: temp0 cel: 0 yourself:)
				)
			)
		)
		(2
			(if (!= (scoreHundreds loop?) temp0)
				(UpdateScreenItem
					(scoreHundreds loop: temp0 cel: 0 yourself:)
				)
			)
		)
	)
)

(class Indicator of Prop
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 200
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 3
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		oUnderMouseList 1
		active 0
		caller 0
		lastTicks 0
		ticks 0
	)
	
	(method (init theView theX theY)
		(= x theX)
		(= y theY)
		(= view theView)
		(super init: &rest)
	)
	
	(method (doit)
		(if
			(<=
				(= ticks (- ticks (Abs (- gameTime lastTicks))))
				0
			)
			(= ticks cycleSpeed)
			(cond 
				(
				(and (> oUnderMouseList 0) (<= cel (self lastCel:)))
					(self show:)
					(++ cel)
					(if (> cel (self lastCel:))
						(if caller (caller cue:))
						(= active 0)
					)
					(super doit:)
				)
				((and (< oUnderMouseList 0) (> cel 0)) (-- cel) (self show:) (super doit:))
				(else (if caller (caller cue:)) (= active 0) (super doit:))
			)
		)
		(= lastTicks gameTime)
	)
	
	(method (cue)
		(cond 
			((and (== oUnderMouseList 1) (== loop 9)) (= cel (= loop 0)))
			((== oUnderMouseList 1) (++ loop) (= cel 0))
			((== loop 0) (= loop 9) (= cel (self lastCel:)))
			(else (-- loop) (= cel (self lastCel:)))
		)
	)
	
	(method (advance theOUnderMouseList theCaller)
		(= lastTicks gameTime)
		(= ticks 1)
		(if argc (= caller theCaller))
		(= active 1)
		(if (== (= oUnderMouseList theOUnderMouseList) -1)
			(self cue:)
		)
	)
	
	(method (stop)
		(if active (= active 0) (self cue:))
	)
)

(instance scoreOnes of Indicator
	(properties)
)

(instance scoreTens of Indicator
	(properties)
)

(instance scoreHundreds of Indicator
	(properties)
)

(instance scoreSound of TPSound
	(properties)
)

(class PointGauge of Code
	(properties
		scratch 0
		killAllEventHogs 0
		registerGlobalHandler 0
		unregisterGlobalHandler -1
		margin 0
		view 0
		x 0
		y 0
	)
	
	(method (init &tmp theX)
		(= theX x)
		(scoreHundreds init: view theX y &rest)
		(= theX
			(+ (= theX (+ theX (CelWide view 0 0))) margin)
		)
		(scoreTens init: view theX y &rest)
		(= theX
			(+ (= theX (+ theX (CelWide view 0 0))) margin)
		)
		(scoreOnes init: view theX y &rest)
		(if (!= unregisterGlobalHandler -1)
			(scoreSound number: unregisterGlobalHandler)
		)
		(= registerGlobalHandler 1)
	)
	
	(method (doit)
		(if
			(and
				(!= score killAllEventHogs)
				(not registerGlobalHandler)
			)
			(cond 
				((> score killAllEventHogs) (self advance:))
				((< score killAllEventHogs) (self advance: 0))
			)
		)
		(if (scoreOnes active?) (scoreOnes doit:))
		(if (scoreTens active?) (scoreTens doit:))
		(if (scoreHundreds active?) (scoreHundreds doit:))
	)
	
	(method (dispose)
		(scoreOnes dispose:)
		(scoreTens dispose:)
		(scoreHundreds dispose:)
		(scoreSound dispose:)
		(= registerGlobalHandler 0)
		(super dispose: &rest)
	)
	
	(method (show)
		(if (not (scoreOnes plane?)) (self init:))
		(scoreOnes show:)
		(scoreTens show:)
		(scoreHundreds show:)
		(self update:)
		(= registerGlobalHandler 0)
	)
	
	(method (advance param1 &tmp temp0 temp1 scoreTensLoop)
		(= registerGlobalHandler 1)
		(if (or (not argc) param1)
			(++ killAllEventHogs)
			(= temp0 9)
			(= temp1 1)
		else
			(-- killAllEventHogs)
			(= temp0 0)
			(= temp1 -1)
		)
		(if (== (scoreOnes loop?) temp0)
			(= scoreTensLoop (scoreTens loop?))
			(scoreTens
				advance: temp1 (if (!= temp1 -1) scoreTens else 0)
			)
			(if (== scoreTensLoop temp0)
				(scoreHundreds
					advance: temp1 (if (!= temp1 -1) scoreHundreds else 0)
				)
			)
		)
		(scoreOnes advance: temp1 self)
	)
	
	(method (cue &tmp temp0)
		(if (!= (scoreOnes oUnderMouseList?) -1)
			(scoreOnes cue:)
		)
		(= registerGlobalHandler 0)
	)
	
	(method (hide)
		(= registerGlobalHandler 1)
		(scoreOnes hide:)
		(scoreTens hide:)
		(scoreHundreds hide:)
	)
	
	(method (handleEvent)
		(return 0)
	)
	
	(method (update &tmp temp0 temp1 temp2 temp3)
		(scoreOnes active: 0)
		(scoreTens active: 0)
		(scoreHundreds active: 0)
		(= temp1 ((= temp0 (Str format: {%d} score)) size:))
		(= temp2 0)
		(while (< temp2 temp1)
			(= temp3 (- (temp0 at: (- (- temp1 temp2) 1)) 48))
			(localproc_00fc temp2 temp3)
			(++ temp2)
		)
		(= temp2 0)
		(while (< temp2 (- 3 (- temp1 1)))
			(localproc_00fc (- 3 temp2) -1)
			(++ temp2)
		)
		(= killAllEventHogs score)
		(temp0 dispose:)
	)
	
	(method (registerDefaultHandler param1)
		(if
		(and (> param1 0) (!= unregisterGlobalHandler -1))
			(scoreSound lThumbLoop:)
		)
		(if (< (= score (+ score param1)) 0) (= score 0))
		(if (> score 999) (= score 999))
		(self update:)
	)
)
